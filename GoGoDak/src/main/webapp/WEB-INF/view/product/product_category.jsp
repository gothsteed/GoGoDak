<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>

<jsp:include page="../header.jsp" />
      <!-- inner page section -->
      <!-- end inner page section -->
      <!-- product section -->
      <section class="product_section layout_padding">
     
      
         <div class="container">
            <div class="heading_container heading_center">
               <h2 style="font-weight: normal;">
                   <span>${requestScope.title}</span>
               </h2>
            </div>
            <div class="event-container">
               <c:forEach var="product" items="${requestScope.productList}">

                  <div class="event-card" onclick="location.href='${pageContext.request.contextPath}/product/detail.dk?product_seq=${product.product_seq}'">
			        <img src="${pageContext.request.contextPath}/images/product/${product.main_pic}" alt="Event 1">
			        <div class="event-content">
			            <h2 class="event-title" style="font-weight: bold;">${product.product_name}</h2>
			
			            <c:choose>
	                      <c:when test="${not empty product.discount_type}">
	                         <c:choose>
	                            <c:when test="${product.discount_type == 'percent'}">
	                            <p class="event-description">
	                               <span style="text-decoration: line-through;"><fmt:formatNumber value="${product.base_price}" type="currency" currencySymbol="" groupingUsed="true" />원</span>
	                               <span style="color: red; font-weight: bold;">
	                               	 <fmt:formatNumber value="${product.discountPrice}" type="currency" currencySymbol="" groupingUsed="true" />원
	                               </span>
	                             </p>
	                            </c:when>
	                            <c:when test="${product.discount_type == 'amount'}">
	                           	  <p class="event-description">
	                               <span style="text-decoration: line-through; "><fmt:formatNumber value="${product.base_price}" type="currency" currencySymbol="" groupingUsed="true" />원</span>
	                               <span style="color: red; font-weight: bold;">
	                               	<fmt:formatNumber value="${product.discountPrice}" type="currency" currencySymbol="" groupingUsed="true" />원
	     
	                               </span>
	                              </p>
	                            </c:when>
	                         </c:choose>
	                      </c:when>
	                      <c:otherwise>
	                         <fmt:formatNumber value="${product.base_price }" type="currency" currencySymbol="" groupingUsed="true" />원
	                      </c:otherwise>
	                   </c:choose>
			            
			            
			            
	              		<c:if test="${product.discount_type == 'percent'}">
	                       <p class="event-discount">${product.discount_amount}% 할인</p>
	                    </c:if>
	                    <c:if test="${product.discount_type == 'amount'}">
	                       <p class="event-discount" ><fmt:formatNumber value="${product.discount_amount}" type="currency" currencySymbol="" groupingUsed="true" />원 할인</p>
	                    </c:if>
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
      <!-- <script src="js/jquery-3.4.1.min.js"></script> -->
      <!-- popper js -->
      <!-- <script src="js/popper.min.js"></script> -->
      <!-- bootstrap js -->
      <!-- <script src="js/bootstrap.js"></script> -->
      <!-- custom js -->
      <!-- <script src="js/custom.js"></script> -->

      <style>
		h2 {
            font-family: Arial, sans-serif;
        }
      
      	.event-container {
           	display: grid;
            grid-template-columns: repeat(4, 1fr);
            gap: 40px;
            padding: 60px;
        }
		.event-card {

            background-color: white;
            border-radius: 8px;
            box-shadow: 0 2px 5px rgba(0,0,0,0.1);
            overflow: hidden;
        }
        .event-card img {
            width: 100%;
            height: auto;
        }
        
        .event-card img {
            width: 100%;
            height: auto;
            transition: transform 0.3s ease;
        }
        .event-card:hover img {
            transform: scale(1.1);
        }
        .event-content {
            padding: 15px;
            text-align: center;
        }
        .event-content {
            padding: 15px;
        }
        .event-title {
            font-size: 18px;
            margin: 0;
        }
        .event-description {
            color: #555;
            margin: 10px 0;
        }
        .event-discount {
            font-size: 16px;
            color: #E53935;
            font-weight: bold;
        }

        @media (max-width: 1200px) {
            .event-card {
                width: calc(33.33% - 20px); /* 3 cards in one row for medium screens */
            }
        }

        @media (max-width: 900px) {
            .event-card {
                width: calc(50% - 20px); /* 2 cards in one row for small screens */
            }
        }

        @media (max-width: 600px) {
            .event-card {
                width: calc(100% - 20px); /* 1 card in one row for extra small screens */
            }
        }
      
      
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
            background-color: #fbc02d !important;
            border: solid 1px #fbc02d !important;
         }
         .heading_container h2::after {
		    content: "";
		    display: block;
		    width: 100%;
		    height: 5px;
		    background: #fbc02d;
		    margin: 20px auto;
		    border-radius: 10px;
		}

 
      </style>
</body>
</html>
