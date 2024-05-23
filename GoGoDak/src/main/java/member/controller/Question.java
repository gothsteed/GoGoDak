package member.controller;

import java.util.List;

import common.controller.AbstractController;
import domain.MemberVO;
import domain.QuestionVO;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import member.model.MemberDao;
import member.model.MemberDao_Imple;

public class Question extends AbstractController {

	private MemberDao mdao = null;

	public Question() {
		mdao = new MemberDao_Imple();
	}

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws Exception {

		HttpSession session = request.getSession();
		if (super.checkLogin(request)) {
			MemberVO loginuser = (MemberVO) session.getAttribute("loginuser");
			String id = loginuser.getId();
			System.out.println("id" + id);

			String question = request.getParameter("question_seq");

			int blockSize = 8; // 한번에 보여주는 페이지 수
			int currentPage;

			try {
				currentPage = Integer.parseInt(request.getParameter("page"));
			} catch (NumberFormatException e) {
				currentPage = 1;
			}

			int totalPageNum = mdao.getQuestionTotalPage(blockSize); // 전체페이지수 계산
			if (currentPage > totalPageNum) {
				currentPage = 1;
			}

			List<QuestionVO> questionList = mdao.getQuestionBoard(currentPage, blockSize);

			// pageBar

			int loop = 1;
			int pageNo = ((currentPage - 1) / blockSize) * blockSize + 1;

			String pageBar = "<li class='page-item'><a class='page-link' href='notice.dk?seq=" + question
					+ "&page=1'>[맨처음]</a></li>";

			if (pageNo != 1) {
				pageBar += "<li class='page-item'><a class='page-link' href='notice.dk?type=" + question + "&page="
						+ (pageNo - 1) + "'>[이전]</a></li>";
			}

			// 맨처음 맨마지막 만들기

			while (!(loop > blockSize || pageNo > totalPageNum)) {

				// 1 2 3 4 5 6 7 8 9 10
				// pageBar += "<li class='page-item'><a class='page-link'
				// href='memberList.up?searchType="+searchType+"&searchWord="+searchWord+"&sizePerPage="+sizePerPage+"&currentShowPageNo="+pageNo+"'>"+pageNo+"</a></li>";

				if (pageNo == currentPage) {

					pageBar += "<li class='page-item active'><a class='page-link' href='#'>" + pageNo + "</a></li>";
				} else {

					pageBar += "<li class='page-item'><a class='page-link' href='notice.dk?type=" + question + "&page="
							+ pageNo + "'>" + pageNo + "</a></li>";
				}

				loop++;

				// 1 2 3 4 5 6 7 8 9 10
				// 11 12 13 14 15 16 17 18 19 20
				pageNo++;

			}
			pageBar += "<li class='page-item'><a class='page-link' href='notice.dk?type=" + question + "&page="
					+ (totalPageNum) + "'>[맨마지막]</a></li>";

			// 다음 마지막 만들기
			if (pageNo <= totalPageNum) {
				pageBar += "<li class='page-item'><a class='page-link' href='notice.dk?type=" + question + "&page="
						+ (currentPage + 1) + "'>[다음]</a></li>";
			}
			request.setAttribute("id", id);
			request.setAttribute("questionList", questionList);
			request.setAttribute("pageBar", pageBar);

			super.setRedirect(false);
			super.setViewPage("/WEB-INF/view/member/member_question.jsp");

		} else {
			super.setRedirect(false);
			super.setViewPage("/WEB-INF/view/member/member_Login.jsp");
		}
	}

}
