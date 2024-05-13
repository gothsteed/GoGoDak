<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
    String ctxPath = request.getContextPath();
    //    /Ssangdak
%>
<!DOCTYPE html>
<html>
<head>
<title>고고닭</title> 

<%-- Required meta tags --%>
<meta charset="utf-8">
<meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">

<%-- Bootstrap CSS --%>
<link rel="stylesheet" type="text/css" href="<%= ctxPath%>/bootstrap-4.6.2-dist/css/bootstrap.min.css" >

<%-- Font Awesome 6 Icons --%>
<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.4.2/css/all.min.css">

<%-- favicon --%>
<link rel="icon" type="image/png" sizes="16x16" href="images/header/favicon.ico">

<%-- 직접 만든 CSS --%>
<link rel="stylesheet" type="text/css" href="<%= ctxPath%>/css/style.css" />
<style type="text/css">
	.search_input{
		border: 3px solid #c4211c;
		border-radius: 24px;
		position: relative;
	}
	.search_btn{
		position: absolute;
		right: 4%;
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
	.insta_img > li:hover{
		opacity: 0.5;
		transition: 0.5s;
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
	/* popup */
	.popup{
		position: fixed;
	  	bottom: 30px;
	  	left: 30px;
	  	cursor: pointer;
	  	z-index: 999;
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
<script type="text/javascript" src="<%= ctxPath%>/bootstrap-4.6.2-dist/js/bootstrap.bundle.min.js" ></script> 
</head>
<body class="sub_page">
	<%-- start header section --%>
	<div class="hero_area">
        <header class="header_section">
        	<div class="container">
            	<nav class="navbar navbar-expand-lg custom_nav-container row justify-content-around">
                	<div class="row col-12 col-md-8 justify-content-start">
                		<div class="col-4">
                			<a class="navbar-brand" href="#"><img src="images/header/logo.png" width="150" alt="#" /></a>
                		</div>
                		<form class="form-inline my-2 my-lg-0 col-4 align-self-center">
							<input class="form-control mr-sm-2 search_input mb-0" type="search" placeholder="Search" aria-label="Search">
							<button class="btn my-2 my-sm-0 search_btn" type="submit"><img src="images/header/btn_search.png" width="25" alt="#" /></button>
						</form>
                	</div>
                  	<div class="collapse navbar-collapse col-6 col-md-4" id="navbarSupportedContent">
                    	<ul class="navbar-nav">
                        	<li class="nav-item">
                           		<a class="nav-link text-muted" href="#">회원가입</a>
                        	</li>
                        	<li class="nav-item">
                        		<a class="nav-link text-muted" href="#">공지사항</a>
                        	</li>
                        	<li class="nav-item">
                           		<a class="nav-link text-muted" href="#">고객센터</a>
                        	</li>
                     	</ul>
                  	</div>
            	</nav>
        	</div>
		</header>
	</div> 
	<%-- end header section --%>