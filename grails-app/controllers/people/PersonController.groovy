package people

import grails.converters.JSON

class PersonController {

    def createPersonViaApi() {
        Person person = new Person(name: "Pyot00r", surname: "Pav00levich")

        if (person.save(flush: true)) {
            withFormat {
            html {
                render person as JSON
            }
                json {
                    render person as JSON
                }
            }

        } else {
            render status: 400, text: 'Invalid data provided'
        }
    }

    def createPersonPage() {
        render(view: 'createPerson') // Render the createPerson.gsp page
    }
}
