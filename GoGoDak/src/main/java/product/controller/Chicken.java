package product.controller;

import common.controller.AbstractController;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import product.model.ProductDao;
import product.model.ProductDao_Imple;

public class Chicken extends AbstractController {
	
	
	private ProductDao productDao;
	
	public Chicken() {
		this.productDao = new ProductDao_Imple();
		
	}

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		
		super.setRedirect(false);
		super.setViewPage("/WEB-INF/view/product/product_chicken.jsp");
		
	}

}
