package 과제.과제11;

public class ProductDto {

	// 1. 필드
	private int pno;			// 제품번호
	private String pname;		// 제품이름
	private int pprice;			// 제품가격
	private int pbox;			// 제품재고
	
	// 2. 생성자
	public ProductDto() {}

	public ProductDto(int pno, String pname, int pprice, int pbox) {
		this.pno = pno;
		this.pname = pname;
		this.pprice = pprice;
		this.pbox = pbox;
	}
	
	// 3. 메소드
	@Override
	public String toString() {
		return "ProductDto [pno=" + pno + ", pname=" + pname + ", pprice=" + pprice + ", pbox=" + pbox + "]";
	}

	public int getPno() {
		return pno;
	}

	public void setPno(int pno) {
		this.pno = pno;
	}

	public String getPname() {
		return pname;
	}

	public void setPname(String pname) {
		this.pname = pname;
	}

	public int getPprice() {
		return pprice;
	}

	public void setPprice(int pprice) {
		this.pprice = pprice;
	}

	public int getPbox() {
		return pbox;
	}

	public void setPbox(int pbox) {
		this.pbox = pbox;
	}
	
	
	
}
