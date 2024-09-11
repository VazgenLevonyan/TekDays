<%@ page import="sponsor.TekSponsorship" %>



<div class="fieldcontain ${hasErrors(bean: tekSponsorshipInstance, field: 'event', 'error')} required">
	<label for="event">
		<g:message code="tekSponsorship.event.label" default="Event" />
		<span class="required-indicator">*</span>
	</label>
	<g:select id="event" name="event.id" from="${event.TekEvent.list()}" optionKey="id" required="" value="${tekSponsorshipInstance?.event?.id}" class="many-to-one"/>

</div>

<div class="fieldcontain ${hasErrors(bean: tekSponsorshipInstance, field: 'sponsor', 'error')} required">
	<label for="sponsor">
		<g:message code="tekSponsorship.sponsor.label" default="Sponsor" />
		<span class="required-indicator">*</span>
	</label>
	<g:select id="sponsor" name="sponsor.id" from="${sponsor.TekSponsor.list()}" optionKey="id" required="" value="${tekSponsorshipInstance?.sponsor?.id}" class="many-to-one"/>

</div>

<div class="fieldcontain ${hasErrors(bean: tekSponsorshipInstance, field: 'contributionType', 'error')} required">
	<label for="contributionType">
		<g:message code="tekSponsorship.contributionType.label" default="Contribution Type" />
		<span class="required-indicator">*</span>
	</label>
	<g:select name="contributionType" from="${tekSponsorshipInstance.constraints.contributionType.inList}" required="" value="${tekSponsorshipInstance?.contributionType}" valueMessagePrefix="tekSponsorship.contributionType"/>

</div>

<div class="fieldcontain ${hasErrors(bean: tekSponsorshipInstance, field: 'description', 'error')} ">
	<label for="description">
		<g:message code="tekSponsorship.description.label" default="Description" />
		
	</label>
	<g:textField name="description" value="${tekSponsorshipInstance?.description}"/>

</div>

<div class="fieldcontain ${hasErrors(bean: tekSponsorshipInstance, field: 'notes', 'error')} ">
	<label for="notes">
		<g:message code="tekSponsorship.notes.label" default="Notes" />
		
	</label>
	<g:textArea name="notes" cols="40" rows="5" maxlength="5000" value="${tekSponsorshipInstance?.notes}"/>

</div>

