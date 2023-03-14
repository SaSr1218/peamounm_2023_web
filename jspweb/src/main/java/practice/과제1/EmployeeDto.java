package practice.과제1;

public class EmployeeDto {

	private int 	empNo;				// 사원번호
	private String	empImg;				// 사원사진
	private String 	empName;		 	// 사원명
	private String 	empGrade;	 		// 직급
	private String 	empContruct;		// 고용형태
	private String	empDepart;	 		// 부서
	private int 	empSdate;	 		// 입사일
	private int		empLdate;			// 퇴사일
	private String	empLcomment;		// 퇴사사유
	
	
	public EmployeeDto() { }
	
	
	
	public EmployeeDto(int empNo, String empImg, String empName, String empGrade, String empContruct, String empDepart,
			int empSdate, int empLdate, String empLcomment) {
		super();
		this.empNo = empNo;
		this.empImg = empImg;
		this.empName = empName;
		this.empGrade = empGrade;
		this.empContruct = empContruct;
		this.empDepart = empDepart;
		this.empSdate = empSdate;
		this.empLdate = empLdate;
		this.empLcomment = empLcomment;
	}


	@Override
	public String toString() {
		return "EmployeeDto [empNo=" + empNo + ", empImg=" + empImg + ", empName=" + empName + ", empGrade=" + empGrade
				+ ", empContruct=" + empContruct + ", empDepart=" + empDepart + ", empSdate=" + empSdate + ", empLdate="
				+ empLdate + ", empLcomment=" + empLcomment + "]";
	}
	public int getEmpNo() {
		return empNo;
	}
	public void setEmpNo(int empNo) {
		this.empNo = empNo;
	}
	public String getEmpImg() {
		return empImg;
	}
	public void setEmpImg(String empImg) {
		this.empImg = empImg;
	}
	public String getEmpName() {
		return empName;
	}
	public void setEmpName(String empName) {
		this.empName = empName;
	}
	public String getEmpGrade() {
		return empGrade;
	}
	public void setEmpGrade(String empGrade) {
		this.empGrade = empGrade;
	}
	public String getEmpContruct() {
		return empContruct;
	}
	public void setEmpContruct(String empContruct) {
		this.empContruct = empContruct;
	}
	public String getEmpDepart() {
		return empDepart;
	}
	public void setEmpDepart(String empDepart) {
		this.empDepart = empDepart;
	}
	public int getEmpSdate() {
		return empSdate;
	}
	public void setEmpSdate(int empSdate) {
		this.empSdate = empSdate;
	}
	public int getEmpLdate() {
		return empLdate;
	}
	public void setEmpLdate(int empLdate) {
		this.empLdate = empLdate;
	}
	public String getEmpLcomment() {
		return empLcomment;
	}
	public void setEmpLcomment(String empLcomment) {
		this.empLcomment = empLcomment;
	}
	
	
	
	
}
