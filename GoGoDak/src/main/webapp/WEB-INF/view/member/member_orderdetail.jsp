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
    body {
            font-family: Arial, sans-serif;
            margin: 0;
            padding: 0;
            background-color: #f5f5f5;
        }
        .container {
            margin: 20px auto;
            padding: 20px;
            border-radius: 10px;
            background-color: #fff;
            box-shadow: 0px 0px 10px rgba(0, 0, 0, 0.1);
            overflow-x: auto;
            width: 80%; /* 너비를 조정하여 좌우 여백을 설정합니다. */
            margin-left: 10%; /* 좌우 여백을 설정합니다. */
            margin-right: 10%; /* 좌우 여백을 설정합니다. */
        }
        h2 {
            font-size: 22px;
            font-weight: bold;
            margin-bottom: 20px;
            color: #333;
            text-align: center;
        }
        h3 {
            font-size: 16px;
            font-weight: bold;
            margin-top: 20px;
            margin-bottom: 10px;
            color: #555;
        }
        table {
            width: 100%;
            border-collapse: collapse;
            margin-bottom: 20px;
        }
        th, td {
            border: none;
            padding: 10px;
            font-size: 14px;
            color: #666;
            text-align: left;
        }
        th {
            background-color: #f2f2f2;
            font-weight: bold;
            color: #444;
        }
        .product-image {
            max-width: 80px;
            height: auto;
        }
        .small {
            font-size: 10px;
        }
        .btn-container {
            text-align: center;
            margin-top: 20px;
        }
        .btn {
            padding: 10px 20px;
            background-color: #4CAF50;
            color: white;
            border: none;
            border-radius: 5px;
            cursor: pointer;
            transition: background-color 0.3s ease;
        }
        .btn:hover {
            background-color: #45a049;
        }
        .small-info {
            font-size: 12px;
            color: #888;
            text-align: left;
            margin-bottom: 10px;
        }
        .info-table th {
            font-size: 14px;
            background-color: #f2f2f2;
            color: #444;
            padding: 12px;
            text-align: left;
        }
        .info-table td {
            font-size: 14px;
            color: #666;
            padding: 12px;
            text-align: left;
        }
        .info-table tr:last-child td {
            border-bottom: none;
        }
       
        .payment-info-left {
            flex: 70%;
            background-color: #fff;
        }
        .payment-info-right {
            flex: 30%;
            background-color: #f2f2f2;
        }
      
        .info-table .border-bottom-none td {
            border-bottom: none;
        }
   
         .product-description {
            display: flex;
            align-items: center;
        }
        .option-box {
            background-color: gray;
            color: white;
            padding: 4px 8px;
            border-radius: 4px;
            margin-left: 10px;
        }
        .product-info {
            display: flex;
            align-items: center;
        }
        .product-info a {
            margin-left: 10px;
        }
</style>
      


   <script>
       function goBack() {
           window.history.back();
       }
   </script>


   
<jsp:include page="../header.jsp"></jsp:include>

   <div class="container">
       <h2>주문배송 조회</h2>
       
       <div class="small-info">
           <span class="small">주문일자: </span>2024-05-15
           <span class="small">주문번호: </span>123456
       </div>
       
       <h3>상품 정보</h3>
       <table>
           <tr>
               <th>상품</th>
               <th>수량</th>
               <th>판매가</th>
               <th>할인</th>
               <th>할인 적용금액</th>
           </tr>
           <tr>
               <td class="product-info"> 
                   <a href="#"><img class="product-image" src="images/p3.png" alt="" ></a>
                   <div class="product-description">
                       <a href="#">[바르닭X셰프] 소스 닭다리살 3종 세트 6/12/18/24팩</a>
                       <div class="option-box">12팩 선택(+4000원)</div>
                   </div>
               </td>
               <td>1</td>
               <td>10,000원</td>
               <td>10%</td>
               <td>1,000원</td>
           </tr>
       </table>
       
       <h3>배송지 정보</h3>
       <table class="info-table">
           <tr>
               <th>배송지명</th>
               <td>집</td>
           </tr>
           <tr>
               <th>수령인</th>
               <td>서한솔</td>
           </tr>
           <tr>
               <th>배송지 주소</th>
               <td>서울시 마포구</td>
           </tr>
           <tr>
               <th>연락처</th>
               <td>010-1234-5678</td>
           </tr>
           <tr>
               <th>배송 메모</th>
               <td>부재 시 문 앞에 놔주세요.</td>
           </tr>
           <tr>
               <th>주문자 정보</th>
               <td>서한솔</td>
           </tr>
       </table>
       
       <h3>결제 정보</h3>
       <div style="display: flex;">
           <div class="payment-info-left" style="background-color:#FBFBEF;">
               <table class="info-table">
                   <tr>
                       <th colspan="2" style="background-color:#FBFBEF;" >결제 정보</th>
                   </tr>
                   <tr>
                       <td>카카오페이</td>
                   </tr>
               </table>
           </div>
           <div class="payment-info-right" >
               <table class="info-table">
                   <tr>
                       <th colspan="2" >결제 상세 정보</th>
                   </tr>
                   <tr>
                       <th>상품금액</th>
                       <td>10,000원</td>
                   </tr>
                   <tr>
                       <th>할인금액</th>
                       <td>1,000원</td>
                   </tr>
                   <tr>
                       <th>포인트 사용</th>
                       <td>0원</td>
                   </tr>
                   <tr>
                       <th>최종 결제금액</th>
                       <td>9,000원</td>
                   </tr>
               </table>
           </div>
       </div>

       <div class="btn-container">
           <button class="btn" onclick="goBack()">이전 페이지로 돌아가기</button>
       </div>
   </div>


<jsp:include page="../footer.jsp"></jsp:include>  