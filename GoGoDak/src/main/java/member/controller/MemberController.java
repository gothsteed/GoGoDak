package member.controller;

import common.controller.AbstractController;
import domain.MemberOrderStat;
import domain.MemberVO;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import member.model.MemberDao;
import member.model.MemberDao_Imple;
import order.model.OrderDao;
import order.model.OrderDao_imple;

public class MemberController extends AbstractController {

	private MemberDao memberDao = null;
	private OrderDao orderDao;

	public MemberController() {
		this.memberDao = new MemberDao_Imple();
		this.orderDao = new OrderDao_imple();
	}
	
	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		if(!super.checkLogin(request)) {
	        String message = "로그인 하시오.";
	        String loc = "javascript:history.back()";

	        request.setAttribute("message", message);
	        request.setAttribute("loc", loc);

	        super.setRedirect(false);
	        super.setViewPage("/WEB-INF/view/msg.jsp");
	        return;
			
		}
		
		
		HttpSession session = request.getSession();
		MemberVO loginuser = (MemberVO) session.getAttribute("loginuser");
		
		MemberOrderStat orderStat = orderDao.getOrderStat(loginuser.getMember_seq());
		request.setAttribute("orderStat", orderStat);
		
		super.setRedirect(false);
		super.setViewPage("/WEB-INF/view/member/member_myshop.jsp");
		
	} 

}
