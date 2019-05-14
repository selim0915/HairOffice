<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
	<jsp:include page="/WEB-INF/common/header.jsp"></jsp:include>
	<meta charset="UTF-8">
	<title>Write</title>
	<script type="text/javascript" src="ckeditor/ckeditor.js"></script>
	<script src="https://code.jquery.com/jquery-1.12.4.js"></script>
	<script src="https://code.jquery.com/ui/1.12.1/jquery-ui.js"></script>
	
	<style type="text/css">
		#write_div {
			margin: 3%;
		}
	</style>
</head>
<body>
<hr>
<div id="write_div">

	<form action="QnAinsert.do" method="post" id="frm" enctype="Multipart/form-data">
	    
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
</div>
	<jsp:include page="/WEB-INF/common/footer.jsp"></jsp:include>
</body>
</html>