console.log('1.js실행')
/*
 
 	JAVA / JS : 객체 지향 언어 [ OOP : Object Oriented Programming ]
 		1. 메모리/저장소
 			1. 변수 : let 변수명 = 데이터							: 데이터 1개 
 			2. 상수 : const 상수명 = 데이터							: 데이터 1개 
 			3. 배열 : let 배열명 = [ 데이터1 , 데이터2 , 데이터 3 ]		: 데이터 여러개
 				* 유재석/강호동/신동엽의 아이디 , 비밀번호 , 이름 저장
 				- 문제점 : 서로 다른 데이터 유형을 한번에 저장시 식별 힘듦.
 					유재석 : qweqwe , 123 , 유재석
 					강호동 : asdasd , 456 , 강호동
 					신동엽 : zxczxc , 789 , 신동엽
 			
 				let 배열명 = [ qweqwe , 123 , 유재석 , 
 							 asdasd , 456 , 강호동 ,
 							 zxczxc, 789 , 신동엽 ]
 			
 			4. 객체 : let 객체명 = { }
 
 	객체[ Object ]
 		1. 사용목적 : 서로 다른 데이터유형(속성) 들을 한번에 저장
 		2. 형태 :
 			let 객체명 = { 속성명 : 데이터, 속성명 : 데이터, 속성명 : 데이터 }
 		3. 선언 :
 			let 객체명 = {
				속성명 : 데이터 ,
				속성명 : 변수 ,
				속성명 : 상수 ,
				속성명 : 배열명 ,
				속성명 : 함수명 ,
			 } 
 		
 		4. 객체호출
 			1. 객체 정보 : 객체명
 			2. 객체.속성명 : 객체내 해당 속성의 데이터/함수 호출
 				 . : 접근연산자 [ 속성 호출 ]
 		
 		예) 회원 객체
 			let 회원 = { 아이디 : 'qweqwe' , 비밀번호 : '123' , 이름 : '유재석' }  
 */

// 1. 객체의 선언 : 서로 다른 데이터유형들 간의 식별가능
let 객체1 = {아이디 : 'qweqwe' , 비밀번호 : '123' , 이름 : '유재석' }
console.log( 객체1 ) // 객체호출
	// vs
	let 배열1 = [ 'qweqwe' , '123' , '유재석' ]
console.log( 배열1 )

// 2. 객체의 호출
console.log( 객체1 ) // 객체의 모든 정보 호출
console.log( '객체내 아이디 속성의 값 : ' + 객체1.아이디 ) // 객체내 '아이디' 속성의 값 호출
console.log( '객체내 비밀번호 속성의 값 : ' + 객체1.비밀번호 )
console.log( '객체내 이름 속성의 값 : ' + 객체1.이름 ) 
	// vs
console.log( 배열1[0] )
































