<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="en" >

	<!-- Header Include Start -->
	<jsp:include page="/WEB-INF/common/header.jsp"></jsp:include>
	<!-- Header Include End -->
	<link rel="stylesheet" href="css/contentpopup.css">
	<script src="https://code.jquery.com/jquery-3.2.1.min.js"></script>
	
	<c:set var="blogList" value="${requestScope.bloglist}"></c:set>
	<c:set var="usersDto" value="${sessionScope.usersdto}"></c:set>
	<c:set var="profileDto" value="${requestScope.profiledto}"></c:set>
	<script type="text/javascript">
	
		function btnpopup(photoid) {

						$.ajax({
							url : "Instapopup.insta?",
							data : {
								photoid: photoid,
							},
							type : "GET",
							dataType : "text",
							success : function(data) {
								$('#obscure').html(data);
							}
						});
						setTimeout(function() {
							$('.popup').removeClass('animationClose').addClass(
									'animationOpen');
						}, 100);
						
						$('.obscure').fadeIn(50);
						
							var sessionid = "${sessionScope.usersdto.userId}"; //var sessionid = ''
							$.ajax({
								url : "Instalikes.insta?",
								data : {
									photoid : photoid,
									userid : sessionid,
								},
								type : "GET",
								dataType : "json",
								success : function(data) {
									if(data.likeyn == null){
										insertlike(photoid);
									}else if(data.likeyn == 'y'){
										$('#test').removeClass('far fa-heart');
										$('#test').addClass('fas fa-heart');
									}else if($('#test').hasClass('fas fa-heart') == true){
										$('#test').removeClass('fas fa-heart');
										$('#test').addClass('far fa-heart');
									}
									}
							});
		}
		
		function insertlike(photoid) {
			var sessionid = "${sessionScope.usersdto.userId}"; //var sessionid = ''
			$.ajax({
				url : "Instalike.insta?",
				data : {
					photoid : photoid,
					userid : sessionid,
					likeyn : "n",
					wasuser : "n"
				},
				type : "GET",
				dataType : "json",
				success : function(data) {
					if(data.likeyn == 'y'){
						$('#test').removeClass('far fa-heart');
						$('#test').addClass('fas fa-heart');
					}else if($('#test').hasClass('fas fa-heart') == true){
						$('#test').removeClass('fas fa-heart');
						$('#test').addClass('far fa-heart');
					}
					}
			});
		}
		
		function btnclose() {
			setTimeout(function() {
				$('.obscure').fadeOut(350);
			}, 50);
			$('.popup').removeClass('animationOpen').addClass(
					'animationClose');
		}
	
		function like(photoid) {
			var sessionid = "${sessionScope.usersdto.userId}";
			if($('#test').hasClass('far fa-heart') == true){
				$.ajax({
					url : "InstalikeYn.insta?",
					data : {
						photoid : photoid,
						userid : sessionid,
						likeyn : "y"
					},
					type : "GET",
					dataType : "text",
					success : function(data) {
						$('#test').removeClass('far fa-heart');
						$('#test').addClass('fas fa-heart');
					}
				});
			}else if($('#test').hasClass('fas fa-heart') == true){
				$.ajax({
					url : "InstalikeYn.insta?",
					data : {
						photoid : photoid,
						userid : sessionid,
						likeyn : "n"
					},
					type : "GET",
					dataType : "text",
					success : function(data) {
						$('#test').removeClass('fas fa-heart');
						$('#test').addClass('far fa-heart');
					}
				});
			}
		}
		
		function comments(photoid) {
			var sessionid = "${sessionScope.usersdto.userId}";
			if($('.instagram-card-input').val() == ""){
				$('.instagram-card-input').attr('placeholder', '댓글을 입력하세요.' );
				$('.instagram-card-input').focus();
			}else{
				$.ajax({
					url : "Instacomment.insta?",
					data : {
						comments : $('.instagram-card-input').val(),
						userid : sessionid,
						photoid : photoid
					},
					type : "GET",
					dataType : "text",
					success : function(data) {
						$('.instagram-card-input').val("");
						$('#comment-append').html(data);
					}
				})
			}
		}
		
		function commentfocus() {
			$('.instagram-card-input').focus();
		}
	</script>
		
<body>
<header>

	<div class="container">

		<div class="profile">

			<div class="profile-image">

				<img src="https://images.unsplash.com/photo-1513721032312-6a18a42c8763?w=152&h=152&fit=crop&crop=faces" alt="">

			</div>

			<div class="profile-user-settings">

				<h1 class="profile-user-name">${usersDto.userId }</h1>

				<button class="btn profile-edit-btn">Edit Profile</button>

				<button class="btn profile-edit-btn">Photo</button>

			</div>

			<div class="profile-stats">

				<ul>
					<li><span class="profile-stat-count">164</span> posts</li>
					<li><span class="profile-stat-count">188</span> followers</li>
					<li><span class="profile-stat-count">206</span> following</li>
				</ul>

			</div>

			<div class="profile-bio">

				<p><span class="profile-real-name">${usersDto.userName}</span>　${profileDto.introduction}</p>

			</div>

		</div>
		<!-- End of profile section -->

	</div>
	<!-- End of container -->

</header>

<!-- INSTAGRAM POPUP -->
			<div class="obscure" id="obscure">
				
						</div>
				
				
				<!-- POPUP END -->
				
	<div class="container">

		<div class="gallery">

		<c:choose>
				<c:when test="${blogList.size()==0}">
					<div class="blogListNullDiv">작성 한 게시글이 없습니다.</div>
				</c:when>
				<c:otherwise>
					<c:forEach var="i" begin="0" end="${blogList.size()-1}">
			<div class="gallery-item" tabindex="0">
				<div>
			    
			<a class="openBtn" href="#" onclick="btnpopup(${blogList[i].photoId})">

				<img src="./upload/${blogList[i].fileName}" class="gallery-image">

				<div class="gallery-item-info">

					<ul>
						<li class="gallery-item-likes"><span class="visually-hidden">Likes:</span><i class="fas fa-heart" aria-hidden="true"></i> 56</li>
						<li class="gallery-item-comments"><span class="visually-hidden">Comments:</span><i class="fas fa-comment" aria-hidden="true"></i> 2</li>
					</ul>
				</div>
				</a>
				</div>
			</div>
					</c:forEach>
				</c:otherwise>
			</c:choose>


		</div>
		<!-- End of gallery -->

		<div class="loader"></div>

	</div>
	<!-- End of container -->

	
	<jsp:include page="/WEB-INF/common/footer.jsp"></jsp:include>
