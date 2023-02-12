package 과제.과제4.model;

public class Board {
	// 1. 필드
	public String writer;
	public String title;
	public String content;
	public int view;
	public int no;
	
	// 2.생성자
	public Board() {}

	public Board(String writer, int view, String title, String content) {
		super();
		this.writer = writer;
		this.view = view;
		this.title = title;
		this.content = content;

	}

	public Board(int no, int view) {
		super();
		this.no = no;

		this.view = view;
	}
	
	// 3. 메소드
	
}
