package event

import forum.TekMessage
import org.hibernate.envers.Audited
import org.hibernate.envers.NotAudited
import sponsor.TekSponsorship
import task.TekTask
import user.TekUser

@Audited
class TekEvent {

    String city
    String name
    String venue
    Date startDate
    Date endDate
    String description
    TekUser organizer
    String nickname

    Date dateCreated
    Date lastUpdated

    static hasMany = [ volunteers: TekUser, respondents: String, sponsorships: TekSponsorship, tasks: TekTask, messages: TekMessage,]

    static  mapping = {
        tasks cascade: 'all-delete-orphan'
        messages cascade: 'all-delete-orphan'
        volunteers cascade: 'all-delete-orphan'
        respondents cascade: 'all-delete-orphan'
    }

    def beforeValidate() {
        removeOrganizerFromVolunteers()
    }

    private void removeOrganizerFromVolunteers() {
        if (volunteers && organizer && volunteers.contains(organizer)) {
            volunteers.remove(organizer)
        }
    }

    static constraints = {

        name()
        city()
        description maxSize: 5000
        organizer(nullable: false)
        venue()
        startDate()
        endDate()
        sponsorships nullable: true
        tasks nullable: true
        messages nullable: true
        nickname nullable: true
    }

    String toString() {
        "$name, $city"
    }
}
