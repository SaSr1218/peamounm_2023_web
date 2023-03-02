
function 예제1(){
	let data = document.querySelector('.inputdata').value;
	console.log( data );

	$.ajax({
		url : "http://localhost:8080/jspweb/indextest" ,		// 통신할 서블릿 주소
		method : "post" ,	// HTTP 메소드 
		data : { "data" : data } ,	// 데이터 보내기
		success : function( result ) { 
			console.log( result ) 
			}	// 데이터 받기
	});
	
}

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

	// js --> 서블릿 이동
	// 0. $ : jquery 표현식
	// 1. ajax 메소드 사용 : $.ajax( );
	// 2. ajax 매개변수[속성:객체형태] : { } --> $.ajox( { } )
	// 3. 속성
		// 1. url : 통신할 경로[ 서블릿(클래스) 주소 : ip/프로젝트명/클래스명 ]
			/* 
				url : "http://localhost:8080/jspweb/indextest"
				url : "http://192.168.56.1:8080/jspweb/indextest"
				url : "/jspwep/Indextest"
			*/
			
		// 2. method : http 메소드방식
			/* 
				get
				post 
			*/
			
		// 3. data : 통신할때 보낼 데이터
			/* 
				js객체 형태 : { 매개변수명1 : 데이터 , 매개변수명2 : 데이터 ~~ }
			*/
			
		// 4. succcess : 통신후 응답 데이터 받기
			/*
				1. success : 함수명 ( 매개변수 ){ }
				2. success : ( ) => { }
			 */
		
	/* $.ajax({ 
			url : "jspweb/indextest" , 
			method : "" , 
			data : { 매개변수명 : 데이터 } , 
			success : function( result ) {
				
			} 
		});

	*/
