package Day09.Ex10_게시판.controller;

import java.util.ArrayList;
import java.util.Date;

import Day09.Ex10_게시판.model.Board;

public class Bcontroller {

	// 1. 싱글톤 객체 
	private static Bcontroller bc = new Bcontroller(); // private라 못 나감.
	private Bcontroller() {}
	public static Bcontroller getInstance() { // getInstance : bc를 나가게 해줌.(public)
		return bc;
	}
	
	// 필드 
	private ArrayList<Board> boardDb = new ArrayList<>(); // 필드는 보통 private로 묶음.
	
	// 2. 쓰기 처리 함수 
	public boolean write( String title , String content , 
			String writer , String password , 
			Date date , int view  ) {
		// 1. 유효섬검사 [ 생략 ]
		// 2. 저장[ DB 대신 리스트 ]
			// 1. 객체화[ ]
		Board board = new Board(title, content, writer, 
								password, date, view);
			// 2. 객체를 리스트 담기 
		boardDb.add(board);
		// 3. 결과 반환
		return true;	// return 값은 위랑 묶어야함. boolean -> true
	}
	// 3. 출력 처리 함수 
	public ArrayList<Board> print() {
		// 유효성검사 [ 생략 ] 검색,페이징처리 
		
		return boardDb;
	}
	
	// 4. 특정게시물[1개->object] 출력 처리 함수
	public Board view( int bno ) {
		// * 조회수 올리기	// 객체명.set필드명( 객체명.get필드명()+1 );
		boardDb.get(bno).setView( boardDb.get(bno).getView()+1 );
		return boardDb.get(bno);
	}
}













