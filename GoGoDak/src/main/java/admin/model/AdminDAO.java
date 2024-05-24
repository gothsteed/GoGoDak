package admin.model;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import domain.BoardVO;
import domain.MemberVO;
import domain.OrderVO;

public interface AdminDAO {

	int boardWrite(BoardVO board)throws Exception;
	
	//공지사항 수정하기 혜선
	int updateBoard(BoardVO board)throws SQLException;
	//select
	int boardSelectBySeq(int board_seq)throws Exception;

	
	//delete
	int deletedBoard(BoardVO boardDelete)throws Exception;

	
	// 
	int getTotalPage(Map<String, String> paraMap) throws SQLException;

	List<OrderVO> select_Order_paging(Map<String, String> paraMap)throws Exception;

	int getTotalMemberCount(Map<String, String> paraMap)throws Exception;

	

}
