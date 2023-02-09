package Day07.Ex5;

import java.util.ArrayList;
import java.util.Arrays;

public class 실행 {
	public static void main(String[] args) {
		
		// * 객체 3개 만들기
		Member m1 = new Member();
		m1.id = "qwe";	m1.password="qwe";
		
		Member m2 = new Member();
		m2.id = "asd";	m1.password="asd";
		
		Member m3 = new Member();
		m3.id = "zxc";	m1.password="zxc";
		
		
		// 1. 배열[프로그래밍 언어] 고정길이 -> 삭제하고 따로 땡겨줘야함(고정이라)
		Member[] memberList1 = new Member[3];
		memberList1[0] = m1;
		memberList1[1] = m2;
		memberList1[2] = m3;
		
		System.out.println(Arrays.toString(memberList1)); // 삭제 전
		memberList1[1] = null;		// '1' 인덱스 공백	, 고정
		System.out.println(Arrays.toString(memberList1)); // 삭제 후
		
		// 2. 컬렉션[수집] 프레임워크[미리 만들어진 클래스] -> 삭제하면 자동 땡겨짐(가변이라)
		ArrayList<Member> memberList2 = new ArrayList<>();
		memberList2.add( m1 );
		memberList2.add( m2 );
		memberList2.add( m3 );
		
		System.out.println(memberList2); // 삭제 전	
		memberList2.remove( 1 );	// '1' 인덱스 삭제	, 가변
		System.out.println(memberList2); // 삭제 후
		
		
		
		
		
		
	}
	
}
