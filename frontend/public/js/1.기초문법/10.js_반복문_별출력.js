/*
 	- 출력
	 	출력 : console.log( )
	 	알람메시지출력 : alert( )
	 	html출력 : 
	 		1. document.write( ) -> 그냥 출력
	 		2. document.querySelector( ).innerHTML -> 특정부위에 출력
	 		
	 - 입력
	 	1. 알람메시지입력 : prompt( )
	 	2. html입력 : document.querySelector( ).value
 */
//예시1) 입력받은 수 만큼 * 출력
let output = ''
/*let s1 = Number(prompt('예시1 별 개수'))
for ( let i = 1; i <= s1; i++ ){
	output += '*'
}
console.log(output)

// 예시2) 입력받은 수 만큼 * 출력 [ 3줄마다 줄바꿈 ]
output = ''		// 앞에서 사용한 output 초기화
let s2 = Number(prompt('예시2 별 개수') )
for ( let i = 1; i <= s2; i++ ){
	// 1. 별출력
	output += '*'

	// 2. 줄바꿈 출력
	if( i%3 == 0 ) {output += '\n'}
}
console.log(output)*/


/* 예시3) 입력받은 줄수[line]만큼 출력 
*
**
***
****
*/

/*
	i는 1부터 입력받은 수까지 1씩 증가 = for ( let i = 1; i <= line ; i++ )
	s는 1부터 1까지
	s는 1부터 2까지
	s는 1부터 3까지..
		s는 1부터 i까지			= for ( let s = 1; s <= i ; s++ )
 */
/*output = ''
let line1 = Number(prompt('문제1 줄수'))
for( let i = 1; i <= line1; i++){
	// 1. 별찍기
	for( let s = 1; s <= i; s++) { output += '*'}
	// 2. 줄바꿈
	output += '\n'
}
console.log(output)*/

 /* 예시4) 입력받은 줄수[line]만큼 출력 
**** 
***
**
*
*/

/*output = ''
let line2 = Number(prompt('문제2 줄수'))
for( let i = 1; i <= line2; i++){
	// 1. 별찍기
	for( let s = 1; s <= line2-i+1; s++) { output += '*'}
	// 2. 줄바꿈
	output += '\n'
}
console.log(output)*/
 
 /* 예시5) 입력받은 줄수[line]만큼 왼쪽부터 출력
    *
   **
  ***
 ****
 */
/* 
i는 1부터 입력받은 줄까지 1씩 증가 			  =	for ( let i = 1 ; i <= line3 ; i ++) 
b는 1부터 (입력받은 줄 - 현재줄수) 까지 1씩증가 = for ( let b = 1; b <=line3-i; b++)
s는 1부터 현재줄수까지 1씩 증가				  = for ( let s = 1; s<=i; s++)
*/

/* output = ''
 let line3 = Number(prompt('문제 3 줄수'))
 for ( let i = 1; i <= line3; i++ ){
	 // 1. 공백출력
	 for ( let b = 1; b <= line3-i; b++ ){
		 output += ' '
	 }
	 // 2. 별출력
	 for ( let s = 1; s <= i; s++)  {
		 output += '*'}
		 
	 // 3. 줄바꿈
	{ output += '\n' }
 }
 console.log(output)*/

/* 예시6) 
*****
 ****
  ***
   **
    *
*/ 

/*output = ''
let line4 = Number(prompt('문제 4 줄수'))
for ( let i = 1; i <= line4; i++ ){
	for ( let s = 1; s <= line4-i+1; s++ ) {
		output += '*'
	}
	for (let b = 1; b <= line4-i; b++ ){
		output += ' '
	}
	
	{ output += '\n'}
}
console.log(output)*/
 
/* 예시 7)
   *  
  ***
 *****
*******
*/

output = ''
let line5 = Number(prompt('문제 5 줄수'))
for ( let i = 1; i <= line5; i++){
	for ( let b = 1; b <= line5-i; b++) {output += ' '}
	for ( let s = 1; s <= (i*2)-1; s++) { output += '*'}
	{ output += '\n' }
}
for ( let i = 1; i <= line5; i++){
	for ( let b = 1; b <= i-1;  b++) {output += ' '}
	for ( let s = 1; s <= line5-i; s++ ) { output += '*'}
	for ( let k = 1; k <= line5-i+1; k++ ) { output += '*'}
	{ output += '\n' }
}
console.log(output)

/*
i for ( let i = 1; i <= line5; i++)
b for ( let b = 1; b <= line5-i; b++) {output = ' '}
s for ( let s = 1; s <= i*2-1; s++) { output = '*'}
b for ( let b = 1; b <= line5-i; b++) {output = ' '}
{ output += '\n\' }

b,공백  b[ line5-i ], s [i*2-1] , b[ line5-i ]
4 4
3 3
2 2
1 1
0 0
s, 별, 가운데
1    1 * 2 -1
3    2 * 2 -1
5    3 * 2 -1
7    4 * 2 -1
9    5 * 2 -1
 */
// b = i-6 / 01234 /  -3 -0 3 6 9 / s = i-s
/*
1  b = 공백 0 s = 9 /////// 1 2 3 4 5 
2  b = 공백 1 s = 7
3  b = 공백 2 s = 5
4  b = 공백 3 s = 3 
5  b = 공백 4 s = 1

 */



 
 
 
 
 
 
 
 