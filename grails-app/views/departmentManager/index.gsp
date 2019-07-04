<!DOCTYPE html>
<!--<%@ page import="attainrvtwo.RoleOf" contentType="text/html;charset=UTF-8" %>-->
<html xmlns:g="http://www.w3.org/1999/html">
<head>
    <meta name="layout" content="main"/>
    <g:set var="entityName" value="${message(code: 'user.label', default: 'Purchase')}"/>
    <title>מנהל מחלקה</title>
</head>
<body>
<a href="#list-purchase" class="skip" tabindex="-1">
    <g:message code="default.link.skip.label" default="Skip to content&hellip;"/>
</a>
<div class="nav container" role="navigation">
    <ul>
        <li>
            <g:link class="create bg-success text-white" action="create" controller="purchase">בקשה חדשה</g:link>
        </li>
        <li>
            <g:link class="create bg-info text-white" action="showCompletedPurchases" controller="purchase">בקשות שהושלמו</g:link>
        </li>
        <li>
            <g:link class="create bg-info text-white" action="index" controller="summary">סיכומי ישיבות</g:link>
        </li>
        <g:if test="${session.role == RoleOf.COMMUNITY_SECRETARY}">
            <li>
                <g:link class="create btn bg-success text-white" name="addUser" action="create" controller="user">הוסף משתמש
                </g:link>
            </li>
            <li>
                <g:link class="list btn bg-info text-white" name="showUsers" action="showUsers" controller="user">הצג משתמשים
                </g:link>
            </li>
        </g:if>
    </ul>
</div>
<div id="list-purchase" class="content scaffold-list" role="main">
    <h1>
        <g:message code="default.list.label" args="[entityName]"/>
    </h1>
    <g:if test="${flash.message}">
        <div class="message" role="status">${flash.message}</div>
    </g:if>
    <f:table collection="${purchaseList}" properties="['name','status','departmentApproval','accountantApproval','communityApproval','ceoApproval']"/>

    <div class="pagination">
        <g:paginate total="${purchaseCount ?: 0}"/>
    </div>
</div>
</body>
</html>