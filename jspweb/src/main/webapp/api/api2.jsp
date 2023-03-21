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
	
	<div class="container">
		
		<div id="clickLatlng"></div> <!-- 클릭한 위치에 좌표 알기 -->
		
		<div id="map" style="width:900px;height:700px;"></div>
	</div>
	
	<!-- 카카오지도에 필요한 메소드가 저장된 js , src="~~~appkey= [ 인증키 코드 입력 자리 ]"-->
	<script type="text/javascript" src="//dapi.kakao.com/v2/maps/sdk.js?appkey=819ed2e9364a6a018af953c58d792168"></script>
	<script src="/jspweb/js/api/api2.js" type="text/javascript"></script>
	
</body>
</html>