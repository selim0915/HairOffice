<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<title>QnA 게시판</title>
	<link rel="stylesheet" href="css/style.css">
</head>
<body>
<link href="https://fonts.googleapis.com/icon?family=Material+Icons" rel="stylesheet">
<link href="https://fonts.googleapis.com/css?family=Open+Sans:300,400,400i,600,700,800" rel="stylesheet">

<h3>QnA메인페이지</h3>
<hr>
		<div id="list">
			<table>
				<tr>
	    			<th><p>글번호</p></th>
					<th class="name">제목</th>
					<th><p>작성자</p></th>
					<th><p>작성일</p></th>
					<th><p>조회수</p></th>
				</tr>
				<c:set var="qnalist" value="${requestScope.qnalist}"></c:set>
				<c:forEach var="i" items="${qnalist }">
				<tr>
					<td>${i.boardID }</td>
					<td>
						<a href="QnAdetail.do?boardID=${i.boardID}">
							<fmt:parseNumber value="${i.replyDepth}" var="rr" type="number"/>
								<c:forEach var="a" begin="1" end="${rr}" step="1">
									&nbsp; &nbsp;<img src="img/re.gif"> 
								</c:forEach>
							${i.boardSubject}
						</a>
					</td>
					<td>${i.userID }</td>
					<td>${i.createDate }</td>
					<td>${i.readCount}</td>
				</tr>
				</c:forEach>
			</table>
		</div>
		<center>
		<c:forEach var="i" begin="1" end="5" step="1">
			<c:choose>
				<c:when test="${cpage==i}">
					<font color='red'>[${i}]</font>
				</c:when>
				<c:otherwise>
					<a href="boardlist.bbs?cp=${i}&ps=${pagesize}">[${i}]</a>
				</c:otherwise>
			</c:choose>
		</c:forEach>
		</center>
<hr>
<input type="button" class="dropdown" value="글쓰기" onclick="location='QnAwrite.do'">
</body>

</html>

