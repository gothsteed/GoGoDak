<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
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
<link rel="stylesheet" type="text/css" href="<%= ctxPath%>/css/main/style.css" />

<%-- 직접 만든 CSS --%>
<style type="text/css">

</style>

<%-- Optional JavaScript --%>
<script type="text/javascript" src="<%= ctxPath%>/js/jquery-3.7.1.min.js"></script>
<script type="text/javascript" src="<%= ctxPath%>/bootstrap-4.6.2-dist/js/bootstrap.bundle.min.js"></script> 

</head>
<body class="sub_page">
	<%-- start header section --%>
	<div class="hero_area">
		<header class="header_section">
	    	<div class="container">
	 			<nav class="navbar navbar-expand-lg custom_nav-container">
					<div>
						<a class="navbar-brand" href="<%= ctxPath%>/index.dk"><img src="<%= ctxPath%>/images/header/logo.png" width="150" alt="..." /></a>
					</div>
					<div class="collapse navbar-collapse justify-content-end" id="navbarSupportedContent">
						<ul class="navbar-nav">
		                   	<li class="nav-item">
		                   		<a class="nav-link text-muted" href="#"><i class="fa-solid fa-user"></i>&nbsp;&nbsp;[ ${sessionScope.loginuser.name} ] 님 로그인 중</a>
		                   	</li>
		                   	<li class="nav-item">
		                   		<a class="nav-link text-muted" href="<%= ctxPath%>/login/logout.dk"><i class="fa-solid fa-right-from-bracket"></i>&nbsp;&nbsp;로그아웃</a>
		                   	</li>
		               	</ul>
					</div>
				</nav>
		 	</div>
		</header>
	</div>
	<%-- end header section --%>