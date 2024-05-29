package login.controller;

import common.controller.AbstractController;
import domain.MemberVO;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

public class Logout extends AbstractController {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		HttpSession session = request.getSession();
		String referer = request.getHeader("Referer");
//		System.out.println("referer:" + referer);
		session.setAttribute("goBackURL", referer);

		String goBackURL = (String)session.getAttribute("goBackURL");
		
		MemberVO loginuser = (MemberVO)session.getAttribute("loginuser");
		// Null 체크 추가
        if (loginuser != null) {
            String login_id = loginuser.getId();

            // 첫번째 방법 : 세션을 그대로 존재하게끔 해두고, 세션에 저장되어진 어떤 값(지금은 로그인 되어진 회원객체)을 삭제하기
            // session.removeAttribute("loginUser");

            // 두번째 방법 : WAS 메모리 상에서 세션을 아예 삭제해버리기
            // 세션의 모든 데이터 삭제
            session.invalidate();

            super.setRedirect(true);

            if (goBackURL != null && !"admin".equals(login_id)) {
                super.setViewPage(goBackURL);
            } else {
                super.setViewPage(request.getContextPath() + "/index.dk");
            }
        } else {
            // loginuser가 null일 경우 세션 무효화 및 기본 페이지로 이동
            session.invalidate();
            super.setRedirect(true);
            super.setViewPage(request.getContextPath() + "/index.dk");
        }
    }
}