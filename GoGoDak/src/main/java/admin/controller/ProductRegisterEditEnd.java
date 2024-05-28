package admin.controller;

import common.controller.AbstractController;
import domain.ProductVO;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.Part;
import product.model.ProductDao;
import product.model.ProductDao_Imple;

import java.io.File;
import java.io.IOException;
import java.sql.SQLException;
import java.util.Calendar;

public class ProductRegisterEditEnd extends AbstractController {

    private ProductDao productDao = null;

    public ProductRegisterEditEnd() {
        productDao = new ProductDao_Imple();
    }

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
        String method = request.getMethod(); // "GET" 또는 "POST"

        if ("POST".equalsIgnoreCase(method)) {
            // 사용자로부터 입력 받은 제품 정보 추출
            String product_seq = request.getParameter("product_seq"); // 제품 ID 추가
            String product_name = request.getParameter("product_name");
            String description = request.getParameter("description");
            String base_price = request.getParameter("base_price");
            String stock = request.getParameter("stock");
            String product_type = request.getParameter("product_type");
            String discount_type = request.getParameter("discount_type");
            String discount_amount = request.getParameter("discount_amount");
            String fk_manufacturer_seq = request.getParameter("fk_manufacturer_seq");
            
            // 원래 이미지 파일 가져오기
            ProductVO originalProduct = productDao.getProductBySeq(parseInteger(product_seq));
            String originalMainPic = originalProduct.getMain_pic();
            String originalDescriptionPic = originalProduct.getDescription_pic();

            // 이미지 파일 처리
            String main_pic = uploadFile(request, "main_pic", originalMainPic);
            String description_pic = uploadFile(request, "description_pic", originalDescriptionPic);

            // 입력값 검증 및 변환
            try {
                int productSeq = parseInteger(product_seq); // 제품 ID 변환
                int basePrice = parseInteger(base_price);
                int stockAmount = parseInteger(stock);
                int productType = product_type != null && !product_type.isEmpty() ? parseInteger(product_type) : originalProduct.getProduct_type();
                int discountAmount = parseInteger(discount_amount);
                int fkManufacturerSeq = fk_manufacturer_seq != null && !fk_manufacturer_seq.isEmpty() ? parseInteger(fk_manufacturer_seq) : originalProduct.getFk_manufacturer_seq();
                
                ProductVO pvo = new ProductVO();
                pvo.setProduct_seq(productSeq);
                pvo.setProduct_name(product_name);
                pvo.setDescription(description);
                pvo.setBase_price(basePrice);
                pvo.setStock(stockAmount);
                pvo.setProduct_type(productType);
                pvo.setDiscount_type(discount_type);
                pvo.setDiscount_amount(discountAmount);
                pvo.setMain_pic(main_pic);
                pvo.setDescription_pic(description_pic);
                pvo.setFk_manufacturer_seq(fkManufacturerSeq);
                
                // 상품 수정 처리
                String message;
                String loc;
                try {
                    int productEdit = productDao.updateProduct(pvo);
                    if (productEdit == 1) {
                        message = "상품 수정 완료";
                        loc = request.getContextPath() + "/index.dk"; // 시작페이지로 이동한다.
                    } else {
                        message = "상품 수정 실패";
                        loc = "javascript:history.back()"; // 자바스크립트를 이용한 이전페이지로 이동하는 것.
                    }
                } catch (SQLException e) {
                    message = "상품 수정 중 오류 발생";
                    loc = "javascript:history.back()"; // 자바스크립트를 이용한 이전페이지로 이동하는 것.
                    e.printStackTrace();
                }

                // 결과 메시지 및 이동 경로 설정
                request.setAttribute("message", message);
                request.setAttribute("loc", loc);
                request.setAttribute("productRegisterEditEnd", true);

                // 페이지 이동
                super.setViewPage("/WEB-INF/view/msg.jsp");

            } catch (NumberFormatException e) {
                String message = "숫자로 입력해야 하는 필드가 잘못되었습니다.";
                String loc = "javascript:history.back()";

                request.setAttribute("message", message);
                request.setAttribute("loc", loc);

                super.setViewPage("/WEB-INF/view/msg.jsp");
            }
        }
    }

    private String uploadFile(HttpServletRequest request, String fieldName, String originalFileName) throws ServletException, IOException {
        String savePath = request.getServletContext().getRealPath("/images/product");
        Part part = request.getPart(fieldName);

        if (part != null && part.getSize() > 0) {
            String fileName = extractFileName(part.getHeader("Content-Disposition"));
            String newFilename = generateFileName(fileName);
            String filePath = savePath + File.separator + newFilename;

            part.write(filePath);
            return newFilename;
        }

        return originalFileName;
    }

    private String extractFileName(String contentDisposition) {
        String[] items = contentDisposition.split(";");
        for (String item : items) {
            if (item.trim().startsWith("filename")) {
                return item.substring(item.indexOf("=") + 2, item.length() - 1);
            }
        }
        return "";
    }

    private String generateFileName(String fileName) {
        String newFilename = fileName.substring(0, fileName.lastIndexOf("."));
        newFilename += "_" + String.format("%1$tY%1$tm%1$td%1$tH%1$tM%1$tS", Calendar.getInstance());
        newFilename += System.nanoTime();
        newFilename += fileName.substring(fileName.lastIndexOf("."));
        return newFilename;
    }

    private int parseInteger(String str) throws NumberFormatException {
        return str != null && !str.trim().isEmpty() ? Integer.parseInt(str.trim()) : 0;
    }
}
