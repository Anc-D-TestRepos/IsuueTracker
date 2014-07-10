<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="UTF-8" %>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<!DOCTYPE html>
<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
		<title>Error</title>
		<link type="text/css" href="webResources/css/errorLoginPage.css" rel="stylesheet" />
	</head>
	
	<body>
		<hr color="red">
		<div class="message">
			<spring:message code="label.wrongLogin"/>
		</div>
		<div class="link">
			<a href="StartPage"><spring:message code="label.tryAgain"/></a>
		</div>
	</body>
</html>