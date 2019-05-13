<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Employee Picture</title>
</head>
<body>
	<!-- nav -->
	<div class="container">
		
		<div id="viewfilediv">
			<div><input type="text" class="form-control" value="${requestScope.file.empno}" readonly></div><br><br><br>
			<img src="upload/${requestScope.file.filename}" height="100%" width="100%">
		</div>
	</div>
		
</body>
</html>