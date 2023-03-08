console.log('js열림');

/* 

	JS 정규표현식 : 문자 특정 규칙 , 패턴 , 집합 표현할때 사용되는 언어
		-- 문법
			/^		: 정규표현식 시작
			$/ 		: 정규표현식 끝
			[a-z]	: 소문자 a~z 패턴
			[A-Z]	: 대문자 A~Z 패턴
			[0-9]	: 숫자 0~9 패턴
			[가-힣]	: 한글 패턴
			{ 최소길이 , 최대길이 } : 문자열 길이 패턴
			-----
			(ex)
			[a-zA-Z]		: 영문만 입력
			[a-zA-Z0-9]		: 영문 + 숫자 입력
			[a-zA-Z0-9가-힣]	: 영문 + 숫자 + 한글 입력
			-----
		--	패턴 검사 함수
			정규표현식.test( 데이터 )	: 패턴이 적절하면 true / 아니면 false
			(ex)
			/^[a-z]$/.test( qwe  )	--> true
			/^[a-z]$/.test( QWE  )	--> false
			
*/

// 2. 아이디 유효성검사 [ 1. 문자체크 2. 중복검사 ]
function idcheck(){
	console.log('입력중');
	// 1. 입력할때마다 입력값 가져오기
	let mid = document.querySelector('.mid').value;
	console.log( mid );
	// 2. 정규표현식 [ 영소문자+숫자, 길이 : 5~30글자 내외]
	let midj = /^[a-z0-9]{5,30}$/;
	// 3. 정규표현식 검사
	console.log( midj.test( mid ))
	if ( midj.test( mid ) ){
		
		// 아이디 중복검사 [ js -> 서블릿 -> dao 에게 해당 아이디가 있는 검색 ]
		$.ajax({
			url : "/jspweb/mconfirm" ,
			method : "get" ,
			data : { "mid" : mid } ,
			success : (r) =>{
				console.log('ajax통신');
				console.log(r);	// 응답 결과 [ 있으면 true / 없으면 false]
				if ( r == 'true'){document.querySelector('.idcheckconfirm').innerHTML = '사용중인 아이디입니다.'; }
				else {document.querySelector('.idcheckconfirm').innerHTML = '사용가능한 아이디입니다.';}
			}
		})
	
	
	}else{ document.querySelector('.idcheckconfirm').innerHTML = '영소문자+숫자 조합 5~50사이로 입력해주세요.'}
	
} // 아이디 유효성검사 end

// 1. 회원가입
function signup(){
	console.log('signup 함수 열림');

	// 1. [ 첨부파일 있을때 ] html에 file input 직접적으로 조작 불가능
		// 1. form 객체 가져오기
	let signupForm = document.querySelectorAll('.signupForm')[0]; // 첫번째 form 가져오기
		// 2. form 안에 있는 data 객체 호출 [ js api = FormData ]
	let signupFormData = new FormData( signupForm );
		console.log( signupFormData );
	
	// 2. [ 첨부파일 있을때 ] ajax
	$.ajax({
		url : "/jspweb/member" ,
		method : "post" ,					// 첨부파일 무조건 post
		data : signupFormData ,				// FormData 객체 전송
		// 첨부파일 있을때 추가되는 속성
		contentType : false , 				// true : 매개변수형식의 문자열타입 [ 기본값] , false : 해제
												// form-urlencoded 타입으로 전송
											// false : 해제
											 	// multipart/form 형식으로 전송
		processData : false ,				
		success : (r) => {					
			console.log('ajax 응답');			
			console.log( r );
			if( r == 'true'){
				alert('회원가입성공');
				location.href="/jspweb/index.jsp";
			}else{alert('회원가입실패');}
		 }
	})

		
} // signup end




/*
	// 1. 입력받은 값 모두 가져와서 객체화 [ 첨부파일 없을때 ]
	let info = {
		mid : document.querySelector('.mid').value,
		mpwd : document.querySelector('.mpwd').value,
		mpwdconfirm : document.querySelector('.mpwdconfirm').value,
		meamil : document.querySelector('.meamil').value,
		mimg : document.querySelector('.mimg').value
	}
	console.log(info);
	
	// 2. ajax 통신 [ 첨부파일 없을때 ]
	$.ajax({
		url : "/jspweb/member" ,
		method : "post" ,
		data : info ,
		success : (r) => {
			console.log('ajax응답');
			console.log( r );
			if( r == 'true'){
				alert('회원가입성공');
				location.href="/jspweb/index.jsp";
			}else{alert('회원가입실패');}
		} // success end
	}) // ajax end
*/