package Day19.Ex4;

public class 실행 {

	public static void main(String[] args) {
		
		// 1. 계산기 객체 생성 
		Calculator calculator = new Calculator();
		
		// 2. 유저1 객체 생성
		User1Thread user1Thread = new User1Thread();
		user1Thread.setCalculator(calculator); // 동일한 calculator 객체 메모리 사용
		user1Thread.start();
		
		// 3. 유저2 객체 생성
		User2Thread user2Thread = new User2Thread(); // 동일한 calculator 객체 메모리 사용
		user2Thread.setCalculator(calculator);
		user2Thread.start();
		
	}
	
}
