package discountEvent.model;

import java.io.UnsupportedEncodingException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

import domain.ProductVO;
import util.security.AES256;
import util.security.SecretMyKey;

public class DiscountEventDao_imple implements DiscountEventDao {
	
	private DataSource ds;
	private Connection conn;
	private PreparedStatement pstmt;
	private ResultSet rs;

	private AES256 aes;
	
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
	

	@Override
	public int insertDiscountEvent(String name, String savedFileName, int[] selectedProducts) throws SQLException {
		int result = 0;

		try {
			conn = ds.getConnection();
			conn.setAutoCommit(false);

			String sql = " insert into "
					+ " tbl_discount_event(discount_event_seq, discount_name, pic) "
					   + " values(discount_event_seq.nextval, ?, ?) ";

			pstmt = conn.prepareStatement(sql, new String[] {"discount_event_seq"});

			pstmt.setString(1, name);
			pstmt.setString(2, savedFileName);

			
			System.out.println("inserting discount event");
			result = pstmt.executeUpdate();
			
			if(result != 1) {
				conn.rollback();
				return result;
			}
			
			
			ResultSet rs = pstmt.getGeneratedKeys();
			
            int discount_event_seq = -1;
            if(rs.next()) {
            	discount_event_seq = rs.getInt(1);
            }
            
            
			sql = " UPDATE tbl_product "
					+ " SET fk_discount_event_seq = ?"
					+ "WHERE product_seq = ?";
			
			
			int total = 0;
			for(int seq : selectedProducts) {
				
				
				pstmt = conn.prepareStatement(sql);

				pstmt.setInt(1, discount_event_seq);
				pstmt.setInt(2, seq);


	
				System.out.println("inserting product list");
				int temp = pstmt.executeUpdate();
				
				if(temp != 1) {
					conn.rollback();
					return 0;
				}
				
				total += temp;
				
			}
			
			if(total == selectedProducts.length) {
				result = 1;
				conn.commit();
			}
			
            
            

		} catch (SQLException e) {
			e.printStackTrace();
			conn.rollback();
		} 
		finally {
			conn.setAutoCommit(true);
			close();
		}

		return result;
	}

}
