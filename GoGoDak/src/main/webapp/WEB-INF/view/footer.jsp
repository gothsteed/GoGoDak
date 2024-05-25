<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
    String ctxPath = request.getContextPath();
    //    /GoGoDak
%>
	<%-- start footer section --%>
	<footer class="footer_section pt-5 pb-5">
    	<div class="container">
       		<div class="row">
            	<div class="col-md-4 footer-col">
               		<div class="footer_contact">
                   		<div class="col-4">
                			<a class="navbar-brand" href="#"><img src="<%= ctxPath%>/images/header/logo.png" width="150" alt="..." /></a>
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
                     	<ul class="nav">
							<li class="nav-item">
								<a class="nav-link text-light pl-0" href="<%= ctxPath%>/company.dk">고고닭소개</a>
							</li>
							<li class="nav-item">
								<a class="nav-link text-light" href="<%= ctxPath%>/member/notice.dk">공지사항</a>
							</li>
							<li class="nav-item">
								<a class="nav-link text-light" href="<%= ctxPath%>/agreement.dk">이용약관</a>
							</li>
							<li class="nav-item">
								<a class="nav-link text-light" href="<%= ctxPath%>/privacy.dk">개인정보처리방침</a>
							</li>
							<li class="nav-item">
								<a class="nav-link text-light" href="<%= ctxPath%>/member/question.dk">문의하기</a>
							</li>
						</ul>
						<ul class="nav">
							<li class="nav-item mr-5">평일 : 10:00 ~ 17:00</li>
							<li class="nav-item mr-5">점심 : 12:00 ~ 13:00</li>
							<li class="nav-item">휴무 : 토, 일 / 공휴일</li>
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
               		<p>&copy;<span id="displayYear">&nbsp;2024</span> All Rights Reserved By GOGODAK</p>
            	</div>
        	</div>
		</div>
	  	<div>
			<a href="#top" class="top_button h2 text-warning"><i class="fa-solid fa-circle-chevron-up"></i></a>
		</div>
	</footer>
    <%-- end footer section --%>
     
    <%-- 직접 만든 JS --%>    
    <script type="text/javascript">
		// autocomplete start
    	function autocomplete(inp, arr) {
	 		var currentFocus;
	  
	  		inp.addEventListener("input", function(e) {
	      		var a, b, i, val = this.value;
	      
	      		closeAllLists();
	      		if (!val) { return false;}
	      		currentFocus = -1;
	      
	      		a = document.createElement("DIV");
	      		a.setAttribute("id", this.id + "autocomplete-list");
	      		a.setAttribute("class", "autocomplete-items");
	      	
	      		this.parentNode.appendChild(a);
	     
	      		for (i = 0; i < arr.length; i++) {
	        		if (arr[i].substr(0, val.length).toUpperCase() == val.toUpperCase()) {
	          			b = document.createElement("DIV");
	          			b.innerHTML = "<strong>" + arr[i].substr(0, val.length) + "</strong>";
	       				b.innerHTML += arr[i].substr(val.length);
	          			b.innerHTML += "<input type='hidden' value='" + arr[i] + "'>";
	          			b.addEventListener("click", function(e) {
	              			inp.value = this.getElementsByTagName("input")[0].value;
	              			closeAllLists();
	          			});
	          			a.appendChild(b);
	        		}
	      		}
	  		});
	  
	  		inp.addEventListener("keydown", function(e) {
	      		var x = document.getElementById(this.id + "autocomplete-list");
	      		if (x) x = x.getElementsByTagName("div");
	      		if (e.keyCode == 40) { // key down
	    			currentFocus++;
	        		addActive(x);
	      		} 
	      		else if (e.keyCode == 38) { // key up
	        		currentFocus--;
	        		addActive(x);
	      		} 
	      		else if (e.keyCode == 13) { // enter
	        		e.preventDefault();
	        		if (currentFocus > -1) {
	          			if (x) x[currentFocus].click();
	        		}
	      		}
	  		});
	  		
	  		function addActive(x) {
	    		if (!x) return false;
			    removeActive(x);
			    if (currentFocus >= x.length) currentFocus = 0;
			    if (currentFocus < 0) currentFocus = (x.length - 1);
	    		x[currentFocus].classList.add("autocomplete-active");
	  		}
	  		
		  	function removeActive(x) {
		    	for (var i = 0; i < x.length; i++) {
		      		x[i].classList.remove("autocomplete-active");
		   	 	}
		  	}
		  	
	  		function closeAllLists(elmnt) {
	    		var x = document.getElementsByClassName("autocomplete-items");
	    		for (var i = 0; i < x.length; i++) {
	      			if (elmnt != x[i] && elmnt != inp) {
	        			x[i].parentNode.removeChild(x[i]);
	      			}
	    		}
	  		}
	 
	  		document.addEventListener("click", function (e) {
	      		closeAllLists(e.target);
	  		});
		}

		var foodes = ["고고단","곤약볶음밥","그래놀라","닭가슴살","닭가슴살 곤약볶음밥","닭가슴살 치밥","닭가슴살 치킨","떡마리","만두","볶음밥","붕어빵","피자","사각피자","샤오롱바오","왕만두","쫄깃만두","치밥","치킨","포테이토스틱","프로틴","현미"];
		autocomplete(document.getElementById("myInput"), foodes);
		// autocomplete end
		
		$(document).ready(function(){
			$('[data-toggle="tooltip"]').tooltip();   
		});
		
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
    		$('a[href="#top"]').click(function (e) {
    			e.preventDefault();

    		    jQuery("html, body").animate(
    		    	{
    		        	scrollTop: 0,
    		      	},
    		      	1000
    		    );
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