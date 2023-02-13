package Day09.Ex10_게시판;

import Day09.Ex10_게시판.view.Front;

public class Start {
	public static void main(String[] args) {
		
		Front.getInstance().index();	// 원래 함수 호출시에는 객체가 필요. 싱글톤 객체를 만들어서 getInstance를 만들어놔서 보내줌.
		
	}
}

/*

	-Ex10_게시판 패키지
		controller 패키지
			Bcontroller.java
		model 패키지
			Board.java
		view 패키지
			Front.java
		Start.java
	- 1. start[main함수] 클래스가 front 클래스 호출
	- 2. front 클래스에서 사용자로부터 입·출력 받기
	- 3. Bcontroller 클래스에서 front로부터 전달받은 데이터를 처리/로직
	- 4. Board 클래스에서 게시물의 모델링


*/
