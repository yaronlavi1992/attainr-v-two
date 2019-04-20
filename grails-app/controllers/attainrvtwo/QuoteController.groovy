package attainrvtwo

import grails.validation.ValidationException
import static org.springframework.http.HttpStatus.*

class QuoteController {

    QuoteService quoteService

    static allowedMethods = [save: "POST", update: "PUT", delete: "DELETE"]

    def index(Integer max) {
        params.max = Math.min(max ?: 10, 100)
        respond quoteService.list(params), model:[quoteCount: quoteService.count()]
    }

    def show(Long id) {
        respond quoteService.get(id)
    }

    def create() {
        respond new Quote(params)
    }

    def save(Quote quote) {
        if (quote == null) {
            notFound()
            return
        }

        try {
            quoteService.save(quote)
        } catch (ValidationException e) {
            respond quote.errors, view:'create'
            return
        }

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.created.message', args: [message(code: 'quote.label', default: 'Quote'), quote.id])
                redirect quote
            }
            '*' { respond quote, [status: CREATED] }
        }
    }

    def edit(Long id) {
        respond quoteService.get(id)
    }

    def update(Quote quote) {
        if (quote == null) {
            notFound()
            return
        }

        try {
            quoteService.save(quote)
        } catch (ValidationException e) {
            respond quote.errors, view:'edit'
            return
        }

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.updated.message', args: [message(code: 'quote.label', default: 'Quote'), quote.id])
                redirect quote
            }
            '*'{ respond quote, [status: OK] }
        }
    }

    def delete(Long id) {
        if (id == null) {
            notFound()
            return
        }

        quoteService.delete(id)

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.deleted.message', args: [message(code: 'quote.label', default: 'Quote'), id])
                redirect action:"index", method:"GET"
            }
            '*'{ render status: NO_CONTENT }
        }
    }

    protected void notFound() {
        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.not.found.message', args: [message(code: 'quote.label', default: 'Quote'), params.id])
                redirect action: "index", method: "GET"
            }
            '*'{ render status: NOT_FOUND }
        }
    }
}
