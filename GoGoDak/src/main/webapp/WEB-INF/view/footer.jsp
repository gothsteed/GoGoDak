<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
	<%-- start footer section --%>
	<footer class="footer_section pt-5 pb-5">
    	<div class="container">
       		<div class="row">
            	<div class="col-md-4 footer-col">
               		<div class="footer_contact">
                   		<div class="col-4">
                			<a class="navbar-brand" href="#"><img src="images/header/logo.png" width="150" alt="#" /></a>
                		</div>
                     	<div class="contact_link_box mt-3">
                        	<a href="">
	                        	<i class="fa fa-map-marker" aria-hidden="true"></i>
	                        	<span class="">
	                        		Location
	                        	</span>
                        	</a>
                        	<a href="">
                        		<i class="fa fa-phone" aria-hidden="true"></i>
		                        <span>Call +01 1234567890</span>
                        	</a>
	                        <a href="">
	                        	<i class="fa fa-envelope" aria-hidden="true"></i>
		                        <span>
		                        	gogodak@gmail.com
		                        </span>
	                        </a>
                     	</div>
                  	</div>
               </div>
               <div class="col-md-8 footer-col">
			   		<div class="footer_detail">
               			<a href="#" class="footer-logo">1566-3197</a>
                     	<ul class="nav footer">
							<li class="nav-item">
								<a class="nav-link text-white pl-0" href="#">고고닭소개</a>
							</li>
							<li class="nav-item">
								<a class="nav-link text-white" href="#">공지사항</a>
							</li>
							<li class="nav-item">
								<a class="nav-link text-white" href="#">이용약관</a>
							</li>
							<li class="nav-item">
								<a class="nav-link text-white" href="#">개인정보처리방침</a>
							</li>
							<li class="nav-item">
								<a class="nav-link text-white" href="#">고객센터</a>
							</li>
							<li class="nav-item">
								<a class="nav-link text-white" href="#">문의하기</a>
							</li>
						</ul>
                     	<div class="footer_social">
	                        <a href="#">
	                        	<i class="fa-brands fa-instagram"></i>
	                        </a>
	                        <a href="#">
	                        	<i class="fa-brands fa-youtube"></i>
	                        </a>
	                        <a href="#">
	                        	<i class="fa-solid fa-blog"></i>
	                        </a>
	                        <a href="#">
	                        	<i class="fa-brands fa-facebook"></i>
	                        </a>
                     	</div>
                  	</div>
               </div>
            </div>
            <div class="footer-info">
            	<div class="col-lg-7 mx-auto px-0">
               		<p>&copy;<span id="displayYear"></span> All Rights Reserved By GOGODAK</p>
            	</div>
        	</div>
		</div>
	</footer>
    <%-- end footer section --%>
    
    <%-- start popup section --%>
    <div class="popup clearfix">
    	<img class="popup-img" src="images/footer/pop_coupon.jpg" alt="popup" />
      	<img class="popup-btn" src="images/footer/pop_close.png" alt="popup_close" />
    </div>
    <%-- end popup section --%>
     
    <%-- 직접 만든 JS --%>    
    <script type="text/javascript">
    	$(document).ready(function(){
      		$('#recipeCarousel').carousel({
      			interval: 10000
      		});

     		$('.carousel .carousel-item').each(function(){
     			var minPerSlide = 3;
     		    var next = $(this).next();
     		    if (!next.length) {
     		        next = $(this).siblings(':first');
     		    }
     		    next.children(':first-child').clone().appendTo($(this));
     		    
     		    for (var i=0;i<minPerSlide;i++) {
     		        next=next.next();
     		        if (!next.length) {
     		        	next = $(this).siblings(':first');
     		      	}
     		        next.children(':first-child').clone().appendTo($(this));
     		   }
     		});
    	});
    	
    	$(function () {
    		$(".popup-btn").click(function () {
    	    	$(".popup").hide();
    	  	});
    	});
    </script>
   </body>
</html>