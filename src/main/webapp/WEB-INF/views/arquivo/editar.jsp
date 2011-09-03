<%@page contentType="text/html;charset=UTF-8" %>
<%@page pageEncoding="UTF-8" %>
<%@ page session="false" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

<html>
    <head>
        <META http-equiv="Content-Type" content="text/html;charset=UTF-8">
        <title>Gerenciador de Arquivos - Editar</title>
    </head>
    <body>
    	<h2>Gerenciador de Arquivos</h2>
    	<a href="../../arquivos">Consulta</a> | <a href="../../arquivo">Carregar Arquivo</a> | <a href="../../arquivos/1">Listar Todos</a>
    	<hr>
        <form:form modelAttribute="arquivo" method="post" >
            <fieldset>
                <legend>Metadados</legend>
                <table>
                <tr>
					<td><form:label for="nomeOriginal" path="nomeOriginal">Nome Original</form:label></td>
                    <td><form:input path="nomeOriginal" size="100"/></td>
                </tr>
                
                <tr>
					<td><form:label for="notas" path="notas">Notas</form:label></td>
                    <td><form:input path="notas" size="100"/></td>
                </tr>
              
                <tr>
                    <td colspan="2"><input type="submit" value="Atualizar"/></td>
                </tr>
				</table>
            </fieldset>
        </form:form>
        
        <form:form method="delete" action="../${arquivo.id}">
        	<input type="submit" value="Excluir Arquivo"/>
        </form:form>
        
        <form:form method="get" action="../${arquivo.id}">
        	<input type="submit" value="Descarregar Arquivo"/>
        </form:form>
    </body>
</html>