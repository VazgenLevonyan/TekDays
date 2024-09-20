package task

import grails.transaction.Transactional
import org.slf4j.Logger
import org.slf4j.LoggerFactory

@Transactional
class TaskService {

    private static final Logger LOG = LoggerFactory.getLogger(TaskService)

    def serviceMethod() {
    }


    def addDefaultTasks(tekEvent){
        LOG.info("Info log in TaskService  --addTasksToEvent action")
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