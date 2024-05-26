package domain;

public class Product_listVO {
	private int order_seq;
	private int product_seq;
	private String product_name;
	
	private int fk_manufacturer_seq;	//제조업체고유번호
  
    private String description;			//제품설명
    private float base_price;				//가격
   
    
	private int stock;					//재고
    private String main_pic;			//메인사진
    private String description_pic;		//상세사진
    private int product_type;
    private String discount_type;
    private float discount_amount;
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
	  private int point;				//포인트
	    private String member_postcode;
	    private String member_address;
	    private String member_address_detail;
	    private String member_address_extra;

private int fk_order_seq;
private int fk_product_seq;
private int fk_discount_event_seq;
	
	
	/**
 * @return the fk_order_seq
 */
public int getFk_order_seq() {
	return fk_order_seq;
}
/**
 * @param fk_order_seq the fk_order_seq to set
 */
public void setFk_order_seq(int fk_order_seq) {
	this.fk_order_seq = fk_order_seq;
}
/**
 * @return the fk_product_seq
 */
public int getFk_product_seq() {
	return fk_product_seq;
}
/**
 * @param fk_product_seq the fk_product_seq to set
 */
public void setFk_product_seq(int fk_product_seq) {
	this.fk_product_seq = fk_product_seq;
}
/**
 * @return the fk_discount_event_seq
 */
public int getFk_discount_event_seq() {
	return fk_discount_event_seq;
}
/**
 * @param fk_discount_event_seq the fk_discount_event_seq to set
 */
public void setFk_discount_event_seq(int fk_discount_event_seq) {
	this.fk_discount_event_seq = fk_discount_event_seq;
}
	private ProductVO pdto = new ProductVO();
	private OrderVO odto = new OrderVO();
	
	
	private MemberVO mdto = new MemberVO();

    /**
	 * @return the point
	 */
	public int getPoint() {
		return point;
	}
	/**
	 * @param point the point to set
	 */
	public void setPoint(int point) {
		this.point = point;
	}
	/**
	 * @return the member_postcode
	 */
	public String getMember_postcode() {
		return member_postcode;
	}
	/**
	 * @param member_postcode the member_postcode to set
	 */
	public void setMember_postcode(String member_postcode) {
		this.member_postcode = member_postcode;
	}
	/**
	 * @return the member_address
	 */
	public String getMember_address() {
		return member_address;
	}
	/**
	 * @param member_address the member_address to set
	 */
	public void setMember_address(String member_address) {
		this.member_address = member_address;
	}
	/**
	 * @return the member_address_detail
	 */
	public String getMember_address_detail() {
		return member_address_detail;
	}
	/**
	 * @param member_address_detail the member_address_detail to set
	 */
	public void setMember_address_detail(String member_address_detail) {
		this.member_address_detail = member_address_detail;
	}
	/**
	 * @return the member_address_extra
	 */
	public String getMember_address_extra() {
		return member_address_extra;
	}
	/**
	 * @param member_address_extra the member_address_extra to set
	 */
	public void setMember_address_extra(String member_address_extra) {
		this.member_address_extra = member_address_extra;
	}
	
	/**
	 * @return the fk_manufacturer_seq
	 */
	public int getFk_manufacturer_seq() {
		return fk_manufacturer_seq;
	}
	/**
	 * @param fk_manufacturer_seq the fk_manufacturer_seq to set
	 */
	public void setFk_manufacturer_seq(int fk_manufacturer_seq) {
		this.fk_manufacturer_seq = fk_manufacturer_seq;
	}
	/**
	 * @return the description
	 */
	public String getDescription() {
		return description;
	}
	/**
	 * @param description the description to set
	 */
	public void setDescription(String description) {
		this.description = description;
	}
	/**
	 * @return the base_price
	 */
	public float getBase_price() {
		return base_price;
	}
	/**
	 * @param base_price the base_price to set
	 */
	public void setBase_price(float base_price) {
		this.base_price = base_price;
	}
	/**
	 * @return the stock
	 */
	public int getStock() {
		return stock;
	}
	/**
	 * @param stock the stock to set
	 */
	public void setStock(int stock) {
		this.stock = stock;
	}
	/**
	 * @return the main_pic
	 */
	public String getMain_pic() {
		return main_pic;
	}
	/**
	 * @param main_pic the main_pic to set
	 */
	public void setMain_pic(String main_pic) {
		this.main_pic = main_pic;
	}
	/**
	 * @return the description_pic
	 */
	public String getDescription_pic() {
		return description_pic;
	}
	/**
	 * @param description_pic the description_pic to set
	 */
	public void setDescription_pic(String description_pic) {
		this.description_pic = description_pic;
	}
	/**
	 * @return the product_type
	 */
	public int getProduct_type() {
		return product_type;
	}
	/**
	 * @param product_type the product_type to set
	 */
	public void setProduct_type(int product_type) {
		this.product_type = product_type;
	}
	/**
	 * @return the discount_type
	 */
	public String getDiscount_type() {
		return discount_type;
	}
	/**
	 * @param discount_type the discount_type to set
	 */
	public void setDiscount_type(String discount_type) {
		this.discount_type = discount_type;
	}
	/**
	 * @return the discount_amount
	 */
	public float getDiscount_amount() {
		return discount_amount;
	}
	/**
	 * @param discount_amount the discount_amount to set
	 */
	public void setDiscount_amount(float discount_amount) {
		this.discount_amount = discount_amount;
	}
	/**
	 * @return the fk_member_seq
	 */
	public int getFk_member_seq() {
		return fk_member_seq;
	}
	/**
	 * @param fk_member_seq the fk_member_seq to set
	 */
	public void setFk_member_seq(int fk_member_seq) {
		this.fk_member_seq = fk_member_seq;
	}
	/**
	 * @return the fk_address_seq
	 */
	public int getFk_address_seq() {
		return fk_address_seq;
	}
	/**
	 * @param fk_address_seq the fk_address_seq to set
	 */
	public void setFk_address_seq(int fk_address_seq) {
		this.fk_address_seq = fk_address_seq;
	}
	/**
	 * @return the total_pay
	 */
	public float getTotal_pay() {
		return total_pay;
	}
	/**
	 * @param total_pay the total_pay to set
	 */
	public void setTotal_pay(float total_pay) {
		this.total_pay = total_pay;
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
	 * @return the mdto
	 */
	public MemberVO getMdto() {
		return mdto;
	}
	/**
	 * @param mdto the mdto to set
	 */
	public void setMdto(MemberVO mdto) {
		this.mdto = mdto;
	}

	
	
	public int getOrder_seq() {
		return order_seq;
	}
	public void setOrder_seq(int order_seq) { 
		this.order_seq = order_seq;
	}
	public int getProduct_seq() {
		return product_seq;
	}
	public void setProduct_seq(int product_seq) {
		this.product_seq = product_seq;
	}
	public String getProduct_name() {
		return product_name;
	}
	public void setProduct_name(String product_name) {
		this.product_name = product_name;
	}
	public ProductVO getPdto() {
		return pdto;
	}
	public void setPdto(ProductVO pdto) {
		this.pdto = pdto;
	}
	public OrderVO getOdto() {
		return odto;
	}
	public void setOdto(OrderVO odto) {
		this.odto = odto;
	}
	public void setList_product_name(String string) {
		// TODO Auto-generated method stub
		
	}

	
	
	
	
}
