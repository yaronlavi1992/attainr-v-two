<!DOCTYPE html>
<html>
<head>
    <meta name="layout" content="main"/>
    <g:set var="entityName" value="${message(code: 'summary.label', default: 'Summary')}"/>
    <title>
        <g:message code="default.create.label" args="[entityName]"/>
    </title>
</head>
<body>
<a href="#create-summary" class="skip" tabindex="-1">
    <g:message code="default.link.skip.label" default="Skip to content&hellip;"/>
</a>
<div class="nav" role="navigation">
    <ul>
        <li>
            <g:link class="list" action="index">
                רשימת סיכומים
            </g:link>
        </li>
    </ul>
</div>
<div id="create-summary" class="content scaffold-create" role="main">
    <h1>
        יצירת סיכום חדש
    </h1>
    <g:if test="${flash.message}">
        <div class="message" role="status">${flash.message}</div>
    </g:if>
    <g:hasErrors bean="${this.summary}">
        <ul class="errors" role="alert">
            <g:eachError bean="${this.summary}" var="error">
                <li
                <g:if test="${error in org.springframework.validation.FieldError}">data-field-id="${error.field}"</g:if>
                >
                <g:message error="${error}"/>
                </li>
            </g:eachError>
        </ul>
    </g:hasErrors>
    <g:uploadForm action="save" resource="${this.summary}" method="POST">
        <fieldset class="form">
            <div class="fieldcontain">
                <label>Description</label>
                <g:field type="text" name="description" required="true"/>
                <br>
                <br>
                <label>FileName</label>
                <g:field type="text" name="fileName" required="true"/>
                <br>
                <br>
                <label>File</label>
                <g:field type="file" name="summaryFile" required="true"></g:field>
                <g:field hidden="true" type="text" name="committee.id" value="${session.committeeId}"/>
            </div>
<!--            <f:all bean="summary"/>-->
        </fieldset>
        <fieldset class="buttons">
            <g:submitButton name="create" class="save"
                            value="${message(code: 'default.button.create.label', default: 'Create')}"/>
        </fieldset>
    </g:uploadForm>
</div>
</body>
</html>
