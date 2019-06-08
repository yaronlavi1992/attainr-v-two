<!DOCTYPE html>
<html>
<head>
    <link href="//maxcdn.bootstrapcdn.com/bootstrap/3.3.0/css/bootstrap.min.css" rel="stylesheet" id="bootstrap-css">
    <script src="//maxcdn.bootstrapcdn.com/bootstrap/3.3.0/js/bootstrap.min.js"></script>
    <script src="//code.jquery.com/jquery-1.11.1.min.js"></script>

    <meta name="layout" content="main"/>
    <g:set var="entityName" value="${message(code: 'purchase.label', default: 'Purchase')}"/>
    <title>
        <g:message code="default.create.label" args="[entityName]"/>
    </title>

</head>
<body>
<h1 class="title text-center">בקשת רכש חדשה</h1>
<g:form dir="rtl" class="float-center container">
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
                <g:formatDate format="dd-MM-yyyy" name="orderDate" value="${new Date()}"
                              readonly="readonly"></g:formatDate>
            </td>
        </tr>
    </table>
    <br>
    <div class="container">
        <table name="purchaseTable" class="table order-list table-bordered">
            <thead>
            <tr>
                <td class="text-right">מספר שורה</td>
                <td class="text-right">תיאור</td>
                <td class="text-right">כמות אריזה</td>
                <td class="text-right">מחיר אריזה</td>
                <td class="text-right">מימון חיצוני</td>
                <td class="text-right">סה"כ מחיר</td>
                <td class="text-right">פעולה</td>
            </tr>
            </thead>
            <tbody>
            <tr>
                <td class="col-sm-1">
                    <input type="nuber" name="rowNumber" class="form-control"/>
                </td>
                <td class="col-sm-3">
                    <input type="textarea" name="description" class="form-control"/>
                </td>
                <td class="col-sm-1">
                    <input type="number" name="packQuantity" class="form-control"/>
                </td>
                <td class="col-sm-2">
                    <input type="number" name="packPrice" class="form-control"/>
                </td>
                <td class="col-sm-1">
                    <input type="number" name="externalFunding" class="form-control"/>
                </td>
                <td class="col-sm-2">
                    <input type="number" name="totalPrice" class="form-control"/>
                </td>
                <td class="col-sm-2"><a class="deleteRow"></a>
                </td>
            </tr>
            </tbody>
            <tfoot>
            <tr>
                <td colspan="12" style="text-align: right;">
                    <input type="button" class="btn btn-lg btn-block bg-success" id="addrow" value="Add Row"/>
                </td>
            </tr>
            <tr>
            </tr>
            </tfoot>
        </table>
    </div>

    <table name="freeTextTable" class="table-bordered">
        <tr>
            <td class="col-sm-2">
                <label class="text-right float-right">מלל חופשי</label>
            </td>
            <td class="col-sm-10">
                <g:field class="col-sm-12 float-right" type="text" name="freeText"
                         placeholder="מלל חופשי - הערות, דגשים, הסברים, פירוט מקורות התקציב וכו'"></g:field>
            </td>
        </tr>
    </table>

    <table name="quoteTable" class="table order-list table-bordered">
        <thead>
        <tr>
            <td class="text-right">מספר הצעת מחיר</td>
            <td class="text-right">שם הספק</td>
            <td class="text-right">מחיר</td>
            <td class="text-right">קישור</td>
        </tr>
        </thead>
        <tbody>
        <tr>
            <td class="col-sm-1">
                <input type="number" name="firstQuoteNumber" value="1" class="form-control" readonly="readonly"/>
            </td>
            <td class="col-sm-1">
                <input type="text" name="firstSupplierName" class="form-control"/>
            </td>
            <td class="col-sm-1">
                <input type="number" name="firstQuotePrice" class="form-control"/>
            </td>
            <td class="col-sm-1">
                <g:field type="file" name="firstQuoteAttachment"></g:field>
            </td>

        </tr>
        <tr>
            <td class="col-sm-1">
                <input type="number" name="secondQuoteNumber" value="2" class="form-control" readonly="readonly"/>
            </td>
            <td class="col-sm-1">
                <input type="text" name="secondSupplierName" class="form-control"/>
            </td>
            <td class="col-sm-1">
                <input type="number" name="secondQuotePrice" class="form-control"/>
            </td>
            <td class="col-sm-1">
                <g:field type="file" name="secondQuoteAttachment"></g:field>
            </td>
        </tr>
        <tr>
            <td class="col-sm-1">
                <input type="number" name="thirdQuoteNumber" value="3" class="form-control" readonly="readonly"/>
            </td>
            <td class="col-sm-1">
                <input type="text" name="thirdSupplierName" class="form-control"/>
            </td>
            <td class="col-sm-1">
                <input type="number" name="thirdQuotePrice" class="form-control"/>
            </td>
            <td class="col-sm-1">
                <g:field type="file" name="thirdQuoteAttachment"></g:field>
            </td>
        </tr>
        </tbody>
    </table>
    <div class="text-center">
    <input class="bg-success" type="submit" method="POST" value="שלח">
    </div>
</g:form>
</body>
</html>