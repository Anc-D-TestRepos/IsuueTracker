<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="UTF-8" %>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<link type="text/css" href="webResources/css/defectEditPage.css" rel="stylesheet" />
<script src="webResources//js//issue.js" type="text/javascript"></script>
</head>
	<body>
		<div class="title"><spring:message code="label.cngField"/>
		</div>
		<div class="inputData"> 
			
			<form method="POST" action = "<c:url value='/DefectEditingController'/>">
				<div class="id">
					<span class="idInput">
						<input type="text" name="id" readonly="readonly" value="${issue.id}"  >
					</span>
					<span class="idDescr"><spring:message code="label.ld"/>
					</span>
				</div>
				
				<c:remove var = "id" />
				
				<div class="createDate">
					<span class="createDateInput">
						<input type="text" name="create_date" readonly="readonly"  value="${issue.createDate}" >
					</span>
					<span class="createDateDescr"> <spring:message code="label.crDate"/>
					</span>
				</div>		
				
				<c:remove var = "createDate" />
				
				<div class="createBy">
					<span class="createByInput">
						<input type="text" name="create_by" readonly="readonly" value="${issue.createBy}"  >
					</span>
					<span class="createByDescr"> <spring:message code="label.crBy"/>
					</span>
				</div>	
				
				<c:remove var = "createBy" />
				
				<div class="modifyDate">
					<span class="modifyDateInput">
						<input type="text" name="modified_date" readonly="readonly"  value="${issue.modifiedDate}" >
					</span>
					<span class="modifyDateDescr"><spring:message code="label.mdfDate"/>
					</span>
				</div>		
				
				<c:remove var = "modifyDate" />
				
				<div class="modifyBy">
					<span class="modifyByInput">
						<input type="text" name="modified_by" readonly="readonly" value="${issue.modifiedBy}"  >
					</span>
					<span class="modifyByDescr"> <spring:message code="label.mdfBy"/>
					</span>
				</div>			
				
				<c:remove var = "modifyBy" />
				
				<div class="summary">
					<span class="summaryInput">
						<input type="text" name="summary"  maxlength="50"  value = "${issue.summary}">
					</span>
					<span class="summaryDescr"> <spring:message code="label.summ"/>
					</span>
				</div>
				
				<c:remove var = "activeSummary" />
				
				<div class="description">
					<span class="descriptionDInput">
						<textarea name="description" cols="29" rows="5">${issue.description}</textarea>
						</span>
					<span class="descriptionDescr"> <spring:message code="label.descr"/>
					</span>
				</div>
				
				<c:remove var = "activeDescription" />
				
					 
			 
			 <div class="status">
					<select name="status"  >
						<c:forEach items = "${statusList}" var = "status" varStatus = "newstatus" >	
							<c:if test="${activeStatus == 'Assigned'}"><option  value="${status.id}" selected >Assigned</option></c:if>
							<c:if test="${status.name == 'Assigned'}"><option  value="${status.id}">Assigned</option></c:if>
							<c:if test="${activeStatus == 'In Progress'}"><option  value="${status.id}" selected >In Progress</option></c:if>
							<c:if test="${status.name  == 'In Progress'}"><option  value="${status.id}">In Progress</option></c:if>
						</c:forEach>
					</select>
					<span class="descriptionStatus">  <spring:message code="label.status"/> </span>
				</div>
					<c:remove var = "activeStatus" />
			 
			 
			<div class="resolution">
					<select name="resolution" disabled="disabled" >
					
						<c:forEach items = "${resolutionList}" var = "resolution" varStatus = "status" >	
							<c:if test="${activeResolution == resolution.name}"><option value="${resolution.name}" selected>${resolution.name}</option></c:if>
							<c:if test="${activeResolution != resolution.name}"><option value="${resolution.name}">${resolution.name}</option></c:if>
						</c:forEach>	
																	
					</select>
					<span class="resolutionDescr"> <spring:message code="label.resol"/> </span>
				</div>
				
				<c:remove var = "activeResolution" />
				
				
					<div class="type">
					<select name="type">
					
						
						<c:forEach items = "${typeList}" var = "type" varStatus = "status" >	
							<c:if test="${activeType == type.name}"><option value="${type.name}" selected>${type.name}</option></c:if>
							<c:if test="${activeType != type.name}"><option value="${type.name}">${type.name}</option></c:if>
						
						</c:forEach>
					
					
					</select>
					<span class="typeDescr"> <spring:message code="label.type"/> </span>
				</div>
				
				<c:remove var = "activeType" />
				
				<div class="priority">
					<select name="priority">
				
						
						<c:forEach items = "${priorityList}" var = "priority" varStatus = "status" >	
							<c:if test="${activePriority == priority.name}"><option value="${priority.name}" selected>${priority.name}</option></c:if>
							<c:if test="${activePriority != priority.name}"><option value="${priority.name}">${priority.name}</option></c:if>
						</c:forEach>	
						
					</select>
					<span class="priorityDescr">  <spring:message code="label.priority"/> </span>
				</div>
				
				<c:remove var = "activePriority" />
				
					<div class="project">
					<select name="project">
					<c:forEach items = "${projectName}" var = "project" varStatus = "status" >		
				
						<c:if test="${project.name == issue.project}"><option value="${project.name}" selected>${project.name}</option></c:if>
						<c:if test="${project.name != issue.project}"><option value="${project.name}">${project.name}</option></c:if>
						
						</c:forEach>
						<c:if test="${empty projectName}"><option value="${activeProject}" selected>${activeProject}</option></c:if>
					</select>
					<span class="projectDescr">  <spring:message code="label.project"/> </span>
				</div>
				
				<c:remove var = "activeProject" />
				<c:remove var = "projectName" />
				
				<div class="buildFound">
					<select name="build">
					<c:forEach items = "${buildList}" var = "version" varStatus = "status" >		
						
						<c:if test="${version.name ==  issue.buildFound}"><option value="${version.name}" selected>${version.name}</option></c:if>
						<c:if test="${version.name != issue.buildFound}"><option value="${version.name}">${version.name}</option></c:if>
					
						
						</c:forEach>
						<c:if test="${empty buildList}"><option value="${activeBuild}" selected>${activeBuild}</option></c:if>
					</select>
					<span class="buildDescr"> <spring:message code="label.fndBuild"/> </span>
				</div> 
				
				<c:remove var = "activeBuild" />
				<c:remove var = "buildList" />
				
				
				<div class="assignee">
					<select name="assignee">
					<c:forEach items = "${mailList}" var = "assigned" varStatus = "status" >		
						<c:if test="${assigned.email.length() > 0}">
						<c:if test="${assigned.email == issue.assignee.email}"><option value="${assigned.email}" selected>${assigned.email}</option></c:if>
						<c:if test="${assigned.email != issue.assignee.email}"><option value="${assigned.email}">${assigned.email}</option></c:if>
						</c:if>					
					</c:forEach>
					<c:if test="${empty mailList}"><option value="${activeMail}" selected>${activeMail}</option></c:if>
					</select>
					<span class="assigneeDescr">  <spring:message code="label.assignee"/></span>
				</div> 
				
				<c:remove var = "mailList" />
					<c:remove var = "activeMail" />
				
				
				<div class="comment">
					<span class="commentInput">
						<textarea name="newComment" cols="29" rows="5">${activeComment}</textarea>
						</span>
					<span class="newCommentDescr"><spring:message code="label.addComent"/>  
					</span>
					<c:remove var = "activeComment" />
				</div>
				
				<div class="button"><input type="submit" value="   <spring:message code="label.updateKey"/>    "></div>
				
				<div class="AddFileButton"><input   type="button" value = " <spring:message code="label.addFileKey"/>"  onclick="JavaScript:loadingFile(${issue.id},'upload')"  ></div>
			</form>
			</div>
			<div class="attachment">
				<c:if test="${not empty attachList}">
					<fieldset>
		   				<legend><spring:message code="label.attach"/> </legend>
						
						
						<table class="attachTab">
						<c:forEach items = "${attachList}" var="attach"  varStatus = "newstatus" >		
							<tr>	
								<td>
									<div class="attachHeader">
										<span class="addBy"><spring:message code="label.addAtchBy"/></span> ${attach.addedBy}
										<span class="addDate" ><spring:message code="label.addAtchDate"/></span> ${attach.addDate}
									</div>
									<div class="attachBody">
										${attach.fileName}
											<input   type="button" value = "<spring:message code="label.downloadKey"/>"  onclick="JavaScript:loadingFile('${attach.fileName}','download')"  > 
									</div>
								</td>
							</tr>
							
							</c:forEach>
						</table>
						
					
		</fieldset>
		</c:if>
	</div>
		<c:if test="${not empty commentList}">
			<div class="comment">
			<table class="commentTab">
			
				<c:forEach items = "${commentList}" var = "comment" varStatus = "status" >		
					<tr>	
						<td>
							<div class="commentHeader">
								<span class="addBy"><spring:message code="label.addComBy"/></span> ${comment.addedBy}
								<span class="addDate" ><spring:message code="label.addComDate"/></span>${comment.addDate}
							 </div>
							
							<div class="commentBody">${comment.comment} </div>
						</td>
					</tr>
					
					</c:forEach>
			
			</table>		
			</div>
		</c:if>
			
		<div class="back"><a  href="<c:url value='/DispatcherController'/>"><spring:message code="label.backKey"/></a></div>
		
		<form name = "uploadForm" action = "<c:url value='/addFile.jsp'/>">
					<INPUT TYPE="hidden" NAME="id" VALUE="">
				</form>

				<form name = "downloadForm" action = "<c:url value='/DownloadController'/>">
					<INPUT TYPE="hidden" NAME="fileName" VALUE="">
				</form>	
		
		
	</body>
</html>