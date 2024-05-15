package domain;

public class Login_historyVO {
    
	
	private int login_history_seq;    //로그인히스토리
    private int fk_member_seq;		  //유저고유번호
    private String ip;				  //IP정보
    private String login_date;		  //로그인 일자

	private MemberVO mdto = new MemberVO();

    
    public int getLogin_history_seq() {
		return login_history_seq;
	}

	public void setLogin_history_seq(int login_history_seq) {
		this.login_history_seq = login_history_seq;
	}

	public int getFk_member_seq() {
		return fk_member_seq;
	}

	public void setFk_member_seq(int fk_member_seq) {
		this.fk_member_seq = fk_member_seq;
	}

	public String getIp() {
		return ip;
	}

	public void setIp(String ip) {
		this.ip = ip;
	}

	public String getLogin_date() {
		return login_date;
	}

	public void setLogin_date(String login_date) {
		this.login_date = login_date;
	}

	public MemberVO getMdto() {
		return mdto;
	}

	public void setMdto(MemberVO mdto) {
		this.mdto = mdto;
	}

    
}
