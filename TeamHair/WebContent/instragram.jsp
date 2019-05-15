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
	<c:set var="bloglikedto" value="${requestScope.bloglikedto}"></c:set>
	<script type="text/javascript">
	
		function btnpopup(photoid) {
			
			var sessionid = "${sessionScope.usersdto.userId}"; //var sessionid = ''

						$.ajax({
							url : "Instapopup.insta?",
							data : {
								photoid: photoid,
							},
							type : "POST",
							dataType : "text",
							success : function(data) {
								$('#obscure').html(data);
							}
						})
						
						setTimeout(function() {
							$('.popup').removeClass('animationClose').addClass(
									'animationOpen');
						}, 150);
						
						$('.obscure').fadeIn(50);
						
						$.ajax({
							url : "Instalikes.insta?",
							data : {
								photoid : photoid,
								userid : sessionid,
							},
							type : "POST",
							dataType : "json",
							success : function(data) {
								console.log(data);

								if(data.likeyn == "Y"){
									console.log("Y들어옴");
									setTimeout(function() {
										$('#test').removeClass('far fa-heart');
										$('#test').addClass('fas fa-heart');
									}, 100);
									
								}else if(data.likeyn == "N"){
									console.log("Y들어옴");
									setTimeout(function() {
										$('#test').removeClass('fas fa-heart');
										$('#test').addClass('far fa-heart');
									}, 100);
								}else if(data.likeyn == null){
									insertlike(photoid);
								}
							}
						})
						
							
		}
		
		function insertlike(photoid) {
			var sessionid = "${sessionScope.usersdto.userId}"; //var sessionid = ''
			$.ajax({
				url : "Instalike.insta?",
				data : {
					photoid : photoid,
					userid : sessionid,
					likeyn : "N",
					wasuser : "n"
				},
				type : "POST",
				dataType : "json",
				success : function(data) {
					if(data.likeyn == "Y"){
						$('#test').removeClass('far fa-heart');
						$('#test').addClass('fas fa-heart');
					}else if(data.likeyn == "N"){
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
		
		function btnclosesub() {
			setTimeout(function() {
				$('.obscure-sub').fadeOut(350);
			}, 50);
			$('.popup-sub').removeClass('animationOpen-sub').addClass(
					'animationClose-sub');
		}
		
		function likecount(photoid) {
			$.ajax({
				url : "Instaphotolike.insta?",
				data : {
					photoid: photoid,
				},
				type : "POST",
				dataType : "text",
				success : function(data) {
					console.log(data);
					$('#likecount').html(data);
				}
			})
		}
	
		function like(photoid) {
			var sessionid = "${sessionScope.usersdto.userId}";
			if($('#test').hasClass('far fa-heart') == true){
				console.log("like 들어옴");
				$.ajax({
					url : "InstalikeYn.insta?",
					data : {
						photoid : photoid,
						userid : sessionid,
						likeyn : "Y"
					},
					type : "POST",
					dataType : "text",
					success : function(data) {
						$('#test').removeClass('far fa-heart');
						$('#test').addClass('fas fa-heart');
						likecount(photoid);
					}
				});
			}else if($('#test').hasClass('fas fa-heart') == true){
				$.ajax({
					url : "InstalikeYn.insta?",
					data : {
						photoid : photoid,
						userid : sessionid,
						likeyn : "N"
					},
					type : "POST",
					dataType : "text",
					success : function(data) {
						$('#test').removeClass('fas fa-heart');
						$('#test').addClass('far fa-heart');
						likecount(photoid);
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
					type : "POST",
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
		
		function photoinsert() {
			setTimeout(function() {
				$('.popup-sub').removeClass('animationClose-sub').addClass(
						'animationOpen-sub');
			}, 100);
			
			$('.obscure-sub').fadeIn(50);
		}
		
		function deletephoto(photoid) {
			 location.href = "Instadelete.insta?photoid="+photoid;
		}
		
		function followerclick() {
			setTimeout(function() {
				$('.popup-sub-follower').removeClass('animationClose-sub-follower').addClass(
						'animationOpen-sub-follower');
			}, 100);
			
			$('.obscure-sub-follower').fadeIn(50);
		}
		
		function followingclick() {
			setTimeout(function() {
				$('.popup-sub-following').removeClass('animationClose-sub-following').addClass(
						'animationOpen-sub-following');
			}, 100);
			
			$('.obscure-sub-following').fadeIn(50);
		}
		
	</script>
		
<body>
<header>

	<div class="container">

		<div class="profile">

			<div class="profile-image">

				<img src="./upload/${profiledto.photoName}" alt="">

			</div>

			<div class="profile-user-settings">

				<h1 class="profile-user-name">${usersDto.userId }</h1>

				<button class="btn profile-edit-btn" onclick="location.href='Modify.usr'">Edit Profile</button>

				<button class="btn profile-edit-btn" onclick="photoinsert()">Photo</button>

			</div>

			<div class="profile-stats">

				<ul>
					<li><span class="profile-stat-count">${blogList.size()}</span> posts</li>
					<li><a href="#" onclick="followerclick();return false;"><span class="profile-stat-count">188</span> followers</a></li>
					<li><a href="#" onclick="followingclick();return false;"><span class="profile-stat-count">188</span> following</a></li>
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
				
				
				<!-- Photo Insert Popup -->
				<div class="obscure-sub">			
				<div class="popup-sub animationClose-sub">
				<div class="container">
	<div class="row">
		<div class="right-sub">
			<div class="right-content">
			
								<div class="">
					<a class="btnclose" href="#" onclick="btnclosesub()"><i
						class="fas fa-times"></i></a>
				</div>
					<h1>업로드</h1>
					
					<form action="Instawrite.insta" enctype="Multipart/form-data" method="post">
					<div class="mt-10">
						<input type="file" name="file" class="single-input-secondary-sub-infor">
					</div>
					<div class="mt-10">
						<input type="text" name="comments" placeholder="내용" onfocus="this.placeholder = ''" onblur="this.placeholder = '내용'" required="" class="single-input-secondary-sub-infor">
					</div>
					<div class="btn-file">
							<button type="submit" class="genric-btn info circle arrow">올리기<span class="lnr lnr-arrow-right"></span></button>
						</div>
					</form>
				</div>
		</div>
		</div>
</div>
</div>
</div>
				
				
				<!-- INSTAGRAM FOLLOWER LIST START -->
				<div class="obscure-sub-follower">			
				<div class="popup-sub-follower animationClose-sub-follower">
				<div class="container">
	<div class="row">
			
					<h1>팔로워</h1>
					<hr>
				</div>
		</div>
		</div>
</div>
				
				<!-- INSTAGRAM FOLLOWER LIST END -->
				
				
				<!-- INSTAGRAM FOLLOWING LIST START -->
				<div class="obscure-sub-following">			
				<div class="popup-sub-following animationClose-sub-following">
				<div class="container">
	<div class="row">
			
					<h1>팔로윙</h1>
					<hr>
				</div>
		</div>
		</div>
</div>
				
				<!-- INSTAGRAM FOLLOWING LIST END -->
				
				
				
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
			    
			<a class="openBtn" href="#" onclick="btnpopup(${blogList[i].photoId});return false;">

				<img src="./upload/${blogList[i].fileName}" class="gallery-image">

				<div class="gallery-item-info">

					<ul>
						<li class="gallery-item-likes"><span class="visually-hidden">Likes:</span><i class="fas fa-heart" aria-hidden="true"></i> ${bloglikedto[i].likeCount }</li>
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

	</div>
	<!-- End of container -->

	
	<jsp:include page="/WEB-INF/common/footer.jsp"></jsp:include>
