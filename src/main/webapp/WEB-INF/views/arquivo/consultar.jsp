<%@page contentType="text/html;charset=UTF-8" %>
<%@page pageEncoding="UTF-8" %>
<%@ page session="false" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

<html>
    <head>
        <META http-equiv="Content-Type" content="text/html;charset=UTF-8">
        <title>Query Shared Files</title>
    </head>
    <body>
    	<h2>File Share Service</h2>
    	<a href="consultar">Consulta</a> | <a href="carregar">Carregar Arquivo</a> | <a href="listar">Listar Todos</a>
    	<hr>
        <form:form modelAttribute="arquivoMetadados" method="get">
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