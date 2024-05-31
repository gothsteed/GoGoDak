package domain;

import org.json.JSONObject;

public class MemberPurchaseByMonthChart {
	private int product_type;
	private int m_01, m_02, m_03, m_04, m_05, m_06, m_07, m_08, m_09, m_10, m_11, m_12;
	private int purchase_count;
	private float percent;
	public MemberPurchaseByMonthChart(int product_type, int m_01, int m_02, int m_03, int m_04, int m_05, int m_06, int m_07,
			int m_08, int m_09, int m_10, int m_11, int m_12, int purchase_count, float percent) {
		super();
		this.product_type = product_type;
		this.m_01 = m_01;
		this.m_02 = m_02;
		this.m_03 = m_03;
		this.m_04 = m_04;
		this.m_05 = m_05;
		this.m_06 = m_06;
		this.m_07 = m_07;
		this.m_08 = m_08;
		this.m_09 = m_09;
		this.m_10 = m_10;
		this.m_11 = m_11;
		this.m_12 = m_12;
		this.purchase_count = purchase_count;
		this.percent = percent;
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

	
	
	public void setProduct_type(int product_type) {
		this.product_type = product_type;
	}
	public int getM_01() {
		return m_01;
	}
	public void setM_01(int m_01) {
		this.m_01 = m_01;
	}
	public int getM_02() {
		return m_02;
	}
	public void setM_02(int m_02) {
		this.m_02 = m_02;
	}
	public int getM_03() {
		return m_03;
	}
	public void setM_03(int m_03) {
		this.m_03 = m_03;
	}
	public int getM_04() {
		return m_04;
	}
	public void setM_04(int m_04) {
		this.m_04 = m_04;
	}
	public int getM_05() {
		return m_05;
	}
	public void setM_05(int m_05) {
		this.m_05 = m_05;
	}
	public int getM_06() {
		return m_06;
	}
	public void setM_06(int m_06) {
		this.m_06 = m_06;
	}
	public int getM_07() {
		return m_07;
	}
	public void setM_07(int m_07) {
		this.m_07 = m_07;
	}
	public int getM_08() {
		return m_08;
	}
	public void setM_08(int m_08) {
		this.m_08 = m_08;
	}
	public int getM_09() {
		return m_09;
	}
	public void setM_09(int m_09) {
		this.m_09 = m_09;
	}
	public int getM_10() {
		return m_10;
	}
	public void setM_10(int m_10) {
		this.m_10 = m_10;
	}
	public int getM_11() {
		return m_11;
	}
	public void setM_11(int m_11) {
		this.m_11 = m_11;
	}
	public int getM_12() {
		return m_12;
	}
	public void setM_12(int m_12) {
		this.m_12 = m_12;
	}
	public int getPurchase_count() {
		return purchase_count;
	}
	public void setPurchase_count(int purchase_count) {
		this.purchase_count = purchase_count;
	}
	public float getPercent() {
		return percent;
	}
	public void setPercent(float percent) {
		this.percent = percent;
	}
	
	
    public JSONObject toJson() {
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("product_type", getProduct_type_String());
        jsonObject.put("m_01", m_01);
        jsonObject.put("m_02", m_02);
        jsonObject.put("m_03", m_03);
        jsonObject.put("m_04", m_04);
        jsonObject.put("m_05", m_05);
        jsonObject.put("m_06", m_06);
        jsonObject.put("m_07", m_07);
        jsonObject.put("m_08", m_08);
        jsonObject.put("m_09", m_09);
        jsonObject.put("m_10", m_10);
        jsonObject.put("m_11", m_11);
        jsonObject.put("m_12", m_12);
        jsonObject.put("purchase_count", purchase_count);
        jsonObject.put("percent", percent);
        return jsonObject;
    }
	
	
	
	
	

}
