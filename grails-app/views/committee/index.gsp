<!DOCTYPE html>
<html>
<head>
    <meta name="layout" content="main"/>
    <g:set var="entityName" value="${message(code: 'committee.label', default: 'Committee')}"/>
    <title>
        <g:message code="default.list.label" args="[entityName]"/>
    </title>
</head>
<body>
<a href="#list-committee" class="skip" tabindex="-1">
    <g:message code="default.link.skip.label" default="Skip to content&hellip;"/>
</a>
<div class="nav" role="navigation">
    <ul>
        <li>
            <g:link class="create" action="create">
                יצירת ועדה חדשה
            </g:link>
        </li>
    </ul>
</div>
<div id="list-committee" class="content scaffold-list" role="main">
    <h1>
        רשימת ועדות
    </h1>
    <g:if test="${flash.message}">
        <div class="message" role="status">${flash.message}</div>
    </g:if>
    <f:table collection="${committeeList}"/>

    <div class="pagination">
        <g:paginate total="${committeeCount ?: 0}"/>
    </div>
</div>
</body>
</html>