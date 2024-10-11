package event

import org.hibernate.envers.query.AuditQuery
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.hibernate.envers.AuditReaderFactory

import static org.springframework.http.HttpStatus.*
import grails.transaction.Transactional

@Transactional(readOnly = true)
class TekEventController {

    def datatablesSourceService

    def dtList() {
        println 1
        [tekEventId: params.id]
    }

    def dataTablesRenderer() {
        def propertiesToRender = ["id","name","description", "city", "organizer","changedBy","timestamp"] //<list of fields to be rendered>
        def entityName = 'TekEvent'
        render  datatablesSourceService.dataTablesSource(propertiesToRender, entityName, params)
    }

    def revision(){
        def propertiesToRender = ["id","name", "description", "city"]
        def tekEventId=Long.valueOf(params.id)

        def result=datatablesSourceService.getRevisions(tekEventId,propertiesToRender,params)
        def data=["tekEventId": tekEventId, "result": result]
        [data: data]
    }

    private static final Logger LOG = LoggerFactory.getLogger(TekEventController)
    def taskService

    static allowedMethods = [save: "POST", update: "PUT", delete: "DELETE"]

    def index(Integer max) {
        LOG.info("Info log in EventController --index action")
        params.max = Math.min(max ?: 10, 100)
        respond TekEvent.list(params), model: [tekEventInstanceCount: TekEvent.count()]
    }

    @Transactional
    def volunteer() {
        def event = TekEvent.get(params.id)
        def user = session.user
        if (event && user) {
            event.addToVolunteers(user)
            event.save(flush: true)

            user.setTekEvent(event)
            user.save(flush: true)

            render "Thank you for Volunteering"
        } else {
            render(status: 404, text: "Event or User not found")
        }
    }


    def show(Long id) {
        def tekEventInstance
        if (params.nickname) {
            tekEventInstance = TekEvent.findByNickname(params.nickname)
        } else {
            tekEventInstance = TekEvent.get(id)
        }
        if (!tekEventInstance) {
            if (params.nickname) {
                flash.message = "TekEvent not found with nickname ${params.nickname}"
            } else {
                flash.message = "TekEvent not found with id $id"
            }
            redirect(action: "list")
            return
        }
        [tekEventInstance: tekEventInstance]
    }

    def create() {
        respond new TekEvent(params)
    }

    @Transactional
    def save(TekEvent tekEventInstance) {
        if (tekEventInstance == null) {
            notFound()
            return
        }

        if (tekEventInstance.hasErrors()) {
            respond tekEventInstance.errors, view: 'create'
            return
        }

        tekEventInstance.save flush: true
        taskService.addDefaultTasks(tekEventInstance)

        request.withFormat {
            form {
                flash.message = message(code: 'default.created.message',
                        args: [message(code: 'tekEventInstance.label',
                                default: 'TekEvent'), tekEventInstance.id])
                redirect tekEventInstance
            }
            '*' { respond tekEventInstance, [status: CREATED] }
        }
    }

    def edit(TekEvent tekEventInstance) {
        respond tekEventInstance
    }

    @Transactional
    def update(TekEvent tekEventInstance) {
        if (tekEventInstance == null) {
            notFound()
            return
        }

        if (tekEventInstance.hasErrors()) {
            respond tekEventInstance.errors, view: 'edit'
            return
        }

        tekEventInstance.save flush: true

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.updated.message', args: [message(code: 'TekEvent.label', default: 'TekEvent'), tekEventInstance.id])
                redirect tekEventInstance
            }
            '*' { respond tekEventInstance, [status: OK] }
        }
    }

    @Transactional
    def delete(TekEvent tekEvent) {

        if (tekEvent == null) {
            notFound()
            return
        }

        if (tekEvent.volunteers.contains(tekEvent.organizer)) {
            tekEvent.volunteers.remove(tekEvent.organizer)
        }

        tekEvent.organizer = null



        try {
            // Save changes to the TekEvent before deletion
            tekEvent.save(flush: true)

            // Delete the TekEvent
            tekEvent.delete(flush: true)
        }
        catch (Exception e){
            flash.message = "Error occurred while deleting TekEvent: ${e.message}"
        }

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.deleted.message', args: [message(code: 'TekEvent.label', default: 'TekEvent'), tekEvent.id])
                redirect action: "index", method: "GET"
            }
            '*' { render status: NO_CONTENT }
        }
    }

    protected void notFound() {
        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.not.found.message', args: [message(code: 'tekEvent.label', default: 'TekEvent'), params.id])
                redirect action: "index", method: "GET"
            }
            '*' { render status: NOT_FOUND }
        }
    }
}
