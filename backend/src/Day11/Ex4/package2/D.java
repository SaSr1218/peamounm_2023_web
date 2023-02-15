package Day11.Ex4.package2;

import Day11.Ex4.package1.A;

//--------------패키지2-----------------//

public class D extends A {
	
	public D() {}
	public void method1() {
		this.field = "value";
		super.field = "value";
		
		this.method();
		super.method();
	}
	
	/*
	 * public void method2() { A a = new A(); // new 연산자를 통해 생성자를 직접 호출은 불가! a.field
	 * = "value"; a.method(); }
	 */
}
