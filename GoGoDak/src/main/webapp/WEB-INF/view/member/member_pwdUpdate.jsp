<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%
    String ctxPath = request.getContextPath();
	//    /GoGoDak
%>

<jsp:include page="../header.jsp" />

<style type="text/css">
	div.div_pwd {
		width: 70%;
  		height: 15%;
  		margin-bottom: 5%;
  		margin-left: 10%;
 	}
</style>

<script type="text/javascript">
	$(document).ready(function(){
		$("button.btn-success").click(function(){
			const pwd = $("input:password[name='pwd']").val();
			const pwd2 = $("input:password[id='pwd2']").val();
			
			if(pwd != pwd2){
				alert("암호가 일치하지 않습니다.");
				$("input:password[name='pwd']").val("");
				$("input:password[id='pwd2']").val("");
				return; // 메소드의 종료
			}
			else{
				const regExp_pwd = new RegExp(/^.*(?=^.{8,15}$)(?=.*\d)(?=.*[a-zA-Z])(?=.*[^a-zA-Z0-9]).*$/g); 
             	// 숫자/문자/특수문자 포함 형태의 8~15자리 이내의 암호 정규표현식 객체 생성 
	             
	           	const bool = regExp_pwd.test(pwd);   
	            
	           	if(!bool) { 
	               	alert("암호는 8글자 이상 15글자 이하에 영문자,숫자,특수기호가 혼합되어야만 합니다.");
	               	$("input:password[name='pwd']").val("");
	               	$("input:password[id='pwd2']").val("");
	               	return; 
	           	}
	           	else{ 
	           		const frm = document.pwdUpdateEndFrm;
	           		frm.action = "<%= ctxPath%>/login/pwdUpdateEnd.dk"; 
	           		frm.method = "post";
	           		frm.submit();
	           	}
			}
		}); // end of $("button.btn-success").click(function(){}) ----------
	}); // end of $(document).ready(function(){}) ----------
</script>

<c:if test="${requestScope.method == 'GET'}">
	<form name="pwdUpdateEndFrm">
		<div class="div_pwd" style="text-align: center;">
	    	<span style="color: blue; font-size: 12pt;">새암호</span><br/> 
		    <input type="password" name="pwd" size="25" />
		</div>
		
		<div class="div_pwd" style="text-align: center;">
	    	<span style="color: blue; font-size: 12pt;">새암호확인</span><br/>
		    <input type="password" id="pwd2" size="25" />
		</div>
		
		<input type="hidden" name="userid" value="${requestScope.id}" />
		
		<div style="text-align: center;">
	    	<button type="button" class="btn btn-success">암호변경하기</button>
	    </div>  
	</form>
</c:if>

<c:if test="${requestScope.method == 'POST'}">
	<div style="text-align: center; font-size: 14pt; color: navy;">
		<c:if test="${requestScope.n == 1}">
	    	사용자 ID ${requestScope.id}님의 비밀번호가 변경되었습니다.
	   	</c:if>
	   
	   	<c:if test="${requestScope.n == 0}">
	    	SQL구문 오류가 발생되어 비밀번호 변경을 할 수 없습니다.
	   	</c:if>
	</div>
</c:if>

<jsp:include page="../footer.jsp" />