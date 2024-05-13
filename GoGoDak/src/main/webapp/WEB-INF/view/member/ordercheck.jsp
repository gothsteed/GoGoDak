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
         /* 테이블 스타일 */
         table {
            width: 100%;
            border-collapse: collapse;
            border-spacing: 0;
            margin-bottom: 20px;
         }
         th, td {
            border-bottom: 1px solid #ddd;
            padding: 12px 15px;
            text-align: left;
         }
         th {
            background-color: #f2f2f2;
            color: #333;
            text-transform: uppercase;
         }
         /* 짝수 행 배경색 */
         tr:nth-child(even) {
            background-color: #f2f2f2;
         }
         /* 마지막 열 정렬 */
         td:last-child {
            text-align: center;
         }
         /* 페이징 스타일 */
         .pagination {
            display: flex;
            justify-content: center;
            margin-top: 20px;
         }
         .pagination a {
            color: #333;
            padding: 8px 12px;
            text-decoration: none;
            transition: background-color .3s;
            border: 1px solid #ddd;
            margin: 0 4px;
            border-radius: 4px;
            background-color: #fff;
            cursor: pointer;
         }
         .pagination a.active {
            background-color: #fbc02d;
            color: white;
            border: 1px solid #fbc02d;
         }
         .pagination a:hover:not(.active) {background-color: #ddd;}

         /* 버튼 스타일 */
         .btn-group {
            margin-top: 10px;
         }
         .btn-group button {
            background-color: #fbc02d;
            color: #fff;
            border: none;
            padding: 10px 20px;
            margin-right: 10px;
            border-radius: 5px;
            cursor: pointer;
            transition: background-color 0.3s ease;
         }
         .btn-group button:hover {
            background-color: #ffa000;
         }
         .btn-group input[type="text"] {
            padding: 10px;
            margin-right: 10px;
            border: 1px solid #ddd;
            border-radius: 5px;
         }
         .btn-group button[type="submit"] {
            background-color: #333;
         }
         .btn-group button[type="submit"]:hover {
            background-color: #555;
         }

         /* 네모 박스 디자인 */
         #orderHistory, #cancellationHistory, #pastOrders {
            border: 1px solid #ddd;
            border-radius: 5px;
            padding: 20px;
            margin-bottom: 20px;
         }

         .separator {
            display: inline-block;
            margin: 0 10px; /* Adjust the spacing as needed */
            font-size: 20px; /* Increase font size */
            color: #333; /* Adjust color as needed */
            vertical-align: middle; /* Center vertically */
         }


         body > h4 > button:nth-child(1),
         body > h4 > button:nth-child(2),
         body > h4 > button:nth-child(3) {
         background-color: #fff; /* 배경색을 흰색으로 변경 */
         margin-top: 10px; /* 버튼을 아래로 10px 정도 내립니다. */
         color: #333; /* 글자색을 어두운 회색으로 변경 */
         border: 1px solid #333; /* 테두리를 어두운 회색으로 변경 */
         padding: 10px 20px;
         margin-right: 10px;
         border-radius: 5px;
         cursor: pointer;
         transition: background-color 0.3s ease;
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
      <!-- product section -->
       <!-- jQuery UI CSS -->
        <link rel="stylesheet" href="https://code.jquery.com/ui/1.12.1/themes/base/jquery-ui.css">
        <!-- jQuery -->
        <script src="https://code.jquery.com/jquery-3.6.0.min.js"></script>
        <!-- jQuery UI -->
        <script src="https://code.jquery.com/ui/1.12.1/jquery-ui.js"></script>
         <script>
           $(function() {
              $("#selectedDate").datepicker({
                  dateFormat: "yy-mm-dd", // 날짜 형식 설정
                  onSelect: function(selectedDate) {
                      $("#selectedDate").val(selectedDate); // 선택한 날짜를 텍스트 상자에 입력
                  }
              });
          });
        </script>



      <h4>
         <button onclick="showOrderHistory()">주문내역조회</button>
         <button onclick="showCancellationHistory()">취소/반품/교환내역</button>
         <button onclick="showPastOrders()">과거주문내역</button>
      </h4>

      <div id="orderHistory" style="display: none;">
      
         <div class="btn-group">
            <button onclick="changeDate('오늘')">오늘</button>
            <button onclick="changeDate('1주일')">1주일</button>
            <button onclick="changeDate('1개월')">1개월</button>
            <button onclick="changeDate('3개월')">3개월</button>
            <button onclick="changeDate('6개월')">6개월</button>
            <input type="text" id="selectedDate" value="">
            <span class="separator">~</span> 
            <input type="text" name="birthday" id="datepicker" maxlength="10" />
            <button onclick="search()">조회</button>
         </div>
         <br>
         <div>
           <li>기본적으로 최근 3개월간의 자료가 조회되며, 기간 검색시 주문처리완료 후 36개월 이내의 주문내역을 조회하실 수 있습니다.</li> 
           <li>완료 후 36개월 이상 경과한 주문은 [과거주문내역]에서 확인할 수 있습니다.</li>
           <li> 주문번호를 클릭하시면 해당 주문에 대한 상세내역을 확인하실 수 있습니다.</li>
           <li>  취소/교환/반품 신청은 배송완료일 기준 7일까지 가능합니다.</li>
         </div>
         
      </div>
   

      <div id="cancellationHistory" style="display: none;">
         <div class="btn-group">
            <button onclick="changeDate('오늘')">오늘</button>
            <button onclick="changeDate('1주일')">1주일</button>
            <button onclick="changeDate('1개월')">1개월</button>
            <button onclick="changeDate('3개월')">3개월</button>
            <button onclick="changeDate('6개월')">6개월</button>
            <input type="text" id="selectedDate2"  id="datepicker" value="">
            <span class="separator">~</span> 
            <input type="text" name="birthday" id="datepicker" maxlength="10" />
            <button onclick="search()">조회</button>
         </div>
         <div>
            <li>기본적으로 최근 3개월간의 자료가 조회되며, 기간 검색시 주문처리완료 후 36개월 이내의 주문내역을 조회하실 수 있습니다.</li>
            <li>완료 후 36개월 이상 경과한 주문은 [과거주문내역]에서 확인할 수 있습니다.</li>
            <li>주문번호를 클릭하시면 해당 주문에 대한 상세내역을 확인하실 수 있습니다.</li>

         </div>
      </div>


      <div id="pastOrders" style="display: none;">
         <div class="btn-group">
            <input type="text" id="datepicker" maxlength="10" />
            <span class="separator">년</span> 
            <button onclick="search()">조회</button>
         </div>
         <div>
            <li>주문처리완료 후 36개월 이내의 최근 주문건은 주문내역조회 탭에서 확인할 수 있습니다..</li>
            <li>상품구매금액은 주문 당시의 판매가와 옵션추가금액의 합(부가세 포함)에 수량이 반영된 값입니다.</li>
         </div>
      </div>

 

      <h2>주문 상품 정보</h2>
      
      <table>
          <thead>
              <tr>
                  <th>주문번호</th>
                  <th>이미지</th>
                  <th>상품정보</th>
                  <th>수량</th>
                  <th>상품구매금액</th>
                  <th>주문처리상태</th>
                  <th>취소/교환/반품</th>
              </tr>
          </thead>
          <tbody>
              <tr>
                  <td>주문번호</td>
                  <td><img src="이미지주소" alt="상품이미지"></td>
                  <td>상품정보</td>
                  <td>수량</td>
                  <td>상품구매금액</td>
                  <td>주문처리상태</td>
                  <td>취소/교환/반품</td>
              </tr>
          </tbody>
      </table>
      
      <!-- 페이징 -->
      <div class="pagination">
          <a href="#">&laquo; 이전</a>
          <a href="#" class="active">1</a>
          <a href="#">2</a>
          <a href="#">3</a>
          <a href="#">4</a>
          <a href="#">5</a>
          <a href="#">다음 &raquo;</a>
      </div>

   
      
      </body>
      <!-- end product section -->
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