package domain;

import java.sql.Date;

public class BoardVO {
	private int board_seq;      //공지사항번호
	private String title;  		//제목
	private Date ragisterdate;	//작성날짜
	private String content;		//내용
	private String pic;			//첨부사진
	
	
	
	
	public int getBoard_seq() {
		return board_seq;
	}
	public void setBoard_seq(int board_seq) {
		this.board_seq = board_seq;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public Date getRagisterdate() {
		return ragisterdate;
	}
	public void setRagisterdate(Date ragisterdate) {
		this.ragisterdate = ragisterdate;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public String getPic() {
		return pic;
	}
	public void setPic(String pic) {
		this.pic = pic;
	}
	
	
	
	
	
	
	
}
