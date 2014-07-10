<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="UTF-8" %>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8">
		<title>${user.firstName }_${user.lastName} </title>
		<link type="text/css" href="webResources/css/autorizedUserPage.css" rel="stylesheet" />
		<script src="webResources//js//issue.js" type="text/javascript"></script>
	</head>
	<body>
	
			
	<div class="content">
	
		<div class="user">User:
			<span class="name">${user.firstName} ${user.lastName}</span>
			<span class="logout"><a href="<c:url value='/LogoutController'/>"><spring:message code="label.logout"/></a></span>
		</div>
			
		<div class="chngUserData">
			<span class="chngData"><a href="<c:url value='/changeUserDataByUserPage.jsp'/>"><spring:message code="label.chngUserData"/></a></span>
			<span class="chngPass"><a href="<c:url value='/changeUserPass.jsp'/>"><spring:message code="label.chngUserPass"/></a></span>
		</div>
		<hr color="red">
		<span style="float: right">
		    <a href="<c:url value='/DispatcherController?lang=en'/>">en</a> 
		    | 
		    <a href="<c:url value='/DispatcherController?lang=ru'/>">ru</a> 
		</span>
		
		 <c:if test="${param['lang'] !=null}">
   			 <fmt:setLocale value="${param['lang']}" scope="session" />
		</c:if>
		<div class="button">
			 <form class="createIssue" name="createIssue" method="POST"  action="<c:url value= '/PrepareDataForAddIssueController'/>" > 
				<input type="submit" value = "<spring:message code="label.submIssueKey"/>">
		     </form>
				  
			 <form  class="searchIssue" name="searchIssue" method="POST"  action="<c:url value= '/findIssuePage.jsp'/>" > 
				<input type="submit" value = "<spring:message code="label.searchKey"/>">
			 </form>
		</div>
		
			
		<c:if test="${not empty defectList}">	
			<div class="defectList">
				<table>
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
							<td class="id"><a href="JavaScript:sendId(${defect.id})">${defect.id}</a></td>
							<td><font  color ="${defect.priority.color}">${defect.priority.name}</font></td>
							<td>${defect.assignee.email}</td>
							<td>${defect.type.name} </td>
							<td>${defect.status.name}</td>
							<td>${defect.summary}</td>
						</tr>
					</c:forEach>
				</table>
			</div>
		</c:if>
			<c:if test="${ empty defectList}">	
					<p align="center"><spring:message code="label.noDefect"/></p>
			</c:if>
			<form  name="columnName" method="POST"  action="<c:url value= '/IssueFieldSorter'/>" > 
				<INPUT type=hidden name="sortColumn" value="">
			</form>
			<form  name="idNumber" method="POST"  action="<c:url value= '/PrepareDataForEditIssueController'/>" > 
				<input type=hidden name="id" value="">
			</form>
						</div>	
		<div class="footer">
			<hr color="blue">
		</div>
	</body>
</html>