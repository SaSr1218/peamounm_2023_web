package Day04;

import java.util.Random;
import java.util.Scanner;

public class Ex1_가위바위보 {
	public static void main(String[] args) {
		
		Scanner sa = new Scanner(System.in);
		
		int pwin = 0; int cwin = 0; int play = 0;
		
		
		while(true) {
			System.out.println(" 가위(0) 바위(1) 보(2) 종료(3) : ");
			
			byte player = sa.nextByte();
			System.out.println("player가 낸수 : " + player);
			if( player ==3 ) {
				System.out.println("-- 종료 플레이수 : " + play);
				if(pwin > cwin) { System.out.println("player 최종승리 " + pwin);}
				else if (pwin < cwin) { System.out.println("com 최종승리 " + cwin);}
				else { System.out.println("최종 무승부");}
				break;
			}
			
			// 난수 생성 [1.Math 클래스 2. Random 클래스]
			Random random = new Random();
			int com = random.nextInt(3); // 0 ~ 2  사이의 int형 난수 생성
			System.out.println("컴퓨터가 낸수 : " + com);
			
			// 승리 판단
			if ( player == com ) {
				System.out.println("승리 판단 : 무승부");
			}else if( player == 0 && com == 2 || player == 1 && com == 0 || player == 2 && com == 1 ) {
				System.out.println("승리 판단 : player 승리"); pwin++;
			}else {
				System.out.println("승리 판단 : com 승리"); cwin++;
			}
			play++;
			
		} // while e
				
	}
}

/*
  1. 승리자 판단
  2. 지역변수 특징
  3. Scanner 클래스, Random 클래스 
 */
