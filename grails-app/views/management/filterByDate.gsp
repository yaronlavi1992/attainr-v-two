<!DOCTYPE html>
<!--<%@ page import="attainrvtwo.Purchase" contentType="text/html;charset=UTF-8" %>-->
<!--<%@ page import="attainrvtwo.Approval" contentType="text/html;charset=UTF-8" %>-->
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
        <form controller="management" class="float-center container" action="filterByDate"
              method="POST">
                <label>Starting Date</label>
                <g:datePicker name="startingDate" value="${new Date()}" precision="day"
                              noSelection="['':'-Choose-']" relativeYears="[-5..5]"/>
                <label>Closing Date</label>
                <g:datePicker name="closingDate" value="${new Date()}" precision="day"
                              noSelection="['':'-Choose-']" relativeYears="[-5..5]"/>
                <g:submitButton name="submit" class="bg-info btn text-white"
                                value="סנן לפי התאריכים"/>
        </form>
    </ul>
</div>
<div id="list-purchase" class="content scaffold-list" role="main">
    <div class="mx-auto text-center" style="width: 300px;" dir="rtl">
        <h1><b>בקשות מתאריך<br>${startingDate_day} ${startingDate_month} ${startingDate_year} עד <br> ${closingDate_day} ${closingDate_month} ${closingDate_year}</b></h1>
    </div>
    <h1>
        רשימת בקשות
    </h1>
    <g:if test="${flash.message}">
        <div class="message" role="status">${flash.message}</div>
    </g:if>
    <f:table collection="${purchaseList}"
             properties="['name','paymentDateApproval','departmentApproval','accountantApproval','communityApproval','ceoApproval','paymentCloseDateApproval']"/>

    <div class="pagination">
        <g:paginate total="${purchaseCount ?: 0}"/>
    </div>
</div>
<script type="text/javascript">
    function goToDate() {
    window.location.href="${'/management/filterByDate'}" + "/" + "startingDate=" + $('#startingDate_day').val() + " " + $('#startingDate_month').val() + " " + $('#startingDate_year').val() + "&" + "closingDate=" + $('#closingDate_day').val() + " " + $('#closingDate_month').val() + " " + $('#closingDate_year').val();
    }


</script>
</body>
</html>