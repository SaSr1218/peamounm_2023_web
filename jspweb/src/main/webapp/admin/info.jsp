<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<%@ include file="../header.jsp" %>
	


	<h3> 모든 회원 명단</h3>

	<div class="container">
		
		현재 회원 페이지 번호 : <span class="now_page"> </span>
		<div class="msearchcount"> 총 회원 수 : </div>
		
		<select onchange="setlistsize()" class="listsize">
			<option> 3 </option>
			<option> 5 </option>
			<option> 10 </option>
		</select>
		
		
		
		<table class="mListTable" border="1"></table>
		
		
		<!-- 버튼 -->
		<div class="pagebox"> </div>
		
		<!-- 검색창 -->
		<div>
			<select class="key">
				<option value="m.mno"> 번호 </option>
				<option value="m.mid"> 아이디 </option>
				<option value="m.memail"> 이메일 </option>
			
			</select>
			
			<input class="keyword" type="text">
			
			<button onclick="getmember()" type="button"> 검색 </button>
			
		</div>			
	
	</div>

	
	
	
	<script src="/jspweb/js/admin/info.js" type="text/javascript"></script>

</body>
</html>