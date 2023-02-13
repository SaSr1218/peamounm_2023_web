package Day09.Ex6_p245;

public class 실행 {
	public static void main(String[] args) {
		
		// 1. 인스턴스멤버 호출
			// 객체 -> 인스턴스멤버 호출
		Calculator myCal = new Calculator();
		System.out.println( myCal.no );	
		myCal.no = 10;
		System.out.println( myCal.getNo() );
		
			// 2. 정적멤버 호출
		System.out.println( Calculator.pi );
		double result1 = 10 * 10 * Calculator.pi;
		System.out.println(Calculator.puls( 10, 5 ));
		System.out.println(Calculator.minus( 10, 5 ));
		
		System.out.println( Calculator.info );

	}
}
