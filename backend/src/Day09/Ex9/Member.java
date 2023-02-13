package Day09.Ex9;

/*

	싱글톤 : 프로그램내 하나의 객체를 가지는 패턴  -> 메모리 효율성을 위해 new를 너무 많이 쓰면 낭비.
		- 1. 클래스 내부에 객체 만들기
		- 2. 생성자 private(막기)
		- 3. 내부객체 반환해주는 함수 : getInstance()
		- * 함수호출시 객체가 필요하다. 외부에서 객체 생성을 금지. -> static(객체없이 사용 가능) -> 객체에도 static 붙여주기
*/



public class Member {
	
	// 1. 자신의 타입으로 객체 생성한다. // 2. private로  잠구기
	private static Member mem = new Member();	// 인스턴스 멤버(스태틱 넣어주기)
	// 3. 생성자도 private로 막는다.
	private Member() {}
	// 4.
	public static Member getInstance() {		
		return mem;
	}
	// 5. static 
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}
