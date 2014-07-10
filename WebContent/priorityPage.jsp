<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="UTF-8" %>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
	<title>Priority</title>
	<link href="webResources/css/priorityPage.css" rel="stylesheet"   type="text/css" />
	<script src="webResources//js//issue.js" type="text/javascript"></script>
	
</head>
<body>


	<div class="content">
		<table>
			<tr>
				<th> <spring:message code="label.priority"/> </th>
				
			</tr>
				<c:forEach items = "${priorityList}" var = "priority" varStatus = "newStatus" >
					<tr>
						<td><a  href="JavaScript:addName('${priority.name}','${priority.id}')" >${priority.name}</a></td>
					
					</tr>
				</c:forEach>
			</table>
		</div> 
		
		
		
		 <form  name="name" method="POST"  action="<c:url value= '/PrepareDataForEditPriorityController'/>" > 
					<INPUT type=hidden name="setName" value="">
					<INPUT type=hidden name="setId" value="">
		</form>
		<div class="userPage">

		<a href="<c:url value='/DispatcherController'/>"><spring:message
				code="label.userPage" /> </a>
	</div>
</body>
</html>