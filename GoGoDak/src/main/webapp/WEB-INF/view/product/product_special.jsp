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
            text-align: center;
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
    </style>
  <section class="product_section layout_padding">
  
  	<div align="right" class="onlyLog">
		<c:if test="${not empty sessionScope.loginuser and sessionScope.loginuser.id == 'admin'}">	
			<button type="button" class="btn btn-light" onclick="location.href='<%= ctxPath%>/admin/event.dk'">할인행사 등록</button>
			<button type="button" class="btn btn-light" data-toggle="modal" data-target="#updateEventModal">할인행사 업데이트</button>
			<button type="button" class="btn btn-light" data-toggle="modal" data-target="#deleteEventModal">할인행사 삭제</button>
		</c:if>
	</div>
	
	<div class="modal fade" id="deleteEventModal" tabindex="-1" role="dialog" aria-labelledby="deleteEventModalLabel"
		aria-hidden="true">
		<div class="modal-dialog" role="document">
			<div class="modal-content">
				<div class="modal-header">
					<h5 class="modal-title" id="deleteEventModalLabel">이벤트 삭제</h5>
					<button type="button" class="close" data-dismiss="modal" aria-label="Close">
						<span aria-hidden="true">&times;</span>
					</button>
				</div>
				<div class="modal-body">
					<form id="deleteEventForm" action="<%=ctxPath%>/admin/deleteEvent.dk" method="post">
						<div class="form-group">
							<label for="eventSelect">이벤트 선택</label> <select
								class="form-control" id="eventSelect" name="discount_event_seq">
								<c:forEach var="event" items="${requestScope.discount_event_list}">
									<option value="${event.discount_event_seq}">${event.discount_name}</option>
								</c:forEach>
							</select>
						</div>
					</form>
				</div>
				<div class="modal-footer">
					<button type="button" class="btn btn-secondary" data-dismiss="modal">Close</button>
					<button type="submit" form="deleteEventForm" class="btn btn-primary">Update Event</button>
				</div>
			</div>
		</div>
	</div>

	<div class="modal fade" id="updateEventModal" tabindex="-1" role="dialog" aria-labelledby="updateEventModalLabel"
		aria-hidden="true">
		<div class="modal-dialog" role="document">
			<div class="modal-content">
				<div class="modal-header">
					<h5 class="modal-title" id="updateEventModalLabel">이벤트 수정</h5>
					<button type="button" class="close" data-dismiss="modal" aria-label="Close">
						<span aria-hidden="true">&times;</span>
					</button>
				</div>
				<div class="modal-body">
					<form id="updateEventForm" action="<%=ctxPath%>/admin/updateEvent.dk" method="get">
						<div class="form-group">
							<label for="eventSelect">이벤트 선택</label> <select
								class="form-control" id="eventSelect" name="discount_event_seq">
								<c:forEach var="event" items="${requestScope.discount_event_list}">
									<option value="${event.discount_event_seq}">${event.discount_name}</option>
								</c:forEach>
							</select>
						</div>
					</form>
				</div>
				<div class="modal-footer">
					<button type="button" class="btn btn-secondary" data-dismiss="modal">Close</button>
					<button type="submit" form="updateEventForm" class="btn btn-primary">Update Event</button>
				</div>
			</div>
		</div>
	</div>

	<div class="container">
         <div class="event-container">
          <c:forEach var="event" items="${requestScope.discount_event_list}">

                  <div class="event-card" onclick="location.href='${pageContext.request.contextPath}/product/event/detail.dk?discount_event_seq=${event.discount_event_seq}'">
			        <img src="${pageContext.request.contextPath}/images/special/${event.pic}" alt="">
			        <div class="event-content">
			            <h2 class="event-title" style="font-weight: bold;">${event.discount_name}</h2>
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
<jsp:include page="../footer.jsp"/>  