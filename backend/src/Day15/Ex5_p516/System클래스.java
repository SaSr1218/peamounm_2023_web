package Day15.Ex5_p516;

import java.util.Properties;
import java.util.Set;

public class System클래스 {
	public static void main(String[] args) {
		
		long time1 = System.nanoTime();	// 현재시간을 나노초로 표현
		System.out.println( time1 );
		int sum = 0;
		for( int i = 1 ; i<=1000000 ; i++ ) {	// 백만번 반복 [ CPU마다 다름 ]
			sum += i;	// i를 sum에 누적합계 더하기
		}
		long time2 = System.nanoTime(); // 현재시간을 나노초로 표현
		System.out.println( ( time2-time1 ) + "나노초가 소요 되었습니다.");
		
		// p.518
		System.out.println("---------------------------");
		System.out.println(" key: value");
		System.out.println("---------------------------");
		Properties props = System.getProperties();	// 모든 시스템 속성 호출
		
		for ( Object obj : props.keySet() ) {	// 시스템 속성들의 키를 반복문 돌리기
			System.out.println( 				
					
						(String)obj + "                  " +	// 해당 키를 문자열 변환
						System.getProperty( (String)obj )		// 해당 키를 이용한 시스템 속성 정보 호출
					
					);
		}
		
	}
}

// 중요 경로 -> java.home -> C:\Users\cntjr\.p2\pool\plugins\org.eclipse.justj.openjdk.hotspot.jre.full.win32.x86_64_17.0.5.v20221102-0933\jre
// 중요 경로 -> user.home -> C:\Users\cntjr
// 중요 경로 -> user.dir 

/*
 
 	*밀리초 -> 							1/1000
 		마이크로초 -> 						1/1,000,000,000
 			*나노초 	-> 					1/1,000,000,000,000
 				피코초 -> 				1/1,000,000,000,000,000
 					펨토초 -> 			1/1,000,000,000,000,000,000
 						아토초 -> 		1/1,000,000,000,000,000,000,000
 							젭토초 -> 	1/1,000,000,000,000,000,000,000,000
 								욕토초	1/1,000,000,000,000,000,000,000,000,000
 	
 
 
 
 
 */