package member.controller;

import common.controller.AbstractController;
import domain.MemberVO;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import member.model.MemberDao;
import member.model.MemberDao_Imple;

public class ReviewDelete extends AbstractController {

	private MemberDao mdao = null;

	public ReviewDelete() {
		mdao = new MemberDao_Imple();
	}
	
	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		HttpSession session = request.getSession();
        MemberVO loginuser = (MemberVO)session.getAttribute("loginuser");
        
        String message = "";
		String loc = "";
        
        if(loginuser != null && !"admin".equals(loginuser.getId())) {
            
        	String review_seq = request.getParameter("review_seq");
        	
        	int result = mdao.reviewDelete(review_seq);
        	
        	if(result == 1) {
        		message = "리뷰가 삭제되었습니다.";
        		loc = request.getContextPath() + "/index.dk";
        	}
        	else {
        		message = "리뷰 삭제가 실패되었습니다.";
                loc = "javascript:history.back()";
        	}
        }
        else {
        	message = "리뷰 삭제를 하기 위해서는 먼저 로그인 하셔야 합니다.";
            loc = "javascript:history.back()";
        }
        
        request.setAttribute("message", message);
        request.setAttribute("loc", loc);

        super.setRedirect(false);
        super.setViewPage("/WEB-INF/view/msg.jsp");    
	}

}
