package member.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import common.controller.AbstractController;
import domain.BoardVO;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import member.model.MemberDao;
import member.model.MemberDao_Imple;
import my.util.MyUtil;

public class Notice extends AbstractController {

	private MemberDao mdao = null;

	public Notice() {
		mdao = new MemberDao_Imple();
	}

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		int blockSize = 8;  //한번에 보여주는 페이지 수
		int currentPage;    

		try {
			currentPage = Integer.parseInt(request.getParameter("page"));
		} catch (NumberFormatException e) {
			currentPage = 1;
		}

		int totalPageNum = mdao.getBoardTotalPage(blockSize); //전체페이지수 계산
		if (currentPage > totalPageNum) {
			currentPage = 1;
		}

		List<BoardVO> boardList = mdao.getBoard(currentPage, blockSize);

		// pageBar

		int loop = 1;
		int pageNo = ((currentPage - 1) / blockSize) * blockSize + 1;

		String pageBar = "<li class='page-item'><a class='page-link' href='notice.dk?page=1'>[맨처음]</a></li>";

		if (pageNo != 1) {
			pageBar += "<li class='page-item'><a class='page-link' href='notice.dk?page=" + (pageNo - 1) + "'>[이전]</a></li>";
		}

		// 맨처음 맨마지막 만들기

		while (!(loop > blockSize || pageNo > totalPageNum)) {

			// 1 2 3 4 5 6 7 8 9 10
			// pageBar += "<li class='page-item'><a class='page-link'
			// href='memberList.up?searchType="+searchType+"&searchWord="+searchWord+"&sizePerPage="+sizePerPage+"&currentShowPageNo="+pageNo+"'>"+pageNo+"</a></li>";

			if (pageNo == currentPage) {

				pageBar += "<li class='page-item active'><a class='page-link' href='#'>" + pageNo + "</a></li>";
			} else {

				pageBar += "<li class='page-item'><a class='page-link' href='notice.dk?page=" + pageNo + "'>" + pageNo + "</a></li>";
			}

			loop++;

			// 1 2 3 4 5 6 7 8 9 10
			// 11 12 13 14 15 16 17 18 19 20
			pageNo++;

		}
		pageBar += "<li class='page-item'><a class='page-link' href='notice.dk?page="
				+ (totalPageNum) + "'>[맨마지막]</a></li>";
		
		// 다음 마지막 만들기
		if (pageNo <= totalPageNum) {
			pageBar += "<li class='page-item'><a class='page-link' href='notice.dk?page=" + (currentPage + 1) + "'>[다음]</a></li>";
		}
		

		request.setAttribute("boardList", boardList);
		request.setAttribute("pageBar", pageBar);

		super.setRedirect(false);
		super.setViewPage("/WEB-INF/view/member/member_board.jsp");

	}

}
