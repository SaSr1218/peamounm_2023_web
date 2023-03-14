// 1. header.js에서 ajax 동기식으로 회원정보 가져온 상태

document.querySelector('.mid').innerHTML = memberInfo.mid;
document.querySelector('.memail').innerHTML = memberInfo.memail;
document.querySelector('.mimg').src= `/jspweb/member/mimg/${ memberInfo == null ? 'default.webp' : memberInfo.mimg} ` 
document.querySelector('.mpoint').innerHTML = memberInfo.mpoint;

// * 회원정보.js : 만약에 로그인이 안되어 있으면 불가능 [ 제어 ]
if ( memberInfo.mid == null ){
	alert('로그인하고 오세요');
	location.href="/jspweb/member/login.jsp";
}

// 1. 회원탈퇴
function setDelete(){
	
	$.ajax({
		url : "/jspweb/member" ,
		method : "delete" ,
		data : { "mpwd" : document.querySelector('.mpwd').value } ,
		success : (r) =>{
			if ( r == 'true' ){
				alert('회원탈퇴성공');
				location.href="/jspweb/member/logout.jsp";
				
			}else{
				document.querySelector('.mpwd').value ='';
				alert('회원탈퇴실패[비밀번호가 다릅니다.]');			
			}
		}
	}) // ajax end
}

