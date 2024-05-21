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
import domain.ProductVO;
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

	// ID 중복검사
	@Override
	public boolean idDuplicateCheck(String id) throws SQLException {
		
		boolean isExists = false;
		
		try {
			conn = ds.getConnection();
			
			String sql = " select id "
					   + " from tbl_member "
					   + " where id = ? ";
			
			pstmt = conn.prepareStatement(sql);
			
			pstmt.setString(1, id);
			
			rs = pstmt.executeQuery();
			
			isExists = rs.next(); 
			
		} finally {
			close();
		}
		
		return isExists;
	}

	// EMAIL 중복검사 
	@Override
	public boolean emailDuplicateCheck(String email) throws SQLException {

		boolean isExists = false;
		
		try {
			conn = ds.getConnection();
			
			String sql = " select email "
					   + " from tbl_member "
					   + " where email = ? ";
			
			pstmt = conn.prepareStatement(sql);
			
			pstmt.setString(1, aes.encrypt(email));
			
			rs = pstmt.executeQuery();
			
			isExists = rs.next();
			
		} catch (UnsupportedEncodingException | GeneralSecurityException e) {
			e.printStackTrace();
		} finally {
			close();
		}
		
		return isExists;
	}

	// 총 페이지 수 알아오기
	public int getTotalPage(Map<String, String> paraMap) throws SQLException {

		int totalPage = 0;
		
		try {
			conn = ds.getConnection();
			
			String sql = " select ceil(count(*)/?) "
					   + " from tbl_member "
					   + " where id != 'admin' ";
			
			String colname = paraMap.get("searchType");
			String searchWord = paraMap.get("searchWord");
			
			if("email".equals(colname)) { 
				searchWord = aes.encrypt(searchWord);
			}
			
			if( (colname != null && !colname.trim().isEmpty()) && (searchWord != null && !searchWord.trim().isEmpty()) ) { 
				sql += " and " + colname + " like '%' || ? || '%' ";
			}
			
			pstmt = conn.prepareStatement(sql);
			
			pstmt.setInt(1, Integer.parseInt(paraMap.get("sizePerPage")));
			
			if( (colname != null && !colname.trim().isEmpty()) && (searchWord != null && !searchWord.trim().isEmpty()) ) {
				pstmt.setString(2, searchWord);
			}
						
			rs = pstmt.executeQuery();
			
			rs.next();
			
			totalPage = rs.getInt(1);
			
		} catch(GeneralSecurityException | UnsupportedEncodingException e) {
	    	e.printStackTrace();
	    } finally {
			close();
		}
		
		return totalPage;
	}

	// 모든 회원 또는 검색한 회원 목록 보여주기
	@Override
	public List<MemberVO> select_Member_paging(Map<String, String> paraMap) throws SQLException {

		List<MemberVO> memberList = new ArrayList<>();
		
		try {
			conn = ds.getConnection();
			
			String sql = " SELECT rno, id, name, email, gender "
					   + " FROM "
					   + " ( "
					   + "    select rownum as rno, id, name, email, gender "
					   + "    from "
					   + "    ( "
					   + "        select id, name, email,"
					   + "               case when substr(jubun, 7, 1) in('1', '3') then '남' else '여' end AS GENDER "
					   + "        from tbl_member "
					   + "        where id != 'admin' ";
			
			String colname = paraMap.get("searchType");
			String searchWord = paraMap.get("searchWord");
			
			if("email".equals(colname)) { 
				searchWord = aes.encrypt(searchWord); 
			}
			
			if( (colname != null && !colname.trim().isEmpty()) && (searchWord != null && !searchWord.trim().isEmpty()) ) { 
				sql += " and " + colname + " like '%' || ? || '%' ";
			}
			
			sql += " order by registerday desc "
			    + "    ) V "
			    + " ) T "
			    + " WHERE T.rno BETWEEN ? AND ? ";
			
			pstmt = conn.prepareStatement(sql);
			
			int currentShowPageNo = Integer.parseInt(paraMap.get("currentShowPageNo"));
			int sizePerPage = Integer.parseInt(paraMap.get("sizePerPage"));
			
			if( (colname != null && !colname.trim().isEmpty()) && (searchWord != null && !searchWord.trim().isEmpty()) ) { 
				pstmt.setString(1, searchWord);
				pstmt.setInt(2, (currentShowPageNo * sizePerPage) - (sizePerPage - 1)); 
				pstmt.setInt(3, (currentShowPageNo * sizePerPage)); 
			}
			else { 
				pstmt.setInt(1, (currentShowPageNo * sizePerPage) - (sizePerPage - 1)); 
				pstmt.setInt(2, (currentShowPageNo * sizePerPage));
			}
						
			rs = pstmt.executeQuery();
			
			while(rs.next()) {
				MemberVO mvo = new MemberVO();
				mvo.setId(rs.getString("id"));
				mvo.setName(rs.getString("name"));
				mvo.setEmail(aes.decrypt(rs.getString("email")));
				mvo.setJubun(rs.getString("gender"));
				
				memberList.add(mvo);
			} // end of while(rs.next()) ----------
			
		} catch(GeneralSecurityException | UnsupportedEncodingException e) {
	    	e.printStackTrace();
	    } finally {
			close();
		}
		
		return memberList;
	}

	// 검색이 있는 또는 검색이 없는 회원의 총개수 알아오기
	@Override
	public int getTotalMemberCount(Map<String, String> paraMap) throws SQLException {
		
		int totalMemberCount = 0;
		
		try {
			conn = ds.getConnection();
			
			String sql = " select count(*) "
					   + " from tbl_member "
					   + " where id != 'admin' ";
			
			String colname = paraMap.get("searchType");
			String searchWord = paraMap.get("searchWord");
			
			if("email".equals(colname)) {
				searchWord = aes.encrypt(searchWord);
			}
			
			if( (colname != null && !colname.trim().isEmpty()) && (searchWord != null && !searchWord.trim().isEmpty()) ) { 
				sql += " and " + colname + " like '%' || ? || '%' ";
			}
			
			pstmt = conn.prepareStatement(sql);
			
			if( (colname != null && !colname.trim().isEmpty()) && (searchWord != null && !searchWord.trim().isEmpty()) ) { 
				pstmt.setString(1, searchWord);
			}
						
			rs = pstmt.executeQuery();
			
			rs.next();
			
			totalMemberCount = rs.getInt(1); 
			
		} catch(GeneralSecurityException | UnsupportedEncodingException e) {
	    	e.printStackTrace();
	    } finally {
			close();
		}
		
		return totalMemberCount;
	}



	
	

	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	//공지사항 페이징처리 혜선 
	@Override
	public List<BoardVO> getBoard(int currentPage, int blockSize)throws SQLException  {
		List<BoardVO> boardList = new ArrayList<>();

		try {
			conn = ds.getConnection();

			String sql = " SELECT * "
					   + "FROM (  "
					   + "    SELECT rownum as rno, board_Seq , title , content , pic "
					   + "    FROM(  "
					   + "        select *  "
					   + "        from tbl_board) V "
					   + "		  ORDER BY board_Seq desc"
					   + "        )T WHERE T.rno between ? and ?";
					   
					


			pstmt = conn.prepareStatement(sql);

	
			pstmt.setLong(1, (currentPage * blockSize) - (blockSize - 1)); // 페이징처리 공식
			pstmt.setLong(2, (currentPage * blockSize));


			rs = pstmt.executeQuery();

			while (rs.next()) {

				BoardVO bvo = new BoardVO();
				bvo.setBoard_seq(rs.getInt("board_seq"));
				bvo.setTitle(rs.getString("title"));
				bvo.setContent(rs.getString("content"));
				bvo.setPic(rs.getString("pic"));
				
		
				boardList.add(bvo);
			} // end of while(rs.next())---------------------

		} finally {
			close();
		}

		return boardList;
	}

	
	//공지사항 총 페이지수 알아오기  혜선
	@Override
	public int getBoardTotalPage(int blockSize) throws SQLException {
		
		int boardTotalPage = 0;

		try {
			conn = ds.getConnection();

			String sql = " select ceil(count(*)/?) as pgn "
					   + " from tbl_board ";
					   


			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, blockSize);
			
			rs = pstmt.executeQuery();

			if (rs.next()) {
				boardTotalPage = rs.getInt(1);
				
			} // end of while(rs.next())---------------------

		} finally {
			close();
		}

		return boardTotalPage;
	}

	
	//공지사항 디테일 페이지 혜선
	@Override
	public BoardVO selectOneBoard(String board_seq) throws SQLException {
		
		
		BoardVO boardView = null;
	      
	      try {
	         conn = ds.getConnection();
	         
	         String sql =  " select title , content , pic ,board_seq"
	         			+  " from tbl_board "
	         			+  " where board_seq = ? ";
	                     
	         pstmt = conn.prepareStatement(sql);
	         
	         pstmt.setString(1, board_seq);
	         
	         rs = pstmt.executeQuery();
	         
	         if(rs.next()) {
	        	 boardView = new BoardVO();
	            
	        	 boardView.setTitle(rs.getString("title"));
	        	 boardView.setContent(rs.getString("content"));
	        	 boardView.setPic(rs.getString("pic"));
	        	 boardView.setBoard_seq(rs.getInt("board_seq"));
	         } // end of if(rs.next())-------------------
	         
	      } finally {
	         close();
	      }
	      
	      return boardView;
	}



	
	
	
	
	
	// 입력받은 id 를 가지고 한명의 회원정보를 리턴시켜주는 메소드
	@Override
	public MemberVO selectOneMember(String id) throws SQLException {
		MemberVO mvo = null;
		try {
			conn = ds.getConnection();
			String sql = " select id, name, email, tel, postcode, address, address_detail, address_extra, "
					   + "       jubun, point, to_char(registerday, 'yyyy-mm-dd') AS registerday "
					   + " from tbl_member "
					   + " where exist_status = 1 and id = ? ";
		
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, id);
			
			rs = pstmt.executeQuery();
			
			if(rs.next()) {
				mvo = new MemberVO();
				
				mvo.setId(rs.getString(1));
				mvo.setName(rs.getString(2));
				mvo.setEmail(aes.decrypt(rs.getString(3)));
				mvo.setTel(aes.decrypt(rs.getString(4)));
				mvo.setPostcode(rs.getString(5));
				mvo.setAddress(rs.getString(6));
				mvo.setAddress_detail(rs.getString(7));
				mvo.setAddress_extra(rs.getString(8));
				mvo.setJubun(rs.getString(9));
				mvo.setPoint(rs.getInt(10));
				mvo.setRegisterDate(rs.getDate(11));
				
			}// end if(rs.next())
			else {
				System.out.println("데이터가 없습니다.");
			}
		} catch(GeneralSecurityException | UnsupportedEncodingException e) {
			e.printStackTrace();
		} finally {
			close();
		}
		
		return mvo;		
		
	}// end of public MemberVO selectOneMember(String id) throws SQLException {}
	//,status             number(1) default 1 not null     -- 회원탈퇴유무   1: 사용가능(가입중) / 0:사용불능(탈퇴) 


	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	

}

