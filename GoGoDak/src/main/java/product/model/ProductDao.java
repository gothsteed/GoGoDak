package product.model;

import java.sql.SQLException;
import java.util.List;

import domain.ProductVO;

public interface ProductDao {

	List<ProductVO> getProductByType(int type, int currentPage) throws SQLException;

	int getTotalPage(int productType) throws SQLException;

}
