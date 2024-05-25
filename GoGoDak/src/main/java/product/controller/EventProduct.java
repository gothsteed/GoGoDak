package product.controller;

import java.util.List;

import common.controller.AbstractController;
import discountEvent.model.DiscountEventDao;
import discountEvent.model.DiscountEventDao_imple;
import domain.Discount_eventVO;
import domain.ProductVO;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import product.model.ProductDao;
import product.model.ProductDao_Imple;

public class EventProduct extends AbstractController {
	
	private ProductDao productDao;
	private DiscountEventDao discountEventDao;
	
	public EventProduct() {
		this.productDao = new ProductDao_Imple();
		discountEventDao = new DiscountEventDao_imple();
	}

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		int discount_event_Seq;
		try {
			discount_event_Seq = Integer.parseInt(request.getParameter("discount_event_seq"));
		}
		catch (NumberFormatException e) {
			super.setRedirect(true);
			super.setViewPage(request.getContextPath() + "/product/event.dk");
			return;
		}
		
		Discount_eventVO discount_eventVO = discountEventDao.getDiscountEventBySeq(discount_event_Seq);
		
		int blockSize = 8;
		int currentPage;
		
		try {
			currentPage = Integer.parseInt(request.getParameter("page"));
		} catch (NumberFormatException e) {
			currentPage = 1;
		}
		
		
		int totalPageNum = productDao.getEventProductTotalPage(discount_event_Seq, blockSize);
		System.out.println("total page num: " + totalPageNum);
		if(currentPage > totalPageNum) {
			currentPage = 1;
		}
		
		
		
		List<ProductVO> productList = productDao.getProductByDiscountEvent(discount_event_Seq, currentPage, blockSize);
		
		int loop = 1;
		int pageNo = ((currentPage - 1)/blockSize) * blockSize + 1;
		
		String pageBar = "<li class='page-item'><a class='page-link' href='detail.dk?discount_event_seq=" + discount_event_Seq + "&page=1'>[맨처음]</a></li>";
		
		if(pageNo != 1) {
			pageBar += "<li class='page-item'><a class='page-link' href='detail.dk?discount_event_seq=" + discount_event_Seq + "&page="+ (pageNo - 1) +"'>[이전]</a></li>";
		}
		
		//맨처음 맨마지막 만들기
		
		while( !(loop > blockSize || pageNo > totalPageNum) ) {
			
			//1 2 3 4 5 6 7  8 9 10
			//pageBar += "<li class='page-item'><a class='page-link' href='memberList.up?searchType="+searchType+"&searchWord="+searchWord+"&sizePerPage="+sizePerPage+"&currentShowPageNo="+pageNo+"'>"+pageNo+"</a></li>";
			
			if(pageNo == currentPage) {
				
				pageBar += "<li class='page-item active'><a class='page-link' href='#'>"+pageNo+"</a></li>";
			}
			else {
				
				pageBar += "<li class='page-item'><a class='page-link' href='detail.dk?discount_event_seq=" + discount_event_Seq + "&page="+ pageNo + "'>"+pageNo+"</a></li>";
			}
			
			
			loop ++; 
			
			
			// 1 2 3 4 5 6 7  8 9 10
			// 11 12 13 14 15 16 17 18 19 20
			pageNo ++;
			
		}
		
		pageBar += "<li class='page-item'><a class='page-link' href='detail.dk?discount_event_seq=" + discount_event_Seq + "&page="+ (totalPageNum) +"'>[맨마지막]</a></li>";
		//다음 마지막 만들기
		if(pageNo <= totalPageNum) {
			pageBar += "<li class='page-item'><a class='page-link' href='detail.dk?discount_event_seq=" + discount_event_Seq + "&page="+ (currentPage + 1)+"'>[다음]</a></li>";
		}

		
		//TODO: banner here
		String title = "<div><img src='" + request.getContextPath() + "/images/special/"+discount_eventVO.getPic()+"' height='500px' /></div>";
		
		request.setAttribute("title", title);
		request.setAttribute("productList", productList);
		request.setAttribute("pageBar", pageBar);
		
		super.setRedirect(false);
		super.setViewPage("/WEB-INF/view/product/product_category.jsp");

	}

}
