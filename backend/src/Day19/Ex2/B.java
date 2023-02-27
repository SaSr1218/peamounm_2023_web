package Day19.Ex2;

public class B implements Runnable{
	@Override
	public void run() {
		while(true) {
			System.out.println("4. B 스레드 : " + Thread.currentThread() );
			try { Thread.sleep(1000); } // 현 스레드 1초간 일시정지
			catch (Exception e) { }
		}
	}
}
