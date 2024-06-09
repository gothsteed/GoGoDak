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

public class Brand extends AbstractController {

	private ProductDao pdao = null;
	@Autowired
	public Brand(ProductDao pdao) {
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
		
		String manufacturer_seq = request.getParameter("manufacturer_seq");
		int blockSize = 8;
		int currentPage;
		
		try {
			currentPage = Integer.parseInt(request.getParameter("page"));
		} catch (NumberFormatException e) {
			currentPage = 1;
		}
		
		
		Pager<ProductVO> brandProductList = pdao.getBrandProductList(manufacturer_seq, currentPage, blockSize);
		
		if(brandProductList.getContent().isEmpty()) {
			String message = "해당 브랜드의 상품이 존재하지 않습니다.";
	        String loc = "javascript:history.back()";
	         
	        request.setAttribute("message", message);
	        request.setAttribute("loc", loc);
	        
	        super.setRedirect(false);
	        super.setViewPage("/WEB-INF/view/msg.jsp");
		}
		else {
			request.setAttribute("productList", brandProductList);
			
			if(manufacturer_seq.equals("1")) {
				request.setAttribute("title", "🍦 딜리스틱 🍦");
			}
			else if(manufacturer_seq.equals("2")) {
				request.setAttribute("title", "🍤 닥터리브 🍤");
			}
			else if(manufacturer_seq.equals("3")){
				request.setAttribute("title", "🍉 제로아워 🍉");
			}
			request.setAttribute("pageBar", brandProductList.makePageBar("brand.dk", "manufacturer_seq="+manufacturer_seq));
			
			super.setRedirect(false);
			super.setViewPage("/WEB-INF/view/product/product_category.jsp");
		}

	}

}
