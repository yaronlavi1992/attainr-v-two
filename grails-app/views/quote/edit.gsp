<!DOCTYPE html>
<html>
<head>
    <meta name="layout" content="main"/>
    <g:set var="entityName" value="${message(code: 'quote.label', default: 'Quote')}"/>
    <title>
        <g:message code="default.edit.label" args="[entityName]"/>
    </title>
</head>
<body>
<a href="#edit-quote" class="skip" tabindex="-1">
    <g:message code="default.link.skip.label" default="Skip to content&hellip;"/>
</a>
<div class="nav" role="navigation">
    <ul>
        <li>
            <g:link class="list" action="index">
                רשימת הצעות מחיר
            </g:link>
        </li>
        <li>
            <g:link class="create" action="create">
                יצירת הצעת מחיר חדשה
            </g:link>
        </li>
    </ul>
</div>
<div id="edit-quote" class="content scaffold-edit" role="main">
    <h1>
        עריכת הצעת מחיר
    </h1>
    <g:if test="${flash.message}">
        <div class="message" role="status">${flash.message}</div>
    </g:if>
    <g:hasErrors bean="${this.quote}">
        <ul class="errors" role="alert">
            <g:eachError bean="${this.quote}" var="error">
                <li
                <g:if test="${error in org.springframework.validation.FieldError}">data-field-id="${error.field}"</g:if>
                >
                <g:message error="${error}"/>
                </li>
            </g:eachError>
        </ul>
    </g:hasErrors>
    <g:form resource="${this.quote}" method="PUT">
        <g:hiddenField name="version" value="${this.quote?.version}"/>
        <fieldset class="form">
            <f:all bean="quote"/>
        </fieldset>
        <fieldset class="buttons">
            <input class="save" type="submit"
                   value="${message(code: 'default.button.update.label', default: 'Update')}"/>
        </fieldset>
    </g:form>
</div>
</body>
</html>
