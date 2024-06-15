package admin.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import common.controller.AbstractController;
import conatainer.annotation.Autowired;
import domain.MemberVO;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import member.model.MemberDao;
import member.model.MemberDao_Imple;
import my.util.MyUtil;
import pager.Pager;

public class Member extends AbstractController{

	private MemberDao mdao = null;

	@Autowired
	public Member(MemberDao mdao) {
		this.mdao = mdao;
	}
	
	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		HttpSession session = request.getSession();
		MemberVO loginuser = (MemberVO)session.getAttribute("loginuser");
		
		if(loginuser != null && "admin".equals(loginuser.getId())) {
			
			String searchType = request.getParameter("searchType");
			String searchWord = request.getParameter("searchWord");
			String sizePerPage = request.getParameter("sizePerPage");
			String currentShowPageNo = request.getParameter("page");
	
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
			
			int sizePerPageInt;
			try {
				sizePerPageInt = Integer.parseInt(sizePerPage);
			} catch (NumberFormatException e) {
				sizePerPageInt = 10;
			}
			int currentShowPageNoInt;
			try {
				currentShowPageNoInt = Integer.parseInt(currentShowPageNo);
			} catch (NumberFormatException e) {
				currentShowPageNoInt = 1;
			}
			
			
			System.out.println("sizePerPageInt : " + sizePerPageInt);
			System.out.println("currentShowPageNoInt : " + currentShowPageNoInt);
			
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

			
			String currentURL = MyUtil.getCurrentURL(request);
			
			Pager<MemberVO> memberList = mdao.select_Member_paging(searchType, searchWord, currentShowPageNoInt, sizePerPageInt);
			
			request.setAttribute("memberList", memberList.getContent());

			
			request.setAttribute("sizePerPage", sizePerPage);
			request.setAttribute("pageBar", memberList.makePageBar("member.dk", "searchType="+searchType, "searchWord="+searchWord, "sizePerPage="+sizePerPage));
			request.setAttribute("currentURL", currentURL);
			
			request.setAttribute("totalMemberCount", memberList.getTotalElementCount());
			
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
