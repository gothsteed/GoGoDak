package domain;

public class ProductVO {
	
    private int product_Seq;			//제품고유번호
    private int fk_manufacturer_seq;	//제조업체고유번호
    private String product_name;		//제품이름
    private String description;			//제품설명
    private float price;				//가격
    private int stock;					//재고
    private String main_pic;			//메인사진
    private String description_pic;		//상세사진
    private int product_type;
    
    
    
    
    
   
    private MaufacturerVO madto;
    //todo 할인 정보 dto 추가
    private DiscountVO discountVO;
    
    
    

    
	public DiscountVO getDiscountVO() {
		return discountVO;
	}
	public void setDiscountVO(DiscountVO discountVO) {
		this.discountVO = discountVO;
	}
	public int getProduct_Seq() {
		return product_Seq;
	}
	public int getProduct_type() {
		return product_type;
	}
	public void setProduct_type(int product_type) {
		this.product_type = product_type;
	}
	public void setProduct_Seq(int product_Seq) {
		this.product_Seq = product_Seq;
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
	public float getPrice() {
		return price;
	}
	public void setPrice(float price) {
		this.price = price;
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
	
	
	public MaufacturerVO getMadto() {
		return madto;
	}
	public void setMadto(MaufacturerVO madto) {
		this.madto = madto;
	}
    
    
        
    
   
    
    

}
