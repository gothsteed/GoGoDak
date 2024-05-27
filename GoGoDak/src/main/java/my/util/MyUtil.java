package my.util;

import jakarta.servlet.http.HttpServletRequest;

public class MyUtil {
	
	// *** ? 다음의 데이터까지 포함한 현재 URL 주소를 알려주는 메소드를 생성 *** //
	public static String getCurrentURL(HttpServletRequest request) {
		   
		String currentURL = request.getRequestURL().toString(); // 현재 URL 값을 알아오는 것
//		System.out.println("currentURL : "+ currentURL);
//		currentURL : http://localhost:9090/MyMVC/member/memberList.up
		   
		String queryString = request.getQueryString();
//		System.out.println(queryString);
		   
		if(queryString != null) { // GET 방식일 경우
			currentURL += "?" + queryString;
		}
		   
		String ctxPath = request.getContextPath();
		   
		int beginIndex = currentURL.indexOf(ctxPath) + ctxPath.length();
		currentURL = currentURL.substring(beginIndex);
//		System.out.println(currentURL);
		
		return currentURL;
	}
}
