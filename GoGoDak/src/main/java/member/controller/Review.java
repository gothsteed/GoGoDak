package member.controller;

import java.io.File;
import java.io.IOException;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collector;
import java.util.stream.Collectors;

import org.json.JSONObject;

import common.controller.AbstractController;
import domain.AnswerVO;
import domain.MemberVO;
import domain.OrderVO;
import domain.ProductVO;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import jakarta.servlet.http.Part;
import order.model.OrderDao;
import order.model.OrderDao_imple;
import review.model.ReviewDao;
import review.model.ReviewDao_imple;

public class Review extends AbstractController {
	
	private ReviewDao reviewDao;
	private OrderDao orderDao;
	
	public Review() {
		this.reviewDao = new ReviewDao_imple();
		this.orderDao = new OrderDao_imple();
	}
	
	
	private void sendMsg(HttpServletRequest request, boolean isSuccess, String message) {
        System.out.println(message);
        JSONObject jsonResponse = new JSONObject();
        jsonResponse.put("success", isSuccess);
        jsonResponse.put("message",message);

        System.out.println(jsonResponse.toString());
        setRedirect(false);
        request.setAttribute("json", jsonResponse.toString());
        setViewPage("/WEB-INF/jsonview.jsp");
        return;
	}
	
	
	private String extractFileName(String partHeader) {
		for (String cd : partHeader.split("\\;")) {
			if (cd.trim().startsWith("filename")) {
				String fileName = cd.substring(cd.indexOf("=") + 1).trim().replace("\"", "");
				int index = fileName.lastIndexOf(File.separator); // File.separator 란? os가 windows 이라면 \ 이고 , OS 가
																	// mac,linux,unux이라면 / 을 말하는 것이다
				return fileName.substring(index + 1);
			}
		}
		return null;
	}
	
	private String saveFile(HttpServletRequest request) throws IOException, ServletException {
		
		HttpSession session = request.getSession();
		String savePath = session.getServletContext().getRealPath("/images/review");
		System.out.println("save path : " + savePath);
		
		
		Collection<Part> parts = request.getParts();
		
		String savedFileName = null;
		
		
		
		for(Part part : parts) {
			
			if (!part.getHeader("Content-Disposition").contains("filename=")) {
				continue;
			}
			
			String fileName = extractFileName(part.getHeader("Content-Disposition"));
			if (part.getSize() <= 0) {
				continue;
			}
			
			
			String newFilename = fileName.substring(0, fileName.lastIndexOf(".")); // 확장자를 뺀 파일명 알아오기

			newFilename += "_"
					+ String.format("%1$tY%1$tm%1$td%1$tH%1$tM%1$tS", Calendar.getInstance());
			newFilename += System.nanoTime();
			newFilename += fileName.substring(fileName.lastIndexOf(".")); // 확장자 붙이기
			
			System.out.println("new File upload: " + newFilename);
			savedFileName = newFilename;
			
			part.write(savePath + File.separator + newFilename);
			
			// >>> 임시저장된 파일 데이터를 제거해준다. <<<
			// 즉 ,
			// @MultipartConfig(location="C:\\NCS\\workspace_jsp\\MyMVC\\images_temp_upload",
			// fileSizeThreshold = 1024) 와 같이 설정되었다면
			// C:\\NCS\\workspace_jsp\\MyMVC\\images_temp_upload 폴더에 임시 저장된파일을 제거해야하다
			part.delete();
			
		}
		
		return savedFileName;
	}
	
	

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		String method =request.getMethod();
		
		if(!method.equalsIgnoreCase("post")) {
	        boolean isSuccess = false;
	        String message = "잘못된 접근";
	        
	        sendMsg(request, isSuccess, message);
	        return;
		}
		//TODO: view에서 order_seq, product_seq를 받아와야함.
		
		
		int order_seq;
		try {
			order_seq = Integer.parseInt(request.getParameter("order_seq"));
		} catch (NumberFormatException e) {
	        boolean isSuccess = false;
	        String message = "잘못된 접근";
	        
	        sendMsg(request, isSuccess, message);
			return;
		}
		
		int product_seq;
		try {
			product_seq = Integer.parseInt(request.getParameter("product_seq"));
		}
		catch (NumberFormatException e) {
	        boolean isSuccess = false;
	        String message = "잘못된 접근";
	        
	        sendMsg(request, isSuccess, message);
	        return;
		}
		System.out.println("order_seq: " + order_seq);
		System.out.println("product_seq: " + product_seq);
		
	    HttpSession session = request.getSession();
	    MemberVO loginuser = (MemberVO)session.getAttribute("loginuser");
		
		OrderVO order = orderDao.getOrderBySeq(order_seq);
		List<ProductVO> productList = orderDao.getProductList(order_seq);
		
		
		if(order.getFk_member_seq() != loginuser.getMember_seq()) {
	        boolean isSuccess = false;
	        String message = "잘못된 접근";
	        
	        sendMsg(request, isSuccess, message);
			return;
		}
		
		List<Integer> product_seqArr = productList.stream().map(p -> p.getProduct_seq()).collect(Collectors.toList());
		if(!product_seqArr.contains(product_seq)) {
	        boolean isSuccess = false;
	        String message = "잘못된 접근";
	        
	        sendMsg(request, isSuccess, message);
			return;
		}
		
		
		int star;
		try {
			star = Integer.parseInt(request.getParameter("star"));
		}
		catch (NumberFormatException e) {
	        boolean isSuccess = false;
	        String message = "잘못된 입력";
	        
	        sendMsg(request, isSuccess, message);
			return;
		}
		String content = request.getParameter("content");
		String picName = saveFile(request);
		
		int result = reviewDao.insertReview(loginuser.getMember_seq(), product_seq, order_seq, loginuser.getId()
				,star, content, picName);
		
		
		if(result != 1) {
	        boolean isSuccess = false;
	        String message = "리뷰작성 실패";
	        
	        sendMsg(request, isSuccess, message);
			return;
		}
		
		
        boolean isSuccess = true;
        String message = "리뷰작성 성공";
        
        sendMsg(request, isSuccess, message);
		
	}

}
