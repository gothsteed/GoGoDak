package member.controller;

import java.sql.SQLException;

import common.controller.AbstractController;
import domain.MemberVO;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import member.model.MemberDao;
import member.model.MemberDao_Imple;

public class MemberWithdrawal extends AbstractController {

	private MemberDao mdao = null;

	public MemberWithdrawal() {
		mdao = new MemberDao_Imple();
	}
	
	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		String method = request.getMethod();
		
		if("POST".equalsIgnoreCase(method)) {
			String id = request.getParameter("id");	
			String pwd = request.getParameter("password");
			
			MemberVO member = new MemberVO();
			member.setId(id);
			member.setPassword(pwd);
			String message = "";
			String loc = "";
			   
			try {
				int n = mdao.deleteMember(member);
				
				if(n == 1) {
					message = "회원탈퇴가 완료되었습니다. 그동안 고고닭을 이용해주셔서 감사합니다:)";
					loc = request.getContextPath() + "/login/logout.dk";
				}
				else {
					message = "회원탈퇴가 실패홤 다시시도하셈:)";
					loc = request.getContextPath() + "/login/logout.dk";
				}
			} catch(SQLException e) {
				message = "SQL구문에 오류가 발생하였습니다.";
				loc = "javascript:history.back()";
				
				e.printStackTrace();
			}
			
			request.setAttribute("message", message);
			request.setAttribute("loc", loc);
			super.setRedirect(false);
			super.setViewPage("/WEB-INF/view/msg.jsp");
		}
		
	}

}
