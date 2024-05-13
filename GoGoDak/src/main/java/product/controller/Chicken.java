package product.controller;

import common.controller.AbstractController;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public class Chicken extends AbstractController {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		
		super.setRedirect(false);
		super.setViewPage("/WEB-INF/view/product/product_chicken.jsp");
		
	}

}
