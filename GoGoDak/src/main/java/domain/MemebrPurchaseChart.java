package domain;

import org.json.JSONObject;

public class MemebrPurchaseChart {
	private int product_type;
	private int purchase_count;
	private float percentage;
	
	
	public MemebrPurchaseChart(int product_type, int purchase_count, float percentage) {
		this.product_type = product_type;
		this.purchase_count = purchase_count;
		this.percentage = percentage;
	}
	








	public void setProduct_type(int product_type) {
		this.product_type = product_type;
	}
	
	public String getProduct_type_String( ) {
		String productTypeString = null;
		
		
		if(product_type == 1) {
			productTypeString = "닭가슴살";
		}
		else if(product_type == 2) {
			productTypeString = "볶음밥";
			
		}
		else if(product_type== 3) {
			productTypeString = "베이커리";
		}
		else if(product_type== 4) {
			productTypeString = "디저트";
		}
		else {
			productTypeString = "가타";
			
		}
		
		return productTypeString;
	}




	public int getPurchase_count() {
		return purchase_count;
	}


	public void setPurchase_count(int purchase_count) {
		this.purchase_count = purchase_count;
	}


	public float getPercentage() {
		return percentage;
	}


	public void setPercentage(float percentage) {
		this.percentage = percentage;
	}

    public JSONObject toJson() {
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("product_type", getProduct_type_String());
        jsonObject.put("purchase_count", purchase_count);
        jsonObject.put("percentage", percentage);
        return jsonObject;
    }
	
	
	
	

}
