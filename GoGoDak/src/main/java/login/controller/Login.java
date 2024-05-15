package login.controller;

import java.util.HashMap;
import java.util.Map;

import common.controller.AbstractController;
import domain.MemberVO;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import member.model.MemberDao;
import member.model.MemberDao_Imple;

public class Login extends AbstractController {
	
	private MemberDao memberDao;

	
	public Login() {
		this.memberDao = new MemberDao_Imple();
	}


	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		String method = request.getMethod();
		
		if(method.equalsIgnoreCase("get")) {
			super.setRedirect(false);
			
			//TODO: 로그인 뷰페이지 설정
			super.setViewPage("");
			
		}
		else {
			String clientIp = request.getRemoteAddr();

			String userId = request.getParameter("userid");
			String password = request.getParameter("pwd");

			System.out.println(userId + " " + password + " " + clientIp);

			Map<String, String> paraMap = new HashMap<String, String>();
			
			paraMap.put("userId", userId);
			paraMap.put("password", password);
			paraMap.put("clientIp", clientIp);
			
			
			MemberVO loginUser = memberDao.login(paraMap);
			
			
			if(loginUser == null) {
				System.out.println("!!!!로그인 실패!!!!");
				
				String errormsg = "로그인 실패";
				String loc = "javascript:history.back()";

				request.setAttribute("message", errormsg);
				request.setAttribute("loc", loc);
				super.setRedirect(false);
				super.setViewPage("/WEB-INF/view/msg.jsp");
				
				return;
			}
			
			//System.out.println("로그인 성공: " + loginUser.getName());
			

//			System.out.println("is required password change: " + loginUser.isRequirePasswordChange());
			if(loginUser.getActive_status() == 0) {
				System.out.println("휴면");
				
				String errormsg = "!!!!휴면계정입니다!!!!";
				String loc = request.getContextPath() + "";

				request.setAttribute("message", errormsg);
				request.setAttribute("loc", loc);

				super.setViewPage("/WEB-INF/view/msg.jsp");
				
				return; 
			}

			if(loginUser.isRequirePasswordChange()) {
				
				String errormsg = "!!!!비밀번호 변경하세요!!!!";
				String loc = request.getContextPath() + "";

				request.setAttribute("message", errormsg);
				request.setAttribute("loc", loc);

				
				super.setRedirect(false);
				super.setViewPage("/WEB-INF/view/msg.jsp");
				
				return; 
				
			}
						
			HttpSession session = request.getSession();
			session.setAttribute("loginUser", loginUser);

			
			super.setRedirect(true);
			super.setViewPage(request.getContextPath() + "/index.dk");
			
		}

	}

}
