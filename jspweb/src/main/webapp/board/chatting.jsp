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
					<div class="secontent">
						<div class="date"> 오전 10:07 </div>
						<div class="content"> 안녕하세요 </div>
					</div>
					
					<!--  알람 -->
					<div class="alarm">
						<span> 최성아님이 입장 하셨습니다. </span>
					</div>
					
					<!--  받을때 [ 프로필 , 시간 , 내용 -->
					<div>
						<span><img src="/jspweb/member/mimg/default.webp" class="hpimg"></span>
						<div>
							<div> 최성아 </div>
							<div> ㅎ2 </div>
							<div> 오전 10:10</div>
						</div>
					</div>
				</div>
				
				<!-- form-control msgbox : bs -->
				<textarea class="msgbox" rows="" cols=""></textarea>
				
					<button onclick="보내기()" class="sendbtn" type="button"> 보내기 </button>
					
				</div>
				
		</div>
	
	
	<script src="/jspweb/js/board/chatting.js" type="text/javascript"></script>
	
</body>
</html>