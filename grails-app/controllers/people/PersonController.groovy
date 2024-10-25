package people

import grails.converters.JSON

class PersonController {

    def create() {
        def requestBody = request.JSON
        def name = requestBody.name
        def surname = requestBody.surname

        if (!name || !surname) {
            response.status = 400  // Bad Request
            render(contentType: 'application/json') {
                Message = "Name and surname are required"
                Status = 400
            }
            return
        }

        def person = new Person(name: name, surname: surname)

        if (!person.validate()) {
            response.status = 422
            render(contentType: 'application/json') {
                Message = "Validation failed"
                errors = person.errors.allErrors.collect { error ->
                    [field: error.field, message: error.defaultMessage]
                }
                Status = 422
            }
            return
        }

        person.save(flush: true)
        response.status = 201
        render(contentType: 'application/json') {
            Message = "Person created successfully"
            Id = person.id
            Name=person.name
            Surname=person.surname
            Status = 201
        }
    }

    def show(Long id) {
        def person = Person.get(id)

        if (!person) {
            response.status = 404
            render(contentType: 'application/json') {
                Message = "Person by id ${id} not found"
                Status = 404
            }
            return
        }

        response.status = 200
        render(contentType: 'application/json') {
            Id = person.id
            Name = person.name
            Surname = person.surname
            Status = 200
        }
    }

    def update(Long id) {
        def person = Person.get(id)
        if (!person) {
            response.status = 404
            render(contentType: 'application/json') {
                Message = "Person with id ${id} not found"
                Status = 404
            }
            return
        }

        def requestBody = request.JSON
        def name = requestBody.name
        def surname = requestBody.surname

        if (!name || !surname) {
            response.status = 400
            render(contentType: 'application/json') {
                Message = "Name and surname are required"
                Status = 400
            }
            return
        }

        person.name = name
        person.surname = surname

        if (!person.save(flush: true)) {
            response.status = 422
            render(contentType: 'application/json') {
                Message = "Validation failed"
                errors = person.errors.allErrors.collect { error ->
                    [field: error.field, message: error.defaultMessage]
                }
                Status = 422
            }
            return
        }

        def updatedPerson = person.save(flush: true)
        response.status = 200
        render(contentType: 'application/json') {
            Message = "Person updated successfully"
            Id = updatedPerson.id
            Name = updatedPerson.name
            Surname = updatedPerson.surname
            Status = 200
        }
    }

    def delete(Long id) {
        def person = Person.get(id)

        if (!person) {
            response.status = 404
            render(contentType: 'application/json') {
                Message = "Person with id ${id} not found"
                Status = 404
            }
            return
        }
        person.delete(flush: true)

        response.status = 200
        render(contentType: 'application/json') {
            Message = "Person deleted successfully"
            Status = 200
        }
    }
}
