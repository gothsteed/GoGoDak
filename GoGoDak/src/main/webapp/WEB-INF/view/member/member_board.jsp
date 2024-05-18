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


<script type="text/javascript">
	
	
		
	}//end of function goSearch()-----------------	
			
			
	
</script>	


<jsp:include page="../header.jsp" />


<div class="container" style="padding: 3% 0;">
	<h2 class="titleArea ">
		<font face="Arial">공지사항</font>
	</h2>
	<br>
	<div class="text-center">
		<img src="<%= ctxPath%>/images/board/notice.jpg" class="img-fluid" />
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
			<tr class="boardInfo">
       			<td>1</td><%--순서--%>    
				<td>고고닭</td> <%--제목 --%>
				<td>고고닭</td> <%-- 글쓴이 --%>
			</tr>
		</tbody>
	</table>

	&nbsp;
	<form class="text-center mb-5 " name="member_search_frm" style="align-items: baseline;">

		<select name="searchType" style="height: 41px;">
			<option value="">제목</option>
		</select> &nbsp; 
		
		<input type="text" name="searchWord" style="height: 40px;" />
		<input type="text"style="display: none;" />
			

		<button type="button" class="btn btn-secondary" onclick="goSearch()">찾기</button>

	</form>



	<div id="pageBar">
		<nav></nav>
	</div>
</div>
<jsp:include page="../footer.jsp" />
