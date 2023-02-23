package 과제.과제11;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

public class ProductDao {

	// 싱글톤
	private static ProductDao dao = new ProductDao();
	public static ProductDao getInstance() { return dao; }
	
	// 1. 필드
	private Connection conn;		// 연결된 DB 구현 객체 가지고 있는 인터페이스
	private PreparedStatement ps;	// 연결된 SQL 조작
	private ResultSet rs;			// 실행된 SQL 결과
	
	// 2. 생성자
	private ProductDao() {
		try {
			conn = DriverManager.getConnection(
					"jdbc:mysql://localhost:3306/과제11" , "root" , "1234");
		} catch (Exception e) { System.out.println(e.getMessage() );}
	}
	
	// 3. SQL 처리 메소드
		// 1. 제품등록 메소드 [ 인수 : Dto( name , price , pbox) / 반환 : 성공[true] 실패[false] ]
	public boolean signup( ProductDto dto ) {
		// 1.
		String sql = "insert into product ( pname , pprice , pbox ) values ( ? , ? , ? )";
		// 2.
		try {
			ps = conn.prepareStatement(sql);
			// 3.
			ps.setString(1, dto.getPname() );
			ps.setInt(2, dto.getPprice() );
			ps.setInt(3, dto.getPbox());
			// 4.
			ps.executeUpdate();
			// 5.
			return true;
		} catch (Exception e) { System.out.println("db 오류 : " + e );}
		return false;
	}
	
		// 2. 모든 상품 출력 [ 인수 : X / 반환 : 상품[ArrayList] 상품[dto 객체]
	public ArrayList<ProductDto> getProductAll(){
		// 여러개 상품DTO 객체 저장하기 위한 리스트 선언
		ArrayList<ProductDto> list = new ArrayList<>();
		// 1.
		String sql = "select * from product";
		// 2.
		try {
			ps = conn.prepareStatement(sql);
		// 3. 매개변수 없으니 패스
		// 4.
			rs = ps.executeQuery();
		// 5. 
			while( rs.next() ) {
				ProductDto dto = new ProductDto(rs.getInt(1) , rs.getString(2) , rs.getInt(3) , rs.getInt(4) );
				list.add(dto);
			}
			return list;
		} catch (Exception e) { System.out.println("db 오류 : " + e );}
		return null;
	} // list end
	
	// 3. 제품 수정(이름 , 가격) [ 인수 : 제품[pno]의 이름, 가격[새로운 pname , 새로운 pprice] / 반환 : 성공[true] 실패[false] ]
	public boolean update( int pno , String pname , int pprice ) {
		
		// 1.
		String sql = "update product set pname = ? , pprice =? where pno =?";
		// 2.
		try {
			ps = conn.prepareStatement(sql);
		// 3.
			ps.setString(1, pname);
			ps.setInt(2, pprice);
			ps.setInt(3, pno);
		// 4.
			ps.executeUpdate();
		// 5.
			return true;
		} catch (Exception e) { System.out.println("db 오류 : " + e );}
		return false;
	} // update end
	
	// 4. 제품 수정(재고) [ 인수 : 제품[pno] 의 새로운 재고 [ pbox ] / 반환 : true[성공] false[실패] ]
	
	public boolean update2( int pno , int pbox ) {
		
		// 1.
		String sql = "update product set pbox = ? where pno = ?";
		// 2.
		try {
			ps = conn.prepareStatement(sql);
		// 3.
			ps.setInt(1, pbox);
			ps.setInt(2, pno);
		// 4.
			ps.executeUpdate();
		// 5.
			return true;
		} catch (Exception e) { System.out.println("db 오류 : " + e );}
		return false;
	} // update2 end
	
	
	// 5. 제품 삭제 [ 인수 : pno를 가지고 / 반환 : 성공[true] , 실패[false] ]
	public boolean delete(int pno) {
		
		// 1.
		String sql = "delete from product where pno = ?";
		// 2.
		try {
			ps = conn.prepareStatement(sql);
		// 3.
			ps.setInt(1, pno);
		// 4.
			ps.executeUpdate();
		// 5.
			return true;
		} catch (Exception e) { System.out.println("db 오류 : " + e );}
		return false;
	}
	
	// 6. 사용자 제품번호 선택 [ 인수 : pno / 반환 : 성공[true] , 실패[false] ]
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}
