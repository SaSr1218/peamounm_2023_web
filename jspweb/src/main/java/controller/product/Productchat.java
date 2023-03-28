package controller.product;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.fasterxml.jackson.databind.ObjectMapper;

import model.dao.MemberDao;
import model.dao.ProductDao;
import model.dto.ChatDto;

/**
 * Servlet implementation class Productchat
 */
@WebServlet("/product/Chat")
public class Productchat extends HttpServlet {
	private static final long serialVersionUID = 1L;

    public Productchat() {
        super();
        // TODO Auto-generated constructor stub
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		int pno = Integer.parseInt(request.getParameter("pno") );
		int mno = MemberDao.getInstance().getMno((String)request.getSession().getAttribute("login") );
		
		ArrayList<ChatDto> result = ProductDao.getInstance().getChatList(pno, mno);
		ObjectMapper mapper = new ObjectMapper();
		String jsonArray = mapper.writeValueAsString(result);
		
		response.setCharacterEncoding("UTF-8");
		response.setContentType("application/json");
		response.getWriter().print(jsonArray);
		
		
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		request.setCharacterEncoding("UTF-8");
		
		String ncontent = request.getParameter("ncontent");
		int pno = Integer.parseInt(request.getParameter("pno") );
		int mno = MemberDao.getInstance().getMno((String)request.getSession().getAttribute("login") );		
		int tomno = Integer.parseInt(request.getParameter("tomno") ) ;
			
		ChatDto dto = new ChatDto(0, ncontent, null, pno, mno, tomno);
				
		System.out.println("dto : " + dto);
		
		boolean result = ProductDao.getInstance().setChat(dto);
		response.getWriter().print(result);
		
	}
	
	

}
