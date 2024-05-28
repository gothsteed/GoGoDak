package product.controller;

import java.util.List;

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
		
		String referer = request.getHeader("referer");
		
		if(referer == null) { 
			super.setRedirect(true);
			super.setViewPage(request.getContextPath() + "/index.dk");
			return;
		}
		
		String searchWord = request.getParameter("searchWord");
		
		if(searchWord == null || searchWord != null && searchWord.trim().isEmpty()) {
			searchWord = "";
		}
		
		List<ProductVO> productList = pdao.getProductList(searchWord);
	
		if(productList.isEmpty()) {
			String message = "검색하신 상품이 존재하지 않습니다.";
	        String loc = "javascript:history.back()";
	         
	        request.setAttribute("message", message);
	        request.setAttribute("loc", loc);
	        
	        super.setRedirect(false);
	        super.setViewPage("/WEB-INF/view/msg.jsp");
		}
		else {
			request.setAttribute("productList", productList);
			request.setAttribute("title", "🔎 검색어 >> " + searchWord + " 🔎");
			
			super.setRedirect(false);
			super.setViewPage("/WEB-INF/view/product/product_category.jsp");
		}
		
	}

}
