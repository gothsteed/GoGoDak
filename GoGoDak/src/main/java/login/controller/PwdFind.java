package login.controller;

import java.util.HashMap;
import java.util.Map;
import java.util.Random;

import common.controller.AbstractController;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import member.model.MemberDao;
import member.model.MemberDao_Imple;


public class PwdFind extends AbstractController {
	
	private MemberDao memberDao;

	public PwdFind() {
		this.memberDao = new MemberDao_Imple();
	}

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws Exception {

		String method = request.getMethod();
		request.setAttribute("method", method);
		
		if(method.equalsIgnoreCase("get")) {
			
			//TODO: 비밀번호 찾기 뷰 설정
			super.setRedirect(false);
			super.setViewPage("/WEB-INF/view/member/member_findPw.jsp");
			
			return;
		}
		
		String id = request.getParameter("id");
		String name = request.getParameter("name");
		String email = request.getParameter("email");

		Map<String, String> paraMap = new HashMap<String, String>();

		paraMap.put("id", id);
		paraMap.put("name", name);
		paraMap.put("email", email);

		boolean isExist = memberDao.isExist(paraMap);
		
		request.setAttribute("isExist", isExist);
		
		boolean isSendMailSuccess = false;
		
		if(isExist) {
			Random rnd = new Random();

			String certification_code = "";

			char randchar = ' ';
			for (int i=0; i<5; i++) {
				randchar = (char) (rnd.nextInt('z' - 'a' + 1) + 'a');
				certification_code += randchar;
			} // end of for ---------- 

			int randnum = 0;
			for (int i=0; i<7; i++) {
				randnum = rnd.nextInt(9 - 0 + 1) + 0;
				certification_code += randnum;
			} // end of for ---------- 
			
			GoogleMail googleMail = new GoogleMail();
			
			try {
				googleMail.send_certification_code(email, certification_code);
				isSendMailSuccess = true;
				request.setAttribute("sendMailSuccess", isSendMailSuccess);
				
				HttpSession session = request.getSession();
				session.setAttribute("certification_code", certification_code);
				 
				request.setAttribute("id", id);
				request.setAttribute("name", name);
				request.setAttribute("email", email);
				
			} catch (Exception e) {
				e.printStackTrace();
				isSendMailSuccess = false;
				request.setAttribute("sendMailSuccess", isSendMailSuccess);
			}
			
			super.setRedirect(false);
			super.setViewPage("/WEB-INF/view/member/member_findPw.jsp");
		}
		
	}

}
