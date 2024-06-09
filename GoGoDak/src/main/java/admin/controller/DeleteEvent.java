package admin.controller;

import common.controller.AbstractController;
import conatainer.annotation.Autowired;
import discountEvent.model.DiscountEventDao;
import discountEvent.model.DiscountEventDao_imple;
import domain.MemberVO;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import product.model.ProductDao;
import product.model.ProductDao_Imple;

public class DeleteEvent extends AbstractController {
	
	//private ProductDao productDao;
	private DiscountEventDao discountEventDao;
	
	
	@Autowired
	public DeleteEvent(DiscountEventDao discountEventDao) {
		this.discountEventDao = discountEventDao;
	}
	
	private void sendMsg(HttpServletRequest request, String message, String loc) {
        request.setAttribute("message", message);
        request.setAttribute("loc", loc);
         
        super.setRedirect(false);
        super.setViewPage("/WEB-INF/view/msg.jsp");
	}
	
	

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		String method = request.getMethod();
		
		HttpSession session = request.getSession();
		MemberVO loginuser = (MemberVO)session.getAttribute("loginuser");
		
		if(loginuser == null || !loginuser.getId().equals("admin")) {
			String message = "관리자만 접근이 가능합니다.";
	        String loc = "javascript:history.back()";
	         
	        sendMsg(request, message, loc);
	        return;
		}
		
		
		if(method.equalsIgnoreCase("get")) {
			String message = "잘못된 접근 방식입니다.";
	        String loc = "javascript:history.back()";
	         
	        sendMsg(request, message, loc);
	        return;
		}
		
		int discount_event_seq;
		try {
			discount_event_seq = Integer.parseInt(request.getParameter("discount_event_seq"));
		} catch (NumberFormatException e) {
			String message = "잘못된 접근 방식입니다.";
	        String loc = "javascript:history.back()";
	         
	        sendMsg(request, message, loc);
	        return;
		}
		
		int result = discountEventDao.deleteDiscountEvent(discount_event_seq);
		
		if(result != 1) {
			String message = "행사 삭제 실패";
	        String loc = "javascript:history.back()";
	         
	        sendMsg(request, message, loc);
	        return;
		}
		
		String message = "행사 삭제 성공.";
        String loc = "javascript:history.back()";
         
        sendMsg(request, message, loc);	
	}

}
