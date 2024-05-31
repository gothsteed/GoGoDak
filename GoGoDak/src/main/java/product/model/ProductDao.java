package product.model;

import java.io.UnsupportedEncodingException;
import java.security.GeneralSecurityException;
import java.security.NoSuchAlgorithmException;
import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import domain.ProductVO;
import domain.Product_listVO;

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

	Product_listVO memberOrderDetail(String id)throws SQLException, NoSuchAlgorithmException, UnsupportedEncodingException, GeneralSecurityException;

	int getEventProductTotalPage(int discount_event_Seq, int blockSize) throws SQLException;

	List<ProductVO> getProductByDiscountEvent(int discount_event_Seq, int currentPage, int blockSize)throws SQLException;
	List<ProductVO> getProductByDiscountEvent(int discount_event_Seq)throws SQLException;

	List<ProductVO> getBrandProductList(String manufacturer_seq) throws SQLException;

	//tbl_manufacturer (위,경도) 테이블에 있는 정보 가져오기(select)
	List<Map<String, String>> selectStoreMap() throws SQLException;

	List<Map<String, String>> selectStoreMapByLocation(String locationParam) throws SQLException;
	
	





}
