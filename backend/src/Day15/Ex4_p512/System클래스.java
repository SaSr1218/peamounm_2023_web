package Day15.Ex4_p512;

public class System클래스 {
	public static void main(String[] args) {
		
		System.out.println("출력");		// 콘솔에 출력
		System.err.println("에러");		// 콘솔에 error[빨강] 출력 ( 속도 차이 있음! out 사용 추천 )
		
		int value = 100;	// int에 100 대입 가능 [ 정수 ]
		// int value2 = "100";	// int에 "100" 대입 불가능 [ 문자니까 ] 
			// 문자열 --> 기본타입 변환
		int value3 = Integer.parseInt("100");	// "100" -> 100 변환
		
		try { // try{} 에 예외(=에러)가 발생할 것 같은 [*경험]
			int value4 = Integer.parseInt("1oo");	// "1oo" -> 변환 불가!
			
		}
		catch (Exception e) { // try{}에서 예외(에러) 발생했을때 catch{ 실행 } 
			System.err.println("에러 내용");
			System.err.println( e.getMessage() );
		}
		
		// 2. p514
		
		int speed = 0;
		int keyCode = 0;
		
		while(true) {
			
			if( keyCode != 13 && keyCode !=10 ) { // 엔터 [ 13, 10 ] 가 아니고
				if( keyCode == 49 ) { // 숫자1의 코드 = 49
					speed++;
				} else if ( keyCode == 50 ) { // 숫자 2 코드 = 50
					speed--;
				} else if ( keyCode == 51 ) { // 숫자 3코드 = 51
					// break;
					System.exit(0);	// 0 : 정상종료 , 1 or -1 : 비정상종료 -> 프로그램 아예 종료!
				}
				
				System.out.println("현재 속도 : " + speed);
				System.out.println("1. 증속 2. 감속 3. 중지");
				
			}
			
			try {
				keyCode = System.in.read(); // 입력받아 코드로 반환 [*예외처리 필수]
			} catch (Exception e) {
				
			}
		}
		// System.out.println("프로그램 종료");
		
	}
}
