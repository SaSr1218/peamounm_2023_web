package model.dto;

import java.text.SimpleDateFormat;
import java.util.Date;

import javax.websocket.Session;

import controller.admin.Chatting;
import model.dao.MemberDao;

public class MessageDto {
	
	// private Session session;	// 메시지 보낸세션
	private String frommid;	 	// 메시지 보낸회원아이디
	private String fromimg; 	// 메시지 보낸회원프로필
	private String msg;			// 메시지 내용
	private String time;		// 메시지 보낸시간
	// private String 방번호pk;
	
	public MessageDto() {}
	
	// 클라이언트에게 메시지 전송할때 사용하는 생성자
	public MessageDto(Session session, String msg ) {
		super();
		// this.session = session;
		this.msg = msg;
		
		// 메시지를 보낸 클라이언트세션을 통해서 회원아이디 얻기
		for ( ClientDto dto : Chatting.접속명단 ) {
			if ( dto.getSession() == session ) {
				this.frommid = dto.getMid();
				// 보낸 사람의 프로필 얻기		
				this.fromimg = MemberDao.getInstance().getMember( this.frommid ).getMimg() ;
				// 보낸 날짜[메시지객체] 생성된 날짜 (1번 방법)
				this.time = new SimpleDateFormat("hh:mm:ss").format(new Date() ); 
			
			} // if e
		
		} // for e
	}// session , msg 담은 생성자 end
	
	/*
		// (2번 방법) 오늘 객체를 정의한 형식으로 형변환
		Date date = new Date();
		SimpleDateFormat sdf = new SimpleDateFormat("hh:mm:ss");
		tihs.time = sdf.format( date )
	*/



	public String getFrommid() {
		return frommid;
	}

	public void setFrommid(String frommid) {
		this.frommid = frommid;
	}

	public String getFromimg() {
		return fromimg;
	}

	public void setFromimg(String fromimg) {
		this.fromimg = fromimg;
	}

	public String getMsg() {
		return msg;
	}

	public void setMsg(String msg) {
		this.msg = msg;
	}

	public String getTime() {
		return time;
	}

	public void setTime(String time) {
		this.time = time;
	}

	// session 없는 toString
	@Override
	public String toString() {
		return "MessageDto [frommid=" + frommid + ", fromimg=" + fromimg + ", msg=" + msg + ", time=" + time + "]";
	}

	
	
	
	
}
