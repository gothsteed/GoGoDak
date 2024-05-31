package member.controller;

import org.json.JSONObject;

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
	
	private void sendMsg(HttpServletRequest request, boolean isSuccess, String message) {
        System.out.println(message);
        JSONObject jsonResponse = new JSONObject();
        jsonResponse.put("success", isSuccess);
        jsonResponse.put("message",message);

        System.out.println(jsonResponse.toString());
        setRedirect(false);
        request.setAttribute("json", jsonResponse.toString());
        setViewPage("/WEB-INF/jsonview.jsp");
        return;
	}
	
	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		HttpSession session = request.getSession();
        MemberVO loginuser = (MemberVO)session.getAttribute("loginuser");
        
        String message = "";
        
        if(loginuser != null && !"admin".equals(loginuser.getId())) {
            
        	String review_seq = request.getParameter("review_seq");
        	
        	int result = mdao.reviewDelete(review_seq);
        	
        	if(result == 1) {
        		message = "리뷰가 삭제되었습니다.";
        		boolean isSuccess = true;
        		
        		sendMsg(request, isSuccess, message);
        	}
        	else {
        		message = "리뷰 삭제가 실패되었습니다.";
        		boolean isSuccess = false;
        		sendMsg(request, isSuccess, message);
        	}
        }
        else {
        	message = "리뷰 삭제를 하기 위해서는 먼저 로그인 하셔야 합니다.";
        	boolean isSuccess = false;
        	sendMsg(request, isSuccess, message);
        }
         
	}

}
