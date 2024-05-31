<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %> 

<%
String ctxPath = request.getContextPath();
//    /GoGoDak
%>

<jsp:include page="../header.jsp" />

<style type="text/css">


table#memberTbl {
	width: 100%;
	margin: 0 auto;
}

table#memberTbl th, table#memberTbl td {
	text-align: center;
	font-size: 12pt;
}

table#memberTbl tr.memberInfo:hover {
	background-color: #e6ffe6;
	cursor: pointer;
}

form[name="member_search_frm"] {
	border: solid 0px red;
	width: 50%;
	margin: 0 auto 3% auto;
	display: flex;
	align-items: center;
}

form[name="member_search_frm"] button.btn-secondary {
	width: 20%;
	height: 5%;
	margin-left: 2%;
	margin-right: 2%;
}

div#pageBar {
	border: solid 0px red;
	width: 80%;
	margin: 3% auto 0 auto;
	display: flex;
}

div#pageBar>nav {
	margin: auto;
}

.titleArea{
    color: #1a1a1a;
    font-size: 32px;
    font-weight: 700;
    text-align: center;
    line-height: 40px;
    text-transform: uppercase;
    
 }
 
 a{
 	color:black !important;
 }
 


</style>


<script type="text/javascript">


$(document).ready(function() {




	$("table#boardTbl tr.boardInfo").click(e=>{
		
		const board_seq = $(e.target).parent().children(".board_seq").text();
	
		const frm = document.NoticeDetail_frm;
		frm.board_seq.value = board_seq;
		
		<%-- frm.action = "<%= ctxPath%>/member/memberOneDetail.up" --%>
		frm.action = "<%= ctxPath%>/member/noticeDetail.dk";
		frm.method = "post";
		frm.submit();
	});	


});//end of ------------------------


	
</script>	





<div class="container" style="padding: 3% 0;">
	<h2 class="titleArea ">
		<font face="Arial">공지사항</font>
	</h2>
	<br>
	<div class="text-center">
		<img src="<%= ctxPath%>/images/board/GoGodaknotice.jpg" class="img-fluid" />
	</div>
	<br>
	<div align="right" class="onlyLog">
		<c:if test="${not empty sessionScope.loginuser and sessionScope.loginuser.id == 'admin'}">	
			<button type="button" class="btn btn-light" onclick="location.href='<%= ctxPath%>/admin/notice.dk'">공지사항 작성</button>
		</c:if>
	</div> 
	<br>
	&nbsp;
	&nbsp;
	<table class="table table-hover" id="boardTbl">
		<thead>
			<tr>
				<th>번호</th>
				<th>제목</th>
				<th>작성자</th>
			</tr>
		</thead>

		<tbody>
			<c:if test="${not empty requestScope.boardList}">
				<c:forEach var="board" items="${requestScope.boardList}"  varStatus="status">
					<tr class="boardInfo">
					<fmt:parseNumber var="currentPage" value="${requestScope.currentPage}"/>
        			<fmt:parseNumber var="blockSize" value="${requestScope.blockSize}"/>
        			
        				<td>${(requestScope.totalBoardCount) -( currentPage - 1 ) * blockSize-(status.index)}</td>
		       			<td class="board_seq" style="display:none;">${board.board_seq}</td><%--순서--%>    
						<td>${board.title}</td> <%--제목 --%>
						<td>고고닭</td> <%-- 글쓴이 --%>
					</tr>
				</c:forEach>
			</c:if>
			
			<c:if test="${empty requestScope.boardList}">
          		<td colspan="5">데이터가 존재하지 않습니다</td>
         	</c:if>
		</tbody>
	</table>

	&nbsp;
	<form class="text-center mb-5 " name="member_search_frm" style="align-items: baseline;">

		<select name="searchType" style="height: 41px;">
			<option value="">번호</option>
			<option value="title">제목</option>
		</select> &nbsp; 
		
		<input type="text" name="searchWord" style="height: 40px;" />
		<input type="text"style="display: none;" />
			

		<button type="button" class="btn btn-secondary" onclick="goSearch()">찾기</button>

	</form>



	<div id="pageBar">
       <nav>
          <ul class="pagination">${requestScope.pageBar}</ul>
       </nav>
    </div>
</div>

<form name="NoticeDetail_frm">
	<input type="hidden" name="board_seq" /> <%--한명의 회원을 넘겨주기위해--%>
	<input type="hidden" name="goBackURL" value="${requestScope.currentURL}" /> <%--한명의 회원을 넘겨주기위해--%>
</form>

<jsp:include page="../footer.jsp" />





















