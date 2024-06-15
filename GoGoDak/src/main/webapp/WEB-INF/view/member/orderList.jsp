<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%
String ctxPath = request.getContextPath();
%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>

<jsp:include page="../header.jsp" />
<link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css">
<script src="https://code.jquery.com/jquery-3.6.0.min.js"></script>
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/js/bootstrap.bundle.min.js"></script>

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

.search-form select, .search-form input, .search-form button {
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

.star-rating label:hover, .star-rating label:hover ~ label {
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

#reviewModal>div>div>div {
	text-align: center;
}

.disabled-link {
	color: gray;
	pointer-events: none;
	cursor: default;
}

.center {
	text-align: center; /* Center the text inside the div */
	margin-top: 50px; /* Add top margin */
}

.center nav {
	display: inline-block;
	/* Ensure the nav is treated as an inline-block element */
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
form[name="order_search_frm"] {
	width: 100%;
	margin: 20px auto;
	padding: 20px;
	border: 1px solid #dee2e6;
	border-radius: 5px;
	background-color: #f9f9f9;
	display: flex;
	justify-content: space-between;
	align-items: center;
	flex-wrap: wrap;
}

form[name="order_search_frm"] .form-group {
	margin-bottom: 10px;
	display: flex;
	align-items: center;
}

form[name="order_search_frm"] label {
	font-size: 13pt;
	font-weight: bold;
	margin-right: 10px;
}

form[name="order_search_frm"] input[type="date"] {
	padding: 7px;
	font-size: 13pt;
	border: 1px solid #dee2e6;
	border-radius: 5px;
}

form[name="order_search_frm"] select {
	padding: 7px;
	font-size: 13pt;
	border: 1px solid #dee2e6;
	border-radius: 5px;
}

form[name="order_search_frm"] button {
	padding: 10px 20px;
	font-size: 13pt;
	border: none;
	border-radius: 5px;
	background-color: #007bff;
	color: white;
	cursor: pointer;
	transition: background-color 0.3s;
}

form[name="order_search_frm"] button:hover {
	background-color: #0056b3;
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
		
<form name="order_search_frm">
			<div class="form-group">
				<label for="startDate">시작 날짜:</label>
				<input type="date" id="startDate" name="startDate" required>
			</div>
			
			<div class="form-group">
				<label for="endDate">종료 날짜:</label>
				<input type="date" id="endDate" name="endDate" required>
			</div>
			
			<div class="form-group">
				<label for="sizePerPage">페이지당 주문수:</label>
				<select id="sizePerPage" name="sizePerPage">
					<option value="10">10</option>
					<option value="5">5</option>
					<option value="3">3</option>
				</select>
			</div>
			
			<div class="form-group">
				<button type="button" class="btn btn-primary" onclick="goSearch()">검색</button>
			</div>
		</form>

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
						<tr class="clickable-row" data-orderseq="${orderVO.order_seq}">
							<td><fmt:formatDate value="${orderVO.registerday}" pattern="yyyy-MM-dd" /></td>
							<td><c:forEach var="product" items="${orderVO.productList}">

									<c:choose>
										<c:when test="${orderVO.deliverystatus == 3}">
											<a class="product-link" href="javascript:void(0);" data-productname="${product.product_name}" data-orderseq="${orderVO.order_seq}" data-productseq="${product.product_seq}">${product.product_name}</a>
											<br>
										</c:when>
										<c:otherwise>
											<a class="product-link disabled-link" href="javascript:void(0);" data-productname="${product.product_name}" data-orderseq="${orderVO.order_seq}" data-productseq="${product.product_seq}">${product.product_name}</a>
											<br>
										</c:otherwise>
									</c:choose>

								</c:forEach></td>
							<td><fmt:formatNumber value="${orderVO.totalPay}" type="number" pattern="#,###" />원</td>
							<td><c:choose>
									<c:when test="${orderVO.deliverystatus == 0}">미출고</c:when>
									<c:when test="${orderVO.deliverystatus == 1}">출고완료</c:when>
									<c:when test="${orderVO.deliverystatus == 2}">배송 중</c:when>
									<c:when test="${orderVO.deliverystatus == 3}">배송 완료</c:when>
									<c:otherwise>알 수 없음</c:otherwise>
								</c:choose></td>
						</tr>
					</c:forEach>
				</c:if>
			</tbody>
		</table>
	</div>

	<div id="pageBar" class="center">
		<nav>
			<ul class="pagination">${requestScope.pageBar}
			</ul>
		</nav>
	</div>
</div>

<!-- Review Modal -->
<div class="modal fade" id="reviewModal" tabindex="-1" aria-labelledby="reviewModalLabel" aria-hidden="true">
	<div class="modal-dialog modal-dialog-centered">
		<div class="modal-content">

			<form name="reviewFrm" method="post" enctype="multipart/form-data">
				<input type="hidden" name="order_seq" /> <input type="hidden" name="product_seq" />
				<div class="" style="text-align: center; position: relative;">
					<h4 class="modal-title" id="reviewModalLabel">리뷰 작성</h4>
					<button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close" style="position: absolute; right: 5px; top: 3px;"></button>
				</div>

				<div class="modal-body">
					<h3 class="mt-3" id="product-name"></h3>
					<div class="star-rating">
						<input type="radio" id="star5" name="rating" value="5"><label for="star5">★</label> <input type="radio" id="star4" name="rating" value="4"><label for="star4">★</label> <input type="radio" id="star3" name="rating" value="3"><label for="star3">★</label> <input type="radio" id="star2" name="rating" value="2"><label for="star2">★</label> <input type="radio" id="star1" name="rating" value="1"><label for="star1">★</label>
					</div>
					<input type="text" placeholder="리뷰를 작성하세요..." class="form-control mt-3" id="content" name="content" />

					<div class="board-group">
						<label for="pic">이미지</label>
						<div>
							<input type="file" id="pic" name="pic" accept="image/*" class="form-control">
							<%-- <img src="images/image.jpg" class="img-fluid" />  --%>
						</div>
						<div id="imagePreview"></div>
					</div>
				</div>
			</form>
			<div class="modal-footer">
				<button type="button" class="btn btn-primary" onclick="submitReview()">등록</button>
			</div>
		</div>
	</div>
</div>


<!-- Review update Modal -->
<div class="modal fade" id="reviewUpdateModal" tabindex="-1" aria-labelledby="reviewUpdateModalLabel" aria-hidden="true">
	<div class="modal-dialog modal-dialog-centered">
		<div class="modal-content">

			<form name="reviewUpdateFrm" method="post" enctype="multipart/form-data">
				<input type="hidden" name="review_seq" /> <input type="hidden" name="order_seq" /> <input type="hidden" name="product_seq" />
				<div class="" style="text-align: center; position: relative;">
					<h4 class="modal-title" id="reviewUpdateModalLabel">리뷰 수정</h4>
					<button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close" style="position: absolute; right: 5px; top: 3px;"></button>
				</div>

				<div class="modal-body">
					<h3 class="mt-3" id="product-name"></h3>
					<div class="star-rating">
						<input type="radio" id="star5" name="rating" value="5"><label for="star5">★</label> <input type="radio" id="star4" name="rating" value="4"><label for="star4">★</label> <input type="radio" id="star3" name="rating" value="3"><label for="star3">★</label> <input type="radio" id="star2" name="rating" value="2"><label for="star2">★</label> <input type="radio" id="star1" name="rating" value="1"><label for="star1">★</label>
					</div>
					<input type="text" placeholder="리뷰를 작성하세요..." class="form-control mt-3" id="content" name="content" />

					<div class="board-group">
						<label for="pic">이미지</label>
						<div>
							<input type="file" id="pic" name="pic" accept="image/*" class="form-control">
							<%-- <img src="images/image.jpg" class="img-fluid" />  --%>
						</div>
						<div id="imagePreview"></div>
					</div>
				</div>
			</form>
			<div class="modal-footer">
				<button type="button" class="btn btn-primary" onclick="updateReview()">수정</button>
				<button type="button" class="btn btn-primary" onclick="deleteReview()">리뷰 삭제</button>
			</div>
		</div>
	</div>
</div>





</style>

<script type="text/javascript">

$(document).ready(function() {
	
	const imageInput = document.getElementById('pic');
	const imagePreview = document.getElementById('imagePreview');
	
/*     $('#reviewModal').on('show.bs.modal', function (event) {
        var button = $(event.relatedTarget); // Button that triggered the modal
        var productName = button.data('productname'); // Extract info from data-* attributes
        var orderSeq = button.data('orderseq'); // Extract order_seq
        var productSeq = button.data('productseq'); // Extract product_seq
        var modal = $(this);
        modal.find('#product-name').text(productName);
        modal.find('input[name="order_seq"]').val(orderSeq);
        modal.find('input[name="product_seq"]').val(productSeq);
    }); */
    
    $(document).on("click", ".product-link", (event) => {
    	event.preventDefault()
    	
    	
    	var button = $(event.currentTarget); // Button that triggered the modal
        var productName = button.data('productname'); // Extract info from data-* attributes
        var orderSeq = button.data('orderseq'); // Extract order_seq
        var productSeq = button.data('productseq'); // Extract product_seq
        
        console.log(button)
        console.log(orderSeq);
        console.log(productSeq);
        
        $.ajax({
        	url:"<%=ctxPath%>/member/review/checkWrittenReview.dk",
        	type:"post",
        	data:{
        		"order_seq":orderSeq,
        		"product_seq":productSeq
        	},
        	dataType:"json",
        	success:function(response) {
        		if(response.success) {
        			
        			$('#reviewModal').find('#product-name').text(productName);
	                $('#reviewModal').find('input[name="order_seq"]').val(orderSeq);
	                $('#reviewModal').find('input[name="product_seq"]').val(productSeq);
	
	                // Show the modal
	                $('#reviewModal').modal('show');
        			
        		}
        		else {
        			$('#reviewUpdateModal').find('#product-name').text(productName);
	                $('#reviewUpdateModal').find('input[name="order_seq"]').val(orderSeq);
	                $('#reviewUpdateModal').find('input[name="product_seq"]').val(productSeq);
	                console.log(response.message);
	                console.log(JSON.parse(response.message));
	                
	                const review = JSON.parse(response.message)
	                $('#reviewUpdateModal').find('input[name="review_seq"]').val(review.review_seq);
	                $('#reviewUpdateModal').find('input[name="content"]').val(review.content);
	                $('#reviewUpdateModal').find('input[id="star'+review.star+'"]').prop("checked", true);
	
	                // Show the modal
	                $('#reviewUpdateModal').modal('show');
        		}
        		
        		
        	},
        	error: function(xhr, status, error) {
                console.error("에러 발생:", error);
                alert("서버 요청 중 에러가 발생했습니다. 다시 시도해주세요.");
            }
        })
    })
	
	
	imageInput.addEventListener('change', function(event) {
	    const file = event.target.files[0];
	    if (file) {
	        const reader = new FileReader();
	        reader.onload = function() {
	            const imageUrl = reader.result;
	            const imageElement = document.createElement('img');
	            imageElement.src = imageUrl;
	            imageElement.style.maxWidth = '300px'; // 이미지의 최대 너비 지정
	            imageElement.style.maxHeight = '300px'; // 이미지의 최대 높이 지정
	            imagePreview.innerHTML = ''; // 이미지를 교체하므로 이전 이미지를 삭제
	            imagePreview.appendChild(imageElement);
	        };
	        reader.readAsDataURL(file);
	    }
	    console.log("확인용1");
	});
});

document.addEventListener("DOMContentLoaded", function() {
    var rows = document.querySelectorAll(".clickable-row");
    rows.forEach(function(row) {
        row.addEventListener("click", function() {
            if (event.target.tagName.toLowerCase() === 'a') {
                return; // Prevent the default action if an <a> tag is clicked
            }
            var orderSeq = row.dataset.orderseq;
            var form = document.createElement("form");
            form.method = "POST";
            form.action = "<%=ctxPath%>/member/member_orderdetail.dk";

            var hiddenField = document.createElement("input");
            hiddenField.type = "hidden";
            hiddenField.name = "order_seq";
            hiddenField.value = orderSeq;

            form.appendChild(hiddenField);
            document.body.appendChild(form);
            form.submit();
        });
    });
});


function submitReview() {
    const frm = document.reviewFrm;
    const content = frm.content.value.trim();
    const star =frm.rating.value;
    const pic = frm.pic.files[0];
    console.log(star)
    // Validate that name is not empty
    if (content == "") {
        alert("내용을 입력해주세요.");
        frm.content.focus();
        return false;
    }
    
    if(star == null) {
        alert("별점을 입력해주세요.");
        return false;
    }
    
    const formData = new FormData();
    formData.append("content", content);
    formData.append("star", star);
    formData.append("pic", pic);
    formData.append("order_seq", frm.order_seq.value);
    formData.append("product_seq", frm.product_seq.value);

    
    $.ajax({
        url: "<%=ctxPath%>/member/review.dk",
        type: "post",
        data: formData,
        processData: false, // Prevent jQuery from automatically transforming the data into a query string
        contentType: false,
        dataType: "json", // contentType을 설정하지 않음
        success: function(response) {
            // 성공적으로 제출된 후 처리 로직
            if(response.success) {
            	alert("리뷰가 제출되었습니다!");
            	$('#reviewModal').modal('hide');
            	
            }
            else {
            	alert("리뷰가 제출 실패되었습니다!" + response.message);
            }

        },
        error: function(xhr, status, error) {
            // 에러 발생 시 처리 로직
            console.error("리뷰 제출 중 에러 발생:", error);
            alert("리뷰 제출 중 에러가 발생했습니다. 다시 시도해주세요.");
        }
    });

    console.log("submitReview function called");
    
}


function updateReview() {
    const frm = document.reviewUpdateFrm;
    const content = frm.content.value.trim();
    const star =frm.rating.value;
    const pic = frm.pic.files[0];
    console.log(star)
    // Validate that name is not empty
    if (content == "") {
        alert("내용을 입력해주세요.");
        frm.content.focus();
        return false;
    }
    
    if(star == null) {
        alert("별점을 입력해주세요.");
        return false;
    }
    
    const formData = new FormData();
    formData.append("content", content);
    formData.append("star", star);
    formData.append("pic", pic);
    formData.append("order_seq", frm.order_seq.value);
    formData.append("product_seq", frm.product_seq.value);
    formData.append("review_seq", frm.review_seq.value);

    
    $.ajax({
        url: "<%=ctxPath%>/member/reviewUpdate.dk",
        type: "post",
        data: formData,
        processData: false, // Prevent jQuery from automatically transforming the data into a query string
        contentType: false,
        dataType: "json", // contentType을 설정하지 않음
        success: function(response) {
            // 성공적으로 제출된 후 처리 로직
            console.log(response.success);
            console.log(response.message);
            
            if(response.success) {
            	alert("리뷰가 수정되었습니다!");
            	$('#reviewUpdateModal').modal('hide');
            	
            }
            else {
            	alert("리뷰가 수정 실패되었습니다!" + response.message);
            }

        },
        error: function(xhr, status, error) {
            // 에러 발생 시 처리 로직
            console.error("리뷰 제출 중 에러 발생:", error);
            alert("리뷰 수정 중 에러가 발생했습니다. 다시 시도해주세요.");
        }
    });

    console.log("updateReview function called");
    
}

function deleteReview() {
    const frm = document.reviewUpdateFrm;
    
    const formData = new FormData();

    formData.append("review_seq", frm.review_seq.value);

    
    $.ajax({
        url: "<%=ctxPath%>/member/reviewDelete.dk",
        type: "post",
        data: formData,
        processData: false, // Prevent jQuery from automatically transforming the data into a query string
        contentType: false,
        dataType: "json", // contentType을 설정하지 않음
        success: function(response) {
            // 성공적으로 제출된 후 처리 로직
            console.log(response.success);
            console.log(response.message);
            
            if(response.success) {
            	alert("리뷰가 삭제되었습니다!");
            	$('#reviewUpdateModal').modal('hide');
            	//remove the 
            	
            }
            else {
            	alert("리뷰가 삭제 실패되었습니다!" + response.message);
            }

        },
        error: function(xhr, status, error) {
            // 에러 발생 시 처리 로직
            console.error("리뷰 삭제 중 에러 발생:", error);
            alert("리뷰 삭제 중 에러가 발생했습니다. 다시 시도해주세요.");
        }
    });
    


    
}

function goSearch(){

	const frm = document.order_search_frm;
	frm.action = "orderList.dk";
	frm.post = "get";
	frm.submit();
	
} 


</script>

<jsp:include page="../footer.jsp" />