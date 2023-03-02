// document
	// querySelector( 식별자 )
		// 1. document.querySelector('.classname')
		// 1. document.querySelector('#idname')
		// 1. document.querySelector('tagname[name="name"]')
	// 2. document.querySelectorAll( 식별자 ) : tag 여러개 -> 배열/리스트에 저장
	
	// 3. querySelector().속성명
		// 1. querySelector().value		: input , select , textdrea [ div x ]
		// 2. querySelector().innerHTML : div , span , td 등

// JS 함수
// function 함수명 ( 매개변수 ) { 실행코드 }


function 예제1(){
	let data = document.querySelector('.inputdata').value;
	console.log( data );
	
}