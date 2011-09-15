<%@page contentType="text/html;charset=UTF-8" %>
<%@page pageEncoding="UTF-8" %>
<%@ page session="false" %>

<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt"  uri="http://java.sun.com/jsp/jstl/fmt"  %>

<html>
    <fmt:setBundle basename="com.javahero.fileshare.Messages" var="bundle"/>
    <head>
        <META http-equiv="Content-Type" content="text/html;charset=UTF-8">
        <title><fmt:message key="aplicacao.nome" bundle="${bundle}"/> - <fmt:message key="arquivo.acao.listar" bundle="${bundle}"/></title>
    </head>
    <body>
    	<h2><fmt:message key="aplicacao.nome" bundle="${bundle}"/> - 
    	<fmt:message key="geral.navegacao.pagina" bundle="${bundle}">
    		<fmt:param value="${paginaAtual}" />
    	</fmt:message>
    	
    	</h2>
    	<a href="../consultar"><fmt:message key="arquivo.acao.consultar" bundle="${bundle}"/></a> | 
    	<sec:authorize access="hasRole('ROLE_MANAGER')"><a href="../novo"><fmt:message key="arquivo.acao.carregar" bundle="${bundle}"/></a> |</sec:authorize> 
    	<a href="1"><fmt:message key="arquivo.acao.listar" bundle="${bundle}"/></a>
    	<hr>
    	<table border="1" width="100%">
    		<tr>
    			<th><fmt:message key="arquivo.campo.nomeOriginal" bundle="${bundle}"/></th>
    			<th><fmt:message key="arquivo.campo.tamanho" bundle="${bundle}"/></th>
    			<th><fmt:message key="arquivo.campo.dataCarregamento" bundle="${bundle}"/></th>
    			<th><fmt:message key="arquivo.campo.dataAcesso" bundle="${bundle}"/></th>
    			<th><fmt:message key="arquivo.campo.contadorAcesso" bundle="${bundle}"/></th>
   			</tr>
    	
	        <c:forEach items="${arquivos.arquivoMetadadosList}" var="arquivo">
	        	<tr>
					<td><a href="../../arquivos/${arquivo.id}">${arquivo.nomeOriginal}</a></td>
	    			<td>${arquivo.tamanho}</td>
    				<td>${arquivo.dataCarregamento}</td>
    				<td>${arquivo.dataAcesso}</td>
    				<td>${arquivo.contadorAcesso}</td>
				</tr>
			</c:forEach>
		
			<tr>
				<td colspan="5" align="center">
					<c:choose>
					<c:when test="${existePaginaAnterior}"><a href="${paginaAtual-1}?${query}"><fmt:message key="geral.navegacao.anterior" bundle="${bundle}"/></a> |</c:when>	
					<c:otherwise><fmt:message key="geral.navegacao.anterior" bundle="${bundle}"/> |</c:otherwise>
					</c:choose>
					
					<c:forEach var="counter" begin="1" end="${paginaAtual}">
		        		<a href="${counter}?${query}">${counter}</a> |
					</c:forEach>
					
					<c:if test="${existePaginaPosterior}"><a href="${paginaAtual+1}?${query}">${paginaAtual+1}</a> |</c:if>
					
					<c:choose>
					<c:when test="${existePaginaPosterior}"><a href="${paginaAtual+1}?${query}"><fmt:message key="geral.navegacao.proxima" bundle="${bundle}"/></a></c:when>	
					<c:otherwise><fmt:message key="geral.navegacao.proxima" bundle="${bundle}"/></c:otherwise>
					</c:choose>
				</td>
			</tr>
		</table>
    </body>
</html>