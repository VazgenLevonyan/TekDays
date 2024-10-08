package user

import event.TekEvent
import org.hibernate.envers.Audited

@Audited
class TekUser {

    String fullName
    String userName
    String password
    String email
    String website
    String bio
    TekEvent tekEvent

    String toString() { fullName }

    static constraints = {
        fullName()
        userName()
        email()
        tekEvent(nullable: true)
        website()
        bio maxSize: 5000
    }
}
