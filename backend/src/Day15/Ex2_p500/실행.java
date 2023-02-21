package Day15.Ex2_p500;

public class 실행 {
	public static void main(String[] args) {
		
		// " " 안에 문자열은 같은 주소를 공유해서 쓴다!
		System.out.println("자바".hashCode() );
		System.out.println("자바".hashCode() );
		// 문자열일 경우 이렇게 비교하는 것 가능!(객체는 당연히 불가!)
		System.out.println("자바".hashCode() == "자바".hashCode() );
		
		Student s1 = new Student(1, "홍길동");
			System.out.println( s1 );
		Student s2 = new Student(1, "홍길동");
			System.out.println( s2 );
			
		System.out.println( s1.hashCode() );
		System.out.println( s2.hashCode() );
		System.out.println( s1.hashCode() == s2.hashCode() ); // 해시코드가 동일한지 검사
		System.out.println( s1.equals(s2) ); // 데이터가 동일한지 검사
	}
}
