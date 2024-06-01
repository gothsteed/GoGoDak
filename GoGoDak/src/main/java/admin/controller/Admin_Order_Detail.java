package admin.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import admin.model.AdminDAO;
import admin.model.AdminDAO_imple;
import common.controller.AbstractController;
import domain.MemberVO;
import domain.OrderVO;
import domain.ProductVO;
import domain.Product_listVO;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import my.util.MyUtil;
import order.model.OrderDao;
import order.model.OrderDao_imple;
import product.model.ProductDao;
import product.model.ProductDao_Imple;

public class Admin_Order_Detail extends AbstractController {
	
	private OrderDao orderDao;
	
	public Admin_Order_Detail() {
		orderDao = new OrderDao_imple();
	}
	
	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
	    HttpSession session = request.getSession();
	    MemberVO loginuser = (MemberVO)session.getAttribute("loginuser");

	    if (loginuser != null) {
	        String method = request.getMethod();
	        
	        if ("POST".equalsIgnoreCase(method)) {
	            int order_seq;
	            
	            try {
	            	order_seq = Integer.parseInt(request.getParameter("order_seq"));
	            }catch (NumberFormatException e) {
	    	        String message = "잘못된 접근.";
	    	        String loc = "javascript:history.back()";

	    	        request.setAttribute("message", message);
	    	        request.setAttribute("loc", loc);

	    	        super.setRedirect(false);
	    	        super.setViewPage("/WEB-INF/view/msg.jsp");
	    	        return;
				}
	            
	            String goBackURL = request.getParameter("goBackURL");

	            OrderVO order = orderDao.getOrderWithMember(order_seq);
	            List<ProductVO> productList = orderDao.getProductList(order_seq);

	            request.setAttribute("order", order); // 요청 객체에 데이터 설정
	            request.setAttribute("productList", productList);
	            request.setAttribute("goBackURL", goBackURL);

	            super.setRedirect(false);
	            super.setViewPage("/WEB-INF/view/member/member_orderdetail.jsp");
	        }
	    } else {
	        String message = "로그인 하셔야합니다.";
	        String loc = "javascript:history.back()";

	        request.setAttribute("message", message);
	        request.setAttribute("loc", loc);

	        super.setRedirect(false);
	        super.setViewPage("/WEB-INF/view/msg.jsp");
	    }
	}

	
}
