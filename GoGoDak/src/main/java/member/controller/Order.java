package member.controller;

import org.json.JSONObject;

import common.controller.AbstractController;
import domain.MemberVO;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import order.model.OrderDao;
import order.model.OrderDao_imple;

public class Order extends AbstractController {
	
	
	private OrderDao orderDao;
	
	public Order() {
		this.orderDao = new OrderDao_imple();
		
	}

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		String method = request.getMethod();
		if(method.equalsIgnoreCase("get")) {
		    JSONObject jsonResponse = new JSONObject();
		    jsonResponse.put("success", false);
		    jsonResponse.put("message", "잘못된 방식");
		    jsonResponse.put("loc", request.getContextPath() + "javascript:history.back()");
		    setRedirect(false);
	        request.setAttribute("json", jsonResponse.toString());
	        setViewPage("/WEB-INF/jsonview.jsp");
			return;
			
		}
		
		
		if(!super.checkLogin(request)) {
		    JSONObject jsonResponse = new JSONObject();
		    jsonResponse.put("success", false);
		    jsonResponse.put("message", "로그인 하시오");
		    jsonResponse.put("loc", request.getContextPath() + "javascript:history.back()");
		    setRedirect(false);
	        request.setAttribute("json", jsonResponse.toString());
	        setViewPage("/WEB-INF/jsonview.jsp");
			return;
		}
		
		
		int totalAmount = Integer.parseInt(request.getParameter("totalAmount"));
		String postcode = request.getParameter("postcode");
		String address = request.getParameter("address");
		String address_detail = request.getParameter("address_detail");
		String address_extra = request.getParameter("address_extra");
		
		
		HttpSession session = request.getSession();
		MemberVO loginuser = (MemberVO) session.getAttribute("loginuser");
		
		int result = orderDao.insertOrder(loginuser.getMember_seq(), postcode, address, address_detail, address_extra, totalAmount);
		
		
		if(result != 1) {
		    JSONObject jsonResponse = new JSONObject();
		    jsonResponse.put("success", false);
		    jsonResponse.put("message", "주문 실패!");
		    jsonResponse.put("loc", request.getContextPath() + "javascript:history.back()");
		    setRedirect(false);
	        request.setAttribute("json", jsonResponse.toString());
	        setViewPage("/WEB-INF/jsonview.jsp");
			return;
		}
		
	    JSONObject jsonResponse = new JSONObject();
	    jsonResponse.put("success", true);
	    jsonResponse.put("message", "주문성공!");
	    jsonResponse.put("loc", request.getContextPath() + "/index.dk");
	    setRedirect(false);
        request.setAttribute("json", jsonResponse.toString());
        setViewPage("/WEB-INF/jsonview.jsp");
	}

}
