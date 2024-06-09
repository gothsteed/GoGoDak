package product.controller;

import java.util.List;

import common.controller.AbstractController;
import conatainer.annotation.Autowired;
import discountEvent.model.DiscountEventDao;
import discountEvent.model.DiscountEventDao_imple;
import domain.Discount_eventVO;
import domain.ProductVO;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import pager.Pager;
import product.model.ProductDao;
import product.model.ProductDao_Imple;

public class EventProduct extends AbstractController {
	
	private ProductDao productDao;
	private DiscountEventDao discountEventDao;
	
	@Autowired
	public EventProduct(ProductDao productDao, DiscountEventDao discountEventDao) {
		this.productDao = productDao;
		this.discountEventDao = discountEventDao;
	}

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		int discount_event_Seq;
		try {
			discount_event_Seq = Integer.parseInt(request.getParameter("discount_event_seq"));
		}
		catch (NumberFormatException e) {
			super.setRedirect(true);
			super.setViewPage(request.getContextPath() + "/product/event.dk");
			return;
		}
		
		Discount_eventVO discount_eventVO = discountEventDao.getDiscountEventBySeq(discount_event_Seq);
		
		int blockSize = 8;
		int currentPage;
		
		try {
			currentPage = Integer.parseInt(request.getParameter("page"));
		} catch (NumberFormatException e) {
			currentPage = 1;
		}
		
		
		
		Pager<ProductVO> productList = productDao.getProductByDiscountEvent(discount_event_Seq, currentPage, blockSize);
		
		String pageBar = productList.makePageBar("detail.dk", "discount_event_seq=" + discount_event_Seq);

		String title = "<div><img src='" + request.getContextPath() + "/images/special/"+discount_eventVO.getPic()+"' height='500px' /></div>";
		
		request.setAttribute("title", title);
		request.setAttribute("productList", productList.getContent());
		request.setAttribute("pageBar", pageBar);
		super.setRedirect(false);
		super.setViewPage("/WEB-INF/view/product/product_category.jsp");

	}

}
