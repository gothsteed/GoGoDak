<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
    String ctxPath = request.getContextPath();
    //    /GoGoDak
%> 

<jsp:include page="../header.jsp" />


<style type="text/css">
	.flex-row {
		display: flex;
		flex-direction: row;
	}
	.flex-col {
		display: flex;
		flex-direction: col;
	}
	.container {
		border: solid 0px red;
	    float: none;
	    width: 100%;
	    margin: 64px auto 0px;
	}
	.text-center{
	    color: #1a1a1a;
	    font-size: 32px;
	    font-weight: 700;
	    text-align: left;
	    line-height: 40px;
	}
	li a{
		color: black;
	}
	.Mypiont{
		overflow: hidden;
	    padding: 20px 0px 20px 0px;
	    margin: 20px auto 0 auto;
	    border: 1px solid #e3e3e3;
	    border-top: 2px solid #1a1a1a;
	    background: #fff;
	}
	.myphoto{
		border: solid 0px red;
		width: 25%;
	}
	#semicontainer{
		width:100%;
		padding:40px 10px 20px 10px ; 
		border-top:2px solid black;
		border-left: 1px solid gray;
		border-right: 1px solid gray;
		border-bottom: 1px solid gray;
	}
	#semicontainer2{
		width:100%;
		border-top:2px solid black;
		border-left: 1px solid gray;
		border-right: 1px solid gray;
		border-bottom: 1px solid gray;	
	}
	.img-egg{
		width: 90%;
		height: 90%
	}
	.order li {
	 	list-style: none;
		display:block;
	    float: left;
	    width: 55%;
	    padding: 0 0 4px;
	    margin: 0 auto;
	    border-right: 1px dotted #c9c7ca;
	    text-align: center;
	}
	.icons{
		border: solid 0px red;
		display: flex;
	}
	.icons > li {
		margin: 50px 0px 20px 0px;
		border: 1px solid #e9e9e9;
	    padding: 40px 15px;
		text-align:center;
		list-style: none;
		width: 220px;
		height: 265px;
		flex: 1;
		color: black;
	}
	.icons > li:not(:last-child){
		margin-right: 25%;
	}
	.img-fluid{
		width: 80px;
		height: 80px;
	}
</style>

	<div class="container">
		<h2 class="text-center ">마이 쇼핑</h2>
		<br>
		<br>
		
		
		<div class="row ml-0" id="semicontainer" >
			
			<div class="col w-50" style="display: flex;">
				<div class="myphoto">
					<img src="<%= ctxPath%>/images/myshop/my_shop_달걀.jpg" class="img-egg" />
				</div>
				
				<div>
					<p class="h4"> ${sessionScope.loginuser.name} 님</p>
					<p> 달걀 / ${sessionScope.loginuser.point} 원 적립</p>
					<p style="color:gold">1원 더 구매시 삐약이로 레벨업!</p>
				</div>
				<div>
					
				</div>
				
			</div>
			
			
			<div class="col w-50">
				<ul class="navbar-nav">
					
			        <li class="nav-item row">
			            <strong class="title col-5">총적립금</strong>
			            <strong class="data col-7 text-right"><span id="total_mileage"> ${sessionScope.loginuser.point}원</span></strong>
			        </li>
			       
			        
			        <li class="nav-item row">
			            <strong class="title col-5">총주문</strong>
			            <strong class="data col-7 text-right"><span id="order_price"> 원</span>(<span id="order_count">&</span>회)</strong>
			        </li>
			        
			    </ul>
			</div> 
		</div>  
		
		 
    	<br><br><br>
    	
    	
    	
    	<div id="semicontainer2">
    		<div style="padding:10px 10px 0px 10px; border-bottom:solid 1px black; background-color: #f6f6f6;">
    			<p style="font-size: 14px" >나의 주문현황</p>
    		</div>
 		<div class="liblock">
	        <ul class="order flex-col" style="margin-top: 15px; ">
				<li>
	                <strong style="font-size: 18px">출고전 <br><br></strong>
	                <a href="#" class="count"><span id="before_count">0</span></a>
	            </li>
	            <li>
	                <strong style="font-size: 18px">출고완료 <br><br></strong>
	                <a href="#" class="count"><span id="standby_count">0</span></a>
	            </li>
	            <li>
	                <strong style="font-size: 18px">배송중 <br><br></strong>
	                <a href="#" class="count"><span id="begin_count">0</span></a>
	            </li>
	            <li>
	                <strong style="font-size: 18px">배송완료 <br><br></strong>
	                <a href="#" class="count"><span id="shppied_complate_count" >0</span></a>
	            </li>
	        </ul>
		</div>
    	</div>
    
    	<br><br>
    
    	<div class="icons mt-5 row justify-content-around" style="text-align: center;">
   			<div class="shopMain order border col-3" style="padding: 2% 0; cursor: pointer;">
    			<a href="<%= ctxPath%>/member/orderList.dk" class="text-muted">
    				<div class="h-50">
	   					<img src="<%= ctxPath%>/images/myshop/order.png" class="img-fluid" />
	   				</div>
		            <h3 style="font-size: 22px; font-weight: bold;" class="text-warning">Order</h3>
		            <span style="font-size: 16px; font-weight: bold; display: block; margin: 4% 0">주문내역 조회</span>
		            <p style="font-size: 14px;">고객님께서 주문하신 상품의<br>주문내역을 확인하실 수 있습니다.</p>
	            </a>
       		</div>
       		<div class="shopMain profile border col-3" style="padding: 2% 0;">
       			<a href="<%= ctxPath%>/member/memberEdit.dk" class="text-muted">
       				<div class="h-50">
	       				<img src="<%= ctxPath%>/images/myshop/profile.png" class="img-fluid" />
	       			</div>
		            <h3 style="font-size: 22px; font-weight: bold;" class="text-warning">Profile</h3>
		            <span style="font-size: 16px; font-weight: bold; display: block; margin: 4% 0">회원 정보</span>
		            <p style="font-size: 14px;">회원이신 고객님의 개인정보를<br>수정하는 공간입니다.</p>
	            </a>
       		</div>
       		<div class="shopMain statistics border col-3" style="padding: 2% 0;">
       			<a href="<%= ctxPath%>/member/chart.dk" class="text-muted">
       				<div class="h-50">
	       				<img src="<%= ctxPath%>/images/myshop/chart.png" class="img-fluid" />
	       			</div>
		            <h3 style="font-size: 22px; font-weight: bold;" class="text-warning">Chart</h3>
		            <span style="font-size: 16px; font-weight: bold; display: block; margin: 4% 0">나의 주문 통계</span>
		            <p style="font-size: 14px;">고객님께서 구매하신 상품별<br>주문 통계를 보여드립니다.</p>
	            </a>
       		</div>
    	</div>
    </div>
    
    <br><br>
	
	<%-- 회원탈퇴하기 --%>
	<div class="container my-5" align="right">
		<form name="deleteFrm">
			<input type="hidden" name="id" value="${sessionScope.loginuser.id}" />
   			<button type="button" class="btn btn-outline-secondary" onclick="goDel()">회원탈퇴</button>
		</form>
   	</div>
   	
   	<script type="text/javascript">
   		function goDel() {
   			const frm = document.deleteFrm;
			frm.action = "<%= ctxPath%>/member/memberWithdrawal.dk";
			frm.method = "post";
			frm.submit();
		}
   	</script>

<jsp:include page="../footer.jsp" />