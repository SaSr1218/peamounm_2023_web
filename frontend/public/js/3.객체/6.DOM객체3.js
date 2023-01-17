
// 1. keyup 이벤트 : 키보드 키를 떼었을때 작동
	// 1. <textarea> 마크업 객체화
const textarea = document.querySelector('textarea')	
const h3 = document.querySelector('h3')

	// 2. 해당 마크업에 이벤트 등록[ 객체명.addEventListener ]
textarea.addEventListener( 'keyup' , ( 이벤트상태 ) => {
	// 3. 해당 마크업 사이에 html 대입 [ 객체명.innerHTML = html형식의 문자 ]
	h3.innerHTML = `글자 수 : ${ textarea.value.length }`
	// 4. 키 상태 확인
	console.log( 이벤트상태 )
	console.log( " 조합 alt 키 : " + 이벤트상태.altKey )
	console.log( " 조합 ctrl 키 : " + 이벤트상태.ctrlKey )
	console.log( " 조합 shift 키 : " + 이벤트상태.shiftKey )
	console.log( " code 키 : " + 이벤트상태.code )
	console.log( " name 키 : " + 이벤트상태.key )
})
	
	// css 조작
	textarea.style.position = 'absolute' // static이 기본값임. = 위치 조작 불가능
	let x = 5	// x축	// 처음 위치
	let y = 5	// y축	// 상위 마크업 시작점 기준
	let block = 20;	// 이동 단위
	
print();	// 위치 함수 1번 실행
function print() { // 위치 배치 함수
	textarea.style.left = `${ x*block }px`	// css left = (x*이동단위)+'px'
	textarea.style.top = `${ y*block }px`	// css top = `${y*이동단위}px`
}	
	
// 2. keydown 이벤트 : 키보드 키를 누르고 있을때 작동
document.body.addEventListener( 'keydown' , ( e ) => {
	if ( control){
	let key = e.keyCode;	console.log ( e.keyCode )
	if ( key == 37 ){ x--; }	  	// 왼쪽 키
	else if( key == 38){ y--; } 	// 위 키
	else if( key == 39){ x++; }	// 오른쪽 키
	else if( key == 40){ y++; }	 // 아래쪽 키
	print();
	}
})
	
// * 이벤트 제어권 변수
let control = true;
	
// 3. 
document.querySelector('.moving').addEventListener('click' , ()=>{ 
	control = false; // 제어 변수 변경 ---> 버튼이 제어를 변경시키는 것임; true false 값 변경
	// 경우의 수
	// 1.삼항연산자일 경우 -> control ? control=false : control = true
	// 2. 부정 -> control = !control
})	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	