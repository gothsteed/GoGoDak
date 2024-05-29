package admin.model;

import java.io.UnsupportedEncodingException;
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

import domain.AnswerVO;
import domain.BoardVO;
import domain.OrderVO;
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

		 // 총 페이지 수 알아오기
	    @Override
	    public int getTotalPage(Map<String, String> paraMap) throws SQLException {
	        int totalPage = 0;
	       

	        try {
	            conn = ds.getConnection();

	            String sql = "SELECT CEIL(COUNT(*) / ? ) FROM tbl_order";
	            
	            
	            String colname = paraMap.get("searchType");
	            String searchWord = paraMap.get("searchWord");
	            
	            pstmt = conn.prepareStatement(sql);
	            pstmt.setInt(1, Integer.parseInt(paraMap.get("sizePerPage")));
	            
	            rs = pstmt.executeQuery();

	            if (rs.next()) {
	                totalPage = rs.getInt(1);
	            }

	        } finally {
	            if (rs != null) try { rs.close(); } catch (SQLException e) { e.printStackTrace(); }
	            if (pstmt != null) try { pstmt.close(); } catch (SQLException e) { e.printStackTrace(); }
	            if (conn != null) try { conn.close(); } catch (SQLException e) { e.printStackTrace(); }
	        }

	        return totalPage;
	    }

	    // 주문한 회원 조회 메서드
	    public List<OrderVO> select_Order_paging(Map<String, String> paraMap) throws SQLException  {
	        List<OrderVO> OrderList = new ArrayList<>();

	        try {
	            conn = ds.getConnection();

	            String sql = "SELECT rno, order_seq, delivery_status, id, name, tel, address "
	                       + "FROM "
	                       + "( "
	                       + "    SELECT rownum as rno, o.order_seq, o.delivery_status, m.id, m.name, m.tel, m.address "
	                       + "    FROM "
	                       + "    ( "
	                       + "        SELECT order_seq, delivery_status, fk_member_seq "
	                       + "        FROM tbl_order "
	                       + "        ORDER BY order_seq DESC "
	                       + "    ) o "
	                       + "    JOIN tbl_member m ON o.fk_member_seq = m.member_seq "
	                       + ") T "
	                       + "WHERE T.rno BETWEEN ? AND ?";

	            String searchType = paraMap.get("searchType");
	            String searchWord = paraMap.get("searchWord");

	            if (searchType != null && !searchType.trim().isEmpty() && searchWord != null && !searchWord.trim().isEmpty()) {
	                sql = sql.replace("WHERE", "WHERE " + searchType + " LIKE ? AND");
	            }

	            pstmt = conn.prepareStatement(sql);

	            int currentShowPageNo = Integer.parseInt(paraMap.get("currentShowPageNo"));
	            int sizePerPage = Integer.parseInt(paraMap.get("sizePerPage"));
	            int startRno = (currentShowPageNo - 1) * sizePerPage + 1;
	            int endRno = currentShowPageNo * sizePerPage;

	            if (searchType != null && !searchType.trim().isEmpty() && searchWord != null && !searchWord.trim().isEmpty()) {
	                pstmt.setString(1, "%" + searchWord + "%");
	                pstmt.setInt(2, startRno);
	                pstmt.setInt(3, endRno);
	            } else {
	                pstmt.setInt(1, startRno);
	                pstmt.setInt(2, endRno);
	            }

	            rs = pstmt.executeQuery();

	            while (rs.next()) {
	                OrderVO ovo = new OrderVO();
	                ovo.setOrder_seq(rs.getInt("order_seq"));
	                ovo.setId(rs.getString("id"));
	                ovo.setName(rs.getString("name"));
	                ovo.setAddress(rs.getString("address"));
	                ovo.setDeliverystatus(rs.getInt("delivery_status"));

	                OrderList.add(ovo);
	            }
	        } catch (SQLException e) {
	            e.printStackTrace();
	        } finally {
	            close();
	        }

	        return OrderList;
	    }
	    
	    
	    @Override
	    public int getTotalMemberCount(Map<String, String> paraMap) throws Exception {
	       
	    	int totalMemberCount = 0;
	    
	        
	        try {
	            conn = ds.getConnection();
	            
	            String sql = "SELECT COUNT(*) " +
	                         "FROM tbl_member " +
	                         "WHERE id != 'admin' ";

	            String colname = paraMap.get("searchType");
	            String searchWord = paraMap.get("searchWord");
	            
	            if (colname != null && !colname.trim().isEmpty() && searchWord != null && !searchWord.trim().isEmpty()) {
	                sql += " AND " + colname + " LIKE ? ";
	            }
	            
	            pstmt = conn.prepareStatement(sql);
	            
	            if (colname != null && !colname.trim().isEmpty() && searchWord != null && !searchWord.trim().isEmpty()) {
	                pstmt.setString(1, "%" + searchWord + "%");
	            }
	            
	            rs = pstmt.executeQuery();
	            
	            if (rs.next()) {
	                totalMemberCount = rs.getInt(1);
	            }
	            
	        } finally {
	            if (rs != null) try { rs.close(); } catch (SQLException e) { e.printStackTrace(); }
	            if (pstmt != null) try { pstmt.close(); } catch (SQLException e) { e.printStackTrace(); }
	            if (conn != null) try { conn.close(); } catch (SQLException e) { e.printStackTrace(); }
	        }
	        
	        return totalMemberCount;
	    }
	    
	    
	    
	    //답변 작성하는거 05-26 추가 
	      @Override
	      public int answerWrite(AnswerVO ansewer) throws Exception {
	         
	         int result = 0;
	         try {
	            conn = ds.getConnection();
	            String sql = "INSERT INTO tbl_answer (answer_seq , fk_question_seq , title , content ) VALUES ( answer_seq.nextval , ? , ? , ?) ";
	            pstmt = conn.prepareStatement(sql);
	            pstmt.setInt(1, ansewer.getFk_question_seq());
	            pstmt.setString(2, ansewer.getTitle());
	            pstmt.setString(3, ansewer.getContent());
	            
	            //오류확인용 시작//
	            System.out.println("SQL: " + sql);
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
	      //작성한 답변 보여주는거 05-26추가
	      @Override
	      public AnswerVO selectAnswer(String question_seq) throws Exception {
	         AnswerVO answerView = null;
	            
	            try {
	               conn = ds.getConnection();
	               
	               String sql =  " select title , content , answer_seq , fk_question_seq"
	                        +  " from tbl_answer "
	                        +  " where fk_question_seq = ? ";
	                           
	               pstmt = conn.prepareStatement(sql);
	               
	               pstmt.setString(1, question_seq);
	               
	               rs = pstmt.executeQuery();
	               
	               if(rs.next()) {
	                  answerView = new AnswerVO();
	                  
	                  answerView.setTitle(rs.getString("title"));
	                  answerView.setContent(rs.getString("content"));
	                  answerView.setAnswer_seq(rs.getInt("answer_seq"));
	                  answerView.setFk_question_seq(rs.getInt("fk_question_seq"));
	               } // end of if(rs.next())-------------------
	               
	            } finally {
	               close();
	            }
	            
	            return answerView;
	       
	       
	       
	   }
	    
	    
	    
	    
	    
	    
	    
	    
	    
	    
	    
	    
	    
	    
	    
	    
	    
	    
	    
	}
	
	
