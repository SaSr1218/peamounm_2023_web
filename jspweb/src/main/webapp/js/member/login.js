
// 1. 로그인
function login(){
	let mid = document.querySelector('.mid').value;
	let mpwd = document.querySelector('.mpwd').value;
		
	$.ajax({
		url : "/jspweb/login" ,
		method : "post" ,
		data : { "mid" : mid , "mpwd" : mpwd } ,
		success : (r) =>{
			if ( r == 'true') { location.href="/jspweb/index.jsp";}
			else { 
				document.querySelector('.checkconfirm').innerHTML = "회원정보가 다릅니다."
			 }
		}
	})
}

// 2. 아이디 찾기
function findid(){
	let memail = document.querySelector('.memail').value;
	
	$.ajax({
		url : "/jspweb/find" ,
		method : "get" ,
		// "type" : 1 = 아이디 찾기 , 2 = 비밀번호찾기
		data : {"type" : 1 , "memail" : memail} ,
		success : (r) => {
			if ( r == 'false'){
				document.querySelector('.checkconfirm').innerHTML = '일치하는 회원정보가 없습니다.';
			} else{
				document.querySelector('.checkconfirm').innerHTML = '회원님이 찾은 아이디 : ' + r ;
			}
		}
	})
}

// 3. 비밀번호 찾기
function findpwd(){	console.log( "findpwd()함수");
	let info = {
		type : 2 , // "type" : 2 비밀번호찾기
		mid  : document.querySelector('.mid').value ,
		memail : document.querySelector('.memail').value
	}; console.log( info );
	$.ajax({
		url : "/jspweb/find",
		method : "get",
		data : info , 
		success : (r) => {	console.log('통신'); console.log(r);
			if( r == 'false'){ document.querySelector('.checkconfirm').innerHTML= '동일한 회원정보가 없습니다.'; }
			else{ document.querySelector('.checkconfirm').innerHTML= '임시비밀번호를 이메일로 전송했습니다.'; }
		} // success end 
	}) // ajax end 
} // end 












