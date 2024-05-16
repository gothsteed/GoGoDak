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
		
		
		String id = request.getParameter("userid");
		String email = request.getParameter("email");

		Map<String, String> paraMap = new HashMap<String, String>();

		paraMap.put("id", id);
		paraMap.put("email", email);

		boolean isExist = memberDao.isExist(paraMap);
		request.setAttribute("isExist", isExist);
		boolean isSendMailSuccess = false;
		
		
		if(isExist) {
			
			// 인증키를 랜덤하게 생성하도록 한다.
			Random rnd = new Random();

			String certification_code = "";
			// 인증키는 영문소문자 5글자 + 숫자 7글자 로 만들겠습니다.

			char randchar = ' ';
			for (int i = 0; i < 5; i++) {
				/*
				 * min 부터 max 사이의 값으로 랜덤한 정수를 얻으려면 int rndnum = rnd.nextInt(max - min + 1) +
				 * min; 영문 소문자 'a' 부터 'z' 까지 랜덤하게 1개를 만든다.
				 */
				randchar = (char) (rnd.nextInt('z' - 'a' + 1) + 'a');
				certification_code += randchar;
			} // end of for---------------------

			int randnum = 0;
			for (int i = 0; i < 7; i++) {
				/*
				 * min 부터 max 사이의 값으로 랜덤한 정수를 얻으려면 int rndnum = rnd.nextInt(max - min + 1) +
				 * min; 영문 소문자 'a' 부터 'z' 까지 랜덤하게 1개를 만든다.
				 */
				randnum = rnd.nextInt(9 - 0 + 1) + 0;
				certification_code += randnum;
			} // end of for---------------------

			System.out.println("~~~~ 확인용 certification_code : " + certification_code);
//			 ~~~~ 확인용 certification_code : nexrw2738979

			
			
			try {
				
				GoogleMail googleMail = new GoogleMail();
				googleMail.send_certification_code(email, certification_code);
				isSendMailSuccess = true;
				request.setAttribute("sendMailSuccess", isSendMailSuccess);
				
				HttpSession session = request.getSession();
				session.setAttribute("certification_code", certification_code);
				
				 
				request.setAttribute("email", email);
				request.setAttribute("userid", id);
				
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
