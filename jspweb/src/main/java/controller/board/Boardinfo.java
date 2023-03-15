package controller.board;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.oreilly.servlet.MultipartRequest;
import com.oreilly.servlet.multipart.DefaultFileRenamePolicy;

import model.dao.BoardDao;
import model.dao.MemberDao;
import model.dto.BoardDto;

@WebServlet("/board/info")
public class Boardinfo extends HttpServlet {
	private static final long serialVersionUID = 1L;

    public Boardinfo() { super(); }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		// 업로드
		// 1. 업로드할 파일의 저장 위치[경로]
			// 클라이언트 [유저] ----- x -----> git [ 내 프로젝트 ]
			//               ----- o -----> 서버 [ 배포된 프로젝트 ]
		// 2. 경로 찾기
		String path = request.getSession().getServletContext().getRealPath("/board/bfile");
		// 3. 파일 복사
		MultipartRequest multi = new MultipartRequest(
				request,
				path ,
				1024 * 1024 * 10 ,
				"UTF-8" ,
				new DefaultFileRenamePolicy() );
		
		// 값 넘겨받기
		int cno = Integer.parseInt(multi.getParameter("cno") ) ;
		String btitle = multi.getParameter("btitle");
		String bcontent = multi.getParameter("bcontent");
		String bfile = multi.getFilesystemName("bfile");
		
			// 1. 회원게시판 [ 로그인된 회원의 mno 필요 ]
			String mid = (String)request.getSession().getAttribute("login");
			// 2. mid ---> mno ( MemberDao )
			int mno = MemberDao.getInstance().getMno(mid);
			// 3. 유효성검사 ( 로그인하지 않았으면 글쓰기 불가능 )
			if ( mno > 0 ) {
				response.getWriter().print("false");
			}
		// Dto
		BoardDto dto = new BoardDto(btitle, bcontent, bfile, mno, cno);
		// Dao
		boolean result = BoardDao.getInstance().bwrite(dto);
		// 응답
		response.getWriter().print(result);
		
		
	}

	protected void doPut(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

	}

	protected void doDelete(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

	}

}
