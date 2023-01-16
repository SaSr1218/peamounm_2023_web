console.log('1. js 실행 확인')

// * 배열선언 [ board 객체 여러개를 저장하는 배열 / 함수 밖에 있는 이유 : 누적 저장 위해 ]
let boardArray = [ ]
	// 예시 : [ { 속성 : 값 } , { 속성 : 값 } , { 속성 : 값 } ]

// 1. 작성버튼 클릭 시 실행되는 함수
function 작성( ){ // f s
	console.log('2.함수 실행 확인')
	
	// 1. 입력받은 데이터 가져오기
	let 작성자 = document.querySelector('.작성자').value;
		console.log( '3.작성자 : ' + 작성자 )
	let 내용 = document.querySelector('.내용').value;
		console.log( '3. 내용 : ' + 내용 )
	let 비밀번호 = document.querySelector('.비밀번호').value;
		console.log('3. 비밀번호 : ' + 비밀번호 )
	// 2. 2개의 데이터를 하나로 객체화
	let board = { writer : 작성자 , content : 내용 , password : 비밀번호 }
		console.log( board )
		
	// 3. 객체를 배열에 저장 [ 여러개 객체를 저장 ]
	boardArray.push( board )
		console.log( boardArray )
	출력();
} // f e

// 2. 해당 인덱스의 객체[게시물]를 1개 삭제 함수
function 삭제 ( i ) { // i : 내가 삭제할 게시물의 인덱스 
	// 1. 비밀번호 입력받는다.
	let 비밀번호 = prompt('게시물 비밀번호 입력 : ')
	
	// 2. 입력받은 비밀번호와 내가 선택한 게시물의 비밀번호가 같으면 삭제
	if( 비밀번호 == boardArray[i].password ){
		boardArray.splice( i , 1 ); // i번째 인덱스 = 객체 삭제	
		alert('게시물 삭제 성공'); 출력();
	}else{
		alert('패스워드가 다릅니다. 삭제 실패')
	}
} // f e


// 3. 게시물 출력함수 [ 1. 작성 성공시 출력 2. 삭제 성공시 출력]
function 출력(){
		// 4. 배열내 객체 출력
		// 4-1. 테이블 제목행 만들기
	let html = `<tr>
					<th>번호</th>
					<th>내용</th>
					<th>작성자</th>
					<th>비고</th>
				</tr>`
		// 4-2. 반복문 이용한 배열내 모든 요소 누적 행 만들기
			// 1. 반복 이용해서 배열내 객체 하나씩 호출	 : boardArray[i]
			// 2. 객체내 속성 호출					 : boardArray[i].속성명
	for ( let i = 0 ; i<boardArray.length ; i++ ){
		html += `<tr>
					<td> ${ i+1 } </td>
					<td> ${ boardArray[i].content } </td>
					<td> ${ boardArray[i].writer } </td>
					<td>
						<button onclick="삭제( ${i} )"> 삭제 </button>
						<button onclick="수정( ${i} )"> 수정 </button>	
					</td>
				</tr>`
	} // for end
		// 4-3. 반복문 종료시 누적된 html 해당 table 출력
	document.querySelector('.게시물테이블').innerHTML = html;
}

// 4. 해당 인덱스의 객체[게시물]를 1개 내용 수정 함수
function 수정 ( i ) { // 수정버튼 클릭시 -> 비밀번호 입력받아 일치하면 새로운내용[prompt] 입력받기
	// 1. 수정 클릭시 비밀번호 입력받는다.
	let 비밀번호 = prompt('비밀번호 : ')
	// 2. 입력받은 비밀번호와 내가 선택한 게시물의 비밀번호가 같으면 객체내 속성값 수정후 출력
	if ( 비밀번호 == boardArray[i].password ){
		새로운내용 = prompt('수정 할 내용 : ')
		boardArray[i].content = 새로운내용;
		alert('수정 성공');
		출력();
		}else{ // 같지 않으면
		alert('비밀번호가 일치하지 않습니다. 수정 실패')
		}
}

// boardArray 				: 배열
// boardArray[i] 			: 배열내 i번째 요소 --> 객체 1개
// boardArray[i].content 	: 객체.속성명






