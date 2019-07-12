<!DOCTYPE html>
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
        <li>
            <g:link class="list" action="statusDisplay" controller="user">
                רשימת בקשות
            </g:link>
        </li>
        <li>
            <g:link class="btn bg-success" name="isPurchased" action="choice" controller="management"
                    params="[id: params.id, isPurchased: true]">נרכש
            </g:link>
        </li>
    </ul>
</div>
<br>
<div class="text-center" dir="rtl">
    <label for="purchaseName">שם הבקשה</label>
    <input type="text" id="purchaseName" name="purchaseName" value="${purchase.name}" readonly="readonly"/>
</div>
<br>
<g:uploadForm name="myUpload" dir="rtl" resource="${this.purchase}" action="addComment" id="${params.id}"
              class="float-center container" method="POST">
    <table>
        <tr>
            <td>
                <label>שם קובץ</label>
                <g:field name="fileName" type="text"></g:field>
            </td>
        </tr>
        <tr class="float-left">
            <td>
                <label>קובץ מצורף</label>
                <g:field name="myFile" type="file"></g:field>
            </td>
        </tr>
        <tr>
            <td hidden="true">
                <label>שם משתמש\ת</label>
                <g:field name="user" type="text" readonly="true" value="${session.user}"></g:field>
            </td>
        </tr>
        <tr>
            <td>
                <label>הערות</label>
                <textarea class="col-sm-6" name="content" required="true" style="resize:none"></textarea>
            </td>
            <input hidden="hidden" name="purchaseId" value="${purchase.id}">
        </tr>
    </table>
    <div class="text-center">
        <g:submitButton name="submit" class="save bg-info btn"
                        value="שמור בקשה"/>
    </div>
</g:uploadForm>
</body>
</html>
