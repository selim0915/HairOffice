<%@page import="kr.or.bit.dto.PhotoDto"%>
<%@page import="kr.or.bit.dao.PhotoDao"%>
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
	PhotoDao dao = new PhotoDao();
	PhotoDto dto = new PhotoDto();
	
	int delrow = dao.deletePhoto("woo",1);
%>
DELETE : <%=delrow %><br>


<% 
	dto.setFileName("main.jpg");
	dto.setDescription("좋은 하루");
	dto.setUserId("woo");
%>
BEFORE INSERT : <%=dto.toString() %><br>
<% 	
	int row=dao.insertPhoto(dto);
%>
INSERT : <%=row %><br>
<% 
	dto.setFileName("main2.jpg");
	dto.setDescription("좋은 하루2");
	dto.setUserId("woo");
%>
BEFORE INSERT : <%=dto.toString() %><br>
<% 	
	row=dao.insertPhoto(dto);
%>
INSERT : <%=row %><br>


<%
	List<PhotoDto> dtoList = new ArrayList<>();
	dtoList = dao.selectPhotoAllList("woo");
%>
<c:set var="list" value="<%=dtoList %>"/>
<table border="1">
	<tr><td>전체</td><td>파일명</td><td>내용</td><td>아이디</td></tr>
	<c:forEach var="photo" items="${list}">
		<tr>
			<td>${photo}</td>
			<td>${photo.fileName }</td>
			<td>${photo.description }</td>
			<td>${photo.userId }</td>
		</tr>
	</c:forEach>
</table>


<%
	dto.setFileName("hello.jpg");
	dto.setDescription("날씨 좋다.");
	dto.setUserId("woo");
	dto.setPhotoId(10);
%> 
BEFORE UPDATE : <%= dto.toString() %><br>
<% 
	dao.updatePhoto(dto);
%>
UPDATE : <%=dto.toString() %>

<c:set var="list" value="<%=dtoList %>"/>
<table border="1">
	<tr><td>전체</td><td>파일명</td><td>내용</td><td>아이디</td></tr>
	<c:forEach var="photo" items="${list}">
		<tr>
			<td>${photo}</td>
			<td>${photo.fileName }</td>
			<td>${photo.description }</td>
			<td>${photo.userId }</td>
		</tr>
	</c:forEach>
</table>

</body>
</html>