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
import my.util.MyUtil;

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
			super.setViewPage("/WEB-INF/view/member/member_Login.jsp");
		}
		else {
			
			HttpSession session = request.getSession();
			
			String clientIp = request.getRemoteAddr();
			String id = request.getParameter("id");
			String password = request.getParameter("password");

//			System.out.println(id + " " + password + " " + clientIp);

			Map<String, String> paraMap = new HashMap<String, String>();
			
			paraMap.put("id", id);
			paraMap.put("password", password);
			paraMap.put("clientIp", clientIp);
			
			MemberVO loginUser = memberDao.login(paraMap);
			session.setAttribute("tempuser", loginUser); //세션에 값을 저장
			
			if(loginUser == null) {
//				System.out.println("!!!!로그인 실패!!!!");
				
				String errormsg = "로그인 실패";
				String loc = "javascript:history.back()";

				request.setAttribute("message", errormsg);
				request.setAttribute("loc", loc);
				
				super.setRedirect(false);
				super.setViewPage("/WEB-INF/view/msg.jsp");
				
				return;
			}
			
//			System.out.println("로그인 성공: " + loginUser.getName());
//			System.out.println("is required password change: " + loginUser.isRequirePasswordChange());
			
			if(loginUser.getActive_status() == 0) {
//				System.out.println("휴면");
				
				String errormsg = "로그인 기록이 1년동안 존재 하지 않아 휴면처리 되셨습니다 \\n 휴면 해제 페이지로 이동합니다";
				String loc =request.getContextPath()+ "/login/dormancy.dk";

				request.setAttribute("message", errormsg);
				request.setAttribute("loc", loc);

				super.setRedirect(false);
				super.setViewPage("/WEB-INF/view/msg.jsp");
				
				return; 
			}


			
			if(loginUser.isRequirePasswordChange()) {
				
				
				String errormsg = "비밀번호를 변경하신지 3개월이 지났습니다.\\n 암호 변경 페이지로 이동합니다";
				String loc = request.getContextPath() + "/login/loginPasswdChange.dk";
				request.setAttribute("message", errormsg);
				request.setAttribute("loc", loc);
				
				super.setRedirect(false);
				super.setViewPage("/WEB-INF/view/msg.jsp");
				
				return; 
				
			}
			
			
			session.setAttribute("loginuser", loginUser);
	
			super.setRedirect(true);
			
			String goBackURL = (String)session.getAttribute("goBackURL");
			
			if(goBackURL != null) {
				session.removeAttribute("goBackURL");
				super.setRedirect(true);
				super.setViewPage(goBackURL);
			}
			else { 
				super.setViewPage(request.getContextPath() + "/index.dk");
			}
				
			
			
//			System.out.println("login success: " + loginUser.getName());
			
		}

	}

}
