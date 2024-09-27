package dashboard
import event.TekEvent
import task.TekTask
import forum.TekMessage
import sponsor.TekSponsorship

class DashboardController {

    def index() {}

    def dashboard = {
        def event = TekEvent.get(params.id)
        if (event) {
            if (event.organizer.userName == session.user.userName || event.volunteers.collect { it.userName }.contains(session.user.userName)) {
                //def tasks = TekTask.findAllByEventAndCompleted(event, false, max: 3, sort: 'dueDate', order: 'asc')
                def tasks = TekTask.findAllByEventAndCompleted(event, false).sort { it.dueDate }[0..2]
                def volunteers = event.volunteers
                def messages = TekMessage.findAllByEventAndParentIsNull(event, [sort: 'id', order: 'desc'])

                def sponsorships = event.sponsorships
                return [event   : event, tasks: tasks, volunteers: volunteers,
                        messages: messages, sponsorships: sponsorships]

            } else {
                flash.message = "Access to dashboard for ${event.name} denied."
                redirect controller: 'tekEvent', action: 'index'
            }
        } else {
            flash.message = "No event was found with an id of ${params.id}"
            redirect controller: 'tekEvent', action: 'index'
        }
    }
}
