package member.controller;

import java.io.BufferedReader;
import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.json.JSONArray;
import org.json.JSONObject;

import common.controller.AbstractController;
import conatainer.annotation.Autowired;
import domain.Discount_eventVO;
import domain.MemberVO;
import domain.ProductVO;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import member.model.MemberDao;
import member.model.MemberDao_Imple;
import product.model.ProductDao;
import product.model.ProductDao_Imple;

public class Cart extends AbstractController {

	private ProductDao productDao;

	@Autowired
	public Cart( ProductDao productDao) {
		this.productDao =productDao;
	}

	private String getJsonStringFromRequest(HttpServletRequest request) throws IOException {
		StringBuilder jsonString = new StringBuilder();
		String line;

		try (BufferedReader reader = request.getReader()) {
			while ((line = reader.readLine()) != null) {
				jsonString.append(line);
			}
		}

		return jsonString.toString();
	}

	private void addToCart(JSONArray productArr, HttpServletRequest request) throws SQLException {
		float totalPay = 0;
		HttpSession session = request.getSession();

		if (session.getAttribute("cart") == null) {
			session.setAttribute("cart", new HashMap<ProductVO, Integer>());
		}

		Map<ProductVO, Integer> cart = (HashMap<ProductVO, Integer>) session.getAttribute("cart");
		
		for (int i = 0; i < productArr.length(); i++) {
			JSONObject productJson = productArr.getJSONObject(i);
			int product_seq = -1;
			try {
				product_seq = productJson.getInt("product_seq");
			} catch (NumberFormatException e) {
				System.out.println("not a number");
				JSONObject jsonResponse = new JSONObject();
				jsonResponse.put("success", false);
				jsonResponse.put("message", "Invalid product sequence.");

				System.out.println(jsonResponse.toString());
				setRedirect(false);
				request.setAttribute("json", jsonResponse.toString());
				setViewPage("/WEB-INF/jsonview.jsp");
				return;
			}

			ProductVO product = productDao.getProductBySeq(product_seq);

			if (product == null) {
				System.out.println("does not exist");
				JSONObject jsonResponse = new JSONObject();
				jsonResponse.put("success", false);
				jsonResponse.put("message", "Invalid product sequence.");

				System.out.println(jsonResponse.toString());
				setRedirect(false);
				request.setAttribute("json", jsonResponse.toString());
				setViewPage("/WEB-INF/jsonview.jsp");
				return;
			}


			if (productJson.getInt("quantity") > 0) {
				cart.put(product, productJson.getInt("quantity"));
				totalPay +=  product.getDiscountPrice() * productJson.getInt("quantity");
			} else {
				cart.remove(product);
			}

		}
		

		JSONObject jsonResponse = new JSONObject();
		jsonResponse.put("success", true);
		jsonResponse.put("message", "added to cart");
		jsonResponse.put("totalPay", Math.floor(totalPay));
		jsonResponse.put("size", cart.size());
		setRedirect(false);
		request.setAttribute("json", jsonResponse.toString());
		setViewPage("/WEB-INF/jsonview.jsp");
	}

	private void postMethod(HttpServletRequest request, HttpServletResponse response) throws Exception {
		System.out.println("====Cart Post====");
		String product_json = getJsonStringFromRequest(request);
		JSONObject jsonObject = new JSONObject(product_json);
		JSONArray productArr = jsonObject.getJSONArray("cart");

		System.out.println(product_json);

		addToCart(productArr, request);


	}

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		HttpSession session = request.getSession();

		String method = request.getMethod();
		if (method.equalsIgnoreCase("post")) {
			postMethod(request, response);
			return;
		}

//		MemberVO loginuser =(MemberVO)session.getAttribute("loginuser");

		if (session.getAttribute("cart") == null) {
			session.setAttribute("cart", new HashMap<ProductVO, Integer>());
		}

		super.setRedirect(false);
		super.setViewPage("/WEB-INF/view/member/cart.jsp");

	}

}
