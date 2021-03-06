package attainrvtwo

import grails.validation.ValidationException

import static org.springframework.http.HttpStatus.*

class UserController {

    UserService userService

    static allowedMethods = [save: "POST", update: "PUT", delete: "DELETE"]

    def index(Integer max) {
        params.max = Math.min(max ?: 10, 100)
        respond userService.list(params), model: [userCount: userService.count()]
    }

    def show(Long id) {
        respond userService.get(id)
    }

    def create() {
        respond new User(params)
    }

    def save(User user) {
        user.password = PasswordHasher.encrypt(params.password)

        if (user == null) {
            notFound()
            return
        }

        try {
            userService.save(user)
        } catch (ValidationException e) {
            respond user.errors, view: 'create'
            return
        }

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.created.message', args: [message(code: 'user.label', default: 'User'), user.id])
                redirect user
            }
            '*' { respond user, [status: CREATED] }
        }
    }

    def edit(Long id) {
        respond userService.get(id)
    }

    def update(User user) {
        user.password = PasswordHasher.encrypt(params.password)

        if (user == null) {
            notFound()
            return
        }

        try {
            userService.save(user)
        } catch (ValidationException e) {
            respond user.errors, view: 'edit'
            return
        }

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.updated.message', args: [message(code: 'user.label', default: 'User'), user.id])
                redirect user
            }
            '*' { respond user, [status: OK] }
        }
    }

    def delete(Long id) {
        if (id == null) {
            notFound()
            return
        }

        userService.delete(id)

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.deleted.message', args: [message(code: 'user.label', default: 'User'), id])
                redirect action: "index", method: "GET"
            }
            '*' { render status: NO_CONTENT }
        }
    }

    def statusDisplay() {
        if (session.permission == PermissionOf.LOW) {
            redirect(controller: 'committeeManager', action: 'index')
        } else if (session.permission == PermissionOf.MID && session.committee == CommitteeOf.MANAGEMENT) {
            redirect(controller: 'departmentManager', action: 'index')
        } else if (session.permission == PermissionOf.HIGH) {
            redirect(controller: 'management', action: 'index')
        }
    }

    def login() {
        def userFound = false
        User.list().each {
            if (params.username == it.name && PasswordHasher.encrypt(params.password) == it.password) {
                session.user = it.name
                session.userId = it.id
                session.committee = (it?.committee).name
                session.committeeId = (it.committee).id
                session.department = (it.committee).department
                session.permission = it.permission
                session.role = it.role
                userFound = true
            }
        }
        if (userFound) {
            statusDisplay()
        } else {
            flash.message = "???????????? ?????????????? ????????????"
            redirect(action: 'index')
        }
    }

    def logout() {
        session.user = null
        session.invalidate()
        redirect(action: 'index')
    }

    def showUsers(Integer max) {
        params.max = Math.min(max ?: 10, 100)
        respond userService.list(params), model: [userCount: userService.count()]
    }

    protected void notFound() {
        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.not.found.message', args: [message(code: 'user.label', default: 'User'), params.id])
                redirect action: "index", method: "GET"
            }
            '*' { render status: NOT_FOUND }
        }
    }
}
