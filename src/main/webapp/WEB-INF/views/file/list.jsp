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
    	<a href="query">Query Params</a> | <a href="new">Upload New</a> | <a href="all">All Files</a>
    	<hr>
    	<table border="1">
    		<tr>
    			<th>Name</th>
    			<th>Size</th>
    			<th>Upload Date</th>
    			<th>Access Date</th>
    			<th>Access Count</th>
    			<th>Document Type</th>
    			<th>Document #</th>
    			<th>Process</th>
   			</tr>
    	
	        <c:forEach items="${fileList}" var="file">
	        	<tr>
					<td><a href="download/${file.id}">${file.fileName}</a></td>
	    			<td>${file.size}</td>
    				<td>${file.uploadDate}</td>
    				<td>${file.accessDate}</td>
    				<td>${file.accessCount}</td>
	    			<td>${file.documentType}</td>
	    			<td>${file.document}</td>
	    			<td>${file.process}</td>
				</tr>
			</c:forEach>
		
		</table>
    </body>
</html>