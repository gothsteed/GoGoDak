package review.model;

import java.sql.SQLException;
import java.util.List;

import domain.ReviewVO;

public interface ReviewDao {

	List<ReviewVO> getReviewListByProductSeq(int product_seq) throws SQLException;

	int insertReview(int member_seq, int product_seq, int order_seq, String id, int star, String content,
			String picName) throws SQLException;

}
