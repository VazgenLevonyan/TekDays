package event

import grails.test.mixin.TestFor
import spock.lang.Specification
import user.TekUser

/**
 * See the API for {@link grails.test.mixin.domain.DomainClassUnitTestMixin} for usage instructions
 */
@TestFor(TekEvent)
class TekEventSpec extends Specification {

    def setup() {
    }

    def cleanup() {
    }

    void "test toString"() {
        when: "a tekEvent has a name and a city"
        def tekEvent = new TekEvent(name:'C',
                city: 'San Francisco',

                 organizer: [fullName: 'A']
                 as TekUser)
        then: "the toString method will combine them."
        tekEvent.toString() == 'C, San Francisco'

    }
}