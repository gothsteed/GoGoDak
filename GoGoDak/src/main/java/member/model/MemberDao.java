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

	boolean idDuplicateCheck(String id) throws SQLException;

	boolean emailDuplicateCheck(String email) throws SQLException;

	int getTotalPage(Map<String, String> paraMap) throws SQLException;

	List<MemberVO> select_Member_paging(Map<String, String> paraMap) throws SQLException;

	int getTotalMemberCount(Map<String, String> paraMap) throws SQLException;
	
	
	
	
//	■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■혜선작업시작■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■

		
		
//	■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■혜선작업끝■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■


	
	
	// 입력받은 userid 를 가지고 한명의 회원정보를 리턴시켜주는 메소드
		MemberVO selectOneMember(String userid) throws SQLException;
		
		
}
