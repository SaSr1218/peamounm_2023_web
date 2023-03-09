package controller.member;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.dao.MemberDao;


@WebServlet("/login")
public class Login extends HttpServlet {
	private static final long serialVersionUID = 1L;

    public Login() { super();}

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String mid = request.getParameter("mid");
		String mpwd = request.getParameter("mpwd");
		
		boolean result = MemberDao.getInstance().login( mid , mpwd );
		
		if ( result == true ) {
			// 로그인 성공했으면 로그인 세션 만들기
				// request.getSession() : 서버[톰캣] 내 세션 객체 호출
				// setAttribute( "key" , value ); : 서버[톰캣] 내 세션객체에 속성[데이터] 추가
			request.getSession().setAttribute("login", mid); // 세션에 login 이름으로 로그인 성공한 mid 데이터 저장

		}
		
		response.getWriter().print(result);
		
	}

}
