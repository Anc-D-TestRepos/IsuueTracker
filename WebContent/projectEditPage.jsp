<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="UTF-8" %>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
		<link type="text/css" href="webResources/css/projectEditPage.css" rel="stylesheet" />
		<title>edit project</title>
	</head>
<body>

<div class="title"><spring:message code="label.enterNewData"/> </div>
		<div class="inputData"> 
			
			<form name="addProject"  method="POST" action = "<c:url value='/ProjectEditController'/>">
				<div class="name">
					<span class="nameInput">
						<c:if test="${empty  activeName}">
								<input type="text" name="name"  maxlength="50" value = " ${project.name}" >
							
						</c:if>
						<c:if test="${not empty  activeName}">
								<input type="text" name="name"  maxlength="50" value = " ${activeName}" >
							
						</c:if>
					</span>
					<span class="nameDescr"> <spring:message code="label.name"/>
					</span>
				</div>
				
				<c:remove var = "activeName" />
				
				<div class="description">
					<span class="descriptionDInput">
					
					<c:if test="${empty  activeDescription}">
								<textarea name="description" cols="29" rows="5"  >${project.description} </textarea>
					
						</c:if>
						<c:if test="${not empty  activeDescription}">
								<textarea name="description" cols="29" rows="5"  >${activeDescription} </textarea>
					
						</c:if>
					
					
						</span>
					<span class="descriptionDescr"> <spring:message code="label.descr"/> 
					</span>
				</div>
				
				<c:remove var = "activeDescription" />
				
				
					
				<div class="build">
					<select name="build" onchange="newBuild.value='';newBuild.disabled=true">
						<option value="" ></option>
						<c:forEach items = "${buildList}" var = "buildName" varStatus = "status" >		
							<option value="${buildName.name}">${buildName.name}</option>
						</c:forEach>
						
					</select>
					<span class="buildDescr"> <spring:message code="label.builds"/></span>
				</div> 
			
				
				
				<div class="newBuild">
					<input type="text" name="newBuild"  maxlength="50" value = " ${activeNewBuild}" >
					<span class="newBuildDescr"> <spring:message code="label.addNewBuild"/></span>
				</div> 
									
				<c:remove var = "activeNewBuild" />
				
				
				
						
				<div class="manager">
				<c:if test="${empty activeManager}">
				
					<input type="text" name="manager"  maxlength="50" value = " ${project.manager}" >
				</c:if>
				
				<c:if test="${not empty activeManager}">
					<input type="text" name="manager"  maxlength="50" value = " ${activeManager}" >
				</c:if>
					
					<span class="managerDescr"> <spring:message code="label.manager"/></span>
				</div> 
				
				<c:remove var = "activeManager" />
				
				
				<div class="button">
					<input type="submit" value="   <spring:message code="label.editKey"/>   ">
				</div>
				
				<c:set var="projId" scope="session" >${project.id}</c:set>
				<input type=hidden name="projId" value="${project.id}">
			</form>
		
		</div>
		
		<div class="back">
			<a  href="<c:url value='/DispatcherController'/>"><spring:message code="label.backKey"/></a>
		</div>
		
</body>
</html>