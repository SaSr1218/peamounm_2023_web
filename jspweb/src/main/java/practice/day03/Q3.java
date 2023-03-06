package practice.day03;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.fasterxml.jackson.databind.ObjectMapper;

/**
 * Servlet implementation class Q3
 */
@WebServlet("/Q3")
public class Q3 extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Q3() {
        super();
    }

	// 2. C
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		
		String pname = request.getParameter("pname");
		int pprice = Integer.parseInt(request.getParameter("pprice") );
			System.out.println("pname : " + pname );
			System.out.println("pprice : " + pprice);
		
		ProductDto productDto = new ProductDto(0, pname, pprice);
			System.out.println(" pdto : " + productDto);
			
		boolean result = BoardDao.getInstance().c_product(productDto);
			System.out.println( "c_product result : " + result );
			
		response.getWriter().print(result);
		
	}
    
    // 1. R
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setCharacterEncoding("UTF-8");
		
		ArrayList<ProductDto> result = BoardDao.getInstance().c_print();
			System.out.println( " dao list : " + result);
		ObjectMapper mapper = new ObjectMapper();
		String jsonArray = mapper.writeValueAsString(result);
			System.out.println("jsonArray : " + jsonArray);
		
		response.setContentType("application/json");
		response.getWriter().print(jsonArray);
	}



	// 3. U
	protected void doPut(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		
		int pno = Integer.parseInt(request.getParameter("pno") );
			System.out.println("수정할 번호 요청 : " + pno ); 
		
		String newPname = request.getParameter("newPname");
			System.out.println("수정할 내용 요청 :  " + newPname );
		
		int newPprice = Integer.parseInt(request.getParameter("newPprice") );
			System.out.println("수정할 가격 요청 : " + newPprice );
		
		boolean result = BoardDao.getInstance().c_update(pno, newPname, newPprice);
		
		response.getWriter().print(result);
		
	}

	// 4. D
	protected void doDelete(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int pno = Integer.parseInt(request.getParameter("pno") );
			System.out.println("삭제할 번호 요청 : " + pno );
		boolean result = BoardDao.getInstance().c_delete(pno);
		
		response.getWriter().print(result);
		
	}

}
