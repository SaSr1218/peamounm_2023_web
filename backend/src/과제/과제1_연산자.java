package 과제;

import java.util.Scanner;

public class 과제1_연산자 {
	public static void main(String[] args) {
		Scanner sa = new Scanner(System.in);
		
		/*
		  System.out.println("1번 문제"); System.out.println("|\\_/|");
		  System.out.println("|q p|   /}"); System.out.println("( 0 )\"\"\"\\");
		  System.out.println("|\"^\"`    |"); System.out.println("||_/=\\\\__|");
		 */
        
		/*
		  System.out.println("2번 문제"); System.out.println("작성자 : "); String 작성자 =
		  sa.next(); sa.nextLine(); System.out.println("내용 : "); String 내용 =
		  sa.nextLine(); System.out.println("날짜 : "); String 날짜 = sa.next();
		  System.out.println("------------------------방문록----------------------");
		  System.out.printf("%3s | %6s | %15s | %6s \n", "번호", "작성자", "내용", "날짜");
		  System.out.printf("%4d | %6s | %15s | %6s \n", 1, 작성자, 내용, 날짜);
		  System.out.println("--------------------------------------------------");
		 */
        
        
		/*
		  System.out.println("3번 문제"); System.out.println("기본급 : "); int 기본급 =
		  sa.nextInt(); System.out.println("수당 : "); int 수당 = sa.nextInt();
		  System.out.printf("%.0f \n" , (기본급+수당)-(기본급*0.1) ); // 2번쨰 방법 : int 실수령액 =
		  기본급 + 수당 - (int)( 기본급*0.1 ); or 아예 double 실수령액으로 정의
		 */        
		
		/*
        System.out.println("4번 문제");
        System.out.println("금액 : ");
        int 금액 = sa.nextInt();
        System.out.println("십만원" + (금액/100000) +"장" );
        금액 -= (금액/100000) * 100000;
        System.out.println("만원" + (금액/10000)  +"장"  );
        금액 -= (금액/10000) * 10000;
        System.out.println("천원" +  (금액/1000) +"장" );
        금액 -= (금액/1000) * 1000;
        System.out.println("백원" + (금액/100)  +"개" );
        */
        
		/*
        System.out.println("5번 문제");
        System.out.println("7의 배수 찾기");
        int 배수 = sa.nextInt();
        System.out.println((배수%7 == 0 ? "O" : "X"));
        */ 
        
		/*
        System.out.println("6번 문제");
        System.out.println("홀짝 찾기");
        int 배수 = sa.nextInt();
        System.out.println((배수%2 == 0 ? "짝수" : "홀수"));
        */
        
		/*
        System.out.println("7번 문제");
        System.out.println("7배수이면서 짝수 찾기");
        int 배수 = sa.nextInt();
        System.out.println( (배수%7 == 0 && 배수%2 ==0 )? "O" : "X" );
        */
        
		/*
        System.out.println("8번 문제");
        System.out.println("7배수이거나 홀수 찾기");
        int 배수 = sa.nextInt();
        System.out.println( (배수%7 == 0 || 배수%2 ==1 )? "O" : "X" );
        */
        
		/*
        System.out.println("9번 문제");
        System.out.println("a 값 : "); int a = sa.nextInt();
        System.out.println("b 값 : "); int b = sa.nextInt();
        System.out.println( (a>b) ? "a가 더 크다" : "b가 더 크다");
        */
        
		/*
        System.out.println("10번 문제");
        System.out.println("반지름 입력 : ");
        double 반지름 = sa.nextDouble();
        System.out.printf("%.0f \n" , 반지름*반지름*3.14 );
        */
		
		/*
        System.out.println("11번 문제 : 앞실수의 값이 뒤의 값의 몇% ?");
        double a = sa.nextDouble();
        double b = sa.nextDouble();
        System.out.println( (a/b*100)+"%");
        */
		
		/*
        System.out.println("12번 문제 : 사다리꼴 넓이구하기 ");
        System.out.println("윗변 : "); int a = sa.nextInt();
        System.out.println("밑변 : "); int b = sa.nextInt();
        System.out.println("높이 : "); int c = sa.nextInt();
        System.out.println( (a*b)*c/2 );
        */
		
		/*
        System.out.println("13번 문제 : 키를 이용한 표준 체중 구하기");
        int a = sa.nextInt();
        System.out.println( (a-100)*0.9);
        */
		
		/*
        System.out.println("14번 문제 : BMI 출력");
        System.out.println("키 : ");   int a = sa.nextInt();
        System.out.println("몸무게 : ");int b = sa.nextInt();
        System.out.println( (b)/((a/100)*(a/100)) );
        */
		
		/*
        System.out.println("15번 문제 : inch를 cm로 바꾸기");
        int a = sa.nextInt();
        System.out.println(a*2.54+"cm");
		*/
		
		/*
        System.out.println("16번 문제 : 중간, 기말, 수행평가 비율별로 소수 2자리까지 구하기");
        System.out.println("중간 : "); int a = sa.nextInt();
        System.out.println("기말 : "); int b = sa.nextInt();
        System.out.println("수행 : "); int c = sa.nextInt();
        System.out.printf("%.2f \n" , (a*0.3) + (b*0.3) + (c*0.4) );
        */
		
		/* 문제 이해 안감!
        System.out.println("17번 문제");
    	int x = 10;
    	int y = x-- + 5 + --x;
    	System.out.printf(" x의 값 : %d , y의값 :  %d \n", x, y);
        */
		
		/*
        System.out.println("18번 문제");
        int a = sa.nextInt();
        System.out.println( (a >= 40 ? "중년" : 
        						a>=20 ? "성인" : 
        							a>=10 ? "학생" : "그외" ) );
        */
        
        System.out.println("19번 문제");
        
        System.out.println("20번 문제");
        
        System.out.println("21번 문제");
        
        System.out.println("22번 문제");
       

	}
}

/*
문제1 : 강아지 console 출력
	|\_/|
	|q p|   /}
	( 0 )"""\
	|"^"`    |
	||_/=\\__|

문제2 : 입력받은 3개 데이터를 이용한 표 만들기 
	[입력변수]
		1. 작성자[문자열] , 내용[문자열] , 날짜[문자열] 입력받아 각 변수에 저장 
	[출력 예]
		   --------------방문록--------------------
		   | 순번  | 작성자  | 	내용 	    | 날짜      |
		   |  1       | 강호동 |안녕하세요          | 09-28  |
		  ----------------------------------------
	
문제3 : 급여 계산
	[입력조건]
		기본급[정수] , 수당[정수]
	[ 조건 ] 
		 실수령액 = 기본급 + 수당 - 세금[기본급10%] 
	[출력조건] 
		실수령액 출력[ 소수점 없이  ]

문제4 : 지폐 세기 
	 [입력조건 ] 
		금액 
	 [조건 ] 
		 십만원 부터 백원 까지의 개수 세기 
	[ 출력조건 ]
		예) 356789 입력시 
		 십만원 3장 
		 만원 5장
		 천원 6장 
		 백원 7개

문제5 : 하나의 정수를 입력받아 7의 배수인지 출력[ 'O' 혹은 'X' 로 출력]

문제6 : 하나의 정수를 입력받아 홀수 인지 출력 [ 'O' 혹은 'X' 로 출력]

문제7 : 하나의 정수를 입력받아 7의 배수 이면서 짝수 인지 출력 [ 'O' 혹은 'X' 로 출력 ]

문제8 : 하나의 정수를 입력받아 7의 배수 이거나 홀수 인지 출력 [ 'O' 혹은 'X' 로 출력 ]

문제9 : 두개의 정수를 입력받아 더 큰수 출력 

문제10 : 반지름을 입력받아 원 넓이 출력하기 
	계산식) 원 넓이 공식 => 반지름 * 반지름 * 원주율[3.14]

문제11 : 두 실수를 입력받아 앞실수의 값이 뒤의 값의 몇% 인지 출력하기 
	예) 54.5   84.3 실수 2개입력시 결과는   64.285714%

문제12 : 사다리꼴 넓이 구하기[윗변과 밑변 높이를 입력받아 출력하기 
	계산식) 사다리꼴 계산식 = > (윗변 * 밑변) * 높이 / 2

문제13: 키를 정수를 입력받아 표준체중 출력하기 
	계산식) 표준체중 계산식 = > (키 - 100) * 0.9

문제14: 키와 몸무게를 입력받아 BMI 출력하기 
	계산식) BMI 계산식 = > 몸무게 / ((키 / 100) * (키 / 100))

문제15: inch 를 입력받아 cm 로 변환하기 
	계산식) 1 inch -> 2.54cm

문제16:  중간고사, 기말고사, 수행평가를 입력받아 반영비율별 계산하여 소수 둘째자리까지 점수 출력하시오 
	계산식 반영비율)  중간고사 반영비율 => 30 %  / 기말고사 반영비율 => 30 %   / 수행평가 반영비율 => 40 %

문제17 :  연산 순서 나열 하고 printf() 에 출력되는 x 와 y 값을 예측하시오.
	int x = 10;
	int y = x-- + 5 + --x;
	printf(" x의 값 : %d , y의값 :  %d ", x, y)

문제18 : 나이를 입력받아 나이가 10세이상이면 학생 , 20세이상이면 성인 , 40세이상이면 중년 으로 출력하기

문제19 : 국어 , 영어 , 수학 점수를 입력받아 각 변수에 저장하고 총점(소수점 0자리) 출력 , 평균(소수점 2자리 까지) 출력

문제20 : 아이디[문자열] 와 비밀번호[문자열] 를 입력받아 아이디가 'admin' 이고 비밀번호가 '1234' 와 일치하면 로그인성공 아니면 로그인실패 출력

문제21 : 세 정수를 입력받아 가장 큰수 출력 

문제22 : [ 가위바위보 게임 ] 
	- 가위가 '0' 이고 바위가 '1' 이고 보가 '2' 일때 플레이어1와 플레이어2 가 있습니다. 승리자 판단과 무승부 경우의수를 출력하시오.
	[입력]
		플레이어1가 0 혹은 1 혹은 2 입력받아 변수에 저장합니다.
		플레이어2가 0 혹은 1 혹은 2 입력받아 변수에 저장합니다.
	[경우의수 판단]
		플레이어1 이겼을때 경우의수 ' 플레이어1 승리 ' 출력
		플레이어2 이겼을때 경우의수 ' 플레이어2 승리 ' 출력
		비겼을경우 '무승부' 출력

		
























	
*/