<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="UTF-8" %>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<link type="text/css" href="webResources/css/projectAddingPage.css" rel="stylesheet" />
<title>add project</title>
</head>
<body>

<div class="title"><spring:message code="label.enterNewProj"/></div>
		<div class="inputData"> 
			
			<form name="addProject"  method="POST" action = "<c:url value='/ProjectAddingController'/>">
				<div class="name">
					<span class="nameInput">
						<input type="text" name="name"  maxlength="50" value = "${activeName}" >
					</span>
					<span class="nameDescr"> <spring:message code="label.name"/> 
					</span>
				</div>
				
				<c:remove var = "activeName" />
				
				<div class="description">
					<span class="descriptionDInput">
						<textarea name="description" cols="29" rows="5"  >${activeDescription}</textarea>
						</span>
					<span class="descriptionDescr"> <spring:message code="label.descr"/>  
					</span>
				</div>
				
				<c:remove var = "activeDescription" />
				
				
				<div class="build">
					<span class="buildInput">
					<input type="text" name="build"  maxlength="30" value = "${activeBuild}" >
					
						</span>
					<span class="buildDescr"> <spring:message code="label.build"/> 
					</span>
				</div>
				
				<c:remove var = "activeBuild" />
				
						
				<div class="manager">
					<input type="text" name="manager"  maxlength="30" value = "${activeManager}" >
					
					<span class="managerDescr"> <spring:message code="label.manager"/> </span>
				</div> 
				
				<c:remove var = "activeManager" />
				
				
				<div class="button">
					<input type="submit" value="   <spring:message code="label.addKey"/>    ">
				</div>
			</form>
			
		</div>
		<div class="back">
			<a  href="<c:url value='/DispatcherController'/>"><spring:message code="label.backKey"/> </a>
		</div>

</body>
</html>