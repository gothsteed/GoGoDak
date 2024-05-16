package login.controller;

import java.util.HashMap;
import java.util.Map;

import common.controller.AbstractController;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import member.model.MemberDao;
import member.model.MemberDao_Imple;


public class IdFind extends AbstractController {
	
	private MemberDao memberDao;

	public IdFind() {
		this.memberDao = new MemberDao_Imple();
	}



	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		super.setRedirect(false);
		super.setViewPage("/WEB-INF/view/member/member_findId.jsp");

		String method = request.getMethod();

		if (method.equalsIgnoreCase("get")) {
//			super.setRedirect(false);
//			super.setViewPage("");
			
			return;
			
		}
		
		
		String name = request.getParameter("name");
		String email = request.getParameter("email");

		Map<String, String> paraMap = new HashMap<String, String>();

		paraMap.put("name", name);
		paraMap.put("email", email);

		String id = memberDao.getId(paraMap);

		//TODO: 뷰 세팅하기

	}

}
