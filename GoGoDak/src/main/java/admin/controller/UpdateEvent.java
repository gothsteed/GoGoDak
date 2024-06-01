package admin.controller;

import java.io.File;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Collection;
import java.util.List;

import common.controller.AbstractController;
import discountEvent.model.DiscountEventDao;
import discountEvent.model.DiscountEventDao_imple;
import domain.Discount_eventVO;
import domain.MemberVO;
import domain.ProductVO;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import jakarta.servlet.http.Part;
import product.model.ProductDao;
import product.model.ProductDao_Imple;

public class UpdateEvent extends AbstractController {
	
	private ProductDao productDao;
	private DiscountEventDao discountEventDao;
	
	public UpdateEvent() {
		this.productDao = new ProductDao_Imple();
		discountEventDao = new DiscountEventDao_imple();
	}
	
	private static int safeParseInt(String str) {
	    try {
	        return Integer.parseInt(str);
	    } catch (NumberFormatException e) {
	        return -1;
	    }
	}
	
	private void postMethod(HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		int  discount_event_seq ;
		try {
			discount_event_seq =  Integer.parseInt(request.getParameter("discount_event_seq"));
		} catch (NumberFormatException e) {
			String loc = request.getContextPath() + "/product/event.dk";
			String message = "행사 수정 실패";
			
			request.setAttribute("loc", loc);
			request.setAttribute("message", message);
			
			setRedirect(false);
			setViewPage("/WEB-INF/view/msg.jsp");
			return;
		}
		
		String name = request.getParameter("name");
		int[] selectedProducts = Arrays.stream(request.getParameterValues("product")).mapToInt(UpdateEvent::safeParseInt).filter(val -> val != -1).toArray();
		if(name == null ) {
			String loc = request.getContextPath() + "/product/event.dk";
			String message = "행사 수정 실패";
			
			request.setAttribute("loc", loc);
			request.setAttribute("message", message);
			
			setRedirect(false);
			setViewPage("/WEB-INF/view/msg.jsp");
			return;
		}
		
		HttpSession session = request.getSession();
		String savePath = session.getServletContext().getRealPath("/images/special");
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
	
		
		int result = 0;
		if(savedFileName == null) {
			result = discountEventDao.updateDiscountEvent(discount_event_seq , name,selectedProducts);
		}
		else {
			result = discountEventDao.updateDiscountEvent(discount_event_seq, name, savedFileName, selectedProducts);
		}
		
		
		if(result != 1) {
			String loc = request.getContextPath() + "/product/event.dk";
			String message = "행사 수정 실패";
			
			request.setAttribute("loc", loc);
			request.setAttribute("message", message);
			
			setRedirect(false);
			setViewPage("/WEB-INF/view/msg.jsp");
			return;
			
		}
		
		
		
		String loc = request.getContextPath() + "/product/event.dk";
		String message = "행사 등록 성공";
		
		request.setAttribute("loc", loc);
		request.setAttribute("message", message);
		
		setRedirect(false);
		setViewPage("/WEB-INF/view/msg.jsp");
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
	
	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		HttpSession session = request.getSession();
		MemberVO loginuser = (MemberVO)session.getAttribute("loginuser");
		
		if(loginuser == null || !loginuser.getId().equals("admin")) {
			String message = "관리자만 접근이 가능합니다.";
	        String loc = "javascript:history.back()";
	         
	        request.setAttribute("message", message);
	        request.setAttribute("loc", loc);
	         
	        super.setRedirect(false);
	        super.setViewPage("/WEB-INF/view/msg.jsp");
	        return;
		}
		
		String method = request.getMethod();
		
		
		if(method.equalsIgnoreCase("post")) {
			postMethod(request, response);
			return;
		}
		
		
		int discount_event_seq;
		
		try {
			discount_event_seq = Integer.parseInt(request.getParameter("discount_event_seq"));
		} catch (NumberFormatException e) {
			super.setRedirect(true);
			super.setViewPage(request.getContextPath() + "/product/event.dk");
			return;
		}
		
		
		Discount_eventVO discount_eventVO = discountEventDao.getDiscountEventBySeq(discount_event_seq);
		List<ProductVO> productList =  productDao.getAllProduct();
		List<ProductVO> currentEventProductList = productDao.getProductByDiscountEvent(discount_event_seq);
		
		request.setAttribute("productList", productList);
		request.setAttribute("discount_eventVO", discount_eventVO);
		request.setAttribute("currentEventProductList", currentEventProductList);
		
        super.setRedirect(false);
        super.setViewPage("/WEB-INF/view/admin/admin_eventWrite.jsp");
		
		

	}

}
