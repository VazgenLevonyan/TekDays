<%@ page import="task.TekTask" %>



<div class="fieldcontain ${hasErrors(bean: tekTaskInstance, field: 'title', 'error')} required">
	<label for="title">
		<g:message code="tekTask.title.label" default="Title" />
		<span class="required-indicator">*</span>
	</label>
	<g:textField name="title" required="" value="${tekTaskInstance?.title}"/>

</div>

<div class="fieldcontain ${hasErrors(bean: tekTaskInstance, field: 'notes', 'error')} ">
	<label for="notes">
		<g:message code="tekTask.notes.label" default="Notes" />
		
	</label>
	<g:textArea name="notes" cols="40" rows="5" maxlength="5000" value="${tekTaskInstance?.notes}"/>

</div>

<div class="fieldcontain ${hasErrors(bean: tekTaskInstance, field: 'assignedTo', 'error')} ">
	<label for="assignedTo">
		<g:message code="tekTask.assignedTo.label" default="Assigned To" />
		
	</label>
	<g:select id="assignedTo" name="assignedTo.id" from="${user.TekUser.list()}" optionKey="id" value="${tekTaskInstance?.assignedTo?.id}" class="many-to-one" noSelection="['null': '']"/>

</div>

<div class="fieldcontain ${hasErrors(bean: tekTaskInstance, field: 'dueDate', 'error')} ">
	<label for="dueDate">
		<g:message code="tekTask.dueDate.label" default="Due Date" />
		
	</label>
	<g:datePicker name="dueDate" precision="day"  value="${tekTaskInstance?.dueDate}" default="none" noSelection="['': '']" />

</div>

<div class="fieldcontain ${hasErrors(bean: tekTaskInstance, field: 'event', 'error')} required">
	<label for="event">
		<g:message code="tekTask.event.label" default="Event" />
		<span class="required-indicator">*</span>
	</label>
	<g:select id="event" name="event.id" from="${event.TekEvent.list()}" optionKey="id" required="" value="${tekTaskInstance?.event?.id}" class="many-to-one"/>

</div>

