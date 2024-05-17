package member.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import common.controller.AbstractController;
import domain.BoardVO;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import member.model.MemberDao;
import member.model.MemberDao_Imple;
import my.util.MyUtil;

public class Notice extends AbstractController {
	
	private MemberDao mdao=null;
	public Notice() {
		mdao = new MemberDao_Imple();
	}
	
	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws Exception {

		
			String searchType = request.getParameter("searchType");
			String searchWord = request.getParameter("searchWord");
			String sizePerPage = request.getParameter("sizePerPage"); // 뷰단페이지에 폼태그 안에 포함되어있는 sizePerPage
			String currentShowPageNo = request.getParameter("currentShowPageNo");
//					System.out.println("currentShowPageNo : " + currentShowPageNo);

			/*
			 * if (searchType == null || (!"name".equals(searchType) &&
			 * !"userid".equals(searchType) && !"email".equals(searchType))) { searchType =
			 * ""; }
			 * 
			 * if (searchWord == null || (searchWord != null &&
			 * searchWord.trim().isEmpty())) { searchWord = ""; }
			 * 
			 * if (sizePerPage == null || (!"10".equals(sizePerPage) &&
			 * !"5".equals(sizePerPage) && !"3".equals(sizePerPage))) { sizePerPage = "10";
			 * 
			 * // 장난치려고 aflkaf 라고 써도 10값이 들어감 디폴트 }
			 */
			if (currentShowPageNo == null) {
				currentShowPageNo = "1";
			}

			Map<String, String> paraMap = new HashMap<>();
			paraMap.put("searchType", searchType);
			paraMap.put("searchWord", searchWord);

			// 페이징 처리를 안한 모든 회원 또는 검색한 회원 목록 보여주기 [복수개]
			// List<MemberVO>memberList = mdao.select_Member_nopaging(paraMap);

			paraMap.put("currentShowPageNo", currentShowPageNo);
			paraMap.put("sizePerPage", sizePerPage); // 한페이지당 보여줄 행의 개수 3,5,10

			// ****페이징 처리한 모든 회원 또는 검색한 회원 목록 보여주기 [복수개라서 List]****//

			// 페이징 처리를 위한 검색이 있는 또는 검색이 없는 회원에 대한 총 페이지수 알아오기//
			int totalPage = mdao.getTotalPage(paraMap);
			// System.out.println("확인용 : " +totalPage);

			// === GET 방식이므로 사용자가 웹브라우저 주소창에서 currentShowPageNo 에 totalPage 값 보다 더 큰값을 입력하여
			// 장난친 경우
			// === GET 방식이므로 사용자가 웹브라우저 주소창에서 currentShowPageNo 에 0 또는 음수를 입력하여 장난친 경우
			// === GET 방식이므로 사용자가 웹브라우저 주소창에서 currentShowPageNo 에 숫자가 아닌 문자열을 입력하여 장난친 경우
			// 아래처럼 막아주도록 하겠다.
			try {
				if (Integer.parseInt(currentShowPageNo) > totalPage || Integer.parseInt(currentShowPageNo) <= 0) {

					currentShowPageNo = "1";
					paraMap.put("currentShowPageNo", currentShowPageNo);

				}
			} catch (NumberFormatException e) { // 장난쳐 온다면
				currentShowPageNo = "1";
				paraMap.put("currentShowPageNo", currentShowPageNo);
			}

			String pageBar = "";

			int blockSize = 10; // blockSize 는 블럭(토막)당 보여지는 페이지 번호의 개수이다.

			int loop = 1; // loop 는 1 부터 증가하여 1개 블럭을 이루는 페이지번호의 개수(지금은 10개)까지만 증가하는 용도이다.

			// ==== !!! pageNo 구하는 공식 !!! ==== //
			int pageNo = ((Integer.parseInt(currentShowPageNo) - 1) / blockSize) * blockSize + 1;
			// pageNo 는 페이지바에서 보여지는 첫번째 번호이다.

			// ***[맨처음][이전] 만들기 *** //
			pageBar += "<li class='page-item'><a class='page-link' href='memberList.up?searchType=" + searchType
					+ "&searchWord=" + searchWord + "&sizePerPage=" + sizePerPage
					+ "&currentShowPageNo=1'>[맨처음]</a></li>";

			if (pageNo != 1) {
				pageBar += "<li class='page-item'><a class='page-link' href='memberList.up?searchType=" + searchType
						+ "&searchWord=" + searchWord + "&sizePerPage=" + sizePerPage + "&currentShowPageNo="
						+ (pageNo - 1) + "'>[이전]</a></li>";
			}
			while (!(loop > blockSize || pageNo > totalPage)) {

				if (pageNo == Integer.parseInt(currentShowPageNo)) { // 현재 보고자 하는 페이지가
					pageBar += "<li class='page-item active'><a class='page-link' href='#'>" + pageNo + "</a></li>";
				} else {
					pageBar += "<li class='page-item'><a class='page-link' href='memberList.up?searchType=" + searchType
							+ "&searchWord=" + searchWord + "&sizePerPage=" + sizePerPage + "&currentShowPageNo="
							+ pageNo + "'>" + pageNo + "</a></li>";
				}
				loop++; // 1 2 3 4 5 6 7 8 9 10
				pageNo++; // 1 2 3 4 5 6 7 8 9 10
							// 11 12 13 14 15 16 17 18 19 20

			} // end of while

			// ***[다음][마지막] 만들기 *** //
			// pageNo ==> 11
			if (pageNo <= totalPage) { // 맨 마지막이 아니라면 다음을 넣어준다
				pageBar += "<li class='page-item'><a class='page-link' href='memberList.up?searchType=" + searchType
						+ "&searchWord=" + searchWord + "&sizePerPage=" + sizePerPage + "&currentShowPageNo=" + pageNo
						+ "'>[다음]</a></li>";
			}
			pageBar += "<li class='page-item'><a class='page-link' href='memberList.up?searchType=" + searchType
					+ "&searchWord=" + searchWord + "&sizePerPage=" + sizePerPage + "&currentShowPageNo=" + totalPage
					+ "'>[마지막]</a></li>";

			// *** ==== 페이지바 만들기 끝 ==== *** //

			
			
			// *** ====== 현재 페이지를 돌아갈 페이지(goBackURL)로 주소 지정하기 ======= *** //
			String currentURL = MyUtil.getCurrentURL(request); // 회원조회를 했을시 현재 그 페이지로 그대로 되돌아가길 위한 용도로 쓰임.
//					System.out.println("currentURL :" + currentURL );

			List<BoardVO> memberList = mdao.select_Member_paging(paraMap);

			request.setAttribute("memberList", memberList);

			if (searchType != null
					&& ("name".equals(searchType) || "userid".equals(searchType) || "email".equals(searchType))) {
				request.setAttribute("searchType", searchType);
			}

			if (searchWord != null && !searchWord.trim().isEmpty()) {
				request.setAttribute("searchWord", searchWord);
			}

			request.setAttribute("sizePerPage", sizePerPage);// 담아서 뷰단으로 보냄 memberList.jsp

			request.setAttribute("pageBar", pageBar);// 담아서 뷰단으로 보냄 memberList.jsp

			request.setAttribute("currentURL", currentURL); // 담아서 뷰단으로 보냄

			/*
			 * >>> 뷰단(memberList.jsp)에서 "페이징 처리시 보여주는 순번 공식" 에서 사용하기 위해 검색이 있는 또는 검색이 없는 회원의
			 * 총개수 알아오기 시작 <<<
			 */
			int totalMemberCount = mdao.getTotalMemberCount(paraMap); // 맵에서 받아온 데이터개수를 알아오기 위한것
//					System.out.println("확인용 totalMemberCount : "+ totalMemberCount);
			request.setAttribute("totalMemberCount", totalMemberCount);// 담아서 뷰단으로 보냄
			request.setAttribute("currentShowPageNo", currentShowPageNo);// 담아서 뷰단으로 보냄

//						검색이 있는 또는 검색이 없는 회원의 총개수 알아오기 끝 <<<

			
			
			
			super.setRedirect(false);
			super.setViewPage("/WEB-INF/view/member/member_board.jsp");

	

	}

}
