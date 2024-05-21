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

	int pwdUpdate(Map<String, String> paraMap) throws SQLException;
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	//공지사항 페이징처리 혜선 
	List<BoardVO> getBoard(int currentPage, int blockSize)throws SQLException ;
	
	//공지사항 총 페이지수 알아오기  혜선
	int getBoardTotalPage(int blockSize)throws SQLException ;
	
	//공지사항 디테일 페이지 혜선
	BoardVO selectOneBoard(String board_seq)throws SQLException ;

		
		


	
	
	// 입력받은 id 를 가지고 한명의 회원정보를 리턴시켜주는 메소드
	MemberVO selectOneMember(String id) throws SQLException;

	
		
		
		
		
		
}

