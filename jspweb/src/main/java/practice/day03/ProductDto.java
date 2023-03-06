package practice.day03;

public class ProductDto {
	private int pno;
	private String pname;
	private int pprice;
	
	public ProductDto() { }
	
	public ProductDto(int pno, String pname, int pprice) {
		super();
		this.pno = pno;
		this.pname = pname;
		this.pprice = pprice;
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

	@Override
	public String toString() {
		return "ProductDto [pno=" + pno + ", pname=" + pname + ", pprice=" + pprice + "]";
	}
	
	
	
	
}
