<!DOCTYPE html>
<html>
<head>
    <meta name="layout" content="main"/>
    <g:set var="entityName" value="${message(code: 'user.label', default: 'User')}"/>
    <title>
        <g:message code="default.show.label" args="[entityName]"/>
    </title>
</head>
<body>
<g:if test="${session.role == RoleOf.COMMUNITY_SECRETARY}">
    <a href="#show-user" class="skip" tabindex="-1">
        <g:message code="default.link.skip.label" default="Skip to content&hellip;"/>
    </a>
    <div class="nav" role="navigation">
        <ul>
            <li>
                <g:link class="list" action="index">
                    רשימת משתמשים
                </g:link>
            </li>
            <li>
                <g:link class="create" action="create">
                    יצירת משתמש חדש
                </g:link>
            </li>
        </ul>
    </div>
    <div id="show-user" class="content scaffold-show" role="main">
        <h1>
            פרטי משתמש
        </h1>
        <g:if test="${flash.message}">
            <div class="message" role="status">${flash.message}</div>
        </g:if>
        <f:display bean="user"/>
        <g:form resource="${this.user}" method="DELETE">
            <fieldset class="buttons">
                <g:link class="edit" action="edit" resource="${this.user}">
                    <g:message code="default.button.edit.label" default="Edit"/>
                </g:link>
                <input class="delete" type="submit"
                       value="${message(code: 'default.button.delete.label', default: 'Delete')}"
                       onclick="return confirm('${message(code: 'default.button.delete.confirm.message', default: 'Are you sure?')}');"/>
            </fieldset>
        </g:form>
    </div>
</g:if>
<g:else>
    <div class="message">גישה נדחתה</div>
</g:else>
</body>
</html>
