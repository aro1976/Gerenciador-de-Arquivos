<%@page contentType="text/html;charset=UTF-8" %>
<%@page pageEncoding="UTF-8" %>
<%@ page session="false" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt"  uri="http://java.sun.com/jsp/jstl/fmt"  %>

<html>
	<fmt:setBundle basename="com.javahero.fileshare.Messages" var="bundle"/>
    <head>
        <META http-equiv="Content-Type" content="text/html;charset=UTF-8">
        <title><fmt:message key="aplicacao.nome" bundle="${bundle}"/> - <fmt:message key="arquivo.acao.editar" bundle="${bundle}"/></title>
    </head>
    <body>
    	<h2><fmt:message key="aplicacao.nome" bundle="${bundle}"/></h2>
	    <a href="consultar"><fmt:message key="arquivo.acao.consultar" bundle="${bundle}"/></a> | 
    	<sec:authorize url="/arquivos/novo"><a href="novo"><fmt:message key="arquivo.acao.carregar" bundle="${bundle}"/></a> |</sec:authorize>
    	<a href="listar/1"><fmt:message key="arquivo.acao.listar" bundle="${bundle}"/></a>
    	<hr>
        <form:form modelAttribute="arquivo" method="put" >
            <fieldset>
                <legend><fmt:message key="arquivo.campo.metadados" bundle="${bundle}"/></legend>
                <table>
                <tr>
					<td><form:label for="nomeOriginal" path="nomeOriginal"><fmt:message key="arquivo.campo.nomeOriginal" bundle="${bundle}"/></form:label></td>
                    <td><form:input path="nomeOriginal" size="100"/></td>
                </tr>
                
                <tr>
					<td><form:label for="notas" path="notas"><fmt:message key="arquivo.campo.notas" bundle="${bundle}"/></form:label></td>
                    <td><form:input path="notas" size="100"/></td>
                </tr>
              
              	<sec:authorize url="/arquivos/**" method="PUT">
                <tr>
                    <td colspan="2"><input type="submit" value="<fmt:message key="arquivo.acao.editar" bundle="${bundle}"/>"/></td>
                </tr>
                </sec:authorize>
                
				</table>
            </fieldset>
        </form:form>
        
        <sec:authorize url="/arquivos/**" method="DELETE">
	        <form:form method="delete" action="${arquivo.id}">
	        	<input type="submit" value="<fmt:message key="arquivo.acao.excluir" bundle="${bundle}"/>"/>
	        </form:form>
        </sec:authorize>
        
        <sec:authorize url="/arquivos/**/descarregar" method="GET">
        <form:form method="get" action="${arquivo.id}/descarregar">
        	<input type="submit" value="<fmt:message key="arquivo.acao.descarregar" bundle="${bundle}"/>"/>
        </form:form>
        </sec:authorize>
    </body>
</html>