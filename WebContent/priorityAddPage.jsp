<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="UTF-8" %>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
		<title>Add priority</title>
		<link href="webResources/css/priorityAddPage.css" rel="stylesheet"   type="text/css" />
	</head>
	<body>
		<div class="content">
		 <span class="text"><spring:message code="label.enterPriority"/></span>
		<form method="POST" action = "<c:url value='/PriorityAddingController'/>">
			<span><input type ="text" name="newStatus" value="" maxlength="50"></span>
			
			<div class="button"><input type="submit" value="   <spring:message code="label.addKey"/>   "></div>
		</form>
		<div class="userPage">
				
				<a  href="<c:url value='/DispatcherController'/>"><spring:message code="label.userPage"/> </a>
			</div>
		
		</div>
		
	</body>
</html>