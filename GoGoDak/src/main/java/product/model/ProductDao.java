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

	
	//상품등록
	int productregister(ProductVO pvo) throws Exception;

	
	//상품 수정 
	int updateProduct(ProductVO pvo) throws SQLException;
	
	//상품 selectseq
	int productSelectBySeq(int product_seq) throws Exception;

	//상품 삭제 
	int deletedProduct(ProductVO productDelete) throws Exception;

}
