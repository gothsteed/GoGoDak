package member.model;

import java.io.UnsupportedEncodingException;
import java.security.GeneralSecurityException;
import java.security.NoSuchAlgorithmException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

import domain.BoardVO;
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
			ds = (DataSource) envContext.lookup("jdbc/semioracle");
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
			
			String sql = "SELECT MEMBER_SEQ, EMAIL, ID, PASSWORD, NAME, TEL, JUBUN, POINT, "
		            + "EXIST_STATUS, ACTIVE_STATUS, LAST_PASSWORD_CHANGE, POSTCODE, ADDRESS, "
		            + "ADDRESS_DETAIL, ADDRESS_EXTRA, REGISTERDAY, "
		            + "TRUNC(MONTHS_BETWEEN(SYSDATE, LAST_PASSWORD_CHANGE)) AS PWDCHANGGAP "
		            + "FROM tbl_member "
		            + "WHERE exist_status=1 AND id=? AND password=?";

	        
	         
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, paraMap.get("id"));
			
//			System.out.println("encrypted password: " + Sha256.encrypt(paraMap.get("password")));
//			System.out.println("encrypted email: " + aes.encrypt("jyleeturbo123@gmail.com"));
//			System.out.println("encrypted tel: " +  aes.encrypt("01045377647"));
			
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
			memberTemp.setRegisterDate(rs.getDate("REGISTERDAY"));
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

	@Override
	public int register(MemberVO member) throws SQLException {
		int result = 0;

		try {
			conn = ds.getConnection();

			String sql = " insert into tbl_member(member_seq, id, password, name, email, tel, postcode, address, address_detail, address_extra, jubun) "
					+ " values(MEMBER_SEQ.nextval, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?) ";

			pstmt = conn.prepareStatement(sql);

			pstmt.setString(1, member.getId());
			pstmt.setString(2, member.getPassword());
			pstmt.setString(3, member.getName());
			pstmt.setString(4, aes.encrypt(member.getEmail()));

			pstmt.setString(5, aes.encrypt(member.getTel()));

			pstmt.setString(6, member.getPostcode());
			pstmt.setString(7, member.getAddress());
			pstmt.setString(8, member.getAddress_detail());
			pstmt.setString(9, member.getAddress_extra());
			pstmt.setString(10, member.getJubun());

			
			
			result = pstmt.executeUpdate();

		} catch (UnsupportedEncodingException | GeneralSecurityException e) {
			e.printStackTrace();
		} finally {
			close();
		}

		return result;
	}

	@Override
	public String getId(Map<String, String> paraMap) throws SQLException {
		String id = null;
		
		try {
			
			conn = ds.getConnection();
			
			String sql = " select id "
					+ " from tbl_member "
					+ " where EXIST_STATUS = 1 and name =? and email=? ";
			
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, paraMap.get("name"));
			pstmt.setString(2, aes.encrypt(paraMap.get("email")));
			
			rs = pstmt.executeQuery();
			
			if(rs.next()) {
				
				id = rs.getString("id");
				
			}
			
		} catch (NoSuchAlgorithmException e) {

			e.printStackTrace();
		} catch (UnsupportedEncodingException e) {

			e.printStackTrace();
		} catch (GeneralSecurityException e) {

			e.printStackTrace();
		}finally {
			close();
		}
		
		return id;
		
	}

	@Override
	public boolean isExist(Map<String, String> paraMap) throws SQLException {
		boolean result = false;

		try {
			conn = ds.getConnection();

			String sql = " select id "
					+ " from tbl_member " 
					+ " where EXIST_STATUS = 1 = 1 and id = ? and email = ? ";

			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, paraMap.get("id"));
			pstmt.setString(2, aes.encrypt(paraMap.get("email")));

			rs = pstmt.executeQuery();

			result = rs.next();

		} catch (GeneralSecurityException | UnsupportedEncodingException e) {
			e.printStackTrace();
		} finally {
			close();
		}

		return result;
	}

	
//	■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■혜선작업시작■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■
	
	
	
	   
	// **** 페이징 처리한 모든 회원 또는 검색한 회원 목록 보여주기 [복수개] **** //   
	@Override
	public List<BoardVO> select_Member_paging(Map<String, String> paraMap) throws SQLException {

		 List<BoardVO> memberList = new ArrayList<>();
	      
	      try {
	         conn = ds.getConnection();
	          
	         String sql = " SELECT rno , board_seq , title  "
		         		+ "   FROM "
		         		+ "    ( "
		         		+ "   select rownum as rno, board_seq , title "
		         		+ "   from "
		         		+ "        ( "
		         		+ "        Select board_seq , title  "
		         		+ "        From tbl_board  ";
		         		
		         		
	         
	         String colname = paraMap.get("searchType");
	         String searchWord = paraMap.get("searchWord");
	         
	         
	         if( (colname != null && !colname.trim().isEmpty() ) && 
    		 (searchWord != null && !searchWord.trim().isEmpty()) ){
	        	 
		         sql += " and " + colname + " like '%'|| ? ||'%' ";
		         // 컬럼명과 테이블명은 위치홀더(?)로 사용하면 꽝!!! 이다.
	             // 위치홀더(?)로 들어오는 것은 컬럼명과 테이블명이 아닌 오로지 데이터값만 들어온다.!!!!
	         }
	         
	         
	         sql += "        Order By registerday Desc "
      		  + "        )V "
      	  	  + "    )T  "
      		  + "    WHERE T.rno BETWEEN ? AND ? ";
	         
	         pstmt = conn.prepareStatement(sql); 
/*
	         === 페이징처리의 공식 ===
	         where RNO between (조회하고자하는페이지번호 * 한페이지당보여줄행의개수) - (한페이지당보여줄행의개수 - 1) and (조회하고자하는페이지번호 * 한페이지당보여줄행의개수);
*/
	         //조회하고자 하는 페이지번호가 매번 변경되기 때문에 Map에 담아야함[컨트롤러에서]
	         
	         
	         
	         int currentShowPageNo = Integer.parseInt(paraMap.get("currentShowPageNo")) ;
	         int sizePerPage = Integer.parseInt(paraMap.get("sizePerPage")) ;
	         
	         if( (colname != null && !colname.trim().isEmpty() ) && 
	             (searchWord != null && !searchWord.trim().isEmpty() ) ){ //검색이 있을때 
	        	 pstmt.setString(1, searchWord);
	        	 pstmt.setInt(2, (currentShowPageNo*sizePerPage) - (sizePerPage-1));
	        	 pstmt.setInt(3, (currentShowPageNo*sizePerPage));
	         }
	         else {//검색이 없는경우
	        	 pstmt.setInt(1, (currentShowPageNo*sizePerPage) - (sizePerPage-1));
	        	 pstmt.setInt(2, (currentShowPageNo*sizePerPage));
	        	 
	         }
	         
	         rs = pstmt.executeQuery();
	         
	         while(rs.next()) {
	            
	            BoardVO bvo = new BoardVO();
	            // userid, name, email, gender
	            bvo.setBoard_seq(rs.getInt("board_seq"));
	            bvo.setTitle(rs.getString("title"));
	            
	            memberList.add(bvo);
	         }// end of while(rs.next())---------------------
	         
	      
	      } finally {
	         close();
	      }
	      
	      return memberList;
	}//end of public List<MemberVO> select_Member_paging(Map<String, String> paraMap) {}---------------------------

	
	
//	■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■
	
	
	
	
	// >>> 뷰단(memberList.jsp)에서 "페이징 처리시 보여주는 순번 공식" 에서 사용하기 위해 검색이 있는 또는 검색이 없는 회원의 총개수 알아오기
	@Override
	public int getTotalMemberCount(Map<String, String> paraMap) throws SQLException {
		
		
		int totalMemberCount = 0;
	      
	      try {
	         conn = ds.getConnection();
	          
	         String sql = " Select count(*)  "
		         		+ " From tbl_order ";
		         	

	         
	         String colname = paraMap.get("searchType");
	         String searchWord = paraMap.get("searchWord");
	         
	        
	         if( (colname != null && !colname.trim().isEmpty() ) && 
	        	 (searchWord != null && !searchWord.trim().isEmpty()) ){
	        	 
		         sql += " and " + colname + " like '%'|| ? ||'%' ";
		         // 컬럼명과 테이블명은 위치홀더(?)로 사용하면 꽝!!! 이다.
	             // 위치홀더(?)로 들어오는 것은 컬럼명과 테이블명이 아닌 오로지 데이터값만 들어온다.!!!!
	         }
	         
	         pstmt = conn.prepareStatement(sql); 
	         
	         if( (colname != null && !colname.trim().isEmpty() ) && 
	             (searchWord != null && !searchWord.trim().isEmpty() ) ){ //검색이 있을때 
	        	 pstmt.setString(1, searchWord);
	         }
	         
	         rs = pstmt.executeQuery();
	         
	         rs.next();
	         
	         totalMemberCount = rs.getInt(1); //값이 몇개인지 리턴되어지는 것 ->totalMemberCount 로 담아줌
	         
	      } finally {
	         close();
	      }
	      
	      return totalMemberCount;
	}//end of public int getTotalMemberCount(Map<String, String> paraMap) throws SQLException {}--------------

	
//	■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■
	
	
	//페이징 처리를 위한 검색이 있는 또는 검색이 없는 회원에 대한 총 페이지수 알아오기//
	@Override
	public int getTotalPage(Map<String, String> paraMap) throws SQLException {
		
		int totalPage = 0;
	      
	      try {
	         conn = ds.getConnection();
	          
	         String sql = " Select ceil (count(*)/?) "
		         		+ " From tbl_order ";
		         		
	         
	         String colname = paraMap.get("searchType");
	         String searchWord = paraMap.get("searchWord");
	         
	       
	         if( (colname != null && !colname.trim().isEmpty() ) && 
	        	 (searchWord != null && !searchWord.trim().isEmpty()) ){
		         sql += " and " + colname + " like '%'|| ? ||'%' ";
		         // 컬럼명과 테이블명은 위치홀더(?)로 사용하면 꽝!!! 이다.
	             // 위치홀더(?)로 들어오는 것은 컬럼명과 테이블명이 아닌 오로지 데이터값만 들어온다.!!!!
	         }
	         
	         pstmt = conn.prepareStatement(sql); 
	         
	         pstmt.setInt(1,Integer.parseInt(paraMap.get("sizePerPage")));

	         if( (colname != null && !colname.trim().isEmpty() ) && 
	             (searchWord != null && !searchWord.trim().isEmpty() ) ){ //검색이 있을때 
	        	 pstmt.setString(2, searchWord);
	         }
	         
	         rs = pstmt.executeQuery();
	         
	         rs.next();
	         
	         totalPage = rs.getInt(1); //값이 몇개인지 리턴되어지는 것 ->totalMemberCount 로 담아줌
	         
	      } finally {
	         close();
	      }
	      
	      return totalPage;
	}//end of public int getTotalPage(Map<String, String> paraMap) throws SQLException{}--------------------

//	■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■혜선작업끝■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■
	
	
	
}
