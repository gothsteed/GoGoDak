package order.model;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import domain.OrderVO;
import domain.ProductVO;

public interface OrderDao {

	int insertOrder(int member_seq, String postcode, String address, String address_detail, String address_extra,
			 String  delivery_message, int totalAmount, Map<ProductVO, Integer> cart) throws SQLException;

	OrderVO getOrderBySeq(int order_seq) throws SQLException;
	List<ProductVO> getProductList(int order_seq)  throws SQLException;
	
	OrderVO getOrderWithMember(int order_seq)  throws SQLException;

	int updateDelivery_status(int order_seq) throws SQLException;

}
