<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%
    String ctxPath = request.getContextPath();
    //    /MyMVC
%> 
<%@ taglib prefix="c"   uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn"  uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>  
    
<jsp:include page="../header.jsp" /> 

<style type="text/css">

  table.table-bordered > tbody > tr > td:nth-child(1) {
  	 width: 25%;
  	 font-weight: bold;
  	 text-align: right;
  }

</style>

<div class="container" >
		<h2 class="text-center ">회원 정보 수정</h2>
		<br>
		<br>
		
		
		<div class="row" id="semicontainer" >
			
			<div class="col w-50" style="display: flex;">
				<div class="myphoto">
					<img src="<%= ctxPath%>/images/myshop/my_shop_달걀.jpg" class="img-egg" />
				</div>
				
				<div>
					<p class="h4"> ${sessionScope.loginuser.name} 님</p>
					<p> 달걀 / ${sessionScope.loginuser.point} 원 적립</p>
					<p> 저희 쇼핑몰을 이용해주셔서 감사합니다. ${sessionScope.loginuser.name} 님은  </p>
				</div>
				<div>
					
				</div>
				
			</div>
			