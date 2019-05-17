<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>    
    
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<style type="text/css">
	#searchResult {
		margin : 5% 10%;
	}
	#period {
		margin : 10px;
	}
</style>
<title>Space Search Result</title>
<jsp:include page="/WEB-INF/common/header.jsp"></jsp:include>
</head>
<body>
 	<div id="searchResult">

		<c:set var="list" value="${requestScope.spaceList }"/>
		<div id="period" style="float:right;">
			<b>FROM</b>&nbsp;&nbsp;&nbsp;&nbsp; ${requestScope.start_date}&nbsp;&nbsp;&nbsp;&nbsp;<b>TO</b>&nbsp;&nbsp;&nbsp;&nbsp;${requestScope.end_date}
		</div>		
		<table border="1">
			<tr><td><b>지점명</b></td><td><b>공간ID</b></td><td><b>공간명</b></td><td><b>공간유형</b></td><td><b>가격</b></td></tr>
			<c:forEach var="space" items="${list}">
				<tr><td>${space.branchName}</td><td>${space.spaceId }</td><td>${space.spaceName }</td><td>${space.codeName}</td>
					<td>
						<c:choose>
							<c:when test="${space.code == '01'}">&#8361;400,000</c:when>
							<c:when test="${space.code == '03'}">&#8361;900,000</c:when>
							<c:when test="${space.code == '07'}">&#8361;2,500,000</c:when>
							<c:when test="${space.code == '14'}">&#8361;3,000,000</c:when>
							<c:when test="${space.code == '15'}">문의</c:when>
						</c:choose>
					</td></tr>
			</c:forEach>
		</table>
	</div>

	<div style="margin: auto;" >
		HairOffice는 직접 경험할 때 가장 잘 알 수 있습니다!
		위에서 방문을 예약하거나 전화로 문의하세요: 02-553-2244
	</div>
	
	<jsp:include page="/WEB-INF/common/footer.jsp"></jsp:include>
</body>
</html>