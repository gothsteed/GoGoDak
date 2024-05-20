<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
    
<jsp:include page="../header.jsp" />
      <!-- inner page section -->
      <!-- end inner page section -->
      <!-- product section -->
      <section class="product_section layout_padding">
         <div class="container">
            <div class="heading_container heading_center">
               <h2>
                   <span>${requestScope.title}</span>
               </h2>
            </div>
            <div class="row">
               
      
               <c:forEach var="product" items="${requestScope.productList}">
	               <div class="col-sm-6 col-md-4 col-lg-3" href="${pageContext.request.contextPath}/product/detail.dk?product_seq=${product.product_Seq}">
	                  <div class="box">
	                     <div class="option_container">
	                        <div class="options">
	                           <a href="" class="option1">
	                           Add To Cart
	                           </a>
	                           <a href="" class="option2">
	                           Buy Now
	                           </a>
	                        </div>
	                     </div>
	                     <div class="img-box">
	                        <img src="${pageContext.request.contextPath}/images/chicken/${product.product_name}.jpg" alt="">
	                     </div>
	                     <div class="detail-box">
	                        <h5>
	                           ${product.product_name}
	                        </h5>
	                        <h6>
	                           <c:choose>
	                              <c:when test="${not empty product.discount_type}">
	                                 <c:choose>
	                                    <c:when test="${product.discount_type == 'percent'}">
	                                       <span style="text-decoration: line-through;">${product.base_price}</span>
	                                       <span>
	                                          ${product.base_price - (product.base_price * product.discount_amount / 100)}
	                                       </span>
	                                    </c:when>
	                                    <c:when test="${product.discount_type == 'amount'}">
	                                       <span style="text-decoration: line-through;">${product.base_price}</span>
	                                       <span>
	                              
	                                          ${product.base_price - product.discount_amount}
	                                       </span>
	                                    </c:when>
	                                 </c:choose>
	                              </c:when>
	                              <c:otherwise>
	                                 ${product.base_price}
	                              </c:otherwise>
	                           </c:choose>
	                        </h6>
	                     </div>
	                  </div>
	               </div>
					
			   </c:forEach>

            </div>
		     <div id="pageBar" class="center">
		       <nav>
		           <ul class="pagination">
		           		${requestScope.pageBar}
		           </ul>
		       </nav>
		    </div>
         </div>
      </section>
      <!-- end product section -->
      <!-- footer section -->
	<jsp:include page="../footer.jsp"></jsp:include>
      <!-- footer section -->
      <!-- jQery -->
<!--       <script src="js/jquery-3.4.1.min.js"></script>
      popper js
      <script src="js/popper.min.js"></script>
      bootstrap js
      <script src="js/bootstrap.js"></script>
      custom js
      <script src="js/custom.js"></script> -->
      
      
       <style>
		   .center {
		       text-align: center; /* Center the text inside the div */
		       margin-top: 50px; /* Add top margin */
		   }
		   .center nav {
		       display: inline-block; /* Ensure the nav is treated as an inline-block element */
		   }
		   	
		   .pagination {
		       padding: 10px; /* Padding around the pagination */
		       border-radius: 5px; /* Rounded corners */
		   }
		   
		   .pagination li {
		       display: inline; /* Display list items inline */
		   }
		   
		   .pagination li a {
		       color: white; /* Text color */
		       padding: 8px 12px; /* Padding for the links */
		       text-decoration: none; /* Remove underline from links */
		       margin: 0 5px; /* Margin between links */
		       border-radius: 5px; /* Rounded corners for links */
		       background-color: black; /* Background color of links */
		       border: 1px solid white; /* Border color */
		   }
		   
		   .pagination li a:hover {
		       background-color: white; /* Background color on hover */
		       color: black; /* Text color on hover */
		   }
		   
		   .pagination .active a {
			    color: white;
			    background-color: #fbc02d !Important;
			    border: solid 1px #fbc02d !Important;
		   }
	   </style>
      
   </body>
</html>
