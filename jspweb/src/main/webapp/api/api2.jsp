<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>

	<%@include file = "/header.jsp" %>

	<!-- 모달 HTML -->
	<div class="modal_wrap">
		<div class="modal_box">
			<h3 class="modal_title">
				<!-- 제목이 들어가는 자리  -->
			</h3>
			<div class="modal_content">
				<!-- 내용이 들어가는 자리  -->
			</div>
			<div class="modal_btns">
				<button class="modal_check" 	type="button">확인</button>
				<button onclick="closeModal()" class="modal_cencel" type="button">닫기</button>
			</div>
		</div>
	</div>
	
	<div class="container">
		
		<div id="clickLatlng"></div> <!-- 클릭한 위치에 좌표 알기 -->
		
		<div id="map" style="width:900px;height:700px;"></div>
	</div>
	
	<!-- 카카오지도에 필요한 메소드가 저장된 js , src="~~~appkey= [ 인증키 코드 입력 자리 ]"-->
	<script type="text/javascript" src="//dapi.kakao.com/v2/maps/sdk.js?appkey=819ed2e9364a6a018af953c58d792168&libraries=clusterer"></script>
	<script src="/jspweb/js/api/api2.js" type="text/javascript"></script>
	
</body>
</html>