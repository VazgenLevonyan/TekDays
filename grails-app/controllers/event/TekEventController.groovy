package event

class TekEventController {

   // def index() { }

    def scaffold = TekEvent



    def create() {
        def tekEventInstance = new TekEvent(params)
        // Only list volunteers excluding the organizer
        def availableVolunteers = TekUser.list().findAll { it != tekEventInstance.organizer }
        respond tekEventInstance, model: [availableVolunteers: availableVolunteers]
    }

    def edit(TekEvent tekEventInstance) {
        // Only list volunteers excluding the organizer
        def availableVolunteers = TekUser.list().findAll { it != tekEventInstance.organizer }
        respond tekEventInstance, model: [availableVolunteers: availableVolunteers]
    }
}
