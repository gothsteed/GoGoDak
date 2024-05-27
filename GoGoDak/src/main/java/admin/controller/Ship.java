package admin.controller;

import org.json.JSONObject;

import common.controller.AbstractController;
import domain.MemberVO;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import oracle.net.aso.j;
import order.model.OrderDao;
import order.model.OrderDao_imple;

public class Ship extends AbstractController {
	
	private OrderDao orderDao;
	
	public Ship() {
		orderDao = new OrderDao_imple();
	}
	
	private void sendJson(HttpServletRequest request, boolean isSuccess, String message) {
		JSONObject jsonObject = new JSONObject();
		jsonObject.append("success", isSuccess);
		jsonObject.append("message", message);
		
		String json = jsonObject.toString();
		
		request.setAttribute("json", json);
		super.setRedirect(false);
		super.setViewPage("/WEB-INF/jsonview.jsp");
		
	}

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		HttpSession session = request.getSession();
		MemberVO loginuser = (MemberVO)session.getAttribute("loginuser");
		
		if(loginuser == null || !loginuser.getId().equals("admin")) {
			boolean isSuccess = false;
			String message = "관리자만 접근이 가능합니다.";
	         
	    	sendJson(request, isSuccess, message);
			return;
		}
		
		String method = request.getMethod();
		if(!method.equalsIgnoreCase("post")) {
			boolean isSuccess = false;
			String message = "잘못된 접근";
			
			sendJson(request, isSuccess, message);
			return;
		}
		
		int order_seq;
		try {
			order_seq = Integer.parseInt(request.getParameter("order_seq"));
		} catch (NumberFormatException e) {
			boolean isSuccess = false;
			String message = "잘못된 접근";
			
			sendJson(request, isSuccess, message);
			return;
		}
		
		
		int result = orderDao.updateDelivery_status(order_seq);
		
		if(result != 1) {
			boolean isSuccess = false;
			String message = "실패";
			
			sendJson(request, isSuccess, message);
			return;
			
		}
		
		boolean isSuccess = false;
		String message = "성공";
		
		sendJson(request, isSuccess, message);
		return;
		

	}

}
