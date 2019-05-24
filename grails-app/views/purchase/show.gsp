<!DOCTYPE html>
<!--<%@ page import="attainrvtwo.PermissionOf" contentType="text/html;charset=UTF-8" %>-->
<!--<%@ page import="attainrvtwo.PurchaseStatus" contentType="text/html;charset=UTF-8" %>-->
<html>
    <head>
        <meta name="layout" content="main" />
        <g:set var="entityName" value="${message(code: 'purchase.label', default: 'Purchase')}" />
        <title><g:message code="default.show.label" args="[entityName]" /></title>
    </head>
    <body>
        <a href="#show-purchase" class="skip" tabindex="-1"><g:message code="default.link.skip.label" default="Skip to content&hellip;"/></a>
        <div class="nav" role="navigation">
            <ul>
                <li><a class="home" href="${createLink(uri: '/')}"><g:message code="default.home.label"/></a></li>
                <li><g:link class="list" action="statusDisplay" controller="user"><g:message code="default.list.label" args="[entityName]" /></g:link></li>
                <li><g:link class="create" action="create"><g:message code="default.new.label" args="[entityName]" /></g:link></li>
                <g:if test="${session.permission == PermissionOf.MID || session.permission == PermissionOf.HIGH}">
                    <li><g:link class="btn bg-danger" action="choice" controller="management" params="[id: params.id, choice: false]">דחיית בקשה</g:link></li>
                    <li><g:link class="btn bg-success" action="choice" controller="management" params="[id: params.id, choice: true]">אישור בקשה</g:link></li>
                </g:if>
                <g:if test="${this.purchase.get(params.id).status == PurchaseStatus.APPROVED}">
                    <li><g:link class="btn bg-success" name="isPurchased" action="choice" controller="management" params="[id: params.id, isPurchased: true]">נרכש</g:link></li>
                </g:if>
            </ul>
        </div>
        <div id="show-purchase" class="content scaffold-show" role="main">
            <h1><g:message code="default.show.label" args="[entityName]" /></h1>
            <g:if test="${flash.message}">
            <div class="message" role="status">${flash.message}</div>
            </g:if>
            <f:display bean="purchase" />
            <g:form resource="${this.purchase}" method="DELETE">
                <fieldset class="buttons">
                    <g:link class="edit" action="edit" resource="${this.purchase}"><g:message code="default.button.edit.label" default="Edit" /></g:link>
                    <input class="delete" type="submit" value="${message(code: 'default.button.delete.label', default: 'Delete')}" onclick="return confirm('${message(code: 'default.button.delete.confirm.message', default: 'Are you sure?')}');" />
                </fieldset>
            </g:form>
        </div>
    </body>
</html>
