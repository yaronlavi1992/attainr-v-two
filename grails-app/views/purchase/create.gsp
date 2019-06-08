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
<script type="text/javascript">
        $(document).ready(function () {
            var counter = 0;

            $("#addrow").on("click", function () {
                var newRow = $("<tr>");
                var cols = "";

                cols += '<td><input type="textarea" class="form-control" name="description' + counter + '"/></td>';
                cols += '<td><input type="number decimal" class="form-control" id="packQuantity' + counter + '" name="packQuantity' + counter + '" onchange="calculateTotalPrice()"/></td>';
                cols += '<td><input type="number decimal" class="form-control" id="packPrice' + counter + '" name="packPrice' + counter + '" onchange="calculateTotalPrice()"/></td>';
                cols += '<td><input type="number decimal" class="form-control" name="externalFunding' + counter + '"/></td>';
                cols += '<td><input type="number decimal" class="form-control" id="totalPrice' + counter + '" name="totalPrice' + counter + '" readonly="readonly"/></td>';

                cols += '<td><input type="button" class="ibtnDel btn btn-md btn-danger "  value="מחק"></td>';
                newRow.append(cols);
                $("table.order-list").append(newRow);
                counter++;
            });

            $("table.order-list").on("click", ".ibtnDel", function (event) {
                $(this).closest("tr").remove();
                counter -= 1
            });
        });

<!--        function calculateTotalPrice() {-->
<!--            $('table.order-list tr').each(function () {-->
<!--                if( ( $(this).find('input[id^="packQuantity"]') ).val() != null && ( $(this).find('input[id^="packPrice"]') ).val() != null )-->
<!--                    ( $(this).find('input[id^="totalPrice"]') ).val( ( $(this).find('input[id^="packQuantity"]') ).val() * ( $(this).find('input[id^="packPrice"]') ).val());-->
<!--            });-->
<!--        }-->

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
<h1 class="title text-center">בקשת רכש חדשה</h1>
<g:form dir="rtl" class="float-center container" resource="${this.purchase}" method="POST">
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
                <td class="col-sm-3">
                    <input type="textarea" name="description" class="form-control"/>
                </td>
                <td class="col-sm-1">
                    <input type="number decimal" id="packQuantity" name="packQuantity" class="form-control"
                           onchange="calculateTotalPrice()"/>
                </td>
                <td class="col-sm-2">
                    <input type="number decimal" id="packPrice" name="packPrice" class="form-control"
                           onchange="calculateTotalPrice()"/>
                </td>
                <td class="col-sm-1">
                    <input type="number decimal" name="externalFunding" class="form-control"/>
                </td>
                <td class="col-sm-2">
                    <input type="number decimal" id="totalPrice" name="totalPrice" class="form-control" readonly="readonly"/>
                </td>
                <td class="col-sm-2"><a class="deleteRow"></a>
                </td>
            </tr>
            </tbody>
            <tfoot>
            <tr>
                <td colspan="10">
                    <input type="button" class="col-sm-10 btn btn-lg btn-block bg-success" id="addrow" value="הוסף שורה"/>
                </td>
                <td colspan="2">
                    <input type=" number decimal" class="col-sm-2" id="totalPurchasePrice" name="totalPurchasePrice" readonly="readonly"/>
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

    <table name="quoteTable" class="table table-bordered">
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
        <g:submitButton name="create" class="save bg-success"
                        value="שלח"/>
<!--        <input action="Create" class="bg-success" type="submit" method="POST" value="שלח">-->
    </div>
</g:form>
</body>
</html>