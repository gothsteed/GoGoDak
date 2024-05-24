package admin.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import admin.model.AdminDAO;
import admin.model.AdminDAO_imple;
import common.controller.AbstractController;
import domain.MemberVO;
import domain.OrderVO;
import domain.Product_listVO;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import my.util.MyUtil;
import product.model.ProductDao;
import product.model.ProductDao_Imple;

public class Admin_Order_Detail extends AbstractController {
	
	private ProductDao pdo = null;
	
	public Admin_Order_Detail() {
		pdo = new ProductDao_Imple();
	}
	
	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
	    HttpSession session = request.getSession();
	    MemberVO loginuser = (MemberVO)session.getAttribute("loginuser");

	    if (loginuser != null && "admin".equals(loginuser.getId())) {
	        String method = request.getMethod();
	        if ("POST".equalsIgnoreCase(method)) {
	            String id = request.getParameter("id");
	            String goBackURL = request.getParameter("goBackURL");

	            Product_listVO pvo = pdo.memberOrderDetail(id);

	            request.setAttribute("pvo", pvo); // 요청 객체에 데이터 설정
	            request.setAttribute("goBackURL", goBackURL);

	            super.setRedirect(false);
	            super.setViewPage("/WEB-INF/view/member/member_orderdetail.jsp");
	        }
	    } else {
	        String message = "관리자만 접근 가능한 페이지입니다.";
	        String loc = "javascript:history.back()";

	        request.setAttribute("message", message);
	        request.setAttribute("loc", loc);

	        super.setRedirect(false);
	        super.setViewPage("/WEB-INF/msg.jsp");
	    }
	}

	
}
