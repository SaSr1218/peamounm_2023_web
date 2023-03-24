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
			<h3> java community</h3>
			
			<div class="title">아이디</div>
			<input onkeyup="idcheck()" maxlength="30" type="text" name="mid" 	class="mid" >			
			
			<div class="checkconfirm"></div>
				
			<div class="title">비밀번호</div>
			<input onkeyup="pwdcheck()" 		maxlength="20"	type="password" name="mpwd" 		class="mpwd" >		
			
			<div class="checkconfirm"></div>
			
			<div class="subbtnbox">
				<a href = "/jspweb/member/findid.jsp"> 아이디 찾기 </a>
				<a href = "/jspweb/member/findpwd.jsp"> 비밀번호 찾기 </a>
			</div>
			
			<button class="signupbtn" onclick="login()" type="button"> 로그인 </button>
		</form>
		
	</div>


	<!-- 정의 js -->
	<script src="/jspweb/js/member/login.js" type="text/javascript"></script>
</body>
</html>