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
        Purchase purchase = purchaseService.get(id)
        respond purchase
    }

    def create() {
        respond new Purchase(params)
    }


    def save(Purchase purchase) {
        //if the user that created a new purchase is a department manager, the purchase is approved by default(departmentApprocal)
        if (session.permission == PermissionOf.MID && session.committee == CommitteeOf.MANAGEMENT) {
            Approval departmentApp = new Approval()
            departmentApp.approved = true
            purchase.departmentApproval = departmentApp
        }

        //set miscellaneous purchase properties
        purchase.name = params.purchaseName
        purchase.user = userService.get(session.userId)
        purchase.status = PurchaseStatus.IN_PROGRESS
        purchase.paymentDate = params.orderDate as Date
        purchase.description = params.freeText
        purchase.totalPurchasePrice = params.totalPurchasePrice as Double

        //extract first item
        String desc = params.description
        Integer quantity = params.packQuantity as Integer
        Double price = params.packPrice as Double
        Double externalFunding = params.externalFunding as Double
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
            item.save()
            items.add(item)

            // get next line args (if any)
            descKey = "description$i"
            quantityKey = "packQuantity$i"
            packPriceKey = "packPrice$i"
            externalFundingKey = "externalFunding$i"
            totalPriceKey = "totalPrice$i"

            desc = params.get(descKey)
            quantity = params.get(quantityKey) as Integer
            price = params.get(packPriceKey) as Double
            externalFunding = params.get(externalFundingKey) as Double
            totalPrice = params.get(totalPriceKey) as Double
            i++
        }
        purchase.purchaseItems = items

        List<Quote> quoteList = []
        ['first', 'second', 'third'].each {prefix ->
            Quote q = readQuote(purchase, params, prefix)
            if (q) {
                q.save()
                quoteList.add(q)
            }
        }
        purchase.quotes = quoteList
        purchase.quotes.sort{it.number}

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
            file.myFile = (params.get(fileKey)).getBytes()
            file.save()
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
        PurchaseItem item = new PurchaseItem()
        item.description = description
        item.packQuantity = quantity
        item.packPrice = price
        item.externalFunding = externalFund
        item.totalItemPrice = totalPrice
        item.purchase = purchase
        return item
    }

    def addReceipt(Long id) {
        Purchase purchase = purchaseService.get(id)
        List<Receipt> receiptList = []
        Receipt r = createReceipt(purchase, params)
        r.save()
        receiptList.add(r)
        purchase.receipts = receiptList
        purchase.status = PurchaseStatus.PAYMENT_REQUIRED
        purchaseService.save(purchase)
        redirect(controller: "Purchase", action: "show", id: purchase.id)
    }

    def Receipt createReceipt(purchase, params) {
        Receipt receipt = new Receipt()

        String sumKey = "sum"
        receipt.sum = params.get(sumKey) as Double

        MyFile file = new MyFile(params)
        file.fileName = params.fileName
        String fileKey = "myFile"
        file.myFile = (params.get(fileKey)).getBytes()
        file.save()
        receipt.file = file

        receipt.purchase = purchase
        return receipt
    }

    def attachReceipt(Long id) {
        respond purchaseService.get(id)
    }

    def showCompletedPurchases(Integer max) {
        params.max = Math.min(max ?: 10, 100)
        List<Purchase> purchaseList = purchaseService.list()
        if (session.role == RoleOf.COMMITTEE_MANAGER) {
            Committee committee = Committee.findByName(session.committee)
            List<User> userList = User.findAllByCommittee(committee)
            purchaseList = Purchase.findAllByUserInList(userList)
        } else if (session.role == RoleOf.DEPARTMENT_MANAGER) {
            List<Committee> committeeList = Committee.findAllByDepartment(session.department)
            List<User> userList = User.findAllByCommitteeInList(committeeList)
            purchaseList = Purchase.findAllByUserInList(userList)
        }
        purchaseList = purchaseList.findAll{it.status == PurchaseStatus.COMPLETE}
        respond purchaseList, model: [purchaseCount: purchaseService.count()]
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
