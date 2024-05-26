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

	  $("span.error").hide();


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
                  
                       <%-- 
                        <div class="form-group">
                            <label for="category">카테고리 <span class="text-danger">*</span></label>
                            <select name="fk_cnum" class="form-control infoData">
                                <option value="">:::선택하세요:::</option>
                                   
                     <option value="1">닭가슴살</option>
                     <option value="2">볶음밥</option>
                     <option value="3">디저트</option>
                     <option value="4">빵</option>
                               <%-- 
                                <c:forEach var="cvo" items="${requestScope.productList}">
                                    <option value="${cvo.cnum}">${cvo.cname}</option>
                                </c:forEach>
                               
                            </select>
                            <span class="error">필수입력</span>
                        </div>
                        --%>
                        
                        <%-- 
                        
                           <div class="form-group">
                            <label for="category">제조업체고유번호<span class="text-danger">*</span></label>
                            <select name="fk_manufacturer_seq" class="form-control infoData">
                                <option value="">:::선택하세요:::</option>
                                   
                     <option value="1">1</option>
                     <option value="2">2</option>
                     <option value="3">3</option>
                     <option value="4">4</option>
                               
                       
                            </select>
                            <span class="error">필수입력</span>
                  </div>
                  --%>
                        
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
                                <div class="col-sm-6">
                                    <div class="form-check">
                                        <input class="form-check-input infoData" type="radio" name="discount_type" id="percentageDiscount" value="percent" checked>
                                        <label class="form-check-label" for="percentageDiscount">퍼센트 할인</label>
                                      
                                    </div>
                                </div>
                                <div class="col-sm-6">
                                    <div class="form-check">
                                        <input class="form-check-input infoData" type="radio" name="discount_type" id="amountDiscount" value="amount">
                                        <label class="form-check-label" for="amountDiscount">금액 할인</label>
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