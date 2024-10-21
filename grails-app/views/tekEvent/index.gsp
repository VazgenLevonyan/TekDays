
<%@ page import="event.TekEvent" %>
<!DOCTYPE html>
<html>
	<head>
		<meta name="layout" content="main">
		<g:set var="entityName" value="${message(code: 'tekEvent.label', default: 'TekEvent')}" />
		<title><g:message code="default.list.label" args="[entityName]" /></title>

		<script>
			$(document).ready(function () {
				$('#dt').DataTable({
					sScrollY: "75%",
					sScrollX: "100%",
					bProcessing: true,
					bServerSide: true,
					sAjaxSource: "/TekDays/tekEvent/dataTablesRenderer",
					bJQueryUI: false,
					bAutoWidth: false,
					// sPaginationType: "full_numbers",
					//aLengthMenu: [5,  25, 50, 100, 200],
					iDisplayLength: 10,
					aoColumnDefs: [


						{
							// bSearchable: false,
							render: function (data, type, full, meta) {
								if (full[0]) {
									return '<a href="${createLink(controller: 'TekEvent', action: 'show')}/' + full[0] + '"class="btn">' + data + '</a>';
								} else {
									return data;
								}
							},
							aTargets: [0],
							visible: true,
							bSearchable: true,
							bSortable: true
						},

						{
							render: function (data, type, full, meta) {
								if (full[1]) {
									return '<a href="${createLink(controller: 'TekEvent', action: 'show')}/' + full[1] + '"class="btn">' + data + '</a>';
								} else {
									return data;
								}
							},
							aTargets: [1],
							visible: true,
							bSearchable: true,
							bSortable: true
						},

                        {
                            render: function (data, type, full, meta) {
                                if (full[2]) {
                                    return '<a href="${createLink(controller: 'TekEvent', action: 'show')}/' + full[2] + '"class="btn">' + data + '</a>';
                                } else {
                                    return data;
                                }
                            },
                            aTargets: [2],
                            visible: true,
                            bSearchable: true,
                            bSortable: true
                        },

						{
							createdCell:function (td, cellData, rowData, row, col) {
								$(td).attr('style','text-align:center;');
							},
							render: function (data, type, full, meta) {
								return '<a href="${createLink(controller: 'TekEvent', action: 'revision')}/' + data + '" class="btn" title="view changes"><i class="fa fa-history fa-2x"></i>' + '</a>';

							},
							aTargets: [3],
							bSearchable: false,
							bSortable: false,
							visible: true
						}
					]
				});
			});
		</script>

	</head>
	<body>
		<a href="#list-tekEvent" class="skip" tabindex="-1"><g:message code="default.link.skip.label" default="Skip to content&hellip;"/></a>
		<div class="nav" role="navigation">
			<ul>
				<li><a class="home" href="${createLink(uri: '/')}"><g:message code="default.home.label"/></a></li>
				<li><g:link class="create" action="create"><g:message code="default.new.label" args="[entityName]" /></g:link></li>
			</ul>
		</div>



	<div id="list-tekEvent" class="content scaffold-list" role="main">
			<h1><g:message code="default.list.label" args="[entityName]" /></h1>
			<g:if test="${flash.message}">
				<div class="message" role="status">${flash.message}</div>
			</g:if>
		<g:jasperReport jasper="">

		</g:jasperReport>

		<g:jasperReport jasper="test report" format="PDF,XLS" name="test-download" class="form-horizontal">
		</g:jasperReport>

			<table>
			<thead>
					<tr>

						<g:sortableColumn property="name" title="${message(code: 'tekEvent.name.label', default: 'Name')}" />

						<g:sortableColumn property="city" title="${message(code: 'tekEvent.city.label', default: 'City')}" />

						<g:sortableColumn property="description" title="${message(code: 'tekEvent.description.label', default: 'Description')}" />

%{--						<th><g:message code="tekEvent.organizer.label" default="Organizer" /></th>--}%

						<g:sortableColumn property="venue" title="${message(code: 'tekEvent.venue.label', default: 'Venue')}" />

						<g:sortableColumn property="startDate" title="${message(code: 'tekEvent.startDate.label', default: 'Start Date')}" />

						<g:sortableColumn property="revision" title="${message(code: 'tekEvent.city.label', default: 'Revision')}" />

					</tr>
				</thead>
				<tbody>
				<g:each in="${tekEventInstanceList}" status="i" var="tekEventInstance">
					<tr class="${(i % 2) == 0 ? 'even' : 'odd'}">

						<td><g:link action="show" id="${tekEventInstance.id}">${fieldValue(bean: tekEventInstance, field: "name")}</g:link></td>

						<td>${fieldValue(bean: tekEventInstance, field: "city")}</td>

						<td>${fieldValue(bean: tekEventInstance, field: "description")}</td>

%{--						<td>${fieldValue(bean: tekEventInstance, field: "organizer")}</td>--}%

						<td>${fieldValue(bean: tekEventInstance, field: "venue")}</td>

						<td><g:formatDate date="${tekEventInstance.startDate}" /></td>

                        <td>
                            <g:link action="dtList" id="${tekEventInstance.id}" class="btn btn-primary">
                                <i class="fa fa-history"></i> View Revisions
                            </g:link>
                        </td>

					</tr>
				</g:each>
				</tbody>
			</table>
			<div class="pagination">
				<g:paginate total="${tekEventInstanceCount ?: 0}" />
			</div>
		</div>
	</body>
</html>
