package member.controller;

import java.util.List;

import javax.mail.Message;

import org.json.JSONObject;

import common.controller.AbstractController;
import domain.MemberVO;
import domain.OrderVO;
import domain.ProductVO;
import domain.ReviewVO;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import order.model.OrderDao;
import order.model.OrderDao_imple;
import product.model.ProductDao;
import review.model.ReviewDao;
import review.model.ReviewDao_imple;

public class ReviewCheck extends AbstractController {
	
	private ReviewDao reviewDao;
	private OrderDao orderDao;
	
	public ReviewCheck() {
		reviewDao = new ReviewDao_imple();
		orderDao = new OrderDao_imple();
	}
	
	
	private void sendMsg(HttpServletRequest request, boolean isSuccess, String message) {
        System.out.println(message);
        JSONObject jsonResponse = new JSONObject();
        jsonResponse.put("success", isSuccess);
        jsonResponse.put("message",message);

        System.out.println(jsonResponse.toString());
        setRedirect(false);
        request.setAttribute("json", jsonResponse.toString());
        setViewPage("/WEB-INF/jsonview.jsp");
        return;
	}

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		String method = request.getMethod();
		if(!method.equalsIgnoreCase("post")) {
			boolean isSuccess = false;
			String message = "get 접근";
			sendMsg(request, isSuccess, message);
			return;
		}
		
		int order_seq;
		try {
			System.out.println(request.getParameter("order_seq"));
			order_seq = Integer.parseInt(request.getParameter("order_seq"));
		} catch (NumberFormatException e) {
			boolean isSuccess = false;
			String message = "order_Seq 숫자 아님";
			sendMsg(request, isSuccess, message);
			return;
		}
		
		int product_seq;
		try {
			System.out.println(request.getParameter("product_seq"));
			product_seq = Integer.parseInt(request.getParameter("product_seq"));
		} catch (NumberFormatException e) {
			boolean isSuccess = false;
			String message = "product_seq 숫자 아님";
			sendMsg(request, isSuccess, message);
			return;
		}
		
	    HttpSession session = request.getSession();
	    MemberVO loginuser = (MemberVO)session.getAttribute("loginuser");
		
		
		OrderVO order = orderDao.getOrderBySeq(order_seq);
		if(order.getFk_member_seq() != loginuser.getMember_seq() || order.getDeliverystatus() != 3) {
			boolean isSuccess = false;
			String message = "존재하지 않는 주문";
			sendMsg(request, isSuccess, message);
			return;
		}
		
		
		
		List<ProductVO> productList =orderDao.getProductList(order_seq);
		boolean doExist = false;
		for(ProductVO product :productList) {
			if(product.getProduct_seq() == product_seq) {
				doExist = true;
				break;
			}
		}
		
		if(!doExist) {
			boolean isSuccess = false;
			String message = "주문하지 않는 상품";
			sendMsg(request, isSuccess, message);
			return;
		}
		
		ReviewVO myReview = reviewDao.getReview(loginuser.getMember_seq(), order_seq, product_seq);
		
		if(myReview != null) {
			boolean isSuccess = false;
            JSONObject reviewJson = myReview.toJSONObject();
         
			String message = reviewJson.toString();
			sendMsg(request, isSuccess, message);
			return;
		}
		
		boolean isSuccess = true;
		String message = "리뷰작성가능.";
		sendMsg(request, isSuccess, message);
	}

}
