package attainrvtwo

import grails.validation.ValidationException

import static org.springframework.http.HttpStatus.*

class SummaryController {

    SummaryService summaryService
    CommitteeService committeeService

    static allowedMethods = [save: "POST", update: "PUT", delete: "DELETE"]

    def index(Integer max) {
        List<Summary> summaryList
        if (session.role == RoleOf.COMMITTEE_MANAGER) {
            Committee selectedCommittee = committeeService.get(session.committeeId)
            summaryList = Summary.findAllByCommittee(selectedCommittee)
        } else if (session.role == RoleOf.DEPARTMENT_MANAGER || RoleOf.COMMUNITY_SECRETARY) {
            List<Committee> committeeList = Committee.findAllByDepartment(session.department)
            summaryList = Summary.findAllByCommitteeInList(committeeList)
        }
        params.max = Math.min(max ?: 10, 100)
        respond summaryList, model: [summaryCount: summaryService.count()]
    }

    def show(Long id) {
        respond summaryService.get(id)
    }

    def create() {
        def myFiles = new MyFile(params)
        respond new Summary(params)
    }

    def save(Summary summary) {
        summary.description = params.description
        summary.committee = committeeService.get(params.committee.id as Integer)
        summary.file = summaryFile(params)

        if (summary == null) {
            notFound()
            return
        }

        try {
            summaryService.save(summary)
        } catch (ValidationException e) {
            respond summary.errors, view: 'create'
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
    def MyFile summaryFile(params) {
        MyFile file = new MyFile(params)
        file.fileName = params.fileName
        file.myFile = (params.summaryFile).getBytes()
        file.save()
        return file
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
            respond summary.errors, view: 'edit'
            return
        }

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.updated.message', args: [message(code: 'summary.label', default: 'Summary'), summary.id])
                redirect summary
            }
            '*' { respond summary, [status: OK] }
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
                redirect action: "index", method: "GET"
            }
            '*' { render status: NO_CONTENT }
        }
    }

    protected void notFound() {
        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.not.found.message', args: [message(code: 'summary.label', default: 'Summary'), params.id])
                redirect action: "index", method: "GET"
            }
            '*' { render status: NOT_FOUND }
        }
    }
}
