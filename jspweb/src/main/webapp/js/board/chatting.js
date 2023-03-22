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
function 서버소켓연결(){ contentbox.innerHTML += `<div> ------ ${memberInfo.mid}님이 채팅방 입장 ------ </div> ` } 

// 3. 클라이언트소켓이 서버에게 메시지를 보내기 [ @OnMessage ]
function 보내기(){
	let msgbox = document.querySelector('.msgbox').value
	// * 메시지 전송하기 *
	클라이언트소켓.send( msgbox );	// ---> @OnMessage
	
	document.querySelector('.msgbox').value = '';
}

// 4. 서버로부터 메시지가 왔을때 메시지 받기 -> 매개변수 필수! 여기에선 e 를 말함
function 메시지받기(e) { // <---- getBasicRemote().sendText(msg)
	console.log(e);
	console.log(e.data) // 문자열 json -> 객체 json 형변환 시켜야함!!
	console.log( JSON.parse( e.data ) ); // JSON.parse ( json으로 형변환 시킬 데이터 );
	contentbox.innerHTML += `<div> ${e.data} </div>`
}

// 5. 서버와 연결이 끊겼을 때 [ 따로 작성할 필요가 없으면 하지 않아도 됨. ]
function 연결해제(e){
	console.log('연결해제')
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

