<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="UTF-8" %>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
 
<!DOCTYPE html>
<html>
	<head>
			<meta charset="UTF-8">
			<title>change data</title>
 
			<link type="text/css" href="webResources/css/changeUserDataByUserPage.css" rel="stylesheet" />
</head>
<body>

		<div class="inputData"> 
		<div class="description"><spring:message code="label.newUserData"/></div>
			<form method="POST" action = "<c:url value='/ChangeUserDataByUser'/>">
				
				<div class="firstName"><span class="firstInput"><input type="text" name="first_name" value="${choisenFirstName}" maxlength="40"></span><span class="firstDescr"> <spring:message code="label.firstName"/></span></div>
				<div class="lastName"><span class="lastInput"><input type="text" name="last_name" value="${choisenLastName}" maxlength="40"></span><span class="lastDescr"> <spring:message code="label.lastName"/></span></div>
				<div class="email"><span class="email"><input type="text" name="email" value="${choisenEmail}" maxlength="20"></span><span class="emailDescr"><spring:message code="label.emailAddress"/> </span></div>
					
					
				
		
				<div class="button"><input type="submit" value="<spring:message code="label.editKey"/>"></div>
			</form>
		</div>
		<div class="userPage">
				
				<a  href="<c:url value='/DispatcherController'/>"><spring:message code="label.userPage"/> </a>
			</div>
</body>
</html>
			
