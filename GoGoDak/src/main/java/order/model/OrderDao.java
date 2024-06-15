package order.model;

import java.sql.SQLException;
import java.time.LocalDate;
import java.util.List;
import java.util.Map;

import domain.MemberOrderStat;
import domain.MemberPurchaseByMonthChart;
import domain.MemebrPurchaseChart;
import domain.OrderVO;
import domain.ProductVO;
import pager.Pager;

public interface OrderDao {

	int[] insertOrder(int member_seq, String postcode, String address, String address_detail, String address_extra,
			 String  delivery_message, int totalAmount, Map<ProductVO, Integer> cart) throws SQLException;

	OrderVO getOrderBySeq(int order_seq) throws SQLException;
	
	OrderVO getOrderWithMember(int order_seq)  throws SQLException;


	int updateDelivery_status(int order_seq, int status) throws SQLException;

	Pager<OrderVO> getLoginuserList(int fk_member_seq, int currentPage, int blockSize, LocalDate startDate, LocalDate endDate)throws SQLException;

	int updateDelivery_status(int order_seq) throws SQLException;

	List<MemebrPurchaseChart> memberPurchase_byCategory(int member_seq) throws SQLException;

	List<MemberPurchaseByMonthChart> memberPurchase_byMonth_byCategory(int member_seq) throws SQLException;

	MemberOrderStat getOrderStat(int member_seq) throws SQLException;

}
