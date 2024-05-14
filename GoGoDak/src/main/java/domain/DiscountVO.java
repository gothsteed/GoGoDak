package domain;

public class DiscountVO {
	private int discount_seq;		//할인고유번호
	private String discont_type;	//할인타입
	private float discount_number;	//할인율
	
	
	public int getDiscount_seq() {
		return discount_seq;
	}
	public void setDiscount_seq(int discount_seq) {
		this.discount_seq = discount_seq;
	}
	public String getDiscont_type() {
		return discont_type;
	}
	public void setDiscont_type(String discont_type) {
		this.discont_type = discont_type;
	}
	public float getDiscount_number() {
		return discount_number;
	}
	public void setDiscount_number(float discount_number) {
		this.discount_number = discount_number;
	}
	
	
}
