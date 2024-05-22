package admin.controller;

import common.controller.AbstractController;
import domain.MemberVO;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

public class Admin extends AbstractController{

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		HttpSession session = request.getSession();
		MemberVO loginuser = (MemberVO)session.getAttribute("loginuser");
		
		if(loginuser != null && "admin".equals(loginuser.getId())) {
			super.setRedirect(false);
			super.setViewPage("/WEB-INF/view/index_admin.jsp");
		}
		else {
			String message = "관리자만 접근이 가능합니다.";
	        String loc = "javascript:history.back()";
	         
	        request.setAttribute("message", message);
	        request.setAttribute("loc", loc);
	         
	        super.setRedirect(false);
	        super.setViewPage("/WEB-INF/msg.jsp");
	    }
		
	}

}
