<!DOCTYPE html>
<html>
<head>
    <meta name="layout" content="main"/>
    <g:set var="entityName" value="${message(code: 'summary.label', default: 'Summary')}"/>
    <title>
        <g:message code="default.list.label" args="[entityName]"/>
    </title>
</head>
<body>
<a href="#list-summary" class="skip" tabindex="-1">
    <g:message code="default.link.skip.label" default="Skip to content&hellip;"/>
</a>
<div class="nav" role="navigation">
    <ul>
        <li>
            <g:link class="create" action="create">
                יצירת סיכום חדש
            </g:link>
        </li>
    </ul>
</div>
<div id="list-summary" class="content scaffold-list" role="main">
    <h1>
        רשימת סיכומים
    </h1>
    <g:if test="${flash.message}">
        <div class="message" role="status">${flash.message}</div>
    </g:if>
    <f:table collection="${summaryList}"/>

    <div class="pagination">
        <g:paginate total="${summaryCount ?: 0}"/>
    </div>
</div>
</body>
</html>