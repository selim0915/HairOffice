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
	
	<script type="text/javascript">
	
		function btnpopup() {
			
						setTimeout(function() {
							$('.popup').removeClass('animationClose').addClass(
									'animationOpen');
						}, 100);
						
						$('.obscure').fadeIn(50);
						
						/* var sessionid = "${sessionScope.usersdto.userId}"; //var sessionid = ''
							$.ajax({
								url : "Instalike.insta",
								data : {
									photoid : "1",
									userid : sessionid,
									likeyn : "n",
									wasuser : "y"
								},
								type : "GET",
								dataType : "json",
								success : function() {
									}
							});
						console.log("${sessionScope.likesdto.likeYn}"); */
						$.ajax({
							url : "instapopup.jsp",
							type : "GET",
							dataType : "text",
							success : function(data) {
								console.log(data);
								$('#obscure').html(data);
							}
						})
		}
		
		function btnclose() {
			setTimeout(function() {
				$('.obscure').fadeOut(350);
			}, 50);
			$('.popup').removeClass('animationOpen').addClass(
					'animationClose');
		}
	
		function like() {
			var sessionlike = "${sessionScope.likesdto.likeYn}";
			console.log("섹션 " + sessionlike);
			if(sessionlike == 'n'){
				$.ajax({
					url : "InstalikeYn.insta",
					data : {
						likeyn : "y"
					},
					type : "GET",
					dataType : "json",
					success : function() {
						$('#test').removeClass('far fa-heart');
						$('#test').addClass('fas fa-heart');
					}
				});
			}else if(sessionlike == 'y'){
				$.ajax({
					url : "InstalikeYn.insta",
					data : {
						likeyn : "n"
					},
					type : "GET",
					dataType : "json",
					success : function() {
						$('#test').removeClass('fas fa-heart');
						$('#test').addClass('far fa-heart');
					}
				});
			}
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

				<h1 class="profile-user-name">janedoe_</h1>

				<button class="btn profile-edit-btn">Edit Profile</button>

				<button class="btn profile-settings-btn" aria-label="profile settings"><i class="fas fa-cog" aria-hidden="true"></i></button>

			</div>

			<div class="profile-stats">

				<ul>
					<li><span class="profile-stat-count">164</span> posts</li>
					<li><span class="profile-stat-count">188</span> followers</li>
					<li><span class="profile-stat-count">206</span> following</li>
				</ul>

			</div>

			<div class="profile-bio">

				<p><span class="profile-real-name">Jane Doe</span> Lorem ipsum dolor sit, amet consectetur adipisicing elit 📷✈️🏕️</p>

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

			<div class="gallery-item" tabindex="0">
				<div>
			    
			<a class="openBtn" href="#" onclick="btnpopup()">

				<img src="https://images.unsplash.com/photo-1511765224389-37f0e77cf0eb?w=500&h=500&fit=crop" class="gallery-image">

				<div class="gallery-item-info">

					<ul>
						<li class="gallery-item-likes"><span class="visually-hidden">Likes:</span><i class="fas fa-heart" aria-hidden="true"></i> 56</li>
						<li class="gallery-item-comments"><span class="visually-hidden">Comments:</span><i class="fas fa-comment" aria-hidden="true"></i> 2</li>
					</ul>
				</div>
				</a>
				</div>
			</div>
			<div class="gallery-item" tabindex="0">
				<div>
			    
			<a class="openBtn" href="#" onclick="btnpopup()">

				<img src="https://images.unsplash.com/photo-1511765224389-37f0e77cf0eb?w=500&h=500&fit=crop" class="gallery-image">

				<div class="gallery-item-info">

					<ul>
						<li class="gallery-item-likes"><span class="visually-hidden">Likes:</span><i class="fas fa-heart" aria-hidden="true"></i> 56</li>
						<li class="gallery-item-comments"><span class="visually-hidden">Comments:</span><i class="fas fa-comment" aria-hidden="true"></i> 2</li>
					</ul>
				</div>
				</a>
				</div>
			</div>
			<div class="gallery-item" tabindex="0">
				<div>
			    
			<a class="openBtn" href="#" onclick="btnpopup()">

				<img src="https://images.unsplash.com/photo-1511765224389-37f0e77cf0eb?w=500&h=500&fit=crop" class="gallery-image">

				<div class="gallery-item-info">

					<ul>
						<li class="gallery-item-likes"><span class="visually-hidden">Likes:</span><i class="fas fa-heart" aria-hidden="true"></i> 56</li>
						<li class="gallery-item-comments"><span class="visually-hidden">Comments:</span><i class="fas fa-comment" aria-hidden="true"></i> 2</li>
					</ul>
				</div>
				</a>
				</div>
			</div>
		</div>
		<!-- End of gallery -->

		<div class="loader"></div>

	</div>
	<!-- End of container -->

	
	<jsp:include page="/WEB-INF/common/footer.jsp"></jsp:include>
