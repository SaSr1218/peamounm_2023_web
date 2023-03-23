package model.dao;

import model.dto.ProductDto;

public class ProductDao extends Dao{
	private static ProductDao dao = new ProductDao();
	private ProductDao() {}
	public static ProductDao getInstance() { return dao; }
	
	// 1. 물품 등록
	public boolean pwrite ( ProductDto dto ){
		String sql = "insert into jspweb_product ( pname , pcomment , pprice , plat , plng ) values ( ? , ? , ? , ? , ? )";
		
		try {
			ps = con.prepareStatement(sql);
			ps.setString(1, dto.getPname() );
			ps.setString(2, dto.getPcomment() );
			ps.setInt(3, dto.getPprice() );
			ps.setString(4, dto.getPlat() );
			ps.setString(5, dto.getPlng() );
			ps.executeUpdate();
			return true;
		}catch (Exception e) {System.out.println(e);}
		return false;
	}
	
}
