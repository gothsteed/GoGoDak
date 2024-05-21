package login.controller;

import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

import common.controller.AbstractController;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import member.model.MemberDao;
import member.model.MemberDao_Imple;

public class PwdUpdateEnd extends AbstractController {

	private MemberDao mdao = null;

	public PwdUpdateEnd() {
		mdao = new MemberDao_Imple();
	}
	
	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		String id = request.getParameter("id");
		String method = request.getMethod(); 
		
		if("POST".equalsIgnoreCase(method)) {
			
			String new_pwd = request.getParameter("pwd");
			
			Map<String, String> paraMap = new HashMap<>();
			paraMap.put("id", id);
			paraMap.put("new_pwd", new_pwd);
			
			int n = 0;
			
			try { 
				n = mdao.pwdUpdate(paraMap);
			} catch (SQLException e) { 
				e.printStackTrace();
			}
			
			request.setAttribute("n", n);
			
		}  // end of if("POST".equalsIgnoreCase(method)) ----------
		
		request.setAttribute("id", id);
		request.setAttribute("method", method);
		
		super.setRedirect(false);
		super.setViewPage("/WEB-INF/view/member/member_pwdUpdate.jsp");
	}

}
