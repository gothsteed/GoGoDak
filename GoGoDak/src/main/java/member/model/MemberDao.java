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

	// **** 페이징 처리한 모든 회원 또는 검색한 회원 목록 보여주기 [복수개] **** //
	List<BoardVO> select_Member_paging(Map<String, String> paraMap)throws SQLException;

	// >>> 뷰단(memberList.jsp)에서 "페이징 처리시 보여주는 순번 공식" 에서 사용하기 위해 검색이 있는 또는 검색이 없는 회원의 총개수 알아오기
	int getTotalMemberCount(Map<String, String> paraMap)throws SQLException;
	
	//페이징 처리를 위한 검색이 있는 또는 검색이 없는 회원에 대한 총 페이지수 알아오기//	
	int getTotalPage(Map<String, String> paraMap)throws SQLException;
//	■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■혜선작업끝■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■

}
