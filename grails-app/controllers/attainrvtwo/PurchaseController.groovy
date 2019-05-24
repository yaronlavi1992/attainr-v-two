package attainrvtwo

import grails.validation.ValidationException

import static org.springframework.http.HttpStatus.*

class PurchaseController {

    PurchaseService purchaseService

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
