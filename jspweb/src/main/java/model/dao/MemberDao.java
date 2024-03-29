package model.dao;

import java.sql.Statement;
import java.util.ArrayList;

import model.dto.MemberDto;

public class MemberDao extends Dao {

	private static MemberDao dao = new MemberDao();
	private MemberDao() {}
	public static MemberDao getInstance() { return dao; }
	
	
	// 1. 회원가입 
	public boolean signup( MemberDto dto ) {
		String sql = "insert into member(mid,mpwd,memail,mimg)values(?,?,?,?)";
		try {
			ps = con.prepareStatement(sql , Statement.RETURN_GENERATED_KEYS);
			ps.setString( 1 , dto.getMid() );
			ps.setString( 2 , dto.getMpwd() );
			ps.setString( 3 , dto.getMemail() );
			ps.setString( 4 , dto.getMimg() );
			ps.executeUpdate(); 
			rs = ps.getGeneratedKeys(); // 방금 생성된 pk값을 rs로 받기
			if ( rs.next() ) { 
				int pk = rs.getInt(1);
				setPint("회원가입축하" , 100 , pk );

			}
				// 가입 포인트 지급 [ 내용 , 개수 , 방금 회원가입한 회원번호[pk] ]
				
				/*
				 	- inser 이후에 자동으로 생성된 auto key 찾기
				 	ps = con.prepareStatement(sql);
				 	1. 아래로 변경
				 	ps = con.prepareStatement(sql , Statement.RETURN_GENERATED_KEYS);
				 	2. 생성된 pk 결과 담기
				 	rs = ps.getGeneratedKeys(); // 방금 생성된 pk값을 rs로 받기
				 	3.
				 	if ( rs.next() ) { 
						int pk = rs.getInt(1);
						setPint("회원가입축하" , 100 , pk );
					}
				 
				 */
			return true; 
		}catch (Exception e) {System.out.println(e);}
		return false;
	}
	
	// 2. 모든 회원 호출 [ 관리자기준  인수:x 반환:모든회원들의 dto ]
	public ArrayList<MemberDto> getMemberList( int startrow , int listsize , String key , String keyword ){
		ArrayList<MemberDto> list = new ArrayList<>(); // 모든 회원들의 리스트 선언 
		String sql = "";		
		
		if ( key.equals("") && keyword.equals("") ) {
			sql = "select m.mno , m.mid , m.mimg , m.memail from member m"
					+ " limit ? , ? ";
		} else {
			sql = "select m.mno , m.mid , m.mimg , m.memail from member m "
					+ " where "+key+" like '%"+keyword+"%'"
					+ " limit ? , ? ";
		}
		
		try {
			ps = con.prepareStatement(sql);	
			ps.setInt(1, startrow);
			ps.setInt(2, listsize);
			rs = ps.executeQuery();						
			while( rs.next() ) {						
				MemberDto dto = new MemberDto(			
						rs.getInt(1), rs.getString(2), rs.getString(3), 
						rs.getString(4) );
				list.add(dto);							
			}// w end 
		}catch (Exception e) {System.out.println(e);}
		return list;									
	}
	
	// 2-1 게시물/레코드 수 구하기
	public int gettotalsize( String key , String keyword) {
		String sql = "";
		if ( key.equals("") && keyword.equals("") ) {
			sql = "select count(*) from member m ";
		} else {
			sql = "select count(*) from member m "
					+ " where "+key+" like '%"+keyword+"%' ";
		}
		try {
			ps = con.prepareStatement(sql);
			rs = ps.executeQuery();
			if ( rs.next() ) return rs.getInt(1);
					
		}catch (Exception e) {System.out.println(e);} return 0;
	}
	
	// 3. 아이디 중복 검사
	public boolean idcheck( String mid ) {
		String sql = "select * from member where mid = '"+mid+"' ";
		try {
			ps = con.prepareStatement(sql);
			rs = ps.executeQuery();
			if ( rs.next() ) { return true; } // 아이디 중복이면 true
		}catch (Exception e) {System.out.println(e);}
		return false; // 아이디 중복 아니면 false
	}
	
	// 4. 로그인
	public boolean login( String mid , String mpwd ) {
		String sql = "select * from member where mid = ? and mpwd = ?";
		try {
			ps = con.prepareStatement(sql);
			ps.setString(1, mid);
			ps.setString(2, mpwd);
			rs = ps.executeQuery();
			if ( rs.next() ) { return true; }
		}catch (Exception e) {System.out.println(e);}
		return false;
	}
	
	// 5. 비밀번호를 제외한 정보 호출
	public MemberDto getMember( String mid ) { // session에 mid가 들어감
		String sql = "select m.mno , m.mid , m.mimg , m.memail , sum(p.mpamount) as mpoint "
				+ " from member m , mpoint p "
				+ " where m.mno = p.mno and m.mid = ?";
		try {
			ps = con.prepareStatement(sql);
			ps.setString(1, mid);
			rs = ps.executeQuery();
			if ( rs.next() ) { // 비밀번호 제외 // mno , mid , mimg , memail , mpoint
				MemberDto dto = new MemberDto(rs.getInt(1), rs.getString(2), null , rs.getString(3), rs.getString(4) );
				dto.setMpoint(rs.getInt(5)); // 생성자 안만들어서 별도 세팅
				return dto;
			
			}
		}catch (Exception e) {System.out.println(e);}
		return null;
	}
	
	// 6. 아이디 찾기
	public String findid( String memail ) {
		String sql = "select mid from member where memail = ?";
			try {
				ps = con.prepareStatement(sql);
				ps.setString(1, memail);
				rs = ps.executeQuery();
				if ( rs.next() ) { return rs.getString(1); }
			}catch (Exception e) {System.out.println(e);}
			return "false";
	}
	
	// 7. 비밀번호 찾기
	public String findpwd( String mid , String memail , String updatePwd ) {
		String sql = "select mno from member where mid = ? and memail = ?";
		try {
			ps = con.prepareStatement(sql);
			ps.setString( 1 , mid );
			ps.setString( 2 , memail);
			rs = ps.executeQuery();
			if( rs.next() ) {  // 만약에 동일한 아이디와 이메일 일치한 레코드가 있으면 [ 찾았다. ]
				sql = "update member set mpwd = ? where mno = ?";
				ps = con.prepareStatement(sql);
				ps.setString( 1 , updatePwd );		// 임시비밀번호로 업데이트
				ps.setInt( 2 ,  rs.getInt( 1 ) );	// select 에서 찾은 레코드의 회원번호 
				int result = ps.executeUpdate();	// 업데이트한 레코드 개수 반환
				if( result == 1 ) { // 업데이트한 레코드가 1개 이면 
					// -- 이메일전송 테스트 되는경우 만 -- //
					// new MemberDto().sendEmail( memail, updatePwd ); // 임시비밀번호를 이메일로 보내기 
					// return "true";
					
					//  -- 이메일전송 테스트 안되는 경우 -- //
					return updatePwd;
				}
			}
		}catch (Exception e) {System.out.println(e);} 
		return "false";
	}
	
	// 8. 포인트 함수 [ 1. 지급내용 2. 지급개수 3. 지급대상 ]
	public boolean setPint( String content , int point , int mno ) {
		String sql = "insert into mpoint ( mpcomment , mpamount , mno) values ( ? , ? , ? ) ";
		
		try {
			ps = con.prepareStatement(sql);
			ps.setString(1, content);
			ps.setInt(2, point);
			ps.setInt(3, mno);
			ps.executeUpdate();
			return true;
		}catch (Exception e) {System.out.println(e);}
		return false;
	} 
	
	// 9. 회원탈퇴 [ 인수 : mid , mpwd 반환 : 성공/실패 ]
	public boolean delete( String mid , String mpwd ) {
		String sql = "delete from member where mid = ? and mpwd = ?";
		try {
			ps = con.prepareStatement(sql);
			ps.setString(1, mid);
			ps.setString(2, mpwd);
			int count = ps.executeUpdate(); // 삭제된 레코드수 반환 ( 0개 삭제해도 성공되긴 함)
			if ( count == 1 ) { return true; } // 레코드 1개 삭제 성공시 true
		}catch (Exception e) {System.out.println(e);}
		return false;
	}
	
	// 10. 회원수정
	public boolean update( String mid , String mpwd , String newmpwd , String memail , String newmimg ) {
		String sql = "update member set mpwd = ? , memail = ? , mimg = ? where mid = ? and mpwd = ?";
		try {
			ps = con.prepareStatement(sql);
			ps.setString(1, newmpwd);
			ps.setString(2, memail);
			ps.setString(3, newmimg);
			ps.setString(4, mid);
			ps.setString(5, mpwd);
			
			int count = ps.executeUpdate(); // 수정된 레코드 수 반환 ( 0개 수정해도 성공되긴 함)
			if ( count == 1 ) { return true; } // 레코드 1개 수정 성공시 true
		}catch (Exception e) {System.out.println(e);}
		return false;
	}
	
	// 11. 회원 id --> 회원mno로 바꾸기
	public int getMno( String mid ) {
		String sql = "select mno from member where mid = ? ";
		try {
			ps = con.prepareStatement(sql);
			ps.setString(1, mid);
			rs = ps.executeQuery();
			if ( rs.next() )  return rs.getInt(1); 
		}catch (Exception e) {System.out.println(e);}
		return 0;
	}
	
	
	
}

















