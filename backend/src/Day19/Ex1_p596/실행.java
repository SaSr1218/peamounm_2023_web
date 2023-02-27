package Day19.Ex1_p596;

import java.awt.Toolkit;

public class 실행 {
	
	// p.596
		// main메소드() : 메인 thread 싱글스레드
	public static void main(String[] args) {
		
		// ---------------------------------1. 싱글스레드 -------------------------------
		// * 소리 코드가 실행되고 출력 코드 실행
		// 1. 소리 코드
		System.out.println("싱글스레드");
		Toolkit toolkit = Toolkit.getDefaultToolkit();
		for ( int i = 0; i<5 ; i++ ) {
			toolkit.beep();	// 비프음 소리내기
			try { Thread.sleep(500); }
			catch (Exception e) { }
		}
		
		// 2. 출력 코드
		for ( int i = 0 ; i<5 ; i++ ) {
			System.out.println("띵");
			try { Thread.sleep(500); }
			catch (Exception e) { }
		}
		// ----------------------------------------------------------------------------
		// --------------------------------2. 멀티스레드 구현 1 ---------------------------------
		// Thread thread = new Thread( new Runnable() {} );
			// 익명[ 이름이없는 ] 구현 객체
		System.out.println("멀티스레드1 시작");
		Thread thread = new Thread ( new Runnable() {
			
			@Override
			public void run() {
				Toolkit toolkit = Toolkit.getDefaultToolkit();
				for ( int i = 0 ; i < 5 ; i++ ) {
					toolkit.beep();
					try { Thread.sleep(500); }
					catch ( Exception e ) { }
				} // for e
			} // run e
		}); // thread 생성자 end 
		
		thread.start();
		
		// 2. 출력코드
		for ( int i = 0 ; i<5 ; i++ ) {
			System.out.println("띵");
			try { Thread.sleep(500); }
			catch ( Exception e ) { }
		}
		
		// --------------------------------3. 멀티스레드 구현 2 ---------------------------------
		// 1. 소리 출력
		System.out.println("멀티스레드2 시작(상속)");
		Thread thread2 = new sound();
		thread2.start();
		// 2. 출력코드
		for ( int i = 0 ; i<5 ; i++ ) {
			System.out.println("띵");
			try { Thread.sleep(500); }
			catch ( Exception e ) { }
		}
		
		// --------------------------------4. 멀티스레드 구현3------------------------------
		System.out.println("멀티스레드3 시작");

		Runnable runnable = new sound2();
		Thread thread3 = new Thread(runnable);
		thread3.start();
		
		// 2. 출력코드
		for ( int i = 0 ; i<5 ; i++ ) {
			System.out.println("띵");
			try { Thread.sleep(500); }
			catch ( Exception e ) { }
		}
		
		
		
		
	}
}
