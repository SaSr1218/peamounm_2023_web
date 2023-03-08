package model.dao;

import java.util.ArrayList;

import model.dto.MemberDto;

public class MemberDao extends Dao{
	
	private static MemberDao dao = new MemberDao();
	private MemberDao() {}
	public static MemberDao getInstance() { return dao; }
	
	// 1. 회원가입
	public boolean signup ( MemberDto dto ) {
		
		String sql = "insert into member(mid,mpwd,memail,mimg) values(? , ? , ? , ? )";
		try {
			ps = con.prepareStatement(sql);
			ps.setString(1, dto.getMid());
			ps.setString(2, dto.getMpwd());
			ps.setString(3, dto.getMemail());
			ps.setString(4, dto.getMimg());
			ps.executeUpdate(); return true;

		}catch (Exception e) {System.out.println(e);}
		return false;
	}
	
	// 2. 회원호출
	public ArrayList<MemberDto> m_print(){
		
		ArrayList<MemberDto> list = new ArrayList<>();
		String sql = "select * from member";
		try {
			ps = con.prepareStatement(sql);
			rs = ps.executeQuery();
			while( rs.next() ) {
				MemberDto dto = new MemberDto( rs.getInt(1), rs.getString(2) , rs.getString(3) , rs.getString(4) , rs.getString(5) );
			list.add(dto);
			}
		}catch (Exception e) {System.out.println(e);}
		return list;
	}
	
	
	
	
}
