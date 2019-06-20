<!DOCTYPE html>
<!--<%@ page import="attainrvtwo.PermissionOf" contentType="text/html;charset=UTF-8" %>-->
<!--<%@ page import="attainrvtwo.PurchaseStatus" contentType="text/html;charset=UTF-8" %>-->
<!--<%@ page import="attainrvtwo.RoleOf" contentType="text/html;charset=UTF-8" %>-->
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

<script type="text/javascript">
        $(document).ready(function () {
            var counter = 0;

            $("#addrow").on("click", function () {
                var newRow = $("<tr>");
                var cols = "";

                cols += '<td><input type="text" class="form-control" id="description' + counter + '" name="description' + counter + '" readonly="readonly"/></td>';
                cols += '<td><input type="number decimal" class="form-control" id="packQuantity' + counter + '" name="packQuantity' + counter + '" onchange="calculateTotalPrice()" readonly="readonly"/></td>';
                cols += '<td><input type="number decimal" class="form-control" id="packPrice' + counter + '" name="packPrice' + counter + '" onchange="calculateTotalPrice()" readonly="readonly"/></td>';
                cols += '<td><input type="number decimal" class="form-control" name="externalFunding' + counter + '" readonly="readonly"/></td>';
                cols += '<td><input type="number decimal" class="form-control" id="totalPrice' + counter + '" name="totalPrice' + counter + '" readonly="readonly"/></td>';

                cols += '<td><input type="button" class="ibtnDel btn btn-md btn-danger "  value="מחק" disabled="true"></td>';
                newRow.append(cols);
                $("table.order-list").append(newRow);
                counter++;
            });

            $("table.order-list").on("click", ".ibtnDel", function (event) {
                $(this).closest("tr").remove();
                counter -= 1
            });

        });

        function calculateTotalPrice() {
            $('table.order-list tr').each(function () {
                if( ( $(this).find('input[id^="packQuantity"]') ).val() != null && ( $(this).find('input[id^="packPrice"]') ).val() != null )
                    ( $(this).find('input[id^="totalPrice"]') ).val( ( $(this).find('input[id^="packQuantity"]') ).val() * ( $(this).find('input[id^="packPrice"]') ).val());
            });
        }

        function calculateRow(row) {
            var price = +row.find('input[name^="price"]').val();

        }

        function calculateGrandTotal() {
            var grandTotal = 0;
            $("table.order-list").find('input[name^="price"]').each(function () {
                grandTotal += +$(this).val();
            });
            $("#grandtotal").text(grandTotal.toFixed(2));
        }


</script>
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
        <g:if test="${session.permission == PermissionOf.MID || session.permission == PermissionOf.HIGH}">
            <li>
                <g:link class="btn bg-danger" action="choice" controller="management"
                        params="[id: params.id, choice: false]">דחיית בקשה
                </g:link>
            </li>
            <li>
                <g:link class="btn bg-success" action="choice" controller="management"
                        params="[id: params.id, choice: true]">אישור בקשה
                </g:link>
            </li>
        </g:if>
        <g:if test="${this.purchase.get(params.id).status == PurchaseStatus.APPROVED}">
            <li>
                <g:link class="btn bg-success" name="isPurchased" action="choice" controller="management"
                        params="[id: params.id, isPurchased: true]">נרכש
                </g:link>
            </li>
        </g:if>
        <g:if test="${this.purchase.get(params.id).status == PurchaseStatus.PURCHASED}">
            <li>
                <g:link class="btn bg-success" params="[id: params.id]" name="addReceipt" action="attachReceipt">הוסף
                    קבלה
                </g:link>
            </li>
        </g:if>
        <g:if test="${this.purchase.get(params.id).status == PurchaseStatus.PAYMENT_REQUIRED}">
            <li>
                <g:link class="btn bg-primary" controller="myFile" action="showFile"
                        id="${purchase?.receipts[0]?.file?.id}"
                        name="showReceipt">הצג קבלה
                </g:link>
            </li>
        </g:if>
        <g:if test="${this.purchase.get(params.id).status == PurchaseStatus.PAYMENT_REQUIRED && session.role == RoleOf.COMMUNITY_ACCOUNTANT}">
            <li>
                <g:link class="btn bg-primary" controller="management" action="statusComplete" id="${params.id}" name="showReceipt">סיים בקשה
                </g:link>
            </li>
        </g:if>
    </ul>
</div>

<g:form dir="rtl" resource="${this.purchase}" controller="purchase" class="float-center container" action="save"
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
    <table name="purchaseTable" class="table order-list table-bordered">
        <thead>
        <tr>
            <td class="text-right">תיאור</td>
            <td class="text-right">כמות אריזה</td>
            <td class="text-right">מחיר אריזה</td>
            <td class="text-right">מימון חיצוני</td>
            <td class="text-right">סה"כ מחיר(₪)</td>
            <td class="text-right">פעולה</td>
        </tr>
        </thead>
        <tbody>

        <g:each in="${purchase.purchaseItems}">
            <tr>
                <td class="col-sm-3">
                    <input type="text" name="description" class="form-control" value="${it.description}"
                           readonly="readonly"/>
                </td>
                <td class="col-sm-1">
                    <input type="number" id="packQuantity" name="packQuantity" class="form-control"
                           onchange="calculateTotalPrice()" value="${it.packQuantity}" readonly="readonly"/>
                </td>
                <td class="col-sm-2">
                    <input type="number decimal" id="packPrice" name="packPrice" class="form-control"
                           onchange="calculateTotalPrice()" value="${it.packPrice}" readonly="readonly"/>
                </td>
                <td class="col-sm-1">
                    <input type="number decimal" name="externalFunding" class="form-control"
                           value="${it.externalFunding}" readonly="readonly"/>
                </td>
                <td class="col-sm-2">
                    <input type="number decimal" id="totalPrice" name="totalPrice" class="form-control"
                           value="${it.totalItemPrice}" readonly="readonly"/>
                </td>
                <td class="col-sm-2"><a class="deleteRow" disabled="true"></a>
                </td>
            </tr>
        </g:each>

        </tbody>
        <tfoot>
        <tr>
            <td colspan="4">
                <input type="button" class="col-sm-12 btn btn-lg btn-block bg-success" id="addrow" value="הוסף שורה"
                       disabled="true"/>
            </td>
            <td colspan="1">
                <label for="totalPurchasePrice" class="float-right">מחיר בקשה סופי(₪)</label>
                <input type="number decimal" class="col-sm-12" id="totalPurchasePrice" name="totalPurchasePrice"
                       value="${purchase.totalPurchasePrice}" readonly="readonly"/>
            </td>
            <td colspan="1">

            </td>
        </tr>
        <tr>
        </tr>
        </tfoot>
    </table>

    <table name="freeTextTable" class="table-bordered">
        <tr>
            <td class="col-sm-2">
                <label class="text-right float-right">מלל חופשי</label>
            </td>
            <td class="col-sm-10">
                <textarea class="col-sm-12 float-right" name="freeText" id="" style="resize:none"
                          placeholder="מלל חופשי - הערות, דגשים, הסברים, פירוט מקורות התקציב וכו'" readonly="readonly">${purchase.description}</textarea>
            </td>
        </tr>
    </table>

    <table name="quoteTable" class="table table-bordered">
        <thead>
        <tr>
            <td class="text-right">מספר הצעת מחיר</td>
            <td class="text-right">שם הספק</td>
            <td class="text-right">מחיר(₪)</td>
            <td class="text-right">קישור</td>
        </tr>
        </thead>
        <tbody>
        <tr>
            <td class="col-sm-1">
                <input type="number" name="firstQuoteNumber" value="1" class="form-control text-center"
                       readonly="readonly"/>
            </td>
            <td class="col-sm-1">
                <input type="text" name="firstSupplierName" class="form-control" value="${purchase.quotes[0]?.name}"
                       readonly="readonly"/>
            </td>
            <td class="col-sm-1">
                <input type="number decimal" name="firstQuotePrice" class="form-control"
                       value="${purchase.quotes[0]?.price}" readonly="readonly"/>
            </td>
            <td class="col-sm-1">
                <g:link controller="myFile" action="showFile" id="${purchase?.quotes[0]?.file?.id}"
                        name="firstQuoteAttachment" disabled="true">${purchase?.quotes[0]?.name}
                </g:link>
            </td>
        </tr>
        <tr>
            <td class="col-sm-1">
                <input type="number" name="secondQuoteNumber" value="2" class="form-control text-center"
                       readonly="readonly"/>
            </td>
            <td class="col-sm-1">
                <input type="text" name="secondSupplierName" class="form-control" value="${purchase.quotes[1]?.name}"
                       readonly="readonly"/>
            </td>
            <td class="col-sm-1">
                <input type="number" name="secondQuotePrice" class="form-control" value="${purchase.quotes[1]?.price}"
                       readonly="readonly"/>
            </td>
            <td class="col-sm-1">
                <g:link controller="myFile" action="showFile" id="${purchase?.quotes[1]?.file?.id}"
                        name="secondQuoteAttachment" disabled="true">${purchase?.quotes[1]?.name}
                </g:link>
            </td>
        </tr>
        <tr>
            <td class="col-sm-1">
                <input type="number" name="thirdQuoteNumber" value="3" class="form-control text-center"
                       readonly="readonly"/>
            </td>
            <td class="col-sm-1">
                <input type="text" name="thirdSupplierName" class="form-control" value="${purchase.quotes[2]?.name}"
                       readonly="readonly"/>
            </td>
            <td class="col-sm-1">
                <input type="number" name="thirdQuotePrice" class="form-control" value="${purchase.quotes[2]?.price}"
                       readonly="readonly"/>
            </td>
            <td class="col-sm-1">
                <g:link controller="myFile" action="showFile" id="${purchase?.quotes[2]?.file?.id}"
                        name="thirdQuoteAttachment" disabled="true">${purchase?.quotes[2]?.name}
                </g:link>
            </td>
        </tr>
        </tbody>
    </table>
    <br>
</g:form>
<!--<div id="show-purchase" class="content scaffold-show" role="main">-->
<!--    <h1>-->
<!--        <g:message code="default.show.label" args="[entityName]"/>-->
<!--    </h1>-->
<!--    <g:if test="${flash.message}">-->
<!--        <div class="message" role="status">${flash.message}</div>-->
<!--    </g:if>-->
<!--    <f:display bean="purchase"/>-->
<!--    <g:form resource="${this.purchase}" method="DELETE">-->
<!--        <fieldset class="buttons">-->
<!--            <g:link class="edit" action="edit" resource="${this.purchase}">-->
<!--                <g:message code="default.button.edit.label" default="Edit"/>-->
<!--            </g:link>-->
<!--            <input class="delete" type="submit"-->
<!--                   value="${message(code: 'default.button.delete.label', default: 'Delete')}"-->
<!--                   onclick="return confirm('${message(code: 'default.button.delete.confirm.message', default: 'Are you sure?')}');"/>-->
<!--        </fieldset>-->
<!--    </g:form>-->
<!--</div>-->
</body>
</html>
