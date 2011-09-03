<%@page contentType="text/html;charset=UTF-8" %>
<%@page pageEncoding="UTF-8" %>
<%@ page session="false" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

<html>
    <head>
        <META http-equiv="Content-Type" content="text/html;charset=UTF-8">
        <title>Gerenciador de Arquivos - Consultar</title>
    </head>
    <body>
    	<h2>Gerenciador de Arquivos</h2>
    	<a href="arquivos">Consulta</a> | <a href="arquivo">Carregar Arquivo</a> | <a href="arquivos/1">Listar Todos</a>
    	<hr>
        <form:form modelAttribute="arquivoMetadados" method="get" action="arquivos/1">
            <fieldset>
                <legend>Busca por Nome</legend>
                <table>
				<tr>
					<td><form:label for="nomeOriginal" path="nomeOriginal">Nome Original</form:label></td>
                    <td><form:input path="nomeOriginal"/></td>
                </tr>

                <tr>
                    <td colspan="2"><input type="submit" /></td>
                </tr>
				</table>
            </fieldset>
        </form:form>  
        
    </body>
</html>