<%@page import="domain.Product_detailVO"%>
<%@page import="domain.ProductVO"%>
<%@page import="java.util.Map"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%
String ctxPath = request.getContextPath();
%>

<jsp:include page="../header.jsp" />

<script src="https://t1.daumcdn.net/mapjsapi/bundle/postcode/prod/postcode.v2.js"></script>

<script type="text/javascript" src="https://service.iamport.kr/js/iamport.payment-1.1.2.js"></script>
<script>
    function execDaumPostcode() {
        new daum.Postcode({
            oncomplete: function(data) {
                var addr = '';
                var extraAddr = '';
                
                if (data.userSelectedType === 'R') {
                    addr = data.roadAddress;
                } else {
                    addr = data.jibunAddress;
                }

                if (data.userSelectedType === 'R') {
                    if (data.bname !== '' && /[동|로|가]$/g.test(data.bname)) {
                        extraAddr += data.bname;
                    }
                    if (data.buildingName !== '' && data.apartment === 'Y') {
                        extraAddr += (extraAddr !== '' ? ', ' + data.buildingName : data.buildingName);
                    }
                    if (extraAddr !== '') {
                        extraAddr = ' (' + extraAddr + ')';
                    }
                    document.getElementById("extraAddress").value = extraAddr;
                
                } else {
                    document.getElementById("extraAddress").value = '';
                }

                document.getElementById('postcode').value = data.zonecode;
                document.getElementById('address').value = addr;
                document.getElementById('detailAddress').focus();
            }
        }).open();
    }

    $(document).ready(function () {
    	
        updateTotal();
		
        let currentPoint = 

        // Event listeners for points input and buttons
        $('#points').on('input', function () {
            checkPoints(${sessionScope.loginuser.point});
        });
        
        $("input#postcode").attr("readonly", true);
        $("input#address").attr("readonly", true);
        $("input#extraAddress").attr("readonly", true);
        
        let jsonData = collectCartData();
        console.log(jsonData)
    });

    function decreaseQuantity(productId) {
        var quantityInput = document.getElementById('quantity-' + productId);
        var currentValue = parseInt(quantityInput.value);
        if (currentValue > 1) {
            quantityInput.value = currentValue - 1;
            updateTotal();
        }
    }

    function increaseQuantity(productId) {
        var quantityInput = document.getElementById('quantity-' + productId);
        var currentValue = parseInt(quantityInput.value);
        quantityInput.value = currentValue + 1;
        updateTotal();
    }

    function updateTotal() {
        var cartItems = document.querySelectorAll('.cart-item');
        var totalCost = 0;
        cartItems.forEach(function(item) {
            var price = parseFloat(item.getAttribute('data-price'));
            var quantity = parseInt(item.querySelector('input[type="text"]').value);
            totalCost += price * quantity;
        });

        var points = parseInt(document.getElementById('points').value) || 0;
        totalCost -= points;
        document.getElementById('totalCost').innerText = totalCost.toLocaleString() + '원';
        
        
        let cartJson = collectCartData();
        
        
        let point = parseInt(document.getElementById('points').value) || 0;
        $.ajax({
            url: '<%=ctxPath%>/member/cart.dk',
            type: 'post',
            dataType: 'json',
            contentType: 'application/json; charset=utf-8',   
            data: cartJson,
            async:false,
            success: function(response) {
            	if(response.success) {
					console.log("카드 담기 성공")
					console.log(response.size);
					
					$("span.bg-danger.text-white.text-center").text(response.size);

                    
                } else {
                	console.log(response.message)
  
                }
            },
            error: function(xhr, status, error) {
            	console.log(error)
            }
        });
        
        
    }

    function checkPoints(availablePoints) {
        var points = parseInt(document.getElementById('points').value) || 0;
        
        var cartItems = document.querySelectorAll('.cart-item');
        var totalCost = 0;
        cartItems.forEach(function(item) {
            var price = parseFloat(item.getAttribute('data-price'));
            var quantity = parseInt(item.querySelector('input[type="text"]').value);
            totalCost += price * quantity;
        });
        
        if (points > availablePoints || points > totalCost) {
            alert('사용 가능한 포인트를 초과했습니다.');
            document.getElementById('points').value = Math.min(availablePoints, totalCost);
        }
        updateTotal();
    }

    function applyPoints(availablePoints) {
        checkPoints(availablePoints);
    }

    function removeItem(itemId) {
        var itemElement = document.getElementById('cart-item-' + itemId);
        if (itemElement) {
            var quantityInput = document.getElementById('quantity-' + itemId);
            if (quantityInput) {
                quantityInput.value = 0;
            }
            itemElement.style.display = 'none';
            updateTotal();
        }
    }
    function formatPrice(price) {
        return price.toLocaleString();
    }
    
    
    function collectCartData() {
        let cartData = [];

        document.querySelectorAll('.cart-item').forEach(function(cartItem) {
        	let productArr = cartItem.id.split('-');
            let productSeq = productArr[2];  // Extract product_seq from the id attribute
            let quantity = document.getElementById('quantity-' + productSeq).value;
            let product_detail_seq = productArr[3];
            
            
            if(product_detail_seq === undefined) {
	           	cartData.push({
	                "product_seq": parseInt(productSeq, 10),
	                "quantity": parseInt(quantity, 10)
	            });
            }
            else {
	           	cartData.push({
	                "product_seq": parseInt(productSeq, 10),
	                "quantity": parseInt(quantity, 10),
	                "product_detail_seq":parseInt(product_detail_seq, 10)
	            });
            	
            }


        });

        return JSON.stringify({"cart": cartData});
    }
    
    
    function paymentModal(totalAmount, point, postcode, address, address_detail, address_extra) {
    	
    	var IMP = window.IMP;     // 생략가능
       	IMP.init('imp14713247');  // 중요!!  아임포트에 가입시 부여받은 "가맹점 식별코드". 
    	
       // 결제요청하기
       IMP.request_pay({
           pg : 'html5_inicis', // 결제방식 PG사 구분
           pay_method : 'card',	// 결제 수단
           merchant_uid : 'merchant_' + new Date().getTime(), // 가맹점에서 생성/관리하는 고유 주문번호
           name : "고고닭 장바구니 결제",	 // 코인충전 또는 order 테이블에 들어갈 주문명 혹은 주문 번호. (선택항목)원활한 결제정보 확인을 위해 입력 권장(PG사 마다 차이가 있지만) 16자 이내로 작성하기를 권장
           amount : 100,//${requestScope.totalAmount},	  // '${coinmoney}'  결제 금액 number 타입. 필수항목. 
           buyer_email : '${sessionScope.loginuser.email}',  // 구매자 email
           buyer_name : '${sessionScope.loginuser.name}',	  // 구매자 이름 
           buyer_tel : '${sessionScope.loginuser.tel}',    // 구매자 전화번호 (필수항목)
           buyer_addr : '',  
           buyer_postcode : '',
           m_redirect_url : ''  // 휴대폰 사용시 결제 완료 후 action : 컨트롤러로 보내서 자체 db에 입력시킬것!
       }, function(rsp) {


    		if ( rsp.success ) { // PC 데스크탑용

    		
    			$(".loader").css("display", "block");

    			console.log("calling goOrder")
    			goOrder(totalAmount, point, postcode, address, address_detail, address_extra);
    		//  $(opener.location).attr("href", "javascript:goCoinUpdate( '${idx}','${coinmoney}');");
    		
    			
            } else {
                location.href="/GoGoDak";
                alert("결제에 실패하였습니다.");
           }

       });
    	
    	
    }
    
    
    function goPurchase() {
    	
        var cartItems = document.querySelectorAll('.cart-item');
        var hasVisibleItems = false;

        cartItems.forEach(function(item) {
            if (item.style.display !== 'none') {
            	console.log("something is visable")
                hasVisibleItems = true;
            }
        });

        if (!hasVisibleItems) {
            alert('장바구니에 상품이 없습니다.');
            return;
        }
    	
    	
        const postcode = $("input#postcode").val().trim();
        const address = $("input#address").val().trim();
        const detailAddress = $("input#detailAddress").val().trim();
        const extraAddress = $("input#extraAddress").val().trim();
       
        if(postcode == "" || address == "" || detailAddress == "") { 
            alert("우편번호 및 주소를 입력하셔야 합니다.");
            return; 
        }
        
        
        let cartJson = collectCartData();
        
        
        let point = parseInt(document.getElementById('points').value) || 0;
        $.ajax({
            url: '<%=ctxPath%>/member/cart.dk',
            type: 'post',
            dataType: 'json',
            contentType: 'application/json; charset=utf-8',   
            data: cartJson,
            success: function(response) {
                if (response.success) {
<%--                     alert('카트에 담김');
                    window.location.href = '<%=ctxPath%>/member/cart.dk'; --%>
                    
                    paymentModal(Number(response.totalPay) - Number(point) , point, postcode, address, detailAddress, extraAddress)
                    
     <%--                // Create a form element
                    const form = document.createElement('form');
                    form.method = 'POST';
                    form.action = "<%=ctxPath%>/member/purchase.dk";

                    // Set the form target to open in a new window
                    form.target = "cartPurchase";

                    // Create and append input fields to the form
                    const inputPoint = document.createElement('input');
                    inputPoint.type = 'hidden';
                    inputPoint.name = 'point';
                    inputPoint.value = point;
                    form.appendChild(inputPoint);

                    const inputPostcode = document.createElement('input');
                    inputPostcode.type = 'hidden';
                    inputPostcode.name = 'postcode';
                    inputPostcode.value = postcode;
                    form.appendChild(inputPostcode);

                    const inputAddress = document.createElement('input');
                    inputAddress.type = 'hidden';
                    inputAddress.name = 'address';
                    inputAddress.value = address;
                    form.appendChild(inputAddress);

                    const inputDetailAddress = document.createElement('input');
                    inputDetailAddress.type = 'hidden';
                    inputDetailAddress.name = 'address_detail';
                    inputDetailAddress.value = detailAddress;
                    form.appendChild(inputDetailAddress);

                    const inputExtraAddress = document.createElement('input');
                    inputExtraAddress.type = 'hidden';
                    inputExtraAddress.name = 'address_extra';
                    inputExtraAddress.value = extraAddress;
                    form.appendChild(inputExtraAddress);

                    // Append the form to the document body and submit it
                    document.body.appendChild(form);
                    const width = 1000;
                    const height = 600;
                    const left = Math.ceil((window.screen.width - width) / 2);
                    const top = Math.ceil((window.screen.height - height) / 2);
                    
                

                    window.open('', "cartPurchase", `left=${left}, top=${top}, width=${width}, height=${height}, resizable=yes, scrollbars=yes`);
                    form.submit();

                    // Remove the form from the document after submission
                    document.body.removeChild(form); --%>
                } else {
                	console.log(response.message)
                    alert('카트 담기 실패: ' + response.message);
                }
            },
            error: function(xhr, status, error) {
            	console.log(error)
                alert('카트 담기 실패: ' + error);
            }
        });
        


     }
    
    
    function goOrder(totalAmount, point, postcode, address, address_detail, address_extra) {
        // Show the loader
        $(".loader").css("display", "block");

        const delivery_message = document.querySelector("input#delivery_message").value.trim();
        

        $.ajax({
            url : "<%=ctxPath%>/member/order.dk",
            data : {
                "point": point,
                "totalAmount": totalAmount,
                "postcode": postcode,
                "address": address,
                "address_detail": address_detail,
                "address_extra": address_extra,
                "delivery_message": delivery_message
            },
            type : "post",
            async : false,
            dataType : "json",
            success : function(json){
                // Hide the loader
                $(".loader").css("display", "none");

                alert(json.message);
                location.href = json.loc;
            },
            error: function(request, status, error){
                // Hide the loader
                $(".loader").css("display", "none");

                alert("code: "+request.status+"\n"+"message: "+request.responseText+"\n"+"error: "+error);
            }
        });
    }
</script>

<section class="why_section layout_padding inner_page">
	<div class="cart container">
    	<h1 style="text-align: center;">장바구니 목록</h1>
        <br><br>
        <form action="" class="row">
<div class="cart-list col-6">
    <%
        Map<ProductVO, Integer> cart = (Map<ProductVO, Integer>) session.getAttribute("cart");
        if (cart != null) {
            for (Map.Entry<ProductVO, Integer> entry : cart.entrySet()) {
                ProductVO product = entry.getKey();
                Integer quantity = entry.getValue();
                Product_detailVO product_detail = product.getProduct_detailVO();

                if (product_detail == null) {
                    // Handle case where product_detail is null
    %>
                    <div class="cart-item row" data-price="<%= product.getDiscountPrice() %>" id="cart-item-<%= product.getProduct_seq() %>">
                        <img class="col-2" src="<%= ctxPath %>/images/product/<%= product.getMain_pic()%>" alt="<%= product.getProduct_seq()%>">
                        <div class="col-5">
                            <p><%= product.getProduct_name()%></p>
                            <fmt:formatNumber value="<%= product.getDiscountPrice() %>" type="currency" currencySymbol="" groupingUsed="true" />원
                        </div>
                        <div class="quantity col-3">
                            <button type="button" onclick="decreaseQuantity(<%= product.getProduct_seq()%>)">-</button>
                            <input type="text" value="<%= quantity %>" id="quantity-<%= product.getProduct_seq() %>" name="quantity-<%= product.getProduct_seq() %>">
                            <button type="button" onclick="increaseQuantity(<%= product.getProduct_seq() %>)">+</button>
                        </div>
                        <button type="button" class="btn-danger col-1" onclick="removeItem(<%= product.getProduct_seq() %>)">삭제</button>
                    </div>
    <%
                } else {
                    // Handle case where product_detail is not null
    %>
                    <div class="cart-item row" data-price="<%= product.getDiscountPrice() %>" id="cart-item-<%= product.getProduct_seq() %>-<%= product_detail.getProduct_detail_seq() %>">
                        <img class="col-2" src="<%= ctxPath %>/images/product/<%= product.getMain_pic()%>" alt="<%= product.getProduct_seq()%>">
                        <div class="col-5">
                            <p><%= product.getProduct_name()%></p>
                            <p><%= product_detail.getDetail_name() %></p>
                            <fmt:formatNumber value="<%= product.getDiscountPrice() %>" type="currency" currencySymbol="" groupingUsed="true" />원
                        </div>
                        <div class="quantity col-3">
                            <button type="button" onclick="decreaseQuantity(<%= product.getProduct_seq()%>)">-</button>
                            <input type="text" value="<%= quantity %>" id="quantity-<%= product.getProduct_seq() %>" name="quantity-<%= product.getProduct_seq() %>">
                            <button type="button" onclick="increaseQuantity(<%= product.getProduct_seq() %>)">+</button>
                        </div>
                        <button type="button" class="btn-danger col-1" onclick="removeItem(<%= product.getProduct_seq() %>)">삭제</button>
                    </div>
    <%
                }
            }
        }
    %>
</div>

            <div class="container col-5">
            	<br>
                <h3>배송지 정보 입력</h3>
                <br>
                <form method="POST">
                    <div class="form-group">
                        <label for="postcode" class="h5">우편번호</label>
                        <input type="text" id="postcode" name="postcode" placeholder="우편번호를 입력하세요" value="${sessionScope.loginuser.postcode}">
                        <button type="button" onclick="execDaumPostcode()">우편번호 찾기</button>
                    </div>
                    <div class="form-group">
                        <label for="address" class="h5">주소</label>
                        <input type="text" id="address" name="address" placeholder="주소를 입력하세요" value="${sessionScope.loginuser.address}">
                    </div>
                    <div class="form-group">
                        <label for="detailAddress" class="h5">상세주소</label>
                        <input type="text" id="detailAddress" name="address_detail" placeholder="상세주소를 입력하세요" value="${sessionScope.loginuser.address_detail}">
                    </div>
                    <div class="form-group">
                        <label for="extraAddress" class="h5">참고사항</label>
                        <input type="text" id="extraAddress" name="address_extra" placeholder="참고사항을 입력하세요" value="${sessionScope.loginuser.address_extra}">
                    </div>
                    <div class="form-group">
                        <label for="delivery_message" class="h5">배송 메시지</label>
                        <input type="text" id="delivery_message" name="delivery_message">
                    </div>
                </form>
                
                <div class="text-position">
	                <div class="points-use h5">
	                    <c:if test="${empty sessionScope.loginuser}">
	                        <label for="points">포인트 사용(최대: <span id="availablePointsSpan">0</span>):
		                        <input type="text" id="points" name="points" value="0" placeholder="포인트 사용" oninput="applyPoints(0)" class="mt-2">
	                        </label>
	                    </c:if>
	                    <c:if test="${not empty sessionScope.loginuser}">
	                        <label for="points">포인트 사용(최대: <span id="availablePointsSpan">${sessionScope.loginuser.point}</span>)
	                    		<input type="text" id="points" name="points" value="0" placeholder="포인트 사용" oninput="applyPoints(${sessionScope.loginuser.point})" class="mt-2">
	                    	</label>
	                    </c:if>
	                </div>
	            </div>
                
                <div class="total container">
	                <p>총 가격: <span id="totalCost" class="h3"></span></p>
	                <p style="color: red; font-weight: bold;">결제금액의 5%를 포인트로 드립니다.&nbsp;&nbsp;(* 단, 포인트 사용시 적립X *)</p>
	                <button type="button" onclick="goPurchase()">구매하기</button>
	            </div>
            </div>
        </form>
    </div>
    <div class="loader"></div>
</section>

<style>
.cart {
    max-width: 80%;
    margin: auto;
}
.cart-list {
    display: flex;
    flex-direction: column;
    gap: 10px;
}
.cart-item {
    display: flex;
    align-items: center;
    justify-content: space-between;
    gap: 20px;
    padding: 10px;
    background: #f5f5f5;
    border-radius: 8px;
}
.quantity button {
    padding: 5px 10px;
    margin: 0 5px;
    background-color: #ccc;
    border: none;
    cursor: pointer;
}
.quantity input[type="text"] {
    width: 40px;
    text-align: center;
}
.cart-item img {
    width: 100px;
    height: 100px;
    object-fit: cover;
    border-radius: 50%;
}
.cart-item p {
    margin: 0;
    font-size: 16px;
}
button {
    padding: 10px 20px;
    background-color: #007BFF;
    color: white;
    border: none;
    border-radius: 5px;
    cursor: pointer;
    font-size: 16px;
}
button:hover {
    background-color: black;
    color: #fff;
}
button {
    background: #fbc02d;
    color: black;
    border: none;
    top: 0px;
    font-size: 14px;
    height: 48px;
    font-weight: 600;
    padding: 0 15px;
}
    div.loader {
        border: 12px solid #f3f3f3;
        border-radius: 50%;
        border-top: 12px dotted blue;
        border-right: 12px dotted green;
        border-bottom: 12px dotted red;
        border-left: 12px dotted pink;
        width: 120px;
        height: 120px;
        -webkit-animation: spin 2s linear infinite; /* Safari */
        animation: spin 2s linear infinite;
        display: none; /* Hide the loader initially */
        position: absolute;
        top: 50%;
        left: 50%;
        margin-top: -60px; /* Half of the height */
        margin-left: -60px; /* Half of the width */
    }
</style>

<jsp:include page="../footer.jsp" />
