<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>


    <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
    
	<c:set var="likecount" value="${requestScope.likecount}" />
	
	<a class="user-comments" href="#">좋아요 ${likecount }개</a>
	
	