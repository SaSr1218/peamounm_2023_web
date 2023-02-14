package 과제.과제4_싱글톤.controller;
/*
 	
 	1. 글쓰기
 		인수 : title, content					반환 : true 성공, false 실패
 	2. 글출력 데이터 반환
 		인수 : x [페이징처리 , 검색처리]			반환 : 모든글이 담긴 arraylist<board>
 	3. 글상세 데이터 반환
 		인수 : 글번호							반환 : 글 하나 board
 		* 조회수 증가
 	4. 글삭제 처리								반환 : true 성공, false 실패
 		인수 : 글번호							
 	5. 글수정 처리								반환 : true 성공, false 실패
 		인수 : 글번호, 새로운제목, 새로운내용		

 
 */

import java.util.ArrayList;

import 과제.과제4_싱글톤.model.Board;

public class Bcontroller {

	private ArrayList<Board> boardDB = new ArrayList<>();
	
	// 1. 글쓰기
	public boolean write ( String title, String content ) {
		// 1. 유효성검사 [ 로그인 흔적 ] 
		if(Mcontroller.getInstance().getLogSession() == null) {
			return false; 	// 로그인 안된 경우 : false
		}
		// 2. DB 저장(로그인 된 경우)
			// 1. 객체화 [ 글 작성 : 입력받은 데이터2개, 초기값 0, 로그인한 멤버 회원 객체 ]
		Board board = new Board( title, content, 0 , Mcontroller.getInstance().getLogSession() ); // Mcontroller.getInstance().logSession = 글쓴이
			// 2. db에 저장
		boardDB.add(board);
			// 3. 멤버객체에 내가 쓴 글 등록
		Mcontroller.getInstance().getLogSession().getBoardlist().add(board);
		return true;
	}
	
	// 2. 글출력
	public ArrayList<Board> getList() {
		// 추후에 검색처리 , 페이징처리 등등 로직이 들어갈 예정
		return boardDB;
	}
	
	// 3. 글상세
	public Board getBoard( int bno ) {
		return boardDB.get(bno);	// 인수로 전달받은 인덱스[게시물번호]의 게시물을 반환
	}
	
	// 4. 글삭제
	public boolean delete ( int bno ) {
		boardDB.remove(bno);		// 인수로 전달받은 인덱스[게시물번호]의 게시물을 삭제
		return true;
	}
	
	// 5. 글수정
	public boolean update ( int bno, String title , String content ) {
		boardDB.get(bno).setTitle(title);		// 인수로 전달받은 인덱스[게시물번호]의 게시물 제목 수정
		boardDB.get(bno).setContent(content);	// 인수로 전달받은 인덱스[게시물번호]의 게시물 내용 수정
		return true;
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}
