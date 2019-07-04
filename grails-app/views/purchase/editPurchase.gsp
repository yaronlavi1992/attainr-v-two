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
            var counter = $('#purchaseItemsLength').val() - 1;

            $("#addrow").on("click", function () {
                var newRow = $("<tr>");
                var cols = "";

                cols += '<td><input type="number decimal" class="form-control" id="financialSection" name="financialSection' + counter + '" required="true"/></td>';
                cols += '<td><input type="textarea" class="form-control" id="description" name="description' + counter + '" required="true"/></td>';
                cols += '<td><input type="number decimal" class="form-control" id="packQuantity' + counter + '" name="packQuantity' + counter + '" onchange="calculateTotalPrice()" required="true"/></td>';
                cols += '<td><input type="number decimal" class="form-control" id="packPrice' + counter + '" name="packPrice' + counter + '" onchange="calculateTotalPrice()" required="true"/></td>';
                cols += '<td><input type="number decimal" class="form-control" id="externalFunding" value="0" name="externalFunding' + counter + '"/></td>';
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

        function calculateTotalPrice() {
            $('table.order-list tr').each(function () {
                if( ( $(this).find('input[id^="packQuantity"]') ).val() != null && ( $(this).find('input[id^="packPrice"]') ).val() != null )
                    ( $(this).find('input[id^="totalPrice"]') ).val( ( $(this).find('input[id^="packQuantity"]') ).val() * ( $(this).find('input[id^="packPrice"]') ).val());
            });
            calculateGrandTotal();
        }

        function calculateRow(row) {
            var price = +row.find('input[name^="price"]').val();

        }

        function calculateGrandTotal() {
            var grandTotal = 0;
            $("table.order-list").find('input[name^="totalPrice"]').each(function () {
                grandTotal += +$(this).val();
            });
            $("#totalPurchasePrice").val(grandTotal);
            if(grandTotal > 2000) {
                $('input[name$="SupplierName"]').attr("required", "true");
                $('input[name$="QuotePrice"]').attr("required", "true");
                $('input[name$="QuoteAttachment"]').attr("required", "true");
            } else {
                $('input[name="secondSupplierName"]').removeAttr("required");
                $('input[name="secondQuotePrice"]').removeAttr("required");
                $('input[name="secondQuoteAttachment"]').removeAttr("required");
                $('input[name="thirdSupplierName"]').removeAttr("required");
                $('input[name="thirdQuotePrice"]').removeAttr("required");
                $('input[name="thirdQuoteAttachment"]').removeAttr("required");
            }
        }

        function compareTotalPriceAndQuotePrice() {
            if ($("#totalPurchasePrice").val() != $("#firstQuotePrice").val()) {
                alert('הצעת המחיר אינה תואמת את מחיר ההזמנה');
            }
        }



</script>
<h1 class="title text-center">בקשת רכש חדשה</h1>
<g:uploadForm dir="rtl" resource="${this.purchase}" controller="purchase" class="float-center container" action="saveAfterEdit"
              method="POST">
    <table>
        <tr>
            <td>
                <label>מגיש הבקשה</label>
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
        </tr>
    </table>
    <br>
    <div class="text-center">
        <label for="purchaseName">שם הבקשה</label>
        <input type="text" id="purchaseName" name="purchaseName" value="${purchase.name}" readonly="true"/>
    </div>
    <table name="purchaseTable" class="table order-list table-bordered">
        <thead>
        <tr>
            <td class="text-right">סעיף תקציבי</td>
            <td class="text-right">תיאור</td>
            <td class="text-right">כמות אריזה</td>
            <td class="text-right">מחיר אריזה</td>
            <td class="text-right">מימון חיצוני</td>
            <td class="text-right">סה"כ מחיר(₪)</td>
            <td class="text-right">פעולה</td>
        </tr>
        </thead>
        <tbody>

        <input type="number" id="purchaseItemsLength" hidden="true" value="${(purchase.purchaseItems).size()}"/>
        <g:each status="i" in="${purchase.purchaseItems}" var="item">
            <tr>
                <td class="col-sm-1">
                    <input type="number decimal" id="financialSection${i == 0 ? '' : i - 1}" name="financialSection${i == 0 ? '' : i - 1}" class="form-control" value="${item.financialSection}" />
                </td>
                <td class="col-sm-3">
                    <input type="text" name="description${i == 0 ? '' : i - 1}" class="form-control" value="${item.description}"
                           />
                </td>
                <td class="col-sm-1">
                    <input type="number" id="packQuantity${i == 0 ? '' : i - 1}" name="packQuantity${i == 0 ? '' : i - 1}" class="form-control"
                           onchange="calculateTotalPrice()" value="${item.packQuantity as Integer}" />
                </td>
                <td class="col-sm-2">
                    <input type="number decimal" id="packPrice${i == 0 ? '' : i - 1}" name="packPrice${i == 0 ? '' : i - 1}" class="form-control"
                           onchange="calculateTotalPrice()" value="${item.packPrice}" />
                </td>
                <td class="col-sm-1">
                    <input type="number decimal" name="externalFunding${i == 0 ? '' : i - 1}" class="form-control"
                           value="${item.externalFunding}${i == 0 ? '' : i - 1}" />
                </td>
                <td class="col-sm-2">
                    <input type="number decimal" id="totalPrice${i == 0 ? '' : i - 1}" name="totalPrice${i == 0 ? '' : i - 1}" class="form-control"
                           value="${item.totalItemPrice}" readonly="true"/>
                </td>
                <td class="col-sm-2"><a class="deleteRow" disabled="true"></a>
                </td>
            </tr>
        </g:each>

        </tbody>
        <tfoot>
        <tr>
            <td colspan="5">
                <input type="button" class="col-sm-12 btn btn-lg btn-block bg-success" id="addrow" value="הוסף שורה"/>
            </td>
            <td colspan="1">
                <label for="totalPurchasePrice" class="float-right">מחיר בקשה סופי(₪)</label>
                <input type="number decimal" class="col-sm-12" id="totalPurchasePrice" name="totalPurchasePrice"
                       value="${purchase.totalPurchasePrice}" readonly="true"/>
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
                          placeholder="מלל חופשי - הערות, דגשים, הסברים, פירוט מקורות התקציב וכו'" >${purchase.description}</textarea>
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
                       readonly="true"/>
            </td>
            <td class="col-sm-1">
                <input type="text" name="firstSupplierName" class="form-control" required="true"/>
            </td>
            <td class="col-sm-1">
                <input type="number decimal" id="firstQuotePrice" name="firstQuotePrice" class="form-control"
                       required="true"/>
            </td>
            <td class="col-sm-1">
                <g:field type="file" name="firstQuoteAttachment" required="true"></g:field>
            </td>

        </tr>
        <tr>
            <td class="col-sm-1">
                <input type="number" name="secondQuoteNumber" value="2" class="form-control text-center"
                       readonly="true"/>
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
                <input type="number" name="thirdQuoteNumber" value="3" class="form-control text-center"
                       readonly="true"/>
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
        <g:submitButton name="create" class="save bg-info btn"
                        value="שמור בקשה" onclick="compareTotalPriceAndQuotePrice()"/>
    </div>
    <br>
</g:uploadForm>
</body>
</html>