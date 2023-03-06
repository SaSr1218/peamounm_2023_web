// 1.
function doPOST(){
	alert("http POST 메소드 실행합니다.")
	$.ajax({
		url : "/jspweb/Ex3",
		method : "post" ,
		success : ( result ) => { }
	})
}

// 2.
function doGET(){
	alert("http GET 메소드 실행합니다.")
	$.ajax({
		url : "/jspweb/Ex3",
		method : "get" ,
		success : ( result ) => { }
	})
}

// 3.
function doPUT(){
	alert("http PUT 메소드 실행합니다.")
	$.ajax({
		url : "/jspweb/Ex3" ,
		method : "put" ,
		success : ( result ) => { }
	})
}

// 4.
function doDELETE(){
	alert("http DELETE 메소드 실행합니다.")
		$.ajax({
		url : "/jspweb/Ex3" ,
		method : "delete" ,
		success : ( result ) => { }
	})
}

//------------------------------------------------------------------
// 1.등록
function onwrite(){
	console.log('함수 s')
	let info = {
		content : document.querySelector('.content').value ,
		writer : document.querySelector('.writer').value
	}
	console.log(info)
	
	$.ajax({
		url : "/jspweb/Ex3/Board" ,
		method : "post" ,
		data : info ,
		success : ( r ) => {
			console.log ('post 응답성공');
			if ( r == "true"){ alert("등록성공"); 
			onprint();
			document.querySelector('.content').value = "";
			document.querySelector('.writer').value = "";
			} // true 일때 문자인 "true" 를 보낸 것임.
			else { alert("등록실패"); }
		} // success end
	}) // ajax end
} // 등록함수 end

// 2. 모든 게시물출력 [ 1.js열릴때 2.글작성할때 3. 렌더링[삭제,수정될때마다] ]
onprint();
function onprint(){
	
	$.ajax({
		url : "/jspweb/Ex3/Board" ,
		method : "get" ,
		success : ( r )=>{ 
			console.log( 'get 응답 성공 ');	console.log( r );
			// 1. 테이블 제목 구성 
			let html = 
						`<tr>
							<th> 번호 </th>
							<th> 제목 </th>
							<th> 작성자 </th>
							<th> 비고 </th>
						</tr>`;
			// 2. 테이블 내용 구성 
			r.forEach( (o,i) => {
				html += 
						`<tr>
							<td> ${ o.bno } </td>
							<td> ${ o.bcontent }</td>
							<td> ${ o.bwriter } </td>
							<td> 
								<button onclick="ondelete(${o.bno})" type="button"> 삭제 </button> 
								<button onclick="onupdate(${o.bno})" type="button"> 수정 </button>
							</td>
						</tr>`;
				
			});
			// 3. 구성된 html 대입 
			document.querySelector('.boardtable').innerHTML = html;
					
		}
	})
	
} // 출력 함수 end

// 3. 삭제 함수
function ondelete(bno){
	console.log("onDelete()열림" + bno );
	
	$.ajax({
		url : "/jspweb/Ex3/Board" ,
		method : "delete" ,
		data : { "bno" : bno } ,
		success : ( r ) => {
			console.log( "delete 응답 성공"); console.log(r);
			if( r == 'true' ){ alert("삭제성공"); onprint();}
			else{ alert("삭제실패"); }
		}
	})
} // 삭제함수 end

// 4. 수정 함수
function onupdate( bno ){
	console.log( "onupdate() 열람" + bno );
	let newContent = prompt('수정할 내용 입력');
	
	$.ajax({
		url : "/jspweb/Ex3/Board" ,
		method : "put" ,
		data : { "bno" : bno , "newContent" : newContent } ,
		success : ( r )=> {
			console.log("update 응답 성공"); console.log(r);
			if( r == 'true' ){ alert("수정정공"); onprint();}
			else{ alert("수정실패"); }
		}
	})
}










































