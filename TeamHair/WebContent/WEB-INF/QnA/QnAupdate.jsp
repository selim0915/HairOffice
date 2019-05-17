<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<%
	int cpage = (int)request.getAttribute("cpage");
%>
<jsp:include page="/WEB-INF/common/header.jsp"></jsp:include>
<head>
	<meta charset="UTF-8">
	<title>글 수정 페이지</title>
	<script type="text/javascript" src="ckeditor/ckeditor.js"></script>
	<script src="https://code.jquery.com/jquery-1.12.4.js"></script>
	<script src="https://code.jquery.com/ui/1.12.1/jquery-ui.js"></script>
	
</head>
<body>
<hr>
<c:set var="qna" value="${requestScope.qna}"></c:set>
<form action="QnAupdate.do?cpage=${cpage }" method="post" id="frm" enctype="Multipart/form-data">
<input type="hidden" value="${cpage }" id="cpage">
		<table>
			<tr>
				<td style="width:10%;">작성자</td>
				<td><input type="text" name="userid" value="${sessionScope.usersdto.userId}" readonly></td>
			</tr>
			
			<tr>
				<td>제목</td>
				<td><input type="text" name="boardsubject" size="50%" value="${qna.boardSubject }"></td>
			</tr>
			
			<tr>
				<td>내용</td>
				<td>
					<textarea rows="7" cols="50" name="boardcontent" class="ckeditor">
					${qna.boardContent }
				</textarea>
				</td>
			</tr>
			
			<tr>
				<td>기존화일</td>
				<td><input type="text" name="bfilename" value="${qna.fileName }"></td>
			</tr>
			<tr>
				<td>파일 업로드</td>
				<td><input type="file" name="filename"></td>
			</tr>
				<input type="hidden" name="boardid" value="${qna.boardID }">
			<tr>
				<td>수정</td>
				<td><button type="submit">수정완료</td>
			</tr>
		
		</table>
<!-- 
	<table>
		<tr>
			<td>작성자</td>
			<td><input type="text" name="userid" value="${sessionScope.usersdto.userId}" readonly></td>
		</tr>
		
		<tr>
			<td>제목</td>
			<td><input type="text" name="boardsubject" size="50" value="${qna.boardSubject }"></td>
		</tr>
		
		<tr>
			<td>내용</td>
			<td>
				<textarea rows="7" cols="50" name="boardcontent" class="ckeditor">
					${qna.boardContent }
				</textarea>
			</td>
		</tr>
		
		<tr>
			<td>기존파일</td>
			<td><input type="text" name="bfilename" value="${qna.fileName }"></td>
		</tr>
		
		
		<tr>
			<td>파일 업로드</td>
			<td><input type="file" name="filename"></td>
		</tr>
			<input type="hidden" name="boardid" value="${qna.boardID }">
		<tr>
			
		</tr>
		
	</table>
 -->
</form>

</body>
</html>