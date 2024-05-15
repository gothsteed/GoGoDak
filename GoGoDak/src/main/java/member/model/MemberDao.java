package member.model;

import java.sql.SQLException;
import java.util.Map;

import domain.MemberVO;

public interface MemberDao {

	MemberVO login(Map<String, String> paraMap) throws SQLException;

	int register(MemberVO member) throws SQLException;

}
