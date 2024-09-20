package task

import event.TekEvent
import user.TekUser

class TekTask {
    String title
    String notes
    TekUser assignedTo
    Date dueDate
    TekEvent event
    Boolean completed

    static constraints = {
        title blank: false
        notes blank: true, nullable: true, maxSize: 5000
        assignedTo nullable: true
        dueDate nullable: true
        completed nullable: true
    }
    static belongsTo = TekEvent
}