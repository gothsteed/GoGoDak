<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
    String ctxPath = request.getContextPath();
    //    /GoGoDak
%>
<jsp:include page="header_admin.jsp" />

	<%-- start inner page section --%>
	<section class="inner_page mt-5">
		<div class="container">
			<div class="card-group mb-5">
				<div class="card">
					<img src="<%= ctxPath%>/images/index_admin/main_admin_1.png" class="card-img-top" alt="...">
					<div class="card-body">
						<h5 class="card-title">회원정보보기</h5>
						<p class="card-text"></p>
						<a href="<%= ctxPath%>/admin/member.dk" class="btn btn-outline-warning">View Details &raquo;</a>
					</div>
				</div>
				<div class="card">
					<img src="<%= ctxPath%>/images/index_admin/main_admin_2.png" class="card-img-top" alt="...">
					<div class="card-body">
						<h5 class="card-title">상품등록</h5>
						<p class="card-text"></p>
						<a href="<%= ctxPath%>/admin/product.dk" class="btn btn-outline-warning">View Details &raquo;</a>
					</div>
				</div>
				<div class="card">
					<img src="<%= ctxPath%>/images/index_admin/main_admin_3.png" class="card-img-top" alt="...">
					<div class="card-body">
						<h5 class="card-title">주문확인</h5>
						<p class="card-text"></p>
						<a href="<%= ctxPath%>/admin/order.dk" class="btn btn-outline-warning">View Details &raquo;</a>
					</div>
				</div>
				<div class="card">
					<img src="<%= ctxPath%>/images/index_admin/main_admin_4.png" class="card-img-top" alt="...">
					<div class="card-body">
						<h5 class="card-title">공지사항등록</h5>
						<p class="card-text"></p>
						<a href="<%= ctxPath%>/member/notice.dk" class="btn btn-outline-warning">View Details &raquo;</a>
					</div>
				</div>
			</div>
		</div>	
	</section>
	<%-- end inner page section --%>
</body>
</html>