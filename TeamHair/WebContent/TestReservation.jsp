<%@page import="kr.or.bit.dao.ReservationDao"%>
<%@page import="kr.or.bit.dto.ReservationDto"%>
<%@page import="kr.or.bit.utils.TeamDate"%>
<%@page import="kr.or.bit.utils.TeamFormat"%>
<%@page import="org.apache.jasper.tagplugins.jstl.core.ForEach"%>
<%@page import="java.util.ArrayList"%>
<%@page import="java.util.List"%>
<%@page import="java.sql.Connection"%>
<%@page import="javax.sql.DataSource"%>
<%@page import="javax.naming.InitialContext"%>
<%@page import="javax.naming.Context"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
 
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

    
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<%


	ReservationDto dto = new ReservationDto();
	ReservationDao dao = new ReservationDao();
	
//	int delrow = dao.deleteRentContract(42);

	dto.setStartDateTime(TeamDate.datetime(2019, 05, 12, 13, 30));
	dto.setEndDateTime(TeamDate.datetime(2019, 05, 12, 15, 00));
	dto.setCancelYn("N");
	dto.setUserId("mong");
	dto.setSpaceId(23);
	dto.setPhotoId(23);
	
%>
BEFORE INSERT : <%=dto.toString() %><br>

<% 	
	int row=dao.insertReservation(dto);

%>
INSERT : <%=row %><br>

<%
	dto = dao.getReservationbyReserveId(61);
%>
AFTER INSERT : <%=dto.toString() %><br>


<%
	List<ReservationDto> dtoList = new ArrayList<ReservationDto>();
	dtoList = dao.getReservationList();
	
%>


<c:set var="list" value="<%=dtoList %>"/>
<table border="1">
	<tr><td>소개</td><td>웹사이트</td></tr>
	<c:forEach var="reserve" items="${list }">
		<tr><td>${reserve.reserveId}</td><td>${reserve.spaceId }</td></tr>
	</c:forEach>
</table>



<% 
	dto.setReserveId(63);
	dto.setEndDateTime(TeamDate.datetime(2019, 05, 12, 17, 55));

%>

BEFORE UPDATE : <%= dto.toString() %><br>
<% 
	dao.updateReservation(dto);
	dto = dao.getReservationbyReserveId(63);
%>
UPDATE : <%=dto.toString() %>

<%
	int delrow = dao.deleteReservation(66);
%>

DELETE : <%=delrow %><br>

</body>

</html>