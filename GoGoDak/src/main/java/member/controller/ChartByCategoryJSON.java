package member.controller;

import java.util.List;
import java.util.Map;

import org.json.JSONArray;
import org.json.JSONObject;

import common.controller.AbstractController;
import conatainer.annotation.Autowired;
import domain.MemberVO;
import domain.MemebrPurchaseChart;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import order.model.OrderDao;
import order.model.OrderDao_imple;
import product.model.ProductDao;
import product.model.ProductDao_Imple;

public class ChartByCategoryJSON extends AbstractController {

	private OrderDao orderDao;

	@Autowired
	public ChartByCategoryJSON( OrderDao orderDao) {
		this.orderDao = orderDao;
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

		List<MemebrPurchaseChart> myPurchaseChart = orderDao.memberPurchase_byCategory(loginuser.getMember_seq());

		JSONArray json_arr = new JSONArray();

		if (myPurchaseChart.size() > 0) {
			for (MemebrPurchaseChart chart : myPurchaseChart) {
				JSONObject json_obj = chart.toJson();

				json_arr.put(json_obj);
			} // end of for--------------
		}
		
		
		request.setAttribute("json", json_arr.toString());
		
	    super.setRedirect(false);
        super.setViewPage("/WEB-INF/jsonview.jsp");
	}

}
