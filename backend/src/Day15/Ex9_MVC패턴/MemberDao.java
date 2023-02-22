package Day15.Ex9_MVC패턴;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

public class MemberDao {

	// * 싱글톤 : Dao 싱글톤 사용하는 이유 : 프로그램내 DB 연동 1번만 해서 하나의 연동 객체 사용 
		// 1. 내부에 객체 만들기 
	private static MemberDao dao = new MemberDao();
		// 2. 생성자는 private -> 외부에서 new 사용금지 
		// 3. 외부에서 내부객체 사용할수 있게 내부객체 반환 메소드 [ getInstance ]
	public static MemberDao getInstance() { return dao;}
	
	// 1. 필드
	private Connection conn;		// 1. 연결된 DB구현객체를 가지고있는 인터페이스  
	private PreparedStatement ps;	// 2. 연결된 SQL 조작 [ +매개변수 가능 ] 인터페이스
	private ResultSet rs;			// 3. 실행된 SQL 결과 인터페이스
	
	// 2. 생성자 [ 연동코드 -> 객체 생성시 바로 연동될수 있게 할려고  ]
	private MemberDao() {
		try {
			conn = DriverManager.getConnection(
					"jdbc:mysql://localhost:3306/day15" , "root" ,"1234");
		}catch (Exception e) { System.out.println(e.getMessage() );}
	}
	
	// 3. SQL 처리 메소드 
	// 1. 회원가입 메소드 [ 인수 : Dto( id,pw ) / 반환 : 성공[true],실패[false] ]
	public boolean signup( MemberDto dto ) {
		// 1. SQL 작성한다.
		String sql = "insert into member ( mid , mpw ) values ( ? , ? )";
		// 2. 연동 DB에 SQL 대입한다. [ ps -> 매개변수 조작 가능 ]
		try {
			ps = conn.prepareStatement(sql);
			// 3. ps는 매개변수에 대한 조작 가능 
			ps.setString( 1 , dto.getMid() );// SQL 구문내 첫번째 ? 에 대한 데이터 대입 
			ps.setString( 2 , dto.getMpw() ); // SQL 구문내 두번째 ? 에 대한 데이터 대입
			// 4. ps가 sql 실행
			ps.executeUpdate();		// executeUpdate : 실행 결과를 수정해줘.
			// 5. 결과 반환
			return true; // 여기까지 문제 없었으면 저장 성공 
			
		}catch (Exception e) { System.out.println("DB 오류 : " + e); }
		return false; // try{} 문제가 있으면 실행되는 구역 -> 저장 실패
	}
	//////////////////// ------------------------------------- ////////////////////////////////
	
	// 2. 모든 회원 출력 [ 인수 : X	/ 	반환 : 여러명{ArrayList vs 배열] 회원 = dto 객체 ]
	public ArrayList<MemberDto> list() {
		// * 여러명의 회원DTO 객체를 저장하기 위한 리스트 선언
		ArrayList<MemberDto> list = new ArrayList<>();
		// 1. SQL 작성
		String sql = "select * from member";
		// 2. 연결된 DB에 작성된 SQL 대입
		try {
			ps = conn.prepareStatement(sql);
		 
		// 3. SQL 조작 [ 매개변수 없으면 패스 ] 
		// 4. SQL 실행 [ SQL 결과를 rs 인터페이스에 저장 ]
			rs = ps.executeQuery(); // 결과 : 검색된 모든 레코드	// 1. executeQuery : 실행 결과를 보여줘.
		// 5. SQL 결과
				// 레코드 --자바형태로 변환 --> 객체 DTO // 레코드 1개 -> DTO 1개
			while( rs.next() ) { // rs.next() : 다음 레코드 이동 [ 없으면 false] // 마지막 레코드까지 이동
				// re[null] -> rs.next() -> rs[1레코드] --> rs.next() --> rs[2레코드] --> false
				
				// 레코드를 객체화!(자바는 레코드를 사용하지 못해서) [ rs.get~~필드번호 / mno mid mpw 순 ]
				// 레코드 1개 --> 객체화 1개로 한 것임!
				MemberDto dto = new MemberDto(
						rs.getInt(1), rs.getString(2), rs.getString(3) );
				// 1개 객체 --> 리스트에 담기
				list.add(dto);
			}
			return list;
		} catch (Exception e) { System.out.println("DB 오류 : " + e ); }
		return null;
		
	}
	
	// 3. 비밀번호 수정 [ 인수 : 누구[식별 mno]의 비밀번호를 무엇으로[새로운 mpw] 바꿀건가 /  반환 : 성공[true] 실패[false] ]
	public boolean update( int mno , String mpw ) {
		
		// 1. SQL 작성
		String sql = "update member set mpw = ? where mno = ?";
		// 2. 연결 DB에 SQL 대입
		try {
			ps = conn.prepareStatement(sql);
		// 3. SQL 조작
			ps.setString(1, mpw);
			ps.setInt(2, mno);
		// 4. SQL 실행
			ps.executeUpdate(); // insert , update , delete => executeUpdate();	결과 1개
								// select 					=> executeQuery();	결과 여러개
			
		// 5. SQL 결과
			return true;
		} catch (Exception e) { System.out.println("DB 오류 : " + e ); }
		return false;
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}







