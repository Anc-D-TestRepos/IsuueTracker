<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="UTF-8" %>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
    <%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
		<link type="text/css" href="webResources/css/DAOErrPage.css" rel="stylesheet" />
		<title>DAO Error</title>
	</head>
	<body>
		<div class="text"> <spring:message code="label.err"/></div>
		<div class="cause"> <spring:message code="label.cause"/> &rarr; <span><spring:message code="label.${cause}"/></span></div>
		
		<c:remove var = "cause" />
		
		<a href="<c:url value='/DispatcherController'/>"> <spring:message code="label.tryAgain"/> </a>
	</body>
</html>