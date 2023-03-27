package controller.product;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.oreilly.servlet.MultipartRequest;
import com.oreilly.servlet.multipart.DefaultFileRenamePolicy;

import model.dao.ProductDao;
import model.dto.ProductDto;

@WebServlet("/product/info")
public class Productinfo extends HttpServlet {
	private static final long serialVersionUID = 1L;

    public Productinfo() {super(); }

    ObjectMapper mapper = new ObjectMapper();
    
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		request.setCharacterEncoding("UTF-8");
		String 동 = request.getParameter("동");
		String 서 = request.getParameter("서");
		String 남 = request.getParameter("남");
		String 북 = request.getParameter("북");	
		
		
		ArrayList<ProductDto> result = ProductDao.getInstance().getProductList( 동 , 서 , 남 , 북 );
		
		String jsonArray = mapper.writeValueAsString(result);
		
		response.setCharacterEncoding("UTF-8");
		response.setContentType("application/json");
		response.getWriter().print(jsonArray);
		
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		// ------------------------ commons.jar 사용시 --------------- //
		
		request.setCharacterEncoding("UTF-8");
		// 1. 다운로드 할 서버 경로
		String 경로 = request.getSession().getServletContext().getRealPath("/product/pimg");
		// 2. 객체화 : 해당 경로의 파일/폴더 객체화 [ setRepository 에 대입하기 위해 ]
		File 저장경로객체 = new File(경로);
		
		// 3. 업로드할 저장소 객체 생성
		DiskFileItemFactory 파일저장소 = new DiskFileItemFactory();
		파일저장소.setRepository(저장경로객체);			// 파일저장소 위치 대입
		파일저장소.setSizeThreshold(1024*1024*10);		// 파일저장소에 저장할 수 있는 최대용량 범위
		
		// 4. 파일업로드객체
		ServletFileUpload 파일업로드객체 = new ServletFileUpload(파일저장소);
		
		try {
			// 5. 매개변수 요청해서 리스트에 담기 [ 무조건 예외처리 발생 ]
			List<FileItem> 파일아이템목록 = 파일업로드객체.parseRequest(request);
			// 6.
			for ( FileItem item : 파일아이템목록 ) { // 요청된 모든 매개변수들을 반복문 돌려서 확인
				System.out.println("필드명 : " + item.getFieldName() ); 		// 매개변수 명 확인
				System.out.println("필드내 첨부된 파일명 : " + item.getName() ); 	// 매개변수가 파일일경우 파일명 확인
			}
		}catch (Exception e) {System.out.println(e);}
			
		/*      ------------------- cos.jar 사용시 -------------------- //
  
		String path = request.getSession().getServletContext().getRealPath("/product/pimg");
		
		MultipartRequest multi = new MultipartRequest(
				request, path , 1024*1024*10 , "UTF-8" , new DefaultFileRenamePolicy() );
		
		String pname = multi.getParameter("pname");
		String pcomment = multi.getParameter("pcomment");
		int pprice = Integer.parseInt(multi.getParameter("pprice")) ;
		String plat = multi.getParameter("plat");
		String plng = multi.getParameter("plng");
		
		// 첨부파일 1개 이름 가져오기	multiple x
		String pfile = multi.getFilesystemName("pfile");
			System.out.println(pfile);
			
		// 첨부파일 여러개 가져오기1	multiple x
		String pfile1 = multi.getFilesystemName("pfile1");
		String pfile2 = multi.getFilesystemName("pfile2");
		String pfile3 = multi.getFilesystemName("pfile3");
		
		// 첨부파일 여러개 가져오기2(multiple) multiple o
			// multiple input 에 등록된 여러 사진들의 이름 가져오기 불가능 [ cos.jar 제공 x ]
			// 다른 라이브러리 사용 필요!(multiple 사용을 위해!)
		Enumeration pfiles = multi.getFileNames(); // input type이 file의 태그명 호출
			
		while ( pfiles.hasMoreElements() ) {
			// 해당 목록[pfiles]에 요소[element]가 존재하면 true / 아니면 false
			String s = (String) pfiles.nextElement();
				System.out.println("s : " + s);
		}

		
		ProductDto dto = new ProductDto(pname, pcomment, pprice, plat, plng);
		
		boolean result = ProductDao.getInstance().pwrite(dto);
				
		response.getWriter().print(result);
		 */
	}

}
