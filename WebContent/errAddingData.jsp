<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="UTF-8" %>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
		<link type="text/css" href="webResources/css/errAddingData.css" rel="stylesheet" />
		<title>data adding</title>
	</head>
	<body>
		<div class="text"><spring:message code="label.notAded"/> </div>
		<div class="cause"> <spring:message code="label.cause"/> &rarr; <span>${cause}</span></div>
		<c:if test="${not empty badField}">
			<c:forEach items = "${badField}" var = "field" varStatus = "status" >
					<div class="field"> ${field}; </div>
			</c:forEach>
			
		</c:if>
		<c:remove var = "badField" />
		<a href="<c:url value='/defectAddingPage.jsp'/>"> <spring:message code="label.backKey"/> </a>
	</body>
</html>