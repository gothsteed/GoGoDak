package member.controller;

import java.util.List;

import common.controller.AbstractController;
import domain.MemberVO;
import domain.OrderVO;
import domain.ProductVO;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import order.model.OrderDao;
import order.model.OrderDao_imple;

public class OrderList extends AbstractController {

    private OrderDao orderDao;
    
    public OrderList() {
        orderDao = new OrderDao_imple();
    }

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws Exception {

        HttpSession session = request.getSession();
        MemberVO loginuser = (MemberVO) session.getAttribute("loginuser");

        if (loginuser != null) {
        	// 사용자가 로그인한 경우
            
        	int member_seq = loginuser.getMember_seq();
        	
        	List<OrderVO> orderList = orderDao.getLoginuserList(member_seq);
       
        	request.setAttribute("orderList", orderList);
        	
        	super.setRedirect(false);
            super.setViewPage("/WEB-INF/view/member/orderList.jsp");
     
        }
        else {
        	// 사용자가 로그인을 안한 경우
        	String message = "로그인 하셔야 합니다.";
            String loc = "javascript:history.back()";

            request.setAttribute("message", message);
            request.setAttribute("loc", loc);

            super.setRedirect(false);
            super.setViewPage("/WEB-INF/view/msg.jsp");       	
        }
    
    }
}

