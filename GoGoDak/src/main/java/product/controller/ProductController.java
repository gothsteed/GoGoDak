package product.controller;

import java.util.List;

import common.controller.AbstractController;
import conatainer.annotation.Autowired;
import domain.ProductVO;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import pager.Pager;
import product.model.ProductDao;
import product.model.ProductDao_Imple;

public class ProductController extends AbstractController {
	
	private ProductDao productDao;
	
	@Autowired
	public ProductController(ProductDao productDao) {
		this.productDao = productDao;
	}
	

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		String productTypeString = request.getParameter("type");
		
		
		int productType;
		String title;
		
		if(productTypeString.equalsIgnoreCase("fried_rice")) {
			
			productType = 2;
			title = "🍱볶음밥🍱";
		} 
		else if(productTypeString.equalsIgnoreCase("bakery")) {
			
			productType = 3;
			title = "🥯빵🥯";
		} 
		else if(productTypeString.equalsIgnoreCase("dessert")) {
			productType = 4;
			title = "🧁디저트🧁";
		}
		else {
			productType = 1;
			productTypeString = "chicken";
			title="🍗닭가슴살🍗";
		}
		
		int blockSize = 8;
		int currentPage;
		
		try {
			currentPage = Integer.parseInt(request.getParameter("page"));
		} catch (NumberFormatException e) {
			currentPage = 1;
		}
		
		
		
		Pager<ProductVO> productPage = productDao.getProductByType(productType, currentPage, blockSize);
		
		
		String pageBar = productPage.makePageBar("product.dk", "type=" + productType);

		
		request.setAttribute("title", title);
		request.setAttribute("productList", productPage.getContent());
		request.setAttribute("pageBar", pageBar);
		
		super.setRedirect(false);
		super.setViewPage("/WEB-INF/view/product/product_category.jsp");

	}

}
