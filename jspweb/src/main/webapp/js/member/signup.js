/* 
	JS 정규표현식 : 문자 특정 규칙 , 패턴 , 집합 표현할때 사용되는 언어 
		-- 문법 
			/^		: 정규표현식 시작 
			$/		: 정규표현식 끝
			[a-z]	: 소문자 a~z 패턴
			[A-Z]	: 대문자 A~Z 패턴
			[0-9]	: 숫자 0~9 패턴 	<------------> \d
			[가-힣]	: 한글 패턴 
			{ 최소길이 , 최대길이 } : 문자열 길이 패턴 
			+ : 앞 에 있는 패턴 1개 이상 반복 
			? : 앞 에 있는 패턴 0개 혹은 1개 이상 반복
			* : 앞 에 있는 패턴 0개 반복
			
			\ : 이스케이프 문자
			
			----
			[a-zA-Z] 		: 영문 입력
			[a-zA-Z0-9] 	: 영문 + 숫자 입력
			[a-zA-Z0-9가-힣] : 영문 + 숫자 + 한글 입력 
			----
			1개 이상 문자가 포함되어야 하는 경우 
				(?=.*[ 패턴문자 ] )	: 해당 패턴문자가 한개 이상 입력 
			(?=.*[a-z])	:		소문자 한개 이상 입력  
			(?=.*[A-Z])	: 		대문자 한개 이상 입력 
			(?=.*[0-9])	:		숫자 한개 이상 입력 
			(?=.*[!@#$%^&*]):	해당 하는 특수문자 한개 이상 입력
			----
			/^ (?=.*[A-Za-z])(?=.*\d)[A-Za-z\d]{5,20} $/
			1. (?=.*[A-Za-z])					: 영대소문자 한개 이상 입력 
			2. (?=.*\d) vs (?=.*[0-9])			: 숫자 한개 이상 입력 
			3. [ A-Za-z\d ] vs [ A-Za-z0-9 ]	: 영문+숫자 
			--> 영문1개 + 숫자1개 필수로 갖는 5~20글자 사이
			
			/^ (?=.*[A-Z])(?=.*[a-z])(?=.*[0-9])[A-Za-z0-9]{5,20} $/
			--> 영대문자1개 + 영소문자1개 + 숫자1개 필수로 갖는 5~20글자 사이 
			
			/^ (?=.*[A-Z])(?=.*[a-z])(?=.*[0-9])(?=.*[!@#$%^&*])[A-Za-z0-9!@#$%^&*]{5,20} $/
			--> 영대문자1개 + 영소문자1개 + 숫자1개 + 특정특수문자 1개 필수로 갖는  5~20글자 사이
		
		-- 패턴 검사 함수 
			정규표현식.test( 데이터 )	: 패턴이 적합하면 true / 아니면 false 
			ex)
			/^[a-z]$/.test( qwe )	--> true 
			/^[a-z]$/.test( QWE )	--> false
*/




// * 첨부파일 이미지 미리보기 [ 업로드랑 상관 X ] -> 방법 2개 e넣고 this 넣고 e.files 호출[1] vs 직접 js만으로 가지고 오기[2]
	// 사용자[클라이언트]에 운영체제 접근이 불가능함!
function premimg( object ){ // object : 해당 함수르 실행시킨 태그객체
	console.log('첨부파일바뀜' +  object);
	console.log( object.files[0] );	// 현재 이벤트를 실행한 input의 등로한 파일명 호출[1]
	console.log ( document.querySelector('.mimg').files[0] ); // input에 등록한 파일명 호출[2]
	// 1. JS 파일클래스 선언
	let file = new FileReader();	// 파일 읽기 클래스
	// 2. 해당 첨부된 파일 읽어오기 ( file.readAsDataURL(첨부파일) );
	file.readAsDataURL( object.files[0] ) // 해당 파일 읽어오기 , files[0] : 첨부파일 1개
	// 3. 읽어온 파일을 바이트단위로 꺼내기
	file.onload = (e) =>{
		console.log( e.target.result )
		// e.target -> file.onload  : 읽어온 파일
		// e.target.result			: 읽어온 파일의 결과
		// 4. 이미지 태그에 대입
		document.querySelector('.premimg').src = e.target.result;
	}
}

// * checkconfirm span 모두 가져오기 --> 여러개의 span이 배열/리스트 객체에 대입
let checkconfirm = document.querySelectorAll('.checkconfirm')	


// 2. 아이디 유효성검사 [ 1. 문자 패턴 체크 2.중복검사 ]
function idcheck(){ // onkeyup : 키 누르고 떼었때
	// 1. 입력할때마다 입력값 가져오기 
	let mid = document.querySelector('.mid').value;
	// 2. 정규표현식    [ 영문(소문자)+숫자  길이 : 5~30 글자 ]
	let midj = /^[a-z0-9]{5,30}$/	
	// 3. 정규표현식 검사 
	if( midj.test( mid ) ){	// 정규표현식 패턴이 true 이면 
		// 아이디 중복검사 [ js->서블릿->dao 에게 해당 아이디 검색 select ]
		$.ajax({
			url : "/jspweb/mconfirm" ,
			method : "get" , 
			data : { "mid" : mid } ,			// 입력받은 아이디 보내기 
			success : (r)=>{ 
				if( r == 'true'){ 
					checkconfirm[0].innerHTML = '사용중인 아이디입니다.';
				}else{
					checkconfirm[0].innerHTML = 'O';
				}
			}
		}) // ajax end 
		
	}else{ // 정규표현식 패턴이 false 이면 
		checkconfirm[0].innerHTML = '영소문자+숫자 조합 5~30사이로 입력해주세요';
	}
} // idcheck end 

// 3. 비밀번호 유효성검사 
function pwdcheck(){ 
	let mpwd = document.querySelector('.mpwd').value; // 1. 입력받은 값 가져오기 
	// 2. 정규표현식 : 영대소문자+숫자 조합 5~20 글자 
	let mpwdj = /^(?=.*[a-z])(?=.*[A-Z])(?=.*[0-9])(?=.*[!@#$%^*()\-_=+\\\|\[\]{};:\'",.<>\/?]).{5,20}$/
	// 3. 제어
	if( mpwdj.test( mpwd ) ){
		checkconfirm[1].innerHTML = 'O'; pwdconfirmcheck();
	}else{
		checkconfirm[1].innerHTML = '영대소문자+숫자 조합 5~20 글자'
	}
} // pwdcheck end

// 4. 비밀번호 확인 유효성검사 
function pwdconfirmcheck(){
	let mpwd = document.querySelector('.mpwd').value;
	let mpwdconfirm = document.querySelector('.mpwdconfirm').value;
	// 2. 정규표현식 : 영대소문자+숫자 조합 5~20 글자 
	let mpwdj = /^(?=.*[a-z])(?=.*[A-Z])(?=.*[0-9])(?=.*[!@#$%^*()\-_=+\\\|\[\]{};:\'",.<>\/?]).{5,20}$/
	// 3. 제어
	if( mpwdj.test( mpwdconfirm) ){
		if( mpwd != mpwdconfirm ){ // 두 비밀번호간의 동일성 체크 [ 두 비밀번호가 서로 다르면 ]
			checkconfirm[1].innerHTML = '두 비밀번호가 일치하지 않습니다.'
		}else{ // 같으면
			checkconfirm[1].innerHTML = 'O'
		}
	}else{
		checkconfirm[1].innerHTML = '영대소문자+숫자 조합 5~20 글자'
	}
} // pwdconfirmcheck end

// 5. 이메일 유효성검사
function emailcheck(){
	let memail = document.querySelector('.memail').value;
	// 아이디 구역[a-zA-Zp-0_-] , +@[골뱅이 무조건 포함] : 영문 + 숫자 + _ + - + @(필수)
	// 도메인 구역[a-zA-Z0-9-] : 영문 + 숫자 + - + \.(. 필수)
	let memailj = /^[a-zA-Z0-9_-]+@[a-zA-Z0-9]+\.[a-zA-Z0-9]+$/
	if ( memailj.test(memail) ){
		checkconfirm[2].innerHTML = '이메일 인증을 해주세요.'
		document.querySelector('.authbtn').disabled = false;
	}else{
		checkconfirm[2].innerHTML = '이메일 형식으로 입력해주세요'
		document.querySelector('.authbtn').disabled = true;
	}
} // emailcheck 함수 end

// 6. 이메일 인증 함수
function getauth(){
	// ----------------------- 메일 전송 테스트 할 경우 ------------- //
	
	// * ajax가 JAVA에게 이메일 전송후 인증코드 받기!
	/*
	$.ajax({
		url : "/jspweb/email" ,
		method : "post" ,
		data : {"memail" : document.querySelector('.memail').value} ,
		success : (r) =>{
			console.log('통신 성공');
			console.log(r);
				let html = `
				<div class="timebox"> 03 : 00 </div>
				<input type="text" class="authinput" placeholder="인증코드">
				<button onclick="authconfirm()" type="button"> 확인 </button>
				`
	document.querySelector('.authbox').innerHTML = html;
	auth = r;
	timer = 179;
	settimer();
		}
	}) // ajax end
	*/

	// ------------------ 메일 전송 테스트 안되는 경우 --------------- // 
	
		$.ajax({
		url : "/jspweb/email" ,
		method : "post" ,
		data : {"memail" : document.querySelector('.memail').value} ,
		success : (r) =>{
			console.log('통신 성공');
			console.log(r);
				let html = `
				<div class="timebox"> 03 : 00 </div>
				<input type="text" class="authinput" placeholder="인증코드">
				<button onclick="authconfirm()" type="button"> 확인 </button>
				`
	document.querySelector('.authbox').innerHTML = html;
	auth = 1234;
	timer = 179;
	settimer();
		}
	})
	

} // getauth end

let auth = 0;
let timer = 0; // 인증 시간
let timerInter; // Interval 함수를 저장할 변수
// 7. 타이머 함수
function settimer(){
	
	// setInterval : 특정 시간마다 함수 실행
		// clearInterval : Interval 종료
	let timerInter = setInterval( () =>{
		timer--;
		let minutes = parseInt(timer / 60) ;   // 분 계산
		let seconds = parseInt(timer % 60) ; // 분 계산후 나머지 = 초 계산
		// 한자리수 이면 0 추가
		minutes = minutes < 10 ? "0"+minutes : minutes;
		seconds = seconds < 10 ? "0"+seconds : seconds;
		let timeHTML = minutes + " : " + seconds; // 시 : 분 구성
		document.querySelector('.timebox').innerHTML = timeHTML;
		
		if ( timer < 0 ) {
			clearInterval(timerInter);
			checkconfirm[2].innerHTML = "인증실패";
			document.querySelector('.authbox').innerHTML = "";
		}
	} , 1000 ); //1초마다 { } 코드 실행
	
} // settimer 함수 end

// 8. 인증코드 확인
function authconfirm(){
	let authinput = document.querySelector('.authinput').value;
	if ( auth == authinput ){
		clearInterval(timerInter);
		document.querySelector('.authbox').innerHTML = "";
		document.querySelector('.authbtn').innerHTML = "인증완료";
		document.querySelector('.authbtn').disabled = true;
		checkconfirm[2].innerHTML = 'O'
	} else {
		checkconfirm[2].innerHTML = '인증코드가 일치하지 않습니다.';
	}
}




// 1. 회원가입 
function signup(){
	// * 유효성검사에 대한 체크
	let count = 0;
	for ( let i = 0 ; i<checkconfirm.length; i++ ){
		if(checkconfirm[i].innerHTML == 'O'){ count ++ }
	}
	if( count != 3 ) { alert('정상적으로 입력되지 않은 데이터가 있습니다.'); return; }
	
	// 1. [ 첨부파일 있을때 ] html 에 file input 직접적으로 조작 불가능 
		// document.querySelector(".mimg").value ,  -- 불가능 
		// 1. form 객체 가져오기 
	let signupForm = document.querySelectorAll('.signupForm')[0];	// 첫번째 form 가져오기 
		// 2. form 안에 있는 data 객체 호출  [ js api 클래스 = FormData ]
	let signupFormData = new FormData( signupForm );
	// 2. [ 첨부파일 있을때 ] ajax 
	$.ajax({
		url : "/jspweb/member",				// 서블릿 주소 
		method : "post",					// 첨부파일은 무조건 post/put
		data : signupFormData , 			// FormData 객체 전송 
		// 첨부파일 있을때 추가되는 속성 
		contentType : false ,			
		processData : false ,			
		success : (r)=>{
			if( r == 'true'){
				
				openModal();
				
				//location.href="/jspweb/index.jsp"; // 해당 페이지 이동 
			}else{ alert('회원가입실패') }
		}
	})
} // signup end 



	
/*	
	// 1. [첨부파일 없을떄] 입력받은 값 모두 가져와서 객체화 
	let info = {
		mid : document.querySelector(".mid").value , 
		mpwd : document.querySelector(".mpwd").value , 
		mpwdconfirm : document.querySelector(".mpwdconfirm").value , 
		memail : document.querySelector(".memail").value , 
		mimg : document.querySelector(".mimg").value , 
	};console.log( info );
			
	// 2. [ 첨부파일 없을때 ] ajax 통신을 이용한 서블릿에게 데이터 보내고 응답 받기 
	$.ajax({
		url : "/jspweb/member" ,	// 서블릿 클래스의 @WebServlet("/member")
		method : "post" ,			// 메소드 선택
		data : info ,
		success : (r)=>{ 
			console.log('ajax응답'); 
			console.log( r );
			if( r == 'true'){
				alert('회원가입성공');
				location.href="/jspweb/index.jsp"; // 해당 페이지 이동 
			}else{ alert('회원가입실패') }
		} // success end 
	}) // ajax end 
	
*/