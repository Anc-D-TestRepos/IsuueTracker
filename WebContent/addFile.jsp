<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="UTF-8" %>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>

<!DOCTYPE html>

<HTML> 
	<head> 
		<TITLE> <spring:message code="label.fileUpload"/></TITLE> 
		<meta http-equiv="Content-Type" content="text/html; charset=UTF8">
	  	<style type="text/css">
			form{ border : 1px solid; margin:auto; width :250px; height : 180px; margin-top:150px;}
		</style>
	</head>
	
	<BODY>
								
				
		<form   id = "border" name="loading"   method = post ENCTYPE="MULTIPART/FORM-DATA"  ACTION="<c:url value='/UploadController'/>" >	

			<p align="center"> <spring:message code="label.chhoseFile"/></p>
			<br>
			<p  align = "center" > <input type ="file" name = "path"  ></p>
		
			<p  align = "center" ><input type="submit" value = " <spring:message code="label.keyUpload"/>"/></p>
		</form>

		
	</BODY>
</HTML>
			