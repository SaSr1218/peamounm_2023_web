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
	
		<form class="writeForm">
			제품명 :		<input type="text" name="pname">	<br>
			제품설명 : 	<input type="text" name="pcomment">	<br>
			제품가격 : 	<input type="text" name="pprice">	<br>
			위치 : 		<input type="text" name="p"> 
			<div id="map" style="width:100%;height:350px;"></div>
			<button onclick="onwrite()" type="button"> 물품 등록 </button>
			 
		</form>
	
	</div>
	
	<!-- 카카오지도 api 사용 js -->
	<script type="text/javascript" src="//dapi.kakao.com/v2/maps/sdk.js?appkey=819ed2e9364a6a018af953c58d792168"></script>
	<!-- 개인 js -->
	<script src="/jspweb/js/product/write.js" type="text/javascript"></script>
</body>
</html>