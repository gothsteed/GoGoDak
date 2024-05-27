package order.model;

import java.io.UnsupportedEncodingException;
import java.security.GeneralSecurityException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

import com.oracle.wls.shaded.org.apache.regexp.recompile;

import domain.MemberVO;
import domain.OrderVO;
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
					+ "    m.member_seq,  "
					+ "    m.email,  "
					+ "    m.id,  "
					+ "    m.password,  "
					+ "    m.name,  "
					+ "    m.tel,  "
					+ "    m.jubun,  "
					+ "    m.point,  "
					+ "    m.register_date,  "
					+ "    m.exist_status,  "
					+ "    m.active_status,  "
					+ "    m.last_password_change, "
					+ "    o.order_seq,  "
					+ "    o.postcode ,  "
					+ "    o.address,  "
					+ "    o.address_detail,  "
					+ "    o.address_extra,  "
					+ "    o.total_pay "
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
					+ "    pl.order_seq, "
					+ "    pl.product_seq, "
					+ "    pl.quantity, "
					+ "    p.product_seq, "
					+ "    p.fk_maufacturer_seq, "
					+ "    p.product_name, "
					+ "    p.description, "
					+ "    p.base_prive, "
					+ "    p.stock, "
					+ "    p.main_pic, "
					+ "    p.discription_pic, "
					+ "    p.fk_discount_event_seq, "
					+ "    p.discount_type, "
					+ "    p.discount_number "
					+ "FROM  "
					+ "    product_list pl "
					+ "JOIN  "
					+ "    tbl_product p "
					+ "ON  "
					+ "    pl.product_seq = p.product_seq  "
					+ " WHERE order_seq = ? ";
			
			
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, order_seq);
			
			while(rs.next()) {
				productList.add(createProduct(rs));
			}
			
			
		}
		finally {
			close();
		}
		
		
		
		return productList;
	}



}
