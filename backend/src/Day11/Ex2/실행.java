package Day11.Ex2;

public class 실행 {
	public static void main(String[] args) {
		
		//
		int r = 10;
		
		// 1. 부모객체
		Calculator calculator =  new Calculator();
		System.out.println( calculator.areaCircle(r) );
		
		// 2. 자시객객체
		Computer computer = new Computer();
		System.out.println( computer.areaCircle(r) );
	}
}
