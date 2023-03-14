package practice.과제1_인사등록;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

public class EmployeeDao {

	private static EmployeeDao dao = new EmployeeDao();
	public static EmployeeDao getInstance() { return dao; }
	
	public Connection con;
	public PreparedStatement ps;
	public ResultSet rs;
	
	public EmployeeDao() {
		try{
			Class.forName("com.mysql.cj.jdbc.Driver");
			con = DriverManager.getConnection(
					"jdbc:mysql://localhost:3306/jsp_ass1" ,
					"root" ,
					"1234");
			System.out.println("DB연동성공");
		} catch (Exception e) {System.out.println(e);}
	}
	
	// 1. 인사 등록
	public boolean signup( EmployeeDto dto ) {
		String sql = "insert into emp_info(empImg , empName , empGrade , empConstruct , empDepart , empSdate , empLdate , empLcomment) values ( ? ,? ,? ,? ,? ,? ,? ,? ) "; 
		try {
			ps = con.prepareStatement(sql);
			ps.setString(1, dto.getEmpImg());
			ps.setString(2, dto.getEmpName());
			ps.setString(3, dto.getEmpGrade());
			ps.setString(4, dto.getEmpConstruct());
			ps.setString(5, dto.getEmpDepart());
			ps.setString(6, dto.getEmpSdate());
			ps.setString(7, dto.getEmpLdate());
			ps.setString(8, dto.getEmpLcomment());
			ps.executeUpdate();
			return true;
		}catch (Exception e) {System.out.println(e);}
		return false;
	} // 인사 등록 end

	// 2.1 인사 전체출력
	public ArrayList<EmployeeDto> getEmployeeList(){
		ArrayList<EmployeeDto> list = new ArrayList<>();
		String sql = "select * from emp_info";
		try {
			ps = con.prepareStatement(sql);
			rs = ps.executeQuery();
			while ( rs.next() ) {
				EmployeeDto dto = new EmployeeDto(
						rs.getInt(1), rs.getString(2), rs.getString(3), 
						rs.getString(4), rs.getString(5), rs.getString(6), 
						rs.getString(7), rs.getString(8), rs.getString(9) );
			list.add(dto);
			}
		
		}catch (Exception e) {System.out.println(e);}
		return list;
	}
	
	// 2.2 인사 직무(부서별) 출력
	public ArrayList<EmployeeDto> getEmployeejob( String empDepart ){
		ArrayList<EmployeeDto> list2 = new ArrayList<>();
		String sql = "select e.empDepart , e.empGrade , e.empName  from emp_info e where empDepart = ? ";
		try {
			ps = con.prepareStatement(sql);
			ps.setString(1, empDepart);
			rs = ps.executeQuery();
			while ( rs.next() ) {
				EmployeeDto dto2 = new EmployeeDto(rs.getString(1), rs.getString(2), rs.getString(3));
			list2.add(dto2);
			}
		}catch (Exception e) {System.out.println(e);}
		return list2;
	} 
	
	
	
	
	
	
	
	
	
	
	
	

}
