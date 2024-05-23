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




	$("table#questioTbl tr.questioninfo").click(e=>{
		
		const question_seq = $(e.target).parent().children(".question_seq").text();
	
		const frm = document.QuestionDetail_frm;
		frm.question_seq.value = question_seq;
		
		<%-- frm.action = "<%= ctxPath%>/member/memberOneDetail.up" --%>
		frm.action = "<%= ctxPath%>/member/questionView.dk";
		frm.method = "post";
		frm.submit();
	});	

	

});//end of ------------------------





	
</script>





<div class="container" style="padding: 3% 0;">
	<h2 class="titleArea ">
		<font face="Arial">1:1문의</font>
	</h2>
	<br>
	<div class="text-center">
		<img src="<%= ctxPath%>/images/board/member_inquiry.jpg" class="img-fluid" />
	</div>
	<br> &nbsp;
	
	<div style="display:flex;justify-content:flex-end;">
		<button type="button" class="btn btn-secondary" onclick="location.href='<%= ctxPath %>/member/questionWrite.dk'" >고고닭에게 문의</button>
	</div>
		
	&nbsp;
	<table class="table table-hover" id="questioTbl">
		<thead>
			<tr>
				<th>번호</th>
				<th>제목</th>
				<th>작성일</th>
				<th>작성자</th>
				<th>답변</th>
			</tr>
		</thead>

			<tbody>
				<c:if test="${not empty requestScope.questionList}">
					<c:forEach var="question" items="${requestScope.questionList}">
						<c:if test="${sessionScope.loginuser.id == question.id || sessionScope.loginuser.id == 'admin'}">
							<tr class="questioninfo">
				       			<td class="question_seq">${question.question_seq}</td>  <%--번호--%>    
								<td>${question.title}</td> 							    <%--제목 --%>
								<td>${question.ragisterdate}</td>                       <%--작성일 --%>
								<td>${question.id}</td> 								<%--작성자 --%>
								<td>안함</td>                        						<%--답변상황 --%>
							</tr>
						</c:if>
					</c:forEach>
				</c:if>
				
				<c:if test="${empty requestScope.questionList}">
	          		<td colspan="5">검색 결과가 없습니다.</td>
	         	</c:if>
		</tbody>
			

	</table>

	&nbsp;
	<form class="text-center mb-5 " name="member_search_frm" style="align-items: baseline;">


		<select name="searchType" style="height: 41px;">
			<option value="">전체</option>
			<option value="name">미답변</option>
			<option value="userid">답변완료</option>
		</select> &nbsp;


		<select name="searchType" style="height: 41px;">
			<option value="">일주일</option>
			<option value="name">한달</option>
			<option value="userid">세달</option>
			<option value="userid">전체</option>
		</select> &nbsp;


		<select name="searchType" style="height: 41px;">
			<option value="">제목</option>
			<option value="name">내용</option>
			<option value="userid">글쓴이</option>
			<option value="userid">아이디</option>
			<option value="email">이메일</option>
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

<form name="QuestionDetail_frm">
	<input type="hidden" name="question_seq"/> <%--한명의 회원을 넘겨주기위해--%>
	<input type="hidden" name="goBackURL" value="${requestScope.currentURL}" /> <%--한명의 회원을 넘겨주기위해--%>
</form>




<jsp:include page="../footer.jsp" />

