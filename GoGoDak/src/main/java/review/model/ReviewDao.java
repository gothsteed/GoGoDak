package review.model;

import java.sql.SQLException;
import java.util.List;

import domain.ReviewVO;

public interface ReviewDao {

	List<ReviewVO> getReviewListByProductSeq(int product_seq) throws SQLException;

}