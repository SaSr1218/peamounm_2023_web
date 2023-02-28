package Day20.gallery.model.Dto;

public class CategoryDto {
	private int cno;			
    private String cname;		
    
    public CategoryDto() { }

	public CategoryDto(int cno, String cname) {
		this.cno = cno;
		this.cname = cname;
	}

	
	@Override
	public String toString() {
		return "CategoryDto [cno=" + cno + ", cname=" + cname + "]";
	}

	public int getCno() {
		return cno;
	}

	public void setCno(int cno) {
		this.cno = cno;
	}

	public String getCname() {
		return cname;
	}

	public void setCname(String cname) {
		this.cname = cname;
	}
    
	
	
    
}
