import event.TekEvent
import user.TekUser

class BootStrap {

    def init = { servletContext ->

        def tekUser1 = new TekUser(fullName: 'A',
                userName: 'jdoe',
                password: 't0ps3cr3t',
                email: 'jdoe@johnsgroovyshop.com',
                website: 'blog.johnsgroovyshop.com',
                bio: 'John has been programming for over 40 years. ...').save(flush: true)

        def tekUser2 = new TekUser(fullName: 'B',
                userName: 'tractorman',
                password: 't0ps3cr3t',
                email: 'john.deere@porkproducers.org',
                website: 'www.perl.porkproducers.org',
                bio: 'John is a top notch Perl programmer and a ...').save(flush: true)

        def event1 = new TekEvent(name: 'C',

                city: 'Saint Louis, MO',
                organizer: TekUser.findByFullName('A'),
                venue: 'TBD',
                startDate: new Date('11/21/2013'),
                endDate: new Date('11/21/2013'),
                description: '''This conference will bring
coders ...''').save(flush: true)

        def event2 = new TekEvent(name: 'D',

                city: 'Austin, MN',
                organizer: TekUser.findByFullName('B'),
                venue: 'SPAM Museum',
                startDate: new Date('11/2/2013'),
                endDate: new Date('11/2/2013'),
                description: 'Join the Perl programmers of the ...').save(flush: true)

        tekUser1.tekEvent = TekEvent.findByName("C")
        tekUser1.save(flush: true)
        tekUser2.setTekEvent(TekEvent.findByName("D"))
        tekUser2.save(flush: true)
    }
    def destroy = {
    }
}
