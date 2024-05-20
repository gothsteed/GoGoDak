package member.controller;

import java.util.ArrayList;
import java.util.List;

import common.controller.AbstractController;
import domain.MemberVO;
import domain.ProductVO;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import member.model.MemberDao;
import member.model.MemberDao_Imple;

public class Cart extends AbstractController {

	private MemberDao mdao = null;

	public Cart() {
		mdao = new MemberDao_Imple();
	}
	
	
	
	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
	HttpSession session =request.getSession();
		
		MemberVO loginuser =(MemberVO)session.getAttribute("loginuser");
		
		
		// 사용자가 로그인했는지 확인
        if (loginuser == null) {
            super.setRedirect(true);
            super.setViewPage("/WEB-INF/view/member/member_Login.jsp");
            return;
        }
		
		 // 세션에서 장바구니 정보 가져오기
		List<ProductVO> productList = (List<ProductVO>)session.getAttribute("cart");
        
        // 장바구니가 없을 경우 빈 장바구니 생성
        if (productList == null) {
        	productList = new ArrayList<>();
        }
        
        // 장바구니 정보를 JSP로 전달
        request.setAttribute("cart", productList);
     
        
    	super.setRedirect(false);
		super.setViewPage("/WEB-INF/view/member/cart.jsp");


	}

}
