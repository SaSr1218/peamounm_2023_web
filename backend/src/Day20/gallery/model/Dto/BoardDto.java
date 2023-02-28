package Day20.gallery.model.Dto;

public class BoardDto {
	private int bno; 		
    private String btitle;		
    private String bcontent;
    private String bdate;		
    private int bview;		
    private String mid;		// DB 회원번호 대신 mid 가져오려고(회원번호만 있으면 돼서)
    private String cname;	// DB 카테고리 번호 대신 cname 가져오려고(카테고리 번호만 있으면 돼서)		

	public BoardDto() { }
	
	public BoardDto(int bno, String btitle, String bcontent, String bdate, int bview, String mid, String cname) {
		this.bno = bno;
		this.btitle = btitle;
		this.bcontent = bcontent;
		this.bdate = bdate;
		this.bview = bview;
		this.mid = mid;
		this.cname = cname;
	}



	@Override
	public String toString() {
		return "BoardDto [bno=" + bno + ", btitle=" + btitle + ", bcontent=" + bcontent + ", bdate=" + bdate
				+ ", bview=" + bview + ", mid=" + mid + ", cname=" + cname + "]";
	}

	public int getBno() {
		return bno;
	}

	public void setBno(int bno) {
		this.bno = bno;
	}

	public String getBtitle() {
		return btitle;
	}

	public void setBtitle(String btitle) {
		this.btitle = btitle;
	}

	public String getBcontent() {
		return bcontent;
	}

	public void setBcontent(String bcontent) {
		this.bcontent = bcontent;
	}

	public String getBdate() {
		return bdate;
	}

	public void setBdate(String bdate) {
		this.bdate = bdate;
	}

	public int getBview() {
		return bview;
	}

	public void setBview(int bview) {
		this.bview = bview;
	}

	public String getMid() {
		return mid;
	}

	public void setMid(String mid) {
		this.mid = mid;
	}

	public String getCname() {
		return cname;
	}

	public void setCname(String cname) {
		this.cname = cname;
	}

}
