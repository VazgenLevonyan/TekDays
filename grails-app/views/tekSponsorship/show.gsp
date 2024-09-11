
<%@ page import="sponsor.TekSponsorship" %>
<!DOCTYPE html>
<html>
	<head>
		<meta name="layout" content="main">
		<g:set var="entityName" value="${message(code: 'tekSponsorship.label', default: 'TekSponsorship')}" />
		<title><g:message code="default.show.label" args="[entityName]" /></title>
	</head>
	<body>
		<a href="#show-tekSponsorship" class="skip" tabindex="-1"><g:message code="default.link.skip.label" default="Skip to content&hellip;"/></a>
		<div class="nav" role="navigation">
			<ul>
				<li><a class="home" href="${createLink(uri: '/')}"><g:message code="default.home.label"/></a></li>
				<li><g:link class="list" action="index"><g:message code="default.list.label" args="[entityName]" /></g:link></li>
				<li><g:link class="create" action="create"><g:message code="default.new.label" args="[entityName]" /></g:link></li>
			</ul>
		</div>
		<div id="show-tekSponsorship" class="content scaffold-show" role="main">
			<h1><g:message code="default.show.label" args="[entityName]" /></h1>
			<g:if test="${flash.message}">
			<div class="message" role="status">${flash.message}</div>
			</g:if>
			<ol class="property-list tekSponsorship">
			
				<g:if test="${tekSponsorshipInstance?.event}">
				<li class="fieldcontain">
					<span id="event-label" class="property-label"><g:message code="tekSponsorship.event.label" default="Event" /></span>
					
						<span class="property-value" aria-labelledby="event-label"><g:link controller="tekEvent" action="show" id="${tekSponsorshipInstance?.event?.id}">${tekSponsorshipInstance?.event?.encodeAsHTML()}</g:link></span>
					
				</li>
				</g:if>
			
				<g:if test="${tekSponsorshipInstance?.sponsor}">
				<li class="fieldcontain">
					<span id="sponsor-label" class="property-label"><g:message code="tekSponsorship.sponsor.label" default="Sponsor" /></span>
					
						<span class="property-value" aria-labelledby="sponsor-label"><g:link controller="tekSponsor" action="show" id="${tekSponsorshipInstance?.sponsor?.id}">${tekSponsorshipInstance?.sponsor?.encodeAsHTML()}</g:link></span>
					
				</li>
				</g:if>
			
				<g:if test="${tekSponsorshipInstance?.contributionType}">
				<li class="fieldcontain">
					<span id="contributionType-label" class="property-label"><g:message code="tekSponsorship.contributionType.label" default="Contribution Type" /></span>
					
						<span class="property-value" aria-labelledby="contributionType-label"><g:fieldValue bean="${tekSponsorshipInstance}" field="contributionType"/></span>
					
				</li>
				</g:if>
			
				<g:if test="${tekSponsorshipInstance?.description}">
				<li class="fieldcontain">
					<span id="description-label" class="property-label"><g:message code="tekSponsorship.description.label" default="Description" /></span>
					
						<span class="property-value" aria-labelledby="description-label"><g:fieldValue bean="${tekSponsorshipInstance}" field="description"/></span>
					
				</li>
				</g:if>
			
				<g:if test="${tekSponsorshipInstance?.notes}">
				<li class="fieldcontain">
					<span id="notes-label" class="property-label"><g:message code="tekSponsorship.notes.label" default="Notes" /></span>
					
						<span class="property-value" aria-labelledby="notes-label"><g:fieldValue bean="${tekSponsorshipInstance}" field="notes"/></span>
					
				</li>
				</g:if>
			
			</ol>
			<g:form url="[resource:tekSponsorshipInstance, action:'delete']" method="DELETE">
				<fieldset class="buttons">
					<g:link class="edit" action="edit" resource="${tekSponsorshipInstance}"><g:message code="default.button.edit.label" default="Edit" /></g:link>
					<g:actionSubmit class="delete" action="delete" value="${message(code: 'default.button.delete.label', default: 'Delete')}" onclick="return confirm('${message(code: 'default.button.delete.confirm.message', default: 'Are you sure?')}');" />
				</fieldset>
			</g:form>
		</div>
	</body>
</html>
