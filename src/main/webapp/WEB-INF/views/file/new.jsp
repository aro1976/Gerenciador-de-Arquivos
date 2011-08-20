<%@page contentType="text/html;charset=UTF-8" %>
<%@page pageEncoding="UTF-8" %>
<%@ page session="false" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

<html>
    <head>
        <META http-equiv="Content-Type" content="text/html;charset=UTF-8">
        <title>File Share Service - Upload New</title>
    </head>
    <body>
    	<h2>File Share Service</h2>
    	<a href="query">Query Params</a> | <a href="">Upload New</a> | <a href="all">All Files</a>
    	<hr>
        <form:form modelAttribute="fileItem" method="post" enctype="multipart/form-data">
            <fieldset>
                <legend>New File</legend>
                <table>
				<tr>
					<td><form:label for="process" path="process">Process</form:label></td>
                    <td><form:input path="process" size="100"/></td>
                </tr>
                
                <tr>
                    <td><form:label for="documentType" path="documentType">Type</form:label></td>
                    <td>
                    	<form:select path="documentType">
                    		<form:options/>
                    	</form:select>
                    </td>
                </tr>
                
                <tr>
                    <td><form:label for="document" path="document">Document</form:label></td>
                    <td><form:input path="document" size="50"/></td>
                </tr>
                
                <tr>
                    <td><form:label for="fileData" path="fileData">File</form:label></td>
                    <td><form:input path="fileData" type="file" size="100"/></td>
                </tr>

                <tr>
                    <td colspan="2"><input type="submit" /></td>
                </tr>
				</table>
            </fieldset>
        </form:form>
        
    </body>
</html>