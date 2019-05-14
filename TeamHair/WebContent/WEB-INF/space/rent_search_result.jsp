<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>    
    
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Space Search Result</title>
<jsp:include page="/WEB-INF/common/header.jsp"></jsp:include>
</head>
<body>

	<c:set var="list" value="${requestScope.spaceList }"/>
	<table border="1">
		<tr><td>소개</td><td>웹사이트</td></tr>
		<c:forEach var="space" items="${list}">
			<tr><td>${space.spaceId}</td><td>${space.spaceName }</td></tr>
		</c:forEach>
	</table>

	<jsp:include page="/WEB-INF/common/footer.jsp"></jsp:include>
</body>
</html>