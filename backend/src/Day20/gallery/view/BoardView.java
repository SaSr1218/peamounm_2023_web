package Day20.gallery.view;

import java.util.ArrayList;
import java.util.Scanner;

import Day20.gallery.controller.BController;
import Day20.gallery.controller.MController;
import Day20.gallery.model.Dto.CategoryDto;

public class BoardView {

	private static BoardView boardView = new BoardView();
	private BoardView() { }
	public static BoardView getInstance() { return boardView; }

	private Scanner scanner = new Scanner(System.in);
	
	// 1. 게시물 모든 게시물
	public void index() {
		while(true) {
			System.out.println("===================== 로그인 =============");
			// 최신 게시물 3개 출력
			
			
			categoryPrint(); // 모든 갤러리 출력
			
			System.out.println(" -1 : 로그아웃 /  0 : 갤러리추가 / 이동할 갤러리 번호 선택 : ");
			int ch = scanner.nextInt();
			if ( ch == -1 ) {
				MController.getInstance().setLoginSession(0); // 로그인세션 초기화(setter) 
				System.out.println("로그아웃 되셨습니다.");
				break;
			} 
			else if ( ch == 0 ) { categoryAdd(); }
			else if ( ch > 0 ) { }

			
			
		}
	}
	
	// 2. 카테고리 추가
	public void categoryAdd() {
		System.out.println("===================== 갤러리 추가 =============");
		System.out.println("추가할 갤러리 이름 : "); 
		scanner.nextLine();
		String cname = scanner.nextLine(); // 띄어쓰기 필요시
		
		boolean result = BController.getInstance().categoryAdd ( cname );
		
		if ( result ) { System.out.println("[등록성공] 갤러리가 추가 되었습니다. ");}
		else { System.out.println("[등록실패] 관리자에게 문의");}
	
	} // categoryAdd end
	
	// 3. 모든 카레고리 호출 [ 갤러리 1개 = dto 1개 / 갤러리여러개 = dto 여러개 = 배열 혹은 리스트 ] 호출
	public void categoryPrint() {
		// 1. 모든 카테고리 요청후 리스트에 담기
		ArrayList<CategoryDto> clist = BController.getInstance().categoryPrint();
		
		// 2. 출력
		/*
		for ( int i = 0 ; i <clist.size(); i ++ ) {
			System.out.println( clist.get(i).getCno() + " - " + clist.get(i).getCname() );
		}
		*/
		
		// 2. 출력2 : 향상된 for문 위의 for문과 똑같은 효과
		int count = 0;
		for ( CategoryDto dto : clist ) {
			System.out.print( dto.getCno() +" - "+ dto.getCname() + "\t");
			count++;
			if ( count % 4 == 0 ) System.out.println(); // 4개마다 줄바꿈
		}
		System.out.println();
		
	} // categoryPrint end


}





