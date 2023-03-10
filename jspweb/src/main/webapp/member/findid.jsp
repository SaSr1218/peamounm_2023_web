<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>

	<link href="/jspweb/css/signup.css" rel="stylesheet">

</head>
<body>

	<!-- 헤더 -->
	<%@ include file = "/header.jsp" %>

		<div class="container">
	
		<form class="signupForm">	<!-- 폼 전송시 자식 input의 매개변수 식별 : name -->
			<h3> 아이디 찾기 </h3>
			
			<div class="title">이메일</div>
			<input type="text" name="memail" 	class="memail" >			
			
			<div class="checkconfirm"></div>

			<button class="signupbtn" onclick="findid()" type="button"> 아이디 찾기 </button>
		</form>
		
	</div>

	<!-- 정의 js -->
	<script src="/jspweb/js/member/login.js" type="text/javascript"></script>
</body>
</html>