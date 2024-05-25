package discountEvent.model;

import java.io.UnsupportedEncodingException;
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

import domain.Discount_eventVO;
import domain.ProductVO;
import util.security.AES256;
import util.security.SecretMyKey;

public class DiscountEventDao_imple implements DiscountEventDao {

	private DataSource ds;
	private Connection conn;
	private PreparedStatement pstmt;
	private ResultSet rs;

	private AES256 aes;
	
	
	private Discount_eventVO createDiscount_eventVO(ResultSet rs) throws SQLException {
		
		//TODO: 생정자에 ResultSet을 넘겨줄까??
		Discount_eventVO discount_eventVO = new Discount_eventVO();
		
		discount_eventVO.setDiscount_event_seq(rs.getInt("discount_event_seq"));
		discount_eventVO.setDiscount_name(rs.getString("discount_name"));
		discount_eventVO.setPic(rs.getString("pic"));
		
		return discount_eventVO;
		
	}
	

	public DiscountEventDao_imple() {
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
	
	
	private int updateDiscountProducts(Connection conn, int[] selectedProducts, int discount_event_seq ) throws SQLException {
		String sql = " UPDATE tbl_product " + " SET fk_discount_event_seq = ?" + "WHERE product_seq = ?";

		int total = 0;
		for (int seq : selectedProducts) {

			PreparedStatement preparedStatement = conn.prepareStatement(sql);

			preparedStatement.setInt(1, discount_event_seq);
			preparedStatement.setInt(2, seq);

			System.out.println("inserting product list");
			int temp = preparedStatement.executeUpdate();

			if (temp != 1) {
				return 0;
			}

			total += temp;
		}
		
		return total;
	}

	@Override
	public int insertDiscountEvent(String name, String savedFileName, int[] selectedProducts) throws SQLException {
		int result = 0;

		try {
			conn = ds.getConnection();
			conn.setAutoCommit(false);

			String sql = " insert into " + " tbl_discount_event(discount_event_seq, discount_name, pic) "
					+ " values(discount_event_seq.nextval, ?, ?) ";

			pstmt = conn.prepareStatement(sql, new String[] { "discount_event_seq" });

			pstmt.setString(1, name);
			pstmt.setString(2, savedFileName);

			System.out.println("inserting discount event");
			result = pstmt.executeUpdate();

			if (result != 1) {
				conn.rollback();
				return result;
			}

			ResultSet rs = pstmt.getGeneratedKeys();

			int discount_event_seq = -1;
			if (rs.next()) {
				discount_event_seq = rs.getInt(1);
			}

			sql = " UPDATE tbl_product " + " SET fk_discount_event_seq = ?" + "WHERE product_seq = ?";

			int total = updateDiscountProducts(conn, selectedProducts, discount_event_seq);

			if (total == selectedProducts.length) {
				result = 1;
				conn.commit();
			}
			else {
				conn.rollback();
				result = 0;
			}

		} catch (SQLException e) {
			e.printStackTrace();
			conn.rollback();
		} finally {
			conn.setAutoCommit(true);
			close();
		}

		return result;
	}

	@Override
	public List<Discount_eventVO> getAllDiscountEvent(int currentPage, int blockSize) throws SQLException {
		List<Discount_eventVO> discountEventList = new ArrayList<>();

		try {
			conn = ds.getConnection();

			String sql = "    SELECT * "
					+ "    FROM " + 
					"    ( "
					+ "        SELECT rownum as rno, discount_event_seq, "
					+ "               discount_name, pic    "
					+ "        FROM " 
					+ "        ( "
					+ "            select * "
					+ "            from tbl_discount_event "
					+ "            ) V "
					+ " )T " 
					+ " WHERE T.rno between ? and ? ";


			pstmt = conn.prepareStatement(sql);
			
			pstmt.setLong(1, (currentPage * blockSize) - (blockSize - 1)); // 페이징처리 공식
			pstmt.setLong(2, (currentPage * blockSize));


			rs = pstmt.executeQuery();

			while (rs.next()) {

				Discount_eventVO discount_eventVO = createDiscount_eventVO(rs);

				discountEventList.add(discount_eventVO);
			} // end of while(rs.next())---------------------

		} finally {
			close();
		}

		return discountEventList;
	}

	@Override
	public int getTotalPage(int blockSize) throws SQLException {
		int totalPage = 0;

		try {
			conn = ds.getConnection();

			String sql = " select ceil(count(*)/?) "
					+ "            from tbl_discount_event ";



			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, blockSize);
			
			rs = pstmt.executeQuery();

			if (rs.next()) {
				totalPage = rs.getInt(1);
				
			} // end of while(rs.next())---------------------

		} finally {
			close();
		}

		return totalPage;
	}


	@Override
	public Discount_eventVO getDiscountEventBySeq(int discount_event_Seq) throws SQLException {
		Discount_eventVO discount_eventVO = null;
		
		
		try {
			conn = ds.getConnection();

			String sql = " select * "
					+ " from tbl_discount_event "
					+ " where discount_event_seq = ? ";



			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, discount_event_Seq);
			
			rs = pstmt.executeQuery();

			if (rs.next()) {
				discount_eventVO = createDiscount_eventVO(rs);
				
			} // end of while(rs.next())---------------------

		} finally {
			close();
		}
		
		return discount_eventVO;
	}
	
	
	private int resetDiscountProducts(int discount_event_seq, Connection conn) throws SQLException {
		
		String sql = " UPDATE tbl_product "
				+ " SET fk_discount_event_seq = null "
				+ " WHERE fk_discount_event_seq = ? ";
		
		PreparedStatement preparedStatement = conn.prepareStatement(sql);
		preparedStatement.setInt(1, discount_event_seq);
		int result = preparedStatement.executeUpdate();
		System.out.println("reset result : " + result);
		return result;
	}


	@Override
	public int updateDiscountEvent(int discount_event_seq, String name, int[] selectedProducts) throws SQLException {
		int result = 0;
		
		try {
		
			conn = ds.getConnection();
			conn.setAutoCommit(false);
			
			
			String sql = " UPDATE tbl_discount_event "
					+ " SET discount_name = ?"
					+ " WHERE discount_event_seq = ? ";
			
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, name);
			pstmt.setInt(2, discount_event_seq);
			
			

			result = pstmt.executeUpdate();
			
			if(result != 1) {
				return 0;
			}

			resetDiscountProducts(discount_event_seq, conn);

			int total = updateDiscountProducts(conn, selectedProducts, discount_event_seq);
			
			if (total == selectedProducts.length) {
				result = 1;
				conn.commit();
			}
			else {
				conn.rollback();
				result = 0;
			}
			
			
		} catch (SQLException e) {
			
			e.printStackTrace();
			conn.rollback();
			
		}
		
		finally {
			conn.setAutoCommit(true);
			close();
		}
		
		System.out.println("result :" + result);
		return result;
		
	}


	@Override
	public int updateDiscountEvent(int discount_event_seq, String name, String savedFileName, int[] selectedProducts)
			throws SQLException {
		int result = 0;
		
		try {
			conn = ds.getConnection();
			conn.setAutoCommit(false);
			
			String sql = " UPDATE tbl_discount_event "
					+ " SET discount_name = ?, pic = ?"
					+ " WHERE discount_event_seq = ? ";
			
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, name);
			pstmt.setString(2, savedFileName);
			pstmt.setInt(3, discount_event_seq);

			result = pstmt.executeUpdate();
			
			if(result != 1) {
				return 0;
			}

			resetDiscountProducts(discount_event_seq, conn);
			int total = updateDiscountProducts(conn, selectedProducts, discount_event_seq);
			
			if (total == selectedProducts.length) {
				result = 1;
				conn.commit();
			}
			else {
				conn.rollback();
				result = 0;
			}
			
			
		} catch (SQLException e) {
			e.getStackTrace();
			conn.rollback();
		}
		
		finally {
			conn.setAutoCommit(true);
			close();
		}
		
		return result;
	}



}
