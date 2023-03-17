package controller.board;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.dao.BoardDao;

@WebServlet("/board/view")
public class View extends HttpServlet {
	private static final long serialVersionUID = 1L;

    public View() { super();}

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int type = Integer.parseInt(request.getParameter("type") );
		int bno = Integer.parseInt(request.getParameter("bno") );
		
		/*
		 	- 특정 시간 [ 하루에 한번씩만 조회수,좋아요,싫어요 증가 ]
		 	- 회원별 vs PC마다
		 	- 세션 사용
		 */
		// 1. pc 네트워크 식별자 [ ip ]
			// 1. java에서 현재 요청한 클라이언트의 ip 확인
			String ip = request.getRemoteAddr();
			
			// 4. 만약에 기존 세션이 없으면 증가 O 있으면 증가 X 
			Object o = request.getSession().getAttribute(type + ip + bno);
			if ( o == null ) { // 세션 [ bno+type+ip ] 생성한 적 X
				
				// 2. 세션 생성 [ bno + type + ip ] 
				request.getSession().setAttribute( type + ip + bno , 1);
				// 3. 세션 생명주기 [ 생명주기 지나면 자동으로 메모리 삭제 ] 초 단위
					// 세션 초기화 되는 기준
						// 1. 서버 끌때 2. 서버 다시 켰을 때 3. setMaxInactiveInterval 
						// 4. .invalidate() 5. setAttribute ( "세션명" , null )
				request.getSession().setMaxInactiveInterval( 60*60*24 ); // 하루
				
				// dao 직접 전달
				BoardDao.getInstance().bIncrease( type , bno );
				
			} 
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

	}

}
