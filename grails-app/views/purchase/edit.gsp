<!DOCTYPE html>
<!--<%@ page import="attainrvtwo.PermissionOf" contentType="text/html;charset=UTF-8" %>-->
<!--<%@ page import="attainrvtwo.PurchaseStatus" contentType="text/html;charset=UTF-8" %>-->
<html>
<head>
    <link href="//maxcdn.bootstrapcdn.com/bootstrap/3.3.0/css/bootstrap.min.css" rel="stylesheet" id="bootstrap-css">
    <script src="//maxcdn.bootstrapcdn.com/bootstrap/3.3.0/js/bootstrap.min.js"></script>
    <script src="//code.jquery.com/jquery-1.11.1.min.js"></script>

    <meta name="layout" content="main"/>
    <g:set var="entityName" value="${message(code: 'purchase.label', default: 'Purchase')}"/>
    <title>
        <g:message code="default.show.label" args="[entityName]"/>
    </title>
</head>
<body>
<br>
<a href="#show-purchase" class="skip" tabindex="-1">
    <g:message code="default.link.skip.label" default="Skip to content&hellip;"/>
</a>
<div class="nav container" role="navigation">
    <ul>
        <li><a class="home" href="${createLink(uri: '/')}">
            <g:message code="default.home.label"/>
        </a></li>
        <li>
            <g:link class="list" action="statusDisplay" controller="user">
                <g:message code="default.list.label" args="[entityName]"/>
            </g:link>
        </li>
        <li>
            <g:link class="create" action="create">
                <g:message code="default.new.label" args="[entityName]"/>
            </g:link>
        </li>
        <g:if test="${this.purchase.get(params.id).status == PurchaseStatus.APPROVED}">
            <li>
                <g:link class="btn bg-success" name="isPurchased" action="choice" controller="management"
                        params="[id: params.id, isPurchased: true]">נרכש
                </g:link>
            </li>
        </g:if>
    </ul>
</div>
<g:uploadForm dir="rtl" resource="${this.purchase}" controller="purchase" class="float-center container" action="save"
        method="POST">
    <table>
        <tr>
            <td>
                <label>שם משתמש</label>
                <g:field type="text" name="userName" value="${session.user}" readonly="readonly"></g:field>
            </td>
            <td>
                <label>מחלקה</label>
                <g:field type="text" name="departmentName" value="${session.department}" readonly="readonly"></g:field>
            </td>
            <td>
                <label>ועדה</label>
                <g:field type="text" name="committeeName" value="${session.committee}" readonly="readonly"></g:field>
            </td>
            <td>
                <label>תאריך</label>
                <g:formatDate format="dd-MM-yyyy" name="orderDate" value="${purchase.paymentDate}"
                              readonly="readonly"></g:formatDate>
            </td>
        </tr>
    </table>
    <br>
    <div class="text-center">
        <label for="purchaseName">שם הבקשה</label>
        <input type="text" id="purchaseName" name="purchaseName" value="${purchase.name}" readonly="readonly"/>
    </div>
    <br>
    <g:if test="${this.purchase.get(params.id).status == PurchaseStatus.PURCHASED}">
        <g:uploadForm dir="rtl" resource="${this.purchase}" class="float-center container" method="POST">
            <table>
                <tr>
                    <td>
                        <label>שם קובץ</label>
                        <g:field name="fileName" type="text"></g:field>
                    </td>
                    <td>
                        <label>קובץ מצורף</label>
                        <g:field name="myFile" type="file"></g:field>
                    </td>
                    <td>
                        <label>סכום קבלה</label>
                        <g:field name="sum" type="number decimal"></g:field>
                    </td>
                    <input hidden="hidden" name="purchaseId" value="${purchase.id}">
                </tr>
                <tr>
                    <td colspan="3" class="text-center">
                        <g:link class="btn bg-success"
                                params="[purchaseId: params.id, fileName: fileName, myFile: myFile, sum: sum, id: purchaseId]"
                                name="addReceipt" action="addReceipt">הוסף קבלה
                        </g:link>
                    </td>
                </tr>
            </table>
        </g:uploadForm>
    </g:if>
</g:uploadForm>
</body>
</html>
