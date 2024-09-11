package sponsorship

import sponsor.TekSponsorship

import static org.springframework.http.HttpStatus.*
import grails.transaction.Transactional

@Transactional(readOnly = true)
class TekSponsorshipController {

    static allowedMethods = [save: "POST", update: "PUT", delete: "DELETE"]

    def index(Integer max) {
        params.max = Math.min(max ?: 10, 100)
        respond TekSponsorship.list(params), model:[tekSponsorshipInstanceCount: TekSponsorship.count()]
    }

    def show(TekSponsorship tekSponsorshipInstance) {
        respond tekSponsorshipInstance
    }

    def create() {
        respond new TekSponsorship(params)
    }

    @Transactional
    def save(TekSponsorship tekSponsorshipInstance) {
        if (tekSponsorshipInstance == null) {
            notFound()
            return
        }

        if (tekSponsorshipInstance.hasErrors()) {
            respond tekSponsorshipInstance.errors, view:'create'
            return
        }

        tekSponsorshipInstance.save flush:true

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.created.message', args: [message(code: 'tekSponsorship.label', default: 'TekSponsorship'), tekSponsorshipInstance.id])
                redirect tekSponsorshipInstance
            }
            '*' { respond tekSponsorshipInstance, [status: CREATED] }
        }
    }

    def edit(TekSponsorship tekSponsorshipInstance) {
        respond tekSponsorshipInstance
    }

    @Transactional
    def update(TekSponsorship tekSponsorshipInstance) {
        if (tekSponsorshipInstance == null) {
            notFound()
            return
        }

        if (tekSponsorshipInstance.hasErrors()) {
            respond tekSponsorshipInstance.errors, view:'edit'
            return
        }

        tekSponsorshipInstance.save flush:true

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.updated.message', args: [message(code: 'TekSponsorship.label', default: 'TekSponsorship'), tekSponsorshipInstance.id])
                redirect tekSponsorshipInstance
            }
            '*'{ respond tekSponsorshipInstance, [status: OK] }
        }
    }

    @Transactional
    def delete(TekSponsorship tekSponsorshipInstance) {

        if (tekSponsorshipInstance == null) {
            notFound()
            return
        }

        tekSponsorshipInstance.delete flush:true

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.deleted.message', args: [message(code: 'TekSponsorship.label', default: 'TekSponsorship'), tekSponsorshipInstance.id])
                redirect action:"index", method:"GET"
            }
            '*'{ render status: NO_CONTENT }
        }
    }

    protected void notFound() {
        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.not.found.message', args: [message(code: 'tekSponsorship.label', default: 'TekSponsorship'), params.id])
                redirect action: "index", method: "GET"
            }
            '*'{ render status: NOT_FOUND }
        }
    }
}
