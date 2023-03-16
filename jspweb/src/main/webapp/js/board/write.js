// 경로 타고 들어올 시 로그인 하라고 명령
if ( memberInfo.mid == null ){
	alert('회원제 기능입니다. 로그인 후 작성해주세요.'); location.href="/jspweb/member/login.jsp";
}

// 1. 게시물 글쓰기 함수
function bwrite(){
	let writeForm = document.querySelectorAll('.writeForm')[0];
	let writeFormData = new FormData ( writeForm );
	
	$.ajax({
		url : "/jspweb/board/info" ,
		method : "post" ,
		data : writeFormData ,
		contentType : false ,
		processData : false ,
		success : (r) => {
			if ( r == 'true'){ alert('글쓰기 성공'); }
			else{ alert('글쓰기 실패'); }
		}
	})// ajax end
} // bwrite end