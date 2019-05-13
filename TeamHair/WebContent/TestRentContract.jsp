<%@page import="kr.or.bit.dao.RentcontractDao"%>
<%@page import="kr.or.bit.dao.RentalHistoryDao"%>
<%@page import="kr.or.bit.utils.TeamDate"%>
<%@page import="kr.or.bit.utils.TeamFormat"%>
<%@page import="kr.or.bit.dao.RentContractDao"%>
<%@page import="kr.or.bit.dto.RentContractDto"%>
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

	RentContractDto dto = new RentContractDto();
	RentcontractDao dao = new RentcontractDao();
	
 	
	int delrow = dao.deleteRentContract(42);
	
	dto.setUserId("mong");
	dto.setSpaceId(22);
	dto.setStartDate(TeamDate.date("20190301"));
	dto.setEndDate(TeamDate.date("20191231"));
	dto.setDeposit(1000000);
	dto.setMonthlyrental(500000);
	dto.setDiscountAmount(0);
	dto.setPayMethod("CRDT");
	
%>
BEFORE INSERT : <%=dto.toString() %><br>

<% 	
	int row=dao.insertRentContract(dto);

%>
INSERT : <%=row %><br>

<%
	dto = dao.getRentContractbyCondition(43, "mong", 22);
%>
AFTER INSERT : <%=dto.toString() %><br>

<%
	List<RentContractDto> dtoList = new ArrayList<RentContractDto>();
	dtoList = dao.getRentContractList();
	
%>


<c:set var="list" value="<%=dtoList %>"/>
<table border="1">
	<tr><td>소개</td><td>웹사이트</td></tr>
	<c:forEach var="rent" items="${list }">
		<tr><td>${rent.rentId}</td><td>${rent.spaceId }</td></tr>
	</c:forEach>
</table>


<% 
	dto.setEndDate(TeamDate.date("20200505"));
	dto.setMonthlyrental(888888);
%>

BEFORE UPDATE : <%= dto.toString() %><br>
<% 
	dao.updateRentContract(dto);
	dto = dao.getRentContractbyRentId(43);
%>
UPDATE : <%=dto.toString() %>


</body>

</html>