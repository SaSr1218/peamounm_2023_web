
console.log(memberInfo)
if ( memberInfo.mid == null ){
	document.querySelector('.rcontent').disabled = true;
	document.querySelector('.rcontent').disabled = `로그인 후 작성가능합니다.`;
	document.querySelector('.rwritebtn').disabled = true;
	
}

// 현재 보고 있는 게시물 번호
let bno = document.querySelector('.bno').innerHTML;


// 1. 개별 게시물 출력
getBoard();
function getBoard(){
	
	
	$.ajax({
		url : "/jspweb/board/info" ,
		method : "get" ,
		data : { "type" : 2 , "bno" : bno } , // 2 : 개별출력
		success : (r) => {
			let html = `${r.bdate} /
						${r.bview} / 
						<button onclick="bIncrease(2)">${r.bgood} </button> /
						<button onclick="bIncrease(3)">${r.bbad}  </button>`
			
			document.querySelector('.infobox').innerHTML = html;
			document.querySelector('.pimgbox').innerHTML = r.mid;
			document.querySelector('.btitle').innerHTML = r.btitle;
			document.querySelector('.bcontent').innerHTML = r.bcontent;
			
			if ( r.bfile == null ) {
				document.querySelector('.bfile').innerHTML = '첨부파일없음';
			}else{ // 인수 넘기고자 할때 ' ' 로 문자 처리 해주기( . 이 JS에서 접근연산자라서 )
				html = `${r.bfile}<button onclick="bdownload( '${ r.bfile }' )" type="button"> 다운로드 </button>`
					// <a href="/jspweb/filedownload?bfile=${r.bfile}"> 다운로드 </a> 이렇게 보내도 됨!
				document.querySelector('.bfile').innerHTML = html;
			}
			
			//-----------로그인된 회원과 작성자가 일치하면 삭제/수정 버튼 출력-----------//
			if( memberInfo.mid == r.mid ){
				html = `
						<button onclick="bdelete(${bno}  , ${r.cno} )" type="button"> 삭제 </button>
						<button onclick="bupdate(${bno})" type="button"> 수정 </button>						
						`;
						document.querySelector('.btnbox').innerHTML = html;
			}
			getReplyList();
		}
	}) // ajax end
	
} // 개별 게시물 출력 end

// 2. 다운로드 [ 다운로드할 파일명 ]
function bdownload( bfile ){
	/*
	$.ajax({
		url : "/jspweb/filedownload" ,
		method : "get" ,
		data : { "bfile" : bfile } ,
		success : (r) => {
			console.log('통성'); console.log(r);
			
		}
	}) // ajax end
	*/
	location.href="/jspweb/filedownload?bfile="+bfile;
	
} // 파일 다운로드 end 

/*
	
	전송 방법
		HTML 	:	1. <form>	2. <a href="">
		JS		:	1. location.href=""
		JQUERY	:	1. $.ajax({ })
		servlet	:	1. response.getWriter.print();
					2. response.sendRedirect('경로');
 */

// 3. 조회수(1) , 좋아요(2) , 싫어요(3)
bIncrease(1); // 해당 jsp/js가 열리는순간 조회수 증가
function bIncrease( type ){
	let bno = document.querySelector('.bno').innerHTML;
	$.ajax({
		url : "/jspweb/board/view" ,
		method : "get" ,
		data : { "type" : type , "bno" : bno } ,
		success : (r) => {
			getBoard();
		}
	})
	
}

// 4. 삭제
function bdelete( bno , cno ){
	$.ajax({
		url : "/jspweb/board/info" ,
		method : "delete" ,
		// type : 1이면 게시물삭제[첨부파일삭제] , type : 2이면 첨부파일만 삭제		
		data : { "bno" : bno , "type" : 1 } , 
		success : (r) => {
			if ( r == 'true' ){alert('삭제성공'); location.href = "/jspweb/board/list.jsp?cno="+cno; } 
			else { alert ('삭제실패') }
			
			
		}
	})
	
}

// 5. 수정 페이지로 이동 
function bupdate( bno ){
	location.href="/jspweb/board/update.jsp?bno="+bno;
}

// 6. 댓글 쓰기
function rwrite(){
	
	$.ajax({
		url : "/jspweb/board/reply" ,
		method : "post" ,
		data : { 
			"type" : 1 ,
			"bno" : bno , 
			"rcontent" : document.querySelector('.rcontent').value } ,
		success : (r) => {
			console.log(r);
			if (r=='true') { 
				alert('댓글작성성공'); 
				document.querySelector('.rcontent').value ='' 
				// 특정 div만 새로고침[렌더링]
				// $('.replylistbox').load(location.href+' .replylistbox')}
				// 현재페이지 새로고침[렌더링]
				location.reload();
			} else { alert('댓글작성실패') }
		}
	});	
} // 댓글 쓰기 함수 end

// 7. 댓글 출력
function getReplyList() {
	$.ajax({
		url : "/jspweb/board/reply" ,
		method : "get" ,
		data : { "type" : 1 , "bno" : bno } ,
		success : (r) => {
			
			let html = ''
			r.forEach( (o,i) => {
				html +=`
					<div>
						<span>${ o.mimg} </span>
						<span>${ o.mid} </span>
						<span>${ o.rdate} </span>
						<span>${ o.rcontent} </span>
						<button onclick="rereplyview(${ o.rno })" type="button"> 댓글 작성 </button>
						<div class="rereplybox${ o.rno }"></div>
					</div>
					`
			})
			
			document.querySelector('.replylistbox').innerHTML = html;
			
		}
	})
}
// 8. 하위 댓글 구역 표시
function rereplyview( rno ){
	
	$.ajax({
		url : "/jspweb/board/reply" ,
		async : 'false' ,
		method : "get" , 
		data : { "type" : 2 , "bno" : bno , "rindex" : rno } ,
		success : (r) => {
			console.log(r);
		}
	})
	
	let html = `
				<textarea class="rrcontent${rno}"> </textarea>
				<button onclick="rrwrite(${rno})" type="button"> 답글 작성 </button>
				`
	document.querySelector('.rereplybox'+rno ).innerHTML = html;
	
}

// 9. 하위 댓글 작성
function rrwrite( rno ){ // bno , mno , rcontent , rindex(상위댓글번호) , type
	$.ajax({
		url : "/jspweb/board/reply" ,
		method : "post" ,
		data : { 
			"type" : 2 , "bno" : bno , "rindex" : rno , 
			"rcontent" : document.querySelector('.rrcontent'+rno).value } ,
		success : (r) => {
			console.log(r);
		}
	})
	
}










