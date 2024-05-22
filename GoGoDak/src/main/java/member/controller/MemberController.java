package member.controller;

import common.controller.AbstractController;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import member.model.MemberDao;
import member.model.MemberDao_Imple;

public class MemberController extends AbstractController {

	private MemberDao memberDao = null;

	public MemberController() {
		this.memberDao = new MemberDao_Imple();
	}
	
	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		
		
		super.setRedirect(false);
		super.setViewPage("/WEB-INF/view/member/member_myshop.jsp");
		
	} 

}
