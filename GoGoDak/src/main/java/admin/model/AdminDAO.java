package admin.model;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import domain.AnswerVO;
import domain.BoardVO;
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

	//답변 작성하는거 05-26추가
	int answerWrite(AnswerVO ansewer)throws Exception;
	//작성한 답변 보여주는거 05-26추가
	AnswerVO selectAnswer(String question_seq)throws Exception;
	
	
	
}
