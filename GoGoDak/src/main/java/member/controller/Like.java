package member.controller;

import java.util.List;

import common.controller.AbstractController;
import domain.MemberVO;
import domain.ProductVO;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import product.model.ProductDao;
import product.model.ProductDao_Imple;

public class Like extends AbstractController {
	
	
	private ProductDao productDao;
	
	public Like() {
		this.productDao = new ProductDao_Imple();
		
	}

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		HttpSession session = request.getSession();
		MemberVO loginuser = (MemberVO) session.getAttribute("loginuser");
		
		if(loginuser == null) {
			String msg = "로그인 하시오";
			String log = "javascript:history.back()";
			
			request.setAttribute("msg", msg);
			request.setAttribute("log", log);
			setRedirect(false);
			setViewPage("/WEB-INF/view/msg.jsp");
			return;
		}
		
		List<ProductVO> likedProduct = productDao.getLikedProduct(loginuser.getMember_seq());
		String title = "❤️좋아요❤️";
		
		request.setAttribute("title", title);
		request.setAttribute("productList", likedProduct);
		super.setRedirect(false);
		super.setViewPage("/WEB-INF/view/product/product_category.jsp");
		
	}

}
