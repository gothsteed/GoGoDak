package member.model;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import domain.BoardVO;
import domain.MemberVO;

public interface MemberDao {

	MemberVO login(Map<String, String> paraMap) throws SQLException;

	int register(MemberVO member) throws SQLException;

	String getId(Map<String, String> paraMap) throws SQLException;

	boolean isExist(Map<String, String> paraMap) throws SQLException;
	
	
	
	
//	■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■혜선작업시작■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■

		
		
//	■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■혜선작업끝■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■


}
