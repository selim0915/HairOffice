<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="zxx" class="no-js">

	<!-- Header Include Start -->
	<jsp:include page="/WEB-INF/common/header.jsp"></jsp:include>
      <link rel="stylesheet" href="css/style.css">
	<!-- Header Include End -->


<c:set var="likephotolistdto" value="${requestScope.likephotolistdto }"></c:set>
<section class="home-banner-area relative banner-area">
	<div class="container-fluid">
		<div class="row  d-flex align-items-center justify-content-center">
		<div class="col-lg-11 col-md-6 ">
  <h1>최고 디자이너들의 갤러리 목록</h1>
  <p>고객이 가장 많이 좋아요를 누른 디자이너의 Photo list입니다.</p>
		</div>
		</div>
	</div>
</section>

	<form action="Instauserid.insta" method="GET">
<div class="container-fluid">
	<div class="row gallery-sub">
		<div class="block" id="blue">
	<select class="display-none">
		<option value="userid">ID</option>
	</select>
	</div>
			<div class="search-insta">
	<input type="text" name="userid" placeholder="Search"
		onfocus="this.placeholder = ''" onblur="this.placeholder = 'Search'"
		required class="single-input-secondary-sub">
	</div>
			<div class="search-btn">
				<button class="genric-btn info circle arrow">검색<span
					class="lnr lnr-arrow-right"></span></button>
			</div>

</div>
</div>
			</form>
    <script  src="js/selectscroll.js"></script>

<!-- Start Condition Area -->

<section class="home-banner-area relative banner-area">
	<div class="container-fluid">
		<div class="row  d-flex align-items-center justify-content-center">
			<div class="col-lg-11 col-md-6 ">
			<div id='content'>
			<c:forEach var="i" begin="0" end="${likephotolistdto.size()-1 }">
			<a href="Instauserid.insta?userid=${likephotolistdto[i].userid }" class="post">
			<div class="image" style="background-image:url('./upload/${likephotolistdto[i].filename}');"></div>
			<ul>
			<li><i class="fa fa-camera"></i> Normal</li>
			<li><i class="fa fa-heart"></i> ${likephotolistdto[i].likecount }</li>
			<li><i class="fa fa-comment"></i></li>
			
			</ul>
			
			</a>
			
			</c:forEach>
			</div>
				
			</div>
		</div>
	</div>
</section>
<!-- End Condition Area -->
	
	<jsp:include page="/WEB-INF/common/footer.jsp"></jsp:include>