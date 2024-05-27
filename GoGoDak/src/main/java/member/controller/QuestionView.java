package member.controller;

import admin.model.AdminDAO;
import admin.model.AdminDAO_imple;
import common.controller.AbstractController;
import domain.AnswerVO;
import domain.QuestionVO;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import member.model.MemberDao;
import member.model.MemberDao_Imple;
public class QuestionView extends AbstractController {

	private MemberDao mdao = null;
	private AdminDAO adao = null;

	public QuestionView() {
		mdao = new MemberDao_Imple();
		adao = new AdminDAO_imple();
	}

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws Exception {



		
		String question_seq = request.getParameter("question_seq");

		
		String goBackURL = request.getParameter("goBackURL"); // 뷰단 인풋태그에 담은 URL 을 담아온다
		System.out.println("goBackURL : " + goBackURL);
		// 돌아갈페이지

		QuestionVO qvo = mdao.selectOneQuestion(question_seq);


		
		AnswerVO avo = adao.selectAnswer(question_seq); //05-26추가


		request.setAttribute("avo", avo);//05-26추가
		request.setAttribute("qvo", qvo);
		request.setAttribute("goBackURL", goBackURL); // 돌아갈 페이지를 뷰단페이지에 넘겨준다 => 뷰단간다

		super.setRedirect(false);
		super.setViewPage("/WEB-INF/view/member/member_questionView.jsp");
	}
}