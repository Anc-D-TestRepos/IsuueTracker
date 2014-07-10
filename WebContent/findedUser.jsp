<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="UTF-8" %>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<link href="webResources/css/findedUser.css" rel="stylesheet"   type="text/css" />
<title>Finded user</title>
</head>
<body>



	<div class="content">
	
		<c:if test="${not empty findedUser}">	
				
					<table>
						<tr>
							<th><spring:message code="label.email"/></th>
							<th><spring:message code="label.firstName"/></th>
							<th><spring:message code="label.lastName"/> </th>
							<th><spring:message code="label.role"/></th>
					
						</tr>
									
					
							<tr> 
								
								<td>${findedUser.email}</td>
								<td>${findedUser.firstName} </td>
								<td>${findedUser.lastName}</td>
								<td>${findedUser.role}</td>
							</tr>
					
					</table>
				
			</c:if>
			<span class="user">
				<c:if test="${ empty findedUser}">	
					<p>	<spring:message code="label.noFndUser"/></p>
				</c:if>
			</span>
	
		<div class="back">
			<a  href="<c:url value='/DispatcherController'/>"><spring:message code="label.backKey"/></a>
		</div>
		</div>
</body>
</html>