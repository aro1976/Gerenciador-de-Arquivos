<%@page contentType="text/html;charset=UTF-8" %>
<%@page pageEncoding="UTF-8" %>
<%@ page session="false" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<html>
    <head>
        <META http-equiv="Content-Type" content="text/html;charset=UTF-8">
        <title>File Share Service</title>
    </head>
    <body>
    	<h2>File Share Service</h2>
    	<a href="consultar">Consulta</a> | <a href="carregar">Carregar Arquivo</a> | <a href="listar">Listar Todos</a>
    	<hr>
    	<table border="1">
    		<tr>
    			<th>Nome Original</th>
    			<th>Tamanho</th>
    			<th>Data Carregamento</th>
    			<th>Data Acesso</th>
    			<th>Contador de Acessos</th>
   			</tr>
    	
	        <c:forEach items="${lista}" var="arquivo">
	        	<tr>
					<td><a href="editar/${arquivo.id}">${arquivo.nomeOriginal}</a></td>
	    			<td>${arquivo.tamanho}</td>
    				<td>${arquivo.dataCarregamento}</td>
    				<td>${arquivo.dataAcesso}</td>
    				<td>${arquivo.contadorAcesso}</td>
				</tr>
			</c:forEach>
		
		</table>
    </body>
</html>