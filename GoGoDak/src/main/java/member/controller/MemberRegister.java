package member.controller;

import java.sql.SQLException;

import common.controller.AbstractController;
import domain.MemberVO;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import member.model.MemberDao;
import member.model.MemberDao_Imple;
import util.security.Sha256;


public class MemberRegister extends AbstractController {
	
	private MemberDao memberDao = null;

	public MemberRegister() {
		this.memberDao = new MemberDao_Imple();
	}

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		
		String method = request.getMethod();
		
		if(method.equalsIgnoreCase("get")) {
			super.setRedirect(false);
			
			//TODO: 뷰페이지 view 폴더에 저장하기
			super.setViewPage("/WEB-INF/view/member/register.jsp");
			return;
		}
		
		String hp1 = request.getParameter("hp1");
		String hp2 = request.getParameter("hp2");
		String hp3 = request.getParameter("hp3");

		String tel = hp1 + hp2 + hp3;
		
		
		MemberVO member = new MemberVO();
		member.setName(request.getParameter("name"));
		member.setId(request.getParameter("userid"));
		member.setPassword(Sha256.encrypt(request.getParameter("pwd")));
		member.setEmail(request.getParameter("email"));
		member.setTel(tel);
		member.setPostcode(request.getParameter("postcode"));
		member.setAddress(request.getParameter("address"));
		member.setAddress_detail(request.getParameter("detailaddress"));
		member.setAddress_extra(request.getParameter("extraaddress"));
		member.setJubun(request.getParameter("jubun"));

		String message = "";
		String loc = "";
	
	
		//자동 로그인
		try {

			int result = memberDao.register(member);
			
//			request.setAttribute("userid", loc)
			request.setAttribute("userid", request.getParameter("userid")); 
			request.setAttribute("pwd", request.getParameter("pwd"));
			
			
			super.setViewPage("/WEB-INF/view/member/memberRegister_after_autoLogin.jsp");

		} catch (SQLException e) {
			message = "!!!! 회원가입 실패 !!!!";
			loc = "javascript:history.back()";
			e.printStackTrace();
			
			
			request.setAttribute("message", message);
			request.setAttribute("loc", loc);
			
			
			super.setRedirect(false);
			super.setViewPage("/WEB-INF/view/msg.jsp");
		}
	
		
	}

}
