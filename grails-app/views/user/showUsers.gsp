<!DOCTYPE html>
<html>
<!--<%@ page import="attainrvtwo.RoleOf" contentType="text/html;charset=UTF-8" %>-->
<head>
    <meta name="layout" content="main"/>
    <g:set var="entityName" value="${message(code: 'user.label', default: 'User')}"/>
    <title>
        <g:message code="default.list.label" args="[entityName]"/>
    </title>
</head>
<body>
<g:if test="${session.role == RoleOf.COMMUNITY_SECRETARY}">
    <a href="#list-user" class="skip" tabindex="-1">
        <g:message code="default.link.skip.label" default="Skip to content&hellip;"/>
    </a>
    <div class="nav" role="navigation">
        <ul>
            <li>
                <g:link class="create" action="create">
                    יצירת משתמש חדש
                </g:link>
            </li>
        </ul>
    </div>
    <div id="list-user" class="content scaffold-list" role="main">
        <h1>
            רשימת משתמשים
        </h1>
        <g:if test="${flash.message}">
            <div class="message" role="status">${flash.message}</div>
        </g:if>
        <f:table collection="${userList}"/>

        <div class="pagination">
            <g:paginate total="${userCount ?: 0}"/>
        </div>
    </div>
</g:if>
<g:else>
    <div class="message">גישה נדחתה</div>
</g:else>
</body>
</html>