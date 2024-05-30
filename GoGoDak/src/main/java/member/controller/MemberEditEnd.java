package member.controller;

import java.sql.SQLException;

import common.controller.AbstractController;
import domain.MemberVO;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import member.model.MemberDao;
import member.model.MemberDao_Imple;

public class MemberEditEnd extends AbstractController {

	private MemberDao mdao = null;

	public MemberEditEnd() {
		mdao = new MemberDao_Imple();
	}

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
	String method = request.getMethod(); // "GET" 또는 "POST" 
		System.out.println("editend 컨트롤러");
		if("POST".equalsIgnoreCase(method)) {
           // **** POST 방식으로 넘어온 것이라면 **** //
		
		   String id = request.getParameter("id");	
		   String name = request.getParameter("name");
		   String password = request.getParameter("password");
		   String email = request.getParameter("email");
    	   String hp1 = request.getParameter("hp1");
		   String hp2 = request.getParameter("hp2");
		   String hp3 = request.getParameter("hp3");
 		   String postcode = request.getParameter("postcode");
		   String address = request.getParameter("address");
		   String address_detail = request.getParameter("address_detail");
		   String address_extra = request.getParameter("address_extra");
			
		   String tel = hp1 + hp2 + hp3;
			
		   MemberVO member = new MemberVO();
		   member.setId(id);
		   member.setPassword(password);
		   member.setName(name);
		   member.setEmail(email);		   
		   member.setTel(tel);
		   member.setPostcode(postcode);
		   member.setAddress(address);
		   member.setAddress_detail(address_detail);
		   member.setAddress_extra(address_extra);
			
		   // === 회원수정이 성공되어지면 "회원정보 수정 성공!!" 이라는 alert 를 띄우고 시작페이지로 이동한다. === //
		   String message = "";
		   String loc = "";
			
			try {
				int n = mdao.updateMember(member); 
				
				if(n==1) {
					
					// !!!! session 에 저장된 loginuser 를 변경된 사용자의 정보값으로 변경해주어야 한다. !!!!
					HttpSession session = request.getSession();
					MemberVO loginuser = (MemberVO) session.getAttribute("loginuser");
					
					loginuser.setName(name);
					loginuser.setPassword(password);
					loginuser.setEmail(email);
					loginuser.setTel(tel);
					loginuser.setPostcode(postcode);
					loginuser.setAddress(address);
					loginuser.setAddress_detail(address_detail);
					loginuser.setAddress_extra(address_extra);
					
					message = "회원정보 수정 성공!!";
					loc = request.getContextPath() + "/index.dk"; // 시작페이지로 이동한다.
				}
			} catch(SQLException e) {
				message = "SQL구문 에러발생";
				loc = "javascript:history.back()"; // 자바스크립트를 이용한 이전페이지로 이동하는 것. 
				e.printStackTrace();
			}
			System.out.println("여기까지옴");
			request.setAttribute("message", message);
			request.setAttribute("loc", loc);
			
			
			
			super.setRedirect(false); 
			super.setViewPage("/WEB-INF/view/msg.jsp");
		   
		   
		}	 
	}

}
