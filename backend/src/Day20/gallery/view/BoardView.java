package Day20.gallery.view;

import java.util.ArrayList;
import java.util.Scanner;

import Day20.gallery.controller.BController;
import Day20.gallery.controller.MController;
import Day20.gallery.model.Dao.BoardDao;
import Day20.gallery.model.Dto.BoardDto;
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
			else if ( ch > 0 ) { boardPrint( ch ); }  				

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

	// 4. 게시글 추가하기
	public void boardAdd( int cno ) {
		System.out.println("===================== 게시물 쓰기 =============");
		System.out.println("제목 : "); String btitle = scanner.next();
		System.out.println("내용 : "); String bcontent = scanner.next();
		
		boolean result = BController.getInstance().boardAdd(btitle, bcontent , cno);
		if ( result ) { System.out.println("[글등록성공]");}
		else { System.out.println("[글등록실패]");}
		
	} // boardAdd end
	
	// 5. 최신 글 3개 출력 페이지
	public void boardPrintRecent() {
		System.out.println("===================== 최신 글 =============");
		ArrayList<BoardDto> blist = BController.getInstance().boardPrintRecent();
		System.out.printf("%10s\t%10s\t%10s\t%10s\t%10s\t%10s \n" ,
				"번호" , "제목" , "작성자" , "조회수" ,  "갤러리", "작성일");
		for ( BoardDto dto : blist ) {
			System.out.printf("%10s\t%10s\t%10s\t%10s\t%10s\t%10s \n" ,
					dto.getBno() , dto.getBtitle() , dto.getMid() ,
					dto.getBview() , dto.getCname() , dto.getBdate() );
		} // for end
	} // boardPrintRecent end
	
	// 6. 선택한 갤러리의 모든 게시물 페이지
	public void boardPrint(int cno) {
		System.out.println("===================== 선택한 갤러리=============");
		ArrayList<BoardDto> blist = BController.getInstance().boardPrint(cno);
		System.out.printf("%10s\t%10s\t%10s\t%10s\t%10s \n" ,
				  "번호" , "제목" , "작성자" , "조회수" , "작성일");
		for ( BoardDto dto : blist ) {
			System.out.printf("%10s\t%10s\t%10s\t%10s\t%10s \n" ,
					dto.getBno() , dto.getBtitle() , dto.getMid() ,
					dto.getBview() , dto.getBdate() );
		}// for end
		
		while( true ) {
			System.out.println("갤러리 메뉴 : 1.뒤로가기 2.게시물쓰기 3.게시물보기 : ");
			int ch = scanner.nextInt();
			if ( ch == 1 ) { break; }
			else if ( ch == 2 ) { boardAdd( cno ); }
			else if ( ch == 3 ) { 
				//미구현
			}
		} // while end	
	} // boardPrint end
	
	
}





