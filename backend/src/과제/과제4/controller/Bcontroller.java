package 과제.과제4.controller;

import java.util.ArrayList;

import 과제.과제4.model.Board;

public class Bcontroller {
	ArrayList<Board> boardList = new ArrayList<>();
	
	// 1. 게시물 목록 전달
	public ArrayList<Board> mboard() {
		return boardList;
	}
	
	
	// 2. 글쓰기
	public int writeBoard( String title, String content, String writer) {
		int size = boardList.size();
		
		Board board = new Board( writer, 0, title, content);
		
		boardList.add(board);
		
		if(size < boardList.size()) {
			return 0;
		} else {
			return 1;
		}		
		
	}
	
	// 3. 글 보기
	public Board viewBoard( int index ) {
		boardList.get(index).view += 1;
		
		Board board = new Board( index , boardList.get(index).view );
		
		return boardList.get(index);
	}
	
	
	
}
