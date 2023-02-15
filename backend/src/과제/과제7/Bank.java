package 과제.과제7;

import java.util.Scanner;

public class Bank {
	// 1. 필드
	public int acountNo;
	public int pwd;
	public String name;
	public int count;
	
	
	// 2. 생성자
	public Bank () {}
	
	public Bank(int acountNo, int pwd, String name, int count) {
		super();
		this.acountNo = acountNo;
		this.pwd = pwd;
		this.name = name;
		this.count = count;
	}
	
	Scanner scanner = new Scanner(System.in);
	
	// 3. 메소드
	// 계좌생성
	public boolean 계좌생성( ) {	
		int ch = scanner.nextInt(); // 3개 은행 중 선택
		if( ch == 1 ) { }
		else if ( ch == 2 ) { }
		else if ( ch == 3 ) { }
		return false;
	}
	// 예금
	public boolean 예금() {
		System.out.println("계좌번호 : "); int acountNo = scanner.nextInt();
		System.out.println("예금액 : ");	int count = scanner.nextInt();
		
		
		return false;
	}
	
	
}
