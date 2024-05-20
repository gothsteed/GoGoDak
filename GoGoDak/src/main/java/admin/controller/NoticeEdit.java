package admin.controller;

import admin.model.AdminDAO;
import admin.model.AdminDAO_imple;
import common.controller.AbstractController;
import domain.BoardVO;
import domain.MemberVO;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

public class NoticeEdit extends AbstractController {
	
	
	private AdminDAO adao = null;

	public NoticeEdit() {
		adao = new AdminDAO_imple();
	}
	
	
	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		if(super.checkLogin(request)) {
			 String id = request.getParameter("id");
			 
	         HttpSession session = request.getSession();
	         MemberVO loginuser = (MemberVO) session.getAttribute("loginuser"); 
	         
	         if(loginuser.getId().equalsIgnoreCase("admin")) {
	        	
	        	int board_seq = Integer.parseInt(request.getParameter("board_seq"));
	        	
	        	System.out.println("board_seq:" + board_seq);
	        	
	        	int board_select = adao.boardSelectBySeq(board_seq);
	        	BoardVO board_seq1 = new BoardVO();
	        	board_seq1.setBoard_seq(board_select);
	        	
	        	System.out.println("test"+board_select);
	        	
	        	
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
