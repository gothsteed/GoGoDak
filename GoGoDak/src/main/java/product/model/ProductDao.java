package product.model;

import java.sql.SQLException;
import java.util.List;

import domain.ProductVO;

public interface ProductDao {

	List<ProductVO> getProductByType(int type) throws SQLException;

}
