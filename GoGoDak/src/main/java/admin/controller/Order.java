package admin.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import admin.model.AdminDAO;
import admin.model.AdminDAO_imple;
import common.controller.AbstractController;
import domain.MemberVO;
import domain.OrderVO;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import my.util.MyUtil;


public class Order extends AbstractController{

	private AdminDAO adao = null;
	
	public Order() {
		adao = new AdminDAO_imple();
	}
	
	
	
	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		HttpSession session = request.getSession();
		MemberVO loginuser = (MemberVO)session.getAttribute("loginuser");
		
		if(loginuser != null && "admin".equals(loginuser.getId())) {
			
			String searchType = request.getParameter("searchType");
			String searchWord = request.getParameter("searchWord");
			String sizePerPage = request.getParameter("sizePerPage");
			String currentShowPageNo = request.getParameter("currentShowPageNo");
	
			if(searchType == null || (!"name".equals(searchType) && !"id".equals(searchType) && !"email".equals(searchType)) ) {
				searchType = "";
			}
			
			if(searchWord == null || (searchWord != null && searchWord.trim().isEmpty()) ) {
				searchWord = "";
			}
			
			if(sizePerPage == null || (!"10".equals(sizePerPage) && !"5".equals(sizePerPage) && !"3".equals(sizePerPage)) ) {
				sizePerPage = "10";
			}
			
			if(currentShowPageNo == null) {
				currentShowPageNo = "1";
			}
			
		
			Map<String, String> paraMap = new HashMap<>();
			paraMap.put("searchType", searchType);
			paraMap.put("searchWord", searchWord);
			paraMap.put("currentShowPageNo", currentShowPageNo); 
			paraMap.put("sizePerPage", sizePerPage);
			
			int totalPage = adao.getTotalPage(paraMap);

			try {
				if(Integer.parseInt(currentShowPageNo) > totalPage || Integer.parseInt(currentShowPageNo) <= 0 ) {
					currentShowPageNo = "1";
					paraMap.put("currentShowPageNo", currentShowPageNo);
				}
			} catch(NumberFormatException e) {
				currentShowPageNo = "1";
				paraMap.put("currentShowPageNo", currentShowPageNo);
			}

			String pageBar = "";
			
			int blockSize = 10; 
			
			int loop = 1;
			
			int pageNo = ( (Integer.parseInt(currentShowPageNo) - 1)/blockSize ) * blockSize + 1;
			
			pageBar += "<li class='page-item'><a class='page-link' href='order.dk?searchType="+searchType+"&searchWord="+searchWord+"&sizePerPage="+sizePerPage+"&currentShowPageNo=1'>[맨처음]</a></li>";
			
			if(pageNo != 1) {
				pageBar += "<li class='page-item'><a class='page-link' href='order.dk?searchType="+searchType+"&searchWord="+searchWord+"&sizePerPage="+sizePerPage+"&currentShowPageNo="+(pageNo-1)+"'>[이전]</a></li>";
			}
			   while (!(loop > blockSize || pageNo > totalPage)) {

	                if (pageNo == Integer.parseInt(currentShowPageNo)) {
	                    pageBar += "<li class='page-item active'><a class='page-link' href='#'>" + pageNo + "</a></li>";
	                } else {
	                    pageBar += "<li class='page-item'><a class='page-link' href='order.dk?searchType=" + searchType + "&searchWord=" + searchWord + "&sizePerPage=" + sizePerPage + "&currentShowPageNo=" + pageNo + "'>" + pageNo + "</a></li>";
	                }

	                loop++;

	                pageNo++;

	            }

	            if (pageNo <= totalPage) {
	                pageBar += "<li class='page-item'><a class='page-link' href='order.dk?searchType=" + searchType + "&searchWord=" + searchWord + "&sizePerPage=" + sizePerPage + "&currentShowPageNo=" + pageNo + "'>[다음]</a></li>";
	            }
	            pageBar += "<li class='page-item'><a class='page-link' href='order.dk?searchType=" + searchType + "&searchWord=" + searchWord + "&sizePerPage=" + sizePerPage + "&currentShowPageNo=" + totalPage + "'>[마지막]</a></li>";

	            String currentURL = MyUtil.getCurrentURL(request);

	            List<OrderVO> orderList = adao.select_Order_paging(paraMap);

	            request.setAttribute("orderList", orderList);
	            request.setAttribute("searchType", searchType);
	            request.setAttribute("searchWord", searchWord);
	            request.setAttribute("sizePerPage", sizePerPage);
	            request.setAttribute("pageBar", pageBar);
	            request.setAttribute("currentURL", currentURL);
	            request.setAttribute("totalMemberCount", adao.getTotalMemberCount(paraMap));
	            request.setAttribute("currentShowPageNo", currentShowPageNo);

	            super.setRedirect(false);
	            super.setViewPage("/WEB-INF/view/admin/admin_order.jsp");
	        } else {
	            String message = "관리자만 접근이 가능합니다.";
	            String loc = "javascript:history.back()";

	            request.setAttribute("message", message);
	            request.setAttribute("loc", loc);

	            super.setRedirect(false);
	            super.setViewPage("/WEB-INF/view/msg.jsp");
	        }
	    }
	}