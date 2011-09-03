<%@page contentType="text/html;charset=UTF-8" %>
<%@page pageEncoding="UTF-8" %>
<%@ page session="false" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<html>
    <head>
        <META http-equiv="Content-Type" content="text/html;charset=UTF-8">
        <title>Gerenciador de Arquivos - Listar</title>
    </head>
    <body>
    	<h2>Gerenciador de Arquivos - Página ${paginaAtual}</h2>
    	<a href="..">Consulta</a> | <a href="../arquivo">Carregar Arquivo</a> | <a href="1">Listar Todos</a>
    	<hr>
    	<table border="1" width="100%">
    		<tr>
    			<th>Nome Original</th>
    			<th>Tamanho</th>
    			<th>Data Carregamento</th>
    			<th>Data Acesso</th>
    			<th>Contador de Acessos</th>
   			</tr>
    	
	        <c:forEach items="${lista}" var="arquivo">
	        	<tr>
					<td><a href="../arquivo/${arquivo.id}/metadados">${arquivo.nomeOriginal}</a></td>
	    			<td>${arquivo.tamanho}</td>
    				<td>${arquivo.dataCarregamento}</td>
    				<td>${arquivo.dataAcesso}</td>
    				<td>${arquivo.contadorAcesso}</td>
				</tr>
			</c:forEach>
		
			<tr>
				<td colspan="5" align="center">
					<c:choose>
					<c:when test="${existePaginaAnterior}"><a href="${paginaAtual-1}?${query}">Anterior</a> |</c:when>	
					<c:otherwise>Anterior |</c:otherwise>
					</c:choose>
					
					<c:forEach var="counter" begin="1" end="${paginaAtual}">
		        		<a href="${counter}?${query}">${counter}</a> |
					</c:forEach>
					
					<c:if test="${existePaginaPosterior}"><a href="${paginaAtual+1}?${query}">${paginaAtual+1}</a> |</c:if>
					
					<c:choose>
					<c:when test="${existePaginaPosterior}"><a href="${paginaAtual+1}?${query}">Próxima</a></c:when>	
					<c:otherwise>Próxima</c:otherwise>
					</c:choose>
				</td>
			</tr>
		</table>
    </body>
</html>