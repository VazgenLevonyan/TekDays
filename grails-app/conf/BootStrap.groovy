import event.TekEvent
import user.TekUser
import sponsor.TekSponsorship
import sponsor.TekSponsor

class BootStrap {

    def init = { servletContext ->


        def eventOrganizer = new TekUser(fullName: 'John Doe',
                userName: 'jdoe',
                password: 't0ps3cr3t',
                email: 'jdoe@johnsgroovyshop.com',
                website: 'blog.johnsgroovyshop.com',
                bio: 'John has been programming for over 40 years. ...').save()

        def event1 = new TekEvent(name: 'C',
                organizer: eventOrganizer,
                city: 'Saint Louis, MO',
                venue: 'TBD',
                startDate: new Date('11/21/2013'),
                endDate: new Date('11/21/2013'),
                description: '''This conference will bring
coders ...''').save(flush: true)


        def volunteer1 = new TekUser(fullName: 'A',
                userName: 'jdoe',
                password: 't0ps3cr3t',
                email: 'jdoe@johnsgroovyshop.com',
                website: 'blog.johnsgroovyshop.com',
                bio: 'John has been programming for over 40 years. ...').save(flush: true)

        def volunteer2 = new TekUser(fullName: 'B',
                userName: 'tractorman',
                password: 't0ps3cr3t',
                email: 'john.deere@porkproducers.org',
                website: 'www.perl.porkproducers.org',
                bio: 'John is a top notch Perl programmer and a ...').save(flush: true)

        event1.addToVolunteers(volunteer1)
        event1.addToVolunteers(volunteer2);
        event1.addToRespondents('ben@grailsmail.com')
        event1.addToRespondents('zachary@linuxgurus.org')
        event1.addToRespondents('solomon@bootstrapwelding.com')
        event1.save(flush: true)



        def s1 = new TekSponsor(name:'Contegix',

                website:'http://www.contegix.com',
                description:'Beyond Managed Hosting for your Enterprise').save()


        def s2 = new TekSponsor(name:'Object Computing Incorporated',
                website:'http://ociweb.com',
                description:'An OO Software Engineering Company'
        ).save()

        def sp1 = new TekSponsorship(event:event1,
                sponsor:s1,
                contributionType:'Other',
                description:'Cool T-Shirts').save()

        def sp2 = new TekSponsorship(event:event1,
                sponsor:s2,
                contributionType:'Venue',
                description:'Will be paying for the Moscone').save()




    }
    def destroy = {
    }
}
