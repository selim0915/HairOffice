<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html lang="en">

<!-- Header Include Start -->
<jsp:include page="/WEB-INF/common/header.jsp"></jsp:include>
<!-- Header Include End -->
<link rel="stylesheet" href="css/contentpopup.css">
<script src="https://code.jquery.com/jquery-3.2.1.min.js"></script>

<c:set var="blogList" value="${requestScope.bloglist}"></c:set>
<c:set var="profileDto" value="${requestScope.profiledto}"></c:set>
<c:set var="bloglikedto" value="${requestScope.bloglikedto}"></c:set>
<c:set var="followinglist" value="${requestScope.followinglist}"></c:set>
	
<c:set var="followerList" value="${requestScope.followerList}"></c:set>
<c:set var="followingList" value="${requestScope.followingList}"></c:set>

<script type="text/javascript">
	
		function btnpopup(photoid) {
			
			var userid = "${param.userid}"; //var sessionid = ''
						$.ajax({					url : "Instapopupid.insta?",
							data : {
								userid: userid,
								photoid: photoid,
							},
							type : "POST",
							dataType : "text",
							success : function(data) {
								console.log(data);
								$('#obscure').html(data);
							}
						})
						
						setTimeout(function() {
							$('.popup').removeClass('animationClose').addClass(
									'animationOpen');
						}, 100);
						
						$('.obscure').fadeIn(50);
						
						var sessionuserid = "${sessionScope.usersdto.userId}";
						
						$.ajax({
							url : "Instalikes.insta?",
							data : {
								photoid : photoid,
								userid : sessionuserid,
							},
							type : "POST",
							dataType : "json",
							success : function(data) {
								console.log("비교값"+data);

								if(data.likeyn == "Y"){
									console.log("Y들어옴");
									setTimeout(function() {
										$('#test').removeClass('far fa-heart');
										$('#test').addClass('fas fa-heart');
									}, 100);
									
								}else if(data.likeyn == "N"){
									console.log("N들어옴");
									setTimeout(function() {
										$('#test').removeClass('fas fa-heart');
										$('#test').addClass('far fa-heart');
									}, 100);
								}else if(data.likeyn == null){
									console.log("null if");
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
			 location.href = "Instadelete.insta?photoid=" + photoid;
		}
		
		function follower() {
			console.log("들어옴");
			if($('#follower').hasClass('profile-edit-btn-followerid') == true){
				location.href = "Instafollower.insta?userid=" + "${param.userid}" + "&sessionid=" + "${sessionScope.usersdto.userId}";
			} else if($('#follower').hasClass('profile-edit-btn-followingid') == true){
				console.log("true wing");
				location.href = "Instafollowing.insta?sessionid=" + "${param.userid}" + "&userid=" + "${sessionScope.usersdto.userId}";

			}
			
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

					<h1 class="profile-user-name">${param.userid}</h1>

					<c:set var="loop_flag" value="false" />
					<c:forEach var="i" begin="0" end="${followinglist.size()}">
						<c:if test="${not loop_flag }">
							<c:if test="${followinglist[i].userId == null}">
								<button id="follower" class="btn profile-edit-btn-followerid"
									onclick="follower()">팔로우</button>
							</c:if>
						
							<c:if test="${followinglist[i].userId == param.userid}">
								<button id="follower" class="btn profile-edit-btn-followingid"
									onclick="follower()">팔로우</button>
									<c:set var="loop_flag" value="true" />
							</c:if>
					<!--  
							<c:if test="${followinglist[i].userId == sessionScope.usersdto.userId}">
								<button id="follower" class="btn profile-edit-btn-followerid"
									onclick="follower()">팔로윙</button>
							</c:if>
					-->
						</c:if>
					</c:forEach>
						
				<!-- 
					<button class="btn profile-edit-btn" onclick="photoinsert()">Photo</button>
					-->
				</div>

				<div class="profile-stats">

					<ul>
						<li><span class="profile-stat-count">${blogList.size()}</span>
							posts</li>
						<li><span class="profile-stat-count">${requestScope.followerNumber }</span> followers</li>
						<li><span class="profile-stat-count">${requestScope.followingNumber }</span> following</li>

					</ul>

				</div>

				<div class="profile-bio">

					<p>
						<span class="profile-real-name">${usersDto.userName}</span>
						${profileDto.introduction}
					</p>

				</div>

			</div>
			<!-- End of profile section -->

		</div>
		<!-- End of container -->

	</header>

	<!-- INSTAGRAM POPUP -->
	<div class="obscure" id="obscure"></div>


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

							<form action="Instawrite.insta" enctype="Multipart/form-data"
								method="post">
								<div class="mt-10">
									<input type="file" name="file"
										class="single-input-secondary-sub-infor">
								</div>
								<div class="mt-10">
									<input type="text" name="comments" placeholder="내용"
										onfocus="this.placeholder = ''"
										onblur="this.placeholder = '내용'" required=""
										class="single-input-secondary-sub-infor">
								</div>
								<div class="btn-file">
									<button type="submit" class="genric-btn info circle arrow">
										올리기<span class="lnr lnr-arrow-right"></span>
									</button>
								</div>
							</form>
						</div>
					</div>
				</div>
			</div>
		</div>
	</div>

	<!-- Photo Insert End -->


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

								<a class="openBtn" href="#"
									onclick="btnpopup(${blogList[i].photoId});return false;"> <img
									src="./upload/${blogList[i].fileName}" class="gallery-image">

									<div class="gallery-item-info">

										<ul>
											<li class="gallery-item-likes"><span
												class="visually-hidden">Likes:</span><i class="fas fa-heart"
												aria-hidden="true"></i> ${bloglikedto[i].likeCount }</li>
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