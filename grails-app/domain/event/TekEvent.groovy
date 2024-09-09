package event

import user.TekUser

class TekEvent {

    String city
    String name
    String venue
    Date startDate
    Date endDate
    String description
    TekUser organizer

    static hasMany = [volunteers: TekUser, respondents: String]

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
    }

    String toString() {
        "$name, $city"
    }
}
