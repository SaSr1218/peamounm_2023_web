package 과제.과제2;

import java.util.Scanner;

public class 과제2_console_키오스크 {
	public static void main(String[] args) {
		Scanner sa = new Scanner(System.in);

		int coke = 10;
		int cider = 8;
		int hwan = 15;
		int 콜라바구니 = 0;
		int 사이다바구니 = 0;
		int 환타바구니 = 0;

		while (true) {
			System.out.println("-----------------");
			System.out.print("1.콜라 2.사이다 3.환타 4. 결제 >>>> ");
			int 메뉴 = sa.nextInt();

			if (메뉴 == 1) {
				System.out.println("선택 개수 : ");
				int count = sa.nextInt();
				if (coke - count > 0) {
					콜라바구니 = count;
				} else {
					System.out.println("재고가 없습니다.");
				}
			}

			else if (메뉴 == 2) {
				System.out.println("선택 개수 : ");
				int count = sa.nextInt();
				if (cider - count > 0) {
					사이다바구니 = count;
				} else {
					System.out.println("재고가 없습니다.");
				}
			} else if (메뉴 == 3) {
				System.out.println("선택 개수 : ");
				int count = sa.nextInt();
				if (hwan - count > 0) {
					환타바구니 = count;
				} else {
					System.out.println("재고가 없습니다.");
				}
			} else if (메뉴 == 4) {
				System.out.println("- 현재 장바구니 현황 표시 목록");
				System.out.println("-----------------------");
				System.out.println("제품명 수량  가격");
				System.out.println("콜라  " + 콜라바구니 + "   300");
				System.out.println("사이다  " + 사이다바구니 + "   400");
				System.out.println("환타  " + 환타바구니 + "   500");
				System.out.println("총가격 : " + ((콜라바구니 * 300) + (사이다바구니 * 400) + (환타바구니 * 500)));
				System.out.println("결제메뉴 1.결제완료 2.결제취소");
				int 결제메뉴 = sa.nextInt();

				if (결제메뉴 == 1) {
					System.out.println("금액을 입력해주세요.");
					int 입력금액 = sa.nextInt();
					if (입력금액 > ((콜라바구니 * 300) + (사이다바구니 * 400) + (환타바구니 * 500))) {
						입력금액 -= ((콜라바구니 * 300) + (사이다바구니 * 400) + (환타바구니 * 500));
						System.out.println("결제완료됐습니다. 거스름돈 : " + 입력금액);
					} else {
						System.out.println("결제금액이 부족합니다. 장바구니가 초기화됩니다.");
						break;
					}
				} else if (결제메뉴 == 2) {
					System.out.println("결제취소");
					break;

				} else {
					System.err.println("잘못된 결제 처리입니다.");
				}
			} else {
				System.err.println(" [알림] 알 수 없는 번호입니다.");
			}

		} // while e

	}
}

/*
 * package 과제.과제2;
 * 
 * import java.util.Scanner;
 * 
 * public class 과제2_키오스크_변수버전 { // class s public static void main(String[]
 * args) { // main s // * 입력객체 Scanner scanner = new Scanner(System.in); // * 가격
 * 변수 int 콜라가격 = 300; int 사이다가격 = 400; int 환타가격 = 500; // * 재고 변수 t int 콜라재고 =
 * 10; int 사이다재고 = 8; int 환타재고 = 15; // * 장바구니 변수 int 콜라바구니 = 0; int 사이다바구니 = 0;
 * int 환타바구니 = 0;
 * 
 * // * 프로그램 실행 반복 while( true ) { // while s // 무한루프(반복) // 종료조건없음 break 없음
 * 
 * System.out.println("-------------------------");
 * System.out.print("1.콜라 2.사이다 3.환타 4.결제 >>>>  "); int 메뉴 = scanner.nextInt();
 * 
 * // * 메뉴의 입력에 따른 제어 !!! ///
 * -----------------------------------------------------------------------------
 * -------------------------------- ////
 * 
 * if( 메뉴 == 1 ) { // 1. 콜라 선택시 // 1. 재고 여부 판단 // 만약에 콜라재고가 0보다 크면 재고 차감 바구니 증가
 * if( 콜라재고 > 0 ) { 콜라재고--; 콜라바구니++; System.err.println(" [ 알림 ] 콜라 담았습니다. "); }
 * else { System.err.println(" [ 알림 ] 콜라 재고 부족 "); } }
 * 
 * ///
 * -----------------------------------------------------------------------------
 * -------------------------------- ////
 * 
 * else if( 메뉴 == 2 ) { // 2. 사이다 선택시 // * 위 조건식 동일하되 제품만 다르다. if( 사이다재고 > 0 ) {
 * 사이다재고--; 사이다바구니++; System.err.println(" [ 알림 ] 사이다 담았습니다. "); } else {
 * System.err.println(" [ 알림 ] 사이다 재고 부족 "); } }
 * 
 * ///
 * -----------------------------------------------------------------------------
 * -------------------------------- ////
 * 
 * else if( 메뉴 == 3 ) { // 3. 환타 선택시 if( 환타재고 > 0 ) { 환타재고--; 환타바구니++;
 * System.err.println(" [ 알림 ] 환타 담았습니다. "); } else {
 * System.err.println(" [ 알림 ] 환타 재고 부족 "); } }
 * 
 * ///
 * -----------------------------------------------------------------------------
 * -------------------------------- //// else if( 메뉴 == 4 ) { // 4. 결제 선택시
 * System.out.println("----- 구매 제품 목록 -------- ");
 * System.out.println("제품명\t수량\t가격"); // 1. 만약에 바구니에 제품이 있을경우에 출력 if( 콜라바구니 > 0
 * ) System.out.println("콜라\t"+콜라바구니+"\t"+(콜라바구니*콜라가격) ); if( 사이다바구니 > 0 )
 * System.out.println("사이다\t"+사이다바구니+"\t"+(사이다바구니*사이다가격)); if( 환타바구니 > 0 )
 * System.out.println("환타\t" + 환타바구니 + "\t" + (환타바구니*환타가격)); // 2. 총가격 int 총가격 =
 * (콜라바구니*콜라가격) + (사이다바구니*사이다가격) + (환타바구니*환타가격); if( 총가격 > 0 )
 * System.out.println(" [알림] 총 결제 예정금액 : " + 총가격 ); // 3. 결제 여부 제어
 * System.out.print(" 1.결제 2.취소 선택 >>> "); int 결제메뉴 = scanner.nextInt(); /////
 * --------------------------------------------------- if( 결제메뉴 == 1 ) { // 1.
 * 결제 선택시 System.out.print(" [알림] 결제 금액 : "); int 금액 = scanner.nextInt(); if( 금액
 * >= 총가격 ) { // 만약에 입력받은 금액이 총가격보다 크거나 같으면 성공
 * System.out.println(" [알림] 결제 완료 주문번호 : A - 01 "); System.out.println(" 잔돈 : "
 * + (금액 - 총가격) ); } else { System.out.println(" [알림] 결제 취소 : 금액이 부족합니다 ");
 * 콜라재고+=콜라바구니; 사이다재고+=사이다바구니; 환타재고+=환타바구니; // 1. 재고 다시 채우기 } 콜라바구니 = 0; 사이다바구니
 * = 0; 환타바구니 = 0; // 2. 장바구니 비우기 } /////
 * --------------------------------------------------- else if( 결제메뉴 == 2 ) { //
 * 2. 취소 선택시 System.err.println(" [알림] 결제 취소합니다. "); 콜라재고+=콜라바구니; 사이다재고+=사이다바구니;
 * 환타재고+=환타바구니; // 1. 재고 다시 채우기 콜라바구니 = 0; 사이다바구니 = 0; 환타바구니 = 0; // 2. 장바구니 비우기
 * } ///// --------------------------------------------------- else {
 * System.err.println(" [알림] 알수 없는 번호 입니다. "); } /////
 * --------------------------------------------------- } ///
 * -----------------------------------------------------------------------------
 * -------------------------------- ////
 * 
 * else { System.err.println(" [알림] 알수 없는 번호 입니다. "); } } // while e } // main e
 * } // class e
 * 
 * 
 * 중요 1. 변수/메모리 선택 2. if 제어문 중첩 [ 흐름 제어 ]
 * 
 */
