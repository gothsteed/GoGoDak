<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c"   uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="fn"  uri="http://java.sun.com/jsp/jstl/functions"%>
        
<jsp:include page="../header_admin.jsp" />

<style type="text/css">   
	table#memberTbl {
    	width: 90%;
      	margin: 0 auto;
   	}
   	table#memberTbl th {
      	text-align: center;
      	font-size: 14pt;
   	}
   	table#memberTbl tr.memberInfo:hover {
      	cursor: pointer;
   	}
   	form[name="member_search_frm"] {
      	width: 90%;
      	margin: 0 auto 2% auto;
   	}
   	form[name="member_search_frm"] button.btn {
      	margin-left: 2%;
   	}
   	div#pageBar {
      	width: 90%;
      	margin: 3% auto 0 auto;
      	display: flex;
   	}
   	div#pageBar > nav {
      	margin: auto;
   	}
   	.my.pagination > .active > a, 
	.my.pagination > .active > span, 
	.my.pagination > .active > a:hover, 
	.my.pagination > .active > span:hover, 
	.my.pagination > .active > a:focus, 
	.my.pagination > .active > span:focus {
	  	background: #fbc02d;
	  	border-color: #fbc02d;
	}
	.page-link{
		color: #212529;
	}
</style>

<script type="text/javascript">
	$(document).ready(function(){

		if("${requestScope.searchType}" != "" && "${requestScope.searchWord}" != "" ){ 
			$("select[name='searchType']").val("${requestScope.searchType}");	
			$("input:text[name='searchWord']").val("${requestScope.searchWord}");
		}
		
		if("${requestScope.sizePerPage}" != ""){ 
			$("select[name='sizePerPage']").val("${requestScope.sizePerPage}");	
		}
		
		$("input:text[name='searchWord']").bind("keydown", function(e){
			if(e.keyCode == 13){
				goSearch();
			}
		});
		
		$("select[name='sizePerPage']").bind("change", function(){ 
			const frm = document.member_search_frm;
			frm.action = "member.dk";
			frm.post = "get";
			frm.submit();
		});
		
		$("table#memberTbl tr.memberInfo").click( e => {
		 	const id = $(e.target).parent().children(".id").text(); 
		 	
		 	const frm = document.memberOneDetail_frm;
		 	frm.id.value = id;
		 	frm.action = "${pageContext.request.contextPath}/admin/memberOneDetail.dk";
		 	frm.method = "post";
			frm.submit();
		});
		
	}); // end of $(document).ready(function(){}) ----------
	
	///////////////////////////////////////////////////////////////

	// Function Declaration
	function goSearch(){
		const searchType = $("select[name='searchType']").val();
		
		if(searchType == ""){ 
			alert("검색대상을 선택하세요!!");
			return; 
		}
		
		const frm = document.member_search_frm;
		frm.action = "member.dk";
		frm.post = "get";
		frm.submit();
		
	} // end of function goSearch() ----------
</script>

	<div class="container" style="padding: 3% 0;">
		<h2 class="text-center mb-5"><i class="fa-solid fa-quote-left"></i>&nbsp;&nbsp;회원전체 목록&nbsp;&nbsp;<i class="fa-solid fa-quote-right"></i></h2>
	   
	   	<form name="member_search_frm" class="row justify-content-between" style="align-items: center;">
	    	<div class="col-6">
    		   	<select name="searchType" style="padding: 3px;">
		        	<option value="">검색대상</option>
		         	<option value="name">회원명</option>
		         	<option value="id">아이디</option>
		         	<option value="email">이메일</option>
		      	</select>
		      	&nbsp;
		      	<input type="text" name="searchWord" style="padding: 2px;" /> 
		        <input type="text" style="display: none;" />
		      
		      	<button type="button" class="btn btn-secondary" onclick="goSearch()">검색</button>
	    	</div>
	    	
	      	<div class="col-3">
	      		<span style="font-size: 13pt; font-weight: bold;">페이지당 회원명수&nbsp;&nbsp;</span>
	      		<select name="sizePerPage" style="padding: 3px;">
		        	<option value="10">10명</option>
		         	<option value="5">5명</option>
		         	<option value="3">3명</option>      
		      	</select>
	      	</div>
	   	</form>
	   
	   	<table class="table table-bordered table-hover" id="memberTbl">
	    	<thead style="background: #fbc02d; color: #fff">
	        	<tr>
	            	<th>번호</th>
	             	<th>아이디</th>
	             	<th>회원명</th>
	             	<th>이메일</th>
	             	<th>성별</th>
	          	</tr>
	      	</thead>
	      
	      	<tbody>
				<c:if test="${not empty requestScope.memberList}">
					<c:forEach var="membervo" items="${requestScope.memberList}" varStatus="status" >
						<tr class="memberInfo">
							<fmt:parseNumber var="currentShowPageNo" value="${requestScope.currentShowPageNo}" />
							<fmt:parseNumber var="sizePerPage" value="${requestScope.sizePerPage}" />
							
							<td>${(requestScope.totalMemberCount) - (currentShowPageNo - 1) * sizePerPage - (status.index)}</td>	
							<td class="id">${membervo.id}</td>
							<td>${membervo.name}</td>
							<td>${membervo.email}</td>
							<td>
								<c:choose>
									<c:when test="${membervo.jubun == '남'}">남</c:when>
									<c:otherwise>여</c:otherwise>
								</c:choose>
							</td>
						</tr>
					</c:forEach>
				</c:if>	
				
				<c:if test="${empty requestScope.memberList}">
					<tr>
						<td colspan="5">데이터가 존재하지 않습니다.</td>
					</tr>
				</c:if>	
	  
	      	</tbody>
	   	</table>     
	
	    <div id="pageBar">
	    	<nav>
	        	<ul class="pagination my">${requestScope.pageBar}</ul>
	       	</nav>
	    </div>
	</div>
	
	<form name="memberOneDetail_frm">
		<input type="hidden" name="id" />
		<input type="hidden" name="goBackURL" value="${requestScope.currentURL}" />
	</form>

   </body>
</html>