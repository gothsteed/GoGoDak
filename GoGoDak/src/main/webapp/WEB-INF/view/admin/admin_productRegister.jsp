<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%
    String ctxPath = request.getContextPath();
    //    /GoGoDak
%>
<jsp:include page="../header.jsp" />


<script>

$(document).ready(function() {

		// 에러 상태 변수 초기화
		   let errorStatus = {};
		
		
		$("span.error").hide();

		// 상품가격, 재고, 할인 금액(또는 퍼센트) 필드에 대해서만 유효성 검사
	    $("#price, #stock, #discountValue").on("blur change", function() {
	        validateField($(this));
	    });

	    $("#btnRegister").click(function(event) {
	        if (!validateForm()) {
	            event.preventDefault();
	        } else {
	            goRegister();
	        }
	    });


	 // ==>> 제품이미지 파일선택을 선택하면 화면에 이미지를 미리 보여주기 시작 <<== //
	    $(document).on("change", "input.img_file", function(e){
	      
	       const input_file =  $(e.target).get(0);
	      
	        const fileReader = new FileReader();
	       
	        fileReader.readAsDataURL(input_file.files[0]); 
	        // FileReader.readAsDataURL() --> 파일을 읽고, result속성에 파일을 나타내는 URL을 저장 시켜준다.
	       
	        fileReader.onload = function(){ // FileReader.onload --> 파일 읽기 완료 성공시에만 작동하도록 하는 것임.
	          // console.log(fileReader.result);
	           /*
	              data:image/jpeg;base64,/9j/4AAQSkZJRgABAQEAeAB4AAD/2wBDAAIBAQIBAQICAgICAgICAwUDAwMDAwYEBAMFBwYHBwcGBwcICQsJCAgKCAcHCg0KCgsMDAwMBwkODw0MDgsMDAz/2wBDAQICAg 
	              이러한 형태로 출력되며, img.src 의 값으로 넣어서 사용한다.
	            */
	            
	            document.getElementById("previewImg").src = fileReader.result;
	        };
	        
	    });
	    // ==>> 제품이미지 파일선택을 선택하면 화면에 이미지를 미리 보여주기 끝 <<== //

	

});

//유효성 검사 시작
function validateField(field) {
    let isValid = true;
    let errorMsg = "";

    if (!$.isNumeric(field.val()) || parseFloat(field.val()) <= 0) {
        errorMsg = "양수만 입력 가능합니다.";
        isValid = false;
    } else {
        if (field.attr("id") === "price") {
            errorMsg = "상품가격은 양수만 가능합니다.";
        } else if (field.attr("id") === "stock") {
            errorMsg = "재고는 양수만 가능합니다.";
        } else if (field.attr("id") === "discountValue") {
            if ($("input[name='discount_type']:checked").val() === "percent") {
                if (parseFloat(field.val()) < 1 || parseFloat(field.val()) > 100) {
                    errorMsg = "퍼센트 할인은 1부터 100까지만 가능합니다.";
                    isValid = false;
                }
            } else if ($("input[name='discount_type']:checked").val() === "amount") {
                if (parseFloat(field.val()) >= parseFloat($("#price").val())) {
                    errorMsg = "할인 금액은 상품 가격보다 작아야 합니다.";
                    isValid = false;
                }
            }
        }
    }

    if (!isValid) {
        if (!field.data("errorShown")) {
            field.closest(".form-group").append("<span class='error' style='color: red;'>" + errorMsg + "</span>");
            field.data("errorShown", true); // 에러 표시 상태를 저장
        }
        field.val(""); // 입력값 초기화
        field.focus(); // 입력 필드에 포커스 주기
    } else {
        field.closest(".form-group").find("span.error").remove(); // 오류 메시지 제거
        field.data("errorShown", false); // 에러 표시 상태 초기화
    }

    return isValid;
}

function validateForm() {
    let isValid = true;

    // 상품가격, 재고, 할인 금액(또는 퍼센트) 필드에 대해서만 유효성 검사
    $("#price, #stock, #discountValue").each(function() {
        if (!validateField($(this))) {
            isValid = false;
            return false; // 루프 종료
        }
    });

    return isValid;
}
//유효성 검사 끝



function goRegister() {
	
	const frm = document.boardFrm;
    frm.action = "<%=ctxPath%>/admin/productRegister.dk";
    frm.method = "post";
    frm.submit();
    console.log("goRegister 함수 호출됨");
}



function goReset() {
    history.back(); // 이전 페이지로 이동
  }




</script>




<div class="container">
    <div class="row justify-content-center">
        <div class="col-md-6">
            <div class="card">
                <div class="card-header">
                    <h3 class="text-center mb-0">상품 등록</h3>
                </div>
<form name="boardFrm" enctype="multipart/form-data"  enctype="multipart/form-data"> 
               
             <div class="card-body">
                  
                        <div class="form-group">
                            <label for="category">제조회사 <span class="text-danger">*</span></label>
                            <select name="fk_maunfactuer_seq" class="form-control infoData">
                                <option value="">:::선택하세요:::</option>

			                    <option value="1">1.☆딜리스틱☆</option>
			                    <option value="2">2.★제로아워★</option>
			                    <option value="3">3.●닥터리브●</option>
			     
                            </select>
                            <span class="error">필수입력</span>
                 		 </div>
                       
                       
                        
                        <div class="form-group">
                            <label for="product">상품명 <span class="text-danger">*</span></label>
                            <div class="input-group">
                                <input type="text" name="product_name" id="product" class="form-control infoData" placeholder="상품명을 입력하세요">
                                <div class="input-group-append">
                                <span class="error">필수입력</span>   
                                   
                                </div>
                            </div>
                        </div>
                        <div class="form-group">
                            <label for="product">상품가격 <span class="text-danger">*</span></label>
                            <input type="text" name="base_price" id="price" class="form-control infoData" >
                            <span class="error">필수입력</span>
                        </div>
                        
                        <div class="form-group">
                            <label for="product">제품설명 <span class="text-danger">*</span></label>
                            <input type="text" name="description" id="discription" class="form-control infoData">
                            <span class="error">필수입력</span>
                        </div>
                         <div class="form-group">
                      <label for="stock">재고 <span class="text-danger">*</span></label>
                      <input type="number" name="stock" id="stock" class="form-control infoData">
                      <span class="error">필수입력</span>
                  </div>
                      
                        <div class="form-group">
                            <label>할인 종류 <span class="text-danger">*</span></label>
                            <div class="row mx-0">
                                <div class="col-sm-4">
                                    <div class="form-check">
                                        <input class="form-check-input infoData" type="radio" name="discount_type" id="percentageDiscount" value="percent" style="margin-left: 35px;" checked>
                                        <label class="form-check-label" for="percentageDiscount">퍼센트 할인</label>
                                    </div>
                                </div>
                                <div class="col-sm-4">
                                    <div class="form-check">
                                        <input class="form-check-input infoData" type="radio" name="discount_type" id="amountDiscount" value="amount" style="margin-left: 35px;">
                                        <label class="form-check-label" for="amountDiscount">금액 할인</label>
                                    </div>
                                </div>
                                 <div class="col-sm-4">
                                    <div class="form-check">
                                        <input class="form-check-input infoData" type="radio" name="discount_type" id="amountDiscount" value="amount" style="margin-left: 35px;">
                                        <label class="form-check-label" for="amountDiscount">할인없음</label>
                                    </div>
                                </div>
                            </div>
                        </div>
                        <div class="form-group">
                            <label for="discountValue">할인 금액 또는 퍼센트 <span class="text-danger">*</span></label>
                            <input type="number" name="discount_amount" id="discountValue" class="form-control infoData" >
                             <span class="error">필수입력</span>
                        </div>
                       <%--
                       <div class="form-group">
                      <label for="expectedPrice">예상 금액(1개당)</label>
                      <input type="text" name="expectedPrice" id="expectedPrice" class="form-control" readonly>
                  </div>
                   --%>
                  
                  <div class="form-group">
                            <label for="category">상품타입 <span class="text-danger">*</span></label>
                            <select name="product_type" class="form-control infoData">
                                <option value="">:::선택하세요:::</option>
                                   
                     <option value="1">1.🍗닭가슴살🍗</option>
                     <option value="2">2.🍱볶음밥🍱</option>
                     <option value="3">3.🥯빵🥯</option>
                     <option value="4">4.🧁디저트🧁</option>
                               
                       
                            </select>
                            <span class="error">필수입력</span>
                  </div>
                  
                  
                  <!--제품 이미지 -->
                  <div class="form-group">
                     <label for="discountImage">제품 이미지</label>
                     <div class="row">
                        <div class="col">
                           <input type="file" name="main_pic" class="infoData img_file" accept='image/*' /><span class="error"></span>
                        </div>
                     </div>
                  </div>
                  <!--상세 이미지 -->
                  <div class="form-group">
                     <label for="discountImage">상세 이미지</label>
                     <div class="row">
                        <div class="col">
                           <input type="file" name="description_pic" class="infoData img_file" accept='image/*'/><span class="error"></span>
                        </div>   
                     </div>
                  </div>
                  
                           
               <!-- 이미지 미리보기 -->
               <div class="form-group">
                   <label for="previewImg">이미지 미리보기</label>
                   <img id="previewImg" width="300">
               </div>

                  
                        <div class="form-group text-center">
                            <button type="button" class="btn btn-success btn-submit mr-3" onclick="goRegister()">상품등록</button>
                            <button type="reset" class="btn btn-secondary btn-submit"  onclick="goReset()">초기화</button>
                        </div>


               </div> 
</form>
           </div>
        </div>
    </div>    
</div>
 


<jsp:include page="../footer.jsp" />  


<script src="https://code.jquery.com/jquery-3.5.1.slim.min.js"></script>
<script src="https://cdn.jsdelivr.net/npm/@popperjs/core@2.5.4/dist/umd/popper.min.js"></script>
<script src="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js"></script>