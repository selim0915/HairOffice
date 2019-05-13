<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
    
	<c:set var="commentslist" value="${requestScope.commentslist}" />

		<c:forEach var="i" begin="0" end="${commentslist.size()}">
        <p><a class="user-comments" href="#">${commentslist[i].userId}</a> ${commentslist[i].comments}</p>
        <p class="comments">15시간전</p>
        </c:forEach>