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
			else { alert('회원정보가 다릅니다.') }
		}
	})
}
