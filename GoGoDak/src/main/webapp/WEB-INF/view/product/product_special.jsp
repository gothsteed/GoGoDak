<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>

<%
    String ctxPath = request.getContextPath();
    //    /GoGoDak
%>
   
 
<jsp:include page="../header.jsp" />

    <style>
/*         body {
            font-family: Arial, sans-serif;
            margin: 0;
            padding: 0;
            background-color: #f4f4f4;
        }
        .header {
            background-color: #4CAF50;
            color: white;
            padding: 10px 20px;
            text-align: center;
        } */
        .event-container {
           	display: grid;
            grid-template-columns: repeat(3, 1fr);
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
        }
    </style>
  <section class="product_section layout_padding">
  
  	<div align="right" class="onlyLog">
		<c:if test="${not empty sessionScope.loginuser and sessionScope.loginuser.id == 'admin'}">	
			<button type="button" class="btn btn-light" onclick="location.href='<%= ctxPath%>/admin/event.dk'">할인행사 등록</button>
		</c:if>
	</div> 
  	<div class="container">
         <div class="event-container">
		    <div class="event-card">
		        <img src="<%=ctxPath %>/images/special/p1.png" alt="Event 1">
		        <div class="event-content">
		            <h2 class="event-title">Summer Sale</h2>
		            <p class="event-description">Enjoy up to 50% off on summer collections. Limited time offer!</p>
		            <p class="event-discount">Up to 50% off</p>
		        </div>
		    </div>
		
		    <div class="event-card">
		        <img src="<%=ctxPath %>/images/special/p1.png" alt="Event 2">
		        <div class="event-content">
		            <h2 class="event-title">Back to School</h2>
		            <p class="event-description">Get your school supplies at amazing discounts. Don't miss out!</p>
		            <p class="event-discount">Up to 30% off</p>
		        </div>
		    </div>
		
		    <div class="event-card">
		        <img src="<%=ctxPath %>/images/special/p1.png" alt="Event 3">
		        <div class="event-content">
		            <h2 class="event-title">Black Friday Deals</h2>
		            <p class="event-description">Exclusive Black Friday deals on electronics, fashion, and more.</p>
		            <p class="event-discount">Up to 70% off</p>
		        </div>
		    </div>
		
		    <div class="event-card">
		        <img src="<%=ctxPath %>/images/special/p1.png" alt="Event 4">
		        <div class="event-content">
		            <h2 class="event-title">Holiday Specials</h2>
		            <p class="event-description">Celebrate the holidays with special discounts on gifts and decorations.</p>
		            <p class="event-discount">Up to 40% off</p>
		        </div>
		    </div>
		    
		    		    <div class="event-card">
		        <img src="<%=ctxPath %>/images/special/p1.png" alt="Event 4">
		        <div class="event-content">
		            <h2 class="event-title">Holiday Specials</h2>
		            <p class="event-description">Celebrate the holidays with special discounts on gifts and decorations.</p>
		            <p class="event-discount">Up to 40% off</p>
		        </div>
		    </div>
		    
		   	<div class="event-card">
		        <img src="<%=ctxPath %>/images/special/p1.png" alt="Event 4">
		        <div class="event-content">
		            <h2 class="event-title">Holiday Specials</h2>
		            <p class="event-description">Celebrate the holidays with special discounts on gifts and decorations.</p>
		            <p class="event-discount">Up to 40% off</p>
		        </div>
		    </div>
			</div>
            <div class="btn-box">
               <a href="">
               View All products
               </a>
            </div>
         </div>
        </div>
      </section>
<jsp:include page="../footer.jsp"/>  