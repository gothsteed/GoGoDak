package discountEvent.model;

import java.sql.SQLException;
import java.util.List;

import domain.Discount_eventVO;

public interface DiscountEventDao {

	int insertDiscountEvent(String name, String savedFileName, int[] selectedProducts) throws SQLException;

	List<Discount_eventVO> getAllDiscountEvent(int currentPage, int blockSize) throws SQLException;
	
	int getTotalPage(int blockSize) throws SQLException;

	Discount_eventVO getDiscountEventBySeq(int discount_event_Seq) throws SQLException;

	int updateDiscountEvent(int discount_event_seq, String name, int[] selectedProducts) throws SQLException;

	int updateDiscountEvent(int discount_event_seq, String name, String savedFileName, int[] selectedProducts) throws SQLException;

	int deleteDiscountEvent(int discount_event_seq) throws SQLException;


}
