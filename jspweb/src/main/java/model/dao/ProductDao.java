package model.dao;

import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;

import model.dto.ChatDto;
import model.dto.ProductDto;

public class ProductDao extends Dao{
	private static ProductDao dao = new ProductDao();
	private ProductDao() {}
	public static ProductDao getInstance() { return dao; }
	
	// 1. 물품 등록
	public boolean pwrite ( ProductDto dto ){
		// 1. 제품 우선 등록
		String sql = "insert into jspweb_product ( pname , pcomment , pprice , plat , plng , mno ) values ( ? , ? , ? , ? , ? , ? )";
		
		try {
			// insert 후 생성된 제품pk 번호 호출
			ps = con.prepareStatement(sql , Statement.RETURN_GENERATED_KEYS);
			ps.setString(1, dto.getPname() );
			ps.setString(2, dto.getPcomment() );
			ps.setInt(3, dto.getPprice() );
			ps.setString(4, dto.getPlat() );
			ps.setString(5, dto.getPlng() );
			ps.setInt(6, dto.getMno() );
			ps.executeUpdate();
			
			rs = ps.getGeneratedKeys(); // [ pno 구하기 ]
			
			if ( rs.next() ) { // 만약에 생성된 제품 pk번호가 존재하면
				// dto 내 첨부파일명 리스트에서 하나씩 첨부파일명을 insert 하기	
				for( String pimgname : dto.getPimglist() ) {
					sql = "insert into pimg ( pimgname , pno ) values ( ? , ? )";
					ps = con.prepareStatement(sql);
					ps.setString( 1 , pimgname ); 
					ps.setInt( 2 , rs.getInt(1) );
					ps.executeUpdate();
				}
			}
			
			return true;
		}catch (Exception e) {System.out.println(e);}
		return false;
	}
	
	// 2. 물품 전체 호출
	public ArrayList<ProductDto> getProductList( String 동 , String 서 , String 남 , String 북 ) {
		ArrayList<ProductDto> list = new ArrayList<>();
		String sql = "select p.* , m.mid , m.mimg from jspweb_product p natural join member m "
				+ " where ? >= plng and ? <= plng and ? <= plat and ? >= plat";
		try {
			ps = con.prepareStatement(sql);
			ps.setString(1, 동);
			ps.setString(2, 서);
			ps.setString(3, 남);
			ps.setString(4, 북);
			
			rs = ps.executeQuery();
			while(rs.next() ) {
				
				// 사진 레코드 호출
				ArrayList<String> pimglist = new ArrayList<>();
				sql = "select * from pimg where pno = "+rs.getInt(1) ; // 동일한 제품번호의 이미지들을 호출
				ps = con.prepareStatement(sql);
				ResultSet rs2 = ps.executeQuery();
				
				while(rs2.next() ) { // pno가 일치할 경우!
					pimglist.add( rs2.getString(2) ); // 검색된 이미지이름 리스트에 저장
				}
						
				ProductDto dto = new ProductDto(
						rs.getInt(1), rs.getString(2), rs.getString(3),
						rs.getInt(4), rs.getInt(5), rs.getString(6), 
						rs.getString(7), rs.getInt(8), rs.getString(9),
						rs.getInt(10), rs.getString(11), rs.getString(12), pimglist );
						
				list.add(dto);
			}
			
		}catch (Exception e) {System.out.println(e);}
		return list;
	}
	
	// 3. 찜하기 등록/취소
	public boolean setplike ( int pno , int mno ) {
		// 1. 등록할지 취소할지 먼저 검색하기
		String sql = "select * from plike where pno = "+pno+" and mno = "+mno ;
		
		try {
			ps = con.prepareStatement(sql);
			rs = ps.executeQuery();
			if (rs.next() ) { // 해당 회원이 이미 찜하기를 한 제품 --> 취소하기				
				sql = "delete from plike where pno = "+pno+" and mno = "+mno;
				ps = con.prepareStatement(sql);  // sql 문 리셋?
				ps.executeUpdate();
				return false;		// 취소 되었을때
			} else { // 해당 회원이 찜하기를 하지 않은 제품 -> 찜하기
				sql = "insert into plike( pno , mno ) values ( "+pno+" , "+mno+" )";
				ps = con.prepareStatement(sql);
				ps.executeUpdate();
				return true;		// 등록 되었을때
			}
		}catch (Exception e) {System.out.println( "찜하기 오류 : " + e);}
		return false;
	}
	
	// 4. 현재 회원의 해당 찜하기 제품 상태 확인하기
	public boolean getplike( int pno , int mno ) {
		String sql = "select * from plike where pno = "+pno+" and mno = "+mno ;
		
		try {
			ps = con.prepareStatement(sql); 
			rs = ps.executeQuery();
			if ( rs.next() ) { return true; }
		}catch (Exception e) {System.out.println(e);}
		return false;
	}
	
	// 5. 채팅받기
	public boolean setChat ( ChatDto dto ) {
		String sql = "insert into note (ncontent , pno , frommno , tomno ) values ( ? , ? , ? , ?)";
		try {
			ps = con.prepareStatement(sql);
			ps.setString(1, dto.getNcontent());
			ps.setInt(2, dto.getPno());
			ps.setInt(3, dto.getFrommno());
			ps.setInt(4, dto.getTomno());
			ps.executeUpdate();
			return true;
		}catch (Exception e) {System.out.println(e);}
		return false;
	}
	
	// 6. 채팅받은리스트 출력
	public ArrayList<ChatDto> getChatList ( int pno , int mno ) {
		String sql = "select * from note where pno = ? and ( frommno = ? or tomno = ? )";
		
		ArrayList<ChatDto> list = new ArrayList<>();
		try {
			ps = con.prepareStatement(sql);
			ps.setInt(1, pno);
			ps.setInt(2, mno);
			ps.setInt(3, mno);
			rs = ps.executeQuery();
			while (rs.next() ) {
				list.add( new ChatDto(
						rs.getInt(1), rs.getString(2), 
						rs.getString(3), rs.getInt(4), 
						rs.getInt(5), rs.getInt(6)) );
			} 
			
		}catch (Exception e) {System.out.println(e);}
		return list;
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}
