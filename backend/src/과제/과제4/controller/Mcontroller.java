package 과제.과제4.controller;

import java.util.ArrayList;

import 과제.과제4.model.Member;

// * 처리/제어 담당 (함수)
public class Mcontroller {
	// * DB 대신할 [ 임시 데이터 저장소 만들기 ]
	ArrayList<Member> memberDb = new ArrayList<>();
	
	// 1. 회원가입 로직
	public int signup( String id , String pwd , String confirmpwd , String name , String phone ) {
		// 1. 유효성검사
		if( !pwd.equals(confirmpwd) ) { return 1; } // 회원가입실패 = 1번 
		// 2. 객체 만들기
		Member member = new Member(id, pwd, name, phone);
		// 3. db처리 [ 리스트 ]
		memberDb.add(member);
		return 0; // 회원가입성공 = 0번 . 이때 사용하는 것이 return(반환)
	}
	// 2. 로그인 로직
	public int login( String id , String pwd  ) {
		
		// * 모든 멤버들 중 동일한 아이디/비밀번호 찾기
		for ( int i = 0 ; i<memberDb.size(); i++ ) {
			if( memberDb.get(i).id.equals(id) ) {
				// 비밀번호가 같으면
				if ( memberDb.get(i).pwd.equals(pwd) ) {
					return i; // 회원번호[인덱스]
				}else {
					return -1; // index를 쓰고 있어서 0이하를 써야함.
				}
			} // if end
		} // for end
		return -2; // 아이디가 없음 // for문 다 방문했지만 동일한 아이디가 없었다.
	}

	// 3. 아이디 찾기 로직
	public String findId( String name, String phone ) {
	
		for ( int i = 0 ; i<memberDb.size(); i++ ) {
			// 이름이 같으면
			if(memberDb.get(i).name.equals(name)) {
				// 이름 같고 번호도 같으면
				if(memberDb.get(i).phone.equals(phone)) {
					return "찾은 아이디 : " + memberDb.get(i).id;
				} else {	
					return "전화번호가 틀립니다"; // 전화번호 오류
				}
			}
		}// for e		
	return "아이디가 다릅니다."; // 아이디 다름
	
	}
	/* 아이디 for문
	public String findId( String name, String phone);{
		for ( Member m : memberDb){
			if( m.name.equals(name) && m.phone.equals(phone) ) {
				return m.id;
			}
		}
		return null;
	}
	*/
		
	// 4. 비밀번호 찾기 로직
	public String findPw( String id, String phone ) {
		
		for ( int i = 0 ; i<memberDb.size(); i++ ) {
			// id 같으면
			if(memberDb.get(i).id.equals(id)) {
				// id 같고 번호도 같으면
				if(memberDb.get(i).phone.equals(phone)) {
					return "찾은 비밀번호 : " + memberDb.get(i).pwd;
				} else {	
					return "전화번호가 틀립니다"; // 전화번호 오류
				}
			}
		}// for e		
	return "id가 다릅니다."; // 아이디 다름
	}
}
