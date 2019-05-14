<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
	<jsp:include page="/WEB-INF/common/header.jsp"></jsp:include>
	<meta charset="UTF-8">
	<title>글 작성 페이지</title>
	<script type="text/javascript" src="ckeditor/ckeditor.js"></script>
	<script src="https://code.jquery.com/jquery-1.12.4.js"></script>
	<script src="https://code.jquery.com/ui/1.12.1/jquery-ui.js"></script>
</head>
<body>
<h3>글작성 페이지</h3>
<hr>

<form action="QnAinsert.do" method="post" id="frm" enctype="Multipart/form-data">
	<table>
		<tr>
			<td>작성자</td>
			<td><input type="text" name="userid" value="${sessionScope.usersdto.userId}"></td>
		</tr>
		
		<tr>
			<td>제목</td>
			<td><input type="text" name="boardsubject"></td>
		</tr>
		
		<tr>
			<td>내용</td>
			<td>
				<textarea rows="10" cols="60" name="boardcontent" class="ckeditor"></textarea>
			</td>
		</tr>
		
		<tr>
			<td>파일 업로드</td>
			<td><input type="file" name="filename"></td>
		</tr>
		
		<tr>
			<td>등록</td>
			<td><button type="submit">글 등록</td>
		</tr>
	
	</table>
</form>
	<jsp:include page="/WEB-INF/common/footer.jsp"></jsp:include>
</body>
</html>