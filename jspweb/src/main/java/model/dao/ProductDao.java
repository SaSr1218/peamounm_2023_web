package model.dao;

import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;

import model.dto.ChatDto;
import model.dto.ProductDto;

public class ProductDao extends Dao{
	private static ProductDao dao = new ProductDao();
	private ProductDao() {}
	public static ProductDao getInstance() { return dao; }
	
	// 1. 물품 등록 [ synchronized : 멀티스레드 사용시(servlet) wait 상태 걸기 ]
	public synchronized boolean pwrite ( ProductDto dto ){
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
	public synchronized ArrayList<ProductDto> getProductList( String 동 , String 서 , String 남 , String 북 ) {
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
	public synchronized boolean setplike ( int pno , int mno ) {
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
	public synchronized boolean getplike( int pno , int mno ) {
		String sql = "select * from plike where pno = "+pno+" and mno = "+mno ;
		
		try {
			ps = con.prepareStatement(sql); 
			rs = ps.executeQuery();
			if ( rs.next() ) { return true; }
		}catch (Exception e) {System.out.println(e);}
		return false;
	}
	
	// 5. 제품에 채팅 등록
	public synchronized boolean setChat ( ChatDto dto ) {
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
	
	// 6. 채팅받은리스트 출력 [ 제품번호 일치 , 현재 로그인 된 회원의 받거나 보낸 내용들 ]
	public synchronized ArrayList<ChatDto> getChatList ( int pno , int mno , int chatmno ) {
		ArrayList<ChatDto> list = new ArrayList<>();
		
		// 판매자 측에서 문제 발생. 모두 다 출력받아서 방에 상관없이 자기가 받은 내용이 다 뜬다.
		// String sql = "select * from note where pno = ? and ( frommno = ? or tomno = ? ) ";
		
		// 현재 같이 채팅 하고 있는 대상자의 내용물만 출력해야함.
		String sql = "";
		
		if ( chatmno !=0 ) { // 현재 같이 채팅 하고 있는 대상자들[로그인된회원,채팅대상자]의 내용물만 출력
			sql = "select * from note where pno = ? and "
					+ " ( ( frommno = ? and tomno = ? ) or ( frommno = ? and tomno = ? ) )";
		} else {
			sql = "select * from note where pno = ? and ( frommno = ? or tomno = ? ) ";
		}
		
		try {
			ps = con.prepareStatement(sql);
			
			ps.setInt(1, pno); 
			if ( chatmno !=0 ) { 
			ps.setInt(2, mno); ps.setInt(3, chatmno);
			ps.setInt(4, chatmno); ps.setInt(5, mno);
			} else {
				ps.setInt(2, mno); ps.setInt(3, mno);
			}
			rs = ps.executeQuery();
			while (rs.next() ) {
				ChatDto dto =  new ChatDto(
						rs.getInt(1), rs.getString(2), 
						rs.getString(3), rs.getInt(4), 
						rs.getInt(5), rs.getInt(6));
				
				// 보낸 회원 정보 호출
				sql = "select mid , mimg from member where mno = " + rs.getInt(5); // rs.getInt(5) = frommno
				ps = con.prepareStatement(sql);
				ResultSet rs2 = ps.executeQuery();
				if ( rs2.next() ) {
					dto.setFrommid( rs2.getString(1) );
					dto.setFrommimg( rs2.getString(2) ); 
				}
				list.add(dto);
			} 
			
		}catch (Exception e) {System.out.println(e);}
		return list;
	}
	
	/*
	- 1. 로그인된 회원기준으로 보내거나 받은 메시지 모두 출력
		select * from noet where pno = ? and ( frommno = ? or tomno = ? )
		1. 구매가 문제 없음 2. 판매자는 채팅 대상자의 메시지만 출력 해야하는데 여기서 문제 발생
	- 2. 
  		만약에 채팅방에 4번, 5번 회원이 있다.
  		frommno = 4이면서 tomno = 5 이거나 frommno = 5이면서 tomno = 4
  		- 4번회원이 보냈거나 받았으면 / 5번회원이 받았거나 보냈으면
 */	
	
	// 7. 날짜별 포인트 충전 내역 출력
	public HashMap < String , Integer > getSum(){
		// ArrayList<String> list ; 	// String 타입객체만 리스트에 저장
		HashMap< String, Integer > map = new HashMap<>();
		String sql = " select sum( if ( mpcomment='포인트 충전' , mpamount , 0 ) ) as 충전된포인트 , date_format( mpdate, '%Y%m%d' ) as 충전날짜 "
				+    " from mpoint group by date_format( mpdate , '%Y%m%d') order by 충전날짜 desc limit 5 ";
		try {
			ps = con.prepareStatement(sql);
			rs = ps.executeQuery();
			while ( rs.next() ) {
				map.put( rs.getString(2), rs.getInt(1) );
			}
			
		}catch (Exception e) {System.out.println(e);}
		return map;
	}


/*

  	// 1. 해당 타입의 객체를 여러개 저장할수 있는 리스트 객체 선언 
 	ArrayList< 타입 > list = new ArrayList<>();
 		데이터 : '유재석' , '강호동' , '신동엽' 	-> 타입 1개 
 		[ '유재석' , '강호동' , '신동엽'  ]
 		
 		.add( 데이터 )
 		
 	// 2. 해당 키타입과 데이터타입 의 해당하는 키와데이터를 여러개 저장할수 있는 맵 객체 선언 
 	HashMap< 키타입 , 데이터타입 > map = new HashMap<>();
 		데이터 : '유재석=30' , '강호동=10' , '신동엽=90' -> 타입 2개 
 		{ '유재석'=30 , '강호동'=10 , '신동엽'=90  }
 		
 		.put( 키 , 데이터 )
 		
 	// * JSON = JS객체
 	LET 객체명 = { 필드명 : 값 , 필드명 : 값  }	    
 		
 	
 */
	
	
	
	
	
	
	
	
	
	
}
