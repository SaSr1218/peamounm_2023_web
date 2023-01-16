 /* ---------공통부분 시작 ------------*/
 // 도서목록 선언
 let 도서목록 = []
 //대여목록 선언
 let 대여목록 = []
 /* ---------공통부분 끝--------------*/
 
 /* ----------------최성아 작성 시작-------------- */
 function click_btn() { // f s
let content_register = document.querySelector('.content_register').value
	등록하기( content_register ) // 이 안에 유효성 검사
	printContent( ) // 이 안에 Delete 기능
} // f e

// 2. 삭제 버튼 함수

function Delete ( i ){
	for ( value of 도서목록 ){
		if(대여목록.includes(도서목록[i]) == true){
			alert ('대여중인 도서는 삭제가 불가능합니다.')
			return;
		}
	}
	도서목록.splice ( i , 1 )
	printContent( )
	print()
}

function printContent( ){
	let html = `<tr>
					<th>번호</th>
					<th>도서</th>
					<th>도서대여여부</th>
					<th>비고</th>
				</tr>`
	for ( let i = 0 ; i < 도서목록.length; i++ ){ // for s
			if( 대여목록.includes(도서목록[i]) == true ){
			html += `<tr>
					<td>${ i+1 }</td>
					<td>${ 도서목록[i] }</td>
					<td>대여중</td>
					<td><button onclick="Delete( ${i} )">삭제</button></td>
				</tr>`	
			}else{
			html += `<tr>
					<td>${ i+1 }</td>
					<td>${ 도서목록[i] }</td>
					<td>대여가능</td>
					<td><button onclick="Delete( ${i} )">삭제</button></td>
				</tr>`	
			}
				document.querySelector('.result_box1').innerHTML = html
	} // for e
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
		printContent()
		return;
	}
	도서목록.push( 등록 )
	print()
}
 /* ----------------최성아 작성 끝-------------- */
 
 /* ----------------권태형 작성 시작-------------- */
 function take(x){ //대여 함수
 	//도서목록에서 데이터 가져옴
 	let book = 도서목록[x]
 	//데이터값이 도서목록에 있는지 확인 [ 있으면 인덱스 번호 들어가고 없으면 -1]
 	let bookname = 도서목록.indexOf(book)
 	//데이터값이 대여목록에 있는지 확인 [ 있으면 인덱스 번호 들어가고 없으면 -1]
 	let noneBook = 대여목록.indexOf(book)
 	//대여목록에 있으면 이미 대여된거
 	if(noneBook!=-1){
		 alert('이미 대여된 책입니다.')
		 return;
	}
	//대여목록에 없으면 대여실행 
 	else{
		 //도서목록에 있는 데이터를 대여목록에 삽입
		 대여목록.push(도서목록[x])
		 alert('대여하였습니다.')
 	}
 	//출력함수
	print()
	printContent( )
 } //대여 함수 끝
 
 function retrun(x){ // 반납함수
 	//도서목록에서 데이터 가져옴
  	let book = 도서목록[x]
  	//데이터값이 도서목록에 있는지 확인 [ 있으면 인덱스 번호 들어가고 없으면 -1]
 	let bookname = 도서목록.indexOf(book)
 	//데이터값이 대여목록에 있는지 확인 [ 있으면 인덱스 번호 들어가고 없으면 -1]
 	let noneBook = 대여목록.indexOf(book)
 	//대여목록에 없으면 대여안한 책이라고 뜸
 	if(noneBook==-1){
		 alert('대여하지 않은 책입니다.')
		 return;
	}
	//대여목록에 없으면 대여목록배열에서 삭제하고 반납되었습니다.
 	else{
		 대여목록.splice(noneBook,1)
		 alert('반납되었습니다.')
 	}
 	//출력함수
	print()
	printContent( )
 }// 반납함수 끝
 
 function print(){ // 출력함수
 	//초기 헤드라인
 	let html = `<tr><th>번호</th><th>도서</th><th>도서대여여부</th><th>대여/반납</th></tr>`
	// 내용추가
	for(i=0;i<도서목록.length;i++){
		// 첫째칸 = i+1 = 번호
		// 둘째칸 = 	대여가능여부함수를 사용하기 위해 td에 class를 순차입력, 
		// 			내용물에 대여가능여부함수 순차입력 
		html += `<tr>
					<td>${i+1}</td>
					<td> ${도서목록[i]}</td>
					<td class="list(${i})"> ${possible(i)} </td>
					<td><button onclick="take(${i})">대여</button>
					<button onclick="retrun(${i})">반납</button></td>
					</tr>` 
 	}
 	//출력
 	document.querySelector('.book_table').innerHTML = html
 }

 function possible(x){ //대여여부함수
 	//도서목록에서 데이터 가져옴
   	let book = 도서목록[x]
   	//데이터값이 대여목록에 있는지 확인 [ 있으면 인덱스 번호 들어가고 없으면 -1]
 	let noneBook = 대여목록.indexOf(book)
 	//대여목록에 없으면 대여가능 return
 	if(noneBook==-1){
		 return '대여가능'
	 }
	 //대여목록에 있으면 대여중 return
	 else{ return '대여중'}
 } // 대여 여부 함수 끝
 /* ----------------권태형 작성 끝-------------- */