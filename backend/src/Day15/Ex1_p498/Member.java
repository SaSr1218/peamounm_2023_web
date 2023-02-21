package Day15.Ex1_p498;

public class Member {
	
	// 1. 필드
	public String id;
	
	// 2. 생성자
	public Member ( String id ) {
		this.id = id;
	}
	
	// 3. 메소드
	// 주소번지 비교가 아닌 내부 값 비교로 재정의[오버라이딩]
	@Override
	public boolean equals(Object obj) {		// 
//		if(obj instanceof Member target)
			// 부모객체 instanceof 자식클래스 변환객체명 ( JDK 16버전 이상에서 가능 / 데이터베이스 위해 바꾼 18버전에서는 불가)
			// obj -> target
		if( obj instanceof Member ) {
			Member target = (Member)obj;
			if(id.equals(target.id)) {
				return true;
			}
		}
		return false;
	}

}
