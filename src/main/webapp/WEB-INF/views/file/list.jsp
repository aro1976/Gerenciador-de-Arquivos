<%@page contentType="text/html;charset=UTF-8" %>
<%@page pageEncoding="UTF-8" %>
<%@ page session="false" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<html>
    <head>
        <META http-equiv="Content-Type" content="text/html;charset=UTF-8">
        <title>File Share</title>
    </head>
    <body>
    	<table border="1">
    		<tr>
    			<th>Name</th>
    			<th>Size</th>
    			<th>Upload Date</th>
    			<th>Document Type</th>
    			<th>Document #</th>
    			<th>Process</th>
   			</tr>
    	
	        <c:forEach items="${fileList}" var="file">
	        	<tr>
					<td><a href="http://localhost:8080/fileshare-service/app/file/${file.id}">${file.fileName}</a></td>
	    			<td>${file.size}</td>
    				<td>${file.date}</td>
	    			<td>${file.documentType}</td>
	    			<td>${file.document}</td>
	    			<td>${file.process}</td>
				</tr>
			</c:forEach>
		
		</table>
    </body>
</html>