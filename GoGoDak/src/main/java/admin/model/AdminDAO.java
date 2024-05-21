package admin.model;

import java.sql.SQLException;

import domain.BoardVO;

public interface AdminDAO {

	int boardWrite(BoardVO board)throws Exception;
	
	//공지사항 수정하기 혜선
	int updateBoard(BoardVO board)throws SQLException;
	//select
	int boardSelectBySeq(int board_seq)throws Exception;

	
	//delete
	int deletedBoard(BoardVO boardDelete)throws Exception;

}
