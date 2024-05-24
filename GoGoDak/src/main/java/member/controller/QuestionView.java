package member.controller;

import common.controller.AbstractController;
import domain.QuestionVO;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import member.model.MemberDao;
import member.model.MemberDao_Imple;

public class QuestionView extends AbstractController {

	private MemberDao mdao = null;

	public QuestionView() {
		mdao = new MemberDao_Imple();
	}

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws Exception {

		

		String question_seq = request.getParameter("question_seq");

		String goBackURL = request.getParameter("goBackURL"); // 뷰단 인풋태그에 담은 URL 을 담아온다
		System.out.println("goBackURL : " + goBackURL);
		// 돌아갈페이지

		QuestionVO qvo = mdao.selectOneQuestion(question_seq);

		request.setAttribute("qvo", qvo);
		request.setAttribute("goBackURL", goBackURL); // 돌아갈 페이지를 뷰단페이지에 넘겨준다 => 뷰단간다

		super.setRedirect(false);
		super.setViewPage("/WEB-INF/view/member/member_questionView.jsp");

	}

}
