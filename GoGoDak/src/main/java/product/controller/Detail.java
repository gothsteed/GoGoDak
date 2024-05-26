package product.controller;

import java.util.List;

import common.controller.AbstractController;
import domain.ProductVO;
import domain.ReviewVO;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import product.model.ProductDao;
import product.model.ProductDao_Imple;
import review.model.ReviewDao;
import review.model.ReviewDao_imple;

public class Detail extends AbstractController {
	
	private ProductDao productDao;
	private ReviewDao reviewDao;
	
	public Detail() {
		
		this.productDao = new ProductDao_Imple();
		this.reviewDao = new ReviewDao_imple();
	}

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		super.goBackURL(request);
		
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
		
		
		request.setAttribute("product", product);
		request.setAttribute("reviewList", reviewList);
		
		
		super.setRedirect(false);
		super.setViewPage("/WEB-INF/view/product/product_detail.jsp");
	}

}
