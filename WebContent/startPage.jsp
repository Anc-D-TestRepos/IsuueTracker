<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="UTF-8" %>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>  
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<!DOCTYPE html>
<html>
	<head>
			<meta charset="UTF-8">
			 <title>Issue Tracker</title>
			<link type="text/css" href="webResources/css/startPage.css" rel="stylesheet" />
			<script src="webResources//js//issue.js" type="text/javascript"></script>
	</head>
	<body>
		<div class="role">${role}</div>
		<div class="description"><spring:message code="label.loginMessage"/></div>
		<div class="inputData"> 
		
			<%-- <form method="POST" action = "<c:url value='/Authentification'/>">
				<div class="email"> <input type="text" name="email">  <span class="email"> <spring:message code="label.email"/></span></div>
				<div class="pass">  <input type="password" name="pass"><span class="pass"> <spring:message code="label.pass"/></span></div>
				<div class="button"><input type="submit" value="   <spring:message code="label.keyEnter"/>   "></div>
			</form> --%>
			
			<form:form method="post" commandName="employee" action = "Authentification">
			<div class="email">
					<form:input path="email" />		
					<span class="email"> <spring:message code="label.email"/></span><br>
					<span class = "error"><form:errors path="email" /></span>
			</div>
			<div class="pass">
				  <form:password path="password"/>
				  <span class="pass"> <spring:message code="label.pass"/></span><br>
				<span class = "error">  <form:errors path="password" /></span>
			</div>
			<div class="button">
				<input type="submit" value="   <spring:message code="label.keyEnter"/>   "/>
			</div>
			 <form:hidden path="role" value = "111"/>
			  <form:hidden path="firstName" value = "111"/>
			   <form:hidden path="lastName" value = "111"/>
			</form:form>
			
		</div>
		<hr color="red">
		<span style="float: right">
    <a href="?lang=en">en</a> 
    | 
    <a href="?lang=ru">ru</a>
    <c:if test="${param['lang'] !=null}">
   		 <fmt:setLocale value="${param['lang']}" scope="session" />
	</c:if>
    
</span>
				
		<c:if test="${not empty defectList}">	
			<div class="defectList">
				<table >
					<tr>
						<th><a  href="JavaScript:sendColumnName('id')"       >Id</a></th>
						<th><a  href="JavaScript:sendColumnName('priority')" >Priority</a></th>
						<th><a  href="JavaScript:sendColumnName('assignee')" >Assignee</a></th>
						<th><a  href="JavaScript:sendColumnName('type')"     >Type</a></th>
						<th><a  href="JavaScript:sendColumnName('status')"   >Status</a></th>
						<th><a  href="JavaScript:sendColumnName('summary')"  >Summary</a></th>
					</tr>
								
					<c:forEach items = "${defectList}" var = "defect" varStatus = "status" >
						<tr>
							<td class="id">${defect.id}</td>
							<td><font  color ="${defect.priority.color}">${defect.priority.name}</font></td>
							<td>${defect.assignee.email}</td>
							<td>${defect.type.name}</td>
							<td>${defect.status.name}</td>
							<td>${defect.summary}</td>
						</tr>
					</c:forEach>	
				</table>
			</div>
		</c:if>
		<c:if test="${empty defectList}">	
			<p align="center">Application doesn't have any defects </p>
		</c:if>
		 <form  name="columnName" method="POST"  action="<c:url value= 'IssueFieldSorter'/>" > 
					<INPUT type=hidden name="sortColumn" value="">
		</form>
	</body>
</html>