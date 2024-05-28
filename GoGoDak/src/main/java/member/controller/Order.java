package member.controller;

import java.util.Map;

import org.json.JSONObject;

import common.controller.AbstractController;
import domain.MemberVO;
import domain.ProductVO;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import member.model.MemberDao;
import member.model.MemberDao_Imple;
import order.model.OrderDao;
import order.model.OrderDao_imple;

public class Order extends AbstractController {
	
	private MemberDao memberDao;
	private OrderDao orderDao;
	
	public Order() {
		this.orderDao = new OrderDao_imple();
		this.memberDao = new MemberDao_Imple();
		
	}

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		String method = request.getMethod();
		if(method.equalsIgnoreCase("get")) {
		    JSONObject jsonResponse = new JSONObject();
		    jsonResponse.put("success", false);
		    jsonResponse.put("message", "잘못된 방식");
		    jsonResponse.put("loc", "javascript:history.back()");
		    setRedirect(false);
	        request.setAttribute("json", jsonResponse.toString());
	        setViewPage("/WEB-INF/jsonview.jsp");
			return;
			
		}
		
		
		if(!super.checkLogin(request)) {
		    JSONObject jsonResponse = new JSONObject();
		    jsonResponse.put("success", false);
		    jsonResponse.put("message", "로그인 하시오");
		    jsonResponse.put("loc", "javascript:history.back()");
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
		String delivery_message = request.getParameter("delivery_message");
		
		
		HttpSession session = request.getSession();
		MemberVO loginuser = (MemberVO) session.getAttribute("loginuser");
		Map<ProductVO, Integer> cart = (Map<ProductVO, Integer>) session.getAttribute("cart");
		
		int result = orderDao.insertOrder(loginuser.getMember_seq(), postcode, address, address_detail, address_extra,  delivery_message, totalAmount, cart );
		//int pointResult = memberDao.updatePoint(loginuser.getPoint() + (int)Math.round(totalAmount * 0.05), loginuser.getMember_seq());
		
		if(result != 1) {
		    JSONObject jsonResponse = new JSONObject();
		    jsonResponse.put("success", false);
		    jsonResponse.put("message", "주문 실패!");
		    jsonResponse.put("loc", "javascript:history.back()");
		    setRedirect(false);
	        request.setAttribute("json", jsonResponse.toString());
	        setViewPage("/WEB-INF/jsonview.jsp");
			return;
		}
		
		session.removeAttribute("cart");
		
	    JSONObject jsonResponse = new JSONObject();
	    jsonResponse.put("success", true);
	    jsonResponse.put("message", "주문성공!");
	    jsonResponse.put("loc", request.getContextPath() + "/index.dk");
	    setRedirect(false);
        request.setAttribute("json", jsonResponse.toString());
        setViewPage("/WEB-INF/jsonview.jsp");
	}

}
