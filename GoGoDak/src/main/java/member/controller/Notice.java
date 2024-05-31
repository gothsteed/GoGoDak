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
		
		String blockSize = "8";  //한번에 보여주는 페이지 수
		String currentPage = request.getParameter("currentPage");

		try {
			currentPage = request.getParameter("page");
		} catch (NumberFormatException e) {
			currentPage = "1";
		}
		
		Map<String,String> paraMap = new HashMap<>();
		paraMap.put("blockSize",blockSize); //한번에 보여주는 페이지 수
		paraMap.put("currentPage",currentPage);

		int totalPageNum = mdao.getBoardTotalPage(paraMap); //전체페이지수 계산
		
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
	
		String pageBar = "<li class='page-item'><a class='page-link' href='notice.dk?page=1'>[맨처음]</a></li>";

		if (pageNo != 1) {
			pageBar += "<li class='page-item'><a class='page-link' href='notice.dk?page=" + (pageNo - 1) + "'>[이전]</a></li>";
		}

		// 맨처음 맨마지막 만들기

		while (!(loop > blockpage || pageNo > totalPageNum)) {

			if (pageNo == Integer.parseInt(currentPage)) {

				pageBar += "<li class='page-item active'><a class='page-link' href='#'>" + pageNo + "</a></li>";
			} else {

				pageBar += "<li class='page-item'><a class='page-link' href='notice.dk?page=" + pageNo + "'>" + pageNo + "</a></li>";
			}

			loop++;
			pageNo++;

		}
		pageBar += "<li class='page-item'><a class='page-link' href='notice.dk?page="
				+ (totalPageNum) + "'>[맨마지막]</a></li>";
		
		// 다음 마지막 만들기
		
		if (pageNo <= totalPageNum) {
			pageBar += "<li class='page-item'><a class='page-link' href='notice.dk?page=" + (currentPage + 1) + "'>[다음]</a></li>";
		}
		List<BoardVO> boardList = mdao.getBoard(paraMap);
		request.setAttribute("boardList", boardList);

		
		request.setAttribute("pageBar", pageBar);
		request.setAttribute("pageBar", pageBar);
		request.setAttribute("blockSize", blockSize);
		
		int totalBoardCount = mdao.getTotalBoardCount(paraMap);
		request.setAttribute("totalBoardCount", totalBoardCount);
		request.setAttribute("currentPage", currentPage);


		super.setRedirect(false);
		super.setViewPage("/WEB-INF/view/member/member_board.jsp");

	}

}
