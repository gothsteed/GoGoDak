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
            itemElement.remove();
            updateTotal();
        }
    }

    function formatPrice(price) {
        return price.toLocaleString();
    }
    
    
    function goPurchase() {
    	
        const postcode = $("input#postcode").val().trim();
        const address = $("input#address").val().trim();
        const detailAddress = $("input#detailAddress").val().trim();
        const extraAddress = $("input#extraAddress").val().trim();
       
        if(postcode == "" || address == "" || detailAddress == "") { 
            alert("우편번호 및 주소를 입력하셔야 합니다.");
            return; 
        }

        // 너비 1000, 높이 600 인 팝업창을 화면 가운데 위치시키기
        const width = 1000;
        const height = 600;
        //todo: !!!purchase url 추가!!!!!
        const url = ``

        const left = Math.ceil((window.screen.width - width)/2);
        const top = Math.ceil((window.screen.height - height)/2);

        window.open(url, "coinPurchaseEnd", `left=${left}, top=${top}, width=${width}, height=${height}`);


     }
    
    

</script>



<section class="why_section layout_padding">
    <div class="cart">
        <h2>장바구니 목록</h2>
        <br>
        <form action="">
            <div class="cart-list">
           		<%
                    Map<ProductVO, Integer> cart = (Map<ProductVO, Integer>) session.getAttribute("cart");
                    if (cart != null) {
                        for (Map.Entry<ProductVO, Integer> entry : cart.entrySet()) {
                            ProductVO product = entry.getKey();
                            Integer quantity = entry.getValue();
                            System.out.println(product.getDiscountPrice());
                %>
                            <div class="cart-item" data-price="<%= product.getDiscountPrice() %>" id="cart-item-<%= product.getProduct_seq() %>">
                                <img src="<%= ctxPath %>/images/product/<%= product.getMain_pic()%>.jpg" alt="<%= product.getProduct_seq() %>">
                                <p><%= product.getProduct_name()%> - <fmt:formatNumber value="<%= product.getDiscountPrice() %>" type="currency" currencySymbol="" groupingUsed="true" />원</p>
                                <div class="quantity">
                                    <button type="button" onclick="decreaseQuantity(<%= product.getProduct_seq()%>)">-</button>
                                    <input type="text" value="<%= quantity %>" id="quantity-<%= product.getProduct_seq() %>" name="quantity-<%= product.getProduct_seq() %>">
                                    <button type="button" onclick="increaseQuantity(<%= product.getProduct_seq() %>)">+</button>
                                </div>
                                <button type="button" class="btn-danger" onclick="removeItem(<%= product.getProduct_seq() %>)">삭제</button>
                            </div>
                <%
                        }
                    }
                %>
            </div>
            <div class="container">
            	<br>
                <h2 >배송지 정보 입력</h2>
                <br>
                <form method="POST">
                    <div class="form-group">
                        <label for="postcode">우편번호</label>
                        <input type="text" id="postcode" name="postcode" placeholder="우편번호를 입력하세요" value="${sessionScope.loginuser.postcode}">
                        <button type="button" onclick="execDaumPostcode()">우편번호 찾기</button>
                    </div>
                    <div class="form-group">
                        <label for="address">주소</label>
                        <input type="text" id="address" name="address" placeholder="우편번호를 입력하세요" value="${sessionScope.loginuser.address}">
                    </div>
                    <div class="form-group">
                        <label for="detailAddress">상세주소</label>
                        <input type="text" id="detailAddress" name="address_detail" placeholder="상세주소를 입력하세요" value="${sessionScope.loginuser.address_detail}">
                    </div>
                    <div class="form-group">
                        <label for="extraAddress">참고사항</label>
                        <input type="text" id="extraAddress" name="address_extra" placeholder="우편번호를 입력하세요" value="${sessionScope.loginuser.address_extra}">
                    </div>
                </form>
            </div>
            <div class="text-position">
                <div class="points-use">
                    <c:if test="${empty sessionScope.loginuser}">
                        <label for="points">포인트 사용(최대: <span id="availablePointsSpan">0</span>):
	                        <input type="text" id="points" name="points" value="0" placeholder="포인트 사용" oninput="applyPoints(0)">
                        </label>

                    </c:if>
                    <c:if test="${not empty sessionScope.loginuser}">
                        <label for="points">포인트 사용(최대: <span id="availablePointsSpan">${sessionScope.loginuser.point}</span>):
                    		<input type="text" id="points" name="points" value="0" placeholder="포인트 사용" oninput="applyPoints(${sessionScope.loginuser.point})">
                    	</label>
             
                    </c:if>

                </div>
            </div>
            <div class="total">
                <p>총 가격: <span id="totalCost"></span></p>
                <p style="color: red; font-weight: bold;">결제금액의 5%를 포인트로 드립니다</p>
            </div>
            <button type="submit" onclick="goPurchase()">구매하기</button>
        </form>
    </div>
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
    margin-top: 20px;
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


</style>

<jsp:include page="../footer.jsp" />
