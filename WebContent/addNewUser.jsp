<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="UTF-8" %>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="org.training.issueTracker.service.constants.RoleConst" %>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>

<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title><spring:message code="label.addUser"/></title>
 			<link type="text/css" href="webResources/css/addNewUser.css" rel="stylesheet" />
			
</head>
<body>
			
		<div class="inputData"> 
			<div class="description"><spring:message code="label.newUserData"/></div>
					
			<form method="POST" action = "<c:url value='/NewUserController'/>">
				<div class="firstName"><span class="firstInput"><input type="text" name="first_name" value="${choisenFirstName}" maxlength="40"></span><span class="firstDescr"> <spring:message code="label.firstName"/></span></div>
				<c:remove var = "choisenFirstName" />
				<div class="lastName"><span class="lastInput"><input type="text" name="last_name" value="${choisenLastName}" maxlength="40"></span><span class="lastDescr"><spring:message code="label.lastName"/></span></div>
				<c:remove var = "choisenLastName" />
				<div class="email"><span class="emailInput"><input type="text" name="email" value="${choisenEmail}" maxlength="40"></span><span class="emailDescr"> <spring:message code="label.emailAddress"/></span></div>
				<c:remove var = "choisenEmail" />
					
			
			
				<div class="role">	
					<select name="role">
						<c:if test="${choisenRole == 'admin'}"><option value="<%= RoleConst.ADMIN.toString()%>" selected><%= RoleConst.ADMIN.toString()%></option></c:if>
						<c:if test="${choisenRole != 'admin'}"><option value="<%= RoleConst.ADMIN.toString()%>"><%= RoleConst.ADMIN.toString()%></option></c:if>
						<c:if test="${choisenRole == 'user'}"><option value="<%= RoleConst.USER.toString()%>" selected><%= RoleConst.USER.toString()%></option></c:if>
						<c:if test="${choisenRole != 'user'}"><option value="<%= RoleConst.USER.toString()%>"><%= RoleConst.USER.toString()%></option></c:if>
						<c:if test="${choisenRole == 'guest'}"><option value="<%= RoleConst.GUEST.toString()%>" selected><%= RoleConst.GUEST.toString()%></option></c:if>
				   		<c:if test="${choisenRole != 'guest'}"><option value="<%= RoleConst.GUEST.toString()%>"><%= RoleConst.GUEST.toString()%></option></c:if>
					</select>
				
				<span class="roleDescr"> <spring:message code="label.role"/></span>
				<c:remove var = "choisenRole" />
				</div>
				
			
					
				<div class="newPass"><span class="newInput"><input type="password" name="new_password" maxlength="35"></span><span class="newDescr"> <spring:message code="label.newPassword"/></span></div>
				<div class="confirmPass"><span class="confirmInput"><input type="password" name="confirm_password" maxlength="35"></span><span class="confirmDescr"><spring:message code="label.passConfir"/> </span></div>
							
		
				<div class="button"><input type="submit" value="   <spring:message code="label.addKey"/>    "></div>
			</form>
			
		</div>
		<div class="userPage">
				
				<a  href="<c:url value='/DispatcherController'/>"><spring:message code="label.userPage"/> </a>
			</div>
</body>
</html>