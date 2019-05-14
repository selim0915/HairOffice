<%@page import="kr.or.bit.dao.FollowingFollowerDao"%>
<%@page import="kr.or.bit.dto.FollowerDto"%>
<%@page import="kr.or.bit.dto.FollowingDto"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>

<%
	FollowingDto followingDto = new FollowingDto();
	FollowerDto followerDto = new FollowerDto();
	
	FollowingFollowerDao dao = new FollowingFollowerDao();
	
	followingDto.setFollowingId("Woo");
	followingDto.setUserId("king");
	
	
	int addrow = dao.addFollowingFollower(followingDto);
	int followingNum = dao.getFollowingNumberByUserId("king");
	int followerNum = dao.getFollowerNumberByUserId("king");
	
%>
ADD <%= addrow %><br>

Following : <%=followingNum %><br>
Follower : <%=followerNum %><br>


</body>
</html>