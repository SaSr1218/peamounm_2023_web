package 과제.과제8.view;

import java.util.Scanner;

import 과제.과제8.controller.Mcontroller;


public class Front {
	
	Scanner scanner = new Scanner(System.in);
	Mcontroller mc = new Mcontroller();
	
	// 1. 메인페이지
	public void index() {
		while(true) {
			System.out.println(" ************이젠 중고************ ");
			System.out.println(" 1. 회원가입 2. 로그인 3. 아이디찾기 4. 비밀번호찾기");	// 즐겨찾기 품목 추가??
			System.out.println("메뉴 선택 : ");int ch = scanner.nextInt();
			if ( ch == 1 ) { signup(); }
			else if ( ch == 2 ) { login(); }
			else if ( ch == 3) { findId(); }
			else if ( ch == 4 ) { findPw(); } 
			else { System.err.println("메뉴선택을 [1] ~ [4]번 안에서 해주세요."); }
		}
	}
	
	// 2. 회원가입 페이지
	void signup() {
		System.out.println("아이디 : ");		String id = scanner.next();
		System.out.println("비밀번호 : ");		String pwd = scanner.next(); 
		System.out.println("비밀번호 확인 : ");	String confirmpwd = scanner.next();
		System.out.println("이름 : ");		String name = scanner.next();
		System.out.println("전화번호 : ");		String phone = scanner.next();
		
		
	}
	
	// 3. 로그인 페이지
	void login() {
		
	}
	
	// 4. 아이디 찾기 페이지
	void findId(){
		
	}
	
	// 5. 비밀번호 찾기 페이지
	void findPw() {
		
	}
	
	// 6. product로 화면 이동
	public void product( ) { }
	
	
	
	
	
	
	
	
	
	
	
	
} // class e
