package 과제.과제4_싱글톤.model;

/*
 
 	model 관계
 		
 		1. 한명은 회원이 글 작성한다.
 		2. 회원은 여러개의 글을 작성할 수 있다.
 		* 양방향 설정 [ * toString 메소드 사용 불가 ] -> 스택오버플로 발생(StackOverflow)
 			- 객체호출시 -> 주소값
 			
 			- 스택영영		vs		힙영역
 			Board board = new Board();
 							32번지
 			
 			Member member = new Member() ;
 							33번지
 			
 			글쓰기 할때 양방향 설정
 				board.setMember( member ); 	32번지에 33번지를 대입
 				member.setBoard( board );	33번지에 32번지를 대입
 				
 				board 호출시 32번지가 가지고 있던 member 33번지 호출시 33번지가 가지고 있던 32번를 불러냄 --> 33번지 --> 32번지 순환참조!!
 				
 				board.getContent(); 처럼 해당 필드를 참조해야함!
 			
 			System.out.println( board ); 	
 			// 스택이[변수] 가지고 있는 주소 출력 -> 주소가 싫어서 toString으로 씀. 
 			주소 출력 자체가 toString을 쓴 것이다. 
 
 */

public class Board {
	
	// 1. 필드
	private String title;
	private String content;
	private int view;
	// 하나의 게시물[객체]가 하나의 member 객체를 가질 수 있다.
	private Member member;	
	// 2. 생성자
	
	public Board() {} 
	
	public Board(String title, String content, int view, Member member) {
		super();
		this.title = title;
		this.content = content;
		this.view = view;
		this.member = member;
	}

	// 3. 메소드
	@Override
	public String toString() {
		return "Board [title=" + title + ", content=" + content + ", view=" + view + ", member=" + member + "]";
	}
	
	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public int getView() {
		return view;
	}

	public void setView(int view) {
		this.view = view;
	}

	public Member getMember() {
		return member;
	}

	public void setMember(Member member) {
		this.member = member;
	}


	
	
}
