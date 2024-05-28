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
			String referer = request.getHeader("Referer");
//			System.out.println("referer:" + referer);
			
			HttpSession session = request.getSession();
			session.setAttribute("goBackURL", referer);
			
			super.setRedirect(false);
			super.setViewPage("/WEB-INF/view/member/member_Login.jsp");
		}
		else {
			String clientIp = request.getRemoteAddr();
			String id = request.getParameter("id");
			String password = request.getParameter("password");

//			System.out.println(id + " " + password + " " + clientIp);

			Map<String, String> paraMap = new HashMap<String, String>();
			
			paraMap.put("id", id);
			paraMap.put("password", password);
			paraMap.put("clientIp", clientIp);
			
			MemberVO loginUser = memberDao.login(paraMap);
			
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
				
				String errormsg = "!!!!휴면계정입니다!!!!";
				String loc = request.getContextPath() + "";

				request.setAttribute("message", errormsg);
				request.setAttribute("loc", loc);

//				super.setRedirect(false);
				super.setViewPage("/WEB-INF/view/msg.jsp");
				
				return; 
			}

			HttpSession session = request.getSession();
			session.setAttribute("loginuser", loginUser);
			
			if(loginUser.isRequirePasswordChange()) {
				
				String errormsg = "!!!!비밀번호 변경하세요!!!!";
				String loc = request.getContextPath() + "";

				request.setAttribute("message", errormsg);
				request.setAttribute("loc", loc);
				
//				super.setRedirect(false);
				super.setViewPage("/WEB-INF/view/msg.jsp");
				
				return; 
				
			}
			else {
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
				
			}		
			
//			System.out.println("login success: " + loginUser.getName());
			
		}

	}

}
