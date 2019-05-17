<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
	<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

	<c:set var="commentslist" value="${requestScope.commentslist}" />

		<c:forEach var="i" begin="0" end="${commentslist.size()}">
		<fmt:formatDate var="commentsdate" value="${commentslist[i].updateDate}" pattern="yyyy년MM월dd일"/>
        <p><a class="user-comments" href="#">${commentslist[i].userId}</a> ${commentslist[i].comments}</p>
        <p class="comments">${commentsdate }</p>
        </c:forEach>