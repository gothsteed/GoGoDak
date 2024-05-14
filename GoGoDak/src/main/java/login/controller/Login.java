package login.controller;

import java.util.HashMap;
import java.util.Map;

import common.controller.AbstractController;
import domain.MemberVo;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

public class Login extends AbstractController {


	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		String method = request.getMethod();
		
		if(method.equalsIgnoreCase("get")) {
			
			
		}
		else {
			
			
		}

	}

}
