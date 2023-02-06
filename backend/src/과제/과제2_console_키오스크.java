package 과제;

import java.util.Scanner;

public class 과제2_console_키오스크 {
	public static void main(String[] args) {
		Scanner sa = new Scanner(System.in);
		
		int coke = 10; int cider = 8; int hwan = 15;
		int 콜라바구니 = 0; int 사이다바구니 = 0; int 환타바구니 = 0;
	
		while(true) {
			System.out.println("-----------------");
			System.out.print("1.콜라 2.사이다 3.환타 4. 결제 >>>> ");
			int 메뉴 = sa.nextInt();
			
			if( 메뉴 == 1) {
				System.out.println("선택 개수 : ");int count = sa.nextInt();
				if(coke-count > 0) {
				콜라바구니 += 10 - count;
				}else {
					System.out.println("재고가 없습니다.");
				}
			}
			
			else if(메뉴 ==2 ) {
				System.out.println("선택 개수 : ");int count = sa.nextInt();
				if(cider-count > 0) {
				사이다바구니 += 8 - count;
				}else {
					System.out.println("재고가 없습니다.");
				}
			}
			else if(메뉴 ==3 ) {
				System.out.println("선택 개수 : ");int count = sa.nextInt();
				if(hwan-count > 0) {
				사이다바구니 += 15 - count;
				}else {
					System.out.println("재고가 없습니다.");
				}
			}
			else if(메뉴 ==4 ) {
				System.out.println("- 현재 장바구니 현황 표시 목록");
				System.out.println("-----------------------");
				System.out.println("제품명 수량  가격");
				System.out.println("콜라  " + 콜라바구니 + "   300");
				System.out.println("사이다  " + 사이다바구니 + "   400");
				System.out.println("환타  " + 환타바구니 + "   500");
				System.out.println("총가격 : " + ((콜라바구니*300) + (사이다바구니*400) + (환타바구니*500)) );
				System.out.println("결제메뉴 1.결제완료 2.결제취소"); int 결제메뉴 = sa.nextInt();
				
				
				if(결제메뉴 == 1) {
					System.out.println("금액을 입력해주세요."); int 입력금액 = sa.nextInt();
					if( 입력금액 > ((콜라바구니*300) + (사이다바구니*400) + (환타바구니*500)) ) {
					입력금액 -= ((콜라바구니*300) + (사이다바구니*400) + (환타바구니*500));
					System.out.println("결제완료됐습니다. 거스름돈 : " + 입력금액);
					}else {
						System.out.println("결제금액이 부족합니다. 장바구니가 초기화됩니다."); break;
					}
				}else if (결제메뉴 == 2) {
					System.out.println("결제취소"); break;
					
				}else {System.err.println("잘못된 결제 처리입니다.");}
			}
			else {System.err.println(" [알림] 알 수 없는 번호입니다.");}
			
		} // while e

	
	}
}








