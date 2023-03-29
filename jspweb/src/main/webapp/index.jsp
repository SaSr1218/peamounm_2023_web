<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html style="height : 100%">	<!-- 가로/세로 사이즈 생략시 auto(내용물크기만큼 사용) -->
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body style="height : 100%">
	<%@ include file = "/header.jsp" %>	<!-- JSP 페이지 포함 -->
	
	<!-- 모달창 -->
	<div class="searchbox">
		검색창
	</div>
	
	<div class="contentbox" >
		<!-- 지도 -->
		<div id="map" style="width:75%;height:100%;"></div>
		<!-- 사이드바 -->
		<div class="produclistbox">
		

					
		</div>
		
	</div>
	
	<script type="text/javascript" src="//dapi.kakao.com/v2/maps/sdk.js?appkey=b7c0acb1395b016fc6b2661dad73840f&libraries=clusterer"></script>
	<script src="/jspweb/js/index.js" type="text/javascript"></script>
</body>
</html>


<!-- 			<div class="pviewbox">
				
				<div class="pviewinfo">
					<div class="mimgbox">
						<img src="/jspweb/member/mimg/default.webp" class="hpimg">
						qwe1234
					</div>
					<div>
						<button class="pbadge" type="button"> 목록보기 </button>
					</div>
				</div>
			
				이미지 캐러셀 : 이미지 슬라이드 부트스트랩
				<div id="carouselExampleControls" class="carousel slide" data-bs-ride="carousel">
				  <div class="carousel-inner">
				    <div class="carousel-item active">
				      <img src="/jspweb/product/pimg/노트북.jpeg" class="d-block w-100" alt="...">
				    </div>
				    <div class="carousel-item">
				      <img src="/jspweb/product/pimg/노트북.jpeg" class="d-block w-100" alt="...">
				    </div>
				    <div class="carousel-item">
				      <img src="/jspweb/product/pimg/노트북.jpeg" class="d-block w-100" alt="...">
				    </div>
				  </div>
				  <button class="carousel-control-prev" type="button" data-bs-target="#carouselExampleControls" data-bs-slide="prev">
				    <span class="carousel-control-prev-icon" aria-hidden="true"></span>
				    <span class="visually-hidden">Previous</span>
				  </button>
				  <button class="carousel-control-next" type="button" data-bs-target="#carouselExampleControls" data-bs-slide="next">
				    <span class="carousel-control-next-icon" aria-hidden="true"></span>
				    <span class="visually-hidden">Next</span>
				  </button>
				</div>
				
				제품 상세 내용물
				<div class="pdate"> 2023-03-20 </div>
				<div class="pname"> 노트북 </div>
				<div class="pcomment"> 놋북임다놋북임다놋북임다 </div>
				<div class="pstate"> <span class="pbadge"> 판매중 </span> </div>
				<div class="pprice"> 3000 </div>
				<div class="petc"> 
					<i class="far fa-eye"></i> 30
					<i class="far fa-thumbs-up"></i> 5
					<i class="far fa-comment-dots"></i> 2
				
				</div>
				<div class="pviewbtnbox">
				 	<button type="button"><i class="far fa-heart"></i> </button>
				 	<button type="button"> 채팅 </button>
				 </div>
				
			</div> -->
 