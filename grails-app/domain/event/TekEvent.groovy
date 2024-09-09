package event

import forum.TekMessage
import sponsor.TekSponsorship
import task.TekTask
import user.TekUser

class TekEvent {

    String city
    String name
    String venue
    Date startDate
    Date endDate
    String description
    TekUser organizer

    static hasMany = [volunteers: TekUser, respondents: String, sponsorships: TekSponsorship, tasks: TekTask, messages: TekMessage,]

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
    }

    String toString() {
        "$name, $city"
    }
}
