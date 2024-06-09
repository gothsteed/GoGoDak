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
import oracle.net.aso.c;
import pager.Pager;
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
	public Pager<ProductVO> getProductByType(int type, int currentPage, int blockSize) throws SQLException {
		
		List<ProductVO> productList = new ArrayList<>();
		int totalCount = 0;

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
			
			String totalScountSql = " select count(*) "
					+ "            from tbl_product "
					+ "            where product_type= ? and exist_status = 1";
			
			pstmt = conn.prepareStatement(totalScountSql);
			pstmt.setInt(1, type);
			rs = pstmt.executeQuery();
			
				
			if(rs.next()) {
				totalCount = rs.getInt(1);
			}
			
			

		} finally {
			close();
		}

		return new Pager(productList, currentPage, blockSize, totalCount);
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
	public Pager<ProductVO> getProductList(String searchWord, int currentPage, int blockSize) throws SQLException {
		
		List<ProductVO> productList = new ArrayList<>();
		int totalCount = 0; 
		
		try {
			conn = ds.getConnection();
			
			String sql = "    SELECT * "
					+ "    FROM " + "    ( "
					+ "        SELECT rownum as rno, PRODUCT_SEQ, FK_MANUFACTURER_SEQ,"
					+ "   PRODUCT_NAME, DESCRIPTION, BASE_PRICE,  STOCK, MAIN_PIC, DISCRIPTION_PIC, PRODUCT_TYPE, FK_DISCOUNT_EVENT_SEQ,  "
					+ "    DISCOUNT_TYPE,  DISCOUNT_NUMBER "
					+ "        FROM " + "        ( "
					+ "            select rownum as rno,  "
					+ "       product_seq,  "
					+ "       fk_manufacturer_seq,  "
					+ "       product_name,  "
					+ "       description,  "
					+ "       base_price,  "
					+ "       stock,  "
					+ "       main_pic,  "
					+ "       discription_pic,  "
					+ "       product_type,  "
					+ "       discount_type,  "
					+ "       discount_number, "
					+ "       fk_discount_event_seq "
					+ "from tbl_product "
					+ "where product_name like '%' || ? || '%'  "
					+ "  and exist_status = 1 "
					+ ") V ";



			sql += " )T " + " WHERE T.rno between ? and ? ";
			

			
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, searchWord);
			pstmt.setLong(2, (currentPage * blockSize) - (blockSize - 1)); // 페이징처리 공식
			pstmt.setLong(3, (currentPage * blockSize));
			
			rs = pstmt.executeQuery();
			
			while(rs.next()) {
				ProductVO pvo = createProductVO(rs);
				
				productList.add(pvo);
			}
			
			String totalScountSql = " select count(*) "
					+ "            from tbl_product "
					+ "            where product_name like '%' || ? || '%' and exist_status = 1 ";
			
			pstmt = conn.prepareStatement(totalScountSql);
			pstmt.setString(1, searchWord);
			rs = pstmt.executeQuery();
			
				
			if(rs.next()) {
				totalCount = rs.getInt(1);
			}
			
			
			
			
		} finally {
			close();
		}
		
		//return new Pager(productList, );
		
		return new Pager<ProductVO>(productList, currentPage, blockSize, totalCount);
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



	@Override
	public Pager<ProductVO> getProductByDiscountEvent(int discount_event_Seq, int currentPage, int blockSize) throws SQLException {
		List<ProductVO> productList = new ArrayList<>();
		int totalPage = 0;

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
			
		

			String totalPageSql = " select count(*) "
					+ "            from tbl_product "
					+ "            where fk_discount_event_seq= ? and exist_status = 1";



			pstmt = conn.prepareStatement(totalPageSql);
			pstmt.setInt(1, discount_event_Seq);
			
			rs = pstmt.executeQuery();
			
			if(rs.next()) {
				totalPage = rs.getInt(1);
			}
			
		} finally {
			close();
		}

		return new Pager<ProductVO>(productList, currentPage, blockSize, totalPage);
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
	public Pager<ProductVO> getBrandProductList(String manufacturer_seq, int currentPage, int blockSize) throws SQLException {
		
		List<ProductVO> brandProductList = new ArrayList<>();
		int totalCount = 0;
		
		
		try {
			conn = ds.getConnection();
			
			String sql = " rownum as rno, manufacturer_name, product_seq, fk_manufacturer_seq, product_name, description, base_price, stock, main_pic, discription_pic, product_type, discount_type, discount_number, fk_discount_event_seq "
					+ " from "
					+ " (SELECT manufacturer_name, product_seq, fk_manufacturer_seq, product_name, description, base_price, stock, main_pic, discription_pic, product_type, discount_type, discount_number, fk_discount_event_seq "
					   + " FROM "
					   + " ( "
					   + "    select manufacturer_seq, manufacturer_name "
					   + "    from tbl_manufacturer "
					   + "    where manufacturer_seq = ? "
					   + " ) M "
					   + " JOIN tbl_product P "
					   + " ON P.fk_manufacturer_seq = M.manufacturer_seq"
					   + " where exist_status = 1 "
					   + " order by product_seq DESC) A"
					   + " WHERE A.rno between ? and ?   ";
			
			pstmt = conn.prepareStatement(sql);
			
			pstmt.setInt(1, Integer.parseInt(manufacturer_seq));
			pstmt.setLong(2, (currentPage * blockSize) - (blockSize - 1)); // 페이징처리 공식
			pstmt.setLong(3, (currentPage * blockSize));
			
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
			
			String totalScountSql = " SELECT count(*)  "
					+ "	 FROM   "
					+ "	(   "
					+ "	   select manufacturer_seq, manufacturer_name   "
					+ "	   from tbl_manufacturer   "
					+ "	  where manufacturer_seq = ?   "
					+ "	 ) M   "
					+ "	 JOIN tbl_product P   "
					+ "	 ON P.fk_manufacturer_seq = M.manufacturer_seq  "
					+ "	 where exist_status = 1   "
					+ "	 order by product_seq DESC ";
			
			pstmt = conn.prepareStatement(totalScountSql);
			pstmt.setString(1, manufacturer_seq);
			rs = pstmt.executeQuery();
			
				
			if(rs.next()) {
				totalCount = rs.getInt(1);
			}
			
			
		} finally {
			close();
		}
		
		return new Pager<ProductVO>(brandProductList, currentPage, blockSize, totalCount);
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

	@Override
	public List<ProductVO> getRecentProduct() throws SQLException {
		List<ProductVO> productList = new ArrayList<>();

		try {
			conn = ds.getConnection();

			String sql = "SELECT * "
					+ "FROM ( "
					+ "    SELECT * "
					+ "    FROM tbl_product "
					+ "    ORDER BY PRODUCT_SEQ DESC "
					+ ") "
					+ "WHERE ROWNUM <= 3 and exist_status = 1 ";

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

	@Override
	public int insertProductLike(int member_seq, int product_seq) throws SQLException {
		int result = 0;
		try {
			conn = ds.getConnection();
			String sql = " INSERT INTO tbl_product_like (FK_MEMBER_SEQ, FK_PRODUCT_SEQ) "
					+ "VALUES (?, ?) ";
			
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, member_seq);
			pstmt.setInt(2, product_seq);

			
			result = pstmt.executeUpdate();
		} finally {
			close();
		}
		
		return result;
	}

	@Override
	public int deletedProductLike(int member_seq, int product_seq) throws SQLException {
		int result = 0;
		try {
			conn = ds.getConnection();
			String sql = " DELETE FROM tbl_product_like "
					+ "WHERE FK_MEMBER_SEQ = ? AND FK_PRODUCT_SEQ = ? ";
			
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, member_seq);
			pstmt.setInt(2, product_seq);

			
			result = pstmt.executeUpdate();
		} finally {
			close();
		}
		
		return result;
	}

	@Override
	public int getLikeCount(int product_seq) throws SQLException {
		int count = 0;

		try {
			conn = ds.getConnection();

			String sql = "SELECT count(*) "
					+ " from tbl_product_like"
					+ " where fk_product_seq = ?  ";

			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, product_seq);

			rs = pstmt.executeQuery();

			while (rs.next()) {

				count = rs.getInt(1);
			} // end of while(rs.next())---------------------

		} finally {
			close();
		}

		return count;
	}

	@Override
	public boolean isLiked(int member_seq, int product_seq) throws SQLException {
		
		int count = 0;

		try {
			conn = ds.getConnection();

			String sql = "SELECT count(*) "
					+ " from tbl_product_like"
					+ " where fk_member_seq = ? and fk_product_seq=? ";

			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, member_seq);
			pstmt.setInt(2, product_seq);

			rs = pstmt.executeQuery();

			while (rs.next()) {

				count = rs.getInt(1);
				
			} // end of while(rs.next())---------------------

		} finally {
			close();
		}

		return count > 0 ? true : false;

	}

	@Override
	public List<ProductVO> getLikedProduct(int member_seq) throws SQLException {
		List<ProductVO> productList = new ArrayList<>();

		try {
			conn = ds.getConnection();

			String sql = " select * "
					+ " from tbl_product a "
					+ " join "
					+ " tbl_product_like b "
					+ " on a.product_seq = b.fk_product_seq "
					+ " where b.fk_member_seq = ? and exist_status = 1 ";

			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, member_seq);

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