package domain.member;

public class ProductVo {
	
    private String productSeq;
    private String productName;
    private String description;
    private String manufacturerName;
    private float price;
    private int stock;
    private String mainPic;
    private String descriptionPic;
    
    
    //todo 할인 정보 dto 추가
    
    
    
	public String getProductSeq() {
		return productSeq;
	}
	public void setProductSeq(String productSeq) {
		this.productSeq = productSeq;
	}
	public String getProductName() {
		return productName;
	}
	public void setProductName(String productName) {
		this.productName = productName;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public String getManufacturerName() {
		return manufacturerName;
	}
	public void setManufacturerName(String manufacturerName) {
		this.manufacturerName = manufacturerName;
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
	public String getMainPic() {
		return mainPic;
	}
	public void setMainPic(String mainPic) {
		this.mainPic = mainPic;
	}
	public String getDescriptionPic() {
		return descriptionPic;
	}
	public void setDescriptionPic(String descriptionPic) {
		this.descriptionPic = descriptionPic;
	}
    
    
    

}
