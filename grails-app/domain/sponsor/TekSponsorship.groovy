package sponsor
import event.TekEvent

class TekSponsorship {

        TekEvent event
        TekSponsor sponsor
        String contributionType
        String description
        String notes
        static constraints = {
            event nullable: false
            sponsor nullable: false
            contributionType inList:["Other", "Venue", "A/V", "Promotion", "Cash"]
            description nullable: true, blank: true
            notes nullable: true, blank: true, maxSize: 5000
        }
    }
