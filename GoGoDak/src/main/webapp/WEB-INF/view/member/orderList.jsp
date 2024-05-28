<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%
    String ctxPath = request.getContextPath();
%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<jsp:include page="../header.jsp" /> 
<link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css">
<script src="https://code.jquery.com/jquery-3.6.0.min.js"></script>
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/js/bootstrap.bundle.min.js"></script>

<script>
    $(document).ready(function(){
        $('#reviewModal').on('show.bs.modal', function (event) {
            var button = $(event.relatedTarget); // Button that triggered the modal
            var productName = button.data('productname'); // Extract info from data-* attributes
            var modal = $(this);
            modal.find('#product-name').text(productName);
        });

        window.submitReview = function() {
            // 여기에 리뷰 제출 로직을 추가합니다.
            alert('리뷰가 제출되었습니다!');
            $('#reviewModal').modal('hide');
        }
    });
</script>
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
    .star-rating {
        font-size: 24px;
        display: flex;
        flex-direction: row-reverse;
        justify-content: center;
        color: gray;
        cursor: pointer;
    }
    .star-rating input {
        display: none;
    }
    .star-rating label {
        cursor: pointer;
        width: 50px;
        height: 50px;
        position: relative;
        transition: color 0.2s;
    }
    .star-rating input:checked ~ label {
        color: #ffd700;
    }
    .star-rating label:hover,
    .star-rating label:hover ~ label {
        color: #ffdd00;
    }
    .product-link {
        color: black;
        text-decoration: none;
    }
    .product-link:hover {
        text-decoration: underline;
    }
    footer a {
        text-decoration: none;
    }
#reviewModal > div > div > div {
    	
    	  text-align: center;
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
                            <td>
                                <c:forEach var="product" items="${orderVO.productList}">
                                    <a class="product-link" href="#" data-bs-toggle="modal" data-bs-target="#reviewModal" data-productname="${product.product_name}">${product.product_name}</a><br>
                                </c:forEach>
                            </td>
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

<!-- Review Modal -->
<div class="modal fade" id="reviewModal" tabindex="-1" aria-labelledby="reviewModalLabel" aria-hidden="true">
    <div class="modal-dialog modal-dialog-centered">
        <div class="modal-content">
            <div class="" style="text-align: center; position: relative;">
                <h4 class="modal-title" id="reviewModalLabel" >리뷰 작성</h4>
                <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close" style="position: absolute; right: 5px; top: 3px;"></button>
            </div>
            
            <div class="modal-body">
                <h3 class="mt-3" id="product-name"></h3>
                <div class="star-rating">
                    <input type="radio" id="star5" name="rating" value="5"><label for="star5">★</label>
                    <input type="radio" id="star4" name="rating" value="4"><label for="star4">★</label>
                    <input type="radio" id="star3" name="rating" value="3"><label for="star3">★</label>
                    <input type="radio" id="star2" name="rating" value="2"><label for="star2">★</label>
                    <input type="radio" id="star1" name="rating" value="1"><label for="star1">★</label>
                </div>
                <textarea placeholder="리뷰를 작성하세요..." class="form-control mt-3"></textarea>
            </div>
            <div class="modal-footer">
                <button type="button" class="btn btn-primary" onclick="submitReview()">등록</button>
            </div>
        </div>
    </div>
</div>

<jsp:include page="../footer.jsp" />