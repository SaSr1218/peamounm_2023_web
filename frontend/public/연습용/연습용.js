
/* 아이디, 비밀번호 배열 선언*/

let userArray = [ 
				{ id: 'qwe123' , pw: 'qwe123'} ,
				{ id: 'asd123' , pw: 'asd123'} ,
				{ id: 'zxc123' , pw: 'zxc123'} ,
				{ id: 'movie' , pw: 'movie'} ,
				{ id: 'actor' , pw: 'actor'} ,
			]

/* 등록되어 있는 영화 목록 */
let movieList =  [
				 {name : '더 퍼스트 슬램덩크', img : 'slam_dunk', category : '애니메이션', age : 12, star : 9.24},
				 {name : '장화신은 고양이', img : 'PussInBoots', category : '애니메이션', age : 0, star : 8.41},
				 {name : '캐리의 슈퍼콜라', img : 'CarrieAndSuperKola', category : '애니메이션', age : 0, star : 8.69},
				 {name : '교섭', img : 'ThePointMan', category : '드라마', age : 12, star : 6.30},
				 {name : '아바타', img : 'avatar', category : '액션,모험,SF,스릴러', age : 12, star : 8.83},
				 {name : '메간', img : 'M3GAN', category : '공포,스릴러,코미디', age : 15, star : 7.34},
				 {name : '유령', img : 'phantom', category : '액션', age : 15, star : 6.75},,
				 {name : '상견니', img : 'SomedayOrOneDay', category : '드라마,판타지,멜로/로맨스', age : 15, star : 7.83},
				 {name : '영웅', img : 'Hero', category : '드라마,뮤지컬', age : 12, star : 8.41},
				 {name : '오늘밤, 세계에서 이 사랑이 사라진다해도', img : 'EvenIfThisLoveDissappear', category : '멜로/로맨스', age : 12, star : 7.99}
				 ]


function singupbtn(){ // f s
	let id = document.querySelector('.id').value;
	let pw = document.querySelector('.pw').value;
	let pw_check = document.querySelector('.pw_check').value;
	let name = document.querySelector('.name').value;
	let sname = document.querySelector('.sname').value;
	let mw = document.querySelector('.mw').value;

	// 유효성검사
	let check = true;	
		//1. id 빈 값일 경우, 빈 값이 아닐 경우
		
		if ( id.length == 0 ){
			document.querySelector('.id_null').style.display = 'block';
			check = false;
			}else if(id.length != 0 ){
			document.querySelector('.id_null').style.display = 'none';				
			} 
			
		//2. pw 빈 값일 경우, 빈 값이 아닐 경우
		if ( pw.length == 0 ){
			document.querySelector('.pw_null').style.display = 'block';
			check = false;
			}else if ( pw.length != 0 )	{
			document.querySelector('.pw_null').style.display = 'none';				
			}
			
			// 2.1 pw 빈 값 X + pw 와 pw_check와 값이 다른 경우, 같은 경우
		if (pw != pw_check){
			document.querySelector('.pwcheck_null').style.display = 'block';
			check = false;
			}else if(pw == pw_check){
			document.querySelector('.pwcheck_null').style.display = 'none';
			}

			// 2.2 pw와 pw_check 값이 같은 경우
		
		// 3. name 빈 값일 경우, 빈 값이 아닐 경우
		if ( name.length == 0 ){
			document.querySelector('.name_null').style.display = 'block';
			check = false;
			}else if ( name.length != 0)	{
			document.querySelector('.name_null').style.display = 'none';				
			}
		
		// 4. 성별이 체크되어 있지 않은 경우
		if ( mw == "성별" ){
			document.querySelector('.mw_null').style.display = 'block';
			check = false;
			} else if ( mw != "성별"){
			document.querySelector('.mw_null').style.display = 'none';
			}
			
	for(let i = 0; i <userArray.length; i++){ // 문구 위치 바꾸면 됨!
		if(id == userArray[i].id){
			document.querySelector('#id_error').style.display = 'inline-block';
		check = false; return; 
		}else if(id != userArray[i].id ){
			document.querySelector('#id_error').style.display = 'none';
		}
	}
	
	// 유효성검사 통과했을 경우 모달박스 켜기
	if(check) { document.querySelector('.modal_wrap').style.display = 'block';  }
	
}// f e

/* location.href = 'login.html'; */

// 모달 기능
	// 모달 닫기
function modal(){location.href = 'login.html';}


