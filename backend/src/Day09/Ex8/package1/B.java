package Day09.Ex8.package1;

public class B {
	A a; // 기능 : 같은 패키지니까 import 안해도 가능
	A a1 = new A(true); // public 가능
	A a2 = new A(1);	// default 같은 패키지까지 가능
	// A a3 = new A("문자열"); // private 무조건 내부에서만 가능
	
	
	public static void main(String[] args) {
		A a4 = new A(true);
		a4.field1 = 10;		// public라 가능
		a4.field2 = 10;		// 같은 패키지라 가능
		// a4.field3 = 10; // private라 불가능
	}
}
