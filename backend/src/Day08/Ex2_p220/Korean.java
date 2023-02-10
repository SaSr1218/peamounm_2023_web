package Day08.Ex2_p220;

public class Korean {
	// 1. 필드
	String nation = "대한민국";
	String name;
	String ssn;
	
	// 2. 생성자
		// 1. 빈 생성자
	public Korean() {}
	
	public Korean ( String n, String s ) {
		name = n;
		ssn = s;
	}

	// 3. 메소드
	@Override
	public String toString() {
		return "Korean [nation=" + nation + ", name=" + name + ", ssn=" + ssn + "]";
	}
	
}
