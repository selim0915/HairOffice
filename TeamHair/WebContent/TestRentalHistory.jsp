<%@page import="kr.or.bit.dao.RentalHistoryDao"%>
<%@page import="kr.or.bit.dto.RentalHistoryDto"%>
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
	
	RentalHistoryDto dto = new RentalHistoryDto();
	RentalHistoryDao dao = new RentalHistoryDao();
	
	
 	
 	int delrow = dao.deleteRentalHistory(1, "mong", 21, "20190430");
%>
INSERT : <%=delrow %><br>
 	
<%	
	dto.setRentId(1);
	dto.setUserId("mong");
	dto.setSpaceId(21);
	dto.setBaseDate("20190430");
	dto.setRentalRevenue(500000);
	dto.setDiscount(0);
	dto.setPayMethod("CRDT");
%>
BEFORE INSERT : <%=dto.toString() %><br>

<% 	
	int row=dao.insertRentalHistory(dto);

%>
INSERT : <%=row %><br>

<%
	dto = dao.getRentalHistorybyCondition(1, "mong", 21, "20190430");
%>
AFTER INSERT : <%=dto.toString() %><br>

<%
	List<RentalHistoryDto> dtoList = new ArrayList<RentalHistoryDto>();
	dtoList = dao.getRentalHistoryList();
	
%>


<c:set var="list" value="<%=dtoList %>"/>
<table border="1">
	<tr><td>소개</td><td>웹사이트</td></tr>
	<c:forEach var="rent" items="${list }">
		<tr><td>${rent.rentId}</td><td>${rent.spaceId }</td></tr>
	</c:forEach>
</table>

<%
	dto.setRentalRevenue(4000000);
	dto.setDiscount(100000);
	
	dao.updateRentalHistory(dto,dto.getRentId(), dto.getUserId(), dto.getSpaceId(), dto.getBaseDate());

	dto = dao.getRentalHistorybyCondition(dto.getRentId(), dto.getUserId(), dto.getSpaceId(), dto.getBaseDate());
%>

UPDATE : <%=dto.toString() %><br>


</body>

</html>