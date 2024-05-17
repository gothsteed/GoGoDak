<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>	
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
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
	
	$(document).ready(function() {

		if( "${requestScope.searchType}" != "" &&
			"${requestScope.searchWord}" != "" ){
			
			$("select[name='searchType']").val("${requestScope.searchType}");
			$("input:text[name='searchWord']").val("${requestScope.searchWord}");
		}
		
		if( "${requestScope.sizePerPage}" != "" ){ //10개 5개 등 내가 선택한 sizeperpage 를 유지시키는것
				
			$("select[name='sizePerPage']").val("${requestScope.sizePerPage}");
		}
		
		
		$("input:text[name='searchWord']").bind("keydown" , function(e){
			if(e.keyCode == 13){
				goSearch();
			}
			
		
			
			
		});

		// **** select 태그에 대한 이벤트는 click 이 아니라 change 이다. **** //
		
		$("select[name='sizePerPage']").bind("change", function(){
			const frm = document.member_search_frm; //회원을 찾는 폼
			// frm.action = "memberList.up";
			// frm.method = "get";
			frm.submit();
		});
		
		
		
		// **** 특정 회원을 클릭하면 그 회원의 상세정보를 보여주도록 한다. **** //
		$("table#boardTbl tr.boardInfo").click(e=>{
			//alert($(e.target).parent().html() );
			//const userid = $(e.target).parent().find(".userid").text();
			//   			    선택한곳의. 부모한테서.찾는다.userid를가지고있는class의.글자를
			
			const title = $(e.target).parent().children(".title").text();
			//		            선택한곳의. 부모한테서.찾는다.userid를가지고있는 자식의.글자를
			alert(title);
		
			const frm = document.memberOneDetail_frm;
			frm.userid.value = userid;
			
			<%-- frm.action = "<%= ctxPath%>/member/memberOneDetail.up" --%>
			frm.action = "${pageContext.request.contextPath}/member/memberOneDetail.up";
			frm.method = "post";
			frm.submit();
		});
	     
	});//end of $(document).ready(function() {}---------------------
	
	function goSearch(){
		
		
		const searchType = $("select[name='searchType']").val();
		
		if(searchType == ""){
			alert("검색대상을 선택하세요!!");
			return;//종료
		}
		
		const frm = document.member_search_frm; //회원을 찾는 폼
		// frm.action = "memberList.up";
		// form 태그에 action 이 명기되지 않았으면 현재보이는 URL 경로로 submit 되어진다.
		// frm.method = "get";
		// form 태그에 method 를 명기하지 않으면 "get" 방식이다.
		frm.submit();
		
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
			<c:if test="${not empty requestScope.boardList}"> <%-- 공지사항에 글이 있을경우 --%>
				<c:forEach var="boardvo" items="${requestScope.boardList}" varStatus="status">
					<tr class="boardInfo">
						<%-- fmt:parseNumber 은 문자열을 숫자형식으로 형변환 시키는 것이다. --%>
	          			<fmt:parseNumber var="currentShowPageNo" value="${requestScope.currentShowPageNo}"/>
	          			<fmt:parseNumber var="sizePerPage" value="${requestScope.sizePerPage}"/>
	          			<td>${(requestScope.totalMemberCount) -( currentShowPageNo - 1 ) * sizePerPage-(status.index)}</td><%--순서--%>    
						<td class="title">${boardvo.title}</td> <%--제목 --%>
						<td></td> <%-- 글쓴이 --%>
					</tr>
				</c:forEach>
			</c:if>
			
			<c:if test="${empty requestScope.boardList}"><%-- 공지사항에 글이 없을경우 --%>
				<td colspan="5">공지사항 글이 존재하지 않습니다</td>
			</c:if>
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

