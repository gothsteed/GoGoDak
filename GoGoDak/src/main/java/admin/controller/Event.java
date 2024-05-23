package admin.controller;

import java.io.File;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Collection;
import java.util.List;

import org.apache.tomcat.util.http.fileupload.FileItem;

import common.controller.AbstractController;
import discountEvent.model.DiscountEventDao;
import discountEvent.model.DiscountEventDao_imple;
import domain.ProductVO;
import jakarta.servlet.ServletContext;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import jakarta.servlet.http.Part;
import product.model.ProductDao;
import product.model.ProductDao_Imple;

public class Event extends AbstractController {
	
	private ProductDao productDao;
	private DiscountEventDao discountEventDao;
	
	public Event() {
		productDao = new ProductDao_Imple();
		discountEventDao = new DiscountEventDao_imple();
	}
	
	private static int safeParseInt(String str) {
	    try {
	        return Integer.parseInt(str);
	    } catch (NumberFormatException e) {
	        return -1;
	    }
	}

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		String method = request.getMethod();
		
		if(method.equalsIgnoreCase("post")) {
			postMethod(request, response);
			return;
		}
		
		
		List<ProductVO> productList = productDao.getAllProduct();
		
		request.setAttribute("productList", productList);
		
        super.setRedirect(false);
        super.setViewPage("/WEB-INF/view/admin/admin_eventWrite.jsp");

	}

	private void postMethod(HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		String name = request.getParameter("name");
		int[] selectedProducts = Arrays.stream(request.getParameterValues("product")).mapToInt(Event::safeParseInt).filter(val -> val != -1).toArray();
		
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
		
		if(name == null || savedFileName == null) {
			String loc = request.getContextPath() + "/product/event.dk";
			String message = "행사 등록 실패";
			
			request.setAttribute("loc", loc);
			request.setAttribute("message", message);
			
			setRedirect(false);
			setViewPage("/WEB-INF/view/msg.jsp");
			return;
		}
		
		
		int result = discountEventDao.insertDiscountEvent(name, savedFileName, selectedProducts);
		
		if(result != 1) {
			String loc = request.getContextPath() + "/product/event.dk";
			String message = "행사 등록 실패";
			
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

}
