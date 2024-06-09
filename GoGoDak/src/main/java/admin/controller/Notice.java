package admin.controller;

import java.io.File;
import java.util.Calendar;
import java.util.Collection;

import admin.model.AdminDAO;
import admin.model.AdminDAO_imple;
import common.controller.AbstractController;
import conatainer.annotation.Autowired;
import domain.BoardVO;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import jakarta.servlet.http.Part;

public class Notice extends AbstractController {
    
    private AdminDAO adao = null;

    @Autowired
    public Notice(AdminDAO adao) {
        this.adao = adao;
    }
    
    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
        
        String method = request.getMethod();
        System.out.println("HTTP Method: " + method);
        
        if ("POST".equalsIgnoreCase(method)) {
        	
        	String title = request.getParameter("title");
            String content = request.getParameter("content");

            ///////////////////////////////////////////////////////////////
    	
    		HttpSession session = request.getSession();
    		String savePath = session.getServletContext().getRealPath("/images/board");
    		
    		Collection<Part> parts = request.getParts();
    		
    		String savedFileName = null;
    		
    		for(Part part : parts) {
    			
    			if (!part.getHeader("Content-Disposition").contains("filename=")) {
    				continue;
    			}
    			
    			String fileName = extractFileName(part.getHeader("Content-Disposition"));
    			if (part.getSize() <= 0) {
    				continue;
    			}
    			
    			String newFilename = fileName.substring(0, fileName.lastIndexOf(".")); // 확장자를 뺀 파일명 알아오기

    			newFilename += "_" + String.format("%1$tY%1$tm%1$td%1$tH%1$tM%1$tS", Calendar.getInstance());
    			newFilename += System.nanoTime();
    			newFilename += fileName.substring(fileName.lastIndexOf(".")); // 확장자 붙이기
    			
    			System.out.println("new File upload: " + newFilename);
    			savedFileName = newFilename;
    			
    			part.write(savePath + File.separator + newFilename);
    		
    			part.delete();
    		}
    		
    		if(title == null || savedFileName == null) {
    			String loc = request.getContextPath() + "/member/notice.dk";
    			String message = "공지사항 등록 실패";
    			
    			request.setAttribute("loc", loc);
    			request.setAttribute("message", message);
    			
    			setRedirect(false);
    			setViewPage("/WEB-INF/view/msg.jsp");
    			return;
    		}
    		
			BoardVO board = new BoardVO();
	        board.setTitle(title);
	        board.setContent(content);
	        board.setPic(savedFileName);
              
    		int result = adao.boardWrite(board);
    		
    		if(result != 1) {
    			String loc = request.getContextPath() + "/member/notice.dk";
    			String message = "공지사항 등록 실패";
    			
    			request.setAttribute("loc", loc);
    			request.setAttribute("message", message);
    			
    			setRedirect(false);
    			setViewPage("/WEB-INF/view/msg.jsp");
    			return;
    		}

            if(result == 1) {
                String message = "공지사항이 등록 되었습니다.";
                String loc = request.getContextPath()+"/member/notice.dk";

                request.setAttribute("message", message);
                request.setAttribute("loc", loc);
                
                super.setViewPage("/WEB-INF/view/msg.jsp");
            } 
            
        } else {
            super.setRedirect(false);
            super.setViewPage("/WEB-INF/view/admin/admin_boardWrite.jsp");
        }
    }
    
    private String extractFileName(String partHeader) {
		for (String cd : partHeader.split("\\;")) {
			if (cd.trim().startsWith("filename")) {
				String fileName = cd.substring(cd.indexOf("=") + 1).trim().replace("\"", "");
				int index = fileName.lastIndexOf(File.separator);
				return fileName.substring(index + 1);
			}
		}
		return null;
	}
}