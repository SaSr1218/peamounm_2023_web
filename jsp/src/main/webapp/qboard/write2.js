console.log('작동확인')


let 게시물임시저장소 = []

// 1. 글 등록 버튼을 클릭했을때 함수
let result = true;

function qboard_write(){
	// 1. 입력받은 데이터 가져온다.
	let board = {
		qtitle : document.querySelector('.qtitle').value ,
		qcontent : document.querySelector('.qcontent').value ,
		qwriter : document.querySelector('.qwriter').value ,
		qpassword : document.querySelector('.qpassword').value 
		// 첨부파일 X
	}; console.log( board )
	
	// [첨부파일 있을경우]
	// 1. 입력양식 객체화 가져오기
	let writeform = document.querySelector('.writeform');
	let formdata = new FormData( writeform ); console.log( formdata )
	
	// 2. JAVA에게 데이터를 전송한다. [ 통신 - AJAX 등 ] 생략
	
	게시물임시저장소.push( board )
	// 3. 결과에 따른 이벤트
	let result = true; // java로부터 전송결과
	if ( result ) {alert('글쓰기 성공'); location.href = 'list2.html';}
	else{ alert('글쓰기 실패');}
	/* 
		페이지 전환 함수
		location.href = '경로';
	 */
}

$(document).ready(function() {
  $('#summernote').summernote( {
	   height : 300, 
	   lang: 'ko-KR' 
  });
});