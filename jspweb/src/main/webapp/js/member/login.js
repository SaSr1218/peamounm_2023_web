
// 로그인
function login(){
	let mid = document.querySelector('.mid').value;
	let mpwd = document.querySelector('.mpwd').value;
		
	$.ajax({
		url : "/jspweb/login" ,
		method : "post" ,
		data : { "mid" : mid , "mpwd" : mpwd } ,
		success : (r) =>{
			console.log('통신');
			console.log(r);
			
			if ( r == 'true') { location.href="/jspweb/index.jsp";}
			else { 
				document.querySelector('.checkconfirm').innerHTML = "회원정보가 다릅니다."
			 }
		}
	})
}

// 아이디 찾기
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