package product.model;

import java.sql.SQLException;
import java.util.List;

import domain.ProductVO;

public interface ProductDao {

	List<ProductVO> getProductByType(int type, int currentPage, int blockSize) throws SQLException;

	int getTotalPage(int productType, int blockSize) throws SQLException;

	ProductVO getProductBySeq(int product_seq) throws SQLException;


	List<ProductVO> getProductList(String searchWord) throws SQLException;




	List<ProductVO> getAllProduct()throws SQLException;

	int getEventProductTotalPage(int discount_event_Seq, int blockSize) throws SQLException;

	List<ProductVO> getProductByDiscountEvent(int discount_event_Seq, int currentPage, int blockSize)throws SQLException;
	List<ProductVO> getProductByDiscountEvent(int discount_event_Seq)throws SQLException;



}
