<!DOCTYPE html>
<html>
<head>
    <meta name="layout" content="main"/>
    <g:set var="entityName" value="${message(code: 'department.label', default: 'Department')}"/>
    <title>
        <g:message code="default.list.label" args="[entityName]"/>
    </title>
</head>
<body>
<a href="#list-department" class="skip" tabindex="-1">
    <g:message code="default.link.skip.label" default="Skip to content&hellip;"/>
</a>
<div class="nav" role="navigation">
    <ul>
        <li>
            <g:link class="create" action="create">
                יצירת מחלקה חדשה
            </g:link>
        </li>
    </ul>
</div>
<div id="list-department" class="content scaffold-list" role="main">
    <h1>
        רשימת מחלקות
    </h1>
    <g:if test="${flash.message}">
        <div class="message" role="status">${flash.message}</div>
    </g:if>
    <f:table collection="${departmentList}"/>

    <div class="pagination">
        <g:paginate total="${departmentCount ?: 0}"/>
    </div>
</div>
</body>
</html>