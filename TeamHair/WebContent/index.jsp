<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="zxx" class="no-js">
	
	<!-- Header Include Start -->
	<jsp:include page="/WEB-INF/common/header.jsp"></jsp:include>
	<!-- Header Include End -->
	
	<!-- start Banner Area -->
	<section class="home-banner-area relative">
		<div class="container-fluid">
			<div class="row  d-flex align-items-center justify-content-center">
				<div class="col-lg-9 col-md-6 col-sm-8 header-right">
					<div class="owl-carousel owl-banner">
						<img class="img-main w-100" src="img/salone/shop1.jpg" alt="">
						<img class="img-main w-100" src="img/salone/shop2.jpg" alt="">
						<img class="img-main w-100" src="img/salone/shop3.jpg" alt="">
					</div>
				</div>
			</div>
		</div>
	</section>
<section>
	<div class="container">
		<div class="row align-items-center justify-content-center">
				<form action="SearchSpaceType.brh">
			<div class="header-content">
				<div class="form-wrap">
				
				<div>
					<h2>Hair의 격을 높이는 공간, 공유미용실</h2>
					
					<div class="single-element-widget mt-30">
						<div class="default-select" id="default-select">
						<span>어디에 업무공간이 필요하신가요?</span>
							<select name="branchid">
								<option value="100" selected>강남</option>
								<option value="200">역삼</option>
								<option value="300">청담</option>
							</select>
						</div>
					</div>
				</div>
				
				<div class="single-element-widget mt-30">
						<div class="default-select" id="default-select">
						<span>인원수</span>
							<select name="spacetype">
								<option value="01" selected>1명</option>
								<option value="03">2-3명</option>
								<option value="07">4-7명</option>
								<option value="14">8-14명 이상</option>
								<option value="15">15-30명 이상</option>
							</select>
						</div>

					</div>
					<div class="btn-start">
					<button type="submit" class="genric-btn info">시작</button>						
					</div>
					
				</div>
			</div>
					</form>
		</div>
	</div>
</section>

<!-- End Banner Area -->

	<!-- Start Condition Area -->
	<section class="condition-area event-details-area section-gap">
		<div class="container maincontainer">
			<div class="row align-items-center justify-content-center">
				<div class="col-lg-6 col-md-8 col-sm-10">
					<div class="condition-left">
						<img class="img-mid" src="img/main1.jpg" alt="">
					</div>
				</div>
				<div class="col-lg-5 mainContent1">
					<div class="condition-right mainContentTitle1">
						<h2>
							헤어 매니지먼트 공간을 공유하세요.<br>
						</h2>
						<p>
							<ul>
								<li>디자이너의 공간을 자유롭게</li>
								비즈니스를 변화시키는 힘을 가진 공간—사람과 조직의 잠재력을 확인하세요. 
								팀이 최고의 업무를 해낼 수 있게 해주는 디자인, 따뜻한 인간미 및 유연한 
								솔루션을 모두 갖추고 있습니다.  
								<li>투자비용과 리스크 감소</li>
								초기투자비용 부담을 줄이고 좋은 입지 선정<br>
								<a href="seoul_office.jsp">전체 지점 보기</a>
							</ul>
						</p>
					</div>
				</div>
			</div>
		</div>
		
		<div class="container maincontainer">
			<div class="row align-items-center justify-content-center">
				<div class="col-lg-5 mainContent1">
					<div class="condition-right mainContentTitle2">
						<h2>
							블로그를 이용하여 자신을 홍보하세요.<br>
						</h2>
						<p>
							<ul>
								<li>디자이너 블로그 주소 무료 지원</li>
								<li>회원들과 다양한 소통 가능</li><br>
								<a href="/insta/instragram.jsp">블로그 보기</a>
							</ul>
						</p>
					</div>
				</div>
				<div class="col-lg-6 col-md-8 col-sm-10">
					<div class="condition-left">
						<img class="img-mid2" src="img/main2.jpg" alt="">
					</div>
				</div>
			</div>
		</div>
	</section>
	<!-- End Condition Area -->

	<!-- Start Causes Area -->
	 <section class="causes-area section-gap">
		<div class="container maincontainer">
			<div class="row justify-content-center">
				<div class="col-md-7 section-title session3title">
					<h2>모든 규모의 팀에 적합한 공간</h2>
				</div>
			</div>
			<div class="row">
				<div class="col-lg-4 col-md-6">
					<div class="single-cause">
						<div class="top">
							<div class="thumb">
								<img class="c-img img-fluid" src="img/causes/c1.jpg" alt="">
							</div>
							<a href="#">
								<h3>강남점</h3>
							</a>
							<p class="text">
								- 공용 회의실 및 공용 라운지 제공<br> 
								- 원하는 좌석을 예약 가능
							</p>
						</div>
						<div class="bottom d-flex justify-content-between align-items-center flex-wrap">
							<a href="GangnamOfficeInfo.brh" class="primary-btn offwhite">보러가기</a>
						</div>
					</div>
				</div>

				<div class="col-lg-4 col-md-6">
					<div class="single-cause">
						<div class="top">
							<div class="thumb">
								<img class="c-img img-fluid" src="img/causes/c2.jpg" alt="">
							</div>
							<a href="#">
								<h3>역삼점</h3>
							</a>
							<p class="text">
								- 공용 회의실 및 공용 라운지 제공<br> 
								- 장기 대여 가능
							</p>
						</div>
						<div class="bottom d-flex justify-content-between align-items-center flex-wrap">
							<a href="#" class="primary-btn offwhite">보러가기</a>
						</div>
					</div>
				</div>

				<div class="col-lg-4 col-md-6">
					<div class="single-cause">
						<div class="top">
							<div class="thumb">
								<img class="c-img img-fluid" src="img/causes/c3.jpg" alt="">
							</div>
							<a href="#">
								<h3>청담점</h3>
							</a>
							<p class="text">
								- 공용 데스크 이용 가능<br> 
								- 원하는 좌석을 예약 가능
							</p>
						</div>
						<div class="bottom d-flex justify-content-between align-items-center flex-wrap">
							<a href="#" class="primary-btn offwhite">보러가기</a>
						</div>
					</div>
				</div>
			</div>
		</div>
	</section>
	
	<jsp:include page="/WEB-INF/common/footer.jsp"></jsp:include>
