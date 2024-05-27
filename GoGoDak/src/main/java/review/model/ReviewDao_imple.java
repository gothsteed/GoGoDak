package review.model;

import java.io.UnsupportedEncodingException;
import java.security.GeneralSecurityException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

import domain.MemberVO;
import domain.ReviewVO;
import util.security.AES256;
import util.security.SecretMyKey;

public class ReviewDao_imple implements ReviewDao {
	
	private DataSource ds;
	private Connection conn;
	private PreparedStatement pstmt;
	private ResultSet rs;

	private AES256 aes;
	
	public ReviewDao_imple() {
		try {

			Context initContext = new InitialContext();
			Context envContext = (Context) initContext.lookup("java:/comp/env");
			ds = (DataSource) envContext.lookup("jdbc/semioracle");
			aes = new AES256(SecretMyKey.KEY);

		} catch (NamingException e) {
			e.printStackTrace();
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		
	}
	
	private void close() {
		try {
			if (rs != null) {
				rs.close();
				rs = null;
			}
			if (pstmt != null) {
				pstmt.close();
				pstmt = null;
			}
			if (conn != null) {
				conn.close();
				conn = null;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	
	private ReviewVO createReviewVO(ResultSet rs) throws SQLException {
		
		ReviewVO reviewVO = new ReviewVO();
		
		reviewVO.setReview_seq(rs.getInt("REVIEW_SEQ"));
		reviewVO.setFk_member_seq(rs.getInt("FK_MEMBER_SEQ"));
		reviewVO.setFk_product_seq(rs.getInt("FK_PRODUCT_SEQ"));
		reviewVO.setFk_order_seq(rs.getInt("FK_ORDER_SEQ"));
		reviewVO.setId(rs.getString("ID"));
		reviewVO.setStar(rs.getInt("STAR"));
		reviewVO.setContent(rs.getString("CONTENT"));
		reviewVO.setPic(rs.getString("PIC"));
		reviewVO.setRagisterdate(rs.getDate("REGISTERDAY"));
		
		return reviewVO;
	}
	

	@Override
	public List<ReviewVO> getReviewListByProductSeq(int product_seq) throws SQLException {
		List<ReviewVO> reviewList = new ArrayList<>();
	
		
		try {
			conn = ds.getConnection();
			
			
			String sql = " select * "
					+ " from tbl_review "
					+ " where FK_PRODUCT_SEQ =?  ";
			
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, product_seq);
			
			rs = pstmt.executeQuery();
			
			while (rs.next()) {
				
				reviewList.add(createReviewVO(rs));
				
			}
		
		} finally {
			close();
		}
		
		return reviewList;
	}

	@Override
	public int insertReview(int member_seq, int product_seq, int order_seq, String id, int star, String content,
			String picName) throws SQLException {
		
		int result = 0;

		try {
			conn = ds.getConnection();

			String sql = " insert into tbl_review(REVIEW_SEQ, FK_MEMBER_SEQ, FK_PRODUCT_SEQ, FK_ORDER_SEQ, ID, STAR, CONTENT, PIC, REGISTERDAY) "
					   + " values(REVIEW_SEQ.nextval, ?, ?, ?, ?, ?, ?, ?, sysdate) ";

			pstmt = conn.prepareStatement(sql);

			pstmt.setInt(1, member_seq);
			pstmt.setInt(2, product_seq);
			pstmt.setInt(3, order_seq);
			pstmt.setString(4, id);
			pstmt.setInt(5, star);
			pstmt.setString(6, content);
			pstmt.setString(7, picName);

			
			result = pstmt.executeUpdate();

		} finally {
			close();
		}

		return result;
	}

}
