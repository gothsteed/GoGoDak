package product.model;

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

import domain.ProductVO;
import util.security.AES256;
import util.security.SecretMyKey;

public class ProductDao_Imple implements ProductDao {
	
	private DataSource ds;
	private Connection conn;
	private PreparedStatement pstmt;
	private ResultSet rs;

	private AES256 aes;
	
	public ProductDao_Imple() {
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
	public List<ProductVO> getProductByType(int type) throws SQLException {
		
		List<ProductVO> productList = new ArrayList<ProductVO>();
		
		try {
			
			conn = ds.getConnection();
			
	         String sql = " select *  "
	         		+ " from tbl_product"
	         		+ " where product_type = ? ";
	         
	         
	        pstmt = conn.prepareStatement(sql);
	        pstmt.setInt(1, type);
	        rs = pstmt.executeQuery();
	        
	        while (rs.next()) {
	        
	        	ProductVO prodcut = new ProductVO();
	        	
	        	prodcut.setProduct_Seq(rs.getInt("PRODUCT_SEQ"));
	        	prodcut.setFk_manufacturer_seq(rs.getInt("FK_MANUFACTURER_SEQ"));
				prodcut.setProduct_name(rs.getString("PRODUCT_NAME"));
				prodcut.setDescription(rs.getString("DESCRIPTION"));
				prodcut.setPrice(rs.getFloat("PRICE"));
				prodcut.setStock(rs.getInt("STOCK"));
				prodcut.setMain_pic(rs.getString("MAIN_PIC"));
				prodcut.setDescription_pic(rs.getString("DISCRIPTION_PIC"));
				prodcut.setProduct_type(rs.getInt("PRODUCT_TYPE"));
				
				
				productList.add(prodcut);
	        	
			}
	        
	         
			
			
		}
		finally {
			close();
		}
		
		
		return productList;
	}
	
	
	
	

}
