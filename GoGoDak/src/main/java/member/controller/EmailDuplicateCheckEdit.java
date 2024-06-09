package member.controller;

import java.util.HashMap;
import java.util.Map;

import org.json.JSONObject;

import common.controller.AbstractController;
import conatainer.annotation.Autowired;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import member.model.MemberDao;
import member.model.MemberDao_Imple;

public class EmailDuplicateCheckEdit extends AbstractController {

	private MemberDao mdao = null;

	@Autowired
	public EmailDuplicateCheckEdit(MemberDao mdao) {
		this.mdao = mdao;
	}
	
	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		String method = request.getMethod(); // "GET" 또는 "POST"

		if ("POST".equalsIgnoreCase(method)) {

			String email = request.getParameter("email");
			String id = request.getParameter("id");

			// System.out.println(">>> 확인용 email => " + email);
			// >>> 확인용 email => hanmailrg@naver.com

			// System.out.println(">>> 확인용 userid => " + userid);
			// >>> 확인용 userid => seoyh

			Map<String, String> paraMap = new HashMap<>();
			paraMap.put("email", email);
			paraMap.put("id", id);

			boolean isEmailDuplicate = mdao.emailDuplicateCheck2(paraMap);

			JSONObject jsonObj = new JSONObject(); // {}
			jsonObj.put("isEmailDuplicate", isEmailDuplicate); // {"isExists":true} 또는 {"isExists":false} 으로 만들어준다.

			String json = jsonObj.toString(); // 문자열 형태인 "{"isExists":true}" 또는 "{"isExists":false}" 으로 만들어준다.
			// System.out.println(">>> 확인용 json => " + json);
			// >>> 확인용 json => {"isExists":true}
			// >>> 확인용 json => {"isExists":false}

			request.setAttribute("json", json);

			// super.setRedirect(false);
			super.setViewPage("/WEB-INF/jsonview.jsp");

		}

	}

}
