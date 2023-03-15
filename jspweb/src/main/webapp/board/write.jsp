<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>

 
	<%
		// jsp로 페이지 막기
		Object o = request.getSession().getAttribute("login");
		if ( o == null ) { response.sendRedirect("/jspweb/member/login.jsp"); }
	%>

	<%@ include file = "/header.jsp" %>


<!--  jsp에서 js 로 유효성 검사( 페이지가 아예 보이지 않게 하기 위함 )
	<script type="text/javascript"> 
	
		if ( memberInfo.mid == null ){
			alert('회원제 기능입니다. 로그인 후 작성해주세요.'); 
			location.href="/jspweb/member/login.jsp";}
	</script>
-->

	<div class="container">
		<h3> 글쓰기 </h3>
		<form class="writeForm">
			카테고리  : <select name = "cno">
						<option value = "1"> 공지사항 </option>
						<option value = "2"> 커뮤니티 </option>
						<option value = "3"> QnA </option>
						<option value = "4"> 노하우 </option>
					</select> <br>
			제목 : <input name="btitle" type="text"> <br>
			내용 : <textarea name="bcontent" rows="6" cols="15"> </textarea> <br>
			첨부파일 : <input name="bfile" type="file"> <br>
			<button onclick="bwrite()" type="button"> 글 등록 </button>
		</form>
	</div>

	<script src="/jspweb/js/board/write.js" type="text/javascript"></script>

</body>
</html>