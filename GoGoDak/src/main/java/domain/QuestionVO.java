package domain;

import java.sql.Date;

public class QuestionVO {
	private int question_seq;		//질문고유번호
	private int fk_member_seq;		//유저고유번호
	private String title;			//제목
	private Date ragisterdate;		//작성날짜
	private String id;				//작성자아이디
	private String email;			//작성자이메일
	private String pic;				//첨부사진
	private int fk_answer_seq;
	private String content; //내용
	
	private boolean hasAnswer;       // 답변 여부
	
	
	private MemberVO mdto = new MemberVO();
	
	
	public int getQuestion_seq() {
		return question_seq;
	}
	public void setQuestion_seq(int question_seq) {
		this.question_seq = question_seq;
	}
	public int getFk_member_seq() {
		return fk_member_seq;
	}
	public void setFk_member_seq(int fk_member_seq) {
		this.fk_member_seq = fk_member_seq;
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
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getPic() {
		return pic;
	}
	public void setPic(String pic) {
		this.pic = pic;
	}
	public MemberVO getMdto() {
		return mdto;
	}
	public void setMdto(MemberVO mdto) {
		this.mdto = mdto;
	}
	public int getFk_answer_seq() {
		return fk_answer_seq;
	}
	public void setFk_answer_seq(int fk_answer_seq) {
		this.fk_answer_seq = fk_answer_seq;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	
	
	public boolean isHasAnswer() {
		return hasAnswer;
	}
	public void setHasAnswer(boolean hasAnswer) {
		this.hasAnswer = hasAnswer;
	}
	
}
