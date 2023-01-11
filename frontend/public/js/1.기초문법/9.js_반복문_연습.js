
// 예1) i는 1부터 10이하까지 1씩 증가 반복 [ 한줄씩 출력 ]
console.log('--------------------예1---------------') 
for ( let i = 0; i <= 10; i++ ){// for s
	console.log( i )
} // for e

// 예2) i는 1부터 10이하까지 1씩 증가 반복 [ 한줄에 모두 출력]
console.log('--------------------예2---------------') 
let output = ' ' // 문자 변수 선언[ 깡통 ]
for ( let i = 0; i <= 10; i++ ) { // for s
	output += i+ "\t";	// vs output = output + i
} // for e
console.log( output )

// 예3) i는 1부터 10이하까지 1씩 증가 반복 [ html 출력 ]
console.log('--------------------예3---------------') // html에 출력 됨.
output = ''			// 위에서 사용했기 때문에 ''으로 변경[수정]
for ( let i = 0; i <= 10; i++ ){ // for s
	output += i+"\t"	
} // for e
document.querySelector('body').innerHTML = output

// 예4) i는 1부터 10이하까지 1씩 증가 반복 누계
console.log('--------------------예4---------------') 
let sum = 0 ; // 숫자변수
for ( let i = 1; i <= 10 ; i++ ) {sum += i }
console.log( sum )

// 예5) 1부터 100까지 7배수 누적합계
console.log('--------------------예5---------------')

// 1. [i+=7] 
sum = 0 ; 
for ( let i = 7; i <= 100; i+=7 ){ sum += i}
console.log( sum )

// 2. if( i % 7 == 0 )
sum = 0;
for ( let i = 1; i <= 100; i++ ) {
	 if( i % 7 == 0 ){ // if s
		  sum+=i 
		  }  // if e
		} // for e
console.log( sum )

// 예6) 2단 구구단
console.log('--------------------예6---------------')

output = ''

for ( let 곱 = 1; 곱 <= 9; 곱++ ) { // for s
	console.log( '2 * ' + 곱 +' = ' + (2*곱) ); // 변수는 문자처리 X
				// '문자' + 변수 + '문자' + (계산식)
	output += '2 * ' + 곱 +' = ' + (2*곱) + '<br/>' // 누계
} // for e
document.querySelector('body').innerHTML = output

// 예7) 구구단 [ 단 : 2~9 변수, 곱 : 1~9 변수 ]
console.log('--------------------예7---------------')

	// 1단계. 단 만들기
for ( let 단 = 2; 단 <= 9; 단++ ){
	// 단은 2부터 9까지 1씩 증가 반복처리
	console.log( 단 )
}
	
	// 2단계. 곱 만들기
for (let 곱 = 1; 곱 <= 9; 곱++ ){
	// 곱은 1부터 9까지 1씩 증가 반복처리
	console.log( 곱 )
}
	// 3단계. 단/곱 코드 합치기 [ '단'이 1바퀴 돌 때 '곱' 은 9바퀴 돌아야함.]
for ( let 단 = 2; 단 <= 9; 단++ ){ // for1 s
	for (let 곱 = 1; 곱 <= 9; 곱++ ){ // for2 s
		console.log( 단 + ' * ' + 곱 + ' = ' + (단*곱))
	} // for2 e
} // for1 e

