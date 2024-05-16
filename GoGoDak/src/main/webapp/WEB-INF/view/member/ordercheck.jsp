<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
    String ctxPath = request.getContextPath();
    //    /GoGoDak
%>
   
<!-- jQuery UI CSS -->
<link rel="stylesheet" href="https://code.jquery.com/ui/1.12.1/themes/base/jquery-ui.css">
<!-- jQuery UI -->
<script src="https://code.jquery.com/ui/1.12.1/jquery-ui.js"></script>
 <style type="text/css">
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
    
    select {
     font-size: 20px; 
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
	
    $(function() {
        $("#selectedDate").datepicker({
            dateFormat: "yy-mm-dd", // 날짜 형식 설정
            onSelect: function(selectedDate) {
                $("#selectedDate").val(selectedDate); // 선택한 날짜를 텍스트 상자에 입력
            }
        });
    });
</script>
   
<jsp:include page="../header.jsp"></jsp:include>

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
           <li>주문번호를 클릭하시면 해당 주문에 대한 상세내역을 확인하실 수 있습니다.</li>
           <li>취소/교환/반품 신청은 배송완료일 기준 7일까지 가능합니다.</li>
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
				<select> <!-- 설렉트는 한개만 선택가능 -->
					<option selected>2023</option>
					<option>2022</option>
					<option>2021</option> 
					<option>2020</option>
					<option>2019</option>
				</select>
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

<jsp:include page="../footer.jsp"></jsp:include>  