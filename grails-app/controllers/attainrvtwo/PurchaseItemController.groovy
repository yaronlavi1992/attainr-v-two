package attainrvtwo

import grails.validation.ValidationException
import static org.springframework.http.HttpStatus.*

class PurchaseItemController {

    PurchaseItemService purchaseItemService

    static allowedMethods = [save: "POST", update: "PUT", delete: "DELETE"]

    def index(Integer max) {
        params.max = Math.min(max ?: 10, 100)
        respond purchaseItemService.list(params), model:[purchaseItemCount: purchaseItemService.count()]
    }

    def show(Long id) {
        respond purchaseItemService.get(id)
    }

    def create() {
        respond new PurchaseItem(params)
    }

    def save(PurchaseItem purchaseItem) {
        if (purchaseItem == null) {
            notFound()
            return
        }

        try {
            purchaseItemService.save(purchaseItem)
        } catch (ValidationException e) {
            respond purchaseItem.errors, view:'create'
            return
        }

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.created.message', args: [message(code: 'purchaseItem.label', default: 'PurchaseItem'), purchaseItem.id])
                redirect purchaseItem
            }
            '*' { respond purchaseItem, [status: CREATED] }
        }
    }

    def edit(Long id) {
        respond purchaseItemService.get(id)
    }

    def update(PurchaseItem purchaseItem) {
        if (purchaseItem == null) {
            notFound()
            return
        }

        try {
            purchaseItemService.save(purchaseItem)
        } catch (ValidationException e) {
            respond purchaseItem.errors, view:'edit'
            return
        }

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.updated.message', args: [message(code: 'purchaseItem.label', default: 'PurchaseItem'), purchaseItem.id])
                redirect purchaseItem
            }
            '*'{ respond purchaseItem, [status: OK] }
        }
    }

    def delete(Long id) {
        if (id == null) {
            notFound()
            return
        }

        purchaseItemService.delete(id)

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.deleted.message', args: [message(code: 'purchaseItem.label', default: 'PurchaseItem'), id])
                redirect action:"index", method:"GET"
            }
            '*'{ render status: NO_CONTENT }
        }
    }

    protected void notFound() {
        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.not.found.message', args: [message(code: 'purchaseItem.label', default: 'PurchaseItem'), params.id])
                redirect action: "index", method: "GET"
            }
            '*'{ render status: NOT_FOUND }
        }
    }
}
