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
  
   

    // 퍼센트 할인, 금액 할인, 할인 없음 라디오 버튼 클릭 시 오류 메시지 숨기기
    $("input[name='discount_type']").click(function() {
        $("span.error").hide(); // 클릭 시 모든 오류 메시지 숨기기
    });

 
    
 	// 금액 할인 라디오 버튼 클릭 시 할인 금액 입력 필드 초기화
    $("#amountDiscount").click(function() {
        $("#discountValue").val('');
    });
    
 	
    // 퍼센트 할인 라디오 버튼 클릭 시 할인 금액 입력 필드 초기화
    $("#percentageDiscount").click(function() {
        $("#discountValue").val('');
    });
    
    // 페이지 로드 시 할인 없음이 선택되어 있으면 할인 금액 입력 필드 비활성화 및 내용 공백 처리
    if ($("#noDiscount").is(":checked")) {
        $("#discountValue").prop('disabled', true).val('');
    }
   
    // 필드가 blur 되거나 변경될 때 유효성 검사 실행
    $("#price, #stock, #discountValue").on("blur change", function() {
        validateField($(this));
    });

    // 등록 버튼 클릭 시 유효성 검사 후 등록 함수 호출
    $("#btnRegister").click(function(event) {
        if (!validateForm()) {
            event.preventDefault();
        } else {
            goRegister();
        }
    });

    // 할인 유형 변경 시 할인 금액 입력 필드 활성화/비활성화
    $("input[name='discount_type']").change(function() {
        if ($("#noDiscount").is(":checked")) {
            $("#discountValue").val('').prop('disabled', true);
        } else {
            $("#discountValue").prop('disabled', false);
        }
    });

 

    // 할인 금액 입력 필드에 '0' 입력 시 초기화
    $("#discountValue").on("input", function() {
        if ($(this).val() == '0') {
            $(this).val('');
        }
    });

    // 페이지 로드 시 할인 없음이 선택되어 있으면 할인 금액 입력 필드 비활성화
    if ($("#noDiscount").is(":checked")) {
        $("#discountValue").prop('disabled', true);
    }

    // 제품 이미지 파일 선택 시 미리보기 기능
    $(document).on("change", "input.img_file", function(e){
        const input_file =  $(e.target).get(0);
        const fileReader = new FileReader();
        fileReader.readAsDataURL(input_file.files[0]); 
        fileReader.onload = function(){
            document.getElementById("previewImg").src = fileReader.result;
        };
    });
    
    

    // 다른 할인 유형 라디오 버튼 클릭 시 할인 금액 입력 필드 활성화
    $("input[name='discount_type']").not("#noDiscount").click(function() {
        $("#discountValue").prop('disabled', false);
    });
});



//필드 유효성 검사 함수
function validateField(field) {
	$("span.error").show(); 
	let isValid = true;
    let errorMsg = "";

    // 숫자가 아니거나 음수인 경우 오류
    if (!$.isNumeric(field.val()) || parseFloat(field.val()) < 0) {
        errorMsg = "음수는 입력할 수 없습니다.";
        isValid = false;
    } else {
        // 필드 별로 유효성 검사
         if (field.attr("id") === "price") {
        	  if (parseFloat(field.val()) === 0) { // 재고가 0인 경우 오류
        		  errorMsg = "상품가격은 0 이상의 값이어야 합니다.";
                  isValid = false;
              }
        }  else if (field.attr("id") === "stock") {
            if (parseFloat(field.val()) === 0) { // 재고가 0인 경우 오류
                errorMsg = "재고는 0 이상의 값이어야 합니다.";
                isValid = false;
            }
        } else if (field.attr("id") === "discountValue" && !$("input[name='discount_type'][value='none']").prop("checked")) {
            if ($("input[name='discount_type']:checked").val() === "percent") {
                if (parseFloat(field.val()) < 1 || parseFloat(field.val()) >=100) {
                    errorMsg = "다시 입력하세요";
                    isValid = false;
                 
                }
            } else if ($("input[name='discount_type']:checked").val() === "amount") {
                if (parseFloat(field.val()) < 0 || parseFloat(field.val()) >= parseFloat($("#price").val())) {
                    errorMsg = "다시 입력하세요";
                    isValid = false;
                    
                }
            }
        }
    }

    // 유효하지 않으면 오류 메시지 표시 후 필드 초기화
    if (!isValid) {
        if (!field.data("errorShown")) {
            field.closest(".form-group").append("<span class='error' style='color: red;'>" + errorMsg + "</span>");
            field.data("errorShown", true);
        }
        field.val("");
        field.focus();
    } else {
        field.closest(".form-group").find("span.error").remove();
        field.data("errorShown", false);
    }

    return isValid;
}


// 전체 폼 유효성 검사 함수
function validateForm() {
    let isValid = true;

    // 모든 필드에 대해 유효성 검사 실행
    $("#price, #stock, #discountValue").each(function() {
        if (!validateField($(this))) {
            isValid = false;
            return false;
        }
    });

    return isValid;
}

// 수정하기
function goeditend() {
    
    const frm = document.boardFrm;
    frm.action = "<%=ctxPath%>/admin/productRegisterEditEnd.dk";
    frm.method = "post";
    frm.submit();
    console.log("goRegister 함수 호출됨");
}


//취소하기
function goReset() {
    history.back(); // 이전 페이지로 이동
}



</script>



<div class="container">
    <div class="row justify-content-center">
        <div class="col-md-6">
            <div class="card">
                <div class="card-header">
                    <h3 class="text-center mb-0">상품 수정</h3>
                </div>
<form name="boardFrm" enctype="multipart/form-data"  method="post" > 
               
             <div class="card-body">
    
				<div class="form-group">
				    <label for="category">제조회사 <span class="text-danger">*</span></label>
				    <select name="fk_manufacturer_seq" class="form-control infoData">
				        <option value="">:::선택하세요:::</option>
				        <c:forEach begin="1" end="3" var="i">
				            <option value="${i}" ${i == requestScope.fk_manufacturer_seq ? 'selected' : ''}>
				                ${i}. ${i == 1 ? '☆딜리스틱☆' : (i == 2 ? '★제로아워★' : '●닥터리브●')}
				            </option>
				        </c:forEach>
				    </select>
				    <span class="error"></span>
				</div>
        
                        <div class="form-group">
                            <label for="product">상품명 <span class="text-danger">*</span></label>
                            <div class="input-group">
                                <input type="text" name="product_name" id="product" class="form-control infoData" placeholder="상품명을 입력하세요"  value="${requestScope.product_name}">
                                <input type="hidden" name="product_seq" value="${requestScope.product_seq}">
                                <div class="input-group-append">
                                <span class="error"></span>   
                                   
                                </div>
                            </div>
                        </div>
                        <div class="form-group">
                            <label for="product">상품가격 <span class="text-danger">*</span></label>
                            <input type="text" name="base_price" id="price" class="form-control infoData" value="${requestScope.base_price}" >
                            <span class="error"></span>
                        </div>
                        
                        <div class="form-group">
                            <label for="product">제품설명 <span class="text-danger">*</span></label>
                            <input type="text" name="description" id="discription" class="form-control infoData" value="${requestScope.description}">
                            <span class="error"></span>
                        </div>
                         <div class="form-group">
                      <label for="stock">재고 <span class="text-danger">*</span></label>
                      <input type="number" name="stock" id="stock" class="form-control infoData" value="${requestScope.stock}">
                      <span class="error"></span>
                  </div>
                      
                         <div class="form-group">
                            <label>할인 종류 <span class="text-danger">*</span></label>
                            <div class="row mx-0">
                                <div class="col-sm-4">
                                    <div class="form-check">
                                        <input class="form-check-input infoData" type="radio" name="discount_type" id="percentageDiscount" value="percent" style="margin-left: 35px;" ${requestScope.discount_type == 'percent' ? 'checked' : ''}>
                                        <label class="form-check-label" for="percentageDiscount">퍼센트 할인</label>
                                    </div>
                                </div>
                                <div class="col-sm-4">
                                    <div class="form-check">
                                        <input class="form-check-input infoData" type="radio" name="discount_type" id="amountDiscount" value="amount" style="margin-left: 35px;" ${requestScope.discount_type == 'amount' ? 'checked' : ''}>
                                        <label class="form-check-label" for="amountDiscount">금액 할인</label>
                                    </div>
                                </div>
                                <div class="col-sm-4">
                                    <div class="form-check">
                                        <input class="form-check-input infoData" type="radio" name="discount_type" id="noDiscount" value="" style="margin-left: 35px;" ${requestScope.discount_type == '' ? 'checked' : ''}>
                                        <label class="form-check-label" for="noDiscount">할인 없음</label>
                                    </div>
                                </div>
                            </div>
                        </div>
                        <div class="form-group">
						    <label for="discountValue">할인 금액 또는 퍼센트 <span class="text-danger">*</span></label>
						    <input type="number" name="discount_amount" id="discountValue" class="form-control infoData" value="${requestScope.discount_amount}">
						    <span id="discountTypeNoDiscount" class="text-danger" style="display: none;">할인 없음</span>
						</div>
                     
                
                  
				             <div class="form-group">
				    <label for="category">상품타입 <span class="text-danger">*</span></label>
				    <select name="product_type" class="form-control infoData">
				        <option value="">:::선택하세요:::</option>
				        <c:forEach begin="1" end="4" var="i">
				            <option value="${i}" ${i == requestScope.product_type ? 'selected' : ''}>
				                ${i}. ${i == 1 ? '🍗닭가슴살🍗' : (i == 2 ? '🍱볶음밥🍱' : (i == 3 ? '🥯빵🥯' : '🧁디저트🧁'))}
				            </option>
				        </c:forEach>
				    </select>
				    <span class="error"></span>
				</div>

                 <!-- 제품 이미지 -->
				<div class="form-group">
				    <label for="main_pic">제품 이미지</label>
				    <div class="row">
				        <div class="col">				      
				            <input type="file" name="main_pic" class="infoData img_file" accept="image/*" value="${requestScope.product.main_pic}">
				            <span class="error"></span>
				        </div>
				    </div>
				</div>	
                  
                  
                  <!--상세 이미지 -->
                  <div class="form-group">
                     <label for="discountImage">상세 이미지</label>
                     <div class="row">
                        <div class="col">
                           <input type="file" name="description_pic" class="infoData img_file" accept='image/*' value="${requestScope.discription_pic}"/><span class="error"></span>
                        </div>   
                     </div>
                  </div>
                  
                           
               <!-- 이미지 미리보기 -->
               <div class="form-group">
                   <label for="previewImg">이미지 미리보기</label>
                   <img id="previewImg" width="300">
               </div>

                  
                        <div class="form-group text-center">
                            <button type="button" class="btn btn-success btn-submit mr-3" onclick="goeditend()">수정</button>
                            <button type="reset" class="btn btn-secondary btn-submit"  onclick="goReset()">취소</button>
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