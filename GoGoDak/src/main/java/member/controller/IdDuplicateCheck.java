package member.controller;

import org.json.JSONObject;

import common.controller.AbstractController;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import member.model.MemberDao;
import member.model.MemberDao_Imple;

public class IdDuplicateCheck extends AbstractController {

	private MemberDao mdao = null;

	public IdDuplicateCheck() {
		mdao = new MemberDao_Imple();
	}
	
	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		String method = request.getMethod();	
		
		if("POST".equals(method)) {
			
			String id = request.getParameter("id"); 
			
			boolean isExists = mdao.idDuplicateCheck(id);
			
			JSONObject jsonObj = new JSONObject();
			jsonObj.put("isExists", isExists);
		
			String json = jsonObj.toString(); 
			System.out.println(">>> 확인용 json => " + json);
			
			request.setAttribute("json", json);
			
			super.setRedirect(false);
			super.setViewPage("/WEB-INF/jsonview.jsp");
			
		}
	}
}
