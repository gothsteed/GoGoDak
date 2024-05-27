package member.controller;

import java.sql.SQLException;

import common.controller.AbstractController;
import domain.QuestionVO;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import member.model.MemberDao;
import member.model.MemberDao_Imple;

public class QuestionDelete extends AbstractController {

	private MemberDao mdao = null;

	public QuestionDelete() {
		mdao = new MemberDao_Imple();
	}
	
	
	
	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		//05-26 추가
		String method = request.getMethod(); // "GET" 또는 "POST"

		if ("POST".equalsIgnoreCase(method)) {
			// **** POST 방식으로 넘어온 것이라면 **** //
			String question_seq = request.getParameter("question_seq");

			QuestionVO questionDelete = new QuestionVO();
			questionDelete.setQuestion_seq(Integer.parseInt(question_seq));

			String message = "";
			String loc = "";

			try {
				int question_Delete = mdao.questionDelete(questionDelete);
				if (question_Delete == 1) {
					message = "1:1 문의 삭제 완료";
					loc = request.getContextPath() + "/member/question.dk"; // 시작페이지로 이동한다.
				}
			} catch (SQLException e) {
				message = "SQL구문 에러발생";
				loc = "javascript:history.back()"; // 자바스크립트를 이용한 이전페이지로 이동하는 것.
				e.printStackTrace();
			}

			request.setAttribute("message", message);
			request.setAttribute("loc", loc);


			// super.setRedirect(false);
			super.setViewPage("/WEB-INF/view/msg.jsp");
		}
		
	 
		
		
	}

}
