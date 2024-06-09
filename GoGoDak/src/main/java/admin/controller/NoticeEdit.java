package admin.controller;

import admin.model.AdminDAO;
import admin.model.AdminDAO_imple;
import common.controller.AbstractController;
import conatainer.annotation.Autowired;
import domain.BoardVO;
import domain.MemberVO;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

public class NoticeEdit extends AbstractController {
	
	
	/*
	 * private AdminDAO adao = null;
	 * 
	 * @Autowired public NoticeEdit(AdminDAO adao) { this.adao = adao; }
	 */
	
	
	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		
			 
	         HttpSession session = request.getSession();
	         MemberVO loginuser = (MemberVO) session.getAttribute("loginuser"); 
	         
	         if(loginuser.getId().equalsIgnoreCase("admin")) {
	        	
	        	String board_seq = request.getParameter("board_seq");
	 			String title = request.getParameter("title");
	 			String content = request.getParameter("content");
	 			String pic = request.getParameter("pic");
	        	
	 			
	 			request.setAttribute("board_seq", board_seq);
	 			request.setAttribute("title", title);
	 			request.setAttribute("content", content);
	 			request.setAttribute("pic", pic);
	        	 
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

}
