package domain;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

public class OrderVO {

    private int order_seq;            // 주문고유번호
    private int fk_member_seq;        // 유저고유번호
    private int fk_address_seq;       // 주소고유번호
    private float total_pay;          // 총금액
    private String postcode;          // 우편번호
    private String address;           // 주소
    private String address_detail;    // 상세주소
    private String address_extra;     // 
    private String id;
    private String name;
    private String tel;
    private int deliverystatus;
    private String delivery_message;
    private Date registerday;
    
    private List<Product_listVO> productList;
	private MemberVO mdto;
	
    public List<Product_listVO> getProductList() {
        return productList;
    }

    public void setProductList(List<Product_listVO> productList) {
        this.productList = productList;
    }
    
    public String getFirstProductName() {
        if (productList != null && !productList.isEmpty()) {
            return productList.get(0).getProduct_name();
        }
        return "";
    }

    // 모든 상품명을 합친 문자열을 반환하는 메서드
    public String getAllProductNames() {
        if (productList != null && !productList.isEmpty()) {
        	 return productList.stream()
                     .map(Product_listVO::getProduct_name)
                     .collect(Collectors.joining(", <br>"));
        }
        return "";
    }

    // 총 금액을 반환하는 메서드
    public float getTotalPay() {
        return total_pay;
    }

    // 기존 getter와 setter들

    public Date getRegisterday() {
        return registerday;
    }

    public void setRegisterday(Date registerday) {
        this.registerday = registerday;
    }

    public String getDelivery_message() {
        return delivery_message;
    }

    public void setDelivery_message(String delivery_message) {
        this.delivery_message = delivery_message;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getTel() {
        return tel;
    }

    public void setTel(String tel) {
        this.tel = tel;
    }

    public int getDeliverystatus() {
        return deliverystatus;
    }

    public void setDeliverystatus(int deliverystatus) {
        this.deliverystatus = deliverystatus;
    }

    public String getPostcode() {
        return postcode;
    }

    public void setPostcode(String postcode) {
        this.postcode = postcode;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getAddress_detail() {
        return address_detail;
    }

    public void setAddress_detail(String address_detail) {
        this.address_detail = address_detail;
    }

    public String getAddress_extra() {
        return address_extra;
    }

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
