package product.controller;

import java.util.List;

import common.controller.AbstractController;
import domain.ProductVO;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import product.model.ProductDao;
import product.model.ProductDao_Imple;

public class ProductController extends AbstractController {
	
	private ProductDao productDao;
	
	public ProductController() {
		this.productDao = new ProductDao_Imple();
		
	}
	

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		String productTypeString = request.getParameter("type");
		
		int productType;
		

		if(productTypeString.equalsIgnoreCase("fried_rice")) {
			
			productType = 2;
		} 
		else if(productTypeString.equalsIgnoreCase("bakery")) {
			
			productType = 3;
		} 
		else if(productTypeString.equalsIgnoreCase("dessert")) {
			productType = 4;
		}
		else {
			productType = 1;
			productTypeString = "chicken";
		}
		
		List<ProductVO> productList = productDao.getProductByType(productType);

		
		
		request.setAttribute("ProductList", productList);
		
		super.setRedirect(false);
		super.setViewPage("/WEB-INF/view/product/product_"+ productTypeString.toLowerCase() +".jsp");

	}

}
