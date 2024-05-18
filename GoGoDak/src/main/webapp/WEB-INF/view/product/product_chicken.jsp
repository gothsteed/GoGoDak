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
                   <span>🍗닭가슴살🍗</span>
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
	                           ${product.price}
	                        </h6>
	                     </div>
	                  </div>
	               </div>
					
			   </c:forEach>

            </div>
		     <div id="pageBar">
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
   </body>
</html>