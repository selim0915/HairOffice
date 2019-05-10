<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%
	response.setCharacterEncoding("UTF-8");
	response.setContentType("text/html;charset=UTF-8");
%>
<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<title>상세정보</title>
	
	<script type="text/javascript" src="http://code.jquery.com/jquery-3.2.0.min.js" ></script>
	<script>
		$(function(){
			$('#enroll').click(function(){
				addreply();
		    	list();
			});
			$('#delete').click(function(){
				deletereply($(this).attr('value'));
			});
		});
		
		function list(){
			var output;
	    	$.getJSON("QnAcommentslistAsync.do?",{boardid:$('#boardid').val()}, function (data) {
	    		$('#reply > table > tr').remove();
	    		
	    		var comment = data.commentslist;
	    		console.log(comment);
	    		
	    		$.each(comment, function(key,val){
	    			output += "<tr>";
	    			output += "<td>작성자 : "+val.userID+" </td>";
	    			output += "<td> "+val.createDate+" </td>";
	    			output += "<td><button id='delete' value='" + val.commentsID + "' onclick='deletereply(this.value)'>삭제</button></td>";
	    			output += "</tr>";
	    			output += "<tr>";
	    			output += "<td>내용 : "+val.comments+"</td>";
	    			output += "</tr>";
	 				$('#reply').html(output);
	    		});
			});
		}
		
		function addreply(){	
			var param = {
					       "boardid":$('#boardid').val(),
						   "comments":$('#comment').val(),
						   "userid" : "detail.jsp...session.getid해오기"
					    };
			//alert("**param : " + $('#reply_writer').val());
			$.ajax({
				url:"QnAcommentsinsert.do",
				datatype:"json",
				data:param,
			});
			return false;
		}
		
		function deletereply(commentid){
			var param = commentid;
			var url="QnAcommentsdelete.do?commentid=" + param;
			console.log(url);
			$.ajax({
				url:url,
				type:"get",
				success: function(){
					list();
				},
			});
			return false;
		}
		
		
		
	</script>
	
	<script type="text/javascript">
		function check(){
			var form = document.file;
			var ext = form.filename.value;
			console.log(form);
			
			var result = ext.substring(ext.lastIndexOf(".")+1);
			console.log(result);
			
 			if(result == "jpg" || result=="png" || result=="jpeg" ||result=="bmp" || result=="gif"){
				console.log("이미지파일 맞음");
				form.action="FileUpload.do";
				document.getElementById('file').submit();
			}else{
				console.log("이미지파일 아님");
				alert('이미지 파일이 아닙니다.');
			}
		}
	</script>

</head>
<body>
	<c:set var="detail" value="${requestScope.detail}"></c:set>
	<table border="1">
		<tr>
			<td>글번호</td>
			<td>${detail.boardID }</td>
		</tr>
		
		<tr>
			<td>제목</td>
			<td>${detail.boardSubject }</td>
		</tr>
		
		<tr>
			<td>작성자</td>
			<td>${detail.userID }</td>
		</tr>
		
		<tr>
			<td>작성일</td>
			<td>${detail.createDate }</td>
		</tr>
		
		<tr>
			<td>내용</td>
			<td>${detail.boardContent }</td>
		</tr>
		
		<tr>
			<td>업로드 파일</td>
			<td>${detail.fileName }</td>
		</tr>
		
	</table>
	
	
	<input type="button" value="글 수정" onClick="location.href='QnAupdateform.do?boardid=${detail.boardID}'">
	<input type="button" value="글 삭제" onClick="location.href='QnAdelete.do?boardid=${detail.boardID}'">
	<input type="button" value="답글" onClick="location.href='QnAdelete.do?boardid=${detail.boardID}'">
	<input type="button" value="목록보기" onClick="location.href='QnA.do'">
	<hr>

	댓글<br>
	<hr>
	<div id="reply">
		<c:set var="comments" value="${requestScope.comment}"></c:set>
		<table>
		<c:forEach var="i" items="${comments }">
		
			<tr>
				<td>작성자 : ${i.userID }</td>
				<td>${i.createDate }</td>
				<td><button id='delete' value='${i.commentID }'>삭제</button></td>
			</tr>
			<tr>
				<td>내용 : ${i.comments }</td>
			</tr>
		</c:forEach>
		</table>
	</div>
	<hr>
	
	<!-- <form action="QnAcommentsinsert.do" method="post"> -->
	
	
	<input type="hidden" name="boardid" id="boardid" value="${detail.boardID }">
	<input type="text" name="comment" id="comment">	
	

	<input type="button" id="enroll" value="등록">
	
	
</body>
</html>