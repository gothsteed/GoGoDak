package admin.controller;

import java.sql.SQLException;

import common.controller.AbstractController;
import conatainer.annotation.Autowired;
import domain.ProductVO;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import product.model.ProductDao;
import product.model.ProductDao_Imple;

public class ProductRegisterEditDelete extends AbstractController {

	private ProductDao productDao = null;

	
	@Autowired
	public ProductRegisterEditDelete(ProductDao productDao) {
		this.productDao = productDao;
	}

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws Exception {

		String method = request.getMethod(); // "GET" 또는 "POST"

		if ("POST".equalsIgnoreCase(method)) {
			// **** POST 방식으로 넘어온 것이라면 **** //
			String product_seq = request.getParameter("product_seq");

			ProductVO productDelete = new ProductVO();
			productDelete.setProduct_seq(Integer.parseInt(product_seq));

			// === 회원수정이 성공되어지면 "회원정보 수정 성공!!" 이라는 alert 를 띄우고 시작페이지로 이동한다. === //
			String message = "";
			String loc = "";

			try {
				int productEdit = productDao.deletedProduct(productDelete);
				System.out.println(productEdit);

				if (productEdit == 1) {

					message = "상품 삭제 완료";
					loc = request.getContextPath() + "/index.dk"; // 시작페이지로 이동한다.
				}
			} catch (SQLException e) {
				message = "SQL구문 에러발생";
				loc = "javascript:history.back()"; // 자바스크립트를 이용한 이전페이지로 이동하는 것.
				e.printStackTrace();
			}

			request.setAttribute("message", message);
			request.setAttribute("loc", loc);

			// super.setRedirect(false);
			super.setViewPage("/WEB-INF/view/msg.jsp");
		}

	}

}
