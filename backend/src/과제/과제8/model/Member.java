package 과제.과제8.model;

public class Member {
	
	// 1. 필드
	public String id;
	public String pwd;
	public String name;
	public String phone;
	public int mno;
	
	// 2. 생성자
	public Member() { } // 빈
	public Member(String id, String pwd, String name, String phone, int mno) { // 풀
		super();
		this.id = id;
		this.pwd = pwd;
		this.name = name;
		this.phone = phone;
		this.mno = mno;		// 고유 회원번호
	}
	
	
	
}
