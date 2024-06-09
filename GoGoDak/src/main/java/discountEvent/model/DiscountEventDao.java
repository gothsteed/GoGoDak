package discountEvent.model;

import java.sql.SQLException;
import java.util.List;

import domain.Discount_eventVO;
import pager.Pager;

public interface DiscountEventDao {

	int insertDiscountEvent(String name, String savedFileName, int[] selectedProducts) throws SQLException;

	Pager<Discount_eventVO> getAllDiscountEvent(int currentPage, int blockSize) throws SQLException;
	List<Discount_eventVO> getAllDiscountEvent() throws SQLException;
	

	Discount_eventVO getDiscountEventBySeq(int discount_event_Seq) throws SQLException;

	int updateDiscountEvent(int discount_event_seq, String name, int[] selectedProducts) throws SQLException;

	int updateDiscountEvent(int discount_event_seq, String name, String savedFileName, int[] selectedProducts) throws SQLException;

	int deleteDiscountEvent(int discount_event_seq) throws SQLException;


}
