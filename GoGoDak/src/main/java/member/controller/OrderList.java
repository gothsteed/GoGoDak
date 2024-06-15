package member.controller;

import java.time.LocalDate;
import java.time.format.DateTimeParseException;
import java.util.List;

import common.controller.AbstractController;
import conatainer.annotation.Autowired;
import domain.MemberVO;
import domain.OrderVO;
import domain.ProductVO;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import order.model.OrderDao;
import order.model.OrderDao_imple;
import pager.Pager;

public class OrderList extends AbstractController {

    private OrderDao orderDao;
    
    @Autowired
    public OrderList(OrderDao orderDao) {
        this.orderDao =orderDao;
    }

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws Exception {

        HttpSession session = request.getSession();
        MemberVO loginuser = (MemberVO) session.getAttribute("loginuser");

        if (loginuser != null) {
        	// 사용자가 로그인한 경우
            
        	int member_seq = loginuser.getMember_seq();
        	
    		int currentPage;
    		int sizePerPage;
    		
    		try {
    			currentPage = Integer.parseInt(request.getParameter("page"));
    		} catch (NumberFormatException e) {
    			currentPage = 1;
    		}
    		
    		try {
    			sizePerPage = Integer.parseInt(request.getParameter("sizePerPage"));
			} catch (NumberFormatException e) {
				sizePerPage = 10;
			}
    		
            String startDateStr = request.getParameter("startDate");
            String endDateStr = request.getParameter("endDate");
            
            LocalDate startDate = null;
            LocalDate endDate = null;
            
            try {
            	startDate = LocalDate.parse(startDateStr);
            }catch (Exception e) {
            	startDate = null;
			}
            
            try {
            	endDate = LocalDate.parse(endDateStr);
            }catch (Exception e) {
            	endDate = null;
			}
        	
        	
        	Pager<OrderVO> orderList = orderDao.getLoginuserList(member_seq, currentPage, sizePerPage, startDate, endDate);
       
        	request.setAttribute("orderList", orderList.getContent());
        	request.setAttribute("pageBar", orderList.makePageBar("orderList.dk", "startDate="+startDateStr, 
        			"endDate="+endDateStr, "sizePerPage="+sizePerPage));
        	
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

