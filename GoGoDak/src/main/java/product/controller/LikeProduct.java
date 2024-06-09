package product.controller;

import java.io.PrintWriter;
import java.sql.SQLException;

import org.json.JSONObject;

import common.controller.AbstractController;
import conatainer.annotation.Autowired;
import domain.MemberVO;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import product.model.ProductDao;
import product.model.ProductDao_Imple;

public class LikeProduct extends AbstractController{
	
	private ProductDao productDao;
	
	@Autowired
	public LikeProduct(ProductDao productDao) {
		this.productDao =productDao;
	}

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		HttpSession session = request.getSession();

		String method = request.getMethod();
		if (!method.equalsIgnoreCase("post")) {
			System.out.println("!!!get으로 들어옴!!!");
		    JSONObject jsonResponse = new JSONObject();
		    jsonResponse.put("success", false);
		    jsonResponse.put("message", "잘못된 접근!");
		    jsonResponse.put("loc", "javascript:history.back()");
		    
		    PrintWriter pw = response.getWriter();
		    pw.print(jsonResponse.toString());
			return;
		}
		
		int product_seq;
		
		try {
			product_seq = Integer.parseInt(request.getParameter("product_seq"));
		}catch (NumberFormatException e) {
			System.out.println("!!!알 수 없는 제품!!!");
		    JSONObject jsonResponse = new JSONObject();
		    jsonResponse.put("success", false);
		    jsonResponse.put("message", "알 수 없는 제품");
		    jsonResponse.put("loc", "javascript:history.back()");
		    
		    PrintWriter pw = response.getWriter();
		    pw.print(jsonResponse.toString());
			return;
		}
		
	
		MemberVO loginuser =(MemberVO)session.getAttribute("loginuser");
		if(loginuser == null) {
			System.out.println("로그인 하시오");
		    JSONObject jsonResponse = new JSONObject();
		    jsonResponse.put("success", false);
		    jsonResponse.put("message", "로그인 하시오");
		    
		    PrintWriter pw = response.getWriter();
		    pw.print(jsonResponse.toString());
			return;
		}
		
		
		int member_seq =loginuser.getMember_seq();
		
		try {
			int result = productDao.insertProductLike(member_seq, product_seq);
			
			if(result == 1) {
				System.out.println("!!!좋아요 성공!!!");
			    JSONObject jsonResponse = new JSONObject();
			    jsonResponse.put("success", true);
			    jsonResponse.put("message", "좋아요 성공");
			    
			    PrintWriter pw = response.getWriter();
			    pw.print(jsonResponse.toString());
				return;
			}
			
			System.out.println("!!!좋아요 실패!!!");
		    JSONObject jsonResponse = new JSONObject();
		    jsonResponse.put("success", false);
		    jsonResponse.put("message", "좋아요 실패");
		    
		    PrintWriter pw = response.getWriter();
		    pw.print(jsonResponse.toString());
			return;
		
		} catch (SQLException  e) {
			System.out.println("sql 에러 : " + e.getErrorCode() + "   " + e.getMessage());
			if(e.getErrorCode() == 1) {
				int result = productDao.deletedProductLike(member_seq, product_seq);
				
				if(result == 1) {
					System.out.println("!!!좋아요 삭제!!!");
				    JSONObject jsonResponse = new JSONObject();
				    jsonResponse.put("success", true);
				    jsonResponse.put("message", "좋아요 삭제");
				    
				    PrintWriter pw = response.getWriter();
				    pw.print(jsonResponse.toString());
					return;
				}
				
				System.out.println("!!!좋아요 삭제 실패!!!");
			    JSONObject jsonResponse = new JSONObject();
			    jsonResponse.put("success", false);
			    jsonResponse.put("message", "좋아요 삭제 실패");
			    
			    PrintWriter pw = response.getWriter();
			    pw.print(jsonResponse.toString());
				return;
				
			}
		}
		
		
		System.out.println("!!!서버에러!!!");
	    JSONObject jsonResponse = new JSONObject();
	    jsonResponse.put("success", false);
	    jsonResponse.put("message", "서버에러");
	    
	    PrintWriter pw = response.getWriter();
	    pw.print(jsonResponse.toString());
		return;
		
	}

}
