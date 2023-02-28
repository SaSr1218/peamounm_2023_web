package Day20.gallery.controller;

import Day20.gallery.model.Dao.MemberDao;
import Day20.gallery.model.Dto.MemberDto;

public class MController {
	
	private static MController mcon = new MController();
	private MController() { }
	public static MController getInstance() { return mcon; }
	
	// 0. 로그인 섹션 값
	private int loginSession = 0;	// 로그인 된 회원번호 담기
	public int getLoginSession() { return loginSession; }
	public void setLoginSession(int loginSession) { this.loginSession = loginSession; }
	
	// 1. 회원가입 처리 [ 아이디중복체크 ]
	public int signup ( String mid , String mpw , String mname , String mphone ) {
			
		// 1. 유효성검사 [ 글자수 , 중복체크 등등 ]
			// 1. 아이디 중복체크
		if ( MemberDao.getInstance().idCheck(mid) ) {
			return 2;	// 중복된 아이디입니다. 함수 종료
		}
		// 2. 객체화
		MemberDto dto = new MemberDto(0, mid, mpw, mname, mphone);
		
		// 3. 회원가입 DB처리
		return MemberDao.getInstance().signup(dto);
		}
		
	// 2. 로그인 처리
	public boolean login( String mid , String mpw ) {
		// 1. 유효성검사
		
		// 2. 
		int result = MemberDao.getInstance().login(mid, mpw);
		
		// 3. 로그인증거 [ 로그인 정보 저장소 = 세션 ]
		if ( result == 0 ) { return false; }
		else {
			loginSession = result; // 반환된 회원번호를 세션에 저장
			return true;
			}
	}

	

}

