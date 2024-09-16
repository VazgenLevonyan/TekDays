package task

import grails.transaction.Transactional

@Transactional
class TaskService {

    def serviceMethod() {
    }


    def addDefaultTasks(tekEvent){
        if (tekEvent.tasks?.size() > 0)
            return //We only want to add tasks to a new event
        tekEvent.addToTasks new TekTask(title:'Identify potential venues')
        tekEvent.addToTasks new TekTask(title:'Get price / availability of venues')
        tekEvent.addToTasks new TekTask(title:'Compile potential sponsor list')
        tekEvent.addToTasks new TekTask(title:'Design promotional materials')
        tekEvent.addToTasks new TekTask(title:'Compile potential advertising avenues')
        tekEvent.addToTasks new TekTask(title:'Locate swag provider (preferably local)')
        tekEvent.save()
    }
}
