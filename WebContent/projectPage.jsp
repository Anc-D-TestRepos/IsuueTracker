<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="UTF-8" %>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@taglib prefix="shortOut" uri="/WEB-INF/shortOut.tld"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
		<link type="text/css" href="webResources/css/projectPage.css" rel="stylesheet" />
		<script src="webResources//js//issue.js" type="text/javascript"></script>
		<title>Projects</title>
	</head>
	<body>
	
				<c:if test="${not empty projectList}">	
					<div class="projectList">
						<table>
							<tr>
								<th><spring:message code="label.name"/></th>
								<th><spring:message code="label.manager"/></th>
								<th><spring:message code="label.descr"/></th>
							</tr>
										
							<c:forEach items = "${projectList}" var = "project" varStatus = "status" >
								<tr> 
									<td class="name"><a href="JavaScript:editProject(${project.id})">${project.name}</a></td>
									<td>${project.manager}</td>
									<td><shortOut:txtHandler value="${project.description}"/> </td>
									
								
								</tr>
							</c:forEach>
						</table>
					</div>
				</c:if>
				<c:if test="${ empty projectList}">	
					<p><spring:message code="label.noFndProj"/></p>
				</c:if>
				
				<div class="navigate">
					<c:if test="${not empty projectPageNumber}">	
						<c:if test="${currentPage > 1}">	
							<span class="back"><a  href="JavaScript:backProjectPage(${currentPage})"><spring:message code="label.backKey"/></a></span>
						</c:if>
						
						<c:forEach items = "${projectPageNumber}" var = "pageNumber" varStatus = "status" >
							<a  href="JavaScript:changeProjectPage(${pageNumber})">
								 <c:if test="${currentPage == pageNumber}"><span class="curentPage"></c:if>	
								 	${pageNumber}
								 <c:if test="${currentPage == pageNumber}"></span></c:if>
								 </a>
						
						</c:forEach>
						
						<c:if test="${(fn:length(projectPageNumber)> 1)and (currentPage < (fn:length(projectPageNumber)))}">
							
							<span class="next"><a  href="JavaScript:nextProjectPage(${currentPage})"><spring:message code="label.nextKey"/></a></span>
						</c:if>
					</c:if>
				</div>
				
				<div class="userPage">
				
				<a  href="<c:url value='/DispatcherController'/>"><spring:message code="label.userPage"/> </a>
			</div>
		<form  name="idProject" method="POST"  action="<c:url value= '/PrepareDataForEditProjectController'/>" > 
			<input type=hidden name="projectId" value="">
		</form>
		<form  name="numberPage" method="POST"  action="<c:url value= '/ProjectPageController'/>" > 
			<input type=hidden name="page" value="">
		</form>
		<form  name="nextPage" method="POST"  action="<c:url value= '/ProjectPageController'/>" > 
			<input type=hidden name="page" value="">
		</form>
		<form  name="backPage" method="POST"  action="<c:url value= '/ProjectPageController'/>" > 
			<input type=hidden name="page" value="">
		</form>
	</body>
</html>