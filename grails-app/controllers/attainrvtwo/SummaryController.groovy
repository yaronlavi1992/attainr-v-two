package attainrvtwo

import grails.validation.ValidationException
import static org.springframework.http.HttpStatus.*

class SummaryController {

    SummaryService summaryService

    static allowedMethods = [save: "POST", update: "PUT", delete: "DELETE"]

    def index(Integer max) {
        params.max = Math.min(max ?: 10, 100)
        respond summaryService.list(params), model:[summaryCount: summaryService.count()]
    }

    def show(Long id) {
        respond summaryService.get(id)
    }

    def create() {
        respond new Summary(params)
    }

    def save(Summary summary) {
        if (summary == null) {
            notFound()
            return
        }

        try {
            summaryService.save(summary)
        } catch (ValidationException e) {
            respond summary.errors, view:'create'
            return
        }

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.created.message', args: [message(code: 'summary.label', default: 'Summary'), summary.id])
                redirect summary
            }
            '*' { respond summary, [status: CREATED] }
        }
    }

    def edit(Long id) {
        respond summaryService.get(id)
    }

    def update(Summary summary) {
        if (summary == null) {
            notFound()
            return
        }

        try {
            summaryService.save(summary)
        } catch (ValidationException e) {
            respond summary.errors, view:'edit'
            return
        }

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.updated.message', args: [message(code: 'summary.label', default: 'Summary'), summary.id])
                redirect summary
            }
            '*'{ respond summary, [status: OK] }
        }
    }

    def delete(Long id) {
        if (id == null) {
            notFound()
            return
        }

        summaryService.delete(id)

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.deleted.message', args: [message(code: 'summary.label', default: 'Summary'), id])
                redirect action:"index", method:"GET"
            }
            '*'{ render status: NO_CONTENT }
        }
    }

    protected void notFound() {
        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.not.found.message', args: [message(code: 'summary.label', default: 'Summary'), params.id])
                redirect action: "index", method: "GET"
            }
            '*'{ render status: NOT_FOUND }
        }
    }
}
