package member.controller;

import common.controller.AbstractController;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import domain.MemberVO;


import member.model.MemberDao;
import member.model.MemberDao_Imple;

public class MemberOneDetail extends AbstractController {

	private MemberDao mdao = null;

	public MemberOneDetail() {
		mdao = new MemberDao_Imple();
	}
	
	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		// === 관리자(admin)로 로그인 했을때만 조회가 가능하도록 한다. === //
		HttpSession session = request.getSession();
		
		MemberVO loginuser = (MemberVO)session.getAttribute("loginuser");
		
		if(loginuser != null && "admin".equals(loginuser.getId())) {
			// 관리자(admin)로 로그인 했을 경우 
		   
			String method = request.getMethod();
			
			if("POST".equalsIgnoreCase(method)) {
				// POST 방식일때
				
				String id = request.getParameter("id");
				String goBackURL = request.getParameter("goBackURL");
				
			//	System.out.println("goBackURL => " + goBackURL);
			//	goBackURL => /member/memberList.up?searchType=name&searchWord=%EC%9C%A0&sizePerPage=5&currentShowPageNo=15
				
				
				MemberVO mvo = mdao.selectOneMember(id);
				
				request.setAttribute("mvo", mvo);
				request.setAttribute("goBackURL", goBackURL);
				
			//	super.setRedirect(false);
				super.setViewPage("/WEB-INF/view/member/memberOneDetail.jsp");
			}
			
		}
		else {
			// 로그인을 안하거나 또는 관리자(admin)가 아닌 사용자로 로그인 했을 경우
			
		}
		
	}

}
