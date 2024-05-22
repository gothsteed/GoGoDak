package order.model;

import java.io.UnsupportedEncodingException;
import java.security.GeneralSecurityException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Map;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

import domain.ProductVO;
import util.security.AES256;
import util.security.SecretMyKey;

public class OrderDao_imple implements OrderDao {
	
	private DataSource ds;
	private Connection conn;
	private PreparedStatement pstmt;
	private ResultSet rs;

	private AES256 aes;
	
	public OrderDao_imple() {
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
	
	private int insertProductList(Map<ProductVO, Integer> cart, int order_seq, int member_seq) throws SQLException {
		int result = 0;

		try {
			Connection connection = ds.getConnection();

			String sql = " insert into "
					+ " tbl_product_list(FK_ORDER_SEQ, FK_PRODUCT_SEQ, PRODUCT_NAME, QUANTITY ) "
					   + " values(?, ?, ?, ?) ";
			
			
			for(ProductVO product :cart.keySet()) {
				
				PreparedStatement preparedStatement = connection.prepareStatement(sql);

				preparedStatement.setInt(1, order_seq);
				preparedStatement.setInt(2, product.getProduct_seq());
				preparedStatement.setString(3, product.getProduct_name());
				preparedStatement.setInt(4, cart.get(product));

	
				System.out.println("inserting product list");
				int temp = preparedStatement.executeUpdate();
				
				if(temp != 1) {
					connection.rollback();
					return 0;
				}
				
				result += temp;
				
			}
			


		} finally {
			close();
		}

		return result;

	}
	
	

	@Override
	public int insertOrder(int member_seq, String postcode, String address, String address_detail, String address_extra,
			int totalAmount, Map<ProductVO, Integer> cart) throws SQLException {
		int result = 0;

		try {
			conn = ds.getConnection();
			conn.setAutoCommit(false);

			String sql = " insert into "
					+ " tbl_order(ORDER_SEQ, TOTAL_PAY, POSTCODE, ADDRESS, ADDRESS_DETAIL, ADDRESS_EXTRA, REGISTERDAY, DELIVERY_STATUS, FK_MEMBER_SEQ) "
					   + " values(order_seq.nextval, ?, ?, ?, ?, ? , sysdate, 0, ?) ";

			pstmt = conn.prepareStatement(sql, new String[] {"order_seq"});

			pstmt.setInt(1, totalAmount);
			pstmt.setString(2, postcode);
			pstmt.setString(3, address);
			pstmt.setString(4, address_detail);
			pstmt.setString(5, address_extra);
			pstmt.setInt(6, member_seq);

			
			System.out.println("inserting order");
			result = pstmt.executeUpdate();
			
			if(result != 1) {
				conn.rollback();
				return result;
			}
			
			
			ResultSet rs = pstmt.getGeneratedKeys();
			
            int order_seq = -1;
            if(rs.next()) {
            	order_seq = rs.getInt(1);
            }
            
            
			sql = " insert into "
					+ " tbl_product_list(FK_ORDER_SEQ, FK_PRODUCT_SEQ, PRODUCT_NAME, QUANTITY ) "
					   + " values(?, ?, ?, ?) ";
			
			
			int total = 0;
			for(ProductVO product :cart.keySet()) {
				
				
				pstmt = conn.prepareStatement(sql);

				pstmt.setInt(1, order_seq);
				pstmt.setInt(2, product.getProduct_seq());
				pstmt.setString(3, product.getProduct_name());
				pstmt.setInt(4, cart.get(product));

	
				System.out.println("inserting product list");
				int temp = pstmt.executeUpdate();
				
				if(temp != 1) {
					conn.rollback();
					return 0;
				}
				
				total += temp;
				
			}
			
			if(total == cart.size()) {
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
