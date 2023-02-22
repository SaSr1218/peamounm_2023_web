package 과제.과제11;

import java.util.ArrayList;

import Day15.Ex9_MVC패턴.MemberDao;

public class Controller {
	
	// 싱글톤
	private static Controller con = new Controller();
	public Controller() { }
	public static Controller getInstance() { return con; }
	
	// 1. 제품등록
	public boolean signup( String pname , int pprice , int pbox ) {
		ProductDto dto = new ProductDto( 0 , pname , pprice , pbox );
		boolean result = ProductDao.getInstance().signup(dto);
		return result;
	}
	
	// 2. 모든 제품 출력
	public ArrayList<ProductDto> list() {
		
		ArrayList<ProductDto> result = ProductDao.getInstance().list();
		
		return result;
	}
	
	// 3. 제품 수정 (이름 , 가격)
	public boolean update( int pno , String pname , int pprice ) {
		return ProductDao.getInstance().update(pno, pname, pprice);
	}
	
	// 4. 제품 수정 (재고)
	public boolean update2( int pno , int pbox) {
		return ProductDao.getInstance().update2(pno, pbox);
	}
	
	// 5. 제품 삭제
	public boolean delete( int pno ) {
		return ProductDao.getInstance().delete(pno);
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}

	