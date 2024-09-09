package event

import user.TekUser

class TekEvent {

    String city
    String name
    String venue
    Date startDate
    Date endDate
    String description

    static hasMany = [volunteers : TekUser]

    static constraints = {

        name()
        city()
        description maxSize: 5000
        venue()
        startDate()
        endDate()
    }

    String toString() {
        "$name, $city"
    }
}
