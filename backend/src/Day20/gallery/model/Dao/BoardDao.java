package Day20.gallery.model.Dao;

import java.util.ArrayList;

import Day20.gallery.model.Dto.BoardDto;
import Day20.gallery.model.Dto.CategoryDto;

public class BoardDao extends Dao{

	// 1. 싱글톤
	private static BoardDao dao = new BoardDao();
	private BoardDao() { }
	public static BoardDao getInstance() { return dao; }
	
	// 1. 카테고리 추가 SQL [ 인수 : 입력받은 갤러리이름 / 반환 : 성공, 실패 ]
	public boolean categoryAdd( String cname ) {
		String sql = "insert into category ( cname ) values ( ? )";
		try {
			ps = con.prepareStatement(sql);
			ps.setString(1, cname);
			ps.executeUpdate();
			return true;
		}catch (Exception e) {System.out.println(e);}
		return false;
	}

	
	// 2. 모든 카레고리 호출 SQL [ 인수 : x / 반환 : 여러개[ 리스트, 배열 ] 의 카테고리Dto 반환 
	public ArrayList< CategoryDto > categoryPrint() {
		
		ArrayList<CategoryDto> clist = new ArrayList<>(); // 여러개 cdto 담을 리스트 선언
		
		String sql = "select * from category";
		try {
			ps = con.prepareStatement(sql);
			rs = ps.executeQuery();
			
			// 모든 정보 호출 = rs.next()를 무한루프 돌려야함. 찾은 걸 다 꺼내야해서 
			// 아이디 , 비밀번호 찾기 는 if로 rs.next() true, false 값 찾기만 하면 됨
			while ( rs.next() ) {
				// 레코드 --> dto		// rs.get타입 ( 필드순서번호 ) 1번째 필드는 cno 2번째필드는 cname
				CategoryDto dto = new CategoryDto(rs.getInt(1), rs.getString(2) );
				clist.add(dto);
			}
			return clist;
		}catch (Exception e) {System.out.println(e);}
		return null;
	}

	// 3. 게시물 등록
	public boolean boardAdd( String btitle , String bcontent , int mno , int cno) {
		String sql = "insert into board( btitle , bcontent , mno , cno ) values ( ? , ? , ? , ? )";
		try {
			ps = con.prepareStatement(sql);
			ps.setString( 1, btitle );
			ps.setString( 2, bcontent );
			ps.setInt( 3, mno );
			ps.setInt( 4, cno );
			ps.executeUpdate();
			return true;
			
		}catch (Exception e) {System.out.println(e);}
		return false;
	}
	
	// 4. 최신 게시물 출력 3개 출력
	public ArrayList<BoardDto> boardPrintRecent() {
		ArrayList<BoardDto> blist = new ArrayList<>();
		String sql = " select b.bno , b.btitle , b.bcontent , b.bdate , b.bview , m.mid , c.cname "
				+ " from board b , member m , category c "
				+ " where b.mno = m.mno and b.cno = c.cno "
				+ " order by b.bdate desc limit 3 ";
		try {
			ps = con.prepareStatement(sql);
			rs = ps.executeQuery();
			while( rs.next() ) {
				BoardDto dto = new BoardDto( 
						rs.getInt(1) , rs.getString(2) , rs.getString(3) ,
						rs.getString(4) , rs.getInt(5) , rs.getString(6), rs.getString(7));
				blist.add(dto);
			}
		}catch (Exception e) {System.out.println(e);}
		return blist;
	}
	
	// 5. 해당 카테고리의 모든 게시물 출력
	public ArrayList<BoardDto> boardPrint( int cno ) {
		ArrayList<BoardDto> blist = new ArrayList<>();
		String sql = " select b.bno , b.btitle , b.bcontent , b.bdate , b.bview , m.mid , c.cname "
				+ " from board b , member m , category c "
				+ " where b.mno = m.mno and b.cno = c.cno and b.cno = ?";
		try {
			ps = con.prepareStatement(sql);
			ps.setInt(1, cno);
			rs = ps.executeQuery();
			while( rs.next() ) {
				BoardDto dto = new BoardDto( 
						rs.getInt(1) , rs.getString(2) , rs.getString(3) ,
						rs.getString(4) , rs.getInt(5) , rs.getString(6), rs.getString(7));
				blist.add(dto);
			}
		}catch (Exception e) {System.out.println(e);}
		return blist;
	}

	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
		
		
		
		
}
