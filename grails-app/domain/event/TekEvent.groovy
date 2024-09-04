package event

import user.TekUser

class TekEvent {

    static constraints = {

        name()
        city()
        description maxSize: 5000
        organizer()
        venue()
        startDate()
        endDate()
    }

    String city
    String name
    TekUser organizer
    String venue
    Date startDate
    Date endDate
    String description

    String toString(){
        "$name, $city"
    }
}
