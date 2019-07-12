<!DOCTYPE html>
<html>
<head>
    <meta name="layout" content="main"/>
    <g:set var="entityName" value="${message(code: 'purchase.label', default: 'Purchase')}"/>
    <title>
        <g:message code="default.list.label" args="[entityName]"/>
    </title>
</head>
<body>
<a href="#list-purchase" class="skip" tabindex="-1">
    <g:message code="default.link.skip.label" default="Skip to content&hellip;"/>
</a>
<div class="nav" role="navigation">
    <ul>
        <li>
            <g:link class="create" action="create">
                יצירת בקשה חדשה
            </g:link>
        </li>
    </ul>
</div>
<div id="list-purchase" class="content scaffold-list" role="main">
    <h1>
        רשימת בקשות
    </h1>
    <g:if test="${flash.message}">
        <div class="message" role="status">${flash.message}</div>
    </g:if>
    <f:table collection="${purchaseList}"/>

    <div class="pagination">
        <g:paginate total="${purchaseCount ?: 0}"/>
    </div>
</div>
</body>
</html>