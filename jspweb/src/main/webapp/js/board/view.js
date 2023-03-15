// 1. 개별 게시물 출력
getBoard();
function getBoard(){
	
	let bno = document.querySelector('.bno').innerHTML;
	
	$.ajax({
		url : "/jspweb/board/info" ,
		method : "get" ,
		data : { "type" : 2 , "bno" : bno } , // 2 : 개별출력
		success : (r) => {
			
			let html = `${r.bdate} / ${r.bview} / ${r.bgood} / ${r.bbad}`
			
			document.querySelector('.infobox').innerHTML = html;
			document.querySelector('.pimgbox').innerHTML = r.mid;
			document.querySelector('.btitle').innerHTML = r.btitle;
			document.querySelector('.bcontent').innerHTML = r.bcontent;
			
			if ( r.bfile == null ) {
				document.querySelector('.bfile').innerHTML = '첨부파일없음';
			}else{ // 인수 넘기고자 할때 ' ' 로 문자 처리 해주기( . 이 JS에서 접근연산자라서 )
				html = `${r.bfile}<button onclick="bdownload( '${ r.bfile }' )" type="button"> 다운로드 </button>`
				document.querySelector('.bfile').innerHTML = html;
			}
		}
	}) // ajax end
	
} // 개별 게시물 출력 end

// 2. 다운로드 [ 다운로드할 파일명 ]
function bdownload( bfile ){
	$.ajax({
		url : "/jspweb/filedownload" ,
		method : "get" ,
		data : { "bfile" : bfile } ,
		success : (r) => {
			console.log('통성'); console.log(r);
			
		}
	}) // ajax end
} // 파일 다운로드 end 








