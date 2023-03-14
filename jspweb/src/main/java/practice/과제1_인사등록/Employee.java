package practice.과제1_인사등록;

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

@WebServlet("/employee")
public class Employee extends HttpServlet {
	private static final long serialVersionUID = 1L;

    public Employee() {}


	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		ArrayList<EmployeeDto> result = EmployeeDao.getInstance().getEmployeeList();
		ObjectMapper mapper = new ObjectMapper();
		String jsonArray = mapper.writeValueAsString(result);
		response.setCharacterEncoding("UTF-8");
		response.setContentType("application/json");
		response.getWriter().print(jsonArray);
		
	}


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// 현재 서버에 배포된 프로젝트 내 폴더 경로
		String uploadpath = request.getSession().getServletContext().getRealPath("/member/eimg");
		
		// 업로드
		MultipartRequest multi = new MultipartRequest(
				request,
				uploadpath,
				1024*1024*10 ,
				"UTF-8" ,
				new DefaultFileRenamePolicy() );
		// 그외 매개변수
		String empImg = multi.getFilesystemName("empImg");
		String empName = multi.getParameter("empName");
		String empGrade = multi.getParameter("empGrade");
		String empConstruct = multi.getParameter("empConstruct");
		String empDepart = multi.getParameter("empDepart");
		String empSdate = multi.getParameter("empSdate");
		String empLdate = multi.getParameter("empLdate");
		String empLcomment = multi.getParameter("empLcomment");
		
		EmployeeDto dto = new EmployeeDto(0, empImg, empName, empGrade, empConstruct, empDepart, empSdate, empLdate, empLcomment);
		
		boolean result = EmployeeDao.getInstance().signup(dto);
		response.getWriter().print(result);
		
	}

}
