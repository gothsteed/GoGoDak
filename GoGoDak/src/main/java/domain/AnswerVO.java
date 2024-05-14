package domain;

import java.sql.Date;

public class AnswerVO {
	
	private int answer_seq;			//답변고유번호
	private int fk_question_seq;	//질문고유번호
	private String title;			//제목
	private Date ragisterdate;		//작성날짜
	private String content;			//내용
	
	private QuestionVO qdto = new QuestionVO();
	
	
	public int getAnswer_seq() {
		return answer_seq;
	}
	public void setAnswer_seq(int answer_seq) {
		this.answer_seq = answer_seq;
	}
	public int getFk_question_seq() {
		return fk_question_seq;
	}
	public void setFk_question_seq(int fk_question_seq) {
		this.fk_question_seq = fk_question_seq;
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
	public QuestionVO getQdto() {
		return qdto;
	}
	public void setQdto(QuestionVO qdto) {
		this.qdto = qdto;
	}
	
	
	
	
	
}
