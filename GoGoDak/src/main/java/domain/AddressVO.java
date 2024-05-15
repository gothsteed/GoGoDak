package domain;

public class AddressVO {

	private int address_seq;		//주소고유번호
	private int fk_member_seq;		//유저고유번호
	private String poscode;			//우편번호
    private String address;			//메인주소
    private String addressDetail;	//상세주소
    private String addressExtra;	//참고항목
	
	
	
    public int getAddress_seq() {
		return address_seq;
	}
	public void setAddress_seq(int address_seq) {
		this.address_seq = address_seq;
	}
	public int getFk_member_seq() {
		return fk_member_seq;
	}
	public void setFk_member_seq(int fk_member_seq) {
		this.fk_member_seq = fk_member_seq;
	}
	public String getPoscode() {
		return poscode;
	}
	public void setPoscode(String poscode) {
		this.poscode = poscode;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public String getAddressDetail() {
		return addressDetail;
	}
	public void setAddressDetail(String addressDetail) {
		this.addressDetail = addressDetail;
	}
	public String getAddressExtra() {
		return addressExtra;
	}
	public void setAddressExtra(String addressExtra) {
		this.addressExtra = addressExtra;
	}
	
    
    
    
    
}
