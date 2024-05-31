package member.controller;

import common.controller.AbstractController;
import domain.MemberVO;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

public class MemberWithdrawal extends AbstractController {
	
	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		if(super.checkLogin(request)) {
			
			String id = request.getParameter("id");
         
			HttpSession session = request.getSession();
			MemberVO loginuser = (MemberVO) session.getAttribute("loginuser"); 
         
			if(loginuser.getId().equals(id)) {
            
				super.setRedirect(false);
				super.setViewPage("/WEB-INF/view/member/memberDelete.jsp");
			}
			else { 
				String message = "다른 사용자의 정보 변경은 불가합니다.!!";
				String loc = "javascript:history.back()";
            
				request.setAttribute("message", message);
				request.setAttribute("loc", loc);
            
				super.setRedirect(false);
				super.setViewPage("/WEB-INF/view/msg.jsp");
			}
         
		}
		else { 
			String message = "회원탈퇴를 하기 위해서는 먼저 로그인을 하세요!!";
			String loc = request.getContextPath() + "/login/login.dk";
         
			request.setAttribute("message", message);
			request.setAttribute("loc", loc);
         
			super.setRedirect(false);
			super.setViewPage("/WEB-INF/view/msg.jsp");
		}

	}

}
