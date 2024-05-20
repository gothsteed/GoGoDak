package admin.controller;

import java.sql.SQLException;

import admin.model.AdminDAO;
import admin.model.AdminDAO_imple;
import common.controller.AbstractController;
import domain.BoardVO;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public class NoticeEditEnd extends AbstractController {

	private AdminDAO adao = null;

	public NoticeEditEnd() {
		adao = new AdminDAO_imple();
	}
	
	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		
		
		String method = request.getMethod(); // "GET" 또는 "POST"
		
		if ("POST".equalsIgnoreCase(method)) {
			// **** POST 방식으로 넘어온 것이라면 **** //
			String board_seq = request.getParameter("board_seq");
			String title = request.getParameter("title");
			String content = request.getParameter("content");
			String pic = request.getParameter("pic");

			BoardVO board = new BoardVO();
			board.setBoard_seq(Integer.parseInt(board_seq));
			board.setTitle(title);
			board.setContent(content);
			board.setPic(pic);
			

			// === 회원수정이 성공되어지면 "회원정보 수정 성공!!" 이라는 alert 를 띄우고 시작페이지로 이동한다. === //
			String message = "";
			String loc = "";

			try {
				int boardEdit = adao.updateBoard(board);
				System.out.println(boardEdit);
				if (boardEdit == 1) {

					message = "공지사항 수정 완료";
					loc = request.getContextPath() + "/index.up"; // 시작페이지로 이동한다.
				}
			} catch (SQLException e) {
				message = "SQL구문 에러발생";
				loc = "javascript:history.back()"; // 자바스크립트를 이용한 이전페이지로 이동하는 것.
				e.printStackTrace();
			}

			request.setAttribute("message", message);
			request.setAttribute("loc", loc);

			request.setAttribute("noticeEditEnd", true);

			// super.setRedirect(false);
			super.setViewPage("/WEB-INF/view/msg.jsp");
		}
	}

}
