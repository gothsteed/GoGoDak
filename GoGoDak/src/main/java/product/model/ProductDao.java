package product.model;

import java.io.UnsupportedEncodingException;
import java.security.GeneralSecurityException;
import java.security.NoSuchAlgorithmException;
import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import domain.ProductVO;
import domain.Product_detailVO;
import domain.Product_listVO;
import pager.Pager;

public interface ProductDao {

	Pager<ProductVO> getProductByType(int type, int currentPage, int blockSize) throws SQLException;


	ProductVO getProductBySeq(int product_seq) throws SQLException;



	Pager<ProductVO> getProductList(String searchWord, int currentPage, int blockSize) throws SQLException;




	List<ProductVO> getAllProduct() throws SQLException;


	
	//상품등록
	int productregister(ProductVO pvo, String[] product_detail, Integer[] detail_stock) throws Exception;

	
	//상품 수정 
	int updateProduct(ProductVO pvo, String[] product_detail, Integer[] detail_stock) throws SQLException;
	
	//상품 selectseq
	int productSelectBySeq(int product_seq) throws Exception;

	//상품 삭제 
	int deletedProduct(ProductVO productDelete) throws Exception;


	Pager<ProductVO> getProductByDiscountEvent(int discount_event_Seq, int currentPage, int blockSize)throws SQLException;
	List<ProductVO> getProductByDiscountEvent(int discount_event_Seq)throws SQLException;

	Pager<ProductVO> getBrandProductList(String manufacturer_seq, int currentPage, int blockSize) throws SQLException;

	//tbl_manufacturer (위,경도) 테이블에 있는 정보 가져오기(select)
	List<Map<String, String>> selectStoreMap() throws SQLException;

	List<Map<String, String>> selectStoreMapByLocation(String locationParam) throws SQLException;

	List<ProductVO> getDiscountProduct() throws SQLException;

	List<ProductVO> getRecentProduct() throws SQLException;

	int insertProductLike(int member_seq, int product_seq) throws SQLException;

	int deletedProductLike(int member_seq, int product_seq) throws SQLException;

	int getLikeCount(int product_seq) throws SQLException;

	boolean isLiked(int member_seq, int product_seq) throws SQLException;

	List<ProductVO> getLikedProduct(int member_seq) throws SQLException;


	List<Product_detailVO> getProductDetails(int product_seq) throws SQLException;


	ProductVO getProductBySeq(int product_seq, int product_detail_seq) throws SQLException;
	
	List<ProductVO> getProductList(int order_seq) throws SQLException;
	
	






}
