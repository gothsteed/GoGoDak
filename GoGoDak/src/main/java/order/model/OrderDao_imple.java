package order.model;

import java.io.UnsupportedEncodingException;
import java.security.GeneralSecurityException;
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

import domain.MemberPurchaseByMonthChart;
import domain.MemberVO;
import domain.MemebrPurchaseChart;
import domain.OrderVO;
import domain.ProductVO;

import oracle.net.aso.c;

import domain.Product_listVO;

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
	
	private ProductVO createProduct(ResultSet rs) throws SQLException {
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
		prodcut.setQuantity(rs.getInt("quantity"));
		
		return prodcut;
	}
	
	private OrderVO creaOrder(ResultSet rs) throws SQLException {
		OrderVO order = new OrderVO();
		
		order.setOrder_seq(rs.getInt("order_seq"));
		order.setFk_member_seq(rs.getInt("fk_member_seq"));
		order.setPostcode(rs.getString("postcode"));
		order.setAddress(rs.getString("address"));
		order.setAddress_detail(rs.getString("address_detail"));
		order.setAddress_extra(rs.getString("address_extra"));
		order.setTotal_pay(rs.getInt("total_pay"));
		order.setRegisterday(rs.getDate("registerday"));
		order.setDelivery_message(rs.getString("delivery_message"));
		order.setDeliverystatus(rs.getInt("DELIVERY_STATUS"));
		
		return order;
	}
	
	private MemberVO createMember(ResultSet rs) throws SQLException {
		MemberVO memberTemp = new MemberVO();
		
		memberTemp.setMember_seq(rs.getInt("MEMBER_SEQ"));
		
		try {
			memberTemp.setEmail(aes.decrypt(rs.getString("EMAIL")));
		} catch (UnsupportedEncodingException | GeneralSecurityException | SQLException e) {
			e.printStackTrace();
		}
		memberTemp.setId(rs.getString("ID"));
		memberTemp.setPassword(rs.getString("PASSWORD"));
		memberTemp.setName(rs.getString("NAME"));
		
		try {
			memberTemp.setTel(aes.decrypt(rs.getString("TEL")));
		} catch (UnsupportedEncodingException | GeneralSecurityException | SQLException e) {
			e.printStackTrace();
		}
		
		memberTemp.setJubun(rs.getString("JUBUN"));
		memberTemp.setPoint(rs.getInt("point"));
		memberTemp.setRegisterDate(rs.getDate("REGISTERDAY"));
		memberTemp.setExist_status(rs.getInt("EXIST_STATUS"));
		memberTemp.setActive_status(rs.getInt("ACTIVE_STATUS"));
		memberTemp.setLast_password_change(rs.getDate("LAST_PASSWORD_CHANGE"));
		memberTemp.setPostcode(rs.getString("postcode"));
		memberTemp.setAddress(rs.getString("address"));
		memberTemp.setAddress_detail(rs.getString("address_detail"));
		memberTemp.setAddress_extra(rs.getString("address_extra"));
		
		return memberTemp;
		
	}
	
	private OrderVO createOrderWithMember(ResultSet rs) throws SQLException {
		OrderVO order = new OrderVO();
		
		order.setOrder_seq(rs.getInt("order_seq"));
		order.setFk_member_seq(rs.getInt("fk_member_seq"));
		order.setPostcode(rs.getString("postcode"));
		order.setAddress(rs.getString("address"));
		order.setAddress_detail(rs.getString("address_detail"));
		order.setAddress_extra(rs.getString("address_extra"));
		order.setTotal_pay(rs.getInt("total_pay"));
		order.setDelivery_message(rs.getString("delivery_message"));
		order.setDeliverystatus(rs.getInt("DELIVERY_STATUS"));
		
		MemberVO member = createMember(rs);
		order.setMdto(member);
		
		return order;
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
	public int[] insertOrder(int member_seq, String postcode, String address, String address_detail, String address_extra, String  delivery_message,
			int totalAmount, Map<ProductVO, Integer> cart) throws SQLException {
		int result = 0;
		int[] resultArr = new int[2];

		try {
			conn = ds.getConnection();
			conn.setAutoCommit(false);

			String sql = " insert into "
					+ " tbl_order(ORDER_SEQ, TOTAL_PAY, POSTCODE, ADDRESS, ADDRESS_DETAIL, ADDRESS_EXTRA, REGISTERDAY, DELIVERY_STATUS, FK_MEMBER_SEQ, delivery_message) "
					   + " values(order_seq.nextval, ?, ?, ?, ?, ? , sysdate, 0, ?, ?) ";

			pstmt = conn.prepareStatement(sql, new String[] {"order_seq"});

			pstmt.setInt(1, totalAmount);
			pstmt.setString(2, postcode);
			pstmt.setString(3, address);
			pstmt.setString(4, address_detail);
			pstmt.setString(5, address_extra);
			pstmt.setInt(6, member_seq);
			pstmt.setString(7, delivery_message);

			
			System.out.println("inserting order");
			result = pstmt.executeUpdate();
			
			if(result != 1) {
				conn.rollback();
				return resultArr;
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
					return resultArr;
				}
				
				total += temp;
				
			}
			
			if(total == cart.size()) {
				result = 1;
	            resultArr[0] = result;
	            resultArr[1] = order_seq;
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
		

		return resultArr;

	}

	@Override
	public OrderVO getOrderBySeq(int order_seq) throws SQLException {
		
		OrderVO order= null;
		
		try {
			conn = ds.getConnection();
			
			String sql = " select * "
					   + " from tbl_order "
					   + " where order_seq = ? " ;
			
			pstmt = conn.prepareStatement(sql);
			
			pstmt.setInt(1, order_seq);
			
			rs = pstmt.executeQuery();
			
			if(!rs.next()) {
				return null;
			}
			
			order = creaOrder(rs);
			 
			
		} finally {
			close();
		}
		
		return order;
	}

	@Override
	public OrderVO getOrderWithMember(int order_seq) throws SQLException {
		OrderVO order= null;
		
		try {
			conn = ds.getConnection();
			
			String sql = "SELECT  "
					+ "  m.*, o.*  "
					+ " FROM  "
					+ "    tbl_member m "
					+ " JOIN  "
					+ "     tbl_order o  "
					+ " ON  "
					+ "    m.member_seq = o.fk_member_seq "
					+ " WHERE order_seq = ?";
			
			pstmt = conn.prepareStatement(sql);
			
			pstmt.setInt(1, order_seq);
			
			rs = pstmt.executeQuery();
			
			if(!rs.next()) {
				return null;
			}
			
			order = createOrderWithMember(rs);
			 
			
		} finally {
			close();
		}
		
		return order;
	}
	
	
	
	@Override
	public List<ProductVO> getProductList(int order_seq) throws SQLException {
		List<ProductVO> productList = new ArrayList<ProductVO>();
		
		try {
			conn = ds.getConnection();
			
			String sql = " SELECT "
					+ " pl.*, p.* "
					+ "FROM  "
					+ "    tbl_product_list pl "
					+ "JOIN  "
					+ "    tbl_product p "
					+ "ON  "
					+ "    pl.fk_product_seq = p.product_seq  "
					+ " WHERE fk_order_seq = ? ";
			
			
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, order_seq);
			rs = pstmt.executeQuery();
			
			while(rs.next()) {
				productList.add(createProduct(rs));
			}
			
			
		}
		finally {
			close();
		}
		
		
		
		return productList;
	}


	@Override
	public int updateDelivery_status(int order_seq) throws SQLException {
		int result = 0;
		
		try {
			
			conn = ds.getConnection();
			
			String sql = " UPDATE TBL_ORDER "
					+ " SET DELIVERY_STATUS = 1 "
					+ " where order_seq = ? ";
			
			
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, order_seq);
			result = pstmt.executeUpdate();
		
			
			
		} finally {
			close();
		}
		
		return result;
	}


	
	
	


	@Override
	public List<OrderVO> getLoginuserList(int fk_member_seq) throws SQLException {
	    List<OrderVO> orderList = new ArrayList<>();

	    try {
	        conn = ds.getConnection();

	        // 첫 번째 쿼리: 주문 정보 가져오기
	        String orderSql = "SELECT * FROM tbl_order WHERE fk_member_seq = ? "
	        		+ " order by REGISTERDAY desc ";
	        pstmt = conn.prepareStatement(orderSql);
	        pstmt.setInt(1, fk_member_seq);
	        rs = pstmt.executeQuery();

	        while (rs.next()) {
	            OrderVO order = new OrderVO();
	            order.setOrder_seq(rs.getInt("order_seq"));
	            order.setFk_member_seq(rs.getInt("fk_member_seq"));
	            order.setTotal_pay(rs.getFloat("total_pay"));
	            order.setPostcode(rs.getString("postcode"));
	            order.setAddress(rs.getString("address"));
	            order.setAddress_detail(rs.getString("address_detail"));
	            order.setAddress_extra(rs.getString("address_extra"));
	            order.setDeliverystatus(rs.getInt("delivery_status"));
	            order.setRegisterday(rs.getDate("registerday"));
	            order.setDelivery_message(rs.getString("delivery_message"));

	            // 주문별 상품 목록 설정
	            List<Product_listVO> productList = new ArrayList<>();
	            String productSql = "SELECT p.product_seq, p.product_name, p.description, p.base_price, pl.fk_order_seq, pl.fk_product_seq " +
	                                "FROM tbl_product p " +
	                                "JOIN tbl_product_list pl ON p.product_seq = pl.fk_product_seq " +
	                                "WHERE pl.fk_order_seq = ? ";
	            PreparedStatement pstmt2 = conn.prepareStatement(productSql);
	            pstmt2.setInt(1, order.getOrder_seq());
	            ResultSet rs2 = pstmt2.executeQuery();

	            while (rs2.next()) {
	                Product_listVO product = new Product_listVO();
	                product.setOrder_seq(rs2.getInt("fk_order_seq"));
	                product.setProduct_seq(rs2.getInt("fk_product_seq"));
	                product.setProduct_name(rs2.getString("product_name"));
	                product.setDescription(rs2.getString("description"));
	                product.setBase_price(rs2.getFloat("base_price"));
	                // 필요한 다른 필드도 설정
	                productList.add(product);
	            }
	            rs2.close();
	            pstmt2.close();

	            order.setProductList(productList);  // 주문에 상품 목록 추가
	            orderList.add(order);
	        }

	    } finally {
	        if (rs != null) rs.close();
	        if (pstmt != null) pstmt.close();
	        if (conn != null) conn.close();
	    }

	    return orderList;
	}
	@Override
	public int updateDelivery_status(int order_seq, int status) throws SQLException {
	    int result = 0;

	    try {
	        conn = ds.getConnection();
	        
	        String sql = " UPDATE tbl_order SET DELIVERY_STATUS = ? WHERE order_seq = ? ";
	        System.out.println("Executing SQL: " + sql); // 로그 추가
	        System.out.println("Parameters - status: " + status + ", order_seq: " + order_seq); // 로그 추가

	        pstmt = conn.prepareStatement(sql);
	        pstmt.setInt(1, status);
	        pstmt.setInt(2, order_seq);
	        result = pstmt.executeUpdate();
	        System.out.println("Update result: " + result); // 로그 추가
	    } finally {
	        close();
	    }

	    return result;
	}

	@Override
	public List<MemebrPurchaseChart> memberPurchase_byCategory(int member_seq) throws SQLException {
		List<MemebrPurchaseChart> myPurchaseChart = new ArrayList<>();
		
		
		try {
			conn = ds.getConnection();
			
			String sql = "SELECT  "
					+ "    P.product_type,  "
					+ "    COUNT(*) AS product_count, "
					+ "    round((COUNT(*) * 100.0 / total_count), 1) AS percentage "
					+ " FROM "
					+ " ( "
					+ "    SELECT * "
					+ "    FROM tbl_order "
					+ "    WHERE fk_member_seq = ? "
					+ " ) O "
					+ " JOIN "
					+ " ( "
					+ "    SELECT * "
					+ "    FROM tbl_product_list "
					+ " ) L "
					+ " ON O.order_seq = L.fk_order_seq "
					+ " JOIN "
					+ " ( "
					+ "    SELECT product_seq, product_type "
					+ "    FROM tbl_product  "
					+ " ) P "
					+ " ON P.product_seq = L.fk_product_seq "
					+ " CROSS JOIN "
					+ " ( "
					+ "    SELECT COUNT(*) AS total_count "
					+ "    FROM tbl_order "
					+ "    JOIN tbl_product_list ON tbl_order.order_seq = tbl_product_list.fk_order_seq "
					+ "    JOIN tbl_product ON tbl_product_list.fk_product_seq = tbl_product.product_seq "
					+ "    WHERE tbl_order.fk_member_seq = ? "
					+ " ) T "
					+ " GROUP BY P.product_type, T.total_count ";
			
			
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, member_seq);
			pstmt.setInt(2, member_seq);
			rs = pstmt.executeQuery();
			
			while(rs.next()) {
				myPurchaseChart.add(new MemebrPurchaseChart(rs.getInt("product_type"), rs.getInt("product_count"), rs.getFloat("percentage")));
			}
			
			
		}
		finally {
			close();
		}
		
		
		return myPurchaseChart;
		
	}

	@Override
	public List<MemberPurchaseByMonthChart> memberPurchase_byMonth_byCategory(int member_seq) throws SQLException {
		List<MemberPurchaseByMonthChart> myPurchaseChart = new ArrayList<>();
		
		
		try {
			conn = ds.getConnection();
			
			String sql = " SELECT  "
					+ "    product_type,  "
					+ "    COUNT(CASE WHEN TO_CHAR(REGISTERDAY, 'MM') = '01' THEN 1 ELSE NULL END) AS m_01,  "
					+ "    COUNT(CASE WHEN TO_CHAR(REGISTERDAY, 'MM') = '02' THEN 1 ELSE NULL END) AS m_02,  "
					+ "    COUNT(CASE WHEN TO_CHAR(REGISTERDAY, 'MM') = '03' THEN 1 ELSE NULL END) AS m_03,  "
					+ "    COUNT(CASE WHEN TO_CHAR(REGISTERDAY, 'MM') = '04' THEN 1 ELSE NULL END) AS m_04,  "
					+ "    COUNT(CASE WHEN TO_CHAR(REGISTERDAY, 'MM') = '05' THEN 1 ELSE NULL END) AS m_05,  "
					+ "    COUNT(CASE WHEN TO_CHAR(REGISTERDAY, 'MM') = '06' THEN 1 ELSE NULL END) AS m_06,  "
					+ "    COUNT(CASE WHEN TO_CHAR(REGISTERDAY, 'MM') = '07' THEN 1 ELSE NULL END) AS m_07,  "
					+ "    COUNT(CASE WHEN TO_CHAR(REGISTERDAY, 'MM') = '08' THEN 1 ELSE NULL END) AS m_08,  "
					+ "    COUNT(CASE WHEN TO_CHAR(REGISTERDAY, 'MM') = '09' THEN 1 ELSE NULL END) AS m_09,  "
					+ "    COUNT(CASE WHEN TO_CHAR(REGISTERDAY, 'MM') = '10' THEN 1 ELSE NULL END) AS m_10,  "
					+ "    COUNT(CASE WHEN TO_CHAR(REGISTERDAY, 'MM') = '11' THEN 1 ELSE NULL END) AS m_11,  "
					+ "    COUNT(CASE WHEN TO_CHAR(REGISTERDAY, 'MM') = '12' THEN 1 ELSE NULL END) AS m_12,  "
					+ "    COUNT(*) AS purchase_count,  "
					+ "    ROUND((COUNT(*) * 100.0 / total_order_count), 2) AS percent  "
					+ " FROM (  "
					+ "    SELECT  "
					+ "        p.product_type AS product_type,  "
					+ "        o.REGISTERDAY,  "
					+ "        (SELECT COUNT(*)  "
					+ "         FROM tbl_order  "
					+ "         JOIN tbl_product_list ON tbl_order.order_seq = tbl_product_list.fk_order_seq  "
					+ "         JOIN tbl_product ON tbl_product_list.fk_product_seq = tbl_product.product_seq  "
					+ "         WHERE fk_member_seq = ?) AS total_order_count  "
					+ "    FROM tbl_order o  "
					+ "    JOIN tbl_product_list pl ON o.order_seq = pl.fk_order_seq  "
					+ "    JOIN tbl_product p ON pl.fk_product_seq = p.product_seq  "
					+ "    WHERE o.fk_member_seq = ?  "
					+ " )  "
					+ " GROUP BY product_type, total_order_count  "
					+ " ORDER BY product_type  " ;
			
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, member_seq);
			pstmt.setInt(2, member_seq);
			rs = pstmt.executeQuery();
			
			while(rs.next()) {
				myPurchaseChart.add(new MemberPurchaseByMonthChart(
							rs.getInt("product_type"),
							rs.getInt("m_01"),
							rs.getInt("m_02"),
							rs.getInt("m_03"),
							rs.getInt("m_04"),
							rs.getInt("m_05"),
							rs.getInt("m_06"),
							rs.getInt("m_07"),
							rs.getInt("m_08"),
							rs.getInt("m_09"),
							rs.getInt("m_10"),
							rs.getInt("m_11"),
							rs.getInt("m_12"),
							rs.getInt("purchase_count"),
							rs.getFloat("percent")
						));
			}
			
			
		}
		finally {
			close();
		}
		
		
		return myPurchaseChart;
	}



	
}
