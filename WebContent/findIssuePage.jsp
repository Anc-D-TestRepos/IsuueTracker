<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="UTF-8" %>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8">
		<link type="text/css" href="webResources/css/findIssuePage.css" rel="stylesheet" />
		<title>Find issue</title>
	</head>
<body>
<%-- <fmt:setLocale value="ru"/> --%>
<div class="title"><spring:message code="label.enterId"/></div>
		<div class="inputData"> 
			
			<form name="findIssue"  method="POST" action = "<c:url value='/IssueSearcherController'/>">
				<div class="issueId">
					<span class="issueIdlnput">
						<input type="text" name="issueId"  maxlength="50" value = "${activeIssueId}" >
					</span>
					<span class="issueId"><spring:message code="label.issueId"/>
					</span>
				</div>
				
				<c:remove var = "activeIssueId" />
				
				
				
				
				<div class="button">
					<input type="submit" value=" <spring:message code="label.findKey"/>">
				</div>
			</form>
			
		</div>
		<div class="back">
			<a  href="<c:url value='/DispatcherController'/>"><spring:message code="label.backKey"/></a>
		</div>

</body>
</html>