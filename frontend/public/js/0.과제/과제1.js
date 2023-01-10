/*
	* 오류메시지 : ~~ in not defined
	1. 정의X 2. 이름오타 3. 저장/새로고침 적용X
 */

/*  4. 배열선언 
	1. 함수안에서 선언(함수실행마다 선언) - 누적 저장 X 
	2. 함수밖에서 선언(JS실행 1번 선언) - 누적 저장 O
*/

 // 4. 배열 선언과 동시에 3개의 요소 저장

let studentArray = [ '20230110' , '20230109' , '20230108' ] 

 // 3. HTML : onclick="onLogin()" 에 대한 함수 정의[만들기] 
 function onLogin(){ // 함수[onLogin] s

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
}

	