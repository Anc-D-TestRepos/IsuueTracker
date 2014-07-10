<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="UTF-8" %>
        <%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
            <%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<link type="text/css" href="webResources/css/defectAddingPage.css" rel="stylesheet" />
</head>
	<body>

	<div class="title"><spring:message code="label.enterNewDef"/></div>
		<div class="inputData"> 
			
			<form name="addIssue"  method="POST" action = "<c:url value='/DefectAddingController'/>">
				<div class="summary">
					<span class="summaryInput">
						<input type="text" name="summary"  maxlength="50" value = "${activeSummary}" >
					</span>
					<span class="summaryDescr"> <spring:message code="label.summ"/>
					</span>
				</div>
				
				<c:remove var = "activeSummary" />
				
				<div class="description">
					<span class="descriptionDInput">
						<textarea name="description" cols="29" rows="5"  >${activeDescription}</textarea>
						</span>
					<span class="descriptionDescr"> <spring:message code="label.descr"/> 
					</span>
				</div>
				
				<c:remove var = "activeDescription" />
				
				<div class="status">
					<select name="status"  >
						<c:forEach items = "${statusList}" var = "status" varStatus = "newstatus" >	
							<c:if test="${activeStatus == 'New'}"><option  value="${status.id}" selected >New</option></c:if>
							<c:if test="${status.name == 'New'}"><option  value="${status.id}">New</option></c:if>
							<c:if test="${activeStatus == 'Assigned'}"><option  value="${status.id}" selected >Assigned</option></c:if>
							<c:if test="${status.name  == 'Assigned'}"><option  value="${status.id}">Assigned</option></c:if>
						</c:forEach>
					</select>
					<span class="descriptionStatus"> <spring:message code="label.status"/>  </span>
				</div>
					<c:remove var = "activeStatus" />
				
				<input id="id_2" type="hidden" name="text" value="Assigned">
				<div class="type">
					<select name="type">
						<c:forEach items = "${typeList}" var = "type" varStatus = "status" >	
							<c:if test="${activeType == type.name}"><option value="${type.name}" selected>${type.name}</option></c:if>
							<c:if test="${activeType != type.name}"><option value="${type.name}">${type.name}</option></c:if>
						
						</c:forEach>
					</select>
					<span class="descriptionType"><spring:message code="label.type"/> </span>
				</div>
				
				<c:remove var = "activeType" />
				
				
				<div class="priority">
					<select name="priority">
						<c:forEach items = "${priorityList}" var = "priority" varStatus = "status" >	
							<c:if test="${activePriority == priority.name}"><option value="${priority.name}" selected>${priority.name}</option></c:if>
							<c:if test="${activePriority != priority.name}"><option value="${priority.name}">${priority.name}</option></c:if>
						</c:forEach>	
					</select>
					<span class="descriptionPriority"> <spring:message code="label.priority"/> </span>
				</div>
							
				<c:remove var = "activePriority" />
				<div class="project">
					<select name="project">
						<c:forEach items = "${projectName}" var = "projName" varStatus = "status" >		
							<c:if test="${projName.name == activeProject}"><option value="${projName.name}" selected>${projName.name}</option></c:if>
							<c:if test="${projName.name != activeProject}"><option value="${projName.name}">${projName.name}</option></c:if>
						</c:forEach>
					</select>
					<span class="descriptionProject"> <spring:message code="label.project"/> </span>
				</div>
				
				<c:remove var = "activeProject" />
						
				<div class="buildFound">
					<select name="build">
						<c:forEach items = "${buildList}" var = "buildVersion" varStatus = "status" >		
							<c:if test="${buildVersion.name == activeBuild}"><option value="${buildVersion.name}" selected>${buildVersion.name}</option></c:if>
							<c:if test="${buildVersion.name != activeBuild}"><option value="${buildVersion.name}">${buildVersion.name}</option></c:if>
						</c:forEach>
					</select>
					<span class="descriptionBuild"><spring:message code="label.fndBuild"/> </span>
				</div> 
				
				<c:remove var = "activeBuild" />
				
				<div class="assignee">
					<select name="assignee">
					
						<c:forEach items = "${mailList}" var = "assignee" varStatus = "status" >		
							<c:if test="${assignee.email == activeAssignee}"><option value="${assignee.email}" selected>${assignee.email}</option></c:if>
							<c:if test="${assignee.email != activeAssignee}"><option value="${assignee.email}">${assignee.email}</option></c:if>
						</c:forEach>
					</select>
					<span class="descriptionAssignee"> <spring:message code="label.assignee"/></span>
				</div> 
				
				<c:remove var = "activeAssignee" />
				
				<div class="button">
					<input type="submit" value="  <spring:message code="label.addKey"/>  ">
				</div>
			</form>
			
		</div>
		<div class="back">
			<a  href="<c:url value='/DispatcherController'/>"><spring:message code="label.backKey"/></a>
		</div>
	</body>
</html>