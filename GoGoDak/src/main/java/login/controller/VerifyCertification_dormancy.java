package login.controller;

import common.controller.AbstractController;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import member.model.MemberDao;
import member.model.MemberDao_Imple;

public class VerifyCertification_dormancy extends AbstractController {
	
	private MemberDao mdao = null;

	public VerifyCertification_dormancy() {
		mdao = new MemberDao_Imple();
	}
	
	
	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		String method = request.getMethod();

		if (method.equalsIgnoreCase("POST")) {
			String user_certification_code = request.getParameter("userCertificationCode");

			HttpSession session = request.getSession();
			String certification_code = (String) session.getAttribute("certification_code");

			String message = "";
			String loc = "";
			String id = request.getParameter("id");
			
			if (user_certification_code.equals(certification_code)) {
				
				message = "인증이 성공되었습니다!!";
				loc = request.getContextPath() + "/login/dormancyEnd.dk?id=" + id;
				
			} else {
				message = "발급된 인증코드가 아닙니다.\\n인증코드를 다시 발급받으세요!!";
				loc = request.getContextPath() + "/login/dormancy.dk";
			}

			request.setAttribute("message", message);
			request.setAttribute("loc", loc);

			super.setViewPage("/WEB-INF/view/msg.jsp");
			// !!!! 중요 !!!! //
			// !!!! 세션에 저장된 인증코드 삭제하기 !!!! //
			session.removeAttribute("certification_code");
		}

	}

}
