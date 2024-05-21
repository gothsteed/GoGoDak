package admin.controller;

import admin.model.AdminDAO;
import admin.model.AdminDAO_imple;
import common.controller.AbstractController;
import domain.BoardVO;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public class Notice extends AbstractController {
    
    private AdminDAO adao = null;

    public Notice() {
        adao = new AdminDAO_imple();
    }
    
    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
        
        String method = request.getMethod();
        System.out.println("HTTP Method: " + method);
        
        if ("POST".equalsIgnoreCase(method)) {
            
            String title = request.getParameter("title");
            String content = request.getParameter("content");
            String pic = request.getParameter("pic");

//            System.out.println("Title: " + title);
//            System.out.println("Content: " + content);
//            System.out.println("Pic: " + pic);

            BoardVO board = new BoardVO();
            board.setTitle(title);
            board.setContent(content);
            board.setPic(pic);

            int result = adao.boardWrite(board);
            System.out.println("result : "+result);
            if (result == 1) {
//                System.out.println("DB Insert 성공");
                
                super.setRedirect(false);
                super.setViewPage("/WEB-INF/view/admin/admin_boardWrite.jsp");

                String message = "공지사항이 등록 되었습니다.";
                String loc = "javascript:history.back()";

                request.setAttribute("message", message);
                request.setAttribute("loc", loc);
                
                super.setViewPage("/WEB-INF/view/msg.jsp");
                
            } 
        } else {
//            System.out.println("GET 요청 처리");
            super.setRedirect(false);
            super.setViewPage("/WEB-INF/view/admin/admin_boardWrite.jsp");
        }
    }
}