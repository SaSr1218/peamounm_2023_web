// 1. userbox DOM객체 가져오기
let userbox = document.querySelector('.userbox')
// * userbox [기본/처음] 위치
let u_left = 10;

// 2. 문서 안에서 키 입력 이벤트
document.addEventListener( 'keydown' , (e) =>{
	console.log ( '키 입력' )
	console.log ( e )
	console.log ( e.keyCode )
	
	let key = e.keyCode;
	if (key == 37){ // 왼쪽키
		u_left -= 10
	}else if (key == 39){ // 오른쪽키
		u_left += 10
	}
	userbox.style.left = `${ u_left }px`
})