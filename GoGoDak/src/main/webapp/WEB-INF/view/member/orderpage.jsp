<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
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
</script>


<style>
     body {
            font-family: Arial, sans-serif;
        }
        .container {
            max-width: 1000px;
            margin: 0 auto;
            padding: 20px;
        }
        
        /*장바구니 목록 */
        body > section.why_section.layout_padding > div > h2 {
    		text-align:center;
    		
        }
        
        
        .cart-list {
        max-width: 1090px; /* 원하는 가로 크기로 설정하세요 */
        margin: 0 auto; /* 가운데 정렬을 위해 추가 */
    }
        
        .cart-list, .form-group, .points-use, .total-cost, .submit-btn {
            margin-bottom: 20px;
        }
        .cart-item {
            display: flex;
            align-items: center;
            justify-content: space-between;
            margin-bottom: 10px;
            border-bottom: 1px solid #ddd;
            padding-bottom: 10px;
        }
        .cart-item img {
            width: 100px;
            height: 100px;
        }
  		 body > section.why_section.layout_padding > div > div {
  		 text-align:center;
   			}
	
        .quantity {
            display: flex;
            align-items: center;
        }
        .quantity button {
            width: 30px;
            height: 30px;
        }
        .quantity input {
            width: 50px;
            text-align: center;
            margin: 0 10px;
        }
        .btn-danger {
            background-color: red;
            color: white;
            border: none;
            padding: 5px 10px;
            cursor: pointer;
        }
        
        .form-group label {
            display: block;
            margin-bottom: 5px;
        }
        .form-group input {
            width: calc(100% - 130px);
            padding: 10px;
            margin-right: 10px;
            border: 1px solid #ddd;
        }
        .form-group button {
            padding: 10px;
            cursor: pointer;
        }
        .points-use input {
            width: 100px;
            margin-left: 10px;
            margin-right: 10px;
        }
       
        /* 우편번호 찾기 버튼 */
        body > section.why_section.layout_padding > div > form > div.container > div:nth-child(2) > button {
       	   display: inline-block;
	    padding: 10px 20px;
	    background-color: #ffc107; /* 노란색 배경 */
	    color: #fff; /* 흰색 텍스트 */
	    border: none;
	    border-radius: 5px; /* 둥근 테두리 */
	    font-size: 16px;
	    font-weight: bold;
	    text-transform: uppercase;
	    letter-spacing: 1px;
	    cursor: pointer;
	    transition: background-color 0.3s;
        }
       
       
       
       
        /* 결제 버튼*/
        .submit-btn {
        display: inline-block;
	    padding: 10px 20px;
	    background-color: #ffc107; /* 노란색 배경 */
	    color: #fff; /* 흰색 텍스트 */
	    border: none;
	    border-radius: 5px; /* 둥근 테두리 */
	    font-size: 16px;
	    font-weight: bold;
	    text-transform: uppercase;
	    letter-spacing: 1px;
	    cursor: pointer;
	    transition: background-color 0.3s;
        }  
    
       
        
        
 </style>

  <script>
	  $(document).ready(function () {
	      updateTotal();
	   });

        document.getElementById('availablePointsSpan').innerText = '${sessionScope.loginuser.point}'.toLocaleString() + '원';
		
    
        function decreaseQuantity(productId) {
            var quantityInput = document.getElementById('quantity-' + productId);
            var currentValue = parseInt(quantityInput.value);
            if (currentValue > 1) {
                quantityInput.value = currentValue - 1;W
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
                var price = parseInt(item.getAttribute('data-price'));
                var quantity = parseInt(item.querySelector('input[type="text"]').value);
                totalCost += price * quantity;
            });
            
            var points = parseInt(document.getElementById('points').value);
            totalCost -= points;
            document.getElementById('totalCost').innerText = totalCost.toLocaleString();
        }

        function checkPoints(availablePoints) {
            var points = parseInt(document.getElementById('points').value);
            if (Number(points) > availablePoints) {
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
         updateTotal(); // 장바구니의 총 금액을 다시 계산
      }
   }



   function increaseQuantity(itemId) {
      var quantityInput = document.getElementById('quantity-' + itemId);
      var currentQuantity = parseInt(quantityInput.value);
      quantityInput.value = currentQuantity + 1;
      updateTotal();
   }

   function decreaseQuantity(itemId) {
      var quantityInput = document.getElementById('quantity-' + itemId);
      var currentQuantity = parseInt(quantityInput.value);
      if (currentQuantity > 1) {
         quantityInput.value = currentQuantity - 1;
         updateTotal();
      }
   }

   function updateTotal() {
      var items = document.querySelectorAll('.cart-item');
      totalCost = 0;
      items.forEach(item => {
         var price = parseInt(item.getAttribute('data-price'));
         var quantity = parseInt(item.querySelector('.quantity input[type="text"]').value);
         totalCost += price * quantity;
      });

      var points = parseInt(document.getElementById('points').value);

      totalCost -= points

      document.getElementById('totalCost').innerText = formatPrice(totalCost);
   }

   function applyPoints() {
      var points = parseInt(document.getElementById('points').value);
      var currentTotal = totalCost;
      if (points > currentTotal) {
         alert("포인트가 총 금액보다 많습니다!");
         document.getElementById('points').value = currentTotal;
         points = currentTotal;
      }
      var newTotal = currentTotal - points;
      document.getElementById('totalCost').innerText = formatPrice(newTotal);
   }

   function formatPrice(price) {
      return price.toLocaleString();
   }

   function goPurchase() {

      // 너비 1000, 높이 600 인 팝업창을 화면 가운데 위치시키기
      const width = 1000;
      const height = 600;
      //todo: !!!purchase url 추가!!!!!
      const url = ``

      const left = Math.ceil((window.screen.width - width)/2);
      const top = Math.ceil((window.screen.height - height)/2);

      window.open(url, "coinPurchaseEnd", `left=${left}, top=${top}, width=${width}, height=${height}`);

      /* === 팝업창에서 부모창 함수 호출 방법 3가지 ===
            1-1. 일반적인 방법
            opener.location.href = "javascript:부모창스크립트 함수명();";
                           
            1-2. 일반적인 방법
            window.opener.부모창스크립트 함수명();

            2. jQuery를 이용한 방법
            $(opener.location).attr("href", "javascript:부모창스크립트 함수명();");
      */
   }


</script>
 


<section class="why_section layout_padding">
        <div class="cart">
            <h2>장바구니 목록</h2>
            <form action="">
                <div class="cart-list">
                    <div class="cart-item" data-price="10000" id="cart-item-1">
                        <img src="https://via.placeholder.com/100" alt="상품 1">
                        <p>상품 1 - 10,000원</p>
                        <div class="quantity">
                            <button type="button" onclick="decreaseQuantity(1)">-</button>
                            <input type="text" value="1" id="quantity-1" name="quantity-1">
                            <button type="button" onclick="increaseQuantity(1)">+</button>
                        </div>
                        <button class="btn-danger" onclick="removeItem(1)">삭제</button>
                    </div>
                    <div class="cart-item" data-price="10000" id="cart-item-2">
                        <img src="https://via.placeholder.com/100" alt="상품 2">
                        <p>상품 2 - 10,000원</p>
                        <div class="quantity">
                            <button type="button" onclick="decreaseQuantity(2)">-</button>
                            <input type="text" value="1" id="quantity-2">
                            <button type="button" onclick="increaseQuantity(2)">+</button>
                        </div>
                        <button class="btn-danger" onclick="removeItem(2)">삭제</button>
                    </div>
                    <div class="cart-item" data-price="10000" id="cart-item-3">
                        <img src="https://via.placeholder.com/100" alt="상품 3">
                        <p>상품 3 - 10,000원</p>
                        <div class="quantity">
                            <button type="button" onclick="decreaseQuantity(3)">-</button>
                            <input type="text" value="1" id="quantity-3">
                            <button type="button" onclick="increaseQuantity(3)">+</button>
                        </div>
                        <button class="btn-danger" onclick="removeItem(3)">삭제</button>
                    </div>
                    <div class="cart-item" data-price="10000" id="cart-item-4">
                        <img src="https://via.placeholder.com/100" alt="상품 4">
                        <p>상품 4 - 10,000원</p>
                        <div class="quantity">
                            <button type="button" onclick="decreaseQuantity(4)">-</button>
                            <input type="text" value="1" id="quantity-4">
                            <button type="button" onclick="increaseQuantity(4)">+</button>
                        </div>
                        <button class="btn-danger" onclick="removeItem(4)">삭제</button>
                    </div>
                    <div class="cart-item" data-price="10000" id="cart-item-5">
                        <img src="https://via.placeholder.com/100" alt="상품 5">
                        <p>상품 5 - 10,000원</p>
                        <div class="quantity">
                            <button type="button" onclick="decreaseQuantity(5)">-</button>
                            <input type="text" value="1" id="quantity-5">
                            <button type="button" onclick="increaseQuantity(5)">+</button>
                        </div>
                        <button class="btn-danger" onclick="removeItem(5)">삭제</button>
                    </div>
                </div>
                <div class="container">
                  <h2>배송지 정보 입력</h2>
                  <form action="/submit-order" method="POST">
                      <div class="form-group">
                          <label for="postcode">우편번호</label>
                          <input type="text" id="postcode" name="postcode" placeholder="우편번호를 입력하세요" >
                          <button type="button" onclick="execDaumPostcode()">우편번호 찾기</button>
                      </div>
                      <div class="form-group">
                          <label for="address">주소</label>
                          <input type="text" id="address" name="address" placeholder="주소를 입력하세요" >
                      </div>
                      <div class="form-group">
                          <label for="detailAddress">상세주소</label>
                          <input type="text" id="detailAddress" name="detailAddress" placeholder="상세주소를 입력하세요" >
                      </div>
                      <div class="form-group">
                          <label for="extraAddress">참고사항</label>
                          <input type="text" id="extraAddress" name="extraAddress" placeholder="참고사항을 입력하세요">
                      </div>
                      
                  </form>
              </div>
              <div class="text-position">
                <div class="points-use"> 
                    <label for="points">포인트 사용(최대: <span id="availablePointsSpan">${sessionScope.loginuser.point}</span>):</label>
                    <input type="number" id="points" name="points" value="0" min="0" onchange="checkPoints(${sessionScope.loginuser.point})">
                    <button type="button" onclick="applyPoints(${sessionScope.loginuser.point})">적용</button>
                </div>
                <div class="total-cost">
                    총 금액 : <span id="totalCost">0</span>원
                </div>
                <button type="button" class="submit-btn" onclick="goPurchase()">결제</button>
               </div>  
                
            </form>
        </div>
        
    </section>
<jsp:include page="../footer.jsp" />
