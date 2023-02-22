package Day15.Ex9_MVC패턴;

public class Start {
	public static void main(String[] args) {
		
		View.getInstance().index();
		
	}
}

/*
 
 	내부에서 메소드[멤버]호출 방법
 		1. 메소드명();
 	외부에서 메소드[멤버] 호출 방법
 		1. 인스턴스 메소드
 			클래스명 객체명 = new 생성자();		
 			객체명.메소드명();			-> 재사용 가능
 				vs
 			new 생성자().메소드명(); 	-> 단발성
 			
 		2. 정적 메소드
 			클래스명.메소드명();
 			
 		3. 싱글톤내 메소드 호출
 			클래스명.get싱글톤.메소드();

			JVM
			[ 현재 사용중인 메모리(클래스) : 컴파일한 클래스 + import 클래스 ] // 메소드는 힙에 있는것임!
		
		메소드 영역							스택 영역			힙 영역
			- 전역에서 사용					- 기본자료형 데이터
										- 힙주소 저장
										
			: 클래스 멤버정보				클래스명 객체명    =    new 생성자();
			: static										new 생성자().메소드명;
				- 정적 메소드
			클래스명.메소드명()
			public static void index()
				- 정적 필드
			static 타입 필드명
			static View view						   =    new View();
 
 
 */