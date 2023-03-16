// 1. 개별 게시물 출력
getBoard();
function getBoard(){
	
	let bno = document.querySelector('.bno').innerHTML;
	
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



















