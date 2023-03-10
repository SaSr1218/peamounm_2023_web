package controller.admin;

import java.io.IOException;
import java.util.Random;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.dto.MemberDto;


@WebServlet("/email")
public class Email extends HttpServlet {
	private static final long serialVersionUID = 1L;
       

    public Email() { super(); }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	
	}


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// 1. 받을 회원의 이메일 요청
		String memail = request.getParameter("memail");
		// 2. 인증코드 만들기
		String auth = "";
		
		for ( int i = 0 ; i<6 ; i++ ) { // 6자리 난수 생성
			Random random = new Random();
			auth += random.nextInt(10); // 0~9 사이의 정수 생성
		}		
		// 3. 인증코드를 받는사람의 이메일에게 보내기	
		boolean result = new MemberDto().sendEmail(memail, auth);	
		if ( result ) {
			response.getWriter().print(auth); // 4. 인증코드 보내기
		} else {
			response.getWriter().print(result); // 5. 메일 전송 실패시 false 보내기
		}

		
	}

}

/*
	- SMTP(간이 메일 전송 프로토콜) : 메일 서버에게 우편 전송
	- JAVA mail 라이브러리 추가 [ java-mail , java-activation ]
*/

/*
 	[1] 재호출 불가 X [1 -> 인스턴스 싱글톤이 아니라 new를 써야함]
 	new MemberDto().sendEmail(memail, auth);
  
  	[2] 재호출 가능 O [2 -> 인스턴스를 만들고 스택에 저장, 스택이 메소드 영역에 저장]
 	MemberDto dto = new MemberDto();
	dto.sendEmail(memail, auth);
			
			
 
 */


