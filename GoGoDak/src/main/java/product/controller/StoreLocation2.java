package product.controller;

import common.controller.AbstractController;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public class StoreLocation2 extends AbstractController {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
	    // super.setRedirect(false);
	       super.setViewPage("/WEB-INF/view/product/storeLocation2.jsp");


	}

}