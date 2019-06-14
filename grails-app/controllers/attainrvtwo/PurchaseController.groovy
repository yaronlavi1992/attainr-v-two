package attainrvtwo

import grails.validation.ValidationException

import static org.springframework.http.HttpStatus.*

class PurchaseController {

    PurchaseService purchaseService
    UserService userService

    static allowedMethods = [save: "POST", update: "PUT", delete: "DELETE"]

    def index(Integer max) {
        params.max = Math.min(max ?: 10, 100)
        respond purchaseService.list(params), model: [purchaseCount: purchaseService.count()]
    }

    def show(Long id) {
        if (session.role == RoleOf.DEPARTMENT_MANAGER && session.departmentApp) {
            Purchase purchase = purchaseService.get(id)
            Approval departmentApp = new Approval()
            departmentApp.approved = true
            purchase.departmentApproval = departmentApp
            purchaseService.save(purchase)
            session.departmentApp = false
        }
        respond purchaseService.get(id)
    }

    def create() {
        respond new Purchase(params)
    }


    def save(Purchase purchase) {

        //set miscellaneous purchase properties
        purchase.user = userService.get(session.userId)
        purchase.status = PurchaseStatus.IN_PROGRESS
        purchase.paymentDate = params.orderDate as Date
        purchase.description = params.freeText

        //extract first item
        String desc = params.description
        Integer quantity = params.packQuantity
        Double price = params.packPrice as Double
        Double externalFunding = params.externalFunding
        Double totalPrice = params.totalPrice as Double


        String descKey
        String quantityKey
        String packPriceKey
        String externalFundingKey
        String totalPriceKey
        int i = 0
        List<PurchaseItem> items = []

        while (desc) { // if not present in form - break the loop
            //build item and add to list
            PurchaseItem item = buildPurchaseItem(purchase, desc, quantity, price, externalFunding, totalPrice)
            items.add(item)

            // get next line args (if any)
            descKey = "description$i"
            quantityKey = "packQuantity$i"
            packPriceKey = "packPrice$i"
            externalFundingKey = "externalFunding$i"
            totalPriceKey = "totalPrice$i"

            desc = params.descKey
            quantity = params.quantityKey
            price = params.packPriceKey
            externalFunding = params.externalFundingKey
            totalPrice = params.totalPriceKey
            i++
        }
        purchase.purchaseItems = items

        List<Quote> quoteList = []
        ['first', 'second', 'third'].each {prefix ->
            Quote q = readQuote(purchase, params, prefix)
            if (q) {
                q.save(flush: true)
                quoteList.add(q)
            }
        }
        purchase.quotes = quoteList


        if (purchase == null) {
            notFound()
            return
        }

        try {
            purchaseService.save(purchase)
        } catch (ValidationException e) {
            respond purchase.errors, view: 'create'
            return
        }

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.created.message', args: [message(code: 'purchase.label', default: 'Purchase'), purchase.id])
                redirect purchase
            }
            '*' { respond purchase, [status: CREATED] }
        }
    }

    def Quote readQuote(purchase, params, String prefix) {
        String num
        String name
        Double price
        MyFile file
        String priceKeyCheck = "${prefix}QuotePrice"
        String priceCheck = params.get(priceKeyCheck)
        if (priceCheck) {
            String numberKey = "${prefix}QuoteNumber"
            String nameKey = "${prefix}SupplierName"
            String priceKey = "${prefix}QuotePrice"
            String fileKey = "${prefix}QuoteAttachment"

            num = params.get(numberKey)
            name = params.get(nameKey)
            price = params.get(priceKey) as Double

            file = new MyFile(params)
            file.fileName = name
            file.myFile = params.get(fileKey) as byte[]
            file.save(flush: true)
        }

        if (num) {
            Quote quote = new Quote()
            quote.number = num
            quote.name = name
            quote.price = price
            quote.file = file
            quote.purchase = purchase
            return quote
        } else {
            return null
        }
    }

    def PurchaseItem buildPurchaseItem(purchase, String description, Integer quantity, Double price, Double externalFund, Double totalPrice) {
        def item = new PurchaseItem()
        item.purchase = purchase
        item.description = description
        item.packQuantity = quantity
        item.packPrice = price
        item.externalFunding = externalFund
        item.totalItemPrice = totalPrice
        return item
    }

    def edit(Long id) {
        respond purchaseService.get(id)
    }

    def update(Purchase purchase) {
        if (purchase == null) {
            notFound()
            return
        }

        try {
            purchaseService.save(purchase)
        } catch (ValidationException e) {
            respond purchase.errors, view: 'edit'
            return
        }

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.updated.message', args: [message(code: 'purchase.label', default: 'Purchase'), purchase.id])
                redirect purchase
            }
            '*' { respond purchase, [status: OK] }
        }
    }

    def delete(Long id) {
        if (id == null) {
            notFound()
            return
        }

        purchaseService.delete(id)

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.deleted.message', args: [message(code: 'purchase.label', default: 'Purchase'), id])
                redirect action: "index", method: "GET"
            }
            '*' { render status: NO_CONTENT }
        }
    }

    protected void notFound() {
        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.not.found.message', args: [message(code: 'purchase.label', default: 'Purchase'), params.id])
                redirect action: "index", method: "GET"
            }
            '*' { render status: NOT_FOUND }
        }
    }
}
