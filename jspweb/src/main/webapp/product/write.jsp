<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>

	<style type="text/css">
		.fileDrop{ width:600px; height: 200px; overflow: auto; border: 1px solid red;}
	</style>

</head>
<body>

	<%@include file = "/header.jsp" %>
	<div class="container">
	
		<form class="writeForm">
			제품명 :		<input type="text" name="pname">	<br>
			제품설명 : 	<input type="text" name="pcomment">	<br>
			제품가격 : 	<input type="text" name="pprice">	<br>
			위치 : 		
			<div id="map" style="width:100%;height:350px;"></div>
			
<!-- 			<h5> 첨부파일 여러개(multiple) [ cos.jar 불가능 / commons 가능 ] </h5>
			<input type="file" name="pfiles" multiple="multiple" accept="image/*">			
	 -->		
	 		<!-- 드로그앤드랍 : multiple -->
	 		<div class="fileDrop">
	 			[드로그앤드랍] 여기에 첨부파일을 넣어주세요.
	 		</div>
	 		
			<button onclick="onwrite()" type="button"> 물품 등록 </button>
			
			<!-- 			 
			<h5> 첨부파일 한개 </h5>
			 <input type="file" name="pfile" accept="image/*">
			 
			 <h5> 첨부파일 여러개 </h5>
			 <input type="file" name="pfile1">
			 <input type="file" name="pfile2">
			 <input type="file" name="pfile3"> -->
			 

			 
			 
		</form>
	
	</div>
	
	<!-- 카카오지도 api 사용 js -->
	<script type="text/javascript" src="//dapi.kakao.com/v2/maps/sdk.js?appkey=819ed2e9364a6a018af953c58d792168"></script>
	<!-- 개인 js -->
	<script src="/jspweb/js/product/write.js" type="text/javascript"></script>
</body>
</html>