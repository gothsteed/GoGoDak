package member.controller;

import common.controller.AbstractController;
import domain.MemberVO;
import domain.QuestionVO;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import member.model.MemberDao;
import member.model.MemberDao_Imple;

public class QuestionWrite extends AbstractController {

	private MemberDao mdao = null;

	public QuestionWrite() {
		mdao = new MemberDao_Imple();
	}

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws Exception {

		HttpSession session = request.getSession();
		MemberVO loginuser = (MemberVO) session.getAttribute("loginuser");

		String method = request.getMethod();
		System.out.println("method 퀘스쳔 " + method);

		if ("POST".equalsIgnoreCase(method)) {

			int member_seq = loginuser.getMember_seq();
			String email = loginuser.getEmail();
			String title = request.getParameter("title");
			String content = request.getParameter("content");
			String id = loginuser.getId();

			System.out.println("Title: " + title);
			System.out.println("Content: " + content);
			System.out.println("email: " + email);
			System.out.println("member_seq: " + member_seq);
			System.out.println("id: " + id);

			QuestionVO question = new QuestionVO();
			question.setFk_member_seq(member_seq);
			question.setEmail(email);
			question.setTitle(title);
			question.setContent(content);
			question.setId(id);

			int result = mdao.questionWrite(question);

			if (result == 1) {
				// System.out.println("DB Insert 성공");

				String message = "문의사항이 등록 되었습니다.";
				String loc = request.getContextPath() + "/member/question.dk";

				request.setAttribute("message", message);
				request.setAttribute("loc", loc);

				super.setViewPage("/WEB-INF/view/msg.jsp");

			}

		} else { // System.out.println("GET 요청 처리"); super.setRedirect(false);
			super.setRedirect(false);
			super.setViewPage("/WEB-INF/view/member/member_questionWrite.jsp");
		}

	}

}
