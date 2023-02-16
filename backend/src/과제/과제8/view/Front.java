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
			
		}
	}
	
	// 2. 회원가입 페이지
	void signup() {
		
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
