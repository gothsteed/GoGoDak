package login.controller;

import common.controller.AbstractController;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

public class VerifyCertification extends AbstractController {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		String method = request.getMethod();
		
		if(method.equalsIgnoreCase("Post")) {
			String user_certification_code = request.getParameter("userCertificationCode");
			
			HttpSession session = request.getSession();
			String certification_code = (String) session.getAttribute("certification_code");
			
			String message = "";
			String loc = "";
			String userid = request.getParameter("userid");
			
			System.out.println("~~~~~~~~~~~~ VerifyCertification 의 userid : " + userid);
			
			if(user_certification_code.equals(certification_code)) {
				
				message = "인증성공";
				loc = request.getContextPath() + "/login/pwdUpdateEnd.up?userid="+userid;	
			}
			else {
				message = "발급된 인증코드가 아닙니다.\\\\n인증코드를 다시 발급받으세요!!";
				loc = request.getContextPath() + "/login/pwdFind.up";	
			}
			
			request.setAttribute("message", message);
			request.setAttribute("loc", loc);
			
			super.setViewPage("/WEB-INF/msg.jsp");
			// !!!! 중요 !!!! //
	         // !!!! 세션에 저장된 인증코드 삭제하기 !!!! //
			session.removeAttribute("certification_code");
			
			
		}
		
	}

}