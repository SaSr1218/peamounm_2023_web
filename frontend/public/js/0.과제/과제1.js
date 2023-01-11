/*
	* 오류메시지 : ~~ in not defined
	1. 정의X 2. 이름오타 3. 저장/새로고침 적용X
 */

/*  4. 배열선언 
	1. 함수안에서 선언(함수실행마다 선언) - 누적 저장 X 
	2. 함수밖에서 선언(JS실행 1번 선언) - 누적 저장 O
*/

 // 4. 배열 선언과 동시에 3개의 요소 저장


let studentArray = [ '20230110' , '20230109' , '20230108' ]  // 배열

 // 3. HTML : onclick="onLogin()" 에 대한 함수 정의[만들기] 
 function onLogin(){ // 함수[onLogin] s, 1번 과제 

	// 5. <input> 마크업을 js변수로 가져오기
	let sno = document.querySelector('.sno')
	// <input> 마크업에 입력된 데이터 호출
	let snoValue = sno.value;

	// 6. 비교 [ 만약에 입력한 값이 존재하면 ]
	let sIndex = studentArray.indexOf( snoValue )
	 // 7. 논리 + 출력(innerHTML)
	 	if ( sIndex == -1 ){
	 	//{ alert('로그인 실패')}
	 	document.querySelector('.resultBox').innerHTML = '알 수 없는 학번입니다!'
	 	}
		else{ //alert('로그인 성공'); 
	 	document.querySelector('.resultBox').innerHTML = '로그인성공!'
	 	}
}	 		 
// 함수[onLogin] e

/* 내가 작성한 코드, input value 초기화 오류
function onRegister( ){
	let sre = document.querySelector('.sre') 
	let sreValue = sre.value;
	let sreIndex = studentArray.indexOf( sreValue )
		if ( sreValue == '' ) {
			document.querySelector('.result2Box').innerHTML = '학번을 입력해주세요.'
		} else if(sreValue.length != 8){
			document.querySelector('.result2Box').innerHTML = '8자리로 입력해주세요.'
		} 
		  else if (sreIndex !== -1 ) {
			document.querySelector('.result2Box').innerHTML = '등록실패'
	}
}*/


// 강사님이 작성한 코드
function onAdd( ){ // 1. 함수 시작
	
	// 1. 테스트
	console.log('1.함수 실행 확인')
	
	// 2. <input> 태그 가져오기
	let sno2 = document.querySelector('.sno2')
	
	// 2.2 <input> 에 입력된 value[값] 가져와서 변수에 저장
	let sno2Value = sno2.value
	let result2Box = document.querySelector('.result2Box')

		console.log('2. 입력값 : ' + sno2Value) // 2. 확인
		
	// ! 검사 변수 [ 유효성검사 체크리스트 ] confitm 리스트 만들기 or return 으로 함수 끊기
	let confirm = 0;
	
	// ! 추가코드 1. 만약에 입력된 값이 공백이면
	if ( sno2Value == '' ) {console.log('공백이네요.~'); confirm++; }
	// ! 추가코드 2. 만약에 입력된 값이 8자리 아니면 
	if ( confirm == 0 && sno2Value.length !=8 ) {console.log('8자리로 입력해주세요.'); confirm++; }
	
	if ( confirm ==0 ) {
	
	
	// 3. 유효성검사[ 중복체크 ]
		// 입력받은 값[sno2Value]이 있고 기존에 있는 값[studentArray]들과 비교	
	if ( studentArray.indexOf(sno2Value) == -1 ){ // 중복 X
		// console.log('학번을 등록합니다.')
		studentArray.push( sno2Value )
			console.log( result2Box )
		result2Box.innerHTML = '학번 등록했습니다.'
		// ! 추가코드 3. 만약에 등록을 성공했으면 <input> value 초기화
		sno2.value = ''
	}	
	else { // 중복 있다.
		// console.log('이미 등록된 학번입니다.')
				result2Box.innerHTML = '이미 존재하는 학번입니다.'
		}
	}
} // 함수 끝















	