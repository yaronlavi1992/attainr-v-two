<!DOCTYPE html>
<!--<%@ page import="attainrvtwo.RoleOf" contentType="text/html;charset=UTF-8" %>-->
<html xmlns:g="http://www.w3.org/1999/html">
<head>
    <meta name="layout" content="main"/>
    <g:set var="entityName" value="${message(code: 'user.label', default: 'User')}"/>
    <title>מנהל מחלקה</title>
</head>
<body>
<a href="#list-purchase" class="skip" tabindex="-1">
    <g:message code="default.link.skip.label" default="Skip to content&hellip;"/>
</a>
<div class="nav container" role="navigation">
    <g:if test="${session.role == RoleOf.COMMUNITY_SECRETARY}">
        <ul>
            <li>
                <g:link class="create btn bg-success" name="addUser" action="create" controller="user">הוסף משתמש
                </g:link>
            </li>
            <li>
                <g:link class="create btn bg-success" name="addCommittee" action="create" controller="committee">הוסף
                    וועדה
                </g:link>
            </li>
            <li>
                <g:link class="create btn bg-success" name="addDepartment" action="create" controller="department">הוסף
                    מחלקה
                </g:link>
            </li>
        </ul>
        <ul>
            <li>
                <g:link class="list btn bg-primary" name="showUsers" action="showUsers" controller="user">הצג משתמשים
                </g:link>
            </li>
        </ul>
    </g:if>
</div>
<div id="list-purchase" class="content scaffold-list" role="main">
    <h1>
        <g:message code="default.list.label" args="[entityName]"/>
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