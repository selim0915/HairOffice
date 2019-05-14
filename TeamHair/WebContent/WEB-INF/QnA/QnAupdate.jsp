<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<title>글 수정 페이지</title>
	<script type="text/javascript" src="/t/ckeditor/ckeditor.js"></script>
	<script src="https://code.jquery.com/jquery-1.12.4.js"></script>
	<script src="https://code.jquery.com/ui/1.12.1/jquery-ui.js"></script>
	
</head>
<body>
<h3>글 수정 페이지</h3>
<hr>
<c:set var="qna" value="${requestScope.qna}"></c:set>
${qna }
<form action="QnAupdate.do" method="post" id="frm" enctype="Multipart/form-data">
	<table>
		<tr>
			<td>작성자</td>
			<td><input type="text" name="userid" value="${sessionScope.usersdto.userId}" readonly></td>
		</tr>
		
		<tr>
			<td>제목</td>
			<td><input type="text" name="boardsubject" value="${qna.boardSubject }"></td>
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
			<input type="text" name="boardid" value="${qna.boardID }">
		<tr>
			<td><button type="submit">수정완료</td>
		</tr>
		
	</table>
</form>

</body>
</html>