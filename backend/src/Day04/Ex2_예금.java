package Day04;

import java.util.Scanner;

public class Ex2_예금 {
	public static void main(String[] args) {
		Scanner sa = new Scanner(System.in);
		// * 예금액 [ 누적 ] , 계좌번호, 패스워드 // 누적은 while 문 밖에 입력!
		int balance = 0; String account = "123-1234"; short password = 1234;	
		
		while(true) { // 무한루프 [ 종료조건 : 4 입력시 ]
			System.out.println("-----------------");
			System.out.println("1.예금 2.출금 3.잔고 4.종료 : " );
			System.out.println("-----------------");
			System.out.println(">> 선택 : ");
			int ch = sa.nextInt();
			if ( ch == 1 ) {
				System.out.println(" >>> 예금할 계좌번호 : ");
				String inaccount = sa.next();
				if ( account.equals(inaccount) ) {
					System.out.println(" >>> 예금할 금액 : ");
					balance += sa.nextInt();
					System.out.println(" >>> 예금후 금액 : " + balance);
				}else {
					System.out.println(" >>> 없는 계좌번호입니다.");
				}
			}
			
			else if ( ch == 2 ) {
				System.out.println(" >>> 출금할 계좌번호 : ");
				String inaccount = sa.next();
				if ( account.equals(inaccount) ) {
					System.out.println(" >>> 계좌 비밀번호 : ");
					short inpassword = sa.nextShort();
					if(password == inpassword) {
						System.out.println(">>> 출금할 금액 : ");
						
						int pay = sa.nextInt();
						if ( balance > pay ) {
							balance -= pay;
							System.out.println(" >>> 출금후 금액 : " + balance);
						}else {
							System.out.println(" >>> 잔액부족 : " + balance);
						}
						
					} else {
						System.out.println(" >>> 해당 계좌의 비밀번호가 다릅니다.");
					}
				} else {
					System.out.println(" >>> 없는 계좌번호입니다.");
				}
			}
			
			else if ( ch == 3 ) { System.out.println(" >>> 잔액 : " +  balance);}
			else if ( ch == 4 ) { System.out.println(" >>> 종료"); break;}
			else { System.out.println(">>> 알 수 없는 번호입니다. [ 다시입력 ] ");	}
		}
	}
}

/*
 	
 	1. if 중첩
 	2. 
 		자료형[char] == 	변수 == 변수
 						'안' == '안'
 						
 		클래스[String] : 객체A.equayls(객체B)
 						"안녕" == "안녕" [X]
 						"안녕".equals("안녕") [O]
 	
 
 */
