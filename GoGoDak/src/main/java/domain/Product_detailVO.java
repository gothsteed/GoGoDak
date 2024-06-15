package domain;

public class Product_detailVO {
	
	private int product_detail_seq;
	private int fk_product_seq;
	private String detail_name;
	private int stock;
	
	
	public int getProduct_detail_seq() {
		return product_detail_seq;
	}
	public void setProduct_detail_seq(int product_detail_seq) {
		this.product_detail_seq = product_detail_seq;
	}
	public int getFk_product_seq() {
		return fk_product_seq;
	}
	public void setFk_product_seq(int fk_product_seq) {
		this.fk_product_seq = fk_product_seq;
	}
	public String getDetail_name() {
		return detail_name;
	}
	public void setDetail_name(String detail_name) {
		this.detail_name = detail_name;
	}
	public int getStock() {
		return stock;
	}
	public void setStock(int stock) {
		this.stock = stock;
	}
	
	
	
	

}
