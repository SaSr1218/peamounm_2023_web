package jspweb.model;

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
			// * [웹서버] : 해당 MYSQL 드라이버[라이브러리] 찾기 -> console에서는 필요 X
			Class.forName("com.mysql.cj.jdbc.Driver");
			con = DriverManager.getConnection(
						"jdbc:mysql://localhost:3306/jspweb" ,
						"root" ,
						"1234"
				);
			System.out.println("연동 성공");
		} catch (Exception e) {System.out.println("연동실패 : " + e);}
				
	}
	
	// 2. SQL 메소드
	// 데이터 저장
	public boolean setData( String data ) {
		// 1. SQL 작성
		String sql = "insert into ex1 values(?)";
		// 2. SQL 대입
		try {
			ps = con.prepareStatement(sql);	// 2. 연결된 JDBC에 SQL 대입
			ps.setString(1, data);			// 3. SQL 조작
			ps.executeUpdate();				// 4. SQL 실행 --> 5. SQL 결과[rs 필요 없으니 생략]
			return true;
		}catch (Exception e) {System.err.println(e);}
		return false;
	}
	
	// 데이터 들 호출 [ 1개 : String / 여러개 : ArrayList<String> ]
	public ArrayList<String> getData(){
		
		ArrayList<String> list = new ArrayList<>(); // 필드 1개라 dto 사용 안한것임!
		
		String sql = "select * from ex1";	// 1. SQL 작성
		try {
			ps = con.prepareStatement(sql);	// 2. 연결된 JDBC에 SQL 대입
			rs = ps.executeQuery();			// 3. SQL 조작 X // 4.  SQL 실행 -> 5. SQL 결과
			while ( rs.next() ){// 레코드1개 if / 레코드 여러개 while
				list.add( rs.getString(1) ); // 해당 레코드 첫번째 필드를 리스트에 저장
			}
			System.out.println();
		}catch (Exception e) {System.err.println(e);}
		return list;
	}
	
	// [1] 데이터 저장
	public boolean setData2 ( String data2 ) {
		String sql = "insert into ex2 values( ? )";
		try {
			ps = con.prepareStatement(sql);
			ps.setString(1, data2);
			ps.executeUpdate();
			return true;
		}catch (Exception e) {System.err.println(e);}
		return false;
	}
	
	public ArrayList<String> getData2(){
		
		ArrayList<String> list2 = new ArrayList<>();
		
		String sql = "select * from ex2";
		try {
			ps = con.prepareStatement(sql);
			rs = ps.executeQuery();
			while (rs.next() ) {
				list2.add( rs.getString(1) );
			}
			System.out.println();
		}catch (Exception e) {System.err.println(e);}
		return list2;
	}
	
	
	
	
	
	
	
	
	
	
	
}
