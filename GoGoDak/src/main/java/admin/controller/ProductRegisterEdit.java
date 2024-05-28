package admin.controller;

import java.io.File;
import java.util.Calendar;
import java.util.Collection;
import common.controller.AbstractController;
import domain.MemberVO;
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

    public ProductRegisterEdit() {
        productDao = new ProductDao_Imple();
    }

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
