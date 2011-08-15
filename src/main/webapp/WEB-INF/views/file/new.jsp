<%@page contentType="text/html;charset=UTF-8" %>
<%@page pageEncoding="UTF-8" %>
<%@ page session="false" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

<html>
    <head>
        <META http-equiv="Content-Type" content="text/html;charset=UTF-8">
        <title>New File</title>
    </head>
    <body>
        <form:form modelAttribute="fileItem" method="post" enctype="multipart/form-data">
            <fieldset>
                <legend>New File</legend>
                <table>
				<tr>
					<td><form:label for="process" path="process">Process</form:label></td>
                    <td><form:input path="process"/></td>
                </tr>
                
                <tr>
                    <td><form:label for="type" path="type">Type</form:label></td>
                    <td>
                    	<form:select path="type">
                    		<form:options/>
                    	</form:select>
                    </td>
                </tr>
                
                <tr>
                    <td><form:label for="document" path="document">Document</form:label></td>
                    <td><form:input path="document"/></td>
                </tr>
                
                <tr>
                    <td><form:label for="fileData" path="fileData">File</form:label></td>
                    <td><form:input path="fileData" type="file" /></td>
                </tr>

                <tr>
                    <td colspan="2"><input type="submit" /></td>
                </tr>
				</table>
            </fieldset>
        </form:form>
        
    </body>
</html>