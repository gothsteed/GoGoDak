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
<link rel="stylesheet" type="text/css" href="<%= ctxPath%>/bootstrap-4.6.2-dist/css/bootstrap.min.css" >

<%-- Font Awesome 6 Icons --%>
<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.4.2/css/all.min.css">

<%-- favicon --%>
<link rel="icon" type="image/png" sizes="16x16" href="images/favicon.ico">

<%-- 직접 만든 CSS --%>
<link rel="stylesheet" type="text/css" href="<%= ctxPath%>/css/style.css" />
<style type="text/css">

</style>

<%-- Optional JavaScript --%>
<script type="text/javascript" src="<%= ctxPath%>/js/jquery-3.7.1.min.js"></script>
<script type="text/javascript" src="<%= ctxPath%>/bootstrap-4.6.2-dist/js/bootstrap.bundle.min.js" ></script> 
</head>
<body>
	<%-- start header section --%>
	
        <header class="header_section">
        	<div class="container">
            	<nav class="navbar navbar-expand-lg custom_nav-container row justify-content-around ">
                	<div class="row col-12 col-md-8 justify-content-start">
                		<div class="col-4">
                			<a class="navbar-brand" href="#"><img src="<%= ctxPath%>images/logo.png" width="150" alt="#" /></a>
                		</div>
                	</div>
                  	<div class="collapse navbar-collapse col-6 col-md-4" id="navbarSupportedContent">
                    	<ul class="navbar-nav">
                    		<li class="nav-item pt-2">
                           		<i class="fa-solid fa-user"></i>
                        	</li>
                        	<li class="nav-item">
                           		<a class="nav-link text-muted" href="#">[ 관리자 ] 님 로그인 중</a>
                        	</li>
                        	<li class="nav-item pt-2">
                           		<i class="fa-solid fa-right-from-bracket"></i>
                        	</li>
                        	<li class="nav-item">
                           		<a class="nav-link text-muted" href="#">로그아웃</a>
                        	</li>
                     	</ul>
                  	</div>
            	</nav>
        	</div>
		</header>
	
	<%-- end header section --%>

	<div class="container">
		<div class="card-group mb-5">
			<div class="card">
				<img src="<%= ctxPath%>images/index_admin/main_admin_1.png" class="card-img-top" alt="...">
				<div class="card-body">
					<h5 class="card-title">회원정보보기</h5>
					<p class="card-text"></p>
					<a href="#" class="btn btn-outline-warning">View Details &raquo;</a>
				</div>
			</div>
			
			<div class="card">
				<img src="<%= ctxPath%>images/index_admin/main_admin_2.png" class="card-img-top" alt="...">
				<div class="card-body">
					<h5 class="card-title">상품등록</h5>
					<p class="card-text"></p>
					<a href="#" class="btn btn-outline-warning">View Details &raquo;</a>
				</div>
			</div>
			<div class="card">
				<img src="<%= ctxPath%>images/index_admin/main_admin_3.png" class="card-img-top" alt="...">
				<div class="card-body">
					<h5 class="card-title">주문확인</h5>
					<p class="card-text"></p>
					<a href="#" class="btn btn-outline-warning">View Details &raquo;</a>
				</div>
			</div>
			<div class="card">
				<img src="<%= ctxPath%>images/index_admin/main_admin_4.png" class="card-img-top" alt="...">
				<div class="card-body">
					<h5 class="card-title">공지사항등록</h5>
					<p class="card-text"></p>
					<a href="#" class="btn btn-outline-warning">View Details &raquo;</a>
				</div>
			</div>
		</div>
	</div>	
</body>
</html>