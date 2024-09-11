package task



import static org.springframework.http.HttpStatus.*
import grails.transaction.Transactional

@Transactional(readOnly = true)
class TekTaskController {

    static allowedMethods = [save: "POST", update: "PUT", delete: "DELETE"]

    def index(Integer max) {
        params.max = Math.min(max ?: 10, 100)
        respond TekTask.list(params), model:[tekTaskInstanceCount: TekTask.count()]
    }

    def show(TekTask tekTaskInstance) {
        respond tekTaskInstance
    }

    def create() {
        respond new TekTask(params)
    }

    @Transactional
    def save(TekTask tekTaskInstance) {
        if (tekTaskInstance == null) {
            notFound()
            return
        }

        if (tekTaskInstance.hasErrors()) {
            respond tekTaskInstance.errors, view:'create'
            return
        }

        tekTaskInstance.save flush:true

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.created.message', args: [message(code: 'tekTask.label', default: 'TekTask'), tekTaskInstance.id])
                redirect tekTaskInstance
            }
            '*' { respond tekTaskInstance, [status: CREATED] }
        }
    }

    def edit(TekTask tekTaskInstance) {
        respond tekTaskInstance
    }

    @Transactional
    def update(TekTask tekTaskInstance) {
        if (tekTaskInstance == null) {
            notFound()
            return
        }

        if (tekTaskInstance.hasErrors()) {
            respond tekTaskInstance.errors, view:'edit'
            return
        }

        tekTaskInstance.save flush:true

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.updated.message', args: [message(code: 'TekTask.label', default: 'TekTask'), tekTaskInstance.id])
                redirect tekTaskInstance
            }
            '*'{ respond tekTaskInstance, [status: OK] }
        }
    }

    @Transactional
    def delete(TekTask tekTaskInstance) {

        if (tekTaskInstance == null) {
            notFound()
            return
        }

        tekTaskInstance.delete flush:true

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.deleted.message', args: [message(code: 'TekTask.label', default: 'TekTask'), tekTaskInstance.id])
                redirect action:"index", method:"GET"
            }
            '*'{ render status: NO_CONTENT }
        }
    }

    protected void notFound() {
        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.not.found.message', args: [message(code: 'tekTask.label', default: 'TekTask'), params.id])
                redirect action: "index", method: "GET"
            }
            '*'{ render status: NOT_FOUND }
        }
    }
}
