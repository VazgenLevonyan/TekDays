package sponsor



import static org.springframework.http.HttpStatus.*
import grails.transaction.Transactional

@Transactional(readOnly = true)
class TekSponsorController {

    static allowedMethods = [save: "POST", update: "PUT", delete: "DELETE"]

    def index(Integer max) {
        params.max = Math.min(max ?: 10, 100)
        respond TekSponsor.list(params), model:[tekSponsorInstanceCount: TekSponsor.count()]
    }

    def show(TekSponsor tekSponsorInstance) {
        respond tekSponsorInstance
    }

    def create() {
        respond new TekSponsor(params)
    }

    @Transactional
    def save(TekSponsor tekSponsorInstance) {
        if (tekSponsorInstance == null) {
            notFound()
            return
        }

        if (tekSponsorInstance.hasErrors()) {
            respond tekSponsorInstance.errors, view:'create'
            return
        }

        tekSponsorInstance.save flush:true

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.created.message', args: [message(code: 'tekSponsor.label', default: 'TekSponsor'), tekSponsorInstance.id])
                redirect tekSponsorInstance
            }
            '*' { respond tekSponsorInstance, [status: CREATED] }
        }
    }

    def edit(TekSponsor tekSponsorInstance) {
        respond tekSponsorInstance
    }

    @Transactional
    def update(TekSponsor tekSponsorInstance) {
        if (tekSponsorInstance == null) {
            notFound()
            return
        }

        if (tekSponsorInstance.hasErrors()) {
            respond tekSponsorInstance.errors, view:'edit'
            return
        }

        tekSponsorInstance.save flush:true

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.updated.message', args: [message(code: 'TekSponsor.label', default: 'TekSponsor'), tekSponsorInstance.id])
                redirect tekSponsorInstance
            }
            '*'{ respond tekSponsorInstance, [status: OK] }
        }
    }

    @Transactional
    def delete(TekSponsor tekSponsorInstance) {

        if (tekSponsorInstance == null) {
            notFound()
            return
        }

        tekSponsorInstance.delete flush:true

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.deleted.message', args: [message(code: 'TekSponsor.label', default: 'TekSponsor'), tekSponsorInstance.id])
                redirect action:"index", method:"GET"
            }
            '*'{ render status: NO_CONTENT }
        }
    }

    protected void notFound() {
        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.not.found.message', args: [message(code: 'tekSponsor.label', default: 'TekSponsor'), params.id])
                redirect action: "index", method: "GET"
            }
            '*'{ render status: NOT_FOUND }
        }
    }
}
