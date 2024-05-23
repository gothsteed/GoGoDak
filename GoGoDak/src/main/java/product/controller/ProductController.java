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
		
		
		int totalPageNum = productDao.getTotalPage(productType, blockSize);
		System.out.println("total page num: " + totalPageNum);
		if(currentPage > totalPageNum) {
			currentPage = 1;
		}
		
		
		List<ProductVO> productList = productDao.getProductByType(productType, currentPage, blockSize);
		
		
		//pageBar
		
		
		int loop = 1;
		int pageNo = ((currentPage - 1)/blockSize) * blockSize + 1;
		
		String pageBar = "<li class='page-item'><a class='page-link' href='product.dk?type=" + productTypeString + "&page=1'>[맨처음]</a></li>";
		
		if(pageNo != 1) {
			pageBar += "<li class='page-item'><a class='page-link' href='product.dk?type=" + productTypeString + "&page="+ (pageNo - 1) +"'>[이전]</a></li>";
		}
		
		//맨처음 맨마지막 만들기
		
		while( !(loop > blockSize || pageNo > totalPageNum) ) {
			
			//1 2 3 4 5 6 7  8 9 10
			//pageBar += "<li class='page-item'><a class='page-link' href='memberList.up?searchType="+searchType+"&searchWord="+searchWord+"&sizePerPage="+sizePerPage+"&currentShowPageNo="+pageNo+"'>"+pageNo+"</a></li>";
			
			if(pageNo == currentPage) {
				
				pageBar += "<li class='page-item active'><a class='page-link' href='#'>"+pageNo+"</a></li>";
			}
			else {
				
				pageBar += "<li class='page-item'><a class='page-link' href='product.dk?type=" + productTypeString + "&page="+ pageNo + "'>"+pageNo+"</a></li>";
			}
			
			
			loop ++; 
			
			
			// 1 2 3 4 5 6 7  8 9 10
			// 11 12 13 14 15 16 17 18 19 20
			pageNo ++;
			
		}
		
		pageBar += "<li class='page-item'><a class='page-link' href='product.dk?type=" + productTypeString + "&page="+ (totalPageNum) +"'>[맨마지막]</a></li>";
		//다음 마지막 만들기
		if(pageNo <= totalPageNum) {
			pageBar += "<li class='page-item'><a class='page-link' href='product.dk?type=" + productTypeString + "&page="+ (currentPage + 1)+"'>[다음]</a></li>";
		}

		
		request.setAttribute("title", title);
		request.setAttribute("productList", productList);
		request.setAttribute("pageBar", pageBar);
		
		super.setRedirect(false);
		super.setViewPage("/WEB-INF/view/product/product_category.jsp");

	}

}
