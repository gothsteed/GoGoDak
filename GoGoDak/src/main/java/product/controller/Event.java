package product.controller;

import java.util.List;

import common.controller.AbstractController;
import conatainer.annotation.Autowired;
import discountEvent.model.DiscountEventDao;
import discountEvent.model.DiscountEventDao_imple;
import domain.Discount_eventVO;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import pager.Pager;

public class Event extends AbstractController {
	private DiscountEventDao discountEventDao;
	
	@Autowired
	public Event(DiscountEventDao discountEventDao) {
		this.discountEventDao = discountEventDao;
	}
	

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		
		
		int blockSize = 6;
		int currentPage;
		
		try {
			currentPage = Integer.parseInt(request.getParameter("page"));
		} catch (NumberFormatException e) {
			currentPage = 1;
		}
		
		
		Pager<Discount_eventVO> discount_event_list = discountEventDao.getAllDiscountEvent(currentPage, blockSize);
		
		

		
		request.setAttribute("discount_event_list", discount_event_list.getContent());
		request.setAttribute("pageBar", discount_event_list.makePageBar("event.dk", new String[] {}));
		super.setRedirect(false);
		super.setViewPage("/WEB-INF/view/product/product_special.jsp");

	}

}
