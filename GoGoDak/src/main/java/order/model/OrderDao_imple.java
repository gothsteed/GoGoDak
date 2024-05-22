package order.model;

import java.io.UnsupportedEncodingException;
import java.security.GeneralSecurityException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

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
	
	

	@Override
	public int insertOrder(int member_seq, String postcode, String address, String address_detail, String address_extra,
			int totalAmount) throws SQLException {
		int result = 0;

		try {
			conn = ds.getConnection();

			String sql = " insert into "
					+ " tbl_order(ORDER_SEQ, TOTAL_PAY, POSTCODE, ADDRESS, ADDRESS_DETAIL, ADDRESS_EXTRA, REGISTERDAY, DELIVERY_STATUS, FK_MEMBER_SEQ) "
					   + " values(order_seq.nextval, ?, ?, ?, ?, ? , sysdate, 0, ?) ";

			pstmt = conn.prepareStatement(sql);

			pstmt.setInt(1, totalAmount);
			pstmt.setString(2, postcode);
			pstmt.setString(3, address);
			pstmt.setString(4, address_detail);
			pstmt.setString(5, address_extra);
			pstmt.setInt(6, member_seq);

			
			
			result = pstmt.executeUpdate();

		} finally {
			close();
		}

		return result;

	}

}
