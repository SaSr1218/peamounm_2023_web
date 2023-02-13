package 과제.과제4.view;
// * 입출력 : print , scanner (제어문, 반복문만 돌림, 실제 기능 x)

import java.util.Scanner;

import 과제.과제4.controller.Bcontroller;
import 과제.과제4.controller.Mcontroller;

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
		else if( ch == 2 ) { login();}
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
	String login() {
		System.out.println("ID : "); String id = scanner.next();
		System.out.println("PW : "); String pwd = scanner.next();
		
		int result = mc.login( id, pwd );
		if ( result >= 0 ) {
			System.out.println("로그인 성공");
			mboard(id);
		}else if ( result == -1) {
			System.out.println("비밀번호가 다릅니다.");
		} else if ( result == -2) {
			System.out.println("없는 회원입니다.");
		}
		return id;
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
	public void mboard( String id ) {
		System.out.println("------커뮤니티--------");
		System.out.println("번호\t조회수\t작성자\t제목");
		for( int i = 0 ; i < bc.mboard().size(); i++ ) {
			System.out.printf("%d\t%s\t%s\t%s\n", 
					i,
					bc.mboard().get(i).view, 
					bc.mboard().get(i).writer, 
					bc.mboard().get(i).title);
		}
		System.out.println("메뉴 > 1.글쓰기 2.글보기 3.로그아웃 ");
		int ch = scanner.nextInt();
		if ( ch == 1 ) { writeBoard(id);} // 글쓰기
		else if ( ch == 2 ) { viewBoard(id); } // 글보기
		else if ( ch == 3 ) { logOut(id); } // 로그아웃
		else { System.out.println("메뉴선택이 잘못되었습니다."); }
		
	}
	
	// 7. 글쓰기 함수
	public void writeBoard( String id ) {
		System.out.println("-----------글쓰기----------");
		System.out.println("제목 : "); String title = scanner.next(); scanner.nextLine();
		System.out.println("내용 : "); String content = scanner.nextLine();
	
		int result = bc.writeBoard( title, content, id );
		
		if ( result == 0 ) {
			System.out.println("게시물 등록 완료");
			mboard(id);
		} else if ( result == 1 ) {
			System.err.println("게시물 등록 실패");
		}
		
	}
	
	// 8. 글보기 함수
	public void viewBoard( String id ) {
		System.out.println("----------글보기---------");
		System.out.print("보고싶은 글번호 : "); int index = scanner.nextInt();
		Board select = bc.viewBoard(index);
		System.out.println("----------글상세---------");
		System.out.println("제목 : \t" + select.title);
		System.out.println("작성자 : \t" + select.writer );
		System.out.println("조회수 : \t" + select.view );
		System.out.println("내용 : \t" + select.content);
		
		System.out.println(" 메뉴 > 1.글삭제 2.글수정 3.뒤로가기 : ");
		int ch = scanner.nextInt();
		
		if ( ch == 1 ) { deleteBoard(id , index); }	// 글삭제
		else if ( ch == 2 ) { updateBoard(id, index); } // 글수정
		else if ( ch == 3 ) { mboard(id); } // 뒤로가기
		else { System.out.println("1~3번 중에 선택해주십시오.");}
	}

	// 9. 글삭제 함수
	public void deleteBoard( String  id, int index ) {
		int check = bc.check( id, index );
			if ( check == 0 ) {
				bc.deleteBoard(index);
				System.out.println("해당 게시물 삭제 완료");
			} else {
				System.out.println("해당 게시물 삭제 실패");
			}
			mboard(id);
	}
	
	// 10. 글수정 함수
	public void updateBoard ( String id, int index ) {
		int check = bc.check(id, index);
		if ( check == 0 ) {
			System.out.println("수정할 제목 : "); String title = scanner.next(); scanner.nextLine();
			System.out.println("수정할 내용 : "); String content = scanner.nextLine();
			
			bc.updateBoard(title, content, index);
		}else {
			System.out.println("해당 게시물 수정불가");
		}
		mboard(id);
	}
	
	// 11. 로그아웃 함수
	public void logOut( String id ) {
		index();
	}

	
	
	
	
	
	
	
	
	
	
	
	
	
} // class e
