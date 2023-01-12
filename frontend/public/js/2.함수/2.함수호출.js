
// - 함수호출
	// 1. 호출할 함수의 이름이 존재
	
	/*
		1. 인수X 반환X 없는 함수
		
			-정의						 호출
			function 함수명(){		함수명()
			
			}
			
		2. 인수O 반환X 함수
			-정의
			function 함수명 ( x ) {	 호출
									함수명( x )
			}
			
		3. 인수X 반환O 함수
		
			-정의
			function 함수명(){		 호출
				return 3			let result = 함수명 ( )
			}	
		
		4. 인수O 반환O 함수
			-정의
			function 함수명 ( x ){	 호출
				return 3			let result = 함수명( 3 ) 
			}
		
	 */
			
	/*
		예) prompt ( ) 함수 예시
			1.
				prompt(인수) : 인수 = 메시지창에 출력할 테스트
			2.
				let 반환값 = prompt(인수) : 반환값 = 메시지창에 입력된 테스트
	*/
// prompt ( ) 함수 예시
let 반환값 = prompt('인수자리')

// 1. 인수가 없는 함수 선언
function 함수1( ){ // 함수 s
	console.log('함수1 실행')
} // 함수 e

// 1. js 함수 호출 [ 함수호출 : 함수명( ) ]
함수1( )

// 2. 인수가 1개인 함수 선언
function 함수2( x ) {
	console.log('함수2 실행 인수:' + x )
}
// 2. js에서 함수 호출
함수2( 7 )

// 3. 인수가 2개인 함수 선언
function 함수3( x , y ){
	console.log('함수3 실행 인수 : ' + ( x + y ) )
}
// 3. js에서 함수 호출
함수3( 5 , 7)

// 4. 반환이 있는 함수 선언
function 함수4( x , y , z ){
	let result = x + y + z
	return result;
}

// 4. js에서 함수 호출
함수4( 1 , 2 , 3 ) /// ?? 반환후에 코드 없다.
let result = 함수4( 1 , 2 , 3 )
console.log( '함수4 반환 : ' + 함수4(1,2,3))





















