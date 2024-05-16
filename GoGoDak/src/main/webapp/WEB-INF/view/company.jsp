<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
    String ctxPath = request.getContextPath();
    //    /GoGoDak
%>     
	<jsp:include page="header.jsp" />
	
	<style type="text/css">
		.company{
			position: relative;
		}
		.gogodak_img{
			position: absolute;
	    	bottom: -10%;
	    	right: 3%;
		}
	</style>
	
	<div class="container mt-5 mb-5 company">
    	<h2 class="text-center" style="font-family: 'Noto Sans KR', sans-serif;"><i class="fa-solid fa-quote-left"></i>&nbsp;&nbsp;고고닭 소개&nbsp;&nbsp;<i class="fa-solid fa-quote-right"></i></h2>
		<table class="table table-striped table-bordered mt-5"> 
			<tbody>
				<tr>
					<th>상점명</th>
					<td>고고닭</td>
				</tr>
				<tr>
					<th>대표이사</th>
					<td>서영학</td>
				</tr>
				<tr>
					<th>대표전화</th>
					<td>02-336-8546~8</td>
				</tr>
				<tr>
					<th>주소</th>
					<td>서울특별시 마포구 월드컵북로 21 풍성빌딩 2,3,4층</td>
				</tr>
				<tr>
					<th>사업자등록번호</th>
					<td>301-87-00296</td>
				</tr>
				<tr>
					<th>통신판매업신고</th>
					<td>2017-서울강남-02608</td>
				</tr>
				<tr>
					<th>호스팅제공</th>
					<td>카페24(주)</td>
				</tr>
			</tbody>
		</table>
		<div class="gogodak_img"><img src="<%= ctxPath%>/images/index/gogodak.png" width="200" alt="..." /></div>
	</div>

	<jsp:include page="footer.jsp" />