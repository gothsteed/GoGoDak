package login.controller;

import java.util.HashMap;
import java.util.Map;

import common.controller.AbstractController;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import member.model.MemberDao;
import member.model.MemberDao_Imple;

public class IdFind extends AbstractController {
	
	private MemberDao mdao = null;

	public IdFind() {
		mdao = new MemberDao_Imple();
	}

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		String method = request.getMethod(); 
		
		if("POST".equalsIgnoreCase(method)) { 
			String name = request.getParameter("name");
			String email = request.getParameter("email");
			
			Map<String, String> paraMap = new HashMap<>();
			paraMap.put("name", name);
			paraMap.put("email", email);
			
			String id = mdao.getId(paraMap);
			
			if(id != null) {
				request.setAttribute("id", id);
			}
			else {
				request.setAttribute("id", "존재하지 않습니다.");
			}
			
			request.setAttribute("name", name);
			request.setAttribute("email", email);
			
		} // end of if("POST".equalsIgnoreCase(method)) ----------
		
		request.setAttribute("method", method);
		
		super.setRedirect(false);
		super.setViewPage("/WEB-INF/view/member/member_findId.jsp");
	}

}
