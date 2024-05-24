package domain;

public class OrderVO {

	private int order_seq;			//주문고유번호
	private int fk_member_seq;		//유저고유번호
	private int fk_address_seq;		//주소고유번호
	private float total_pay;		//총금액
	private String postcode; 	// 우편번호
	private String address;	// 주소
	private String address_detail; // 상세주소
	private String address_extra; // 
	private String id;
	private String name;
	private String tel;
	private int deliverystatus;
	
	
	private MemberVO mdto = new MemberVO();
	
	
	
	
	/**
	 * @return the id
	 */
	public String getId() {
		return id;
	}

	/**
	 * @param id the id to set
	 */
	public void setId(String id) {
		this.id = id;
	}

	/**
	 * @return the name
	 */
	public String getName() {
		return name;
	}

	/**
	 * @param name the name to set
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * @return the tel
	 */
	public String getTel() {
		return tel;
	}

	/**
	 * @param tel the tel to set
	 */
	public void setTel(String tel) {
		this.tel = tel;
	}

	/**
	 * @return the deliverystatus
	 */
	public int getDeliverystatus() {
		return deliverystatus;
	}

	/**
	 * @param deliverystatus the deliverystatus to set
	 */
	public void setDeliverystatus(int deliverystatus) {
		this.deliverystatus = deliverystatus;
	}


	/**
	 * @return the postcode
	 */
	public String getPostcode() {
		return postcode;
	}

	/**
	 * @param postcode the postcode to set
	 */
	public void setPostcode(String postcode) {
		this.postcode = postcode;
	}

	/**
	 * @return the address
	 */
	public String getAddress() {
		return address;
	}

	/**
	 * @param address the address to set
	 */
	public void setAddress(String address) {
		this.address = address;
	}

	/**
	 * @return the address_detail
	 */
	public String getAddress_detail() {
		return address_detail;
	}

	/**
	 * @param address_detail the address_detail to set
	 */
	public void setAddress_detail(String address_detail) {
		this.address_detail = address_detail;
	}

	/**
	 * @return the address_extra
	 */
	public String getAddress_extra() {
		return address_extra;
	}

	/**
	 * @param address_extra the address_extra to set
	 */
	public void setAddress_extra(String address_extra) {
		this.address_extra = address_extra;
	}

	

	
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
