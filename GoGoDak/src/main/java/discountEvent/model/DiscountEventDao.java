package discountEvent.model;

import java.sql.SQLException;

public interface DiscountEventDao {

	int insertDiscountEvent(String name, String savedFileName, int[] selectedProducts) throws SQLException;

}
