package admin.controller;

import java.io.File;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Collection;
import java.util.List;

import common.controller.AbstractController;
import conatainer.annotation.Autowired;
import domain.MemberVO;
import domain.Product_detailVO;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import jakarta.servlet.http.Part;
import product.model.ProductDao;
import product.model.ProductDao_Imple;
import jakarta.servlet.ServletContext;
import jakarta.servlet.annotation.MultipartConfig;

@MultipartConfig
public class ProductRegisterEdit extends AbstractController {

    private ProductDao productDao =null;

    @Autowired
    public ProductRegisterEdit(ProductDao productDao) {
        this.productDao = productDao;
    }


    // 상품 타입 목록
   // private List<String> productTypes = Arrays.asList("1.🍗닭가슴살🍗", "2.🍱볶음밥🍱", "3.🥯빵🥯", "4.🧁디저트🧁");
    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
      
    	HttpSession session = request.getSession();
        MemberVO loginuser = (MemberVO) session.getAttribute("loginuser"); 

        if(loginuser.getId().equalsIgnoreCase("admin")) {
          

        	String product_seq= request.getParameter("product_seq");
            String product_name = request.getParameter("product_name");
            String description = request.getParameter("description");
            String base_price = request.getParameter("base_price");
            String stock = request.getParameter("stock");
            String product_type = request.getParameter("product_type");
            String discount_type = request.getParameter("discount_type");
            String discount_amount = request.getParameter("discount_amount");
            String main_pic = request.getParameter("main_pic");
            String discription_pic = request.getParameter("discription_pic");
            String fk_manufacturer_seq = request.getParameter("fk_manufacturer_seq");
            
            int product_seqInt;
            
            try {
				product_seqInt = Integer.parseInt(product_seq);
			} catch (NumberFormatException e) {
	            String message = "관리자 외 접근 불가능 합니다.";
	            String loc = "javascript:history.back()";

	            request.setAttribute("message", message);
	            request.setAttribute("loc", loc);

	            super.setRedirect(false);
	            super.setViewPage("/WEB-INF/view/msg.jsp");
	            return;
			}
            
            List<Product_detailVO>  productDetailList = productDao.getProductDetails(product_seqInt);

            
           // request.setAttribute("productTypes", productTypes);
            request.setAttribute("product_seq", product_seq);
            request.setAttribute("product_name", product_name);
            request.setAttribute("description", description);
            request.setAttribute("base_price", base_price);
            request.setAttribute("stock", stock);
            request.setAttribute("product_type", product_type);
            request.setAttribute("discount_type", discount_type);
            request.setAttribute("discount_amount", discount_amount);
            request.setAttribute("main_pic", main_pic);
            request.setAttribute("discription_pic", discription_pic);
            request.setAttribute("fk_manufacturer_seq", fk_manufacturer_seq);
            request.setAttribute("productDetailList", productDetailList);

            super.setRedirect(false);
            super.setViewPage("/WEB-INF/view/admin/admin_productRegisterEdit.jsp");
        } else {
            String message = "관리자 외 접근 불가능 합니다.";
            String loc = "javascript:history.back()";

            request.setAttribute("message", message);
            request.setAttribute("loc", loc);

            super.setRedirect(false);
            super.setViewPage("/WEB-INF/view/msg.jsp");
        }
    }



}
