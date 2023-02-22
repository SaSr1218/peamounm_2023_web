package 과제.과제11;

import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Scanner;

import Day15.Ex9_MVC패턴.MemberDto;

public class View {
	
	// 싱글톤
	private static View view = new View();
	public View() { }
	public static View getInstance() { return view; }
	
	// 필드
	Scanner scanner = new Scanner(System.in);
	
	// 1. 메인화면
	public void index() {
		while(true) {
			try {
			System.out.println(" 1.등록 2. 출력 3. 수정(이름,가격) 4. 수정(재고) 5. 삭제");
			int ch = scanner.nextInt();
			if ( ch == 1 ) { signup(); }
			else if ( ch == 2 ) { list(); }
			else if ( ch == 3 ) { update(); }
			else if ( ch == 4 ) { update2(); }
			else if ( ch == 5 ) { delete(); }
			} catch (InputMismatchException e) {
				System.out.println("잘못된 입력입니다.");
				scanner = new Scanner(System.in);
			}catch (Exception e) {
				System.out.println("프로그램내 오류 발생 : 관리자에게 문의");
			}
		}
	} // index end
	
	// 2. 제품 등록
	public void signup() {
		System.out.println("---------제품등록-------");
		System.out.println("제품명 : "); 	String pname = scanner.next();
		System.out.println("제품가격 : ");	int pprice = scanner.nextInt();
		System.out.println("제품재고 : ");	int pbox = scanner.nextInt();
		
		boolean result = Controller.getInstance().signup(pname, pprice, pbox);
		
		if ( result ) { System.out.println("제품등록 성공");}
		else { System.out.println("제품등록 실패"); }
		
	} // signup end
	
	// 3. 제품 전체출력 ( 제품번호	제품이름	제품가격	제품재고 )
	public void list() {
		System.out.println("----------모든제품출력--------");
		System.out.printf("%2s \t %8s \t %10s \t %10s \n" , "제품번호" , "제품명" , "제품가격" , "제품재고" );
		
		ArrayList<ProductDto> result = Controller.getInstance().list();
		
		for ( int i = 0 ; i < result.size(); i++ ) {
			System.out.printf("%2s \t %8s \t %11s \t %11s \n" ,
					result.get(i).getPno() , result.get(i).getPname() , result.get(i).getPprice() , result.get(i).getPbox() );	
		}
	}// list end
	
	// 4. 제품 수정(이름,가격)
	public void update() {
		System.out.println("-----------제품수정(이름,가격)---------");
		System.out.println("수정할 제품번호 : "); int pno = scanner.nextInt();
		System.out.println("수정될 제품이름 : "); String pname = scanner.next();
		System.out.println("수정될 제품가격 : "); int pprice = scanner.nextInt();
		
		boolean result = Controller.getInstance().update(pno, pname, pprice);
		if ( result ) { System.out.println("제품 이름, 가격 수정 성공");}
		else { System.out.println(" 제품 이름, 가격 수정실패 - 관리자에게 문의바람 "); }
		
	}
	
	// 5. 제품 수정(재고)
	public void update2() {
		System.out.println("-----------제품수정(재고)-----------");
		System.out.println("수정할 제품번호 : "); int pno = scanner.nextInt();
		System.out.println("수정할 제품재고 : "); int pbox = scanner.nextInt();
		
		boolean result = Controller.getInstance().update2(pno, pbox);
		if ( result ) { System.out.println("제품 재고 수정 완료"); }
		else { System.out.println("제품 재고 수정실패 - 관리자에게 문의바람"); }
	}
	
	// 6. 제품 삭제
	public void delete() {
		System.out.println("------------제품삭제---------");
		System.out.println("삭제할 제품번호 : "); int pno = scanner.nextInt();
		
		boolean result = Controller.getInstance().delete(pno);
		if ( result ) { System.out.println(" 제품삭제성공");}
		else { System.out.println("제품 삭제 실패 - 관리자에게 문의바람");}
	}
	
	
	
	
	
	
	
	
	
	
	
	
}
