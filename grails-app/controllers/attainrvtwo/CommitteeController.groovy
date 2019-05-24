package attainrvtwo

import grails.validation.ValidationException

import static org.springframework.http.HttpStatus.*

class CommitteeController {

    CommitteeService committeeService

    static allowedMethods = [save: "POST", update: "PUT", delete: "DELETE"]

    def index(Integer max) {
        params.max = Math.min(max ?: 10, 100)
        respond committeeService.list(params), model: [committeeCount: committeeService.count()]
    }

    def show(Long id) {
        respond committeeService.get(id)
    }

    def create() {
        respond new Committee(params)
    }

    def save(Committee committee) {
        if (committee == null) {
            notFound()
            return
        }

        try {
            committeeService.save(committee)
        } catch (ValidationException e) {
            respond committee.errors, view: 'create'
            return
        }

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.created.message', args: [message(code: 'committee.label', default: 'Committee'), committee.id])
                redirect committee
            }
            '*' { respond committee, [status: CREATED] }
        }
    }

    def edit(Long id) {
        respond committeeService.get(id)
    }

    def update(Committee committee) {
        if (committee == null) {
            notFound()
            return
        }

        try {
            committeeService.save(committee)
        } catch (ValidationException e) {
            respond committee.errors, view: 'edit'
            return
        }

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.updated.message', args: [message(code: 'committee.label', default: 'Committee'), committee.id])
                redirect committee
            }
            '*' { respond committee, [status: OK] }
        }
    }

    def delete(Long id) {
        if (id == null) {
            notFound()
            return
        }

        committeeService.delete(id)

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.deleted.message', args: [message(code: 'committee.label', default: 'Committee'), id])
                redirect action: "index", method: "GET"
            }
            '*' { render status: NO_CONTENT }
        }
    }

    protected void notFound() {
        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.not.found.message', args: [message(code: 'committee.label', default: 'Committee'), params.id])
                redirect action: "index", method: "GET"
            }
            '*' { render status: NOT_FOUND }
        }
    }
}
