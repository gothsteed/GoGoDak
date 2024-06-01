package member.controller;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

import org.json.JSONObject;

import common.controller.AbstractController;
import domain.MemberVO;
import domain.ProductVO;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import login.controller.GoogleMail;
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
		System.out.println("Adsfasfasdf");
		
		
		String method = request.getMethod();
		if(method.equalsIgnoreCase("get")) {
			System.out.println("get으로 들어옴");
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
			System.out.println("로그인 안됨");
		    JSONObject jsonResponse = new JSONObject();
		    jsonResponse.put("success", false);
		    jsonResponse.put("message", "로그인 하시오");
		    jsonResponse.put("loc", "javascript:history.back()");
		    setRedirect(false);
	        request.setAttribute("json", jsonResponse.toString());
	        setViewPage("/WEB-INF/jsonview.jsp");
			return;
		}

		int point;

		try {
			point = Integer.parseInt(request.getParameter("point"));

		} catch (NumberFormatException e) {
			point = 0;
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
		
		if(point < 0 || loginuser.getPoint() < point) {
			System.out.println("!!!주문 실패!!!");
		    JSONObject jsonResponse = new JSONObject();
		    jsonResponse.put("success", false);
		    jsonResponse.put("message", "주문 실패!");
		    jsonResponse.put("loc", "javascript:history.back()");
		    setRedirect(false);
	        request.setAttribute("json", jsonResponse.toString());
	        setViewPage("/WEB-INF/jsonview.jsp");
			return;
		}
		int[] result = orderDao.insertOrder(loginuser.getMember_seq(), postcode, address, address_detail, address_extra,  delivery_message, totalAmount, cart );
	
		int pointResult = 0;
		if(point == 0) {
			pointResult =memberDao.updatePoint(loginuser.getPoint() + (int) Math.ceil(totalAmount* 0.05) , loginuser.getMember_seq());
			loginuser.setPoint(loginuser.getPoint() + (int) Math.ceil(totalAmount* 0.05));
		}
		else {
			pointResult = memberDao.updatePoint(loginuser.getPoint() + - point , loginuser.getMember_seq());
			loginuser.setPoint(loginuser.getPoint() - point );
		}
		System.out.println(pointResult);
		
		
		//int pointResult = memberDao.updatePoint(loginuser.getPoint() + (int)Math.round(totalAmount * 0.05), loginuser.getMember_seq());
		
		if(result[0] * pointResult != 1) {
			System.out.println("!!!주문 실패!!!");
		    JSONObject jsonResponse = new JSONObject();
		    jsonResponse.put("success", false);
		    jsonResponse.put("message", "주문 실패!");
		    jsonResponse.put("loc", "javascript:history.back()");
		    setRedirect(false);
	        request.setAttribute("json", jsonResponse.toString());
	        setViewPage("/WEB-INF/jsonview.jsp");
			return;
		}
		
		GoogleMail mail = new GoogleMail();
		
		StringBuilder sb = new StringBuilder();
		sb.append("주문코드번호 : <span style='color: blue; font-weight: bold;'>").append(result[1]).append("</span><br/><br/>");
		sb.append("<주문상품><br/>");

		for (ProductVO product : cart.keySet()) {
		    sb.append(product.getProduct_name()).append("    ").append(cart.get(product)).append("개");
		    sb.append("<img src='http://127.0.0.1:9090").append(request.getContextPath()).append("/images/product/").append(product.getMain_pic()).append("' />");
		    sb.append("<br>");
		}

		sb.append("<br> 이용해주셔서 감사합니다");

		
		mail.sendMailOrderFinish(loginuser.getEmail(), loginuser.getName(), sb.toString());
		
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
