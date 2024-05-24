<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
    String ctxPath = request.getContextPath();
    //    /GoGoDak
%>
<jsp:include page="../header.jsp" />

 
<style type="text/css">
  
  
.questionWrite_container{
	text-align: center;
}
      	
</style>
    
<script type="text/javascript">




function goEdit() {
	
	
	const frm = document.boardEditFrm;
    frm.action = "<%=ctxPath%>/member/questionEditEnd.dk";
    frm.method = "post";
    frm.submit();
}


function goReset() {
    history.back(); // 이전 페이지로 이동
}

</script>
    
      

<div class="questionWrite_container">
	<div class="row justify-content-center">
		<div class="col-md-8">
			<div class="card">
				<div class="card-header">
         			<strong style="font-size: 20pt;"> 1:1 문의 </strong><p>문의하실 내용을 입력해주세요.</p>
       			</div>
       			<div class="card-body" id="tblBoardWrite">
					<form name="questionFrm" method="post">
		         		<div class="board-group">
		               		<label for="title">제목</label>
		               		<input type="text" name="title" id="title" placeholder="제목 입력" />
		           		</div>
	         
			            <div class="board-group">
				            <label for="content">내용</label>
				            <textarea name="content" id="content" placeholder="문의하실 내용을 입력해 주세요"></textarea>
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