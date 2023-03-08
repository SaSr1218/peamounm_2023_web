
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