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

    // 1. POST [ C : 등록]
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// 1. 요청시 한글 인코딩	
		
		request.setCharacterEncoding("UTF-8");
		
		// 2. 매개변수 요청 [ AJAX data속성에서 보내준 매개변수 이름 ]
		String content = request.getParameter("content");
			System.out.println( " content : " + content );
		String writer = request.getParameter("writer");
			System.out.println( " writer : " + writer);
			
		// 3. Dto 화 [ 기본값 : int 필드의 0 / 객체 필드의 null ]
		BoardDto boardDto = new BoardDto(0, content, writer, null);
			System.out.println( " dto : " + boardDto );
		
		// 4. Dao 호출해서 결과 저장
		boolean result = BoardDao.getInstance().onwrite(boardDto);
			System.out.println(" onwrite result : " + result);
		
		// 5. Dao 결과인 true, false 데이터를 response
			// "true" vs { 1 : true , 2 : false , 3 : true } // true 일때 문자인 "true" 를 보낸 것임.
			response.getWriter().print(result);
	}

	// 2. GET [ R : 전체 출력 ]
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		// 1. 응답 매개변수 한글 인코딩
		response.setCharacterEncoding("UTF-8");
		
		// 2. DAO 호출해서 모든 게시물을 반환해서 저장
		ArrayList<BoardDto> result = BoardDao.getInstance().onprint();	System.out.println(" DAO LISt : " + result );
		
		// 3. JSON 형식의 문자열로 변환
		ObjectMapper mapper = new ObjectMapper();	// 1. JSON 라이브러리에서 제공하느 클래스
		String jsonArray = mapper.writeValueAsString(result);			// 2. DAO로부터 받은 리스트를 json 형식의 문자열 변환
			System.out.println(" jsonArray : " + jsonArray );
		
		// 4. 응답
		response.setContentType("application/json");
		response.getWriter().print(jsonArray);
		
	}
	
	// 3. DELETE [ D : 삭제 ]
	protected void doDelete(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// 1. 삭제할 게시물번호 요청 [ ????? 매개변수 null ] delete , put 은 get요청과 같이 했을시 오류뜸!
		int bno = Integer.parseInt(request.getParameter("bno") ); 
			System.out.println("삭제할번호요청 : " + bno);
		// 2. Dao 호출해서 결과 얻기
		boolean result = BoardDao.getInstance().ondelete(bno);
		// 3. 응답
		response.getWriter().print(result);
	}
	
	// 4. PUT [ U : 수정 ]
	protected void doPut(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		// 1. 수정할 게시물번호 요청
		int bno = Integer.parseInt(request.getParameter("bno") );
			System.out.println("수정할번호요청 : " + bno);
		// 2. 수정할 게시물내용 요청
		String newContent = request.getParameter("newContent");
			System.out.println("수정할내용요청 : " + newContent);
		// 3. Dao 호출해서 결과 얻기
		boolean result = BoardDao.getInstance().onupdate(bno, newContent);
		// 4. 결과 응답
		response.getWriter().print(result);
	}

}


/*
 
 	JSP 서블릿 에서 HTTP 메소드 put , delete 사용시 필수 설정
 	
 	1. Servers 폴더 클릭
 	2. Tomcat -> server.xml 클릭 -> 아래쪽에 Design 옆에 Source 클릭(Tomcat 설정값)
 	3. 60번대 줄(63번!) Connector connectionTimoout="20000" ~~~ 
 					-->	parseBodyMethods="POST,PUT,DELETE" 추가 후 서버 재가동
  	(ex)
  	원본	: <Connector connectionTimeout="20000" port="8080" protocol="HTTP/1.1" redirectPort="8443" /> 
	수정본 : <Connector connectionTimeout="20000" port="8080" protocol="HTTP/1.1" redirectPort="8443" parseBodyMethods="POST,PUT,DELETE" URIEncoding="UTF-8" />  
 */

















