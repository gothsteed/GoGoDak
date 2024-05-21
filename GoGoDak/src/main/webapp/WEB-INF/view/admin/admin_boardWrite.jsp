<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
    String ctxPath = request.getContextPath();
    //    /GoGoDak
%>
<jsp:include page="../header.jsp" />

 
<style type="text/css">
  
  
.boardWrite_container{
	text-align: center;
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
    frm.action = "<%=ctxPath%>/admin/notice.dk";
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
         			<strong style="font-size: 20pt;">공지사항</strong><p>작성하실 공지사항을 입력해주세요.</p>
       			</div>
       			<div class="card-body" id="tblBoardWrite">
					<form name="boardFrm" method="post">
		         		<div class="board-group">
		               		<label for="title">제목</label>
		               		<input type="text" name="title" id="title" placeholder="제목 입력" />
		           		</div>
	         
			            <div class="board-group">
				            <label for="content">내용</label>
				            <textarea name="content" id="content"  placeholder="내용 입력"></textarea>
			            </div>
						<div class="board-group">
							<label for="pic">이미지</label>
							<div>
								<input type="file" id="pic" name="pic" accept="image/*" class="form-control">
								<%-- <img src="images/image.jpg" class="img-fluid" />  --%>
							</div>
							<div id="imagePreview"></div>
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