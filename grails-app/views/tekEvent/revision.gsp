


%{--<%@ page import="event.TekEvent" %>--}%
%{--<%--}%

%{--%>--}%
%{--<!DOCTYPE html>--}%
%{--<html>--}%
%{--<head>--}%
%{--    <meta name="layout" content="main">--}%
%{--    <g:set var="entityName" value="${message(code: 'tekUser.label', default: 'TekEvent')}"/>--}%
%{--    <title><g:message code="default.list.label" args="[entityName]"/></title>--}%
%{--</head>--}%

%{--<body>--}%
%{--<a href="#list-tekEvent" class="skip" tabindex="-1"><g:message code="default.link.skip.label" default="Skip to content&hellip;"/></a>--}%
%{--<div class="nav" role="navigation">--}%
%{--    <ul>--}%
%{--        <li>--}%
%{--            <a class="home" href="${createLink(uri: '/')}">--}%
%{--                <g:message code="default.home.label"/>--}%
%{--            </a>--}%
%{--        </li>--}%
%{--        <li>--}%
%{--            <g:link class="create" action="create">--}%
%{--                <g:message code="default.new.label" args="[entityName]"/>--}%
%{--            </g:link>--}%
%{--        </li>--}%
%{--    </ul>--}%
%{--</div>--}%

%{--<div id="list-tekEvent" class="content scaffold-list" role="main">--}%
%{--    <h1><g:message code="default.list.label" args="[entityName]"/></h1>--}%
%{--    <g:if test="${flash.message}">--}%
%{--        <div class="message" role="status">${flash.message}</div>--}%
%{--    </g:if>--}%
%{--</div>--}%
%{--<table class="display compact" id="dt">--}%
%{--    <thead>--}%
%{--    <tr>--}%
%{--        <th>Name</th>--}%
%{--        <th>Description</th>--}%
%{--        <th>City</th>--}%
%{--        <th>Created Date</th>--}%
%{--        <th>Last Updated</th>--}%
%{--        <th>Changed By</th>--}%
%{--    </tr>--}%
%{--    </thead>--}%
%{--    <tbody>--}%
%{--    <g:each var="audited" in="${data["result"]}">--}%
%{--        <tr>--}%
%{--            <td>${audited['name']}</td>--}%
%{--            <td>${audited['description']}</td>--}%
%{--            <td style="width: 15px">${audited['city']}</td>--}%
%{--            <td>${audited['date_created']}</td>--}%
%{--            <td>${audited['last_updated']}</td>--}%
%{--            <td><a href="${createLink(controller: 'TekEvent', action: 'show')}/${audited['changedBy']}">${TekEvent.get(audited['changedBy'])}</a></td>--}%

%{--        </tr>--}%
%{--    </g:each>--}%
%{--    </tbody>--}%
%{--    <tfoot>--}%

%{--    </tfoot>--}%
%{--</table>--}%
%{--</body>--}%
%{--</html>--}%
