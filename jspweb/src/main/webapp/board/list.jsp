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

	<%
		// 1. HTTP GET <a href="URL경로?변수명=값"> 전달된 매개변수 가져오기
		String cno = request.getParameter("cno");
		// 2. 표현식을 이용한 input , div 등등 대입
	%>
	<!-- cno 숨겨서 js에게 전달 -->
	<input class="cno" value ="<%=cno%>" type="hidden">

	<div class="container">
	
		<h3 class="cname"> </h3>
		
		<a href="write.jsp"> <h6> 글쓰기 </h6> </a>
		
		<div>
			현재 페이지 번호 : <span class="now_page"> </span>
		</div>
		
		<table class="boardTable table table-hover">
		
		</table>
		
		<!-- 페이징처리 버튼틀 -->
		<div class="pagebox"></div>
		
		<!-- 검색창 -->
				<!-- 
					select * from board where 필드명 = 데이터
					select * from board where bno = 1;
				
				 -->
		<div>
			<select class="key">
				<option value="b.btitle"> 제목 </option>
				<option value="b.bcontent"> 내용 </option>
				<option value="m.mid"> 작성자 </option>
			</select>
			
			<input class="keyword" type="text">
			
			<button onclick="getsearch()" type="button"> 검색 </button>
		</div>
	</div>

	<script src="/jspweb/js/board/list.js" type="text/javascript"></script>

</body>
</html>