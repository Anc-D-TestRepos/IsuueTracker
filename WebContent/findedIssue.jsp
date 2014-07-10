<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="UTF-8" %>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<link href="webResources/css/findedIssue.css" rel="stylesheet"   type="text/css" />
<title>Found issue</title>
</head>
<body>



	<div class="content">
	
		
				
					<c:if test="${not empty issue}">	
				<div class="defectList">
					<table>
						<tr>
							<th>Id</th>
							<th>Priority</th>
							<th>Assignee</th>
							<th>Type</th>
							<th>Status</th>
							<th>Summary</th>
						</tr>
									
						
							<tr> 
								<td>${issue.id}</td>
								<td><font  color ="${issue.priority.color}">${issue.priority.name}</font></td>
								<td>${issue.assignee.email}</td>
								<td>${issue.type.name} </td>
								<td>${issue.status.name}</td>
								<td>${issue.summary}</td>
							</tr>
						
					</table>
				</div>
			</c:if>
			<c:if test="${ empty issue}">	
				<p ><spring:message code="label.noFndDefect"/></p>
			</c:if>
						
		
	
		<div class="back">
			<a  href="<c:url value='/DispatcherController'/>"><spring:message code="label.backKey"/></a>
		</div>
		</div>
</body>
</html>