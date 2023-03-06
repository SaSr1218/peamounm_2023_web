package practice.day03;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

public class BoardDao {

	private static BoardDao dao = new BoardDao();
	public static BoardDao getInstance() { return dao; }
	
	private Connection con;
	private PreparedStatement ps;
	private ResultSet rs;
	
	public BoardDao() {
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			con = DriverManager.getConnection(
					"jdbc:mysql://localhost:3306/jspweb" , "root" , "1234" );
		}catch (Exception e) {System.out.println(e);}
	}
	
	// 1. 등록
	public boolean onwrite( BoardDto dto) {
		String sql = "insert into ex3( bcontent , bwriter) values ( ? , ? )";
		try {
			ps = con.prepareStatement(sql);
			ps.setString(1, dto.getBcontent() );
			ps.setString(2, dto.getBwriter() );
			ps.executeUpdate(); return true;
		}catch (Exception e) {System.out.println(e);}
		return false;
	}
	
	// 2. 모든 방문록 출력 [ 인수 : x / 반환 : 여러개[list] dto ]
	public ArrayList<BoardDto> onprint(){
		ArrayList<BoardDto> list = new ArrayList<>();
		String sql = "select * from ex3";
		try {
			ps = con.prepareStatement(sql);
			rs = ps.executeQuery();
			// 레코드1개 = if / 레코드 여러개 = while
			while( rs.next() ) {
				BoardDto dto = new BoardDto( rs.getInt(1) , rs.getString(2) , rs.getString(3) ,rs.getString(4)  );
				list.add(dto);
			} // while end
		}catch (Exception e) {System.out.println(e);}
		return list;
	}
	
	
	// 3. 삭제
	public boolean ondelete( int bno ) {
		String sql = "delete from ex3 where bno = ?";
		try {
			ps = con.prepareStatement(sql);
			ps.setInt(1, bno );
			ps.executeUpdate(); return true;
		}catch (Exception e) {System.out.println(e);}
		return false;
	}
	
	
	// 4. 업데이트
	public boolean onupdate( int bno , String newContent ) {
		String sql = "update ex3 set bcontent = ? where bno = ?";
		try {
			ps = con.prepareStatement(sql);
			ps.setString(1, newContent );
			ps.setInt(2, bno );
			ps.executeUpdate(); return true;
		}catch (Exception e) {System.out.println(e);}
		return false;
	}
	
	// ----------------------------------------------------
	
	// 1. 제품 등록
	public boolean c_product(ProductDto pdto ){
		String sql = "insert into q3 ( pname , pprice ) values ( ? , ? )";
		try {
			ps = con.prepareStatement(sql);
			ps.setString(1, pdto.getPname());
			ps.setInt(2, pdto.getPprice());
			ps.executeUpdate(); return true;
		}catch (Exception e) {System.out.println(e);}
		return false;
	}
	
	// 2. 제품 출력
	public ArrayList<ProductDto> c_print(){
		ArrayList<ProductDto> list = new ArrayList<>();
		
		String sql = "select * from q3";
		try {
			ps = con.prepareStatement(sql);
			rs = ps.executeQuery();
			while( rs.next() ) {
				ProductDto pdto = new ProductDto(rs.getInt(1) , rs.getString(2) , rs.getInt(3) );
				list.add(pdto);
			}
		}catch (Exception e) {System.out.println(e);}
		return list;
	}
	
	// 3. 제품 삭제
	public boolean c_delete( int pno ) {
		String sql = "delete from q3 where pno =?";
		try {
			ps = con.prepareStatement(sql);
			ps.setInt(1, pno);
			ps.executeUpdate(); return true;
		}catch (Exception e) {System.out.println(e);}
		return false;
	}
	
	// 4. 제품 수정
	public boolean c_update ( int pno , String newPname , int newPprice ) {
		String sql = "update q3 set pname = ? , pprice = ?  where pno = ?";
		try {
			ps = con.prepareStatement(sql);
			ps.setString(1, newPname);
			ps.setInt(2, newPprice);
			ps.setInt(3, pno);
			ps.executeUpdate(); return true;
		}catch (Exception e) {System.out.println(e);}
		return false;
	}
	
	
}
	