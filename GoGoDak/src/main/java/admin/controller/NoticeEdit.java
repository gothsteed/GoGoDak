package admin.controller;

import common.controller.AbstractController;
import domain.MemberVO;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

public class NoticeEdit extends AbstractController {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		if(super.checkLogin(request)) {
			 String userid = request.getParameter("id");
			 
	         HttpSession session = request.getSession();
	         MemberVO loginuser = (MemberVO) session.getAttribute("loginuser"); 
	         
	         if(loginuser.getId().equalsIgnoreCase("admin")) {
	            super.setRedirect(false);
	            super.setViewPage("/WEB-INF/view/admin/admin_boardEdit.jsp");
	         }
	         else {
	            // 로그인한 사용자가 다른 사용자의 정보를 수정하려고 시도하는 경우 
	            String message = "관리자 외 접근 불가능 합니다.1";
	            String loc = "javascript:history.back()";
	            
	            request.setAttribute("message", message);
	            request.setAttribute("loc", loc);
	            
	            super.setRedirect(false);
	            super.setViewPage("/WEB-INF/view/msg.jsp");
	         }
	         
	      
		}
	      else {
	         // 로그인을 안했으면 
	         String message = "관리자 외 접근 불가능 합니다.";
	         String loc = "javascript:history.back()";
	         
	         request.setAttribute("message", message);
	         request.setAttribute("loc", loc);
	         
	      //   super.setRedirect(false);
	         super.setViewPage("/WEB-INF/view/msg.jsp");
	      }
	      

		
		

	}

}