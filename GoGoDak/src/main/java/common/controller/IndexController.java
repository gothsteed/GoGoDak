package common.controller;

import java.util.List;

import discountEvent.model.DiscountEventDao;
import discountEvent.model.DiscountEventDao_imple;
import domain.Discount_eventVO;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public class IndexController extends AbstractController {
	private DiscountEventDao discountEventDao;

	public IndexController() {
		discountEventDao = new DiscountEventDao_imple();
	}

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		
		List<Discount_eventVO> eventList = discountEventDao.getAllDiscountEvent();
		request.setAttribute("eventList", eventList);
		
		
		super.setRedirect(false);
		super.setViewPage("/WEB-INF/view/index.jsp");
	
	}

}
