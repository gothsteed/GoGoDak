<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<jsp:include page="../header.jsp" />

<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>

<%
String contextPath = request.getContextPath();
%>

<link
	href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css"
	rel="stylesheet">
<script
	src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/js/bootstrap.bundle.min.js"></script>

<style>
h2 {
	font-family: 'Noto Sans KR', 'Nanum Gothic', 'Malgun Gothic', sans-serif;
	display: block;
	margin: 8px 0 0px;
	color: #1a1a1a;
	font-size: 26px;
	font-weight: 700;
	line-height: 38px;
}

.product_image img {
	max-width: 100%;
	height: auto;
	border: 1px solid #ddd;
	border-radius: 4px;
	padding: 5px;
}

.product_detail h2 {
	color: #333;
	font-size: 24px;
}

.product_price {
	color: green;
	font-size: 20px;
	margin-top: 5px;
}

.product_description {
	font-size: 16px;
	margin-top: 10px;
	color: #666;
}

.product_review p {
	color: #ffa500;
	margin-top: 10px;
}

.purchase_info button {
	padding: 10px 20px;
	margin-right: 10px;
	border: none;
	border-radius: 5px;
	cursor: pointer;
	font-size: 16px;
	margin-top: 20px;
}

.btn-primary {
	background-color: #007bff;
	color: white;
}

.btn-success {
	background-color: #28a745;
	color: white;
}

.btn-primary:hover, .btn-success:hover {
	opacity: 0.8;
}

.tab-content>.tab-pane {
	padding: 20px;
	border-top: 1px solid #dee2e6;
}

.product_tabs {
	margin: 5% 10%;
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
	color: gold;
}

.star-rating label:hover, .star-rating label:hover ~ label, .star-rating input:checked 
	~ label {
	color: gold;
}

textarea {
	width: 100%;
	height: 100px;
	margin: 10px 0;
}

.file-upload {
	margin: 10px 0;
}

.consent {
	margin: 10px 0;
}

.secondary-button {
	background-color: gray;
	color: white;
}

.flex-col {
	display: flex;
	flex-direction: column;
}

.write-wrap {
	padding-top: 20px;
	margin-top: 40px;
	border-top: 1px solid #f2f2f2;
}

.textarea-wrap {
	width: calc(100% - 2px);
	min-height: 150px;
	padding: 10px;
	border-radius: 10px;
	border: 1px solid white;
	color: #292929;
	background-color: #f2f2f2;
	resize: none;
	outline: none;
	overflow-y: auto;
}

.subtitle-wrap {
	margin-top: 10px;
	text-align: center;
	font-size: 0.875rem;
}

.subtitle {
	color: #292929;
	font-weight: 600;
}

body>section.why_section.layout_padding>div.container>div>div:nth-child(2)>div>h2>p>b
	{
	color: purple;
}

body>section.why_section.layout_padding>div.container>div>div:nth-child(2)>div>h2
	{
	font-size: 30px;
}

body>section.why_section.layout_padding>div.container>div>div:nth-child(2)>div>div.detail
	{
	font-size: 20px;
}

#reviewModal .modal-footer {
	display: flex;
	justify-content: center;
}

#reviewModal .modal-footer>button {
	margin: auto;
}

.modal-body {
	text-align: center;
	margin: auto;
}

.modal-body img {
	width: 100px;
	height: auto;
	display: block;
	margin: 0 auto 20px;
}

.modal-body h3 {
	text-align: center;
	margin-bottom: 20px;
}

.modal-footer {
	display: flex;
	justify-content: center;
}

.product_review {
	font-size: 18px; /* Increase the font size */
}

.product_review p {
	color: #ffa500;
}
</style>
<script>




function goEdit() {
	const frm = document.productRegisterEdit;
	frm.action = "<%=contextPath%>/admin/productRegisterEdit.dk";
    frm.method = "post";
    frm.submit();

}


function goDelete() {
    if (confirm("정말로 이 상품을 삭제하시겠습니까?")) {
        const frm = document.productRegisterEdit;
        frm.action = "<%=contextPath%>/admin/productRegisterEditDelete.dk";
        frm.method = "post";
        frm.submit();
		}
    }



</script>
</head>
<body>
	<div class="container-fluid">
		<div class="row">
			<div class="col-md-12"></div>
		</div>
	</div>

	<!-- why section -->
	<section class="why_section layout_padding">
		<div class="container">
			<div class="row">
			
				<div class="col-md-6">
					<div class="product_image">
						<img
							src="<%= contextPath %>/images/product/${requestScope.product.main_pic}"
							alt="Product Name" style="width: 100%;">
					</div>
				</div>
			
				<div class="col-md-6">
					<div class="product_detail">
						<h2>
							<p class="ttt"></p>
							<p class="eee">${requestScope.product.product_name}</p>
						</h2>
						<br>
						<p class="product_price">
							<c:choose>
								<c:when test="${not empty product.discount_type}">
									<c:choose>
										<c:when test="${product.discount_type == 'percent'}">
                                          <span style="text-decoration: line-through;"><fmt:formatNumber value="${product.base_price}" type="currency" currencySymbol="" groupingUsed="true" />원</span>
                                          <span style="color: red">
                                          	 <fmt:formatNumber value="${product.base_price - (product.base_price * product.discount_amount / 100)}" type="currency" currencySymbol="" groupingUsed="true" />원
                                          </span>
											
										</c:when>
										<c:when test="${product.discount_type == 'amount'}">
                            			  <span style="text-decoration: line-through;"><fmt:formatNumber value="${product.base_price}" type="currency" currencySymbol="" groupingUsed="true" />원</span>
                                          <span style="color: red">
                                          	<fmt:formatNumber value="${product.base_price - product.discount_amount}" type="currency" currencySymbol="" groupingUsed="true" />원
                
                                          </span>
										</c:when>
									</c:choose>
								</c:when>
								<c:otherwise>
	                                  <fmt:formatNumber value="${product.base_price }" type="currency" currencySymbol="" groupingUsed="true" />원
	                              </c:otherwise>
							</c:choose>
						</p>

						<div class="detail">

							<td class="m_item"><span
								style="font-size: 13px; color: #555555; font-weight: bold;">
									<span class="delv_price_B"> <input
										id="delivery_cost_prepaid" name="delivery_cost_prepaid"
										value="P" type="hidden"> <strong>전상품 배송비 무료</strong>
								</span>
							</span></td> <br> <br>
							<tr rel="상품 설명" class=" xans-record-">
								<th scope="row"><span
									style="font-size: 18px; color: #555555;">상품 설명: </span></th>
								<br>
								<td class="m_item"><span
									style="font-size: 18px; color: #555555;"><font
										color="#1c95fd">${product.description}</font><br>
							</tr>
							<br>


							<div class="product_review">

								<p>

									<c:if test="${not empty requestScope.scoreAvg}">
										<c:forEach var="i" begin="0" end="${requestScope.scoreAvg-1}">
									    ★
									</c:forEach>
										<c:forEach var="i" begin="0"
											end="${5 - requestScope.scoreAvg - 1}">
									    ☆
									</c:forEach>
									</c:if>

									<c:if test="${empty requestScope.scoreAvg}">
									평가없음
                            	</c:if>

								</p>
							</div>
						</div>
						<br>
						<div class="purchase_info">
							<div class="quantity">
								<button type="button" onclick="decreaseQuantity()">-</button>
								<input type="text" value="1" id="quantity" name="quantity">
								<button type="button" onclick="increaseQuantity()">+</button>
							</div>


	
							<button class="btn btn-dark"
								onclick="goToCart(${requestScope.product.product_seq})">바로
								구매하기</button>


							<button class="btn btn-secondary"
								onclick="addToCart(${requestScope.product.product_seq})">장바구니
								넣기</button>
								  
							<c:if test="${not empty sessionScope.loginuser and sessionScope.loginuser.id == 'admin'}">	
							  <button type="button" class="btn btn-light" onclick="goEdit()">수정</button>
							</c:if>
							<c:if test="${not empty sessionScope.loginuser and sessionScope.loginuser.id == 'admin'}">	
								<button type="button" class="btn btn-light" onclick="goDelete()">삭제</button>
								
							</c:if>
						
					
						</div>
					</div>
				</div>
			</div>
		</div>

		<div class="product_tabs">
			<ul class="nav nav-tabs" id="productTab" role="tablist">
				<li class="nav-item"><a class="nav-link active"
					id="details-tab" data-toggle="tab" href="#details" role="tab"
					aria-controls="details" aria-selected="true">Product Details</a></li>
				<li class="nav-item"><a class="nav-link" id="reviews-tab"
					data-toggle="tab" href="#reviews" role="tab"
					aria-controls="reviews" aria-selected="false">Reviews</a></li>
			</ul>
			<div class="tab-content" id="productTabContent">
				<div class="tab-pane fade show active" id="details" role="tabpanel"
					aria-labelledby="details-tab">
					<!-- Detailed information about the product -->
					<p>${requestScope.product.description}</p>
					<div>
						<!-- Inserted Image -->
						<img
							src="<%= contextPath %>/images/product/${requestScope.product.description_pic}"
							style="width: 100%; margin-top: 20px;">
					</div>
				</div>
				<div class="tab-pane fade" id="reviews" role="tabpanel"
					aria-labelledby="reviews-tab">
					<button type="button" class="btn btn-warning"
						data-bs-toggle="modal" data-bs-target="#reviewModal">리뷰
						등록하기</button>
					<hr>
					<!-- Customer reviews content -->

					<section class="reviews">


						<c:forEach var="review" items="${requestScope.reviewList}">
							<div class="review">
								<div class="review-header">
									<h2>${review.id}</h2>
									<span class="review-date">${review.ragisterdate}</span>
								</div>
								<p class="review-content">${review.content}</p>
								<div class="review-rating">

									<c:if test="${not empty review.star}">
										<c:forEach var="i" begin="0" end="${review.star-1}">
									    ★
									</c:forEach>
										<c:forEach var="i" begin="0" end="${5 - review.star - 1}">
									    ☆
									</c:forEach>
									</c:if>

									<c:if test="${empty review.star}">
									평가없음
                            	</c:if>
								</div>
							</div>
						</c:forEach>

						<!-- 추가 리뷰 -->
					</section>






					<!-- Modal -->
					<div class="modal fade" id="reviewModal" tabindex="-1"
						aria-labelledby="reviewModalLabel" aria-hidden="true">
						<div class="modal-dialog modal-dialog-centered">
							<div class="modal-content">
								<div class="modal-header">
									<h5 class="modal-title" id="reviewModalLabel">리뷰 작성</h5>
									<button type="button" class="btn-close" data-bs-dismiss="modal"
										aria-label="Close"></button>
								</div>
								<div class="modal-body">
									<img src="/mnt/data/image.png" alt="Product Image"
										class="img-fluid mt-3">
									<h3 class="mt-3">[버닭X강강술래] 소스품은 닭가슴살 2종 세트 4/8/12</h3>
									<div class="star-rating">
										<input type="radio" id="star5" name="rating" value="5">
										<label for="star5">★</label> <input type="radio" id="star4"
											name="rating" value="4"> <label for="star4">★</label>
										<input type="radio" id="star3" name="rating" value="3">
										<label for="star3">★</label> <input type="radio" id="star2"
											name="rating" value="2"> <label for="star2">★</label>
										<input type="radio" id="star1" name="rating" value="1">
										<label for="star1">★</label>
									</div>
									<textarea placeholder="리뷰를 작성하세요..." class="form-control mt-3"></textarea>
								</div>
								<div class="modal-footer">
									<button type="button" class="btn btn-primary"
										onclick="submitReview()">등록</button>
								</div>
							
							</div>
						</div>
					</div>
				</div>
			</div>
		</div>

	</section>
	<form name="productRegisterEdit" method="post" enctype="multipart/form-data">
       <input type="hidden" name="fk_manufacturer_seq" value="${requestScope.product.fk_manufacturer_seq}">
        <input type="hidden" name="product_seq" value="${requestScope.product.product_seq}">
        <input type="hidden" name="product_name" value="${requestScope.product.product_name}">
        <%--int로 형변환 과정 --%>
        <c:set var="basePrice" value="${requestScope.product.base_price}" />
		<fmt:formatNumber value="${basePrice}" type="number" var="formattedBasePrice" /> 
		<input type="hidden" name="base_price" value="${formattedBasePrice}" /> 
        <%--int로 형변환 과정 --%>
        <c:set var="discountAmount" value="${requestScope.product.discount_amount}" />
		<fmt:formatNumber value="${discountAmount}" type="number" var="formattedDiscountAmount" />
        <input type="hidden" name="discount_amount" value="${formattedDiscountAmount}" />
        
        <input type="hidden" name="stock" value="${requestScope.product.stock}">
        <input type="hidden" name="description" value="${requestScope.product.description}">
        <input type="hidden" name="discount_type" value="${requestScope.product.discount_type}">
        <input type="hidden" name="discount_amount" value="${requestScope.product.discount_amount}">
        <input type="hidden" name="product_type" value="${requestScope.product_type}">
    </form>
	
	
 		
	<!-- end why section -->

	<!-- footer section -->
	<jsp:include page="../footer.jsp"></jsp:include>
	<!-- end footer section -->

	<!-- jQuery -->
	<script src="js/jquery-3.4.1.min.js"></script>
	<!-- popper js -->
	<script src="js/popper.min.js"></script>
	<!-- bootstrap js -->
	<script src="js/bootstrap.js"></script>
	<script>
    function decreaseQuantity() {
        var quantityInput = document.getElementById('quantity');
        var currentValue = parseInt(quantityInput.value);
        if (currentValue >= 1) {
            quantityInput.value = currentValue - 1;
        }
    }

    function increaseQuantity() {
        var quantityInput = document.getElementById('quantity');
        var currentValue = parseInt(quantityInput.value);
        quantityInput.value = currentValue + 1;
    }
	
    function submitReview() {
        alert('리뷰가 제출되었습니다.');
    }
    
    
    function addToCart(product_seq) {
        console.log('<%=contextPath%>/member/cart.dk');

        var quantityInput = document.getElementById('quantity');
        var currentValue = parseInt(quantityInput.value);
        
        $.ajax({
            url: '<%=contextPath%>/member/cart.dk',
            type: 'post',
            contentType: 'application/json; charset=utf-8',
            data: JSON.stringify({
                "cart": [
                    {
                        "product_seq": product_seq,
                        "quantity": currentValue
                    }
                ]
            }),
            dataType: 'json',
            success: function(response) {
                if (response.success) {
                    alert('카트에 담김');
                } else {
                	console.log(response.message)
                    alert('카트 담기 실패A: ' + response.message);
                }
            },
            error: function(xhr, status, error) {
            	console.log(error)
                alert('카트 담기 실패B: ' + error);
            }
        });
    }

    function goToCart(product_seq) {
    	
        console.log('<%=contextPath%>/member/cart.dk');

        var quantityInput = document.getElementById('quantity');
        var currentValue = parseInt(quantityInput.value);
    	
        $.ajax({
            url: '<%=contextPath%>/member/cart.dk',
            type: 'post',
            dataType: 'json',
            contentType: 'application/json; charset=utf-8',   
            data: JSON.stringify({
                "cart": [
                    {
                        "product_seq": product_seq,
                        "quantity": currentValue
                    }
                ]
            }),
            success: function(response) {
                if (response.success) {
                    alert('카트에 담김');
                    window.location.href = '<%=contextPath%>/member/cart.dk';
                } else {
                	console.log(response.message)
                    alert('카트 담기 실패:A ' + response.message);
                }
            },
            error: function(xhr, status, error) {
            	console.log(error)
                alert('카트 담기 실패B: ' + error);
            }
        });
    }





    
</script>
</body>
</html>
