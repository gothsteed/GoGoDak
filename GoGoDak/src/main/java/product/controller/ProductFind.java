package product.controller;

import common.controller.AbstractController;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import product.model.ProductDao;
import product.model.ProductDao_Imple;

public class ProductFind extends AbstractController {

	private ProductDao pdao = null;
	
	public ProductFind() {
		pdao = new ProductDao_Imple();
	}
	
	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		String productName = request.getParameter("myFood");
		System.out.println(productName);

	}

}
