<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<jsp:include page="../header.jsp" />

<%
    String contextPath = request.getContextPath();
    //    /Ssangdak
%>
      <!-- inner page section -->
      <section class="inner_page_head">
         <div class="container_fuild">
            <div class="row">
               <div class="col-md-12">
                  <div class="full">
                     <h3>주문 확인</h3>
                  </div>
               </div>
            </div>
         </div>
      </section>
      <!-- end inner page section -->
      <!-- why section -->
      <section class="why_section layout_padding">
         <div class="container">
           <div class="row">
             <div class="col-md-6">
               <div class="product_image">
                 <!-- Product Image -->
                 <img src="<%= contextPath %>/images/chicken/0c21851fc450cbc939f6dd460b7847fd.jpg" alt="Product Name" style="width:100%;">
               </div>
             </div>
             <div class="col-md-6">
               <div class="product_detail">
                 <h2>Product Name</h2>
                 <p class="product_price">$39.99</p>
                 <p class="product_description">
                   Lorem ipsum dolor sit amet, consectetur adipiscing elit. Sed turpis massa, scelerisque vel diam non, 
                   scelerisque interdum ipsum. Pellentesque vitae neque mollis, congue tellus eget, semper massa. 
                   Vivamus sed orci nec risus dictum posuere.
                 </p>
                 <div class="product_review">
                   <p>Customer Reviews: ★★★★☆</p>
                 </div>
                 <div class="purchase_info">
                   <button class="btn btn-primary">Add to Cart</button>
                   <button class="btn btn-success">Buy Now</button>
                 </div>
               </div>
             </div>
           </div>
         </div>

         <div class="product_tabs">
            <ul class="nav nav-tabs" id="productTab" role="tablist">
              <li class="nav-item">
                <a class="nav-link active" id="details-tab" data-toggle="tab" href="#details" role="tab" aria-controls="details" aria-selected="true">Product Details</a>
              </li>
              <li class="nav-item">
                <a class="nav-link" id="reviews-tab" data-toggle="tab" href="#reviews" role="tab" aria-controls="reviews" aria-selected="false">Reviews</a>
              </li>
            </ul>
            <div class="tab-content" id="productTabContent">
              <div class="tab-pane fade show active" id="details" role="tabpanel" aria-labelledby="details-tab">
                <!-- Detailed information about the product -->
                <p>Lorem ipsum dolor sit amet, consectetur adipiscing elit. Maecenas interdum, massa nec vulputate sagittis, 
                dolor massa aliquet velit, in fermentum velit dolor sit amet quam. Sed id justo finibus, gravida lectus ac, 
                viverra elit. Nullam viverra lorem eu augue tincidunt gravida.</p>

                  <div>



                     
                  </div>

              </div>
              <div class="tab-pane fade" id="reviews" role="tabpanel" aria-labelledby="reviews-tab">
                <!-- Customer reviews content -->
                <p>Customer Review 1: "Great product, really happy with the purchase!" - John Doe</p>
                <p>Customer Review 2: "The product could be improved. It worked fine but I had some issues with the delivery." - Jane Smith</p>
              </div>
            </div>
          </div>
          
       </section>
       
      <!-- end why section -->

      <!-- footer section -->
	 <jsp:include page="../footer.jsp"></jsp:include>





      <!-- footer section -->
      <!-- jQery -->
      <script src="js/jquery-3.4.1.min.js"></script>
      <!-- popper js -->
      <script src="js/popper.min.js"></script>
      <!-- bootstrap js -->
      <script src="js/bootstrap.js"></script>
      <!-- custom js -->
      <script src="js/custom.js"></script>

      <script>
         

      </script>
   </body>


   <style>
      .product_image img {
      max-width: 100%;
      height: auto;
      border: 1px solid #ddd;
      border-radius: 4px;
      padding: 5px;
      }

      .product_detail h2 {
      color: #333;
      font-size: 24px;
      }

      .product_price {
      color: green;
      font-size: 20px;
      margin-top: 5px;
      }

      .product_description {
      font-size: 16px;
      margin-top: 10px;
      color: #666;
      }

      .product_review p {
      color: #ffa500;
      margin-top: 10px;
      }

      .purchase_info button {
      padding: 10px 20px;
      margin-right: 10px;
      border: none;
      border-radius: 5px;
      cursor: pointer;
      font-size: 16px;
      margin-top: 20px;
      }

      .btn-primary {
      background-color: #007bff;
      color: white;
      }

      .btn-success {
      background-color: #28a745;
      color: white;
      }

      .btn-primary:hover, .btn-success:hover {
      opacity: 0.8;
      }

      .nav-tabs {
      border-bottom: 2px solid #dee2e6;
      }

      .nav-item {
      margin-bottom: -1px;
      }

      .nav-link {
      border: 1px solid transparent;
      border-radius: 0.25rem;
      padding: 0.5rem 1rem;
      color: #007bff;
      background-color: #fff;
      border-color: #dee2e6 #dee2e6 #fff;
      }

      .nav-link.active {
      color: #495057;
      background-color: #fff;
      border-color: #dee2e6 #dee2e6 #fff;
      }

      .nav-link:hover {
      border-color: #e9ecef #e9ecef #dee2e6;
      }

      .tab-content > .tab-pane {
      padding: 20px;
      border-top: 1px solid #dee2e6;
      }

      .product_tabs {
      margin-top: 5%;  /* Smaller top margin */
      margin-right: 10%;  /* Right margin */
      margin-bottom: 10%;  /* Bottom margin */
      margin-left: 10%;  /* Left margin */
      }

      
  </style>
</html>