package product.model;

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

import com.oracle.wls.shaded.org.apache.regexp.recompile;

import domain.DiscountVO;
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
	
	private ProductVO createProductVO(ResultSet rs) throws SQLException {
    	ProductVO prodcut = new ProductVO();
    	
    	prodcut.setProduct_seq(rs.getInt("PRODUCT_SEQ"));
    	prodcut.setFk_manufacturer_seq(rs.getInt("FK_MANUFACTURER_SEQ"));
		prodcut.setProduct_name(rs.getString("PRODUCT_NAME"));
		prodcut.setDescription(rs.getString("DESCRIPTION"));
		prodcut.setBase_price(rs.getFloat("BASE_PRICE"));
		prodcut.setStock(rs.getInt("STOCK"));
		prodcut.setMain_pic(rs.getString("MAIN_PIC"));
		prodcut.setDescription_pic(rs.getString("DISCRIPTION_PIC"));
		prodcut.setProduct_type(rs.getInt("PRODUCT_TYPE"));
		prodcut.setDiscount_type(rs.getString("DISCOUNT_TYPE"));
		prodcut.setDiscount_amount(rs.getFloat("DISCOUNT_NUMBER"));
		
		/*
		 * System.out.println(rs.getString("DISCOUNT_TYPE"));
		 * System.out.println(prodcut.getDiscount_type());
		 * System.out.println(prodcut.getDiscount_amount());
		 */
		
		return prodcut;
		
	}
	
	/*
	 * private DiscountVO createDiscountVO(ResultSet rs) throws SQLException {
	 * 
	 * DiscountVO discountVO = new DiscountVO();
	 * 
	 * discountVO.setDiscount_seq(rs.getInt("discount_seq"));
	 * discountVO.setDiscont_type(rs.getString("discount_type"));
	 * discountVO.setDiscount_number(rs.getFloat("discount_number"));
	 * 
	 * return discountVO; }
	 */

	@Override
	public List<ProductVO> getProductByType(int type, int currentPage, int blockSize) throws SQLException {
		
		List<ProductVO> productList = new ArrayList<>();

		try {
			conn = ds.getConnection();

			String sql = "    SELECT * "
					+ "    FROM " + "    ( "
					+ "        SELECT rownum as rno, PRODUCT_SEQ, FK_MANUFACTURER_SEQ,"
					+ "   PRODUCT_NAME, DESCRIPTION, BASE_PRICE,  STOCK, MAIN_PIC, DISCRIPTION_PIC, PRODUCT_TYPE, FK_DISCOUNT_EVENT_SEQ,  "
					+ "    DISCOUNT_TYPE,  DISCOUNT_NUMBER "
					+ "        FROM " + "        ( "
					+ "            select * "
					+ "            from tbl_product "
					+ "            where product_type = ? ) V ";



			sql += " )T " + " WHERE T.rno between ? and ? ";

			pstmt = conn.prepareStatement(sql);

	
			pstmt.setInt(1, type);
			pstmt.setLong(2, (currentPage * blockSize) - (blockSize - 1)); // 페이징처리 공식
			pstmt.setLong(3, (currentPage * blockSize));


			rs = pstmt.executeQuery();

			while (rs.next()) {

				ProductVO productVO = createProductVO(rs);

				productList.add(productVO);
			} // end of while(rs.next())---------------------

		} finally {
			close();
		}

		return productList;
	}

	@Override
	public int getTotalPage(int productType, int blockSize) throws SQLException {
		int totalPage = 0;

		try {
			conn = ds.getConnection();

			String sql = " select count(*)/? "
					+ "            from tbl_product "
					+ "            where product_type= ?";



			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, blockSize);
			pstmt.setInt(2, productType);
			
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
	public ProductVO getProductBySeq(int product_seq) throws SQLException {
		ProductVO productVO = null;
		
		
		try {
			conn = ds.getConnection();

			String sql = " select * "
					+ " from tbl_product "
					+ " where product_seq = ? ";



			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, product_seq);
			
			rs = pstmt.executeQuery();

			if (rs.next()) {
				productVO = createProductVO(rs);
				
			} // end of while(rs.next())---------------------

		} finally {
			close();
		}
		
		return productVO;

	}

	@Override
	public List<ProductVO> getProductList(String searchWord) throws SQLException {
		
		List<ProductVO> productList = new ArrayList<>();
		
		try {
			conn = ds.getConnection();
			
			String sql = " select product_seq, fk_manufacturer_seq, product_name, description, base_price, stock, main_pic, discription_pic, product_type, discount_type, discount_number "
					   + " from tbl_product "
					   + " where product_name like '%' || ? || '%' ";
			
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, searchWord);
			
			rs = pstmt.executeQuery();
			
			while(rs.next()) {
				ProductVO pvo = new ProductVO();
				
				pvo.setProduct_seq(rs.getInt("product_seq"));
				pvo.setFk_manufacturer_seq(rs.getInt("fk_manufacturer_seq"));
				pvo.setProduct_name(rs.getString("product_name"));
				pvo.setDescription(rs.getString("description"));
				pvo.setBase_price(rs.getFloat("base_price"));
				pvo.setStock(rs.getInt("stock"));
				pvo.setMain_pic(rs.getString("main_pic"));
				pvo.setDescription_pic(rs.getString("discription_pic"));
				pvo.setDiscount_type(rs.getString("product_type"));
				pvo.setDiscount_type(rs.getString("discount_type"));
				pvo.setDiscount_amount(rs.getFloat("discount_number"));
				
				productList.add(pvo);
			}
			
		} finally {
			close();
		}
		
		return productList;
	}

	


	
	
	
	
	

}