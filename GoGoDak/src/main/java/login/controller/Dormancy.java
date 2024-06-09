package login.controller;

import java.util.HashMap;
import java.util.Map;
import java.util.Random;

import common.controller.AbstractController;
import conatainer.annotation.Autowired;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import member.model.MemberDao;
import member.model.MemberDao_Imple;

public class Dormancy extends AbstractController {

	private MemberDao mdao = null;

	@Autowired
	public Dormancy(MemberDao mdao) {
		this.mdao = mdao;
	}
	
	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		// 비밀번호 찾기 모달창에서 "찾기" 버튼을 클릭했을 경우
		
		 
			String method = request.getMethod(); // "GET" 또는 "POST"
			request.setAttribute("method", method);
			
			if(method.equalsIgnoreCase("get")) {
				
				super.setRedirect(false);
				super.setViewPage("/WEB-INF/view/member/member_dormancy.jsp");
				
				return;
			}
			
				String id = request.getParameter("id");
				String email = request.getParameter("email");
				
				Map<String, String> paraMap = new HashMap<>();
				// 복수개면 Map을 쓴다.
				paraMap.put("id", id);
				paraMap.put("email", email);
				
				
				boolean isUserExist = mdao.isUserExist(paraMap);
				request.setAttribute("isUserExist", isUserExist);
				/////////////////////////////////////////////////////////
				boolean sendMailSuccess = false; // 메일이 정상적으로 전송되었는지 유무를 알아오기 위한 용도
				
				
				if(isUserExist) {
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
					
					GoogleMail googleMail_dormancy = new GoogleMail();
					
					try {
						googleMail_dormancy.send_certification_code_dormancy(email, certification_code , id);
						sendMailSuccess = true;
						request.setAttribute("sendMailSuccess", sendMailSuccess);
						
						HttpSession session = request.getSession();
						session.setAttribute("certification_code", certification_code);
						 
						request.setAttribute("id", id);
						request.setAttribute("email", email);
						
					} catch (Exception e) {
						e.printStackTrace();
						sendMailSuccess = false;
						request.setAttribute("sendMailSuccess", sendMailSuccess);
					}
					
					super.setRedirect(false);
					super.setViewPage("/WEB-INF/view/member/member_dormancy.jsp");
		            
				}// end of if(isUserExist)------------------------------------
				/////////////////////////////////////////////////////////
				
				
				request.setAttribute("sendMailSuccess", sendMailSuccess);
				request.setAttribute("email", email);
				request.setAttribute("id", id);
				
			}// end of if("POST".equalsIgnoreCase(method)) {}----------
			

	

}
