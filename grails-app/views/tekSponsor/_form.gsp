<%@ page import="sponsor.TekSponsor" %>



<div class="fieldcontain ${hasErrors(bean: tekSponsorInstance, field: 'name', 'error')} required">
	<label for="name">
		<g:message code="tekSponsor.name.label" default="Name" />
		<span class="required-indicator">*</span>
	</label>
	<g:textField name="name" required="" value="${tekSponsorInstance?.name}"/>

</div>

<div class="fieldcontain ${hasErrors(bean: tekSponsorInstance, field: 'website', 'error')} required">
	<label for="website">
		<g:message code="tekSponsor.website.label" default="Website" />
		<span class="required-indicator">*</span>
	</label>
	<g:field type="url" name="website" required="" value="${tekSponsorInstance?.website}"/>

</div>

<div class="fieldcontain ${hasErrors(bean: tekSponsorInstance, field: 'description', 'error')} ">
	<label for="description">
		<g:message code="tekSponsor.description.label" default="Description" />
		
	</label>
	<g:textArea name="description" cols="40" rows="5" maxlength="5000" value="${tekSponsorInstance?.description}"/>

</div>

<div class="fieldcontain ${hasErrors(bean: tekSponsorInstance, field: 'logo', 'error')} ">
	<label for="logo">
		<g:message code="tekSponsor.logo.label" default="Logo" />
		
	</label>
	<input type="file" id="logo" name="logo" />

</div>

<div class="fieldcontain ${hasErrors(bean: tekSponsorInstance, field: 'sponsorships', 'error')} ">
	<label for="sponsorships">
		<g:message code="tekSponsor.sponsorships.label" default="Sponsorships" />
		
	</label>
	
<ul class="one-to-many">
<g:each in="${tekSponsorInstance?.sponsorships?}" var="s">
    <li><g:link controller="tekSponsorship" action="show" id="${s.id}">${s?.encodeAsHTML()}</g:link></li>
</g:each>
<li class="add">
<g:link controller="tekSponsorship" action="create" params="['tekSponsor.id': tekSponsorInstance?.id]">${message(code: 'default.add.label', args: [message(code: 'tekSponsorship.label', default: 'TekSponsorship')])}</g:link>
</li>
</ul>


</div>

