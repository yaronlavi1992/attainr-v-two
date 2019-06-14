<!DOCTYPE html>
<html>
    <head>
        <meta name="layout" content="main" />
        <g:set var="entityName" value="${message(code: 'myFile.label', default: 'MyFile')}" />
        <title><g:message code="default.list.label" args="[entityName]" /></title>
    </head>
    <body>
        <a href="#list-myFile" class="skip" tabindex="-1"><g:message code="default.link.skip.label" default="Skip to content&hellip;"/></a>
        <div class="nav" role="navigation">
            <ul>
                <li><a class="home" href="${createLink(uri: '/')}"><g:message code="default.home.label"/></a></li>
                <li><g:link class="create" action="create"><g:message code="default.new.label" args="[entityName]" /></g:link></li>
            </ul>
        </div>
        <div id="list-myFile" class="content scaffold-list" role="main">
            <h1><g:message code="default.list.label" args="[entityName]" /></h1>
            <g:if test="${flash.message}">
                <div class="message" role="status">${flash.message}</div>
            </g:if>

            <table class='display'>
                <tr>
                    <th>File Name</th>
                    <th>Summary</th>
                </tr>

                <g:each in="${myFileList}">
                    <tr>
                        <td><g:link action="show" id="${it.id}">${it.fileName}</g:link></td>
                    </tr>
                </g:each>
            </table>

            <div class="pagination">
                <g:paginate total="${myFileCount ?: 0}" />
            </div>
        </div>
    </body>
</html>