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
        if (points > availablePoints) {
            alert('사용 가능한 포인트를 초과했습니다.');
            document.getElementById('points').value = availablePoints;
        }
        updateTotal();
    }

    function applyPoints(availablePoints) {
        checkPoints(availablePoints);
    }

    function goPurchase() {
        alert('결제가 완료되었습니다!');
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
            let productSeq = cartItem.id.split('-')[2];  // Extract product_seq from the id attribute
            let quantity = document.getElementById('quantity-' + productSeq).value;

            cartData.push({
                "product_seq": parseInt(productSeq, 10),
                "quantity": parseInt(quantity, 10)
            });
        });

        return JSON.stringify({"cart": cartData});
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
                    
                    // Create a form element
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
                    document.body.removeChild(form);

                <%--     
                    const width = 1000;
                    const height = 600;
                    //todo: !!!purchase url 추가!!!!!
                    const url = "<%=ctxPath%>/member/purchase.dk?point=" + point;

                    const left = Math.ceil((window.screen.width - width)/2);
                    const top = Math.ceil((window.screen.height - height)/2);

                    window.open(url, "cartPurchase", `left=${left}, top=${top}, width=${width}, height=${height}`);
                     --%>
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
/*         
		
        // 너비 1000, 높이 600 인 팝업창을 화면 가운데 위치시키기
        const width = 1000;
        const height = 600;
        //todo: !!!purchase url 추가!!!!!
        const url = ``

        const left = Math.ceil((window.screen.width - width)/2);
        const top = Math.ceil((window.screen.height - height)/2);

        window.open(url, "coinPurchaseEnd", `left=${left}, top=${top}, width=${width}, height=${height}`); */


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
                            System.out.println(product.getDiscountPrice());
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
.loader {
  display: none; /* Hide the loader initially */
  border: 16px solid #f3f3f3;
  border-radius: 50%;
  border-top: 16px solid #fbc02d;
  width: 120px;
  height: 120px;
  -webkit-animation: spin 2s linear infinite; /* Safari */
  animation: spin 2s linear infinite;
}
/* Safari */
@-webkit-keyframes spin {
  0% { -webkit-transform: rotate(0deg); }
  100% { -webkit-transform: rotate(360deg); }
}
@keyframes spin {
  0% { transform: rotate(0deg); }
  100% { transform: rotate(360deg); }
}
</style>

<jsp:include page="../footer.jsp" />