<!DOCTYPE html>
<!--<%@ page import="attainrvtwo.Department" contentType="text/html;charset=UTF-8" %>-->
<html xmlns:g="http://www.w3.org/1999/html">
<head>
    <meta name="layout" content="main" />
    <g:set var="entityName" value="${message(code: 'purchase.label', default: 'Purchase')}" />
    <title>ניהול קהילה</title>
</head>
<body>
<a href="#list-purchase" class="skip" tabindex="-1"><g:message code="default.link.skip.label" default="Skip to content&hellip;"/></a>
<div class="nav" role="navigation">
    <ul>
        <!--        <li><a class="home" href="${createLink(uri: '/volunteer/index')}"><g:message code="default.home.label"/></a></li>-->
        <li><g:link class="create" action="create" controller="purchase">בקשה חדשה</g:link></li>
        <li><g:select class="btn bg-primary" dir="rtl" id="depDDLid" name="departmentDDL" action="filterByDepartment" controller="management" from="${Department.list()}" optionKey="id" optionValue="${name}" value="${departments}" noSelection="${['null':'לפי מחלקת..']}" onchange="goToPage(this.value)"/></li>
    </ul>
</div>
<div id="list-purchase" class="content scaffold-list" role="main">
    <h1><g:message code="default.list.label" args="[entityName]" /></h1>

    <g:if test="${session.filterPurchases}">
            <div class="mx-auto text-center" style="width: 300px;" dir="rtl">
                <h1><b>בקשות של מחלקת: ${Department.get(params.id)}</b></h1>
            </div>
    </g:if>

    <g:if test="${flash.message}">
        <div class="message" role="status">${flash.message}</div>
    </g:if>
    <f:table collection="${purchaseList}" />

    <div class="pagination">
        <g:paginate total="${purchaseCount ?: 0}" />
    </div>
</div>
<script type="text/javascript">
    function goToPage(requestParams) {
    window.location.href="${'/management/filterByDepartment'}" + "/" + requestParams;
    }
</script>
</body>
</html>