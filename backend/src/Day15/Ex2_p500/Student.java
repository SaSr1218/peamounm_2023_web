package Day15.Ex2_p500;

public class Student {
	private int no;
	private String name;
	
	public Student(int no, String name) {
		super();
		this.no = no;
		this.name = name;
	}

	public int getNo() {
		return no;
	}

	public String getName() {
		return name;
	}
	
	@Override
	public int hashCode() { // 해시코드가 동일한지 검사
		int hashCode = no + name.hashCode();
		return hashCode;
	}
	
	@Override
	public boolean equals(Object obj) {
		if(obj instanceof Student) {
			Student target = (Student)obj;	// 강제 형변환
			if( no == target.getNo() && name.equals(target.getName() )) {
				return true;
			}
		}
		return false;
	}

}
