//최성아

let 도서목록 = ['혼자공부하는자바', '이것이자바다', '열혈C언어']

function click_btn() { // f s

let content_register = document.querySelector('.content_register').value
	등록하기( content_register )
	printContent( )
} // f e

// 2. 삭제 버튼 함수

function Delete ( d ){
	도서목록.splice ( d , 1 )
	printContent( )
}

function printContent( ){
	let html = `<tr>
					<th>번호</th>
					<th>도서</th>
					<th>도서대여여부</th>
					<th>비고</th>
				</tr>`
	for ( let i = 0 ; i < 도서목록.length; i++ ){ // for s
			html += `<tr>
					<td>${ i+1 }</td>
					<td>${ 도서목록[i] }</td>
					<td>대여가능여부</td>
					<td><button onclick="Delete( ${i} )">삭제</button></td>
				</tr>`	

	} // for e
	document.querySelector('.result_box1').innerHTML = html
} // f e

// 유효성검사 (중복, 글자수 제한)
function 등록하기 ( 등록 ){
	// 1. 중복검사/취소
	if (도서목록.indexOf( 등록 ) >= 0 ) {
		alert('도서명이 이미 등록되어있습니다. [등록되지 않습니다.]')
		도서목록.splice( 도서목록.indexOf(등록), 1 )
		printContent()
		return;
	}
	if ( 등록.length < 5 || 등록.length > 10){
		alert('글자 길이[5-10]를 맞춰주세요.')
		도서목록.splice( 도서목록.indexOf(등록), 1 )
		printContent()
		return;
	}
	도서목록.push( 등록 )
}




/*
구현해야할 기능
1. 도서대여여부 -> 배열과 비교해서 있으면 대여중, 없으면 대여가능으로 출력
2. 도서대여여부가 대여중일 경우 삭제 버튼 비활성화
*/