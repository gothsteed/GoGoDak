package member.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

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

			//int blockSize = 8; // 한번에 보여주는 페이지 수
			//int currentPage;
			String blockSize = "8"; // 한번에 보여주는 행의 수
			String currentPage = request.getParameter("currentPage");
			String searchType1 = request.getParameter("searchType1");
			String searchType2 = request.getParameter("searchType2");
			String searchWord = request.getParameter("searchWord");

			try {
				currentPage = request.getParameter("page");
			} catch (NumberFormatException e) {
				currentPage = "1";
			}
			
			
			

			Map<String,String> paraMap = new HashMap<>();
			paraMap.put("searchType1",searchType1);
			paraMap.put("searchType2",searchType2);
			paraMap.put("searchWord",searchWord);
			
			paraMap.put("blockSize",blockSize); //한번에 보여주는 페이지 수
			paraMap.put("currentPage",currentPage);
			
			int totalPageNum = mdao.getQuestionTotalPage(paraMap); // 전체페이지수 계산
			
			try {
				if(Integer.parseInt(currentPage) > totalPageNum ||
				   Integer.parseInt(currentPage) <= 0	) {
					
					currentPage ="1";
					paraMap.put("currentPage",currentPage);

				}
			}catch (NumberFormatException e) { //장난쳐 온다면 
				currentPage ="1";
				paraMap.put("currentPage",currentPage);
			}
			
			
			
			
			
			
			// pageBar

			int loop = 1;
			int blockpage = 10;
			int pageNo  = ( (Integer.parseInt(currentPage) -1)/blockpage ) * blockpage + 1 ;

			String pageBar = "<li class='page-item'><a class='page-link' href='question.dk?seq=page=1'>[맨처음]</a></li>";

			if (pageNo != 1) {
				pageBar += "<li class='page-item'><a class='page-link' href='question.dk?page="
						+ (pageNo - 1) + "'>[이전]</a></li>";
			}

			// 맨처음 맨마지막 만들기

			while (!(loop > blockpage || pageNo > totalPageNum)) {

				// 1 2 3 4 5 6 7 8 9 10
				// pageBar += "<li class='page-item'><a class='page-link'
				// href='memberList.up?searchType="+searchType+"&searchWord="+searchWord+"&sizePerPage="+sizePerPage+"&currentShowPageNo="+pageNo+"'>"+pageNo+"</a></li>";

				if (pageNo == Integer.parseInt(currentPage)) {

					pageBar += "<li class='page-item active'><a class='page-link' href='#'>" + pageNo + "</a></li>";
				} else {

					pageBar += "<li class='page-item'><a class='page-link' href='question.dk?page=" + pageNo + "'>" + pageNo + "</a></li>";
				}

				loop++;

				// 1 2 3 4 5 6 7 8 9 10
				// 11 12 13 14 15 16 17 18 19 20
				pageNo++;

			}
			pageBar += "<li class='page-item'><a class='page-link' href='question.dk?page="
					+ (totalPageNum) + "'>[맨마지막]</a></li>";

			// 다음 마지막 만들기
			if (pageNo <= totalPageNum) {
				pageBar += "<li class='page-item'><a class='page-link' href='question.dk?page="
						+ (currentPage + 1) + "'>[다음]</a></li>";
			}
			
			
			//======================== 페이지바 만들기 끝 ========================//
			
			List<QuestionVO> questionList = mdao.getQuestionBoard(paraMap);
			request.setAttribute("questionList", questionList);
			
			 for (QuestionVO q : questionList) {
	                boolean isAnswer = mdao.isAnswer(q.getQuestion_seq());
	                q.setHasAnswer(isAnswer);
	            }
			
			request.setAttribute("pageBar", pageBar);
			request.setAttribute("blockSize", blockSize);
			request.setAttribute("id", id);

			
			int totalQuestionCount = mdao.getTotalQuestionCount(paraMap);
			request.setAttribute("totalQuestionCount", totalQuestionCount);
			request.setAttribute("currentPage", currentPage);
			
			super.setRedirect(false);
			super.setViewPage("/WEB-INF/view/member/member_question.jsp");

		} else {
			super.setRedirect(false);
			super.setViewPage("/WEB-INF/view/member/member_Login.jsp");
		}
	}

}
