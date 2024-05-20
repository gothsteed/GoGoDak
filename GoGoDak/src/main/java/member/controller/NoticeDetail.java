package member.controller;

import common.controller.AbstractController;
import domain.BoardVO;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import member.model.MemberDao;
import member.model.MemberDao_Imple;

public class NoticeDetail extends AbstractController {

	private MemberDao mdao = null;

	public NoticeDetail() {
		mdao = new MemberDao_Imple();
	}

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws Exception {

		// === 모든 사람이 조회가 가능하도록 한다. === //

		String method = request.getMethod();

		String board_seq = request.getParameter("board_seq");

		String goBackURL = request.getParameter("goBackURL"); // 뷰단 인풋태그에 담은 URL 을 담아온다
		//System.out.println("goBackURL : " + goBackURL);
		// 돌아갈페이지
		// goBackURL :
		// /member/memberList.up?searchType=name&searchWord=%EC%9C%A0&sizePerPage=5&currentShowPageNo=10

		BoardVO bvo = mdao.selectOneBoard(board_seq);

		request.setAttribute("bvo", bvo);
		request.setAttribute("goBackURL", goBackURL); // 돌아갈 페이지를 뷰단페이지에 넘겨준다 => 뷰단간다

		super.setRedirect(false);
		super.setViewPage("/WEB-INF/view/member/member_bordView.jsp");

	}

}
