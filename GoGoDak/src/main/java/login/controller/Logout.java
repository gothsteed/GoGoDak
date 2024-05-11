package login.controller;

import common.controller.AbstractController;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

public class Logout extends AbstractController {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		HttpSession session = request.getSession();

		// 첫번째 방법 : 세션을 그대로 존재하게끔 해두고, 세션에 저장되어진 어떤 값(지금은 로그인 되어진 회원객체)을 삭제하기
		// session.removeAttribute("loginUser");

		// 두번째 방법 : WAS 메모리 상에서 세션을 아예 삭제해버리기
		// 세션의 모든 데이터 삭제
		session.invalidate();
		
		
		

		super.setRedirect(true);
		super.setViewPage(request.getContextPath() + "/index.up");

	}

}
