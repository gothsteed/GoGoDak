package product.controller;

import java.util.List;

import common.controller.AbstractController;
import conatainer.annotation.Autowired;
import domain.MemberVO;
import domain.ProductVO;
import domain.ReviewVO;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import product.model.ProductDao;
import product.model.ProductDao_Imple;
import review.model.ReviewDao;
import review.model.ReviewDao_imple;

public class Detail extends AbstractController {
	
	private ProductDao productDao;
	private ReviewDao reviewDao;
	
	@Autowired
	public Detail(ProductDao productDao, ReviewDao reviewDao) {
		
		this.productDao = productDao;
		this.reviewDao = reviewDao;
	}

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		String product_seq_String = request.getParameter("product_seq");
		
		int product_seq = -1;
		try {
			product_seq = Integer.parseInt(product_seq_String);
		} catch (NumberFormatException e) {
			super.setRedirect(true);
			super.setViewPage(request.getContextPath() + "/product.dk");
			return;
		}
		
		ProductVO product = productDao.getProductBySeq(product_seq);
		List<ReviewVO> reviewList = reviewDao.getReviewListByProductSeq(product_seq);
		
		int totalScore = 0;
		for(ReviewVO reviewVO : reviewList) {
			totalScore += reviewVO.getStar();
		}
		
		if(reviewList.size() != 0) {
			int scoreAvg = totalScore / reviewList.size();
			request.setAttribute("scoreAvg", scoreAvg);
		}
		
		int likeCount = productDao.getLikeCount(product_seq);
		
		
		
		request.setAttribute("product", product);
		request.setAttribute("reviewList", reviewList);
		request.setAttribute("likeCount", likeCount);
		
		boolean isLiked = false;
		HttpSession session = request.getSession();
		MemberVO loginuser =(MemberVO)session.getAttribute("loginuser");
		if (loginuser != null && productDao.isLiked(loginuser.getMember_seq(), product_seq)) {
			isLiked = true;
		}
		request.setAttribute("isLiked", isLiked);
		
		super.setRedirect(false);
		super.setViewPage("/WEB-INF/view/product/product_detail.jsp");
	}

}
