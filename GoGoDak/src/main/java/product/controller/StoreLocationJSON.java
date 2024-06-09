package product.controller;

import java.util.List;
import java.util.Map;

import org.json.JSONArray;
import org.json.JSONObject;

import common.controller.AbstractController;
import conatainer.annotation.Autowired;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import product.model.ProductDao;
import product.model.ProductDao_Imple;


public class StoreLocationJSON extends AbstractController {

	private ProductDao pdao = null;
	
	@Autowired
	public StoreLocationJSON(ProductDao pdao) {
		this.pdao = pdao;
	}
	
	
	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		  // tbl_map(위,경도) 테이블에 있는 정보를 가져오기(select)
        List<Map<String, String>> storeMapList = pdao.selectStoreMap(); 

        JSONArray jsonArr = new JSONArray(); // []
        
        if(storeMapList.size() > 0) {
           for(Map<String, String> storeMap : storeMapList) {
              JSONObject jsonObj = new JSONObject(); // {}
           
              int manufacturer_seq = Integer.parseInt(storeMap.get("manufacturer_seq"));
              String manufacturer_name = storeMap.get("manufacturer_name");
              String manufacturer_tel =storeMap.get("manufacturer_tel");
              String location = storeMap.get("location");
              String manufacturer_url = storeMap.get("manufacturer_url");
              String manufacturer_img = storeMap.get("manufacturer_img");
              double lat = Double.parseDouble(storeMap.get("LAT"));
              double lng = Double.parseDouble(storeMap.get("LNG")) ;
           
              
              jsonObj.put("manufacturer_seq", manufacturer_seq);
              jsonObj.put("manufacturer_name", manufacturer_name);
              jsonObj.put("manufacturer_tel", manufacturer_tel);
              jsonObj.put("location", location);
              jsonObj.put("manufacturer_url", manufacturer_url);
              jsonObj.put("manufacturer_img", manufacturer_img);
              jsonObj.put("lat", lat);
              jsonObj.put("lng", lng);
              
          
            
              System.out.println(manufacturer_seq);
              System.out.println(manufacturer_name);
              System.out.println(manufacturer_tel);
              System.out.println(location);
              System.out.println(lat);
              System.out.println(lng);
           
            
              
              jsonArr.put(jsonObj); // [{},{},{},{},{}]
           }// end of for---------------------
        }
        String json = jsonArr.toString();
        request.setAttribute("json", json);
        
     //   super.setRedirect(false);
        super.setViewPage("/WEB-INF/jsonview.jsp");


	}

}
