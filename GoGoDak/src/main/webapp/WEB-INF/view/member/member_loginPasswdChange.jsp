<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%
    String ctxPath = request.getContextPath();
	//    /GoGoDak
%>

<jsp:include page="../header.jsp" />

<style type="text/css">

</style>

<script type="text/javascript">
   	$(document).ready(function(){
		$("button.btn-warning").click(function(){
			const pwd = $("input:password[name='pwd']").val();
			const pwd2 = $("input:password[id='pwd2']").val();
			
			if(pwd != pwd2){
				alert("암호가 일치하지 않습니다.");
				$("input:password[name='pwd']").val("");
				$("input:password[id='pwd2']").val("");
				return;
			}
			else{
				const regExp_pwd = new RegExp(/^.*(?=^.{8,15}$)(?=.*\d)(?=.*[a-zA-Z])(?=.*[^a-zA-Z0-9]).*$/g);
	           	const bool = regExp_pwd.test(pwd);   
	            
	           	if(!bool) { 
	               	alert("암호는 영문자, 숫자, 특수기호가 혼합된 8~15 글자로 입력하세요.");
	               	$("input:password[name='pwd']").val("");
	               	$("input:password[id='pwd2']").val("");
	               	return; 
	           	}
	           	else{ 
	           		const frm = document.pwdUpdateEndFrm;
	           		frm.action = "<%= ctxPath%>/login/loginPasswdChange.dk"; 
	           		frm.method = "post";
	           		frm.submit();
	           	}
			}
		}); // end of $("button.btn-success").click(function(){}) ----------
	}); // end of $(document).ready(function(){}) ----------
</script>


	<form name="pwdUpdateEndFrm" style="width: 30%; margin: 5% auto;">
		<div class="div_pwd" style="text-align: center;">
	    	<span style="color: #333; font-size: 14pt; font-weight: bold;">새로운 암호</span><br/> 
		    <input type="password" name="pwd" size="25" style="text-align: center; margin-top: 2%; outline: none;" />
		</div>
		
		<div class="div_pwd" style="text-align: center;">
	    	<span style="color: #333; font-size: 14pt; font-weight: bold;">새로운 암호확인</span><br/>
		    <input type="password" id="pwd2" size="25" style="text-align: center; margin-top: 2%; outline: none;" />
		</div>
		
		<input type="hidden" name="id" value="${requestScope.id}" />
		
		<div style="text-align: center;">
	    	<button type="button" class="btn btn-warning">암호변경하기</button>
	    </div>  
	</form>


<jsp:include page="../footer.jsp" />