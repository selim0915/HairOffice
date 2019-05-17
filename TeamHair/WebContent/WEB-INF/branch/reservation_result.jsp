<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>    
    
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<style type="text/css">
	#reservationResult {
		margin : 5%;
	}
	
	#tableReservation {
		margin : 5%;
	}
</style>
<title>Space Search Result</title>
<jsp:include page="/WEB-INF/common/header.jsp"></jsp:include>
</head>
<body>
 	<div id="reservationResult">


		<div id="tableReservation">
			<div>예약완료</div>
		
			<table border="1">
				<tr><td><b>회원명</b></td><td><b>디자이너</b></td><td><b>예약일</b></td><td><b>시간</b></td><td><b>서비스유형</b></td></tr>
				<tr><td>${requestScope.userName}</td><td>${requestScope.designer}</td><td>${requestScope.start_date}</td><td>${requestScope.hour} 시 </td><td>${requestScope.codeDto.codeName}</td></tr>
			</table>
		</div>		
	</div>

	
	<jsp:include page="/WEB-INF/common/footer.jsp"></jsp:include>
</body>
</html>