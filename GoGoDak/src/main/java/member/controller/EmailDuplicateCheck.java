package member.controller;

import org.json.JSONObject;

import common.controller.AbstractController;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import member.model.MemberDao;
import member.model.MemberDao_Imple;

public class EmailDuplicateCheck extends AbstractController {

	private MemberDao mdao = null;

	public EmailDuplicateCheck() {
		mdao = new MemberDao_Imple();
	}
	
	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		String method = request.getMethod(); 	
		
		if("POST".equals(method)) {
			
			String email = request.getParameter("email");
			
			boolean isExists = mdao.emailDuplicateCheck(email); 
			
			JSONObject jsonObj = new JSONObject(); 
			jsonObj.put("isExists", isExists); 
	
			String json = jsonObj.toString(); 
			
			request.setAttribute("json", json);
			
			super.setRedirect(false);
			super.setViewPage("/WEB-INF/jsonview.jsp");
		}

	}

}
