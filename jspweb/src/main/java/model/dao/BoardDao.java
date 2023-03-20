package model.dao;

import java.sql.ResultSet;
import java.util.ArrayList;

import model.dto.BoardDto;
import model.dto.ReplyDto;

public class BoardDao extends Dao {
	private static BoardDao dao = new BoardDao();
	private BoardDao() {}
	public static BoardDao getInstance() { return dao; }
	
	// 1. 글쓰기
	public boolean bwrite( BoardDto dto ) {
		String sql = "insert into board ( btitle , bcontent , bfile , mno , cno ) values ( ? , ? , ? , ? , ? )";
		try {
			ps = con.prepareStatement(sql);
			ps.setString(1, dto.getBtitle() );
			ps.setString(2, dto.getBcontent() );
			ps.setString(3, dto.getBfile() );
			ps.setInt(4, dto.getMno() );
			ps.setInt(5, dto.getCno() );
			ps.executeUpdate();
			return true;
		}catch (Exception e) {System.out.println(e);}
		return false;
	}
	// 2-2 게시물/레코드 수 구하기
	public int gettotalsize( String key , String keyword , int cno) {
		String sql = "";
		if( key.equals("") && keyword.equals("") ) { // 검색 존재 X
			sql = "select count(*) from member m natural join board b where b.cno = "+cno; 
		}else { // 검색 존재 O
			sql = "select count(*) from member m natural join board b "
					+ " where "+key+" like '%"+keyword+"%' and b.cno="+cno; 
		}
		try {
			ps = con.prepareStatement(sql);
			rs = ps.executeQuery();
			if ( rs.next() ) return rs.getInt(1);
					
		}catch (Exception e) {System.out.println(e);} return 0;
	}
	
	// 2. 모든 글 출력
	public ArrayList<BoardDto> getBoardList( int startrow , int lisisize , String key , String keyword , int cno ){
		ArrayList<BoardDto> list = new ArrayList<>();
		String sql = "";
		if ( key.equals("") && keyword.equals("") ) { // 검색 존재 X
			sql = "select b.* , m.mid , m.mimg from member m natural join board b "
					+ " where b.cno = "+cno
					+ " order by b.bdate desc limit ? , ? ";
		}else { // 검색 존재 O
			sql = 	"select b.* , m.mid , m.mimg from member m natural join board b "
					+ " where "+key+" like '%"+keyword+"%' and b.cno = "+cno
					+ " order by b.bdate desc limit ? , ? " ;
		}
	
		try {
			ps = con.prepareStatement(sql);
			ps.setInt(1, startrow);
			ps.setInt(2, lisisize);
			rs = ps.executeQuery();
			while ( rs.next() ) {
				BoardDto dto = new BoardDto(
						rs.getInt(1), 	 rs.getString(2), rs.getString(3),
						rs.getString(4), rs.getString(5), rs.getInt(6), 
						rs.getInt(7), 	 rs.getInt(8), 	  rs.getInt(9), 
						rs.getInt(10),   rs.getString(11) );
				// !! : 추가된 프로필 이미지 대입
				dto.setMimg(rs.getString(12) );
				
				// !! : 현재 게시물[레코드]의 댓글 수 구하기
				sql = "select count(*) from reply where bno = "+dto.getBno();	// sql = "select count(*) from reply where bno = "+rs.getBno();
				// 모든 게시물을 찾은 rs가 아직 끝나지 않은 상태이므로 새로운 rs를 따로 생성해 선언(방법 1) vs (방법 2) select 중첩
				ps = con.prepareStatement(sql);
				ResultSet rs2 = ps.executeQuery(); 
				if (rs2.next() ) { dto.setRcount(rs2.getInt(1) ); }
				
				list.add(dto);
			}
			
		}catch (Exception e) {System.out.println(e);}
		return list;
	}
	
	// 3. 개별 글 조회 출력
	public BoardDto getBoard( int bno ){
		ArrayList<BoardDto> list = new ArrayList<>();
		String sql = "select b.* , m.mid , m.mimg from member m natural join board b where b.bno = ? ";
		
		try {
			ps = con.prepareStatement(sql);
			ps.setInt(1, bno);
			rs = ps.executeQuery();
			if ( rs.next() ) {
				BoardDto dto = new BoardDto(
						rs.getInt(1), 	 rs.getString(2), rs.getString(3),
						rs.getString(4), rs.getString(5), rs.getInt(6), 
						rs.getInt(7), 	 rs.getInt(8), 	  rs.getInt(9), 
						rs.getInt(10),   rs.getString(11) );
				
				// !! : 추가된 프로필 이미지 대입
				dto.setMimg(rs.getString(12) );
				
				// !! : 현재 게시물[레코드]의 댓글 수 구하기
				sql = "select count(*) from reply where bno = "+dto.getBno();	// sql = "select count(*) from reply where bno = "+rs.getBno();
				// 모든 게시물을 찾은 rs가 아직 끝나지 않은 상태이므로 새로운 rs를 따로 생성해 선언(방법 1) vs (방법 2) select 중첩
				ps = con.prepareStatement(sql);
				ResultSet rs2 = ps.executeQuery(); 
				if (rs2.next() ) { dto.setRcount(rs2.getInt(1) ); }
				return dto;
			}
			
		}catch (Exception e) {System.out.println(e);}
		return null;
	}

	// 4. 조회수/좋아요/싫어요 증가
	public boolean bIncrease( int type , int bno ) {
		String sql = "";
		if ( type == 1 ) { sql="update board set bview = bview+1 where bno = "+bno; }
		if ( type == 2 ) { sql="update board set bgood = bgood+1 where bno = "+bno; }
		if ( type == 3 ) { sql="update board set bbad  = bbad+1	 where bno = "+bno; }
		
		try {
			ps = con.prepareStatement(sql);
			ps.executeUpdate();
			return true;
		}catch (Exception e) {System.out.println(e);}
		return false;
	}
	
	// 5. 게시물 삭제
	public boolean bdelete ( int bno ) {
		String sql = "delete from board where bno = "+bno;
		try {
			ps = con.prepareStatement(sql);
			int count = ps.executeUpdate();
			if ( count == 1 ) return true;
		}catch (Exception e) {System.out.println(e);}
		return false;
	}
	
	// 6. 게시물 수정
	public boolean bupdate( BoardDto dto ) {
		String sql = "update board set btitle = ? ,bcontent = ? , bfile = ? , cno = ? where bno = ? ";
		try {
			ps = con.prepareStatement(sql);
			ps.setString(1, dto.getBtitle() );
			ps.setString(2, dto.getBcontent() );
			ps.setString(3, dto.getBfile() );
			ps.setInt(4, dto.getCno() );
			ps.setInt(5, dto.getBno() );
			int count = ps.executeUpdate();
			if ( count ==1 ) return true;
		}catch (Exception e) {System.out.println(e);}
		return false;
	}
	
	// 7. 파일만 삭제수정
	public boolean bfiledelete( int bno ) {
		String sql = "update board set bfile = null where bno ="+bno;
		try {
			ps = con.prepareStatement(sql); 
			int count = ps.executeUpdate();
			if ( count == 1 ) return true;
		}catch (Exception e) {System.out.println(e);}
		return false;
		
	}
	
	// 8. 댓글 쓰기 
	public boolean rwrite( ReplyDto dto ) {
		try {
			String sql ="";
			if( dto.getRindex() == 0 ) { // 상위댓글
				 sql = "insert into reply(rcontent,mno,bno)values(?,?,?)";
			}else { // 하위댓글 
				 sql = "insert into reply(rcontent,mno,bno,rindex)values(?,?,?,?)";
			}
			ps = con.prepareStatement(sql);	ps.setString( 1, dto.getRcontent() );
			ps.setInt( 2, dto.getMno() );	ps.setInt( 3, dto.getBno() );
			
			// 하위댓글
			if( dto.getRindex() !=0 ) ps.setInt( 4 , dto.getRindex() );
			
			ps.executeUpdate(); return true ;	
			
		}catch (Exception e) {System.out.println(e);	} return false;
		
	}
	// 9. 댓글 출력 
	public ArrayList<ReplyDto> getReplyList( int bno , int rindex ){
		ArrayList<ReplyDto> list = new ArrayList<>();
	
		String sql = " select r.* , m.mid , m.mimg from reply r natural join member m "
					+ " where r.rindex = "+rindex+" and r.bno = "+bno;
		
		try {
			ps = con.prepareStatement(sql);	rs = ps.executeQuery();
			while( rs.next() ) {
				ReplyDto dto = new ReplyDto(rs.getInt(1), rs.getString(2), 
						rs.getString(3), rs.getInt(4), rs.getInt(5), rs.getInt(6), 
						rs.getString(7), rs.getString(8) );
				list.add(dto);
			}
		}catch (Exception e) {System.out.println(e);} return list;
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}
