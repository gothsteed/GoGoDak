<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%
    String ctxPath = request.getContextPath();
    //    /GoGoDak
%>
<jsp:include page="../header.jsp" />

 
<style type="text/css">
  
  
.boardWrite_container{
	text-align: center;
}

#productList {
    max-height: 400px; /* 스크롤바를 추가할 최대 높이 */
    overflow-y: scroll; /* 수직 스크롤바 추가 */
    border: 1px solid #ccc; /* 스크롤 영역을 구분하는 테두리 */
    padding: 10px;
    border-radius: 5px;
    background-color: #f9f9f9; /* 연한 배경색 추가 */
}

#productList label {
    display: block; /* 라벨을 블록으로 설정하여 세로 정렬 */
    padding: 5px;
    margin-bottom: 5px;
    border: 1px solid #ddd;
    border-radius: 3px;
    background-color: #fff;
    transition: background-color 0.3s;
}

#productList label:hover {
    background-color: #f1f1f1; /* 호버 시 배경색 변경 */
}

      	
</style>
    
<script type="text/javascript">

$(document).ready(function() {
		
	const imageInput = document.getElementById('pic');
	const imagePreview = document.getElementById('imagePreview');
	
	imageInput.addEventListener('change', function(event) {
	    const file = event.target.files[0];
	    if (file) {
	        const reader = new FileReader();
	        reader.onload = function() {
	            const imageUrl = reader.result;
	            const imageElement = document.createElement('img');
	            imageElement.src = imageUrl;
	            imageElement.style.maxWidth = '500px'; // 이미지의 최대 너비 지정
	            imageElement.style.maxHeight = '500px'; // 이미지의 최대 높이 지정
	            imagePreview.innerHTML = ''; // 이미지를 교체하므로 이전 이미지를 삭제
	            imagePreview.appendChild(imageElement);
	        };
	        reader.readAsDataURL(file);
	    }
	    console.log("확인용1");
	});
});


function goRegister() {
	
	const frm = document.boardFrm;
    frm.action = "<%=ctxPath%>/admin/event.dk";
    frm.method = "post";
    frm.submit();
    console.log("goRegister 함수 호출됨");
}



function goReset() {
    history.back(); // 이전 페이지로 이동
  }

</script>
    
      

<div class="boardWrite_container">
	<div class="row justify-content-center">
		<div class="col-md-8">
			<div class="card">
				<div class="card-header">
         			<strong style="font-size: 20pt;">할인 이벤트</strong>
       			</div>
       			<div class="card-body" id="tblBoardWrite">
					<form name="boardFrm" method="post" enctype="multipart/form-data">
		         		<div class="board-group">
		               		<label for="name">제목</label>
		               		<input type="text" name="name" id="name" placeholder="제목 입력" />
		           		</div>
						<div class="board-group">
							<label for="pic">이미지</label>
							<div>
								<input type="file" id="pic" name="pic" accept="image/*" class="form-control">
								<%-- <img src="images/image.jpg" class="img-fluid" />  --%>
							</div>
							<div id="imagePreview"></div>
						</div>
						
						
						<div class="board-group">
						    <label for="products">할인 대상 상품</label>
						    <div id="productList">
						        <c:forEach var="product" items="${productList}">
						            <label><input type="checkbox" name="product" id="product" value="${product.product_seq}"> ${product.product_name}</label>
						        </c:forEach>
						        <br>
						    </div>
						</div>
						<br>
			            <div class="form-group text-center">
				           	<button type="button" class="btn btn-success btn-lg mr-3" onclick="goRegister()">저장</button>
                            <button type="reset" class="btn btn-danger btn-lg" onclick="goReset()">취소</button>
			            </div>
					</form>
				</div>
         	</div>
		</div>
	</div>
</div>
 
<jsp:include page="../footer.jsp" />  


<script src="https://code.jquery.com/jquery-3.5.1.slim.min.js"></script>
<script src="https://cdn.jsdelivr.net/npm/@popperjs/core@2.5.4/dist/umd/popper.min.js"></script>
<script src="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js"></script>