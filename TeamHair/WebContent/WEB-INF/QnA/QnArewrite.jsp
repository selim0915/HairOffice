<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<jsp:include page="/WEB-INF/common/header.jsp"></jsp:include>
<script type="text/javascript" src="ckeditor/ckeditor.js"></script>
<meta charset="UTF-8">
<title>답글 작성 페이지</title>
</head>
<body>
<h3>답글작성 페이지</h3>
<hr>
<form action="QnAreinsert.do" method="post" enctype="Multipart/form-data">
	<input type="hidden" name="parentref" value="${requestScope.parent.boardID}">
	<input type="hidden" name="parentdepth" value="${requestScope.parent.replyDepth}">
	<input type="hidden" name="parentseq" value="${requestScope.parent.replySeq}">
	
		<table>
			<tr>
				<td style="width:10%;">작성자</td>
				<td><input type="text" name="userid" value="${sessionScope.usersdto.userId}" readonly></td>
			</tr>
			
			<tr>
				<td>제목</td>
				<td><input type="text" size="50%" name="boardsubject"></td>
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