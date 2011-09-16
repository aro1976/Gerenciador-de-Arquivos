<%@page contentType="text/html;charset=UTF-8" %>
<%@page pageEncoding="UTF-8" %>
<%@ page session="false" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@ taglib prefix="fmt"  uri="http://java.sun.com/jsp/jstl/fmt"  %>

<html>
	<fmt:setBundle basename="com.javahero.fileshare.Messages" var="bundle"/>
    <head>
        <META http-equiv="Content-Type" content="text/html;charset=UTF-8">
        <title><fmt:message key="aplicacao.nome" bundle="${bundle}"/> - <fmt:message key="arquivo.acao.consultar" bundle="${bundle}"/></title>
    </head>
    <body>
    	<h2><fmt:message key="aplicacao.nome" bundle="${bundle}"/></h2>
 		<a href="consultar"><fmt:message key="arquivo.acao.consultar" bundle="${bundle}"/></a> | 
    	<sec:authorize url="/arquivos/novo"><a href="novo"><fmt:message key="arquivo.acao.carregar" bundle="${bundle}"/></a> |</sec:authorize>
    	<a href="listar/1"><fmt:message key="arquivo.acao.listar" bundle="${bundle}"/></a>
    	<span style="float: right;"><a href="../spring_security_login">Login</a> | <a href="../spring_security_logout">Logout</a></span>
    	<hr>
        <form:form modelAttribute="arquivoMetadados" method="get" action="listar/1">
            <fieldset>
                <legend><fmt:message key="arquivo.acao.consultar" bundle="${bundle}"/></legend>
                <table>
				<tr>
					<td><form:label for="nomeOriginal" path="nomeOriginal"><fmt:message key="arquivo.campo.nomeOriginal" bundle="${bundle}"/></form:label></td>
                    <td><form:input path="nomeOriginal"/></td>
                </tr>

                <tr>
                    <td colspan="2"><input type="submit" value="<fmt:message key="arquivo.acao.consultar" bundle="${bundle}"/>" /></td>
                </tr>
				</table>
            </fieldset>
        </form:form>  
        
    </body>
</html>