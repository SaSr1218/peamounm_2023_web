<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>

	<%@ include file = "/header.jsp" %>
	
	<div class="container">
	
		<h3> 회원수정 </h3>
		<form class="updateForm">
			<div>
				<div>
					<img width="70px;" class="mimg" alt="" src="/jspweb/member/mimg/default.webp"> <br>				
					프로필변경 : <input type="file" name="newmimg"> <br>
					<input class="defaultimg" type="checkbox"> 기본프로필 사용
					
				</div>
				<div>
					<div> 아이디 </div>
					<div class="mid">  </div>
				</div>
				<div>
					<div> 현재비밀번호 </div>
					<input type="password" class="mpwd" name="mpwd">
				</div>
				<div>
					<div> 새 비밀번호 </div>
					<input type="password" class="newmpwd" name="newmpwd">
				</div>
				
				<div>
					<div> 새 비밀번호 확인 </div>
					<input type="password" class="newmpwdconfirm">
				</div>
	
				<div>
					<div> 이메일 </div>
					<input class="memail" name="newmemail">
					<button type="button"> 인증 </button>
				</div>
				<button onclick="setUpdate()" type="button"> 회원 수정 </button>
			</div>
		</form>
	</div>
	
	<script src="/jspweb/js/member/update.js" type="text/javascript"></script>

</body>
</html>