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
        <form:form modelAttribute="fileMetadata" method="get">
            <fieldset>
                <legend>Query By Process</legend>
                <table>
				<tr>
					<td><form:label for="process" path="process">Process</form:label></td>
                    <td><form:input path="process"/></td>
                </tr>

                <tr>
                    <td colspan="2"><input type="submit" /></td>
                </tr>
				</table>
            </fieldset>
            
            <fieldset>
                <legend>Query By Document</legend>
                <table>                
                <tr>
                    <td><form:label for="documentType" path="documentType">Type</form:label></td>
                    <td>
                    	<form:select path="documentType">
                    		<form:option value="">--Not Selected--</form:option>
                    		<form:options />
                    	</form:select>
                    </td>
                </tr>
                
                <tr>
                    <td><form:label for="document" path="document">Document</form:label></td>
                    <td><form:input path="document"/></td>
                </tr>

                <tr>
                    <td colspan="2"><input type="submit" /></td>
                </tr>
				</table>
            </fieldset>
        </form:form>
        
    </body>
</html>