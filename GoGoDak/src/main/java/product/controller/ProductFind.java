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

public class ProductFind extends AbstractController {

	private ProductDao pdao = null;
	
	@Autowired
	public ProductFind(ProductDao pdao) {
		this.pdao = pdao;
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
		int blockSize = 8;
		int currentPage;
		
		try {
			currentPage = Integer.parseInt(request.getParameter("page"));
		} catch (NumberFormatException e) {
			currentPage = 1;
		}
		
		if(searchWord == null || searchWord != null && searchWord.trim().isEmpty()) {
			searchWord = "";
		}
		
		Pager<ProductVO> productPage = pdao.getProductList(searchWord, currentPage, blockSize);
		
	
		if(productPage.getContent().isEmpty()) {
			String message = "검색하신 상품이 존재하지 않습니다.";
	        String loc = "javascript:history.back()";
	         
	        request.setAttribute("message", message);
	        request.setAttribute("loc", loc);
	        
	        super.setRedirect(false);
	        super.setViewPage("/WEB-INF/view/msg.jsp");
		}
		else {
			request.setAttribute("productList", productPage.getContent());
			request.setAttribute("title", "🔎 검색어 >> " + searchWord + " 🔎");
			request.setAttribute("pageBar", productPage.makePageBar("productFind.dk", "searchWord=" + searchWord));
			
			super.setRedirect(false);
			super.setViewPage("/WEB-INF/view/product/product_category.jsp");
		}
		
	}

}
