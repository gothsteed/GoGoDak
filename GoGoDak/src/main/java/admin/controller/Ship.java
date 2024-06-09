package admin.controller;

import org.json.JSONObject;

import common.controller.AbstractController;
import conatainer.annotation.Autowired;
import domain.MemberVO;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import oracle.net.aso.j;
import order.model.OrderDao;
import order.model.OrderDao_imple;

public class Ship extends AbstractController {
	
	private OrderDao orderDao;
	
	@Autowired
	public Ship(OrderDao orderDao) {
		this.orderDao = orderDao;
	}
	
	private void sendJson(HttpServletRequest request, boolean isSuccess, String message, String newStatus) {
		JSONObject jsonObject = new JSONObject();

		jsonObject.put("success", isSuccess);
		jsonObject.put("message", message);
		jsonObject.put("newStatus", newStatus);
		
		String json = jsonObject.toString();
		
		request.setAttribute("json", json);
		super.setRedirect(false);
		super.setViewPage("/WEB-INF/jsonview.jsp");
		
	}
	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
	    HttpSession session = request.getSession();
	    MemberVO loginuser = (MemberVO) session.getAttribute("loginuser");

	    if (loginuser == null || !loginuser.getId().equals("admin")) {
	        boolean isSuccess = false;
	        String message = "관리자만 접근이 가능합니다.";
	        sendJson(request, isSuccess, message, "");
	        return;
	    }

	    String method = request.getMethod();
	    System.out.println("Request method: " + method);

	    if (!method.equalsIgnoreCase("post")) {
	        boolean isSuccess = false;
	        String message = "잘못된 접근";
	        sendJson(request, isSuccess, message, "");
	        return;
	    }

	    int order_seq;
	    try {
	        order_seq = Integer.parseInt(request.getParameter("order_seq"));
	        System.out.println("Order seq: " + order_seq);
	    } catch (NumberFormatException e) {
	        boolean isSuccess = false;
	        String message = "잘못된 접근";
	        sendJson(request, isSuccess, message, "");
	        return;
	    }

	    int status;
	    try {
	        status = Integer.parseInt(request.getParameter("deliverystatus"));
	        System.out.println("Delivery status: " + status);
	    } catch (NumberFormatException e) {
	        boolean isSuccess = false;
	        String message = "잘못된 접근";
	        sendJson(request, isSuccess, message, "");
	        return;
	    }

	    int result = orderDao.updateDelivery_status(order_seq, status);
	//    System.out.println("Update result: " + result);

	    if (result != 1) {
	        boolean isSuccess = false;
	        String message = "실패";
	        sendJson(request, isSuccess, message, "");
	        return;
	    }

	    boolean isSuccess = true;
	    String message = "성공";
	    String newStatus = status == 0 ? "미출고" : status == 1 ? "출고" : status == 2 ? "배송중" : "배송완료";

	    sendJson(request, isSuccess, message, newStatus);
	}



}
