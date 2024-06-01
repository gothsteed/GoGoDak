<%@page import="order.model.OrderDao"%>
<%@page import="domain.OrderVO"%>
<%@page import="domain.ProductVO"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
    String ctxPath = request.getContextPath();
	java.util.List<ProductVO> productList = (java.util.List<ProductVO>) request.getAttribute("productList");
	int totalBasePrice = 0;
	if (productList != null) {
	    for (ProductVO product : productList) {
	        totalBasePrice += product.getBase_price() * product.getQuantity();
	    }
	}
	OrderVO order = (OrderVO) request.getAttribute("order");
	
	float discountedAmount = totalBasePrice - order.getTotal_pay();

%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>

<jsp:include page="../header_admin.jsp" />

<!-- jQuery UI CSS -->
<link rel="stylesheet" href="https://code.jquery.com/ui/1.12.1/themes/base/jquery-ui.css">
<!-- jQuery UI -->
<script src="https://code.jquery.com/ui/1.12.1/jquery-ui.js"></script>

<style type="text/css">
    h2 {
        display: block;
        text-align: center;
        font-size: 2em;
        margin-top: 0.67em;
        margin-bottom: 0.67em;
        margin-left: 0;
        margin-right: 0;
        font-weight: bold;
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
    .product-info a {
        margin-left: 10px;
    }
</style>

<script>
    function goBack() {
        window.history.back ();
    }
    
    function changeDeliveryStatus(status) {
        const order_seq = $("input#order_seq").val();
        console.log("Order seq: " + order_seq); // 로그 추가
        console.log("Delivery status: " + status); // 로그 추가

        $.ajax({
            url: '<%=ctxPath%>/admin/ship.dk',
            type: 'post',
            dataType: 'json',
            data: {
                "order_seq": order_seq,
                "deliverystatus": status
            },
            success: function(response) {
                console.log(response); // 응답 로그 추가
                if (response.success) {
                    alert("상태 변경 완료");
                    $("#deliverystatus").text(response.newStatus);
                } else {
                    console.log(response.message);
                    alert('변경 실패: ' + response.message);
                }
            },
            error: function(xhr, status, error) {
                console.log(error);
                alert('변경 실패: ' + error);
            }
        });
    }
</script>


<div class="container">
    <h2>주문배송 조회</h2>

    <h3 style="font-weight: bold;">회원 정보</h3>
    <table>
        <tr>
            <th>아이디</th>
            <td><c:out value="${order.mdto.id}" /></td>
        </tr>
        <tr>
            <th>회원명</th>
            <td><c:out value="${order.mdto.name}" /></td>
        </tr>
    </table>

    <h3 style="font-weight: bold;">상품 정보</h3>
    <table>
        <tr>
            <th>상품이름</th>
            <c:if test="${sessionScope.loginuser.id == 'admin' }">
            	<th>재고</th>
            </c:if>
            
            <th>판매가</th>
    	<th>합계</th>
        </tr>
        <tr>
<%--             <td class="product-info">
                <c:out value="${order.product_name}" />
            </td>
            <td><c:out value="${order.stock}" /></td>
            <td><c:out value="${order.base_price}" />원</td>

            <td><c:out value="${order.discount_amount}" />원</td> --%>
            
            <c:forEach var="product" items="${requestScope.productList}">
		        <tr>
		  			<td class="product-info">
		                <c:out value="${product.product_name}" />
		            </td>
		            
		          	<c:if test="${sessionScope.loginuser.id == 'admin' }">
		            	<td><c:out value="${product.stock}" /></td>
		            </c:if>
		            <td><fmt:formatNumber value="${product.base_price}" type="currency" currencySymbol="" groupingUsed="true" />원</td>
		
		            <td><c:out value="${product.quantity}" />개</td> 
		        </tr>
            </c:forEach>
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
            <td><c:out value="${order.mdto.name}" /></td>
        </tr>
        <tr>
            <th>배송지 주소</th>
            <td>${order.postcode} ${order.address}  ${order.address_detail} ${order.address_extra} </td>
        </tr>
        <tr>
    <th>연락처</th>
    <td>
        <c:choose>
            <c:when test="${not empty order.mdto.tel and fn:length(order.mdto.tel) == 11}">
                ${fn:substring(order.mdto.tel, 0, 3)}-${fn:substring(order.mdto.tel, 3, 7)}-${fn:substring(order.mdto.tel, 7, 11)}
            </c:when>
            <c:otherwise>
                <c:out value="${order.mdto.tel}" />
            </c:otherwise>
        </c:choose>
    </td>
</tr>
        <tr>
            <th>배송 메모</th>
            <td>${order.delivery_message}</td>
        </tr>
        <tr>
            <th>주문자 정보</th>
            <td><c:out value="${order.mdto.name}" /></td>
        </tr>
        <tr>
            <th>배송 상태</th>
            <td id="deliverystatus">
            
            	<c:if test="${order.deliverystatus== 0}">
            		미출고
            	</c:if>
                <c:if test="${order.deliverystatus!= 0}">
            		출고완료
            	</c:if>
            
            </td>
        </tr>
    </table>

    <h3>결제 정보</h3>
    <div style="display: flex;">
        <div class="payment-info-right">
            <table class="info-table">
                <tr>
                    <th colspan="2">결제 상세 정보</th>
                </tr>
<%--                 <tr>
                    <th>상품금액</th>
                    <td><c:out value="${pvo.total_pay}" />원</td>
                </tr>
                <tr>
                    <th>할인금액</th>
                    <td><c:out value="${pvo.discount_amount}" />원</td>
                </tr>
                <tr>
                    <th>포인트 사용</th>
                    <td></td>
                </tr> --%>
                <tr>
                	<th>정가 금액</th>
                    <td><fmt:formatNumber value="<%=totalBasePrice %>" type="currency" currencySymbol="" groupingUsed="true" />원</td>
                    <th>할인액</th>
                    <td style="color:red; font-weight: bold;"><fmt:formatNumber value="<%=discountedAmount %>" type="currency" currencySymbol="" groupingUsed="true" />원</td>
                    <th>최종 결제금액</th>
                    <td style="font-weight: bold;"><fmt:formatNumber value="${order.total_pay}" type="currency" currencySymbol="" groupingUsed="true" />원</td>
                </tr>
            </table>
        </div>
    </div>
    
    <input  id="order_seq" type="hidden" value="${order.order_seq}">
   <div class="btn-container">
   		<c:if test="${sessionScope.loginuser.id == 'admin' }">
  		    <button type="button" class="btn btn-dark" onclick="changeDeliveryStatus(0)">미출고</button>
	        <button type="button" class="btn btn-secondary" onclick="changeDeliveryStatus(1)">출고</button>
	        <button  type="button" class="btn btn-success" onclick="changeDeliveryStatus(2)">배송중</button>
	        <button type="button" class="btn btn-warning" onclick="changeDeliveryStatus(3)">배송완료</button>	
   		</c:if>
        <button type="button" class="btn" onclick="goBack()">이전 페이지로 돌아가기</button>
    </div>
            	
 
  
</div>
</div>