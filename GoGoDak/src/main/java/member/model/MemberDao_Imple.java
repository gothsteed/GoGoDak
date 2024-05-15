package member.model;

import java.io.UnsupportedEncodingException;
import java.security.GeneralSecurityException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Map;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

import domain.MemberVO;
import util.security.AES256;
import util.security.SecretMyKey;
import util.security.Sha256;

public class MemberDao_Imple implements MemberDao {
	
	private DataSource ds;
	private Connection conn;
	private PreparedStatement pstmt;
	private ResultSet rs;

	private AES256 aes;
	
	public MemberDao_Imple() {
		try {

			Context initContext = new InitialContext();
			Context envContext = (Context) initContext.lookup("java:/comp/env");
			ds = (DataSource) envContext.lookup("jdbc/myoracle");
			aes = new AES256(SecretMyKey.KEY);

		} catch (NamingException e) {
			e.printStackTrace();
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		
	}
	
	private void close() {
		try {
			if (rs != null) {
				rs.close();
				rs = null;
			}
			if (pstmt != null) {
				pstmt.close();
				pstmt = null;
			}
			if (conn != null) {
				conn.close();
				conn = null;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	

	@Override
	public MemberVO login(Map<String, String> paraMap) throws SQLException {
		
		MemberVO memberVo = null;
		
		try {
			
			conn = ds.getConnection();
			
	         String sql = " select *, TRUNC(MONTHS_BETWEEN(SYSDATE, LAST_PASSWORD_CHANGE)) AS PWDCHANGGAP "
	         		+ " from tbl_member"
	         		+ " where exist_status=1 and id=? and password=? ";
	         
	         
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, paraMap.get("userId"));
			pstmt.setString(2, Sha256.encrypt(paraMap.get("password")));
			rs = pstmt.executeQuery();
			
			if(!rs.next()) {
				return memberVo;
			}
			
			MemberVO memberTemp = new MemberVO();
			
			memberTemp.setMember_seq(rs.getInt("MEMBER_SEQ"));
			memberTemp.setEmail(aes.decrypt(rs.getString("EMAIL")));
			memberTemp.setId(rs.getString("ID"));
			memberTemp.setPassword(rs.getString("PASSWORD"));
			memberTemp.setName(rs.getString("NAME"));
			memberTemp.setTel(aes.decrypt(rs.getString("TEL")));
			memberTemp.setJubun(rs.getString("JUBUN"));
			memberTemp.setPoint(rs.getInt("point"));
			memberTemp.setRegisterDate(rs.getDate("REGISTER_DATE"));
			memberTemp.setExist_status(rs.getInt("EXIST_STATUS"));
			memberTemp.setActive_status(rs.getInt("ACTIVE_STATUS"));
			memberTemp.setLast_password_change(rs.getDate("LAST_PASSWORD_CHANGE"));
			int passwordChangeGap = rs.getInt("PWDCHANGGAP");
			
			
			sql = "    select TRUNC(MONTHS_BETWEEN(SYSDATE, max(login_date))) as LASTLOGINGAP "
	                  + "    from tbl_login_history "
	                  + "    where fk_member_seq = ? ";
			
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, memberTemp.getMember_seq());
			rs = pstmt.executeQuery();
			
			if(!rs.next()) {
				return memberVo;
			}
			
			int lastLoginGap = rs.getInt("LASTLOGINGAP");
			
			
			if(lastLoginGap >= 12 ) {
				
				if(memberTemp.getActive_status() == 0) {
					
					sql = " update tbl_member set ACTIVE_STATUS=0 " 
					+ " where id = ? ";
					
					pstmt = conn.prepareStatement(sql);
					pstmt.setString(1, memberTemp.getId());
					
					pstmt.executeUpdate();
				}

				memberTemp.setActive_status(0);
			}
			else {
				
				sql = "insert into TBL_LOGIN_HISTORY(login_history_seq, fk_member_seq, ip) values(login_history_seq.nextval, ?, ?)  ";
				
				pstmt =conn.prepareStatement(sql);
				
				pstmt.setInt(1, memberTemp.getMember_seq());
				pstmt.setString(2, paraMap.get("clientIp"));
				
				pstmt.executeUpdate();
//				System.out.println("pwdchangegap" +  ""  +rs.getInt("pwdchangegap"));
//				System.out.println("lastlogingap" +  ""  +rs.getInt("lastlogingap"));
				
				if (passwordChangeGap >= 3) {
					
					memberTemp.setRequirePasswordChange(true);
					
				}
				
			}
			
			memberVo = memberTemp;
			
		} catch (GeneralSecurityException | UnsupportedEncodingException  e) {
			e.printStackTrace();
		} 
		finally {
			close();
		}
		
		
		return memberVo;
	}

}
