package 과제.과제6;

public class 실행 {
	
	public static void main(String[] args) {
		
		Car car = new Car(); 
		for( int i = 1 ; i<=10 ;i++ ) { // i는 1부터 10까지 반복
			int result =  car.run(); // 달리다가 펑크가 나서 멈춘 결과의 반환값 전
			if( result == 1 ) car.frontLeftTire = new HankookTire( 15 , "앞왼쪽" ); 
			if( result == 2 ) car.frontRightTire = new HankookTire( 13 , "앞오른쪽" );
			if( result == 3 ) car.backLeftTire = new KumhoTire( 14 , "뒤왼쪽" );
			if( result == 4 ) car.backRightTire = new KumhoTire( 17 , "뒤오른쪽" );
		} // for 
		
	}
}
