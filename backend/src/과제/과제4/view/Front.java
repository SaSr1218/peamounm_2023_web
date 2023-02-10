package 과제.과제4.view;
// * 입출력 : print , scanner (제어문, 반복문만 돌림, 실제 기능 x)

import java.util.Scanner;

import 과제.과제4.controller.Bcontroller;
import 과제.과제4.controller.Mcontroller;

import 과제.과제4.model.Member;
import 과제.과제4.model.Board;


public class Front {
	
	Scanner scanner = new Scanner(System.in);
	Mcontroller mc = new Mcontroller();	// 각각 선언하면 번지가 다름! 공유하려면 위에 써야함.
	Bcontroller bc = new Bcontroller(); // b컨트롤러로 이동
	
	// 1. 메인페이지
	public void index() {
		while(true) {
		System.out.println("1. 회원가입 2. 로그인 3. 아이디찾기 4. 비밀번호찾기");
		int ch = scanner.nextInt();
		if( ch == 1 ) { signup(); }
		else if( ch == 2 ) { login(); }
		else if( ch == 3 ) { findId(); }
		else if( ch == 4 ) { findPw(); }
		else { System.err.println("잘못된 메뉴선택입니다.");}
		}
	}
	
	// 2. 회원가입 페이지
	void signup() {
		System.out.println("아이디 : ");		String id = scanner.next();
		System.out.println("비밀번호 : ");		String pwd = scanner.next(); 
		System.out.println("비밀번호 확인 : ");	String confirmpwd = scanner.next();
		System.out.println("이름 : ");		String name = scanner.next();
		System.out.println("전화번호 : ");		String phone = scanner.next();
		
		int result = 
				mc.signup( id , pwd, confirmpwd , name , phone );
		if ( result == 1 ) {
			System.out.println("[회원가입 실패]");
		} else if( result == 0 ) {
			System.out.println("[회원가입 성공]");
		}
	}
	
	// 3. 로그인 페이지
	void login() {
		System.out.println("ID : "); String id = scanner.next();
		System.out.println("PW : "); String pwd = scanner.next();
		
		int result = mc.login( id, pwd );
		if ( result >= 0 ) {
			System.out.println("로그인 성공");
			mboard();
			
		}else if ( result == -1) {
			System.out.println("비밀번호가 다릅니다.");
		} else if ( result == -2) {
			System.out.println("없는 회원입니다.");
		}
	}
	// 4. 아이디 찾기 페이지
	void findId() {
		System.out.println("이름 : ");	String name = scanner.next();
		System.out.println("전화번호 : ");	String phone = scanner.next();
		
		String result = mc.findId(name, phone);
		System.out.println( result );

	}

	// 5. 비밀번호 찾기 페이지
	void findPw() {
		System.out.println("아이디 : ");	String id = scanner.next();
		System.out.println("전화번호 : ");	String phone = scanner.next();
	
		String result = mc.findPw( id, phone );
		System.out.println( result );

	}

	// 6. board로 화면 이동
	void mboard() {
		System.out.println("------Board--------");
		System.out.println("화면 이동되었습니다.");
		
	}
	
	
} // class e
