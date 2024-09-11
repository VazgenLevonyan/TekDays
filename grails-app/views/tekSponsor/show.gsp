
<%@ page import="sponsor.TekSponsor" %>
<!DOCTYPE html>
<html>
	<head>
		<meta name="layout" content="main">
		<g:set var="entityName" value="${message(code: 'tekSponsor.label', default: 'TekSponsor')}" />
		<title><g:message code="default.show.label" args="[entityName]" /></title>
	</head>
	<body>
		<a href="#show-tekSponsor" class="skip" tabindex="-1"><g:message code="default.link.skip.label" default="Skip to content&hellip;"/></a>
		<div class="nav" role="navigation">
			<ul>
				<li><a class="home" href="${createLink(uri: '/')}"><g:message code="default.home.label"/></a></li>
				<li><g:link class="list" action="index"><g:message code="default.list.label" args="[entityName]" /></g:link></li>
				<li><g:link class="create" action="create"><g:message code="default.new.label" args="[entityName]" /></g:link></li>
			</ul>
		</div>
		<div id="show-tekSponsor" class="content scaffold-show" role="main">
			<h1><g:message code="default.show.label" args="[entityName]" /></h1>
			<g:if test="${flash.message}">
			<div class="message" role="status">${flash.message}</div>
			</g:if>
			<ol class="property-list tekSponsor">
			
				<g:if test="${tekSponsorInstance?.name}">
				<li class="fieldcontain">
					<span id="name-label" class="property-label"><g:message code="tekSponsor.name.label" default="Name" /></span>
					
						<span class="property-value" aria-labelledby="name-label"><g:fieldValue bean="${tekSponsorInstance}" field="name"/></span>
					
				</li>
				</g:if>
			
				<g:if test="${tekSponsorInstance?.website}">
				<li class="fieldcontain">
					<span id="website-label" class="property-label"><g:message code="tekSponsor.website.label" default="Website" /></span>
					
						<span class="property-value" aria-labelledby="website-label"><g:fieldValue bean="${tekSponsorInstance}" field="website"/></span>
					
				</li>
				</g:if>
			
				<g:if test="${tekSponsorInstance?.description}">
				<li class="fieldcontain">
					<span id="description-label" class="property-label"><g:message code="tekSponsor.description.label" default="Description" /></span>
					
						<span class="property-value" aria-labelledby="description-label"><g:fieldValue bean="${tekSponsorInstance}" field="description"/></span>
					
				</li>
				</g:if>
			
				<g:if test="${tekSponsorInstance?.logo}">
				<li class="fieldcontain">
					<span id="logo-label" class="property-label"><g:message code="tekSponsor.logo.label" default="Logo" /></span>
					
				</li>
				</g:if>
			
				<g:if test="${tekSponsorInstance?.sponsorships}">
				<li class="fieldcontain">
					<span id="sponsorships-label" class="property-label"><g:message code="tekSponsor.sponsorships.label" default="Sponsorships" /></span>
					
						<g:each in="${tekSponsorInstance.sponsorships}" var="s">
						<span class="property-value" aria-labelledby="sponsorships-label"><g:link controller="tekSponsorship" action="show" id="${s.id}">${s?.encodeAsHTML()}</g:link></span>
						</g:each>
					
				</li>
				</g:if>
			
			</ol>
			<g:form url="[resource:tekSponsorInstance, action:'delete']" method="DELETE">
				<fieldset class="buttons">
					<g:link class="edit" action="edit" resource="${tekSponsorInstance}"><g:message code="default.button.edit.label" default="Edit" /></g:link>
					<g:actionSubmit class="delete" action="delete" value="${message(code: 'default.button.delete.label', default: 'Delete')}" onclick="return confirm('${message(code: 'default.button.delete.confirm.message', default: 'Are you sure?')}');" />
				</fieldset>
			</g:form>
		</div>
	</body>
</html>
