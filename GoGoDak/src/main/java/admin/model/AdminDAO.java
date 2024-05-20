package admin.model;

import domain.BoardVO;

public interface AdminDAO {

	int boardWrite(BoardVO board)throws Exception;

}
