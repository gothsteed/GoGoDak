<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
   <head>
      <!-- Basic -->
      <meta charset="utf-8" />
      <meta http-equiv="X-UA-Compatible" content="IE=edge" />
      <!-- Mobile Metas -->
      <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no" />
      <!-- Site Metas -->
      <meta name="keywords" content="" />
      <meta name="description" content="" />
      <meta name="author" content="" />
      <link rel="shortcut icon" href="images/favicon.png" type="">
      <title>Famms - Fashion HTML Template</title>
      <!-- bootstrap core css -->
      <link rel="stylesheet" type="text/css" href="css/bootstrap.css" />
      <!-- font awesome style -->
      <link href="/css/font-awesome.min.css" rel="stylesheet" />
      <!-- Custom styles for this template -->
      <link href="/css/style.css" rel="stylesheet" />
      <!-- responsive style -->
      <link href="/css/responsive.css" rel="stylesheet" />
   </head>
   
   
      <style>
 .board_wrap {
  width: 1000px;
  margin: 100px auto;
  background-color: #f8f9fa;
  border-radius: 8px;
  box-shadow: 0 2px 4px rgba(0, 0, 0, 0.1);
  padding: 20px;
}

.board_title {
  margin-bottom: 20px;
  text-align: center;
}

.board_title strong {
  font-size: 2.2rem;
  color: #333;
}

.board_title p {
  margin-top: 5px;
  font-size: 1.4rem;
  color: #666;
}

.bt_wrap {
  margin-top: 20px;
  text-align: center;
}

.bt_wrap a {
  display: inline-block;
  min-width: 100px;
  margin-left: 10px;
  padding: 8px 16px;
  border: 1px solid #007bff;
  border-radius: 4px;
  font-size: 1.4rem;
  color: #007bff;
  text-decoration: none;
  transition: background-color 0.3s, color 0.3s;
}

.bt_wrap a:first-child {
  margin-left: 0;
}

.bt_wrap a.on {
  background: #007bff;
  color: #fff;
}

.board_list {
  width: 100%;
  border-top: 1px solid #ccc;
}

.board_list > div {
  border-bottom: 1px solid #ccc;
  font-size: 1.4rem;
}

.board_list > div.top {
  border-bottom: 2px solid #007bff;
}

.board_list > div:last-child {
  border-bottom: none;
}

.board_list > div > div {
  display: inline-block;
  padding: 10px 0;
  text-align: center;
  font-size: 1.4rem;
  color: #333;
}

.board_list > div.top > div {
  font-weight: 600;
}

.board_list .num {
  width: 10%;
  color: #fbc02d;
}

.board_list .title {
  width: 70%; /* Increase width for title */
  text-align: left;
}

.board_list .top .title {
  text-align: center;
}

.board_list .writer,
.board_list .date,
.board_list .count {
  width: 10%;
}

.board_page {
  margin-top: 20px;
  text-align: center;
}

.board_page a {
  display: inline-block;
  width: 32px;
  height: 32px;
  line-height: 32px;
  border: 1px solid #ddd;
  border-left: 0;
  text-align: center;
  font-size: 1.2rem;
  color: #333;
  text-decoration: none;
}

.board_page a.bt {
  padding-top: 8px;
}

.board_page a.num {
  padding-top: 7px;
  font-size: 1.4rem;
}

.board_page a.num.on {
  border-color: #fbc02d;
  background: #fbc02d;
  color: #fff;
 
}

.board_page a:first-child {
  border-left: 1px solid #ddd;
}

.board_view {
  width: 100%;
  border-top: 1px solid #ccc;
  font-size: 1.4rem;
}

.board_view .title {
  padding: 20px 0;
  border-bottom: 1px dashed #ddd;
  font-size: 2rem;
}

.board_view .info {
  padding: 15px 0;
  border-bottom: 1px solid #ddd;
  font-size: 1.4rem;
}

.board_view .info dl {
  display: inline-block;
  margin: 0 20px;
}

.board_view .info dl::before {
  content: "|";
  margin-right: 20px;
  color: #ccc;
}

.board_view .info dl:first-child::before {
  display: none;
}

.board_view .info dl dt,
.board_view .info dl dd {
  display: inline-block;
}

.board_view .info dl dd {
  margin-left: 5px;
  color: #777;
}

.board_view .cont {
  padding: 15px 0;
  line-height: 160%;
}

.board_write {
  border-top: 1px solid #ccc;
}

.board_write .title,
.board_write .info {
  padding: 15px 0;
}

.board_write .info {
  border-top: 1px solid #ccc;
  border-bottom: 1px solid #ccc;
}

.board_write .cont {
  padding: 15px 0;
}

.board_write .cont textarea {
  width: 100%;
  height: 250px;
  padding: 10px;
  border: 1px solid #ccc;
  border-radius: 4px;
  resize: vertical;
}

.board_write .title input[type="text"],
.board_write .info input[type="text"],
.board_write .info input[type="password"] {
  width: calc(50% - 10px); /* Set width to 50% of container width */
  padding: 8px; /* Adjust padding */
  border: 1px solid #ccc;
  border-radius: 4px;
  margin-right: 10px; /* Add margin-right for spacing */
  display: inline-block; /* Display inline */
  vertical-align: middle; /* Align vertically */
  background-color: #fff; /* White background */
}

/* Adjust width for smaller screens */
@media (max-width: 768px) {
  .board_write .title input[type="text"],
  .board_write .info input[type="text"],
  .board_write .info input[type="password"] {
    width: calc(100% - 10px); /* Full width on smaller screens */
    margin-right: 0; /* Remove right margin */
    margin-bottom: 10px; /* Add bottom margin for spacing */
  }
}
      	
      
	 </style>
      

      <script>
          // 페이지가 로드될 때 실행되는 함수
          window.onload = function() {
         // showOrderHistory() 함수 호출
             showOrderHistory();
         }
        
        
        function changeDate(date) {
            document.getElementById("selectedDate").value = date;
            document.getElementById("selectedDate2").value = date;

         }

         function search() {
            var selectedDate = document.getElementById("selectedDate").value;
            alert("선택한 날짜: " + selectedDate);
            var selectedDate = document.getElementById("selectedDate2").value;
            alert("선택한 날짜: " + selectedDate2);
            // 조회 기능 구현
         }

         function showOrderHistory() {
            document.getElementById("orderHistory").style.display = "block";
            document.getElementById("cancellationHistory").style.display = "none";
            document.getElementById("pastOrders").style.display = "none";
         }

         function showCancellationHistory() {
            document.getElementById("orderHistory").style.display = "none";
            document.getElementById("cancellationHistory").style.display = "block";
            document.getElementById("pastOrders").style.display = "none";
         }

         function showPastOrders() {
            document.getElementById("orderHistory").style.display = "none";
            document.getElementById("cancellationHistory").style.display = "none";
            document.getElementById("pastOrders").style.display = "block";
         }
      
      function changeDate(period) {
        var today = new Date();
         var selectedDate = "";
         switch (period) {
         case '오늘':
            selectedDate = formatDate(today);
            break;
         case '1주일':
            var oneWeekAgo = new Date(today.getTime() - 7 * 24 * 60 * 60 * 1000);
            selectedDate = formatDate(oneWeekAgo);
            break;
         case '1개월':
            var oneMonthAgo = new Date(today.getFullYear(), today.getMonth() - 1, today.getDate());
            selectedDate = formatDate(oneMonthAgo);
            break;
         case '3개월':
            var threeMonthsAgo = new Date(today.getFullYear(), today.getMonth() - 3, today.getDate());
            selectedDate = formatDate(threeMonthsAgo);
            break;
         case '6개월':
            var sixMonthsAgo = new Date(today.getFullYear(), today.getMonth() - 6, today.getDate());
            selectedDate = formatDate(sixMonthsAgo);
            break;
      }
      document.getElementById("selectedDate").value = selectedDate;
      document.getElementById("selectedDate2").value = selectedDate;
   }

   function formatDate(date) {
      var year = date.getFullYear();
      var month = (date.getMonth() + 1) < 10 ? '0' + (date.getMonth() + 1) : (date.getMonth() + 1);
      var day = date.getDate() < 10 ? '0' + date.getDate() : date.getDate();
      return year + '-' + month + '-' + day;
   }

 

   $(function() {
      $('input[name="datetimes"]').daterangepicker({
         timePicker: true,
         startDate: moment().startOf('hour'),
         endDate: moment().startOf('hour').add(32, 'hour'),
         locale: {
         format: 'M/DD hh:mm A'
         }
      });
   });
        
 
     </script>
   
   
   <body class="sub_page">
      <div class="hero_area">
         <!-- header section strats -->
         <header class="header_section">
            <div class="container">
               <nav class="navbar navbar-expand-lg custom_nav-container ">
                  <a class="navbar-brand" href="index.html"><img width="250" src="images/logo.png" alt="#" /></a>
                  <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarSupportedContent" aria-controls="navbarSupportedContent" aria-expanded="false" aria-label="Toggle navigation">
                  <span class=""> </span>
                  </button>
                  <div class="collapse navbar-collapse" id="navbarSupportedContent">
                     <ul class="navbar-nav">
                        <li class="nav-item">
                           <a class="nav-link" href="index.html">Home <span class="sr-only">(current)</span></a>
                        </li>
                        <li class="nav-item dropdown">
                           <a class="nav-link dropdown-toggle" href="#" data-toggle="dropdown" role="button" aria-haspopup="true" aria-expanded="true"> <span class="nav-label">Pages <span class="caret"></span></a>
                           <ul class="dropdown-menu">
                              <li><a href="about.html">About</a></li>
                              <li><a href="testimonial.html">Testimonial</a></li>
                           </ul>
                        </li>
                        <li class="nav-item active">
                           <a class="nav-link" href="product.html">Products</a>
                        </li>
                        <li class="nav-item">
                           <a class="nav-link" href="blog_list.html">Blog</a>
                        </li>
                        <li class="nav-item">
                           <a class="nav-link" href="contact.html">Contact</a>
                        </li>
                        <li class="nav-item">
                           <a class="nav-link" href="#">
                              <svg version="1.1" id="Capa_1" xmlns="http://www.w3.org/2000/svg" xmlns:xlink="http://www.w3.org/1999/xlink" x="0px" y="0px" viewBox="0 0 456.029 456.029" style="enable-background:new 0 0 456.029 456.029;" xml:space="preserve">
                                 <g>
                                    <g>
                                       <path d="M345.6,338.862c-29.184,0-53.248,23.552-53.248,53.248c0,29.184,23.552,53.248,53.248,53.248
                                          c29.184,0,53.248-23.552,53.248-53.248C398.336,362.926,374.784,338.862,345.6,338.862z" />
                                    </g>
                                 </g>
                                 <g>
                                    <g>
                                       <path d="M439.296,84.91c-1.024,0-2.56-0.512-4.096-0.512H112.64l-5.12-34.304C104.448,27.566,84.992,10.67,61.952,10.67H20.48
                                          C9.216,10.67,0,19.886,0,31.15c0,11.264,9.216,20.48,20.48,20.48h41.472c2.56,0,4.608,2.048,5.12,4.608l31.744,216.064
                                          c4.096,27.136,27.648,47.616,55.296,47.616h212.992c26.624,0,49.664-18.944,55.296-45.056l33.28-166.4
                                          C457.728,97.71,450.56,86.958,439.296,84.91z" />
                                    </g>
                                 </g>
                                 <g>
                                    <g>
                                       <path d="M215.04,389.55c-1.024-28.16-24.576-50.688-52.736-50.688c-29.696,1.536-52.224,26.112-51.2,55.296
                                          c1.024,28.16,24.064,50.688,52.224,50.688h1.024C193.536,443.31,216.576,418.734,215.04,389.55z" />
                                    </g>
                                 </g>
                                 <g>
                                 </g>
                                 <g>
                                 </g>
                                 <g>
                                 </g>
                                 <g>
                                 </g>
                                 <g>
                                 </g>
                                 <g>
                                 </g>
                                 <g>
                                 </g>
                                 <g>
                                 </g>
                                 <g>
                                 </g>
                                 <g>
                                 </g>
                                 <g>
                                 </g>
                                 <g>
                                 </g>
                                 <g>
                                 </g>
                                 <g>
                                 </g>
                                 <g>
                                 </g>
                              </svg>
                           </a>
                        </li>
                        <form class="form-inline">
                           <button class="btn  my-2 my-sm-0 nav_search-btn" type="submit">
                           <i class="fa fa-search" aria-hidden="true"></i>
                           </button>
                        </form>
                     </ul>
                  </div>
               </nav>
            </div>
         </header>
         <!-- end header section -->
      </div>
      <!-- inner page section -->
      <!-- end inner page section -->
      <!-- board section -->
     
	 </head>
   <body>
     <div class="board_wrap">
       <div class="board_title">
         <strong>공지사항</strong>
         <p>공지사항을 안내해드립니다.</p>
       </div>
       <div class="board_write_wrap">
         <div class="board_write">
           <div class="title">
             <dl>
               <dt>제목</dt>
               <dd><input type="text" placeholder="제목 입력" /></dd>
             </dl>
           </div>
         
           <div class="cont">
            <dt>내용</dt> 
            <textarea placeholder="내용 입력"></textarea>
           </div>
         </div>
 
         <div class="bt_wrap">
           <a href="write.html" class="on">등록</a>
           <a href="#">취소</a>
         </div>
       </div>
     </div>
   </body>

	
      <!-- end board section -->
      <!-- footer section -->
      <footer class="footer_section">
         <div class="container">
            <div class="row">
               <div class="col-md-4 footer-col">
                  <div class="footer_contact">
                     <h4>
                        <img width="250" src="images/logo.png" alt="#" />
                     </h4>
                     <div class="contact_link_box">
                        <a href="">
                        <i class="fa fa-map-marker" aria-hidden="true"></i>
                        <span>
                        위치
                        </span>
                        </a>
                        <a href="">
                        <i class="fa fa-phone" aria-hidden="true"></i>
                        <span>
                           대표전화 : 1111-1313
                        </span>
                        </a>
                        <a href="">
                        <i class="fa fa-envelope" aria-hidden="true"></i>
                        <span>
                           sales@barudak.co.kr
                        </span>
                        </a>
                     </div>
                  </div>
               </div>
               <div class="col-md-4 footer-col">
                  <div class="footer_detail">
                     <a href="index.html" class="footer-logo">
                        1111-1313
                     </a>
                     <p>
                        1566-3197
                        평일 : 오전 10:00 ~ 오후 5:00 <br>
                        점심 : 오후 12:00 ~ 오후 01:00<br>
                        휴무 : 토 / 일 / 공휴일은 휴무
                     </p>
                     <div class="footer_social">
                        <a href="">
                        <i class="fa fa-facebook" aria-hidden="true"></i>
                        </a>
                        <a href="">
                        <i class="fa fa-twitter" aria-hidden="true"></i>
                        </a>
                        <a href="">
                        <i class="fa fa-linkedin" aria-hidden="true"></i>
                        </a>
                        <a href="">
                        <i class="fa fa-instagram" aria-hidden="true"></i>
                        </a>
                        <a href="">
                        <i class="fa fa-pinterest" aria-hidden="true"></i>
                        </a>
                     </div>
                  </div>
               </div>
               <div class="col-md-4 footer-col">
                  <div class="map_container">
                     <div class="map">
                        <div id="googleMap"></div>
                     </div>
                  </div>
               </div>
            </div>
            <div class="footer-info">
               <div class="col-lg-7 mx-auto px-0">
                  <p>
                     &copy; <span id="displayYear"></span> All Rights Reserved By
                     <a href="https://html.design/">Free Html Templates</a><br>
         
                     Distributed By <a href="https://themewagon.com/" target="_blank">ThemeWagon</a>
                  </p>
               </div>
            </div>
         </div>
      </footer>
      <!-- footer section -->
      <!-- jQery -->
      <script src="js/jquery-3.4.1.min.js"></script>
      <!-- popper js -->
      <script src="js/popper.min.js"></script>
      <!-- bootstrap js -->
      <script src="js/bootstrap.js"></script>
      <!-- custom js -->
      <script src="js/custom.js"></script>
   </body>
</html>