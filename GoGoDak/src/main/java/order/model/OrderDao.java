package order.model;

import java.sql.SQLException;

public interface OrderDao {

	int insertOrder(int member_seq, String postcode, String address, String address_detail, String address_extra,
			int totalAmount) throws SQLException;

}
