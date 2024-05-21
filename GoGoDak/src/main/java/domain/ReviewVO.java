package domain;

import java.sql.Date;

public class ReviewVO {
	private int review_seq;		  //리뷰고유번호
	private int fk_member_seq;	  //유저고유번호
	private int fk_product_seq;	  //제품고유번호
	private int fk_order_seq;	  //주문고유번호
	private String id;			  //아이디	
	private int star;		  //별점
	private Date ragisterdate;	  //작성날짜
	private String content;		  //내용
	private String pic;			  //첨부사진
	
	
	private MemberVO mdto = new MemberVO();
	private ProductVO pdto = new ProductVO();
	private OrderVO odto = new OrderVO();
	
	
	
	
	public int getReview_seq() {
		return review_seq;
	}
	public void setReview_seq(int review_seq) {
		this.review_seq = review_seq;
	}
	public int getFk_member_seq() {
		return fk_member_seq;
	}
	public void setFk_member_seq(int fk_member_seq) {
		this.fk_member_seq = fk_member_seq;
	}
	public int getFk_product_seq() {
		return fk_product_seq;
	}
	public void setFk_product_seq(int fk_product_seq) {
		this.fk_product_seq = fk_product_seq;
	}
	public int getFk_order_seq() {
		return fk_order_seq;
	}
	public void setFk_order_seq(int fk_order_seq) {
		this.fk_order_seq = fk_order_seq;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public int getStar() {
		return star;
	}
	public void setStar(int star) {
		this.star = star;
	}
	public Date getRagisterdate() {
		return ragisterdate;
	}
	public void setRagisterdate(Date ragisterdate) {
		this.ragisterdate = ragisterdate;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public String getPic() {
		return pic;
	}
	public void setPic(String pic) {
		this.pic = pic;
	}
	public MemberVO getMdto() {
		return mdto;
	}
	public void setMdto(MemberVO mdto) {
		this.mdto = mdto;
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
