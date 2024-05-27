<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%
    String ctxPath = request.getContextPath();
%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<jsp:include page="../header.jsp" /> 
<link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.6.2/css/bootstrap.min.css">
<script src="https://code.jquery.com/jquery-3.6.0.min.js"></script>
<script src="https://stackpath.bootstrapcdn.com/bootstrap/4.6.2/js/bootstrap.min.js"></script>

<style>
    .titleArea {
        text-align: center;
        margin: 20px 0;
    }
    .titleArea h2 {
        font-family: Arial, Helvetica, sans-serif;
        font-size: 24pt;
        font-weight: bold;
        color: #000;
        margin: 20px 0;
        display: inline-block;
    }
    .search-form {
        display: flex;
        justify-content: center;
        margin-bottom: 20px;
    }
    .search-form select,
    .search-form input,
    .search-form button {
        margin: 0 5px;
        padding: 7px;
        font-size: 13pt;
        width: auto;
    }
    .search-form button {
        padding: 5px 10px;
    }
    .order-table th, .order-table td {
        text-align: center;
        vertical-align: middle;
        padding: 14px;
    }
    .no-orders {
        text-align: center;
        margin: 20px 0;
        font-size: 12pt;
    }
    .order-container {
        margin-top: 32px;
    }
    .table thead th {
        border-bottom: 2px solid #dee2e6;
    }
    .table-bordered th, .table-bordered td {
        border: 1px solid #dee2e6;
    }
    .summary {
        text-align: right;
        padding: 10px;
        background-color: #f9f9f9;
        border-top: 1px solid #dee2e6;
    }

    .summary strong {
        font-size: 14pt;
    }
    .payDetail {
        text-align: right;
        padding: 10px;
        background-color: #f9f9f9;
        border-top: 1px solid #dee2e6;
        font-size: 13pt;
    }
    .payDetail strong {
        font-size: 14pt;
    }
    .payDetail span {
        margin-left: 5px;
    }
    
body > div.container > div.order-container > table > thead > tr {
      font-size: 10pt;
    }
</style>

<div class="container">
    <div class="titleArea">
        <h2>주문내역조회</h2>
    </div>

    <hr>

    <div>
        <ul>
            <li>기본적으로 최근 3개월간의 자료가 조회되며, 기간 검색시 지난 주문내역을 조회하실 수 있습니다.</li>
            <li>주문번호를 클릭하시면 해당 주문에 대한 상세내역을 확인하실 수 있습니다.</li>
            <li>취소/교환/반품 신청은 주문완료일 기준 30일까지만 가능합니다.</li>
        </ul>
    </div>

    <div class="order-container">
        <h4>주문 상품 정보</h4>
        <hr>
        <table class="table table-bordered order-table">
            <thead class="thead-light">
                <tr>
                    <th>주문일자</th>
                    <th style="width: 530px;">상품명</th>
                    <th>총 금액</th>
                    <th>주문처리상태</th>
                </tr>
            </thead>
            <tbody>
                <c:if test="${empty requestScope.orderList}">
                    <tr>
                        <td colspan="5" class="no-orders">주문 내역이 없습니다.</td>
                    </tr>
                </c:if>

                <c:if test="${not empty requestScope.orderList}">
                    <c:forEach var="orderVO" items="${requestScope.orderList}">
                        <tr>
                            <td><fmt:formatDate value="${orderVO.registerday}" pattern="yyyy-MM-dd"/></td>
                            <td><a style="color: black" href="#"><c:out value="${orderVO.allProductNames}" escapeXml="false" /></a></td>
                            <td><fmt:formatNumber value="${orderVO.totalPay}" type="number" pattern="#,###" />원</td>
                            <td>
                                <c:choose>
                                    <c:when test="${orderVO.deliverystatus == 0}">배송 안 됨</c:when>
                                    <c:when test="${orderVO.deliverystatus == 1}">배송 완료</c:when>
                                    <c:when test="${orderVO.deliverystatus == 2}">배송 중</c:when>
                                    <c:otherwise>알 수 없음</c:otherwise>
                                </c:choose>
                            </td>
                        </tr>
                    </c:forEach>
                </c:if>
            </tbody>
        </table>
    </div>
</div>

<jsp:include page="../footer.jsp" />
