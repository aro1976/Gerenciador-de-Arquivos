<%@page contentType="text/html;charset=UTF-8" %>
<%@page pageEncoding="UTF-8" %>
<%@ page session="false" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="fmt"  uri="http://java.sun.com/jsp/jstl/fmt"  %>

<html>
	<fmt:setBundle basename="com.javahero.fileshare.Messages" var="bundle"/>
    <head>
        <META http-equiv="Content-Type" content="text/html;charset=UTF-8">
        <title><fmt:message key="aplicacao.nome" bundle="${bundle}"/> - <fmt:message key="arquivo.acao.carregar" bundle="${bundle}"/></title>
    </head>
    <body>
    	<h2><fmt:message key="aplicacao.nome" bundle="${bundle}"/></h2>
    	<a href="arquivos"><fmt:message key="arquivo.acao.consultar" bundle="${bundle}"/></a> | 
    	<a href=""><fmt:message key="arquivo.acao.carregar" bundle="${bundle}"/></a> | 
    	<a href="arquivos/1"><fmt:message key="arquivo.acao.listar" bundle="${bundle}"/></a>
    	<hr>
        <form:form modelAttribute="arquivoConteudo" method="post" enctype="multipart/form-data">
            <fieldset>
                <legend><fmt:message key="arquivo.acao.carregar" bundle="${bundle}"/></legend>
                <table>
	                <tr>
	                    <td><form:label for="arquivo" path="arquivo"><fmt:message key="arquivo.campo.arquivo" bundle="${bundle}"/></form:label></td>
	                    <td><form:input path="arquivo" type="file" size="100"/></td>
	                </tr>
	
	                <tr>
	                    <td colspan="2"><input type="submit" value="<fmt:message key="arquivo.acao.carregar" bundle="${bundle}"/>"/></td>
	                </tr>
				</table>
            </fieldset>
        </form:form>
        
    </body>
</html>