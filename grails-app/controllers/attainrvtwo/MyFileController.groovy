package attainrvtwo

import grails.validation.ValidationException
import org.springframework.util.DigestUtils

import static org.springframework.http.HttpStatus.*

class MyFileController {

    MyFileService myFileService

    static allowedMethods = [save: "POST", update: "PUT", delete: "DELETE"]

    def index(Integer max) {
        params.max = Math.min(max ?: 10, 100)
        respond myFileService.list(params), model:[myFileCount: myFileService.count()]
    }

    def show(Long id) {
        respond myFileService.get(id)
    }

    def create() {
        respond new MyFile(params)
    }

    def save(MyFile myFile) {
        if (myFile == null) {
            notFound()
            return
        }

        try {
            myFileService.save(myFile)
        } catch (ValidationException e) {
            respond myFile.errors, view:'create'
            return
        }

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.created.message', args: [message(code: 'myFile.label', default: 'MyFile'), myFile.id])
                redirect myFile
            }
            '*' { respond myFile, [status: CREATED] }
        }
    }

    def edit(Long id) {
        respond myFileService.get(id)
    }

    def update(MyFile myFile) {
        if (myFile == null) {
            notFound()
            return
        }

        try {
            myFileService.save(myFile)
        } catch (ValidationException e) {
            respond myFile.errors, view:'edit'
            return
        }

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.updated.message', args: [message(code: 'myFile.label', default: 'MyFile'), myFile.id])
                redirect myFile
            }
            '*'{ respond myFile, [status: OK] }
        }
    }

    def delete(Long id) {
        if (id == null) {
            notFound()
            return
        }

        myFileService.delete(id)

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.deleted.message', args: [message(code: 'myFile.label', default: 'MyFile'), id])
                redirect action:"index", method:"GET"
            }
            '*'{ render status: NO_CONTENT }
        }
    }

    protected void notFound() {
        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.not.found.message', args: [message(code: 'myFile.label', default: 'MyFile'), params.id])
                redirect action: "index", method: "GET"
            }
            '*'{ render status: NOT_FOUND }
        }
    }

    def showFile(Long id){
        MyFile myFile = myFileService.get(id)

        byte[] imageInByte = myFile.myFile

        response.setHeader('Content-length', imageInByte.length.toString())

        response.contentType = findContentType(myFile.fileName) // or the appropriate image content type

        response.outputStream << imageInByte
        response.outputStream.flush()

    }

    String findContentType(String name) {
        def suffix = name.toLowerCase().replaceAll(/^.*?\.(\w+)$/, '$   1')
        println "!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!! name: $name\tsuffix: $suffix"
        switch (suffix) {
            case 'pdf': return 'application/pdf'
            case 'png': return 'image/png'
        }

    }

    String hash(String origin) {
        return DigestUtils.encodeAsSHA256(origin)
    }
}
