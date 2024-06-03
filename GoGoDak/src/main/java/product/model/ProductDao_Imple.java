package product.model;

import java.io.UnsupportedEncodingException;
import java.security.GeneralSecurityException;
import java.security.NoSuchAlgorithmException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

import com.oracle.wls.shaded.org.apache.regexp.recompile;

import domain.Discount_eventVO;
import domain.ManufacturerVO;
import domain.ProductVO;
import domain.Product_listVO;
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
					+ "            where product_type = ? and exist_status = 1) V ";



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
					+ "            where product_type= ? and exist_status = 1";



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
					   + " where product_name like '%' || ? || '%' and exist_status = 1 ";
			
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
            
            if(pvo.getFk_manufacturer_seq() == 0) {
            	pstmt.setNull(1, java.sql.Types.INTEGER );
            }else {
            	pstmt.setNull(1, pvo.getFk_manufacturer_seq());
            }
            
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


	public Product_listVO memberOrderDetail(String id) throws SQLException, NoSuchAlgorithmException, UnsupportedEncodingException, GeneralSecurityException {
	    Product_listVO pvo = null;
	    Connection conn = null;
	    PreparedStatement pstmt = null;
	    ResultSet rs = null;

	    try {
	        conn = ds.getConnection();

	        String sql = " SELECT "
	                + " o.order_seq, o.total_pay, o.postcode AS order_postcode, o.address AS order_address, o.address_detail AS order_address_detail, o.address_extra AS order_address_extra, o.delivery_status, "
	                + " m.member_seq, m.id, m.name, m.tel, m.point, m.postcode AS member_postcode, m.address AS member_address, m.address_detail AS member_address_detail, m.address_extra AS member_address_extra, "
	                + " pl.fk_order_seq, pl.fk_product_seq, pl.product_name AS list_product_name, "
	                + " p.product_seq, p.product_name, p.description, p.base_price, p.stock, p.product_type, p.fk_discount_event_seq, p.discount_type, p.discount_number "
	                + " FROM tbl_order o "
	                + " JOIN tbl_member m ON o.fk_member_seq = m.member_seq "
	                + " JOIN tbl_product_list pl ON o.order_seq = pl.fk_order_seq "
	                + " JOIN tbl_product p ON pl.fk_product_seq = p.product_seq "
	                +  " WHERE m.id = ? ";

	        pstmt = conn.prepareStatement(sql);
	        pstmt.setString(1, id);
	        rs = pstmt.executeQuery();

	        if (rs.next()) {
	            pvo = new Product_listVO();

	            // Order 정보 설정
	            pvo.setOrder_seq(rs.getInt("order_seq"));
	            pvo.setTotal_pay(rs.getFloat("total_pay"));
	            pvo.setPostcode(rs.getString("order_postcode"));
	            pvo.setAddress(rs.getString("order_address"));
	            pvo.setAddress_detail(rs.getString("order_address_detail"));
	            pvo.setAddress_extra(rs.getString("order_address_extra"));
	            pvo.setDeliverystatus(rs.getInt("delivery_status"));

	            // Member 정보 설정
	            pvo.setFk_member_seq(rs.getInt("member_seq"));
	            pvo.setId(rs.getString("id"));
	            pvo.setName(rs.getString("name"));
	            pvo.setTel(aes.decrypt(rs.getString("tel")));
	            pvo.setPoint(rs.getInt("point"));
	            pvo.setMember_postcode(rs.getString("member_postcode"));
	            pvo.setMember_address(rs.getString("member_address"));
	            pvo.setMember_address_detail(rs.getString("member_address_detail"));
	            pvo.setMember_address_extra(rs.getString("member_address_extra"));

	            // Product List 정보 설정
	            pvo.setFk_order_seq(rs.getInt("fk_order_seq"));
	            pvo.setFk_product_seq(rs.getInt("fk_product_seq"));
	            pvo.setList_product_name(rs.getString("list_product_name"));

	            // Product 정보 설정
	            pvo.setProduct_seq(rs.getInt("product_seq"));
	            pvo.setProduct_name(rs.getString("product_name"));
	            pvo.setDescription(rs.getString("description"));
	            pvo.setBase_price(rs.getFloat("base_price"));
	            pvo.setStock(rs.getInt("stock"));
	            pvo.setProduct_type(rs.getInt("product_type"));
	            pvo.setFk_discount_event_seq(rs.getInt("fk_discount_event_seq"));
	            pvo.setDiscount_type(rs.getString("discount_type"));
	            pvo.setDiscount_amount(rs.getFloat("discount_number"));
	        }
	    } finally {
	        // 자원 해제
	        if (rs != null) try { rs.close(); } catch (SQLException e) { e.printStackTrace(); }
	        if (pstmt != null) try { pstmt.close(); } catch (SQLException e) { e.printStackTrace(); }
	        if (conn != null) try { conn.close(); } catch (SQLException e) { e.printStackTrace(); }
	    }

	    return pvo;
	}

	@Override
	public int getEventProductTotalPage(int discount_event_Seq, int blockSize) throws SQLException {
		int totalPage = 0;

		try {
			conn = ds.getConnection();

			String sql = " select ceil(count(*)/?) "
					+ "            from tbl_product "
					+ "            where fk_discount_event_seq= ? and exist_status = 1";



			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, blockSize);
			pstmt.setInt(2, discount_event_Seq);
			
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
	public List<ProductVO> getProductByDiscountEvent(int discount_event_Seq, int currentPage, int blockSize) throws SQLException {
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
					+ "            where fk_discount_event_seq = ? and exist_status = 1 ) V ";



			sql += " )T " + " WHERE T.rno between ? and ? ";

			pstmt = conn.prepareStatement(sql);

	
			pstmt.setInt(1, discount_event_Seq);
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
	public List<ProductVO> getProductByDiscountEvent(int discount_event_Seq) throws SQLException {
		List<ProductVO> productList = new ArrayList<>();

		try {
			conn = ds.getConnection();

			String sql = " select * "
					+ "  from tbl_product "
					+ "  where fk_discount_event_seq = ? and exist_status = 1 ";


			pstmt = conn.prepareStatement(sql);

	
			pstmt.setInt(1, discount_event_Seq);


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
	public List<ProductVO> getBrandProductList(String manufacturer_seq) throws SQLException {
		
		List<ProductVO> brandProductList = new ArrayList<>();
		
		try {
			conn = ds.getConnection();
			
			String sql = " SELECT manufacturer_name, product_seq, fk_manufacturer_seq, product_name, description, base_price, stock, main_pic, discription_pic, product_type, discount_type, discount_number "
					   + " FROM "
					   + " ( "
					   + "    select manufacturer_seq, manufacturer_name "
					   + "    from tbl_manufacturer "
					   + "    where manufacturer_seq = ? "
					   + " ) M "
					   + " JOIN tbl_product P "
					   + " ON P.fk_manufacturer_seq = M.manufacturer_seq"
					   + " where exist_status = 1 ";
			
			pstmt = conn.prepareStatement(sql);
			
			pstmt.setInt(1, Integer.parseInt(manufacturer_seq));
			
			rs = pstmt.executeQuery();
			
			while(rs.next()) {
				ProductVO pvo = new ProductVO();
				ManufacturerVO mvo = new ManufacturerVO();
				
				mvo.setManufacturer_name("manufacturer_name");
				pvo.setMadto(mvo);
				
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
				
				brandProductList.add(pvo);
			}
			
		} finally {
			close();
		}
		
		return brandProductList;
	}


	//상품수정하기
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
            
            if(pvo.getFk_manufacturer_seq() == 0) {
            	pstmt.setNull(10, java.sql.Types.INTEGER );
            }else {
            	pstmt.setNull(10, pvo.getFk_manufacturer_seq());
            }
	     
	        pstmt.setInt(11, pvo.getProduct_seq());

	        
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
					   + " where product_seq = ? and exist_status = 1 " ;
			
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
			String sql = " UPDATE tbl_PRODUCT "
					+ "SET exist_status = 0 "
					+ "WHERE product_seq = ?" ;
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


	
	@Override
	public List<Map<String, String>> selectStoreMap() throws SQLException {
		 List<Map<String, String>> storeMapList = new ArrayList<>();
		try {
	            conn = ds.getConnection();
	            
	            String sql = " select manufacturer_seq,manufacturer_name,manufacturer_tel,location,manufacturer_url,manufacturer_img,lat,lng "
	            		+ " from tbl_manufacturer "
	            		+ " order by manufacturer_seq asc ";
	            
	            pstmt = conn.prepareStatement(sql);
	            
	            rs = pstmt.executeQuery();
	            
	            while(rs.next()) {
	               Map<String, String> map = new HashMap<>();
	               map.put("manufacturer_seq", rs.getString("manufacturer_seq"));
	               map.put("manufacturer_name", rs.getString("manufacturer_name"));
	               map.put("manufacturer_tel", rs.getString("manufacturer_tel"));
	               map.put("location", rs.getString("location"));
	               map.put("manufacturer_url", rs.getString("manufacturer_url"));
	               map.put("manufacturer_img", rs.getString("manufacturer_img"));
	               map.put("LAT", rs.getString("LAT"));
	               map.put("LNG", rs.getString("LNG"));
	            
	                           
	               storeMapList.add(map); 
	            }//end of while -----------------------------
	            
	         } finally {
	            close();
	         }
	         
	         return storeMapList;   

	}//end of public List<Map<String, String>> selectStoreMap() throws SQLException-----------------

	@Override
	public List<Map<String, String>> selectStoreMapByLocation(String locationParam) throws SQLException {
		 List<Map<String, String>> storeMapList = new ArrayList<>();
			try {
		            conn = ds.getConnection();
		            
		            String sql = " select manufacturer_seq,manufacturer_name,manufacturer_tel,location,manufacturer_url,manufacturer_img,lat,lng "
		            		+ " from tbl_manufacturer "
		            		+ " order by manufacturer_seq asc ";
		            
		            pstmt = conn.prepareStatement(sql);
		            
		            rs = pstmt.executeQuery();
		            
		            while(rs.next()) {
		               Map<String, String> map = new HashMap<>();
		               map.put("manufacturer_seq", rs.getString("manufacturer_seq"));
		               map.put("manufacturer_name", rs.getString("manufacturer_name"));
		               map.put("manufacturer_tel", rs.getString("manufacturer_tel"));
		               map.put("location", rs.getString("location"));
		               map.put("manufacturer_url", rs.getString("manufacturer_url"));
		               map.put("manufacturer_img", rs.getString("manufacturer_img"));
		               map.put("LAT", rs.getString("LAT"));
		               map.put("LNG", rs.getString("LNG"));
		            
		                           
		               storeMapList.add(map); 
		            }//end of while -----------------------------
		            
		         } finally {
		            close();
		         }
		         
		         return storeMapList;   

	}//public List<Map<String, String>> selectStoreMapByLocation(String locationParam) throws SQLException-------------

	@Override
	public List<ProductVO> getDiscountProduct() throws SQLException {
		List<ProductVO> productList = new ArrayList<>();

		try {
			conn = ds.getConnection();

			String sql = " select * "
					+ " from tbl_product"
					+ " where discount_type is not null and exist_status = 1 ";

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

	
	

	

}