package product.controller;

import common.controller.AbstractController;
import domain.ProductVO;
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
		
		String searchWord = request.getParameter("searchWord");
		
		if(searchWord == null || searchWord != null && searchWord.trim().isEmpty()) {
			searchWord = "";
		}
		
		ProductVO pvo = pdao.getProductList(searchWord);
		
		if(pvo == null) {
			super.setRedirect(false);
			super.setViewPage("/WEB-INF/index.jsp");
			
			return;
		}
		
		request.setAttribute("productList", pvo);
		request.setAttribute("title", "검색어 : " + searchWord);
		
		super.setRedirect(false);
		super.setViewPage("/WEB-INF/view/product/product_category.jsp");

	}

}
