package member.controller;

import java.util.Map;
import java.util.Set;

import common.controller.AbstractController;
import domain.MemberVO;
import domain.ProductVO;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import member.model.MemberDao;
import member.model.MemberDao_Imple;

public class Purchase extends AbstractController {
	
	
	
	private void sendError(HttpServletRequest request, String message, String loc) {

		request.setAttribute("message", message);
		request.setAttribute("loc", loc);

		super.setRedirect(false);
		super.setViewPage("/WEB-INF/view/msg.jsp");
		return;
	}
	
	private int getFlooredTotalAmount(Map<ProductVO, Integer> cart) {
		float total = 0;
		
		Set<ProductVO> productSet = cart.keySet();
		
		for(ProductVO productVO : productSet) {
			total += productVO.getDiscountPrice() * cart.get(productVO);
		}
		
		return (int) Math.floor(total);
	}

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws Exception {

		if (!super.checkLogin(request)) {
			// 로그인을 안했으면
			String message = "결제를 하기 위해서는 먼저 로그인을 하세요!!";
			String loc = request.getContextPath() + "/login/login.dk";

			sendError(request, message, loc);
			return;
		}
		

		HttpSession session = request.getSession();
		MemberVO loginuser = (MemberVO) session.getAttribute("loginuser");

		int point;

		try {
			point = Integer.parseInt(request.getParameter("point"));

		} catch (NumberFormatException e) {
			point = 0;
		}
		
		if(point > loginuser.getPoint()) {
			String message = "포인트 초과 입니다";
			String loc = request.getContextPath() + "";

			sendError(request, message, loc);
			return;
		}

		
		/*
		 * String productName = "새우깡"; request.setAttribute("productName", productName);
		 * 
		 * int coinMoney = 10; request.setAttribute("coinMoney", coinMoney);
		 * 
		 * String email = logiUser.getEmail(); request.setAttribute("email", email);
		 * 
		 * String name = logiUser.getName(); request.setAttribute("name", name);
		 * 
		 * String mobile = logiUser.getMobile(); request.setAttribute("moblie", mobile);
		 * 
		 * request.setAttribute("userid", userId);
		 */
		
		Map<ProductVO, Integer> cart =  (Map<ProductVO, Integer>) session.getAttribute("cart");
		int totalAmount = getFlooredTotalAmount(cart);
		request.setAttribute("totalAmount", totalAmount - point);
		
		System.out.println("using point : " + point);
		String postcode = request.getParameter("postcode");
		String  address =  request.getParameter("address");
		String address_detail = request.getParameter("address_detail");
		String address_extra =  request.getParameter("address_extra");
		
		
//		int result = 0;
//		if(point == 0) {
//			result =memberDao.updatePoint(loginuser.getPoint() + (int) Math.ceil(totalAmount* 0.05) , loginuser.getMember_seq());
//			loginuser.setPoint(loginuser.getPoint() + (int) Math.ceil(totalAmount* 0.05));
//		}
//		else {
//			result = memberDao.updatePoint(loginuser.getPoint() + - point , loginuser.getMember_seq());
//			loginuser.setPoint(loginuser.getPoint() - point );
//		}
//		

//		
//		
//		if(result != 1) {
//			String message = "포인트 사용 실패";
//			String loc = request.getContextPath() + "";
//			sendError(request, message, loc);
//			return;
//		}
		
		
		
		request.setAttribute("point", point);
		request.setAttribute("postcode", postcode);
		request.setAttribute("address", address);
		request.setAttribute("address_detail", address_detail);
		request.setAttribute("address_extra", address_extra);
		
		
		super.setRedirect(false);
		super.setViewPage("/WEB-INF/view/member/paymentGateway.jsp");

	}

}
