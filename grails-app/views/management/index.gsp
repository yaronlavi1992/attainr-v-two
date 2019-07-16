<!DOCTYPE html>
<!--<%@ page import="attainrvtwo.Committee" contentType="text/html;charset=UTF-8" %>-->
<!--<%@ page import="attainrvtwo.Department" contentType="text/html;charset=UTF-8" %>-->
<html xmlns:g="http://www.w3.org/1999/html">
<head>
    <meta name="layout" content="main"/>
    <g:set var="entityName" value="${message(code: 'purchase.label', default: 'Purchase')}"/>
    <title><g:message code="default.list.label" args="[entityName]"/></title>
</head>
<body>
<a href="#list-purchase" class="skip" tabindex="-1">
    <g:message code="default.link.skip.label" default="Skip to content&hellip;"/>
</a>
<div class="nav" role="navigation">
    <ul>
        <li>
            <g:link class="create" action="create" controller="purchase">בקשה חדשה</g:link>
        </li>
        <li>
            <g:link class="create bg-info text-white" action="showCompletedPurchases" controller="purchase">בקשות
                שהושלמו
            </g:link>
        </li>
        <li>
            <g:select class="btn bg-info text-white" dir="rtl" id="commDDLid" name="committeeDDL"
                      action="filterByCommittee"
                      controller="management" from="${Committee.findAllByNameNotEqual( 'MANAGEMENT' )}" optionKey="id"
                      optionValue="${name}"
                      value="${committees}" noSelection="${['null':'לפי ועדת..']}"
                      onchange="goToCommittee(this.value)"/>
        </li>
        <li>
            <g:select class="btn bg-info text-white" dir="rtl" id="depDDLid" name="departmentDDL"
                      action="filterByDepartment"
                      controller="management" from="${Department.list()}" optionKey="id" optionValue="${name}"
                      value="${departments}" noSelection="${['null':'לפי מחלקת..']}"
                      onchange="goToDepartment(this.value)"/>
        </li>
<!--        <li>-->
<!--            <g:link class="btn bg-info text-white" dir="rtl" action="filterByDate" controller="management">-->
<!--                לפי תאריך-->
<!--            </g:link>-->
<!--        </li>-->
    </ul>
</div>
<div id="list-purchase" class="content scaffold-list" role="main">
    <h1>
        רשימת בקשות
    </h1>
    <g:if test="${flash.message}">
        <div class="message" role="status">${flash.message}</div>
    </g:if>
    <f:table collection="${purchaseList}" properties="['name','status','departmentApproval','accountantApproval','communityApproval','ceoApproval']"/>

    <div class="pagination">
        <g:paginate total="${purchaseCount ?: 0}"/>
    </div>
</div>
<script type="text/javascript">
    function goToCommittee(requestParams) {
    window.location.href="${'/management/filterByCommittee'}" + "/" + requestParams;
    }
    function goToDepartment(requestParams) {
    window.location.href="${'/management/filterByDepartment'}" + "/" + requestParams;
    }

</script>
</body>
</html>