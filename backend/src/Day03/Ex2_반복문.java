package Day03;

public class Ex2_반복문 {
	public static void main(String[] args) {
		
		// 1. [ p.124 ]
		int sum = 0;
		sum = sum + 1;
		sum = sum + 2;
		sum = sum + 3;
		sum = sum + 4;
		sum = sum + 5;
		
		for (int i = 1 ; i<=5; i++) { sum = sum + i; }
		
		// 2. [ p.125 ]
		for(int i = 1; i<=10; i++ ) {
			System.out.println(i + " ");
		}
		System.out.println();
		
		// 3. [ p.126 ] 1~100 까지 누적합계
		int total = 0;
		for ( int i = 1; i<=100; i++) {
			//i는 1부터 100까지 1씩증가 반복
			System.out.println(i + " ");
			total += i; // sum = sum + i
		}
		System.out.println("\n총 누적합계 : " + total);
		
		
		// 4. [ p.127 ] 0.1 ~ 1.0 사이의 실수 출력
		for(float x=0.1f; x<=1.0f; x+=0.1f) {
			System.out.println(x);
		}
		
		// 5. [ p.128 ] for중첩 : 구구단
		for (int m =2; m <= 9; m++) {
			System.out.println( m + "단");
		for (int n=1; n <= 9; n++) {
			System.out.println( m + " x " + n + " = " + (m*n));
			}
		}
		
		// 6. [ p.134 ] break; = 반복문 탈출 return; = 함수종료
		while( true ) { // 무한루프
			int num = (int)(Math.random()*6)+1;
			System.out.println( num );
			if ( num == 6 ) {
				System.out.println("while{ } 탈출");
				break; // 가장 가까운 반복문 탈출
			}
		}
		
		// 7. [ p. 135 ] break;
		첫번째for : for ( char upper = 'A'; upper <='Z'; upper++ ) {
			System.out.println(upper);
			
		두번째for :for( char lower ='a'; lower <= 'z' ; lower++ ) {
				System.out.println("\t" + lower);
				if ( lower == 'g') {
					
					break 첫번째for;
				}
			}
		}
		
		// 8. [ p.136 ] continue
		forname : for ( int i = 1; i <= 10; i++ ) {
			if( i%2 !=0 ) {
				// continue;	  // 가장 가까운 반복문[증감식]으로 이동
				continue forname; // 특정 반복문[증감식]으로 이동
			}
		System.out.println(i + " ");
	}
		
		
		
		
		
		
		
		
		
		
		
		
		
	}
}
