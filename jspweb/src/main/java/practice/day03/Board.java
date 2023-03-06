package practice.day03;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class Board
 */
@WebServlet("/Ex3/Board")
public class Board extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Board() {
        super();
        // TODO Auto-generated constructor stub
    }

    // 1. POST [등록]
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// 1. 요청시 한글 인코딩	
		
		request.setCharacterEncoding("UTF-8");
		
		// 2. 매개변수 요청 [ AJAX data속성에서 보내준 매개변수 이름 ]
		String content = request.getParameter("content");
			System.out.println( " content : " + content );
		String writer = request.getParameter("writer");
			System.out.println( " writer : " + writer);
			
		// 3. Dto 화 [ 기본값 : int 필드의 0 / 객체 필드의 null ]
		BoardDto boardDto = new BoardDto(0, content, writer, 0);
			System.out.println( " dto : " + boardDto );
		
		// 4. Dao 호출해서 결과 저장
		boolean result = BoardDao.getInstance().onwrite(boardDto);
			System.out.println(" onwrite result : " + result);
		
		// 5. Dao 결과인 true, false 데이터를 response
			// "true" vs { 1 : true , 2 : false , 3 : true } // true 일때 문자인 "true" 를 보낸 것임.
			response.getWriter().print(result);
	}


	// 2. GET
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

	}
	
	// 3. PUT
	protected void doPut(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

	}

	// 4. DELETE
	protected void doDelete(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

	}

}
