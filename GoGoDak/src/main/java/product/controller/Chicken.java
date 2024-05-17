package product.controller;

import java.util.List;

import common.controller.AbstractController;
import domain.ProductVO;
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
		
		
		List<ProductVO> productList = productDao.getProductByType(1);
		
		request.setAttribute("ProductList", productList);
		
		super.setRedirect(false);
		super.setViewPage("/WEB-INF/view/product/product_chicken.jsp");
		
	}

}
