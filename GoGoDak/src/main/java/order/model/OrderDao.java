package order.model;

import java.sql.SQLException;
import java.util.Map;

import domain.ProductVO;

public interface OrderDao {

	int insertOrder(int member_seq, String postcode, String address, String address_detail, String address_extra,
			int totalAmount, Map<ProductVO, Integer> cart) throws SQLException;

}
