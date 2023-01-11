/*
조건1 : 방문록 내용을 여러개 담는 배열 선언 : let contentArray = []
조건2 : 방문록 내용작성<input>입력받기
조건3 : 등록버튼<button> 클릭시 입력된 데이터가 배열에 저장하는 addContent() 함수 실행
조건4 : 현재 배열에 저장된 모든 방문록을 <table>에 출력 

<tr>
	<th>번호</th> <th>방문록</th>
</tr>

*/



console.log('--------------과제3------------')

let contentArray = []


function addContent( ){

	let 테이블행 = '<tr> <th>번호</th> <th>방문록</th> </tr>'
	
	let num = document.querySelector('.num').value
	contentArray.push( num )
	
	for (let i = 0; i < contentArray.length; i++ ){
		
		
		테이블행 += '<tr>' + '<th>' + (i+1) + '</th>' + '<th>' + contentArray[i] + '</th>' + '</tr>'
	}
	document.querySelector('.b_t').innerHTML = 테이블행
}