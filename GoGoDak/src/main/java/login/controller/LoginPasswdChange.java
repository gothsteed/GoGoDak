package login.controller;

import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

import common.controller.AbstractController;
import domain.MemberVO;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import member.model.MemberDao;
import member.model.MemberDao_Imple;

public class LoginPasswdChange extends AbstractController {

	private MemberDao mdao = null;

	public LoginPasswdChange() {
		mdao = new MemberDao_Imple();
	}
	
	
	
	
	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		HttpSession session = request.getSession();
		MemberVO tempuser = (MemberVO) session.getAttribute("tempuser");
		
		
		
		String method = request.getMethod(); 
		String id = (String)tempuser.getId();
		
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
			
			if(n == 1) {
				String message = id + "님의 비밀번호가 변경되었습니다. 다시 로그인 해주세요!!";
                String loc = request.getContextPath() + "/login/login.dk";

                request.setAttribute("message", message);
                request.setAttribute("loc", loc);
                
                super.setViewPage("/WEB-INF/view/msg.jsp");
                return;
             
			}
			else {
				String message = "SQL구문 오류가 발생되어 비밀번호 변경을 할 수 없습니다.";
                String loc = request.getContextPath() + "/login/pwdFind.dk";

                request.setAttribute("message", message);
                request.setAttribute("loc", loc);
                
                super.setViewPage("/WEB-INF/view/msg.jsp");
                return;
			}
			
		}  // end of if("POST".equalsIgnoreCase(method)) ----------
	
		request.setAttribute("id", id);
		request.setAttribute("method", method);
		super.setRedirect(false);
		super.setViewPage("/WEB-INF/view/member/member_loginPasswdChange.jsp");

		
	}

}
