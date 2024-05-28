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

			String sql = " select ceil(count(*)/?) "
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

	


	public List<ProductVO> getAllProduct() throws SQLException {
		List<ProductVO> productList = new ArrayList<>();

		try {
			conn = ds.getConnection();

			String sql = " select * "
					+ " from tbl_product ";

			pstmt = conn.prepareStatement(sql);

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

	
	//상품등록
	@Override
	public int productregister(ProductVO pvo) throws Exception {
		
		int result = 0;
         
         try {
            conn = ds.getConnection();
            
            String sql = " insert into tbl_product(product_seq ,FK_MANUFACTURER_SEQ, product_name , description ,base_price , stock , main_pic , discription_pic , product_type , discount_type , discount_number ) "
                     + " values(product_seq.nextval,?,?,?,?,?,?,?,?,?,?) " ;
            
            pstmt = conn.prepareStatement(sql);
            
            //pstmt.setInt(1, pvo.getFk_manufacturer_seq());
            pstmt.setInt(1, pvo.getFk_manufacturer_seq());
            pstmt.setString(2, pvo.getProduct_name());
            pstmt.setString(3, pvo.getDescription());
            pstmt.setFloat(4, pvo.getBase_price());    
            pstmt.setInt(5, pvo.getStock()); 
            pstmt.setString(6, pvo.getMain_pic());    
            pstmt.setString(7, pvo.getDescription_pic()); 
            pstmt.setInt(8, pvo.getProduct_type());
            pstmt.setString(9, pvo.getDiscount_type());
            pstmt.setFloat(10, pvo.getDiscount_amount());
           
            result = pstmt.executeUpdate();
         }catch (SQLException e) {
    			e.printStackTrace();
    			//throw new Exception("Database error: " + e.getMessage(), e);
         } finally {
            close();
         }
         
         return result;

	}//end of 	public int productregister(ProductVO pvo) throws SQLException--------------

	
	@Override
	public int updateProduct(ProductVO pvo) throws SQLException {
	    int result = 0;
	    
	    try {
	        conn = ds.getConnection();
	        
	        String sql = " update tbl_product set product_name = ?, description = ?, base_price = ?, stock = ?, main_pic = ?, discription_pic = ?, product_type = ?, discount_type = ?, discount_number = ? , FK_MANUFACTURER_SEQ=? "
	        		   + " where product_seq = ? " ;
	                
	        pstmt = conn.prepareStatement(sql);
	        
	        pstmt.setString(1, pvo.getProduct_name());
            pstmt.setString(2, pvo.getDescription());
            pstmt.setFloat(3, pvo.getBase_price());    
            pstmt.setInt(4, pvo.getStock()); 
            pstmt.setString(5, pvo.getMain_pic());    
            pstmt.setString(6, pvo.getDescription_pic()); 
            pstmt.setInt(7, pvo.getProduct_type());
            pstmt.setString(8, pvo.getDiscount_type());
            pstmt.setFloat(9, pvo.getDiscount_amount());
            pstmt.setInt(10, pvo.getFk_manufacturer_seq());
	        pstmt.setInt(11, pvo.getProduct_seq());
	        
	        
	        System.out.println("sql :" + sql);
	        System.out.println("product_name :" +  pvo.getProduct_name());
	        System.out.println("getDescription :" + pvo.getDescription());
	        System.out.println("getBase_price :" + pvo.getBase_price());
	        System.out.println("getStock :" + pvo.getStock());
	        System.out.println("getMain_pic :" + pvo.getMain_pic());
	        System.out.println("getDescription_pic :" +  pvo.getDescription_pic());
	        System.out.println("getProduct_type :" + pvo.getProduct_type());
	        System.out.println("getDiscount_amount :" + pvo.getDiscount_amount());
	        System.out.println("getProduct_seq :" + pvo.getProduct_seq());
	        System.out.println("getFk_manufacturer_seq :" +  pvo.getFk_manufacturer_seq());
	        result = pstmt.executeUpdate();
	        
	    } finally {
	        close();
	    }
	    
	    return result;      
	}

	
	
	
//select 
	@Override
	public int productSelectBySeq(int product_seq) throws Exception {
		
		int productSelectBySeq = 0;
		
		try {
			conn = ds.getConnection();
			
			String sql = " select product_seq "
					   + " from tbl_product "
					   + " where product_seq = ? " ;
			
			pstmt = conn.prepareStatement(sql);
			
			pstmt.setInt(1, product_seq);
			
			rs = pstmt.executeQuery();
			
			if(rs.next()) {
				
				productSelectBySeq = rs.getInt(1);
				
			}
			 
			
		} finally {
			close();
		}
		
		return productSelectBySeq;
	}

	//상품삭제하기
	@Override
	public int deletedProduct(ProductVO productDelete) throws Exception {

		int result = 0;
		try {
			conn = ds.getConnection();
			String sql = " DELETE FROM tbl_product WHERE product_seq = ? " ;
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, productDelete.getProduct_seq());
			
			
			//오류확인용 시작//
			System.out.println("SQL: " + sql);
			System.out.println("Seq: " + productDelete.getProduct_seq());
			//오류확인용 끝//
			
			result = pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
			throw new Exception("Database error: " + e.getMessage(), e);
		} finally {
			close();
		}
		
		return result;
	}
	

}