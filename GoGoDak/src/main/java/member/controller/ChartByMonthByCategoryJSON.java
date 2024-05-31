package member.controller;

import java.util.List;

import org.json.JSONArray;
import org.json.JSONObject;

import common.controller.AbstractController;
import domain.MemberPurchaseByMonthChart;
import domain.MemberVO;
import domain.MemebrPurchaseChart;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import order.model.OrderDao;
import order.model.OrderDao_imple;
import product.model.ProductDao;
import product.model.ProductDao_Imple;

public class ChartByMonthByCategoryJSON extends AbstractController {
	
	private ProductDao productDao;
	private OrderDao orderDao;

	public ChartByMonthByCategoryJSON() {
		this.productDao = new ProductDao_Imple();
		this.orderDao = new OrderDao_imple();
	}

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		HttpSession session = request.getSession();
		MemberVO loginuser = (MemberVO) session.getAttribute("loginuser");

		if (!super.checkLogin(request)) {
			JSONObject jsonResponse = new JSONObject();
			jsonResponse.put("success", false);
			jsonResponse.put("message", "!!로그인 하시오!!");
			jsonResponse.put("loc", "javascript:history.back()");
			setRedirect(false);
			request.setAttribute("json", jsonResponse.toString());
			setViewPage("/WEB-INF/jsonview.jsp");
			return;
		}

		List<MemberPurchaseByMonthChart> myPurchaseChart = orderDao.memberPurchase_byMonth_byCategory(loginuser.getMember_seq());

		JSONArray json_arr = new JSONArray();

		if (myPurchaseChart.size() > 0) {
			for (MemberPurchaseByMonthChart chart : myPurchaseChart) {
				JSONObject json_obj = chart.toJson();

				json_arr.put(json_obj);
			} // end of for--------------
		}
		
		
		request.setAttribute("json", json_arr.toString());
		
	    super.setRedirect(false);
        super.setViewPage("/WEB-INF/jsonview.jsp");

	}

}
