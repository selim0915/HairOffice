<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
	<jsp:include page="/WEB-INF/common/header.jsp"></jsp:include>
	<script src="https://maps.googleapis.com/maps/api/js?key=AIzaSyCl2Ol3aZj1BeaWTh0oFJGO3xvEqijRPBA&callback=initMap" 
	async defer></script>
	<script type="text/javascript">
     function initialize() {
   var locations = [
     ['<div id="content">'+
         '<div id="siteNotice">'+
         '</div>'+
         '<h1 id="firstHeading" class="firstHeading">청담 1호점</h1>'+
         
         '<img src="img/branch/building3.jpg">'+
         
         '<div id="bodyContent">'+
         '<p>서울시 강남구 청담동 도산대로 87길</p>'+
         '<p>02-552-0088</p>'+
         '</div>'+
         '</div>', 37.526087, 127.049962, 3],
     ['<div id="content">'+
         '<div id="siteNotice">'+
         '</div>'+
         '<h1 id="firstHeading" class="firstHeading">역삼1호점</h1>'+
         
         '<img src="img/branch/building2.jpg">'+
         
         '<div id="bodyContent">'+
         '<p>서울특별시 강남구 역삼동 680</p>'+
         '<p>02-524-0088</p>'+
         '</div>'+
         '</div>', 37.508402, 127.039248, 2],
     ['<div id="content">'+
         '<div id="siteNotice">'+
         '</div>'+
         '<h1 id="firstHeading" class="firstHeading">강남 1호점</h1>'+
         
         '<img src="img/branch/building1.jpg">'+
         
         '<div id="bodyContent">'+
         '<p>서울특별시 서초구 서초동 1306-3</p>'+
         '<p>02-553-2244</p>'+
         '</div>'+
         '</div>',37.500242, 127.026049, 1],
   ];

   var map = new google.maps.Map(document.getElementById('google-map'), {
     zoom: 12,
     center: new google.maps.LatLng(37.550925, 126.990945), 
     mapTypeId: google.maps.MapTypeId.ROADMAP
   });

   var infowindow = new google.maps.InfoWindow();

   var marker, i;

   for (i = 0; i < locations.length; i++) { 
	 var image = {
			    url: 'https://developers.google.com/maps/documentation/javascript/examples/full/images/beachflag.png',
			    // This marker is 20 pixels wide by 32 pixels high.
			    size: new google.maps.Size(20, 32),
			    // The origin for this image is (0, 0).
			    origin: new google.maps.Point(0, 0),
			    // The anchor for this image is the base of the flagpole at (0, 32).
			    anchor: new google.maps.Point(0, 32)
			  };
     marker = new google.maps.Marker({
     position: new google.maps.LatLng(locations[i][1], locations[i][2]),
     map: map,
     icon: image
     });

     google.maps.event.addListener(marker, 'click', (function(marker, i) {
       return function() {
         infowindow.setContent(locations[i][0]);
         infowindow.open(map, marker);
       }
     })(marker, i));
   }
     }

     function loadScript() {
       var script = document.createElement('script');
       script.type = 'text/javascript';
       script.src = 'https://maps.googleapis.com/maps/api/js?key=AIzaSyCl2Ol3aZj1BeaWTh0oFJGO3xvEqijRPBA&v=3.exp&sensor=false&' + 'callback=initialize';
       document.body.appendChild(script);
     }

     window.onload = loadScript;
   </script>
   
   	<div class="container">
		<div class="row">
		<div class="top-title">
		<span class="title-name">서울</span>

			</div>
			</div>
		</div>
   
   
	<div class="container">
		<div class="row">
<div id="google-map"></div>

			</div>
		</div>
	
	<div class="container">
		<div class="row">
		<div class="sub-title">
		<h1>서울 HairOffice 지점</h1>
		</div>
		</div>
	</div>
	
	<section class="causes-area section-gap">
	<div class="container">
	<div class="row">
	<div class="card-deck">

  <!-- Card -->
  <div class="card mb-4">

    <!--Card image-->
    <div class="view overlay">
      <img class="card-img-top" src="img/branch/building1.jpg" alt="Card image cap" height="600" width="100">
      <a href="seoulgangnam.jsp">
        <div class="mask rgba-white-slight"></div>
      </a>
    </div>

    <!--Card content-->
    <div class="card-body">

      <!--Title-->
      <h2 class="card-title">강남 1호점</h2>
      <!--Text-->

      <p class="default-price">기본요금:</p>
      <div class="card-text">
      <div class="card-text-left">
      프라이빗 오피스
      </div>
            <div class="card-text-right">
      600,000/월
      </div>
      </div>
      <div class="card-text">
      <div class="card-text-left">
      전용 데스크
      </div>
            <div class="card-text-right">
      450,000/월
      </div>
      </div>
      <div class="card-text">
      <div class="card-text-left">
      핫 데스크
      </div>
            <div class="card-text-right">
      400,000/월
      </div>
      </div>
      <!-- Provides extra visual weight and identifies the primary action in a set of buttons -->
      <div>
      <button type="button" class="genric-btn primary_sub radius" onclick="location.href='GangnamBranchInfo.brh'">지점 둘러보기</button>
      </div>

    </div>

  </div>
  
  <!-- Card -->

  <!-- Card -->
  <div class="card mb-4">

    <!--Card image-->
    <div class="view overlay">
      <img class="card-img-top" src="img/branch/building2.jpg" alt="Card image cap" height="600" width="100">
      <a href="seoulgangnam.jsp">
        <div class="mask rgba-white-slight"></div>
      </a>
    </div>

    <!--Card content-->
    <div class="card-body">

      <!--Title-->
      <h2 class="card-title">역삼 1호점</h2>
      <!--Text-->

      <p class="default-price">기본요금:</p>
      <div class="card-text">
      <div class="card-text-left">
      프라이빗 오피스
      </div>
            <div class="card-text-right">
      600,000/월
      </div>
      </div>
      <div class="card-text">
      <div class="card-text-left">
      전용 데스크
      </div>
            <div class="card-text-right">
      450,000/월
      </div>
      </div>
      <div class="card-text">
      <div class="card-text-left">
      핫 데스크
      </div>
            <div class="card-text-right">
      400,000/월
      </div>
      </div>
      <!-- Provides extra visual weight and identifies the primary action in a set of buttons -->
      <div>
      <button type="button" class="genric-btn primary_sub radius" onclick="location.href='GangnamBranchInfo.brh'">지점 둘러보기</button>
      </div>

    </div>

  </div>
  <!-- Card -->

  <!-- Card -->
  <div class="card mb-4">

    <!--Card image-->
    <div class="view overlay">
      <img class="card-img-top" src="img/branch/building3.jpg" alt="Card image cap" height="600" width="100">
      <a href="seoulgangnam.jsp">
        <div class="mask rgba-white-slight"></div>
      </a>
    </div>

    <!--Card content-->
    <div class="card-body">

      <!--Title-->
      <h2 class="card-title">청담 1호점</h2>
      <!--Text-->

      <p class="default-price">기본요금:</p>
      <div class="card-text">
      <div class="card-text-left">
      프라이빗 오피스
      </div>
            <div class="card-text-right">
      600,000/월
      </div>
      </div>
      <div class="card-text">
      <div class="card-text-left">
      전용 데스크
      </div>
            <div class="card-text-right">
      450,000/월
      </div>
      </div>
      <div class="card-text">
      <div class="card-text-left">
      핫 데스크
      </div>
            <div class="card-text-right">
      400,000/월
      </div>
      </div>
      <!-- Provides extra visual weight and identifies the primary action in a set of buttons -->
      <div>
      <button type="button" class="genric-btn primary_sub radius" onclick="location.href='GangnamBranchInfo.brh'">지점 둘러보기</button>
      </div>

    </div>

  </div>
  </div>
  
  <div class="card-deck">

</div>
</div>
</div>

	<jsp:include page="/WEB-INF/common/footer.jsp"></jsp:include>

</html>
	