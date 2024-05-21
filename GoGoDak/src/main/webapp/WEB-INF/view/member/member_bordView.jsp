<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %> 
	
	
<%
String ctxPath = request.getContextPath();
//    /GoGoDak
%>

<jsp:include page="../header.jsp" />
<style type="text/css">

.onlyLog{
	margin: 4% auto;
}	

.titleArea{
    color: #1a1a1a;
    font-size: 32px;
    font-weight: 700;
    text-align: center;
    line-height: 40px;
    text-transform: uppercase;
    
 }
	
.bordPic{
	border:solid 0px red;
	max-width: 80%;
	margin: 0 auto;
	display: flex;
	justify-content: center;

}

.btn-outline-dark{
	margin-top: 5%;
}

</style>

<script type="text/javascript">

function goEdit() {
		
	const frm = document.boardEditinfoFrm;
    frm.action = "<%=ctxPath%>/admin/noticeEdit.dk";
    frm.method = "post";
    frm.submit();

}

</script>



	<div class="container" style="padding: 3% 0;" >
		<h2 class="titleArea ">
			<font face="Arial">공지사항</font>
		</h2>
		<br>
	
	<!-- 작성자가 아닌 경우 글에 대한 수정, 삭제 권한 부여 x -->
	<div align="right" class="onlyLog">
		<c:if test="${not empty sessionScope.loginuser and sessionScope.loginuser.id == 'admin'}">	
				<button type="button" class="btn btn-light" onclick="goEdit()">수정</button>
		</c:if>
		<c:if test="${not empty sessionScope.loginuser and sessionScope.loginuser.id == 'admin'}">		
				<button type="button" class="btn btn-light" onclick="goDelete()">삭제</button> <%--삭제하는 이벤트만들기 --%>
		</c:if>
	</div>
	
	<!-- 작성자가 아닌 경우 글에 대한 수정, 삭제 권한 부여 x -->
	<input type="hidden" name="board_seq" value="${requestScope.bvo.board_seq}">
	<table class="table">
		<thead>
			<tr>
				<th class="boardView">제목</th>
	            <th>${requestScope.bvo.title}</th>
	        </tr>
	        <tr>
				<th class="boardView">작성자</th>
	            <th>고고닭</th>
	        </tr>
	    </thead>
    </table>
    	
    	<div>
    		<span>${requestScope.bvo.content}</span>
    	</div>
    	<div class="bordPic">
	 		<img src="<%= ctxPath%>/images/board/${requestScope.bvo.pic}"> <%--이미지 들어갈수있는 태그만들기 --%>   
	 	</div>   
            
    	
                
                
    	<button type="button" class="btn btn-outline-dark" onclick="location.href='<%= ctxPath%>/member/notice.dk'">목록</button>
            
    
	
	
	
	</div>
	
	<form name="boardEditinfoFrm" method="post" style="display: none;">
		<input type="text" name="board_seq" value="${requestScope.bvo.board_seq}">
		<input type="text" name="title" value="${requestScope.bvo.title}">
		<input type="text" name="content" value="${requestScope.bvo.content}">
	</form>

<jsp:include page="../footer.jsp" />