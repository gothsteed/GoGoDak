package common.controller;

import java.util.List;

import discountEvent.model.DiscountEventDao;
import discountEvent.model.DiscountEventDao_imple;
import domain.Discount_eventVO;
import domain.ProductVO;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import product.model.ProductDao;
import product.model.ProductDao_Imple;

public class IndexController extends AbstractController {
	private DiscountEventDao discountEventDao;
	private ProductDao productDao;


	public IndexController() {
		discountEventDao = new DiscountEventDao_imple();
		productDao = new ProductDao_Imple();
	}

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		
		List<Discount_eventVO> eventList = discountEventDao.getAllDiscountEvent();
		request.setAttribute("eventList", eventList);
		
		List<ProductVO> discountProductList = productDao.getDiscountProduct();
		request.setAttribute("discountProductList", discountProductList);
		
		
		List<ProductVO> recentProduct = productDao.getRecentProduct();
		request.setAttribute("recentProduct", recentProduct);
		
		
		super.setRedirect(false);
		super.setViewPage("/WEB-INF/view/index.jsp");
	
	}

}
