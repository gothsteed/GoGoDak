package domain;

import java.util.List;
import java.util.Map;
import java.util.Objects;

public class ProductVO {
	
    private int product_seq;			//제품고유번호
    private int fk_manufacturer_seq;	//제조업체고유번호
    private String product_name;		//제품이름
    private String description;			//제품설명
    private float base_price;				//가격
    private int stock;					//재고
    private String main_pic;			//메인사진
    private String description_pic;		//상세사진
    private int product_type;
    private String discount_type;
    private float discount_amount;
    
    
    
    //tbl_product_list select 용 필드
    private int quantity;
   

	private ManufacturerVO madto;
    //todo 할인 정보 dto 추가
    private Discount_eventVO discountVO;
    
    private Product_detailVO product_detailVO;
    
    
    
    public Product_detailVO getProduct_detailVO() {
		return product_detailVO;
	}

	public void setProduct_detailVO(Product_detailVO product_detailVO) {
		this.product_detailVO = product_detailVO;
	}

	@Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ProductVO productVO = (ProductVO) o;
        return product_seq == productVO.product_seq &&
               Objects.equals(product_detailVO != null ? product_detailVO.getProduct_detail_seq() : null, 
                              productVO.product_detailVO != null ? productVO.product_detailVO.getProduct_detail_seq() : null);
    }

    @Override
    public int hashCode() {
        return Objects.hash(product_seq, product_detailVO != null ? product_detailVO.getProduct_detail_seq() : null);
    }
    
    
    
    public String getDiscount_type() {
		return discount_type;
	}
	public void setDiscount_type(String discount_type) {
		this.discount_type = discount_type;
	}
	public float getDiscount_amount() {
		return discount_amount;
	}
	public void setDiscount_amount(float discount_amount) {
		this.discount_amount = discount_amount;
	}
    
	public Discount_eventVO getDiscountVO() {
		return discountVO;
	}
	public void setDiscountVO(Discount_eventVO discountVO) {
		this.discountVO = discountVO;
	}
	public int getProduct_seq() {
		return product_seq;
	}
	public int getProduct_type() {
		return product_type;
	}
	public void setProduct_type(int product_type) {
		this.product_type = product_type;
	}
	public void setProduct_seq(int product_Seq) {
		this.product_seq = product_Seq;
	}
	public int getFk_manufacturer_seq() {
		return fk_manufacturer_seq;
	}
	public void setFk_manufacturer_seq(int fk_manufacturer_seq) {
		this.fk_manufacturer_seq = fk_manufacturer_seq;
	}
	public String getProduct_name() {
		return product_name;
	}
	public void setProduct_name(String product_name) {
		this.product_name = product_name;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public float getBase_price() {
		return base_price;
	}
	public void setBase_price(float base_price) {
		this.base_price = base_price;
	}
	public int getStock() {
		return stock;
	}
	public void setStock(int stock) {
		this.stock = stock;
	}
	public String getMain_pic() {
		return main_pic;
	}
	public void setMain_pic(String main_pic) {
		this.main_pic = main_pic;
	}
	public String getDescription_pic() {
		return description_pic;
	}
	public void setDescription_pic(String description_pic) {
		this.description_pic = description_pic;
	}
	
	
	public ManufacturerVO getMadto() {
		return madto;
	}
	public void setMadto(ManufacturerVO madto) {
		this.madto = madto;
	}
	public float getDiscountPrice() {
		if(discount_type == null) {
			return base_price;
		}
		
		
		if(discount_type.equalsIgnoreCase("percent")) {
			return base_price - base_price * (discount_amount/100);
		}

		return base_price  - discount_amount;
	}

	public int getQuantity() {
		return quantity;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}






    
    
        
    
   
    
    

}
