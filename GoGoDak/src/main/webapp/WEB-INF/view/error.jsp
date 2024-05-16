<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
    String ctxPath = request.getContextPath();
    //    /GoGoDak
%>     
	<jsp:include page="header.jsp" />
	
	<div class="container mt-5 mb-5">
    	<div class="text-center">
    		<img src="<%= ctxPath%>/images/index/error.gif" class="img-fluid" />
        </div>
        <p class="h3 text-center text-warning mt-5"><i class="fa-solid fa-quote-left"></i>&nbsp;&nbsp;신속한 복구를 위해 최선을 다하겠습니다. 서비스 이용에 불편을 드려 죄송합니다.&nbsp;&nbsp;<i class="fa-solid fa-quote-right"></i></p>
	</div>

	<jsp:include page="footer.jsp" />