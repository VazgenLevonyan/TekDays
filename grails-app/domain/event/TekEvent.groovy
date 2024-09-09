package event

import user.TekUser

class TekEvent {

    String city
    String name
    TekUser organizer
    String venue
    Date startDate
    Date endDate
    String description

    static constraints = {

        name()
        city()
        description maxSize: 5000
        organizer(nullable: true)
        venue()
        startDate()
        endDate()
    }

    String toString() {
        "$name, $city"
    }
}
