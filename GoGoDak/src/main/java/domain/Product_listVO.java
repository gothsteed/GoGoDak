package domain;

public class Product_listVO {
	private int order_seq;
	private int product_seq;
	private String product_name;
	
	private ProductVO pdto = new ProductVO();
	private OrderVO odto = new OrderVO();
	
	
	
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
	
	
	
	
}
