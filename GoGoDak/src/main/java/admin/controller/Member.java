package admin.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import common.controller.AbstractController;
import domain.MemberVO;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import member.model.MemberDao;
import member.model.MemberDao_Imple;
import my.util.MyUtil;

public class Member extends AbstractController{

	private MemberDao mdao = null;

	public Member() {
		mdao = new MemberDao_Imple();
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
			
			int totalPage = mdao.getTotalPage(paraMap);
			
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
			
			pageBar += "<li class='page-item'><a class='page-link' href='member.dk?searchType="+searchType+"&searchWord="+searchWord+"&sizePerPage="+sizePerPage+"&currentShowPageNo=1'>[맨처음]</a></li>";
			
			if(pageNo != 1) {
				pageBar += "<li class='page-item'><a class='page-link' href='member.dk?searchType="+searchType+"&searchWord="+searchWord+"&sizePerPage="+sizePerPage+"&currentShowPageNo="+(pageNo-1)+"'>[이전]</a></li>";
			}
			
			while( !(loop > blockSize || pageNo > totalPage) ){
				
				if(pageNo == Integer.parseInt(currentShowPageNo)) {
					pageBar += "<li class='page-item active'><a class='page-link' href='#'>"+pageNo+"</a></li>";
				}
				else {
					pageBar += "<li class='page-item'><a class='page-link' href='member.dk?searchType="+searchType+"&searchWord="+searchWord+"&sizePerPage="+sizePerPage+"&currentShowPageNo="+pageNo+"'>"+pageNo+"</a></li>";
				}
				
				loop++;
				
				pageNo++; 
						  
			} // end of while() ----------
			
			if(pageNo <= totalPage) { 
				pageBar += "<li class='page-item'><a class='page-link' href='member.dk?searchType="+searchType+"&searchWord="+searchWord+"&sizePerPage="+sizePerPage+"&currentShowPageNo="+pageNo+"'>[다음]</a></li>";
			}
			pageBar += "<li class='page-item'><a class='page-link' href='member.dk?searchType="+searchType+"&searchWord="+searchWord+"&sizePerPage="+sizePerPage+"&currentShowPageNo="+totalPage+"'>[마지막]</a></li>";		
			
			String currentURL = MyUtil.getCurrentURL(request);
			
			List<MemberVO> memberList = mdao.select_Member_paging(paraMap);
			
			request.setAttribute("memberList", memberList);
			
			if(searchType != null && ("name".equals(searchType) || "id".equals(searchType) || "email".equals(searchType)) ) {
				request.setAttribute("searchType", searchType);
			}
			
			if(searchWord != null && !searchWord.trim().isEmpty() ) { 
				request.setAttribute("searchWord", searchWord);
			}
			
			request.setAttribute("sizePerPage", sizePerPage);
			request.setAttribute("pageBar", pageBar);
			request.setAttribute("currentURL", currentURL);
			
			int totalMemberCount = mdao.getTotalMemberCount(paraMap);
			
			request.setAttribute("totalMemberCount", totalMemberCount);
			request.setAttribute("currentShowPageNo", currentShowPageNo);
			
			super.setRedirect(false);
			super.setViewPage("/WEB-INF/view/admin/admin_memberList.jsp");
		}
		else { 
			String message = "관리자만 접근이 가능합니다.";
	        String loc = "javascript:history.back()";
	         
	        request.setAttribute("message", message);
	        request.setAttribute("loc", loc);
	         
			super.setRedirect(false);
	        super.setViewPage("/WEB-INF/view/msg.jsp");
	    }
		
	}

}
