package login.controller;

import java.util.HashMap;
import java.util.Map;

import common.controller.AbstractController;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import member.model.MemberDao;
import member.model.MemberDao_Imple;

public class DormancyEnd extends AbstractController {

	private MemberDao mdao = null;

	public DormancyEnd() {
		mdao = new MemberDao_Imple();
	}
	
	
	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		String id = request.getParameter("id");
		String method = request.getMethod(); 
		System.out.println("dormancy method : " + method );
		System.out.println("dormancy id : " + id );
		
		
			int n = mdao.isDormancy(id);
			
			if(n==1) {
				String message = id + "님의 휴면이 해제 되었습니다. 다시 로그인 해 주세요";
                String loc = request.getContextPath() + "/index.dk";
                
                request.setAttribute("message", message);
                request.setAttribute("loc", loc);
                
                super.setViewPage("/WEB-INF/view/msg.jsp");
			}
			else {
				String message = "SQL구문 오류가 발생되어 휴면을 해제 할 수 없습니다.";
                String loc = request.getContextPath() + "/login/login.dk";

                request.setAttribute("message", message);
                request.setAttribute("loc", loc);
                
                super.setViewPage("/WEB-INF/view/msg.jsp");
			}
			
		
			
			
		

	}

}
