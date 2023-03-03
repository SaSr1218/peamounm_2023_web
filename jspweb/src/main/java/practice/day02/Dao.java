package practice.day02;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

public class Dao {

	private static Dao dao = new Dao();
	public static Dao getInstance() { return dao; }
	
	Connection con;
	PreparedStatement ps;
	ResultSet rs;
	
	private Dao() {
			try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			con = DriverManager.getConnection(
					"jdbc:mysql://localhost:3306/jspweb" ,
					"root" ,
					"1234"
					);
		}catch (Exception e) {System.out.println(e);}
	
	}
	
	// 1. DTO 없는 메소드
	public boolean setData( 
		String data1 , String data2 , double data3 ,
		int data4 , String data5 , String data6 ,
		String data7 , boolean data8 , String data9 , 
		String data10 
			) {
		
		String sql = "insert into ex2 values (?,?,?,?,?,?,?,?,?,?) ";
		try {
			ps = con.prepareStatement(sql);
			ps.setString(1, data1);
			ps.setString(2, data2);
			ps.setDouble(3, data3);
			ps.setInt(4, data4);
			ps.setString(5, data5);
			ps.setString(6, data6);
			ps.setString(7, data7);
			ps.setBoolean(8, data8);
			ps.setString(9, data9);
			ps.setString(10, data10);
			ps.executeUpdate(); return true;
		}catch (Exception e) { System.out.println(e); } return false;
	}
	
	// 1-2 DTO 있는 메소드
	public boolean setData2( Dto dto ) {
		String sql = "insert into ex2 values (?,?,?,?,?,?,?,?,?,?)";
		
		try {
			ps = con.prepareStatement(sql);
			ps.setString(1, dto.getData1() );
			ps.setString(2, dto.getData2() );
			ps.setDouble(3, dto.getData3() );
			ps.setInt(4, dto.getData4() );
			ps.setString(5, dto.getData5() );
			ps.setString(6, dto.getData6() );
			ps.setString(7, dto.getData7() );
			ps.setBoolean(8, dto.isData8() ); // boolean -> get~ X is~ O
			ps.setString(9, dto.getData9() );
			ps.setString(10, dto.getData10() );
			
			ps.executeUpdate();
			return true;
		}catch (Exception e) {System.out.println(e);}
		return false;
	}
	
	
	// 2. DTO 없는 출력
	public ArrayList<Object> getData() {
		
		ArrayList<Object> list = new ArrayList<>();
		
		String sql = "select * from ex2";
		try {
			ps = con.prepareStatement(sql);
			rs = ps.executeQuery();
			while (rs.next()) {
				list.add(rs.getString(1));	list.add(rs.getString(2));
				list.add(rs.getDouble(3));	list.add(rs.getInt(4));
				list.add(rs.getString(5));	list.add(rs.getString(6));
				list.add(rs.getString(7));	list.add(rs.getBoolean(8));
				list.add(rs.getString(9));	list.add(rs.getString(10));
			}
		}catch (Exception e) {System.out.println(e);}
		return list;
	}
	
	// 2-1 DTO 있는 출력
	public ArrayList<Dto> getData2(){
		
		ArrayList<Dto>list = new ArrayList<>();
		
		String sql = "select * from ex2";
		try {
			ps = con.prepareStatement(sql);
			rs = ps.executeQuery();
			while (rs.next()) {	// 레코드 1개 --> Dto 1개 --> 리스트 담기(Dto 여러개)
				Dto dto = new Dto( rs.getString(1) , rs.getString(2) , rs.getDouble(3) , 
						rs.getInt(4) , rs.getString(5) , rs.getString(6) ,
						rs.getString(7) , rs.getBoolean(8) , rs.getString(9) , rs.getString(10) );
				list.add(dto);
			}
		}catch (Exception e) {System.out.println(e);}
		return list;
	}

	// DTO 있는 저장
	public boolean setData_과제2( Dto2 dto2 ) {
		String sql = "insert into ex2_2  values ( ? ,? , ? , ? , ? , ? , ? , ? , ?)";
			try {
				ps = con.prepareStatement(sql);
				ps.setString(1, dto2.getEx2_name() );
				ps.setString(2, dto2.getEx2_phone() );
				ps.setDouble(3, dto2.getEx2_height() );
				ps.setInt(4, dto2.getEx2_age() );
				ps.setString(5, dto2.getEx2_date() );
				ps.setString(6, dto2.getEx2_sex() );
				ps.setBoolean(7, dto2.isEx2_ok() );
				ps.setString(8, dto2.getEx2_live() );
				ps.setString(9, dto2.getEx2_info() );
				
				ps.executeUpdate();
				return true;
			}catch (Exception e) {System.out.println(e);}
			return false;
	}
	
	public ArrayList<Dto2> getData_과제2(){
		ArrayList<Dto2> list2 = new ArrayList<>();
		
		String sql = "select * from ex2_2";
				try {
					ps = con.prepareStatement(sql);
					rs = ps.executeQuery();
					while (rs.next()) {	// 레코드 1개 --> Dto 1개 --> 리스트 담기(Dto 여러개)
						Dto2 dto2 = new Dto2( 
								rs.getString(1) , rs.getString(2) , rs.getDouble(3) , 
								rs.getInt(4) , rs.getString(5) , rs.getString(6) ,
								rs.getBoolean(7) , rs.getString(8) , rs.getString(9) );
						list2.add(dto2);
					}
				}catch (Exception e) {System.out.println(e);}
				return list2;	
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}
