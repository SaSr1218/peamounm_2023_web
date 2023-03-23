<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>

	<link href="/jspweb/css/chatting.css" rel="stylesheet">

</head>
<body>

	<%@include file = "/header.jsp" %>
	<div class="container">
	
		<div class="chattingbox"> <!-- 채팅 구역 -->
			
				<div class="contentbox"> <!-- 채팅창 -->
					
					<!-- 보낼 때 -->

					<!--  알람 -->

					<!--  받을때 [ 프로필 , 시간 , 내용 -->

				</div>
				
				<!-- form-control msgbox : bs -->
				<textarea onkeyup="enterkey()" class="msgbox" rows="" cols=""></textarea>
				
				<button onclick="보내기()" class="sendbtn" type="button"> 보내기 </button>
					
		</div>
			
							
		</div>
	
	
	<script src="/jspweb/js/board/chatting.js" type="text/javascript"></script>
	
</body>
</html>