<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%
String ctxPath = request.getContextPath();
//    /GoGoDak
%>



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



<jsp:include page="header.jsp" />


<div class="container" style="padding: 3% 0;">
	<h2 class="titleArea ">
		<font face="Arial">공지사항</font>
	</h2>
	<br>
	<div class="text-center">
		<img src="images/notice.jpg" class="img-fluid" />
	</div>
	<br> &nbsp;
	<%-- if문 사용하여 어드민일때만 보이게하기 --%>
	<%-- <c:if test="${not empty sessionScope.loginuser and sessionScope.loginuser.userid == 'admin'}"> admin 으로 로그인 했으면 --%>
	<div style="display:flex;justify-content:flex-end;">
	<button type="button" class="btn btn-secondary" onclick="location.href='./boardWrite.jsp'" >글쓰기</button>
	</div>
	<%--</c:if> --%>
	<%-- if문 사용하여 어드민일때만 보이게하기 --%>
		
	&nbsp;
	<table class="table table-hover" id="memberTbl">
		<thead>
			<tr>
				<th>번호</th>
				<th>제목</th>
				<th>작성자</th>
			</tr>
		</thead>

		<tbody>
			<tr>
				<td>1번</td>
				<td><a href="./bordView.jsp">고고닭을 이용해주시는 분들 너무 감사합니당</a></td>
				<td>고고닭</td>
			</tr>
			<tr>
				<td>번</td>
				<td><a href="./bordView.jsp">고고닭을 이용해주시는 분들 행복하세요</a></td>
				<td>고고닭</td>
			</tr>

		</tbody>
	</table>

	&nbsp;
	<form class="text-center mb-5 " name="member_search_frm" style="align-items: baseline;">

		<select name="searchType" style="height: 41px;">
			<option value="">제목</option>
			<option value="name">회원명</option>
			<option value="userid">아이디</option>
			<option value="email">이메일</option>
		</select> &nbsp; 
		
		<input type="text" name="searchWord" style="height: 40px;" />
		<input type="text"style="display: none;" />
			

		<button type="button" class="btn btn-secondary" onclick="goSearch()">찾기</button>

	</form>



	<div id="pageBar">
		<nav></nav>
	</div>
</div>
<jsp:include page="footer.jsp" />

