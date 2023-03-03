package practice.day02;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.fasterxml.jackson.databind.ObjectMapper;

/**
 * Servlet implementation class Q2
 */
@WebServlet("/Q2")
public class Q2 extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Q2() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	
		// 1.
		ArrayList<Dto2> list2 = Dao.getInstance().getData_과제2();
		
		// 2.
		ObjectMapper objectMapper = new ObjectMapper();
		String jsonArray = objectMapper.writeValueAsString(list2);
		
		response.setCharacterEncoding("UTF-8");
		response.setContentType("application/json");
		response.getWriter().print(jsonArray);
	
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// 1.
		request.setCharacterEncoding("UTF-8");
		
		// 2.
		String ex2_1 = request.getParameter("ex2_1");
		String ex2_2 = request.getParameter("ex2_2");
		double ex2_3 = Double.parseDouble(request.getParameter("ex2_3")) ;
		int ex2_4 = Integer.parseInt(request.getParameter("ex2_4")) ;
		String ex2_5 = request.getParameter("ex2_5");
		String ex2_6 = request.getParameter("ex2_6");
		boolean ex2_7 = Boolean.parseBoolean(request.getParameter("ex2_7")) ;
		String ex2_8 = request.getParameter("ex2_8");
		String ex2_9 = request.getParameter("ex2_9");
		
		Dto2 dto2 = new Dto2(ex2_1, ex2_2, ex2_3, ex2_4, ex2_5, ex2_6, ex2_7, ex2_8, ex2_9);
		boolean result2 = Dao.getInstance().setData_과제2(dto2);
		
		response.setCharacterEncoding("UTF-8");
		response.getWriter().print(result2);

	
	
	
	
	
	
	
	
	
	
	
	
	}

	
}
