<%@page import="kr.or.bit.utils.TeamDate"%>
<%@page import="org.apache.jasper.tagplugins.jstl.core.ForEach"%>
<%@page import="java.util.ArrayList"%>
<%@page import="java.util.List"%>
<%@page import="kr.or.bit.dao.SpaceDao"%>
<%@page import="kr.or.bit.dto.SpaceDto"%>
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

	SpaceDto dto = new SpaceDto();
	
	SpaceDao dao = new SpaceDao();
	
	
	int delrow = dao.deleteSpace(123);
%>
DELETE : <%=delrow %><br>

<% 
	dto.setSpaceName("프리미엄고리땡");
	dto.setSpaceType("01");
	dto.setMinNumber(1);
	dto.setMaxNumber(1);
	dto.setBranchId(100);
	
%>

BEFORE INSERT : <%=dto.toString() %><br>

<% 	
	int row=dao.insertSpace(dto);

%>
INSERT : <%=row %><br>

<%
	dto = dao.getSpaceById(48);
%>
SELECT : <%=dto.toString() %><br>


<%
	List<SpaceDto> dtoList = new ArrayList<SpaceDto>();
	dtoList = dao.getSpaceList();
	
%>


<c:set var="list" value="<%=dtoList %>"/>
<table border="1">
	<tr><td>소개</td><td>웹사이트</td></tr>
	<c:forEach var="space" items="${list }">
		<tr><td>${space.spaceName}</td><td>${space.spaceType}</td></tr>
	</c:forEach>
</table>


<%
	
	dto.setSpaceName("일반실");
	
%> 
BEFORE UPDATE : <%= dto.toString() %><br>
<% 
	dao.updateSpace(dto);
	dto = dao.getSpaceById(48);
%>
UPDATE : <%=dto.toString() %><br>
<hr>

희망임대기간동안 사용가능한 space<br>
임대시작 희망일 : 20190601<br>
임대종료 희망일 : 20201231<br>
1인실<br>
<br>
 
<%
	List<SpaceDto> availableDtoList = new ArrayList<SpaceDto>();
	
	availableDtoList = dao.getSpaceAvailableList(1, 100, TeamDate.date("20190601"), TeamDate.date("20201231"));
%>

<c:set var="availablelist" value="<%=availableDtoList %>"/>
<table border="1">
	<tr><td>소개</td><td>웹사이트</td></tr>
	<c:forEach var="space" items="${availablelist }">
		<tr><td>${space.spaceName}</td><td>${space.spaceType}</td></tr>
	</c:forEach>
</table>



</body>

</html>