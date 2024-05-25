package product.controller;

import java.util.List;

import common.controller.AbstractController;
import discountEvent.model.DiscountEventDao;
import discountEvent.model.DiscountEventDao_imple;
import domain.Discount_eventVO;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public class Event extends AbstractController {
	private DiscountEventDao discountEventDao;
	
	public Event() {
		discountEventDao = new DiscountEventDao_imple();
	}
	

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		
		
		int blockSize = 6;
		int currentPage;
		
		try {
			currentPage = Integer.parseInt(request.getParameter("page"));
		} catch (NumberFormatException e) {
			currentPage = 1;
		}
		
		
		int totalPageNum = discountEventDao.getTotalPage(blockSize);
		System.out.println("total page num: " + totalPageNum);
		if(currentPage > totalPageNum) {
			currentPage = 1;
		}
		
		
		List<Discount_eventVO> discount_event_list = discountEventDao.getAllDiscountEvent(currentPage, blockSize);
		
		
		//pageBar
		
		
		int loop = 1;
		int pageNo = ((currentPage - 1)/blockSize) * blockSize + 1;
		
		String pageBar = "<li class='page-item'><a class='page-link' href='event.dk?page=1'>[맨처음]</a></li>";
		
		if(pageNo != 1) {
			pageBar += "<li class='page-item'><a class='page-link' href='event.dk?type=&page="+ (pageNo - 1) +"'>[이전]</a></li>";
		}
		
		//맨처음 맨마지막 만들기
		
		while( !(loop > blockSize || pageNo > totalPageNum) ) {
			
			//1 2 3 4 5 6 7  8 9 10
			//pageBar += "<li class='page-item'><a class='page-link' href='memberList.up?searchType="+searchType+"&searchWord="+searchWord+"&sizePerPage="+sizePerPage+"&currentShowPageNo="+pageNo+"'>"+pageNo+"</a></li>";
			
			if(pageNo == currentPage) {
				
				pageBar += "<li class='page-item active'><a class='page-link' href='#'>"+pageNo+"</a></li>";
			}
			else {
				
				pageBar += "<li class='page-item'><a class='page-link' href='event.dk?page="+ pageNo + "'>"+pageNo+"</a></li>";
			}
			
			
			loop ++; 
			
			
			// 1 2 3 4 5 6 7  8 9 10
			// 11 12 13 14 15 16 17 18 19 20
			pageNo ++;
			
		}
		
		pageBar += "<li class='page-item'><a class='page-link' href='event.dk?page="+ (totalPageNum) +"'>[맨마지막]</a></li>";
		//다음 마지막 만들기
		if(pageNo <= totalPageNum) {
			pageBar += "<li class='page-item'><a class='page-link' href='event.dk?page="+ (currentPage + 1)+"'>[다음]</a></li>";
		}

		
		
		request.setAttribute("discount_event_list", discount_event_list);
		request.setAttribute("pageBar", pageBar);
		
		
		super.setRedirect(false);
		super.setViewPage("/WEB-INF/view/product/product_special.jsp");

	}

}
