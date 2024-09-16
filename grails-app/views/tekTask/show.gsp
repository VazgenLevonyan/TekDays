
<%@ page import="task.TekTask" %>
<!DOCTYPE html>
<html>
	<head>
		<meta name="layout" content="main">
		<g:set var="entityName" value="${message(code: 'tekTask.label', default: 'TekTask')}" />
		<title><g:message code="default.show.label" args="[entityName]" /></title>
	</head>
	<body>
		<a href="#show-tekTask" class="skip" tabindex="-1"><g:message code="default.link.skip.label" default="Skip to content&hellip;"/></a>
		<div class="nav" role="navigation">
			<ul>
				<li><a class="home" href="${createLink(uri: '/')}"><g:message code="default.home.label"/></a></li>
				<li><g:link class="list" action="index"><g:message code="default.list.label" args="[entityName]" /></g:link></li>
				<li><g:link class="create" action="create"><g:message code="default.new.label" args="[entityName]" /></g:link></li>
			</ul>
		</div>
		<div id="show-tekTask" class="content scaffold-show" role="main">
			<h1><g:message code="default.show.label" args="[entityName]" /></h1>
			<g:if test="${flash.message}">
			<div class="message" role="status">${flash.message}</div>
			</g:if>
			<ol class="property-list tekTask">
			
				<g:if test="${tekTaskInstance?.title}">
				<li class="fieldcontain">
					<span id="title-label" class="property-label"><g:message code="tekTask.title.label" default="Title" /></span>
					
						<span class="property-value" aria-labelledby="title-label"><g:fieldValue bean="${tekTaskInstance}" field="title"/></span>
					
				</li>
				</g:if>
			
				<g:if test="${tekTaskInstance?.notes}">
				<li class="fieldcontain">
					<span id="notes-label" class="property-label"><g:message code="tekTask.notes.label" default="Notes" /></span>
					
						<span class="property-value" aria-labelledby="notes-label"><g:fieldValue bean="${tekTaskInstance}" field="notes"/></span>
					
				</li>
				</g:if>
			
				<g:if test="${tekTaskInstance?.assignedTo}">
				<li class="fieldcontain">
					<span id="assignedTo-label" class="property-label"><g:message code="tekTask.assignedTo.label" default="Assigned To" /></span>
					
						<span class="property-value" aria-labelledby="assignedTo-label"><g:link controller="tekUser" action="show" id="${tekTaskInstance?.assignedTo?.id}">${tekTaskInstance?.assignedTo?.encodeAsHTML()}</g:link></span>
					
				</li>
				</g:if>
			
				<g:if test="${tekTaskInstance?.dueDate}">
				<li class="fieldcontain">
					<span id="dueDate-label" class="property-label"><g:message code="tekTask.dueDate.label" default="Due Date" /></span>
					
						<span class="property-value" aria-labelledby="dueDate-label"><g:formatDate date="${tekTaskInstance?.dueDate}" /></span>
					
				</li>
				</g:if>
			
				<g:if test="${tekTaskInstance?.event}">
				<li class="fieldcontain">
					<span id="event-label" class="property-label"><g:message code="tekTask.event.label" default="Event" /></span>
					
						<span class="property-value" aria-labelledby="event-label"><g:link controller="tekEvent" action="show" id="${tekTaskInstance?.event?.id}">${tekTaskInstance?.event?.encodeAsHTML()}</g:link></span>
					
				</li>
				</g:if>

				<g:if test="${taskInstance?.completed}">
					<li class="fieldcontain">
						<span id="completed-label" class="property-label"><g:message
								code="task.completed.label" default="Completed" /></span>
						<span class="property-value" aria-labelledby="completed-label">
							<g:formatBoolean boolean="${taskInstance?.completed}"
											 true="Yes" false="No" /></span>
					</li>
				</g:if>
			</ol>
			<g:form url="[resource:tekTaskInstance, action:'delete']" method="DELETE">
				<fieldset class="buttons">
					<g:link class="edit" action="edit" resource="${tekTaskInstance}"><g:message code="default.button.edit.label" default="Edit" /></g:link>
					<g:actionSubmit class="delete" action="delete" value="${message(code: 'default.button.delete.label', default: 'Delete')}" onclick="return confirm('${message(code: 'default.button.delete.confirm.message', default: 'Are you sure?')}');" />
				</fieldset>
			</g:form>
		</div>
	</body>
</html>
