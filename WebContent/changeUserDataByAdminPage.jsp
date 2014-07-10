<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="UTF-8" %>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="org.training.issueTracker.service.constants.RoleConst" %>

<!DOCTYPE html>
<html>
	<head>
			<meta charset="UTF-8">
			<title>change data</title>
 
			<link type="text/css" href="webResources/css/changeUserDataByAdminPage.css" rel="stylesheet" />
			<script src="webResources//js//issue.js" type="text/javascript"></script>
	</head>
<body>
		<div class="inputData"> 
			<div class="description"><spring:message code="label.newUserData"/></div>
					
			<form method="POST" name="chngData" action = "<c:url value='/ChangeUserDataByAdmin'/>">
				<div class="firstName"><span class="firstInput"><input type="text" name="first_name" value="${choisenFirstName}" maxlength="40"></span><span class="firstDescr"> <spring:message code="label.firstName"/></span></div>
				<div class="lastName"><span class="lastInput"><input type="text" name="last_name" value="${choisenLastName}" maxlength="40"></span><span class="lastDescr"> <spring:message code="label.lastName"/></span></div>
				
				
				<div class="email">
					<select name="emailId" id="select">
					<c:forEach items = "${mailList}" var = "employee" varStatus = "status" >		
				
						<c:if test="${choisenEmail == employee.email}"><option value="${employee.id}" selected>${employee.email}</option></c:if>
						<c:if test="${choisenEmail != employee.email}"><option value="${employee.id}">${employee.email}</option></c:if>
						
					</c:forEach>
												
					</select>
					<span class="emailDescr"> <spring:message code="label.emailAddress"/> </span>
					
					
				</div>
			
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
				
				</div>
			
				<input type=hidden name="email_employee" value="">
				
				<div class="button"> <input   type="button" value = " <spring:message code="label.editKey"/> "  onclick="JavaScript:chngUserData()"  >   </div>
			</form>
		</div>
	<div class="userPage">

		<a href="<c:url value='/DispatcherController'/>"><spring:message
				code="label.userPage" /> </a>
	</div>
</body>
</html>