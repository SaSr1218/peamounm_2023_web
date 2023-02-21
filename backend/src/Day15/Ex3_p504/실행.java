package Day15.Ex3_p504;

public class 실행 {
	public static void main(String[] args) {
		
		SmartPhone myPhone = new SmartPhone("삼성전자" , "안드로이드");
		
		System.out.println( myPhone.toString() );
		
		System.out.println( myPhone ); // 객체 출력 기본값이 toSring() 포함임!
		
	}
}
