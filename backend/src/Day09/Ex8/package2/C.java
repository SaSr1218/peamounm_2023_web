package Day09.Ex8.package2;

import Day09.Ex8.package1.A;

public class C {

	// 1. A클래스가 public 일때
	A a; // 가능 : 같은 패키지 아니지만 import 해서 가능
	// 2. A클래스 public 아닐때(public 지웠을때)
		// 불가능 : default는 같은 패키지만 가능
	
	A a1 = new A(true); // public 가능
	// A a2 = new A(1);	// default 다른 패키지일 경우 불가능
	// A a3 = new A("문자열");// private X
}
