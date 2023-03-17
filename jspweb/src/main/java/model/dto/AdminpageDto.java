package model.dto;

import java.util.ArrayList;

public class AdminpageDto {
	private int page; 		// 현재 페이지
	private int listsize;	// 페이지당 게시물을 표시할 개수
	private int startrow; 	// 현재 페이지에서 시작되는 게시물번호
	private int totalsize;	// 총 게시물수
	private int totalpage;	// 총 페이지수
	
	private int btnsize;	// 페이지별 최대 페이징버튼수
	private int startbtn;	// 페이지별 시작 페이징버튼 번호
	private int endbtn;		// 페이지별 끝 페이징버튼 번호
	
	// 회원 목록
	ArrayList<MemberDto> memberList ;
	
	public AdminpageDto() {}
	
	public AdminpageDto(int page, int listsize, int startrow, int totalsize, int totalpage, int btnsize, int startbtn,
			int endbtn, ArrayList<MemberDto> memberList) {
		super();
		this.page = page;
		this.listsize = listsize;
		this.startrow = startrow;
		this.totalsize = totalsize;
		this.totalpage = totalpage;
		this.btnsize = btnsize;
		this.startbtn = startbtn;
		this.endbtn = endbtn;
		this.memberList = memberList;
	}


	@Override
	public String toString() {
		return "AdminpageDto [page=" + page + ", listsize=" + listsize + ", startrow=" + startrow + ", totalsize="
				+ totalsize + ", totalpage=" + totalpage + ", btnsize=" + btnsize + ", startbtn=" + startbtn
				+ ", endbtn=" + endbtn + ", memberList=" + memberList + "]";
	}

	public int getPage() {
		return page;
	}

	public void setPage(int page) {
		this.page = page;
	}

	public int getListsize() {
		return listsize;
	}

	public void setListsize(int listsize) {
		this.listsize = listsize;
	}

	public int getStartrow() {
		return startrow;
	}

	public void setStartrow(int startrow) {
		this.startrow = startrow;
	}

	public int getTotalsize() {
		return totalsize;
	}

	public void setTotalsize(int totalsize) {
		this.totalsize = totalsize;
	}

	public int getTotalpage() {
		return totalpage;
	}

	public void setTotalpage(int totalpage) {
		this.totalpage = totalpage;
	}

	public int getBtnsize() {
		return btnsize;
	}

	public void setBtnsize(int btnsize) {
		this.btnsize = btnsize;
	}

	public int getStartbtn() {
		return startbtn;
	}

	public void setStartbtn(int startbtn) {
		this.startbtn = startbtn;
	}

	public int getEndbtn() {
		return endbtn;
	}

	public void setEndbtn(int endbtn) {
		this.endbtn = endbtn;
	}

	public ArrayList<MemberDto> getMemberList() {
		return memberList;
	}

	public void setMemberList(ArrayList<MemberDto> memberList) {
		this.memberList = memberList;
	}
	
	
	
}
