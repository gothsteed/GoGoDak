<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%
    String ctxPath = request.getContextPath();
    //    /GoGoDak
%>
<!DOCTYPE html>
<html>
<head>
<title>고고닭</title> 

<%-- Required meta tags --%>
<meta charset="utf-8">
<meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">

<%-- Bootstrap CSS --%>
<link rel="stylesheet" type="text/css" href="<%= ctxPath%>/bootstrap-4.6.2-dist/css/bootstrap.min.css" />

<%-- Font Awesome 6 Icons --%>
<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.4.2/css/all.min.css" />

<%-- favicon --%>
<link rel="icon" type="image/png" sizes="16x16" href="<%= ctxPath%>/images/header/favicon.ico" />

<%-- 공통적으로 적용되는 CSS --%>
<link rel="stylesheet" type="text/css" href="<%= ctxPath%>/css/style.css" />

<%-- 직접 만든 CSS --%>
<style type="text/css">
	/* autocomplete */
	.search_form{
		position: relative;
	}
	.search_btn{
		position: absolute;
		right: 4%;
	}
	.autocomplete{
		position: relative;
	 	border: 3px solid #c4211c;
	 	border-radius: 25px;
	}
	.autocomplete:focus{
		outline: none;
	}
	.autocomplete-items {
		position: absolute;
	  	width: 100%;
	  	border: 1px solid #fbc02d;
	  	border-bottom: none;
	  	border-top: none;
	  	z-index: 999;
	  	top: 100%;
	  	left: 0;
	  	right: 0;
	}
	.autocomplete-items div {
		padding: 10px;
	  	cursor: pointer;
	  	background-color: #fff; 
	}
	.autocomplete-items div:hover {
	 	background-color: #fbc02d;
	 	color: #fff 
	}
	.autocomplete-active {
		background-color: #fbc02d !important; 
	  	color: #fff; 
	}
	/* carousel */
	@media (max-width: 768px){
	    .carousel-inner .carousel-item > div{
	        display: none;
	    }
	    .carousel-inner .carousel-item > div:first-child{
	        display: block;
	    }
	}
	.carousel-inner .carousel-item.active,
	.carousel-inner .carousel-item-next,
	.carousel-inner .carousel-item-prev{
	    display: flex;
	}
	/* display 3 */
	@media (min-width: 768px){
	    .carousel-inner .carousel-item-right.active,
	    .carousel-inner .carousel-item-next{
	    	transform: translateX(33.333%);
	    }
	    .carousel-inner .carousel-item-left.active, 
	    .carousel-inner .carousel-item-prev{
	    	transform: translateX(-33.333%);
	    }
	}
	.carousel-inner .carousel-item-right,
	.carousel-inner .carousel-item-left{ 
		transform: translateX(0);
	}
	/* 기본 설정 초기화 */
	li.nav-item ul{
		background: #fff;
	    border: solid #000 0px;
	   	border-radius: 0;
	    padding: 0px 0px;
	    margin-left: 0px;
	    margin-top: 0px;
	    box-shadow: 0px 0px 0px 0 #000;
	    width: 200px;
	    list-style: none;
	}
	li.nav-item ul li{
		margin: 0 0;
	}
	.new_product{
		overflow: hidden;
	}
	.new_product_img{
		transition: all 0.5s linear;
	}
	.new_product_img:hover{
	    transform: scale(1.1);
	}
	.insta_img > li:hover{
		opacity: 0.5;
		transition: 0.5s;
	}
	footer{
		position: relative;
	}
	.top_button{
	    position: absolute;
	    bottom: 30px;
	    right: 10%;
	}
	/* popup */
	.popup{
		position: fixed;
	  	bottom: 30px;
	  	left: 30px;
	  	cursor: pointer;
	  	z-index: 998;
	}
	.popup-img{
	  	width: 300px;
	    border-radius: 15px;
	}
	.popup-btn{
		position: absolute;
	  	top: 10px;
	  	right: 10px; 
	  	width: 20px;
	}
</style>

<%-- Optional JavaScript --%>
<script type="text/javascript" src="<%= ctxPath%>/js/jquery-3.7.1.min.js"></script>
<script type="text/javascript" src="<%= ctxPath%>/bootstrap-4.6.2-dist/js/bootstrap.bundle.min.js"></script> 

<%-- jQueryUI CSS 및 JS --%>
<link rel="stylesheet" type="text/css" href="<%= ctxPath%>/jquery-ui-1.13.1.custom/jquery-ui.min.css" />
<script type="text/javascript" src="<%= ctxPath%>/jquery-ui-1.13.1.custom/jquery-ui.min.js"></script>

<script type="text/javascript">
	$(document).ready(function(){
		
		$("button.search_btn").click(function(){
			goFind();
		});
		
		$("input#myInput").bind("keyup", function(e){
			if(e.keyCode == 13){
				goFind();
			}
		});
		
	}); // end of $(document).ready(function(){}) ----------
	
	function goFind(){
		
		const productName = $("input#myInput").val().trim();

		if(productName == ""){
			alert("검색어를 입력하세요!!");
			return;
		}
		
		const frm = document.productFindFrm;
        frm.action = "<%= ctxPath%>/product/productFind.dk";
        frm.method = "get";
        frm.submit();
		
	} // end of function goFind() ----------
</script>

</head>
<body class="sub_page">
	<%-- start header section --%>
	<div class="hero_area">
        <header class="header_section">
        	<div class="container">
            	<nav class="navbar navbar-expand-lg custom_nav-container row justify-content-around">
                	<div class="row col-12 col-md-8 justify-content-start">
                		<div class="col-2 col-md-4">
                			<a class="navbar-brand" href="<%= ctxPath%>/index.dk"><img src="<%= ctxPath%>/images/header/logo.png" width="150" alt="..." /></a>
                		</div>
						<form class="form-inline my-2 my-lg-0 pl-0 pr-0 col-4 align-self-center search_form" autocomplete="off" name="productFindFrm">
					    	<input class="autocomplete search_input mb-0" id="myInput" type="text" name="myFood" placeholder="Search" aria-label="Search" style="width:300px;">
						  	<button class="btn my-2 my-sm-0 search_btn" type="submit"><img src="<%= ctxPath%>/images/header/btn_search.png" width="25" alt="..." /></button>
						</form>
                	</div>
                  	<div class="collapse navbar-collapse col-8 col-md-6" id="navbarSupportedContent">
                    	<ul class="navbar-nav">
                    	
                    		<c:if test="${not empty sessionScope.loginuser}">
	                    		<li class="nav-item">
			                   		<a class="nav-link text-muted"><i class="fa-solid fa-user"></i>&nbsp;&nbsp;[ ${sessionScope.loginuser.name} ] 님 로그인 중</a>
			                   	</li>
			                   	<li class="nav-item">
			                   		<a class="nav-link text-muted" href="<%= ctxPath%>/login/logout.dk"><i class="fa-solid fa-right-from-bracket"></i>&nbsp;&nbsp;로그아웃</a>
			                   	</li>
                    		</c:if>
         
                    	    <c:if test="${empty sessionScope.loginuser}">
	                    	    <li class="nav-item">
	                           		<a class="nav-link text-secondary" href="<%= ctxPath%>/member/memberRegister.dk">회원가입</a>
	                        	</li>
                    	    </c:if>
                    	    
                    	    <c:if test="${sessionScope.loginuser.id == 'admin'}">
	                    	    <li class="nav-item">
	                           		<a class="nav-link text-secondary" href="<%= ctxPath%>/admin.dk">관리자 페이지</a>
	                        	</li>
                    	    </c:if>
                    	
           					<c:if test="${sessionScope.loginuser.id != 'admin'}">
	                        	<li class="nav-item">
	                        		<a class="nav-link text-secondary" href="<%= ctxPath%>/member/notice.dk">공지사항</a>
	                        	</li>
	                        	<li class="nav-item">
	                           		<a class="nav-link text-secondary" href="<%= ctxPath%>/member/question.dk">문의하기</a>
	                        	</li>
                        	</c:if>
                     	</ul>
                  	</div>
            	</nav>
        	</div>
		</header>
	</div> 
	<%-- end header section --%>
	
	<%-- start inner page section --%>
  	<section class="inner_page bg-white sticky-top">
  		<div class="container">
  			<nav class="navbar-expand-lg custom_nav-container row mt-3 ml-0">
		  		<ul class="navbar-nav col-md-10 h4" style="align-items: center;">
					<li class="nav-item dropdown">
						<a type="button" id="dropdownMenuButton" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
							<img class="mr-3" src="<%= ctxPath%>/images/index/icon_hamburger.png" width="30" alt="..." />
						</a>
						<div class="dropdown-menu mt-3" aria-labelledby="dropdownMenuButton">
							<ul class="navbar-nav w-100 pt-2 pb-3">
								<li class="nav-item">
									<ul>
										<li><a class="dropdown-item text-warning" href="#"><span class="h6 font-weight-bold">닭가슴살</span></a></li>
										<li><a class="dropdown-item" href="<%= ctxPath%>/product/detail.dk"><span style="font-size:12px;">소스 닭가슴살</span></a></li>
										<li><a class="dropdown-item" href="<%= ctxPath%>/product/detail.dk"><span style="font-size:12px;">크런치 닭가슴살</span></a></li>
										<li><a class="dropdown-item" href="<%= ctxPath%>/product/detail.dk"><span style="font-size:12px;">한입 닭가슴살</span></a></li>
									</ul>
								</li>
								<li class="nav-item">
									<ul>
										<li><a class="dropdown-item text-warning" href="#"><span class="h6 font-weight-bold">간편 한끼</span></a></li>
										<li><a class="dropdown-item" href="<%= ctxPath%>/product/friedRice.dk"><span style="font-size:12px;">도시락·볶음밥</span></a></li>
										<li><a class="dropdown-item" href="<%= ctxPath%>/product/bakery.dk"><span style="font-size:12px;">베이커리</span></a></li>
										<li><a class="dropdown-item" href="<%= ctxPath%>/product/dessert.dk"><span style="font-size:12px;">착한간식</span></a></li>
									</ul>
								</li>
								<li class="nav-item">
									<ul>
										<li><a class="dropdown-item text-warning" href="#"><span class="h6 font-weight-bold">마이페이지</span></a></li>
										<li><a class="dropdown-item" href="<%= ctxPath%>/member/memberEdit.dk"><span style="font-size:12px;">나의정보수정</span></a></li>
										<li><a class="dropdown-item" href="<%= ctxPath%>/member/orderList.dk"><span style="font-size:12px;">주문조회</span></a></li>
										<li><a class="dropdown-item" href="<%= ctxPath%>/member/question.dk"><span style="font-size:12px;">1:1문의</span></a></li>
										<li><a class="dropdown-item" href="<%= ctxPath%>/member/notice.dk"><span style="font-size:12px;">공지사항</span></a></li>
									</ul>
								</li>
								<li class="nav-item">
									<ul>
										<li><a class="dropdown-item text-warning" href="#"><span class="h6 font-weight-bold">브랜드몰</span></a></li>
										<li><a class="dropdown-item" href="<%= ctxPath%>/brand/delistick.dk"><span style="font-size:12px;">딜리스틱</span></a></li>
										<li><a class="dropdown-item" href="<%= ctxPath%>/brand/zeroHour.dk"><span style="font-size:12px;">제로아워</span></a></li>
										<li><a class="dropdown-item" href="<%= ctxPath%>/brand/drLiv.dk"><span style="font-size:12px;">닥터리브</span></a></li>
									</ul>
								</li>
							</ul>
						</div>
		           	</li>
					<li class="nav-item">
		        		<a class="nav-link text-danger" href="<%= ctxPath%>/product/chicken.dk">반값도시</a>
		           	</li>
		           	<li class="nav-item">
		           		<a class="nav-link" href="<%= ctxPath%>/product/chicken.dk">베스트</a>
		           	</li>
		           	<li class="nav-item">
		              	<a class="nav-link" href="<%= ctxPath%>/product/chicken.dk">1팩담기</a>
		           	</li>
		           	<li class="nav-item">
	              		<a class="nav-link" href="<%= ctxPath%>/product/chicken.dk">브랜드몰</a>
	          		</li>
		           	<li class="nav-item">
	              		<a class="nav-link" href="<%= ctxPath%>/product/event.dk">이벤트</a>
		           	</li>          	
				</ul>
				
				<ul class="nav col-md-2">
					<c:if test="${not empty sessionScope.loginuser and sessionScope.loginuser.id != 'admin'}">				
						<li class="nav-item justify-content-end">
		              		<a class="nav-link" href="<%= ctxPath%>/member.dk"><img src="<%= ctxPath%>/images/index/icon_myshop.png" width="50" alt="..." /></a>
			           	</li>
					</c:if>
					
					<c:if test="${empty sessionScope.loginuser}">				
						<li class="nav-item justify-content-end">
		              		<a class="nav-link" href="<%= ctxPath%>/login/login.dk"><img src="<%= ctxPath%>/images/index/icon_myshop.png" width="50" alt="..." /></a>
			           	</li>
					</c:if>

		           	<li class="nav-item">
	              		<a class="nav-link" href="<%= ctxPath%>/member/cart.dk"><img src="<%= ctxPath%>/images/index/icon_cart.png" width="50" alt="..." /></a>
		           	</li>
				</ul>
			</nav>
		</div>
	</section>
	<%-- end inner page section --%>