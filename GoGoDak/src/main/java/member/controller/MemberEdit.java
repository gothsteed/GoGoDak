package member.controller;

import common.controller.AbstractController;
import domain.MemberVO;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

public class MemberEdit extends AbstractController {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws Exception {

		// 내정보(회원정보)를 수정하기 위한 전제조건은 먼저 로그인을 해야 하는 것이다.
		if (super.checkLogin(request)) {
			// 로그인을 했으면

			HttpSession session = request.getSession();
			MemberVO loginuser = (MemberVO) session.getAttribute("loginuser");
			System.out.println("loginuser : " + loginuser.getId());


			super.setRedirect(false);
			super.setViewPage("/WEB-INF/view/member/member_Edit.jsp");
		}

		else {
			// 로그인을 안했으면
			String message = "회원정보를 수정 하기 위해서는 먼저 로그인을 하세요!!";
			String loc = "javascript:history.back()";

			request.setAttribute("message", message);
			request.setAttribute("loc", loc);

			super.setRedirect(false);
			super.setViewPage("/WEB-INF/view/msg.jsp");
		}

	}
}
