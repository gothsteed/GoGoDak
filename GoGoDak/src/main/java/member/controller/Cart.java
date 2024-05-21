package member.controller;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.json.JSONObject;

import common.controller.AbstractController;
import domain.MemberVO;
import domain.ProductVO;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import member.model.MemberDao;
import member.model.MemberDao_Imple;
import product.model.ProductDao;
import product.model.ProductDao_Imple;

public class Cart extends AbstractController {

	private ProductDao productDao;

	public Cart() {
		productDao = new ProductDao_Imple();
	}
	
	
	
	private void postMethod(HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		String product_seqString = request.getParameter("product_seq");
		
		JSONObject jsonResponse = new JSONObject();
		
		int product_seq = -1;
		try {
			product_seq = Integer.parseInt(product_seqString);
			
		}catch (NumberFormatException e) {
	        jsonResponse.put("success", false);
	        jsonResponse.put("message", "Invalid product sequence.");
	        response.setContentType("application/json");
	        response.getWriter().write(jsonResponse.toString());
			return;
		}
		
		
		ProductVO product = productDao.getProductBySeq(product_seq);
		
		if(product == null) {
	        jsonResponse.put("success", false);
	        jsonResponse.put("message", "Invalid product sequence.");
	        response.setContentType("application/json");
	        response.getWriter().write(jsonResponse.toString());
			return;
		}
		
		
		HttpSession session =request.getSession();
		List<ProductVO> productList =  (List<ProductVO>)session.getAttribute("cart");
		
		productList.add(product);
		
        jsonResponse.put("success", true);
        jsonResponse.put("message", "added to cart");
        response.setContentType("application/json");
        response.getWriter().write(jsonResponse.toString());
	}

	
	
	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		HttpSession session =request.getSession();
		
		String method = request.getMethod();
		if(method.equalsIgnoreCase("post")) {
			postMethod(request, response);
			return;
		}
	
		MemberVO loginuser =(MemberVO)session.getAttribute("loginuser");
		
		
		// 사용자가 로그인했는지 확인
        if (loginuser == null) {
            super.setRedirect(true);
            super.setViewPage("/WEB-INF/view/member/member_Login.jsp");
            return;
        }
		
		 // 세션에서 장바구니 정보 가져오기
		List<ProductVO> productList = (List<ProductVO>)session.getAttribute("cart");
        
        // 장바구니가 없을 경우 빈 장바구니 생성
        if (productList == null) {
        	productList = new ArrayList<>();
        }
        
        // 장바구니 정보를 JSP로 전달
        request.setAttribute("cart", productList);
     
        
    	super.setRedirect(false);
		super.setViewPage("/WEB-INF/view/member/cart.jsp");


	}

}
