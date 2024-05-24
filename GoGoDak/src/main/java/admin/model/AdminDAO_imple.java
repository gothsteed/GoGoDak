package admin.model;

import java.io.UnsupportedEncodingException;
import java.security.GeneralSecurityException;
import java.security.NoSuchAlgorithmException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

import domain.BoardVO;
import util.security.AES256;
import util.security.SecretMyKey;


public class AdminDAO_imple implements AdminDAO {
	
	private DataSource ds;
	private Connection conn;
	private PreparedStatement pstmt;
	private ResultSet rs;

	private AES256 aes;

	public AdminDAO_imple() {
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
	public int boardWrite(BoardVO board) throws Exception {

		int result = 0;
		try {
			conn = ds.getConnection();
			String sql = "INSERT INTO tbl_board (board_seq, title, content, pic) VALUES (board_seq.nextval, ?, ?, ?)";
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, board.getTitle());
			pstmt.setString(2, board.getContent());
			pstmt.setString(3, board.getPic());
			
			//오류확인용 시작//
			System.out.println("SQL: " + sql);
			System.out.println("Title: " + board.getTitle());
			System.out.println("Content: " + board.getContent());
			System.out.println("Pic: " + board.getPic());
			//오류확인용 끝//
			
			result = pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
			throw new Exception("Database error: " + e.getMessage(), e);
		} finally {
			close();
		}
		return result;
	

	}
	
	
	
	//공지사항 수정하기 혜선
		@Override
		public int updateBoard(BoardVO board) throws SQLException {
			  int result = 0;
		      
		      try {
		         conn = ds.getConnection();
		         
		         String sql = " update tbl_board set title = ? , content = ? , pic = ? "
		                    + " where board_seq = ? ";
		                  
		         pstmt = conn.prepareStatement(sql);
		         
		         pstmt.setString(1, board.getTitle());
		         pstmt.setString(2, board.getContent());
		         pstmt.setString(3, board.getPic());
		         pstmt.setInt(4, board.getBoard_seq());
		                  
		         result = pstmt.executeUpdate();
		         
		      }
		       finally {
		         close();
		      }
		      
		      return result;      
		}//end of public int updateBoard(BoardVO board) throws SQLException {}------

		
		
		
		//수정해야할 board select board_seq
		@Override
		public int boardSelectBySeq(int board_seq) throws Exception {
			
			int boardSelectBySeq = 0;
			
			try {
				conn = ds.getConnection();
				
				String sql = " select board_seq "
						   + " from tbl_board "
						   + " where board_seq = ? " ;
				
				pstmt = conn.prepareStatement(sql);
				
				pstmt.setInt(1, board_seq);
				
				rs = pstmt.executeQuery();
				
				if(rs.next()) {
					
					boardSelectBySeq = rs.getInt(1);
					
				}
				 
				
			} finally {
				close();
			}
			
			return boardSelectBySeq;
		}

		
		
		//공지사항 게시글 삭제하기
		@Override
		public int deletedBoard(BoardVO boardDelete) throws Exception {
			
			int result = 0;
			try {
				conn = ds.getConnection();
				String sql = " DELETE FROM tbl_board WHERE board_seq = ? " ;
				pstmt = conn.prepareStatement(sql);
				pstmt.setInt(1, boardDelete.getBoard_seq());
				
				
				//오류확인용 시작//
				System.out.println("SQL: " + sql);
				System.out.println("Seq: " + boardDelete.getBoard_seq());
				//오류확인용 끝//
				
				result = pstmt.executeUpdate();
			} catch (SQLException e) {
				e.printStackTrace();
				throw new Exception("Database error: " + e.getMessage(), e);
			} finally {
				close();
			}
			
			return result;
			
		}

		@Override
		public List<String> updateAnswer(String question_seq) throws Exception {
			// TODO Auto-generated method stub
			return null;
		}
		
	

}
