<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
	<!DOCTYPE html>
	<html lang="en">
	<!-- Header Include Start -->
	<jsp:include page="/WEB-INF/common/header.jsp"></jsp:include>
	<!-- Header Include End -->
	<link rel="stylesheet" href="css/contentpopup.css">
	<script type="text/javascript" src="ckeditor/ckeditor.js" ></script>
	<title>Insert title here</title>
	<script type="text/javascript">
		window.onload=function(){
			CKEDITOR.replace('description', {
				height: 300,
				enterMode:'2',
			    shiftEnterMode:'3',
			    filebrowserImageUploadUrl : ''
			});
		};
	</script>
</head>
<body>
	<div class="container">
		<div class="instaWriteFormDiv">
			<form action="instaWriteOk.insta" method="post">
				<input type="text" class="form-control" name="fileName" placeholder="파일이름">
				<textarea id="description" name="description"></textarea>
				<input type="submit" class="btn-lg btn btn-outline-primary instaWriteFormDivBtn" value="작성하기">
			</form>
		</div>
	</div>
	
</body>
	<script src='https://code.jquery.com/jquery-2.2.4.min.js'></script>
	<script src="js/instapopup.js"></script>
	<jsp:include page="/WEB-INF/common/footer.jsp"></jsp:include>
</html>