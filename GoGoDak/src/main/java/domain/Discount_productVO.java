package domain;

public class Discount_productVO {
	private int product_seq;		//제품고유번호
	private int discount_seq;		//할인고유번호
	
	private ProductVO pdto = new ProductVO();
	private DiscountVO didto = new DiscountVO();
	
	
	public int getProduct_seq() {
		return product_seq;
	}

	public void setProduct_seq(int product_seq) {
		this.product_seq = product_seq;
	}

	public int getDiscount_seq() {
		return discount_seq;
	}

	public void setDiscount_seq(int discount_seq) {
		this.discount_seq = discount_seq;
	}

	public ProductVO getPdto() {
		return pdto;
	}

	public void setPdto(ProductVO pdto) {
		this.pdto = pdto;
	}

	public DiscountVO getDidto() {
		return didto;
	}

	public void setDidto(DiscountVO didto) {
		this.didto = didto;
	}
	
	
	
}
