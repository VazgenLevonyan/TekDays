
<%@ page import="sponsor.TekSponsor" %>
<!DOCTYPE html>
<html>
	<head>
		<meta name="layout" content="main">
		<g:set var="entityName" value="${message(code: 'tekSponsor.label', default: 'TekSponsor')}" />
		<title><g:message code="default.list.label" args="[entityName]" /></title>
	</head>
	<body>
		<a href="#list-tekSponsor" class="skip" tabindex="-1"><g:message code="default.link.skip.label" default="Skip to content&hellip;"/></a>
		<div class="nav" role="navigation">
			<ul>
				<li><a class="home" href="${createLink(uri: '/')}"><g:message code="default.home.label"/></a></li>
				<li><g:link class="create" action="create"><g:message code="default.new.label" args="[entityName]" /></g:link></li>
			</ul>
		</div>
		<div id="list-tekSponsor" class="content scaffold-list" role="main">
			<h1><g:message code="default.list.label" args="[entityName]" /></h1>
			<g:if test="${flash.message}">
				<div class="message" role="status">${flash.message}</div>
			</g:if>
			<table>
			<thead>
					<tr>
					
						<g:sortableColumn property="name" title="${message(code: 'tekSponsor.name.label', default: 'Name')}" />
					
						<g:sortableColumn property="website" title="${message(code: 'tekSponsor.website.label', default: 'Website')}" />
					
						<g:sortableColumn property="description" title="${message(code: 'tekSponsor.description.label', default: 'Description')}" />
					
						<g:sortableColumn property="logo" title="${message(code: 'tekSponsor.logo.label', default: 'Logo')}" />
					
					</tr>
				</thead>
				<tbody>
				<g:each in="${tekSponsorInstanceList}" status="i" var="tekSponsorInstance">
					<tr class="${(i % 2) == 0 ? 'even' : 'odd'}">
					
						<td><g:link action="show" id="${tekSponsorInstance.id}">${fieldValue(bean: tekSponsorInstance, field: "name")}</g:link></td>
					
						<td>${fieldValue(bean: tekSponsorInstance, field: "website")}</td>
					
						<td>${fieldValue(bean: tekSponsorInstance, field: "description")}</td>
					
						<td>${fieldValue(bean: tekSponsorInstance, field: "logo")}</td>
					
					</tr>
				</g:each>
				</tbody>
			</table>
			<div class="pagination">
				<g:paginate total="${tekSponsorInstanceCount ?: 0}" />
			</div>
		</div>
	</body>
</html>
