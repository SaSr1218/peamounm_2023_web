package 과제.과제4_싱글톤.controller;
/*
 	컨트롤(이벤트)설계	->>> 설계먼저 시작해야함! 인수, 반환값 나누기!!!
 	1. 회원가입
 		인수 : id, pw, confirmpw, name, phone		반환 : 0성공 , 1:실패
 	2. 로그인
 		인수 : id, pw								반환 : i:회원번호, -1:비밀번호틀림 , -2:아이디 
 	3. 아이디찾기
 		인수 : name, phone						반환 : 찾은아이디, null
 	4. 비밀번호 찾기
		인수 : id, phone							반환 : 찾은비밀번호, null
	5. 로그아웃 처리								
 		인수 : x									반환 : true 성공, false 실패
	
 */

import java.util.ArrayList;

import 과제.과제4_싱글톤.model.Member;

public class Mcontroller {

	// * 싱글톤 : 다른곳에서 해당 객체들 공유 메모리[멤버=필드, 메소드]
		// 1. 본인 클래스로 본인 객체 만들기(외부 차단 -> private 추가)
	private static Mcontroller mc = new Mcontroller();
		// 2. 외부에서 생성자를 사용할 수 없도록 생성자에 private 다 추가
	private Mcontroller() {}
		// 3. 객체는 외부로부터 차단하고, getInstance 함수를 통해 객체를 내보낸다. [유효성검사 위해]
	public static Mcontroller getInstance() {
		return mc;
	}
		// 4. 외부에서 getInstance 함수를 사용하려면 객체가 필요한데.. 외부에서 객체 못 만드니까 정적멤버로 쓰자!(static 다 추가)
	
	// DB 대신 ArrayList 생성
	private ArrayList<Member> memberDB = new ArrayList<>();
	// 로그인 한 회원의 객체를 저장 [ *동시 접속시 문제점 발생!! ]
		// 사용목적 : 페이지가 바뀌더라도 정보 저장 [ *메소드가 종료되더라도 정보는 저장 ]
	private Member logSession = null;
	
	public Member getLogSession() {
		return logSession;
	}
	
	// 1. 회원가입 처리
	public int signup ( String id, String pw, String confirmpw , String name , String phone) {
		// 1. 유효성검사
		if( !pw.equals(confirmpw) ) { return 1;}
		// 2. DB에 저장
			// 1. 객체화 [ 입력받은 데이터 4개와, 회원마다 글 저장할 리스트 메모리 초기
		Member m = new Member( id, pw, name, phone , new ArrayList<>() ); // &_&
				// id , pw , name , phone : 스택영역에 저장된 힙주소 전달
				// new ArrayList<>() : 힙영역에 생성된 힙주소 전달
			// 2. 리스트에 저장
			memberDB.add(m);
		return 0;
	}
	
	// 2. 로그인 처리
	public int login ( String id , String pw ) {
		
		for ( int i = 0 ; i<memberDB.size(); i++ ) {
			if(memberDB.get(i).getId().equals(id) ) { // i번째 인덱스의 아이디와 입력받은 아이디가 같으면
				if( memberDB.get(i).getPw().equals(pw) ) {
					// i 번째 인덱스 비밀번호와 입력받은 비밀번호가 같으면 로그인 성공 [ !로그인 성공시 흔적/식별 ]
					logSession = memberDB.get(i); // 로그인 성공한 회원 객체체를 필드에 저장
					return i;
				}else {
					return -1;
				}
			}
		}
		return -2; // 
	}
	
	// 3. 아이디찾기 처리
	public String findId( String name , String phone ) {
		for ( Member m : memberDB ) { // index가 필요없을때 향상된 for문 사용
			if( m.getName().equals(name) && m.getPhone().equals(phone) ) {
				return m.getId();
			}
		}
		return null;
	}
	
	// 4. 비밀번호찾기 처리
	public String findPw( String id, String phone ) {
		for ( Member m : memberDB ) {
			if( m.getId().equals(id) && m.getPhone().equals(phone) ) {
				return m.getPw();
			}
		}
		return null;
	}
	
	// 5. 로그아웃 처리
	public boolean logOut() {
		logSession = null; // 필드에 저장된 로그인 성공한 회원 객체를 지우기 [ null 대입 = GC가 메모리 자동제거 ] 
		return true;
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}
