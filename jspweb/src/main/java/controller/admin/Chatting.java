package controller.admin;

import java.io.IOException;
import java.util.ArrayList;

import javax.websocket.*;
import javax.websocket.server.PathParam;
import javax.websocket.server.ServerEndpoint;

import com.fasterxml.jackson.databind.ObjectMapper;

import model.dto.ClientDto;
// URL 매개변수받을때 : /경로/{매개변수1}/{매개변수2}					--> @PathParam("경로상의매개변수명") 타입 변수명
			/*
				/jspweb/chatting/qwe123
				/jspweb/chatting/{ mid }
				@PathParam("mid") String 매개변수;
				매개변수 = qwe123
			*/
import model.dto.MessageDto;

// URL 매개변수받을때 : /경로?매개변수명 = 데이터 & 매개변수명2 = 데이터	--> request.getParameter("매개변수명")



@ServerEndpoint("/chatting/{mid}") // 해당 클래스를 서버소켓[종착점]으로 만들기
								   // URL : /경로/{매개변수1}/{매개변수2}
public class Chatting {
	
	// *-* 접속한 클라이언트명단[목록] ( 클라이언트소켓 여러개 저장 )
	public static ArrayList<ClientDto> 접속명단 = new ArrayList<>();
	
	// 클라이언트 소켓이 접속했을때 실행되는 메소드
	@OnOpen		// session [ 접속한 클라이언트소켓 객체 ] // 서버 엔드포인트의 URL 매개변수 [ @PathParam ] 가져오기
	public void Onopen( Session session , @PathParam("mid") String mid ) {
		System.out.println("클라이언트 소켓 들어옴");
		System.out.println( session );
		
		// 접속한 클라이언트소켓들을 보관 
		ClientDto clientDto = new ClientDto(session, mid);
		접속명단.add( clientDto );
	}
	
	@OnClose	// 클라이언트 소켓이 나갔을때
	public void onClose ( Session session ) {
		System.out.println("클라이언트 소켓 접속해제");
		// 접속이 끊긴 세션의 dto 찾아서 제외
		for ( ClientDto dto : 접속명단) {
			// 회원명단에서의 세션과 접속이 끊긴 세션과 일치하면
			if ( dto.getSession() == session ) {
				접속명단.remove(dto); // 접속명단에서 제거
			}
		}
		
	}
	
	// 클라이언트 소켓이 메시지를 보냈을때[ 서버가 메시지 받기 ]
	@OnMessage
	public void onMessage( Session session , String msg ) throws Exception {
		System.out.println("클라이언트 웹소켓이 메시지를 보냈다. [ 서버가 메시지를 받았다.]");
		System.out.println( msg );
		
		// 메시지 형식 구성
		MessageDto messageDto = new MessageDto( session , msg );
			System.out.println( "messageDto : " + messageDto.toString() );
		
		// 메시지 받는 프로그램[JS] : JSON으로 형변환
		ObjectMapper mapper = new ObjectMapper(); // mapper는 Session 객체를 json 형식 변환이 불가능해서
		String json = mapper.writeValueAsString( messageDto );
			System.out.println( "json : " + json );
		
		// * 서버가 클라이언트 소켓에게 메시지를 보내기
		// 현재 서버소켓과 연결된 클라이언트소켓 모두에게 서버가 받은 내용물 전달
		for ( ClientDto dto : 접속명단 ) {
										// json형식[모양]의 [타입]은 <문자열>로 전송된다!
			dto.getSession().getBasicRemote().sendText( json ); // --> 클라이언트소켓.onmessage
		}
		
	}
	
	
	
	
}
