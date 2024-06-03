package admin.controller;

import java.io.File;
import java.util.Calendar;
import java.util.Collection;
import jakarta.servlet.annotation.MultipartConfig;
import jakarta.servlet.http.Part;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import common.controller.AbstractController;
import domain.ProductVO;
import jakarta.servlet.ServletContext;
import product.model.ProductDao;
import product.model.ProductDao_Imple;

@MultipartConfig(fileSizeThreshold = 1024 * 1024, // 1MB
                 maxFileSize = 1024 * 1024 * 50,   // 50MB
                 maxRequestSize = 1024 * 1024 * 100) // 100MB
public class ProductRegister extends AbstractController {

    private ProductDao productDao;

    public ProductRegister() {
        productDao = new ProductDao_Imple();
    }

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
        String method = request.getMethod();

        if (method.equalsIgnoreCase("post")) {
            handleFileUpload(request); // 파일 업로드 처리
            
            // POST 방식으로 요청이 온 경우
            // 상품 등록 처리
        } else { // GET 방식일 때
            // GET 방식으로 요청이 온 경우
            super.setRedirect(false);
            super.setViewPage("/WEB-INF/view/admin/admin_productRegister.jsp");
        }
    }

    // 파일 업로드 처리 메서드
    private void handleFileUpload(HttpServletRequest request) throws Exception {
        HttpSession session = request.getSession();
        ServletContext servletContext = session.getServletContext();
        String savePath = servletContext.getRealPath("/images/product");
        System.out.println("save path : " + savePath);

        Collection<Part> parts = request.getParts();
        String mainPicFileName = null;
        String descriptionPicFileName = null;

        for (Part part : parts) {
            if (!part.getHeader("Content-Disposition").contains("filename=")) {
                continue;
            }

            String fileName = extractFileName(part.getHeader("Content-Disposition"));
            if (part.getSize() <= 0) {
                continue;
            }

            String newFilename = fileName.substring(0, fileName.lastIndexOf(".")); // 확장자를 뺀 파일명 알아오기
            newFilename += "_" + String.format("%1$tY%1$tm%1$td%1$tH%1$tM%1$tS", Calendar.getInstance());
            newFilename += System.nanoTime();
            newFilename += fileName.substring(fileName.lastIndexOf(".")); // 확장자 붙이기

            System.out.println("new File upload: " + newFilename);

            part.write(savePath + File.separator + newFilename);
            part.delete(); // 임시저장된 파일 데이터를 제거

            if (mainPicFileName == null) {
                mainPicFileName = newFilename;
            } else {
                descriptionPicFileName = newFilename;
            }
        }

        // 파일 업로드 완료 후, 상품 등록 처리
        String fk_manufacturer_seq = request.getParameter("fk_maunfactuer_seq");
        String product_name = request.getParameter("product_name");
        String description = request.getParameter("description");
        String base_price = request.getParameter("base_price");
        
        String stock = request.getParameter("stock");
        String product_type = request.getParameter("product_type");
        String discount_type = request.getParameter("discount_type");
        String discount_amount = request.getParameter("discount_amount");

        ProductVO pvo = new ProductVO();
        pvo.setFk_manufacturer_seq(fk_manufacturer_seq != null && !fk_manufacturer_seq.isEmpty() ? Integer.parseInt(fk_manufacturer_seq) : 0);
        pvo.setProduct_name(product_name);
        pvo.setDescription(description);
        pvo.setBase_price(Integer.parseInt(base_price));
        pvo.setStock(Integer.parseInt(stock));
        pvo.setMain_pic(mainPicFileName);
        pvo.setDescription_pic(descriptionPicFileName);
        pvo.setProduct_type(Integer.parseInt(product_type));
        pvo.setDiscount_type(discount_type);

        try {
            if ("none".equals(discount_type)) {
                pvo.setDiscount_amount(0);
            } else {
                pvo.setDiscount_amount(Integer.parseInt(discount_amount));
            }
        } catch (NumberFormatException e) {
            pvo.setDiscount_amount(0); // 기본값 설정 또는 에러 처리
        }
        
        System.out.println("dicount amount : " +Integer.parseInt(discount_amount));

        int result = productDao.productregister(pvo);
        System.out.println("result : " + result);

        if (result == 1) {
            String message = "상품이 등록 되었습니다.";
            String loc = request.getContextPath() + "/index.dk";

            request.setAttribute("message", message);
            request.setAttribute("loc", loc);

            super.setViewPage("/WEB-INF/view/msg.jsp");
        }
    }

    // Content-Disposition 헤더에서 파일명 추출하는 메서드
    private String extractFileName(String header) {
        // Content-Disposition 헤더의 값 예시: form-data; name="file"; filename="example.jpg"
        for (String part : header.split(";")) {
            if (part.trim().startsWith("filename")) {
                String fileName = part.substring(part.indexOf('=') + 1).trim().replace("\"", "");
                return fileName.substring(fileName.lastIndexOf('/') + 1).substring(fileName.lastIndexOf('\\') + 1);
            }
        }
        return null;
    }
}
