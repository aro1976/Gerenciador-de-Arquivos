<%@page contentType="text/html;charset=UTF-8" %>
<%@page pageEncoding="UTF-8" %>
<%@ page session="false" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

<html>
    <head>
        <META http-equiv="Content-Type" content="text/html;charset=UTF-8">
        <title>Gerenciador de Arquivos - Carregar Arquivo</title>
    </head>
    <body>
    	<h2>Gerenciador de Arquivos</h2>
    	<a href="arquivos">Consulta</a> | <a href="">Carregar Arquivo</a> | <a href="arquivos/1">Listar Todos</a>
    	<hr>
        <form:form modelAttribute="arquivoConteudo" method="post" enctype="multipart/form-data">
            <fieldset>
                <legend>Carregar Arquivo</legend>
                <table>
	                <tr>
	                    <td><form:label for="arquivo" path="arquivo">File</form:label></td>
	                    <td><form:input path="arquivo" type="file" size="100"/></td>
	                </tr>
	
	                <tr>
	                    <td colspan="2"><input type="submit" /></td>
	                </tr>
				</table>
            </fieldset>
        </form:form>
        
    </body>
</html>