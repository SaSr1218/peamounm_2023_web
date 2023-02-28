package Day20.gallery.model.Dao;

import Day20.gallery.model.Dto.MemberDto;

public class MemberDao extends Dao{

	// 1. 싱글톤
	private static MemberDao dao = new MemberDao();
	private MemberDao() { }
	public static MemberDao getInstance() { return dao; }
	
	// 기능 메소드
	
	// 1. 아이디 중복체크
	public boolean idCheck ( String mid ) {
		String sql = "select * from member where mid = ?";
		try {
			ps = con.prepareStatement(sql);
			ps.setString(1, sql);
			rs = ps.executeQuery();
			// 검색된 레코드가 있으면 [ 중복된 아이디 ]
			if ( rs.next() ) { return true; }
			// 없으면 [중복된 아이디가 없다 ]
			else { return false; }
			
		}catch (Exception e) { System.out.println(e);}
		return true; // 그외
	}
	
	// 2. 회원가입
	public int signup( MemberDto dto ) {
		String sql = "insert into member ( mid , mpw , mname , mphone ) values ( ? , ? , ? , ? )";
		try {
				ps = con.prepareStatement(sql);
				ps.setString(1, dto.getMid() );
				ps.setString(2, dto.getMpw() );
				ps.setString(3, dto.getMname() );
				ps.setString(4, dto.getMphone() );
				ps.executeUpdate();
				return 1; // 성공
			}catch (Exception e) { System.out.println(e); }
			return 3; // db오류
	} 
	
	// 3. 로그인 [ 반환 : 로그인 성공한 회원번호 반환 ]
		// - 로그인[로그인 되어있는 동안 저장되는]세션 : 1.회원번호 2.Logindto[회원번호 , 포인트, 등급 등 ]
	
	public int login ( String mid , String mpw ) {
		String sql = "select * from member where mid = ? and mpw = ?";
		try {
				ps = con.prepareStatement(sql);
				ps.setString( 1, mid);
				ps.setString( 2, mpw);
				rs = ps.executeQuery();
			if ( rs.next() ) {
				// 만약에 레코드가 존재하면 [ 로그인 성공 ] 
				return rs.getInt(1); // 검색된 레코드에서 첫번째 필드[회원번호] 반환 -> 오른쪽 갈수록 2, 3, 4, 5
			} else {
				// 만약에 레코드가 존재하지 않으면 [ 로그인 실패 ]
				return 0;
			}
		} catch (Exception e) { System.out.println(e) ;}
		return 0;
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}
