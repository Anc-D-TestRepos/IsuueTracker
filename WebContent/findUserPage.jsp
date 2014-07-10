<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="UTF-8" %>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<link type="text/css" href="webResources/css/findUserPage.css" rel="stylesheet" />
<title>Find user</title>
</head>
<body>

<div class="title"><spring:message code="label.enterMail"/> </div>
		<div class="inputData"> 
			
			<form name="findUser"  method="POST" action = "<c:url value='/UserSearcherController'/>">
				<div class="email">
					<span class="emailnput">
						<input type="text" name="userEmail"  maxlength="50" value = "${activeEmail}" >
					</span>
					<span class="emailDescr"> <spring:message code="label.emailAddress"/>
					</span>
				</div>
				
				<c:remove var = "activeEmail" />
				
				
				
				
				<div class="button">
					<input type="submit" value="   <spring:message code="label.findKey"/>   ">
				</div>
			</form>
			
		</div>
		<div class="back">
			<a  href="<c:url value='/DispatcherController'/>"><spring:message code="label.backKey"/></a>
		</div>

</body>
</html>