<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<jsp:include page="../header.jsp" />

<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>

<%
String contextPath = request.getContextPath();
%>

<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css" rel="stylesheet">
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/js/bootstrap.bundle.min.js"></script>

<style>
h2 {
	font-family: Arial, sans-serif;
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

.review-pic {
	height: 200px;
}

.productPic {
	border: solid 0px red;
	max-width: 60%;
	margin: 0 auto;
	display: flex;
	justify-content: center;
}

.like-button {
	display: inline-flex;
	align-items: center;
	padding: 10px 20px;
	font-size: 16px;
	font-weight: bold;
	color: #fff;
	border: none;
	border-radius: 5px;
	cursor: pointer;
	transition: background-color 0.3s;
}

.like-button:hover {
	background-color: #cc0000;
}

.like-button svg {
	fill: #fff;
	width: 20px;
	height: 20px;
	margin-right: 8px;
}

.like-button.liked {
	background-color: red;
}

.like-button.not-liked {
	background-color: gray;
}

.like-button .like-count {
	margin-left: 5px;
	font-size: 16px;
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
    

function likePost() {
    const productSeq = document.querySelector('input[name="product_seq"]').value;
    //console.log('Product Seq:', productSeq);
    const likeButton = document.querySelector('.like-button');
    const likeCount = likeButton.querySelector('.like-count');
    const isLiked = likeButton.classList.contains('liked');
    const currentCount = parseInt(likeCount.textContent, 10);
    
    if(!isLiked) {
	    $.ajax({
	        url: '<%=contextPath%>/product/like.dk',
	        type: 'post',
	        data:{"product_seq": productSeq},
	        dataType: 'json', 
	        success: function(response) {
	            if (response.success) {
	                likeButton.setAttribute('data-liked', 'true');
	                likeCount.textContent = currentCount + 1;
	                likeButton.classList.remove('not-liked');
	                likeButton.classList.add('liked');
	                
	            } else {
					alert(response.message);
	            }
	        },
	        error: function(xhr, status, error) {
	        	console.log(error)
	            alert('좋아요 실패 ' + error);
	        }
	    });
    }
    else {
	    $.ajax({
	        url: '<%=contextPath%>/product/like.dk',
	        type: 'post',
	        data:{"product_seq": productSeq},
	        dataType: 'json', 
	        success: function(response) {
	            if (response.success) {
	                likeButton.setAttribute('data-liked', 'false');
	                likeCount.textContent = currentCount - 1;
	                likeButton.classList.remove('liked');
	                likeButton.classList.add('not-liked');
	            } else {
	            	alert(response.message);
	            }
	        },
	        error: function(xhr, status, error) {
	        	console.log(error)
	            alert('좋아요 실패 ' + error);
	        }
	    });
    	
    	
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
						<img src="<%= contextPath %>/images/product/${requestScope.product.main_pic}" alt="Product Name" style="width: 100%;">
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
											<span style="color: red"> <fmt:formatNumber value="${product.base_price - (product.base_price * product.discount_amount / 100)}" type="currency" currencySymbol="" groupingUsed="true" />원
											</span>

										</c:when>
										<c:when test="${product.discount_type == 'amount'}">
											<span style="text-decoration: line-through;"><fmt:formatNumber value="${product.base_price}" type="currency" currencySymbol="" groupingUsed="true" />원</span>
											<span style="color: red"> <fmt:formatNumber value="${product.base_price - product.discount_amount}" type="currency" currencySymbol="" groupingUsed="true" />원

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

							<td class="m_item"><span style="font-size: 13px; color: #555555; font-weight: bold;"> <span class="delv_price_B"> <input id="delivery_cost_prepaid" name="delivery_cost_prepaid" value="P" type="hidden"> <strong>전상품 배송비 무료</strong>
								</span>
							</span></td> <br> <br>
							<tr rel="상품 설명" class=" xans-record-">
								<th scope="row"><span style="font-size: 18px; color: #555555;">상품 설명: </span></th>
								<br>
								<td class="m_item"><span style="font-size: 18px; color: #555555;"><font color="#1c95fd">${product.description}</font><br>
							</tr>
							<br>


							<div class="product_review">

								<p>
									${equestScope.scoreAvg}
									<c:if test="${not empty requestScope.scoreAvg}">
										<c:forEach var="i" begin="1" end="${requestScope.scoreAvg}">
									    ★
										</c:forEach>

										<c:forEach var="i" begin="${requestScope.scoreAvg+ 1}" end="5">
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

							<c:if test="${not empty requestScope.detailList}">
								<div class="form-group">
									<label for="productOptions">Product Options:</label>
									 <select class="form-control" id="productOptions" name="product_detail_seq">
										<c:forEach var="option" items="${requestScope.detailList}">
											<option value="${option.product_detail_seq}">${option.detail_name}</option>
										</c:forEach>
									</select>
								</div>
							</c:if>

							<div class="quantity">
								<button type="button" onclick="decreaseQuantity()">-</button>
								<input type="text" value="1" id="quantity" name="quantity">
								<button type="button" onclick="increaseQuantity()">+</button>
							</div>


							<c:if test="${requestScope.isLiked}">
								<button class="btn like-button liked" onclick="likePost()">
									<svg xmlns="http://www.w3.org/2000/svg" viewBox="0 0 24 24">
					                <path d="M12 21.35l-1.45-1.32C5.4 15.36 2 12.28 2 8.5 2 5.42 4.42 3 7.5 3c1.74 0 3.41.81 4.5 2.09C13.09 3.81 14.76 3 16.5 3 19.58 3 22 5.42 22 8.5c0 3.78-3.4 6.86-8.55 11.54L12 21.35z" />
					            </svg>
									<span class="like-count">${requestScope.likeCount}</span>
								</button>
							</c:if>
							<c:if test="${!requestScope.isLiked}">
								<button class="btn like-button not-liked" onclick="likePost()">
									<svg xmlns="http://www.w3.org/2000/svg" viewBox="0 0 24 24">
					                <path d="M12 21.35l-1.45-1.32C5.4 15.36 2 12.28 2 8.5 2 5.42 4.42 3 7.5 3c1.74 0 3.41.81 4.5 2.09C13.09 3.81 14.76 3 16.5 3 19.58 3 22 5.42 22 8.5c0 3.78-3.4 6.86-8.55 11.54L12 21.35z" />
					            </svg>
									<span class="like-count">${requestScope.likeCount}</span>
								</button>
							</c:if>

							<button class="btn btn-dark" onclick="goToCart(${requestScope.product.product_seq})">바로 구매하기</button>


							<button class="btn btn-secondary" onclick="addToCart(${requestScope.product.product_seq})">장바구니 넣기</button>

							<c:if test="${not empty sessionScope.loginuser and sessionScope.loginuser.id == 'admin'}">
								<button type="button" class="btn btn-light" onclick="goEdit()" data-liked="false">수정</button>
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
				<li class="nav-item"><a class="nav-link active" id="details-tab" data-toggle="tab" href="#details" role="tab" aria-controls="details" aria-selected="true">Product Details</a></li>
				<li class="nav-item"><a class="nav-link" id="reviews-tab" data-toggle="tab" href="#reviews" role="tab" aria-controls="reviews" aria-selected="false">Reviews</a></li>
			</ul>
			<div class="tab-content" id="productTabContent">
				<div class="tab-pane fade show active" id="details" role="tabpanel">
					<!-- Detailed information about the product -->
					<p>${requestScope.product.description}</p>
					<div class="productPic">
						<!-- Inserted Image -->
						<img src="<%= contextPath %>/images/product/${requestScope.product.description_pic}" style="width: 100%; margin-top: 20px;">
					</div>
				</div>
				<div class="tab-pane fade" id="reviews" role="tabpanel" aria-labelledby="reviews-tab">
					<!-- Customer reviews content -->

					<section class="reviews">


						<c:forEach var="review" items="${requestScope.reviewList}">
							<div class="review">
								<div class="review-header">
									<div style="font-weight: bold">${review.id}</div>
									<span class="review-date">${review.ragisterdate}</span>
								</div>
								<div class="review-rating">

									<c:if test="${not empty review.star}">
										<c:forEach var="i" begin="1" end="${review.star}">
									    ★
									</c:forEach>
										<c:forEach var="i" begin="${review.star + 1}" end="5">
									    ☆
									</c:forEach>
									</c:if>

									<c:if test="${empty review.star}">
									평가없음
                            	</c:if>
								</div>
								<c:if test="${not empty review.pic}">
									<img class="review-pic" src="<%=contextPath %>/images/review/${review.pic}">
								</c:if>


								<p class="review-content">${review.content}</p>
							</div>
							<hr />
						</c:forEach>

						<!-- 추가 리뷰 -->
					</section>

				</div>
			</div>
		</div>

	</section>
	<form name="productRegisterEdit" method="post" enctype="multipart/form-data">
		<input type="hidden" name="fk_manufacturer_seq" value="${requestScope.product.fk_manufacturer_seq}"> <input type="hidden" name="product_seq" value="${requestScope.product.product_seq}"> <input type="hidden" name="product_name" value="${requestScope.product.product_name}">
		<%--int로 형변환 과정 --%>
		<c:set var="basePrice" value="${requestScope.product.base_price}" />
		<fmt:formatNumber value="${basePrice}" pattern="########0" var="formattedBasePrice" />
		<input type="hidden" name="base_price" value="${formattedBasePrice}" />
		<%--int로 형변환 과정 --%>
		<c:set var="discountAmount" value="${requestScope.product.discount_amount}" />
		<fmt:formatNumber value="${discountAmount}" pattern="########0" var="formattedDiscountAmount" />
		<input type="hidden" name="discount_amount" value="${formattedDiscountAmount}" /> <input type="hidden" name="stock" value="${requestScope.product.stock}"> <input type="hidden" name="description" value="${requestScope.product.description}"> <input type="hidden" name="discount_type" value="${requestScope.product.discount_type}"> <input type="hidden" name="discount_amount" value="${requestScope.product.discount_amount}"> <input type="hidden" name="product_type" value="${requestScope.product.product_type}">
	</form>



	<!-- end why section -->

	<!-- footer section -->

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
        
        var selectedOptionElement = document.getElementById('productOptions');
        
        let cartJson;
        if(selectedOptionElement) {
        	cartJson = {
                    "cart": [
                        {
                            "product_seq": product_seq,
                            "quantity": currentValue,
                            "product_detail_seq": selectedOptionElement.value
                        }
                    ]
                }
        }
        else {
        	cartJson = {
                    "cart": [
                        {
                            "product_seq": product_seq,
                            "quantity": currentValue
                        }
                    ]
                }
        }

        
        $.ajax({
            url: '<%=contextPath%>/member/cart.dk',
            type: 'post',
            contentType: 'application/json; charset=utf-8',
            data: JSON.stringify(cartJson),
            dataType: 'json',
            success: function(response) {
                if (response.success) {
                    alert('카트에 담김');
                    $("span.bg-danger.text-white.text-center").text(response.size);
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
        
        var selectedOptionElement = document.getElementById('productOptions');
        
        let cartJson;
        if(selectedOptionElement) {
        	cartJson = {
                    "cart": [
                        {
                            "product_seq": product_seq,
                            "quantity": currentValue,
                            "product_detail_seq": selectedOptionElement.value
                        }
                    ]
                }
        }
        else {
        	cartJson = {
                    "cart": [
                        {
                            "product_seq": product_seq,
                            "quantity": currentValue
                        }
                    ]
                }
        }
    	
        $.ajax({
            url: '<%=contextPath%>/member/cart.dk',
            type: 'post',
            dataType: 'json',
            contentType: 'application/json; charset=utf-8',   
            data: JSON.stringify(cartJson),
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

	<jsp:include page="../footer.jsp"></jsp:include>