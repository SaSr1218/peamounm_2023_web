package controller.board;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.oreilly.servlet.MultipartRequest;
import com.oreilly.servlet.multipart.DefaultFileRenamePolicy;

import model.dao.BoardDao;
import model.dao.MemberDao;
import model.dto.BoardDto;
import model.dto.PageDto;

@WebServlet("/board/info")
public class Boardinfo extends HttpServlet {
	private static final long serialVersionUID = 1L;

    public Boardinfo() { super(); }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		int type = Integer.parseInt(request.getParameter("type") ); 
		if ( type == 1 ) { // 전체 출력
			
			// --------------- 카테고리 별 출력 ---------- //
			// 1. 카테고리 매개변수 요청 [ cno ] 2. gettotalsize() / getBoardList() 조건 전달
			int cno = Integer.parseInt(request.getParameter("cno") );
			
			
			// --------------- 검색 처리 ---------------- //
			// 1. 검색에 필요한 매개변수 요청 [ key , keyword ] , 2. gettotalsize/getBoardList 조건 전달
			String key = request.getParameter("key");	
			String keyword = request.getParameter("keyword");	
		
			
			// --------------- 페이징 처리 --------------- //
			// 1. 현재페이지[ 요청 ] , 2. 현재페이지 [ 게시물시작 , 게시물끝 ]
			int page = Integer.parseInt(request.getParameter("page") );
			// int listsize = 3; // 게시물 개수 체크 만들기 전
			int listsize = Integer.parseInt(request.getParameter("listsize") );
			int startrow = (page-1)*listsize;// 해당 페이지에서의 게시물 시작번호
			
			// -------------- 페이징 버튼 만들기 ----------- //
			// 1. 전체페이지수 [ 총게시물레코드수/페이지당 표시수 ] , 2. 페이지 표시할 최대 버튼수 , 3. 시작버튼 번호/마지막번호 버튼
				// 1. 검색이 없을때
			// int totalsize = BoardDao.getInstance().gettotalsize();
				// 2. 검색이 없을때
			int totalsize = BoardDao.getInstance().gettotalsize( key , keyword , cno);
			
			int totalpage = totalsize % listsize == 0 ? totalsize/listsize : totalsize/listsize+1;
			
			int btnsize = 5; // 최대 페이징 버튼 출력수
			int startbtn = ( (page-1) / btnsize ) * btnsize +1; // 시작
				/*
				 	1페이지 : 1-1 / 5 	*5 +1		-> 0*5+1	1
				 	2페이지 : 2-1 / 5 	*5 +1		-> 0*5+1	1
				 	3페이지 : 3-1 / 5 	*5 +1		-> 0*5+1	1
				 	4페이지 : 4-1 / 5 	*5 +1		-> 0*5+1	1
				 	5페이지 : 5-1 / 5 	*5 +1		-> 0*5+1	1
				 	6페이지 : 6-1 / 5 	*5 1+1		-> 1*5+1	6
				 	7페이지 : 7-1 / 5 	*5 1+1		-> 1*5+1	6
				 */
			int endbtn = startbtn + (btnsize -1);
			// * 마지막 버튼수가 총페이지 수보다는 커지지 못하게 막기 [ 마지막 페이지는 총 페이지수로 대체 ]
			if ( endbtn > totalpage ) endbtn = totalpage;
			
				// 1. 검색이 없을때
			// ArrayList<BoardDto> result = BoardDao.getInstance().getBoardList( startrow , listsize );
				// 2. 검색이 있을때
			ArrayList<BoardDto> result = BoardDao.getInstance().getBoardList(startrow, listsize , key , keyword , cno );
					
					/*
					  	총 게시물수 = 10 	, 페이지당 표시할 게시물수 = 3 
					  	총 레코드수 = 10	, 총 레코드의 인덱스 : 0~9
					  	1. 총 페이지수 = 012 , 345 , 678 , 9 ... --> 4
					  	
					  		총 레코드수/페이지당표시게시물수
					  			1. 나머지가 없으면 => 몫		9/3 -> 3페이지
					  			2. 나머지가 있으면 => 몫+1 	8/3	-> 2페이지 + 1
					  		
					  	2. 페이지별 게시물시작 번호 찾기
					  			1페이지 요청 -> (1-1)*3 => 0
					  			2페이지 요청 -> (2-1)*3 => 3
					  			3페이지 요청 -> (3-1)*3 => 6
					  	3. 시작버튼 , 마지막버튼 수
					  	ex -> 7페이지 btnsize = 5
					  					시작번호패턴 : 1 6 11 16
					  	1페이지 -> 12345
					  	2페이지 -> 12345
					  	3페이지 -> 12345
					  	4페이지 -> 12345
					  	5페이지 -> 12345	
					  	6페이지 -> 678910 -> 67
					  	7페이지 -> 678910 -> 67	
					  	
					  	ex -> 7페이지 btnsize = 3 
					  					시작번호패턴 : 1 4 7 10
					  	1페이지 -> 123
					  	2페이지 -> 123
					  	3페이지 -> 123
					  	4페이지 -> 456
					  	5페이지 -> 456	
					  	6페이지 -> 456
					  	7페이지 -> 7
					  			
					 */
			// 페이지 dto 만들기
			PageDto pageDto = new PageDto(page, listsize, startrow, totalsize, totalpage, btnsize, startbtn, endbtn, result);  
			
			// java --> js 형식 변환
			ObjectMapper mapper = new ObjectMapper();
			String jsonArray = mapper.writeValueAsString(pageDto);
			// 응답
			response.setCharacterEncoding("UTF-8");
			response.setContentType("application/json");
			response.getWriter().print(jsonArray);
			
		}else if ( type == 2) { // 2. 개별 출력
			int bno = Integer.parseInt(request.getParameter("bno") );
			// dao 처리
			BoardDto result = BoardDao.getInstance().getBoard(bno);
			
			// 형변환 처리
			ObjectMapper mapper = new ObjectMapper();
			String json = mapper.writeValueAsString(result);
			// 응답 처리
			response.setCharacterEncoding("UTF-8");
			response.setContentType("application/json");
			response.getWriter().print(json);
		}
		
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
			if ( mno < 1 ) {
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
