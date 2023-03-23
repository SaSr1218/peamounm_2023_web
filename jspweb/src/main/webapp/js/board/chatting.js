/* 
	소켓 			 : 두 프로그램간의 양방향 통신 종착점 [ 도착지 ]
	서버소켓		 : 서버가 가지고 있는 소켓
	클라이언트소켓	 : 클라이언트 가 가지고 있는 소켓 [ 각 클라이언트마다 ]
	
	카카오톡유저								카카오톡 본사/서버실
	클라이언트									서버
	new WebSocket(서버소켓URL)						@ServerEndpoint("/서버소켓URL")
	1. 클라이언트소켓.onopen()	<---연결--->			@OnOpen
	2. 클라이언트소켓.send()		---보내기--->			@OnMessage
	3. 클라이언트소켓.onmessage	<---보내기---			세션명.getBasicRemote().sendText( 보낼 내용 )
	4. 클라이언트소켓.onmessage	<---연결끊기--->		@OnClose
	
	JS WebSocket
		1. JS에서 제공하는 클래스 WebSocket
		2. 소켓 객체 만들기
			let 클라이언트소켓 = new WebSocket('ws://ip:포트번호/프로젝트명/서버소켓URL');
		3. 소켓이 서버소켓과 연동
			1. WebSocket 생성자에서 해당 서버소켓의 경로 확인 통신
	JAVA serverSocket
		1. 임의의 클래스 생성
		2. 서버소켓 만들기
			클래스 위에 @ServerEndpoint
			- @ServerEndpoint 	: 서버소켓의 URL 만들기
			- @WebServlet		: HTTP URL 만들기 
		3. 클라이언트소켓이 서버소켓[엔드포인트] 으로 접속했을때
			@OnOpen : 클라이언트소켓이 접속했을때 매핑[연결]
		4. 클라이언트 소켓이 메시지를 보냈을때 [ 서버가 메시지 받기 ]
			@OnMessage : 서버가 클라이언트 소켓에게 메시지 보내기		
*/

let 클라이언트소켓 = null
let contentbox = document.querySelector('.contentbox')

if(memberInfo.mid == null){
	alert('로그인하고 채팅방 입장 가능합니다.'); location.href = "/jspweb/member/login.jsp";
}else{
	// 1. 클라이언트소켓 생성 과 서버소켓 연결[오픈][@OnOpen] (ex) 'ws://localhost:8080/jspweb/chatting/아이디/채팅방pk'
	클라이언트소켓 = new WebSocket('ws://localhost:8080/jspweb/chatting/'+memberInfo.mid); console.log(클라이언트소켓)
	클라이언트소켓.onopen = function(e){ 서버소켓연결(e) }
	클라이언트소켓.onmessage = function(e) { 메시지받기(e); }
	클라이언트소켓.onClose = function(e) { 연결해제(e); }
}

// 2. 클라이언트소켓이 접속했을때 하고 싶은 이벤트/함수 정의 [ 따로 작성할 필요가 없으면 하지 않아도 됨. ] 
function 서버소켓연결(){ 
	
	자료보내기( memberInfo.mid+"님 이 채팅방에 접속하셨습니다." , "alarm" )
	
} 

// 3. 클라이언트소켓이 서버에게 메시지를 보내기 [ @OnMessage ]
function 보내기(){
	let msgbox = document.querySelector('.msgbox').value
	// *  서버소켓에게 메시지 전송하기 *
		// JSON형식의 문자열 타입 만들어서 문자열로 타입으로 전송
		// JSON.parse ( JSON형식의 문자열타입 )  : 문자열타입 --> JSON 타입으로 변환
		// JSON.stringify ( JSON 객체 )		: JSON타입 --> JSON 형식[모양]의 String 타입으로 변환
		let msg = {
			type : 'msg' ,
			msgbox : msgbox
		}
	클라이언트소켓.send( JSON.stringify( msg ) );	// ---> @OnMessage
	
	document.querySelector('.msgbox').value = '';
}

// 4-2 type 에 따른 출력 html 구별
function 메시지타입구분 ( msg ) {
	let json = JSON.parse( msg );
	
	let html = '';
	if (json.type == 'msg'){
		html += `<div class="content"> ${json.msgbox} </div>`
	}else if ( json.type == 'emo') {
		html += `<div class="content emocontent"> <img src="/jspweb/img/imoji/emo${json.msgbox}.gif"> </div>`
	}
	return html;
}

// 4. 서버로부터 메시지가 왔을때 메시지 받기 -> 매개변수 필수! 여기에선 e 를 말함
function 메시지받기(e) { // <---- getBasicRemote().sendText(msg)
	console.log(e);
	
	let data = JSON.parse( e.data );	// 전달받은 메시지 dto
		console.log(data)
	// let msg = JSON.parse ( data.msg ) ; // 명단에는 data.msg가 존재 X 그래서 주석 처리 후 96, 100번째 줄을 타입변환시킴 원래는 msg.msgbox임
	
	// 명단[여러개=list/Array] vs 메시지정보[1개=dto/object]
		// Array 타입 확인 : Array.isArray( 객체 ) : 해당 객체가 배열/리스트이면 true
	if( Array.isArray( data ) ){
		let html = '';
		data.forEach( (o) => {
			html += `
					<div class="conbox"> <!-- 접속 명단 1명 기준 -->
						<div> <img alt="" src="/jspweb/member/mimg/${ o.fromimg == null ? 'default.webp' : o.fromimg}" class="hpimg"> </div>
						<div class="name"> ${o.frommid} </div>				
					</div>
					`
		})
		document.querySelector('.conlistbox').innerHTML = html;
	}
	else if ( JSON.parse( data.msg ).type == 'alarm'){
			
		contentbox.innerHTML += `
						<div class="alarm">
							<span> ${ JSON.parse( data.msg ).msgbox } </span>
						</div>` 
	} 
	// 보낸 사람과 현재 유저와 일치하면 [ 내가 보낸 메시지 ]
	else if ( data.frommid == memberInfo.mid ) {
		contentbox.innerHTML += `
							<div class="secontent">
								<div class="date"> ${data.time} </div>
								${메시지타입구분( data.msg ) }
							</div>`		
	}

	else{ // [ 내가 받은 메시지 ]
		contentbox.innerHTML += `
							<div class="tocontent">
								<div><img src="/jspweb/member/mimg/${ data.fromimg == null ? 'default.webp' : data.fromimg }" class="hpimg"></div>
								<div class="rcontent">
									<div class="name"> ${data.frommid} </div>
									<div class="contentdate">
										${메시지타입구분( data.msg ) }
										<div class="date"> ${data.time} </div>
									</div>
								</div>
							</div>`
	}
	// ------------------ 스크롤 하단으로 내리기 ---------------- //
/*
	let top = contentbox.scrollTop // 현재 스크롤의 상단 위치 좌표
	let height = contentbox.scrollHeight; // 현재 스크롤 전체의 높이 [ 기본 값 : contentbox height ];
*/ 
	// 스크롤 막대의 상단 위치를 스크롤 막대의 가장 하단 위치로 대입
	 contentbox.scrollTop = contentbox.scrollHeight;	
}


// 5. 서버와 연결이 끊겼을 때 [ 따로 작성할 필요가 없으면 하지 않아도 됨. ]
function 연결해제(e){
	console.log('연결해제')
	// 이미 세션이 종류 후에 발생하는 함수이므로 아래 코드는 다른 세션에게 전달 불가능
	// -> 그래서 서버가 종료 코드를 보내줘야함 Chatting 60 번째 line
	// 자료보내기( memberInfo.mid+"님 이 채팅방에 나갔습니다." , "alarm" )
	
}

// 6. 엔터키를 눌렀을때
function enterkey() {
	if(window.event.keyCode == 13 ){
		보내기();
	}
}

// 7. 이모티콘 출력
getemo();
function getemo(){
	let html = '';
	for ( let i = 0 ; i<=43; i++ ){
		html += `<img onclick="자료보내기(${i} , 'emo')" alt="" src="/jspweb/img/imoji/emo${i}.gif" width="70px">`
	}
	document.querySelector('.enolist').innerHTML = html;
}

// 8. 타입형 메시지 전송하기
function 자료보내기( msgbox , type ){
		let msg = {
			type : type ,
			msgbox : msgbox
		}	
		
	클라이언트소켓.send( JSON.stringify( msg ) ); // ---> @OnMessage
}
/* 
	클라이언트 소켓 통신 결과
		클라이언트소켓.onclose = function(e){ console.log('연결해제'); }
			vs
		클라이언트소켓.onclose = (e) => { console.log('연결해제'); }
			vs
		function 함수명( e ){ console.log('연결해제'); }
		클라이언트소켓.onclose = (e)=>{ 함수명(e) }
	
	AJAX 필드
		// 통신 결과
		success : function(r){ }
		success : (r) => { }
*/

