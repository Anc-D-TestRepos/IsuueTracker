<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="UTF-8" %>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
    <%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>change password</title>
<link type="text/css" href="webResources/css/changeUserPass.css"
	rel="stylesheet" />
</head>
<body>
	<div class="description">
		<spring:message code="label.enterNewPass" />
	</div>
	<div class="inputData">

		<form method="POST"
			action="<c:url value='/ChangeUserPassController'/>">
			<div class="newPass">
				<span class="newInput"><input type="password" name="newPass"
					maxlength="35"></span><span class="newDescr"> <spring:message
						code="label.newPassword" /></span>
			</div>
			<div class="confirmPass">
				<span class="confirmInput"><input type="password"
					name="confirmPass" maxlength="35"></span><span
					class="confirmDescr"><spring:message code="label.passConfir" /></span>
			</div>
			<div class="button">
				<input type="submit" value="<spring:message code="label.editKey"/>">
			</div>
		</form>
	</div>
	<div class="userPage">

		<a href="<c:url value='/DispatcherController'/>"><spring:message
				code="label.userPage" /> </a>
	</div>
</html>