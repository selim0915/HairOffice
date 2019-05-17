<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
	
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>

<c:set var="userlist" value="${requestScope.codedtolist}"/>
<c:set var="profiledto" value="${requestScope.profiledto}"/>
	
<!DOCTYPE html>
<html lang="en">

<!-- Header Include Start -->
<jsp:include page="/WEB-INF/common/header.jsp"></jsp:include>
<!-- Header Include End -->

<script type="text/javascript">

function photoinsert() {
	setTimeout(function() {
		$('.popup-sub').removeClass('animationClose-sub').addClass(
				'animationOpen-sub');
	}, 100);
	
	$('.obscure-sub').fadeIn(50);
}


function previewImage(targetObj, View_area) {
	var preview = document.getElementById(View_area); //div id
	var ua = window.navigator.userAgent;

  //ie일때(IE8 이하에서만 작동)
	if (ua.indexOf("MSIE") > -1) {
		targetObj.select();
		try {
			var src = document.selection.createRange().text; // get file full path(IE9, IE10에서 사용 불가)
			var ie_preview_error = document.getElementById("ie_preview_error_" + View_area);


			if (ie_preview_error) {
				preview.removeChild(ie_preview_error); //error가 있으면 delete
			}

			var img = document.getElementById(View_area); //이미지가 뿌려질 곳

			//이미지 로딩, sizingMethod는 div에 맞춰서 사이즈를 자동조절 하는 역할
			img.style.filter = "progid:DXImageTransform.Microsoft.AlphaImageLoader(src='"+src+"', sizingMethod='scale')";
		} catch (e) {
			if (!document.getElementById("ie_preview_error_" + View_area)) {
				var info = document.createElement("<p>");
				info.id = "ie_preview_error_" + View_area;
				info.innerHTML = e.name;
				preview.insertBefore(info, null);
			}
		}
  //ie가 아닐때(크롬, 사파리, FF)
	} else {
		var files = targetObj.files;
		for ( var i = 0; i < files.length; i++) {
			var file = files[i];
			var imageType = /image.*/; //이미지 파일일경우만.. 뿌려준다.
			if (!file.type.match(imageType))
				continue;
			var prevImg = document.getElementById("prev"); //이전에 미리보기가 있다면 삭제
			if (prevImg) {
				preview.removeChild(prevImg);
			}
			
			var img = document.createElement("img"); 
			img.id = "prev";
			img.classList.add("rounded-circle");
			img.file = file;
			preview.prepend(img);
			if (window.FileReader) { // FireFox, Chrome, Opera 확인.
				var reader = new FileReader();
				reader.onloadend = (function(aImg) {
					return function(e) {
						aImg.src = e.target.result;
					};
				})(img);
				reader.readAsDataURL(file);
			} else { // safari is not supported FileReader
				//alert('not supported FileReader');
				if (!document.getElementById("sfr_preview_error_"
						+ View_area)) {
					var info = document.createElement("p");
					info.id = "sfr_preview_error_" + View_area;
					info.innerHTML = "not supported FileReader";
					preview.insertBefore(info, null);
				}
			}
		}
	}
}

</script>

<!-- 컨텐츠 팝업 시작 -->
<body>
	<div class="container">
		<div class="row contents_modify">
			<div class="col-lg-3 col-md-3 col-xs-3 col-sm-3 modifymenu">
				<ul>
					<a href="Modify.usr"><li>프로필 수정</li></a>
					<a href="ChangePwd.usr"><li>비밀번호 변경</li></a>
				</ul>
			</div>

			<div class="col-lg-9 col-md-9 col-xs-9 col-sm-9 modifycontent">
				<form action="ModifyUserOk.usr" enctype="Multipart/form-data" method="POST">
					<div class="modifycontentsession1" id="View_area">
						<img src="./upload/${profiledto.photoName}" id="prev"class="rounded-circle" alt="프로필사진">
						<p>
							<strong>${requestScope.usersdto.userId}</strong><br></p>
						<div class="filebox">
						  <label for="ex_file">프로필 사진 바꾸기</label>
						  <input type="file" id="ex_file" name="file" onchange="previewImage(this,'View_area')">
						</div>

					</div>

					<div class="modifycontentsession2">
						<input type="hidden" id="pass" name="pass" class="col-md-6 inputcss form-control" value=${requestScope.usersdto.passwords}>
					</div>

					<div class="modifycontentsession2">
						<label class="col-md-3 labelcss" for="name">사용자 이름</label> 
						<input type="text" id="name" name="name" class="col-md-6 inputcss form-control" value=${requestScope.usersdto.userName}>
					</div>
					
					<div class="modifycontentsession2">
						<label class="col-md-3 labelcss" for="website">웹사이트</label> 
						<input type="text" id="website" name="website" class="col-md-6 inputcss form-control"  value=${requestScope.profiledto.website}>
					</div>
					<div class="modifycontentsession2">
						<label class="col-md-3 labelcss" for="introduction">소개</label> 
						<input type="text" id="introduction" name="introduction" class="col-md-6 inputcss form-control"  value=${fn:replace(requestScope.profiledto.introduction, " ", "&nbsp;")}>
					</div>
					<div class="modifycontentsession2">
						<label class="col-md-3 labelcss" for="email">이메일</label> 
						<input type="text" id="email" name="email" class="col-md-6 inputcss form-control"  value=${requestScope.usersdto.email}>
					</div>
					<div class="modifycontentsession2">
						<label class="col-md-3 labelcss" for="phone">전화번호</label> 
						<input type="text" id="phone" name="phone" class="col-md-6 inputcss form-control"  value=${requestScope.usersdto.phone}>
					</div>
					<div class="modifycontentsession2">
						<input type="hidden" id="gender" name="gender" class="col-md-6 inputcss form-control" value=${requestScope.usersdto.gender}>
					</div>
					
					<div class="modifycontentsession2">
						<label class="col-md-3 labelcss" for="usertype">이용타입</label> 
						<select
							class="col-md-6 inputcss form-control form-control-lg"
							id="usertype" name="usertype">
							
							<c:forEach var="i" begin="0" end="${userlist.size()-1}">
								<c:choose>
									<c:when test="${userlist[i].code == requestScope.usersdto.userType}">
										<option value="${ userlist[i].code}" selected>${ userlist[i].codeName}</option>
									</c:when>
									<c:otherwise>
										<option value="${ userlist[i].code}" >${ userlist[i].codeName}</option>
									</c:otherwise> 
								</c:choose>
							</c:forEach>
						</select>
					</div>
					<div class="modifycontentsession2">
						<label class="col-md-3 labelcss2" for="useSnsYn">로그인상태 유지</label>
						<c:choose>
							<c:when test="${requestScope.usersdto.loginYn=='Y'}">
								<input type="checkbox" id="loginYn" name="loginYn" class="col-md-1 form-control" checked>
							</c:when>
							<c:otherwise>
								<input type="checkbox" id="loginYn" name="loginYn" class="col-md-1 form-control">
							</c:otherwise> 
						</c:choose>
					</div>
					
					<div class="modifycontentsession2">
						<label class="col-md-3 labelcss2" for="useSnsYn">SNS 활성화 여부</label>
						<c:choose>
							<c:when test="${requestScope.usersdto.useSnsYn=='Y'}">
								<input type="checkbox" id="useSnsYn" name="useSnsYn" class="col-md-1 form-control" checked>
							</c:when>
							<c:otherwise>
								<input type="checkbox" id="useSnsYn" name="useSnsYn" class="col-md-1 form-control">
							</c:otherwise> 
						</c:choose>
					</div>
					<div class="modifycontentsession2">
						<label class="col-md-3 labelcss2" for="reserveYn">예약 활성화 여부</label>
						<c:choose>
							<c:when test="${requestScope.usersdto.reserveYn=='Y'}">
								<input type="checkbox" id="reserveYn" name="reserveYn" class="col-md-1 form-control" checked>
							</c:when>
							<c:otherwise>
								<input type="checkbox" id="reserveYn" name="reserveYn" class="col-md-1 form-control">
							</c:otherwise> 
						</c:choose>
					</div>

					<div class="modifycontentsession3">
						<button type="submit" class="btn-lg btn btn-outline-primary modifybtn">수정</button>
					</div>
				</form>
			</div>
		</div>
	</div>
</body>
<!-- 컨텐츠 팝업 끝 -->


<script src="js/index.js"></script>
<jsp:include page="/WEB-INF/common/footer.jsp"></jsp:include>