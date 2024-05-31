<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>


<%
    String ctxPath = request.getContextPath();
    //    /GoGoDak
%>
	<jsp:include page="header.jsp" />
	
	<%-- start Carousel section --%>
	<div class="container">
		<div id="carouselExampleIndicators" class="carousel slide carousel-fade mt-3 mb-5" data-ride="carousel">
			<ol class="carousel-indicators">
				<c:forEach var="event" items="${requestScope.eventList}" varStatus="status">
					<c:if test="${status.index == 0}">
						<li data-target="#carouselExampleIndicators" data-slide-to="${status.index}" class="active"></li>
					</c:if>
					<c:if test="${status.index != 0}">
						<li data-target="#carouselExampleIndicators" data-slide-to="${status.index}"></li>
					</c:if>
				</c:forEach>
			
				
<!-- 		    	<li data-target="#carouselExampleIndicators" data-slide-to="0" class="active"></li>
		    	<li data-target="#carouselExampleIndicators" data-slide-to="1"></li>
		    	<li data-target="#carouselExampleIndicators" data-slide-to="2"></li>
		    	<li data-target="#carouselExampleIndicators" data-slide-to="3"></li>
		    	<li data-target="#carouselExampleIndicators" data-slide-to="4"></li>
		    	<li data-target="#carouselExampleIndicators" data-slide-to="5"></li>
		    	<li data-target="#carouselExampleIndicators" data-slide-to="6"></li> -->
		  	</ol>
		  	<div class="carousel-inner">
		  		<c:forEach var="event" items="${requestScope.eventList}" varStatus="status">
					<c:if test="${status.index == 0}">
						<div class="carousel-item active">
				      		<img class="d-block img-fluid mx-auto rounded" src="<%= ctxPath%>/images/special/${event.pic}" alt="...">
				    	</div>   
					</c:if>
					<c:if test="${status.index != 0}">
						<div class="carousel-item">                                    
				      		<img class="d-block img-fluid mx-auto rounded" src="<%= ctxPath%>/images/special/${event.pic}" alt="...">  	      
				    	</div>  
					</c:if>
				</c:forEach>
		  	
		  	
<%-- 		    	<div class="carousel-item active">
		      		<img class="d-block img-fluid mx-auto rounded" src="<%= ctxPath%>/images/index/main_img_1.jpg" alt="...">
		    	</div>                                                         
		    	<div class="carousel-item">                                    
		      		<img class="d-block img-fluid mx-auto rounded" src="<%= ctxPath%>/images/index/main_img_2.jpg" alt="...">  	      
		    	</div>                                                         
		    	<div class="carousel-item">                                    
		      		<img class="d-block img-fluid mx-auto rounded" src="<%= ctxPath%>/images/index/main_img_3.jpg" alt="...">	      
		    	</div>                                                         
		    	<div class="carousel-item">                                    
		      		<img class="d-block img-fluid mx-auto rounded" src="<%= ctxPath%>/images/index/main_img_4.jpg" alt="...">	      
		    	</div>                                                         
		    	<div class="carousel-item">                                    
		      		<img class="d-block img-fluid mx-auto rounded" src="<%= ctxPath%>/images/index/main_img_5.jpg" alt="...">	      
		    	</div>                                                         
		    	<div class="carousel-item">                                    
		      		<img class="d-block img-fluid mx-auto rounded" src="<%= ctxPath%>/images/index/main_img_6.jpg" alt="...">	      
		    	</div>                                                         
		    	<div class="carousel-item">                                    
		      		<img class="d-block img-fluid mx-auto rounded" src="<%= ctxPath%>/images/index/main_img_7.jpg" alt="...">	      
		    	</div> --%>
		  	</div>
		  	<a class="carousel-control-prev" href="#carouselExampleIndicators" role="button" data-slide="prev">
		    	<span class="carousel-control-prev-icon" aria-hidden="true"></span>
		    	<span class="sr-only">Previous</span>
		  	</a>
		  	<a class="carousel-control-next" href="#carouselExampleIndicators" role="button" data-slide="next">
		    	<span class="carousel-control-next-icon" aria-hidden="true"></span>
		    	<span class="sr-only">Next</span>
		  	</a>
		</div>
	</div>
	<%-- end Carousel section --%>
	
	<%-- start inner page section --%>
  	<section class="inner_page">
  		<div class="container">
       		<ul class="nav justify-content-center text-center">
				<li class="nav-item">
					<a class="nav-link" href="<%= ctxPath%>/product.dk?type=chicken"><img src="<%= ctxPath%>/images/index/side_img_1.png" width="100" alt="..." /><br><span class="h4 mt-3 text-body" style="display:block;">닭가슴살</span></a>
				</li>
				<li class="nav-item">
					<a class="nav-link" href="<%= ctxPath%>/product.dk?type=fried_rice"><img src="<%= ctxPath%>/images/index/side_img_2.png" width="100" alt="..." /><br><span class="h4 mt-3 text-body" style="display:block;">도시락·볶음밥</span></a>
				</li>
				<li class="nav-item">
					<a class="nav-link" href="<%= ctxPath%>/product.dk?type=bakery"><img src="<%= ctxPath%>/images/index/side_img_3.png" width="100" alt="..." /><br><span class="h4 mt-3 text-body" style="display:block;">베이커리</span></a>
				</li>
				<li class="nav-item">
					<a class="nav-link" href="<%= ctxPath%>/product.dk?type=dessert"><img src="<%= ctxPath%>/images/index/side_img_4.png" width="100" alt="..." /><br><span class="h4 mt-3 text-body" style="display:block;">착한간식</span></a>
				</li>
			</ul>
		</div>
	</section>
	<%-- end inner page section --%>
	  
	<%-- start inner page section --%>
  	<section class="inner_page">
		<div class="container my-5">
	    	<div class="mb-3">
	    		<img src="<%= ctxPath%>/images/index/title_icon_time.png" width="50" alt="..." /><span class="h2 ml-3 align-bottom">타임특가</span>
	    	</div>
		    <div class="row mx-auto my-auto">
		        <div id="recipeCarousel" class="carousel slide w-100" data-ride="carousel">
	            	<div class="carousel-inner w-100" role="listbox">
	                	<div class="carousel-item active text-center">
	                    	<div class="col-md-4">
	                        	<div class="card"> 
		                            <a href="<%= ctxPath%>/product/detail.dk"><img class="img-fluid card-img-top" src="<%= ctxPath%>/images/index/time_sale_1.jpg" alt="..."></a>
		                            <div class="card-body">
										<h4 class="card-title text-danger font-weight-bold">[ 고고닭 알뜰마켓 ]</h4>
								    	<h5 class="card-text font-weight-bold">다시 없을 한정수량 파격 특가!</h5>
								    	<a href="#" class="h6 text-dark">790원</a>&nbsp;&nbsp;&nbsp;<del class="text-secondary">2,900원</del>
								  	</div>
		                        </div>
		                    </div>
		                </div>
		                <div class="carousel-item text-center">
	                    	<div class="col-md-4">
	                        	<div class="card"> 
		                            <a href="<%= ctxPath%>/product/detail.dk"><img class="img-fluid card-img-top" src="<%= ctxPath%>/images/index/time_sale_2.jpg" alt="..."></a>
		                            <div class="card-body">
								    	<h4 class="card-title text-danger font-weight-bold">[ 타임특가 ]</h4>
								    	<h5 class="card-text font-weight-bold">닥터리브 곤약젤리 3종</h5>
								    	<a href="#" class="h6 text-dark">20,900원</a>&nbsp;&nbsp;&nbsp;<del class="text-secondary">90,000원</del>
								  	</div>
		                        </div>
		                    </div>
		                </div>
		                <div class="carousel-item text-center">
	                    	<div class="col-md-4">
	                        	<div class="card"> 
		                            <a href="<%= ctxPath%>/product/detail.dk"><img class="img-fluid card-img-top" src="<%= ctxPath%>/images/index/time_sale_3.jpg" alt="..."></a>
		                            <div class="card-body">
								    	<h4 class="card-title text-danger font-weight-bold">[ 타임특가 ]</h4>
								    	<h5 class="card-text font-weight-bold">닭가슴살 에스닉볶음밥 5종 세트</h5>
								    	<a href="#" class="h6 text-dark">18,900원</a>&nbsp;&nbsp;&nbsp;<del class="text-secondary">37,500원</del>
								  	</div>
		                        </div>
		                    </div>
		                </div>
		                <div class="carousel-item text-center">
	                    	<div class="col-md-4">
	                        	<div class="card"> 
		                            <a href="<%= ctxPath%>/product/detail.dk"><img class="img-fluid card-img-top" src="<%= ctxPath%>/images/index/time_sale_4.jpg" alt="..."></a>
		                            <div class="card-body">
								    	<h4 class="card-title text-danger font-weight-bold">[ 타임특가 ]</h4>
								    	<h5 class="card-text font-weight-bold">닭가슴살 사각피자 2종 6팩</h5>
								    	<a href="#" class="h6 text-dark">16,900원</a>&nbsp;&nbsp;&nbsp;<del class="text-secondary">30,500원</del>
								  	</div>
		                        </div>
		                    </div>
		                </div>
                        <div class="carousel-item text-center">
	                    	<div class="col-md-4">
	                        	<div class="card"> 
		                            <a href="<%= ctxPath%>/product/detail.dk"><img class="img-fluid card-img-top" src="<%= ctxPath%>/images/index/time_sale_5.jpg" alt="..."></a>
		                            <div class="card-body">
						    	    	<h4 class="card-title text-danger font-weight-bold">[ 주말한정특가 ]</h4>
								    	<h5 class="card-text font-weight-bold">딜리스틱 패키지 (총 10팩)</h5>
								    	<a href="#" class="h6 text-dark">48,000원</a>
								  	</div>
		                        </div>
		                    </div>
		            	</div> 
		            </div>
		            <a class="carousel-control-prev w-auto" href="#recipeCarousel" role="button" data-slide="prev">
		                <span class="carousel-control-prev-icon bg-dark border border-dark rounded-circle" aria-hidden="true"></span>
		                <span class="sr-only">Previous</span>
		            </a>
		            <a class="carousel-control-next w-auto" href="#recipeCarousel" role="button" data-slide="next">
		                <span class="carousel-control-next-icon bg-dark border border-dark rounded-circle" aria-hidden="true"></span>
		                <span class="sr-only">Next</span>
		            </a>
		        </div>
		    </div>
		</div>
	</section> 
	<%-- end inner page section --%>
	
	<%-- start outer page section --%>
  	<section>
		<div class="text-center">
	  		<img class="img-fluid" src="<%= ctxPath%>/images/index/center_banner.jpg" alt="...">
		</div>
	</section>
	<%-- end outer page section --%>
	
	<%-- start inner page section --%>
  	<section class="inner_page">
		<div class="container my-5">
			<div class="mb-3">
	    		<img src="<%= ctxPath%>/images/index/title_icon_present.png" width="50" alt="..." /><span class="h2 ml-3 align-bottom">NEW 이달의 신제품</span>
	    	</div>
			<div class="card-deck mb-5">
				<div class="card">
					<div class="new_product">
						<a href="<%= ctxPath%>/product/detail.dk"><img class="card-img-top new_product_img" src="<%= ctxPath%>/images/index/new_product_1.jpg" alt="..."></a>
					</div>
					<div class="card-body">
						<div class="row justify-content-around">
							<div class="col-7 h5 text-left font-weight-bold">딜리스틱</div>
						    <div class="col-5 col-lg-5">
						    	<span class="fa fa-star checked text-warning"></span>
								<span class="fa fa-star checked text-warning"></span>
								<span class="fa fa-star checked text-warning"></span>
								<span class="fa fa-star checked text-warning"></span>
								<span class="fa fa-star"></span>
						    </div>
						</div>   
						<br>
						<p class="card-text text-left text-secondary">쫄깃한 닭다리살에 소스가 듬뿍!</p>
					</div>
				</div>
				<div class="card">
					<div class="new_product">
						<a href="<%= ctxPath%>/product/detail.dk"><img class="card-img-top new_product_img" src="<%= ctxPath%>/images/index/new_product_2.jpg" alt="..."></a>
					</div>
					<div class="card-body">
						<div class="row justify-content-around">
							<div class="col-7 h5 text-left font-weight-bold">고고닭X강강술래</div>
						    <div class="col-5 col-lg-5">
						    	<span class="fa fa-star checked text-warning"></span>
								<span class="fa fa-star checked text-warning"></span>
								<span class="fa fa-star checked text-warning"></span>
								<span class="fa fa-star"></span>
								<span class="fa fa-star"></span>
						    </div>
					    </div> 
					    <br>
						<p class="card-text text-left text-secondary">갈비 명가의 노하우가 그대로!</p>
					</div>
				</div>
				<div class="card">
					<div class="new_product">
						<a href="<%= ctxPath%>/product/detail.dk"><img class="card-img-top new_product_img" src="<%= ctxPath%>/images/index/new_product_3.jpg" alt="..."></a>
					</div>
					<div class="card-body">
						<div class="row justify-content-around">
							<div class="col-7 h5 font-weight-bold">현미떡마리</div>
						    <div class="col-5 col-lg-5">
						    	<span class="fa fa-star checked text-warning"></span>
								<span class="fa fa-star checked text-warning"></span>
								<span class="fa fa-star checked text-warning"></span>
								<span class="fa fa-star checked text-warning"></span>
								<span class="fa fa-star checked text-warning"></span>
						    </div>
					    </div>   
					    <br> 
						<p class="card-text text-secondary">쫄깃한 떡 속에 토핑이 가~득!</p>
					</div>
				</div>
			</div>
		</div>
	</section> 
	<%-- end inner page section --%>
	
	<%-- start inner page section --%>
  	<section class="inner_page">
		<div class="container my-5 border border-right-0 border-bottom-0 border-left-0">
			<div class="row mt-5">
			    <div class="row col">
			    	<div class="col-4">
			    		<a href="<%= ctxPath%>/product/detail.dk"><img class="rounded" src="<%= ctxPath%>/images/index/sale_product_1.jpg" width="120" alt="..."></a>
			    	</div>
			    	<div class="col-7 offset-md-1 mt-1">
			    		<h6>딜리스틱 소스 닭다리살 양념치킨</h6>
			    		<p class="h5 text-dark">14,000원</p><del class="h6 text-secondary">24,500원</del>
			    	</div>
			    </div>
			    <div class="row col">
			    	<div class="col-4">
			    		<a href="<%= ctxPath%>/product/detail.dk"><img class="rounded" src="<%= ctxPath%>/images/index/sale_product_2.jpg" width="120" alt="..."></a>
			    	</div>
			    	<div class="col-7 offset-md-1 mt-1">
			    		<h6>소스 품은 닭가슴살 매콤 숯불구이</h6>
			    		<p class="h5 text-dark">15,800원</p><del class="h6 text-secondary">23,500원</del>
			    	</div>
			    </div>
			    <div class="row col">
			    	<div class="col-4">
			    		<a href="<%= ctxPath%>/product/detail.dk"><img class="rounded" src="<%= ctxPath%>/images/index/sale_product_3.jpg" width="120" alt="..."></a>
			    	</div>
			    	<div class="col-7 offset-md-1 mt-1">
			    		<h6>현미 떡마리 치즈 불고기 30팩</h6>
			    		<p class="h5 text-dark">10,000원</p><del class="h6 text-secondary">16,500원</del>
			    	</div>
			    </div>
			    <div class="w-100 mt-2 mb-2"></div>
			    <div class="row col">
			    	<div class="col-4">
			    		<a href="<%= ctxPath%>/product/detail.dk"><img class="rounded" src="<%= ctxPath%>/images/index/sale_product_4.jpg" width="120" alt="..."></a>
			    	</div>
			    	<div class="col-7 offset-md-1 mt-1">
			    		<h6>딜리스틱 크런치 닭다리살 케이준치킨</h6>
			    		<p class="h5 text-dark">14,000원</p><del class="h6 text-secondary">24,500원</del>
			    	</div>
			    </div>
			    <div class="row col">
			    	<div class="col-4">
			    		<a href="<%= ctxPath%>/product/detail.dk"><img class="rounded" src="<%= ctxPath%>/images/index/sale_product_5.jpg" width="120" alt="..."></a>
			    	</div>
			    	<div class="col-7 offset-md-1 mt-1">
			    		<h6>소스 품은 닭가슴살 갈비 숯불구이</h6>
			    		<p class="h5 text-dark">15,800원</p><del class="h6 text-secondary">23,500원</del>
			    	</div>
			    </div>
			    <div class="row col">
			    	<div class="col-4">
			    		<a href="<%= ctxPath%>/product/detail.dk"><img class="rounded" src="<%= ctxPath%>/images/index/sale_product_6.jpg" width="120" alt="..."></a>
			    	</div>
			    	<div class="col-7 offset-md-1 mt-1">
			    		<h6>현미 떡마리 더블 치즈 30팩</h6>
			    		<p class="h5 text-dark">10,000원</p><del class="h6 text-secondary">16,500원</del>
			    	</div>
			    </div>
			</div>
		</div>
	</section>
	<%-- end inner page section --%>
	
	<%-- start outer page section --%>
  	<section class="pt-5 pb-5" style="background-color: #fff5bd;">
  		<div class="row justify-content-center">
		    <div class="col-md-4 mr-1">
				<iframe width="560" height="315" src="https://www.youtube.com/embed/6_oQCbpTNHk?si=pXz92F3EolNOQNwN" title="YouTube video player" frameborder="0"></iframe>
		    </div>
		    <div class="col-md-2">
		    	<img class="rounded" src="<%= ctxPath%>/images/index/event_img.jpg" height="315" alt="...">
		    </div>
		</div>
	</section>
	<%-- end outer page section --%>
	
	<%-- start inner page section --%>
  	<section class="inner_page">
  		<div class="container my-5">
  			<div class="mb-3 row">
	    		<span class="col-10 h2">고고닭 인스타그램
	    			<span class="h6 text-secondary">@gogodak_official&nbsp;&nbsp;#고고닭&nbsp;&nbsp;#소품닭&nbsp;&nbsp;#스테이크&nbsp;&nbsp;#큐브&nbsp;&nbsp;#크런치</span>
	    		</span>
	    		<a class="col-2 font-weight-bold text-right text-secondary" href="https://www.instagram.com/barudak_official/" target="_blank" style="align-content: center;"><i class="fa-solid fa-chevron-right"></i>&nbsp;&nbsp;더보기</a>
	    	</div>
	    	
	    	<ul class="nav justify-content-center insta_img">
				<li class="nav-item">
					<a class="nav-link pl-1 pr-1" href="https://www.instagram.com/p/C1B0Rp9r4Zd/" target="_blank"><img class="rounded" src="<%= ctxPath%>/images/index/instagram_img_1.jpg" width="175" alt="..."></a>
				</li>
				<li class="nav-item">
					<a class="nav-link pl-1 pr-1" href="https://www.instagram.com/p/CzYmUWLvM9T/" target="_blank"><img class="rounded" src="<%= ctxPath%>/images/index/instagram_img_2.jpg" width="175" alt="..."></a>
				</li>
				<li class="nav-item">
					<a class="nav-link pl-1 pr-1" href="https://www.instagram.com/p/CpUoTxWLanz/" target="_blank"><img class="rounded" src="<%= ctxPath%>/images/index/instagram_img_3.jpg" width="175" alt="..."></a>
				</li>
				<li class="nav-item">
					<a class="nav-link pl-1 pr-1" href="https://www.instagram.com/p/CuyweCerkFu/" target="_blank"><img class="rounded" src="<%= ctxPath%>/images/index/instagram_img_4.jpg" width="175" alt="..."></a>
				</li>
				<li class="nav-item">
					<a class="nav-link pl-1 pr-1" href="https://www.instagram.com/p/Czvjg_NrNKf/" target="_blank"><img class="rounded" src="<%= ctxPath%>/images/index/instagram_img_5.jpg" width="175" alt="..."></a>
				</li>
				<li class="nav-item">
					<a class="nav-link pl-1 pr-1" href="https://www.instagram.com/p/Cyu_RbhrUDd/%20/" target="_blank"><img class="rounded" src="<%= ctxPath%>/images/index/instagram_img_6.jpg" width="175" alt="..."></a>
				</li>
			</ul>
	    	
    		<ul class="nav justify-content-center insta_img">
				<li class="nav-item">
					<a class="nav-link pl-1 pr-1 pt-0" href="https://www.instagram.com/p/Cz5ynYMry5b/" target="_blank"><img class="rounded" src="<%= ctxPath%>/images/index/instagram_img_7.jpg" width="175" alt="..."></a>
				</li>
				<li class="nav-item">
					<a class="nav-link pl-1 pr-1 pt-0" href="https://www.instagram.com/p/Cv_7QoTL0Qk/" target="_blank"><img class="rounded" src="<%= ctxPath%>/images/index/instagram_img_8.jpg" width="175" alt="..."></a>
				</li>
				<li class="nav-item">
					<a class="nav-link pl-1 pr-1 pt-0" href="https://www.instagram.com/p/CuWeo8NrMt6/" target="_blank"><img class="rounded" src="<%= ctxPath%>/images/index/instagram_img_9.jpg" width="175" alt="..."></a>
				</li>
				<li class="nav-item">
					<a class="nav-link pl-1 pr-1 pt-0" href="https://www.instagram.com/p/C0_KWMSrZ0u/" target="_blank"><img class="rounded" src="<%= ctxPath%>/images/index/instagram_img_10.jpg" width="175" alt="..."></a>
				</li>
				<li class="nav-item">
					<a class="nav-link pl-1 pr-1 pt-0" href="https://www.instagram.com/p/Cvl6N5YrowE/" target="_blank"><img class="rounded" src="<%= ctxPath%>/images/index/instagram_img_11.jpg" width="175" alt="..."></a>
				</li>
				<li class="nav-item">
					<a class="nav-link pl-1 pr-1 pt-0" href="https://www.instagram.com/p/C0Q8dtvr9sw/" target="_blank"><img class="rounded" src="<%= ctxPath%>/images/index/instagram_img_12.jpg" width="175" alt="..."></a>
				</li>
			</ul>
 		</div>
	</section>
	<%-- end inner page section --%>
	
    <%-- start popup section --%>
    <div class="popup clearfix">
    	<img class="popup-img" src="<%= ctxPath%>/images/footer/pop_coupon.jpg" alt="popup" />
      	<img class="popup-btn" src="<%= ctxPath%>/images/footer/pop_close.png" alt="popup_close" />
    </div>
    <%-- end popup section --%>
	
	<jsp:include page="footer.jsp" />