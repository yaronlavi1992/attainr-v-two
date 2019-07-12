<!DOCTYPE html>
<!--<%@ page import="attainrvtwo.RoleOf" contentType="text/html;charset=UTF-8" %>-->
<html>
<head>
    <meta name="layout" content="main"/>
    <g:set var="entityName" value="${message(code: 'user.label', default: 'User')}"/>
    <title>
        <g:message code="default.create.label" args="[entityName]"/>
    </title>
</head>
<body>
<a href="#create-user" class="skip" tabindex="-1">
    <g:message code="default.link.skip.label" default="Skip to content&hellip;"/>
</a>
<div class="nav" role="navigation">
</div>
<div id="create-user" class="content scaffold-create" role="main">
    <g:if test="${session.role == RoleOf.COMMUNITY_SECRETARY}">

        <h1>
            יצירת משתמש חדש
        </h1>
        <g:if test="${flash.message}">
            <div class="message" role="status">${flash.message}</div>
        </g:if>
        <g:hasErrors bean="${this.user}">
            <ul class="errors" role="alert">
                <g:eachError bean="${this.user}" var="error">
                    <li
                    <g:if test="${error in org.springframework.validation.FieldError}">data-field-id="${error.field}"
                    </g:if>
                    >
                    <g:message error="${error}"/>
                    </li>
                </g:eachError>
            </ul>
        </g:hasErrors>
        <g:form resource="${this.user}" method="POST">
            <fieldset class="form">
                <f:all bean="user"/>
            </fieldset>
            <fieldset class="buttons">
                <g:submitButton name="create" class="save"
                                value="${message(code: 'default.button.create.label', default: 'Create')}"/>
            </fieldset>
        </g:form>

    </g:if>
    <g:else>
        <div class="message">גישה נדחתה</div>
    </g:else>
</div>
</body>
</html>
