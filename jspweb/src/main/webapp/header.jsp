<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>

	<!-- 모든 페이지에서 공통 css 사용 -->

</head>
<body>
	
	<h3> 헤더입니다. </h3>	<!-- 절대 경로 사용해야함! -->
	<!-- 모든페이지 공통 메뉴 -->
	<% String login = (String)request.getSession().getAttribute("login"); %>
	
	<a href="/jspweb/index.jsp">홈</a>
	<%
		if ( login == null ){ // 로그인 안했다.
	%>		
			<a href="/jspweb/member/signup.jsp">회원가입</a>
			<a href="/jspweb/member/login.jsp">로그인</a>
	<% 		
		}else if ( login.equals("admin") ){
	%>
			관리자 모드
			<a href="/jspweb/admin/info.jsp">관리자페이지</a>
	<%		
		}else{
	%>
			<%=login %>님 안녕하세요
			<a href="/jspweb/member/logout.jsp">로그아웃</a>
	<%	
		}
	%>

	
	<!-- 모든 페이지에서 공통 js 사용 -->
	<script  src="http://code.jquery.com/jquery-latest.min.js"></script>
	
</body>
</html>