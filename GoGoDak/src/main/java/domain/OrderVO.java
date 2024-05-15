package domain;

public class OrderVO {

	private int order_seq;			//주문고유번호
	private int fk_member_seq;		//유저고유번호
	private int fk_address_seq;		//주소고유번호
	private float total_pay;		//총금액
	
	private MemberVO mdto = new MemberVO();

	
	public int getOrder_seq() {
		return order_seq;
	}

	public void setOrder_seq(int order_seq) {
		this.order_seq = order_seq;
	}

	public int getFk_member_seq() {
		return fk_member_seq;
	}

	public void setFk_member_seq(int fk_member_seq) {
		this.fk_member_seq = fk_member_seq;
	}

	public int getFk_address_seq() {
		return fk_address_seq;
	}

	public void setFk_address_seq(int fk_address_seq) {
		this.fk_address_seq = fk_address_seq;
	}

	public float getTotal_pay() {
		return total_pay;
	}

	public void setTotal_pay(float total_pay) {
		this.total_pay = total_pay;
	}

	public MemberVO getMdto() {
		return mdto;
	}

	public void setMdto(MemberVO mdto) {
		this.mdto = mdto;
	}
	
	
	
}
