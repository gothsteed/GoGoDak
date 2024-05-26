<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%
    String ctxPath = request.getContextPath();
	//    /GoGoDak
%>

<jsp:include page="../header.jsp" />

<style type="text/css">
	body {
	    font-family: 'Arial', sans-serif;
	    margin: 0;
	    padding: 0;
	    display: flex;
	    flex-direction: column;
	    min-height: 100vh;
	}
	.main-container {
	  	display: flex;
	    justify-content: center;
	    align-items: center;
	    flex-grow: 1;
	    padding-top: 50px; 
	    padding-bottom: 50px; /* Adjust based on footer height */
	}
	.angry {
	    max-width: 600px;
	    width: 100%;
	    background: #fff;
	    padding: 40px 30px;
	    border-radius: 10px;
	    box-shadow: 0 2px 10px rgba(0, 0, 0, 0.1);
	}
	h1 {
	    font-family: 'Arial', sans-serif;
	    font-size: 24px;
	    font-weight: 700;
	    text-align: center;
	    margin-bottom: 20px;
	    color: #333;
	}
	.form-group label {
		font-weight: 600;
	    color: #555;
	}
	.btn-primary {
	    width: 100%;
	    background-color: #333;
	    border: none;
	    padding: 12px;
	    font-size: 16px;
	    font-weight: 600;
	    border-radius: 5px;
	}
	.btn-primary:hover {
	    background-color: #555;
	}
	a {
	    color: gray;
	    text-decoration: none;
	    background-color: transparent;
	}
	a:hover {
	    text-decoration: none;
	    color: gray;
	}
	input.form-control{
		text-transform: none !important;
	}
</style>

<script type="text/javascript">
	$(document).ready(function(){
		
		const method = "${requestScope.method}";
		
		if(method == "GET"){
			$("div#div_findResult").hide();
		}
		
		if(method == "POST"){
			$("input:text[name='id']").val("${requestScope.id}");
			$("input:text[name='name']").val("${requestScope.name}");
			$("input:text[name='email']").val("${requestScope.email}");
			
			if(${requestScope.isExist == true && requestScope.sendMailSuccess == true}){
				$("button.btn-primary").hide();
			}
		}
		
		$("button.btn-primary").click(function(){
			goFind();
		});
		
		$("input:text[name='email']").bind("keyup", function(e){
			if(e.keyCode == 13){
				goFind();
			}
		});
		
		$("button.btn-warning").click(function(){
			const input_confirmCode = $("input:text[name='input_confirmCode']").val().trim();
			
			if(input_confirmCode == ""){
				alert("인증코드를 입력하세요!!");
				return;
			}

			const frm = document.verifyCertificationFrm
			frm.userCertificationCode.value = input_confirmCode;
			frm.id.value = $("input:text[name='id']").val();
			
			frm.action = "<%= ctxPath%>/login/verifyCertification.dk";
			frm.method = "post";
			frm.submit();
			
		});
		
	}); // end of $(document).ready(function(){}) ----------

	function goFind(){
		
		const id = $("input:text[name='id']").val().trim();
		
		if(id == ""){
			alert("아이디를 입력하세요!!");
			return;
		}
		
		const name = $("input:text[name='name']").val().trim();
		
		if(name == ""){
			alert("이름을 입력하세요!!");
			return; 
		}
		 
		const email = $("input:text[name='email']").val();
        const regExp_email = new RegExp(/^[0-9a-z]([-_\.]?[0-9a-z])*@[0-9a-z]([-_\.]?[0-9a-z])*\.[a-z]{2,3}$/i);

        const bool = regExp_email.test(email);

        if(!bool){ 
        	alert("이메일을 올바르게 입력하세요!!");
			return;
        }
        
        const frm = document.pwdFindFrm;
        frm.action = "<%= ctxPath%>/login/pwdFind.dk";
        frm.method = "post";
        frm.submit();
		
	} // end of function goFind() ----------
</script>

<div class="main-container">
    <div class="angry">
        <a href="<%= ctxPath%>/login/login.dk" class="mb-3 d-inline-block">←</a>
        <h1>비밀번호 찾기</h1>
        <form name="pwdFindFrm">
            <div class="form-group">
                <label for="userId">아이디</label>
                <input type="text" name="id" id="userId" class="form-control" placeholder="아이디를 입력해주세요">
            </div>
            <div class="form-group">
                <label for="userName">이름</label>
                <input type="text" name="name" id="userName" class="form-control" placeholder="이름을 입력해주세요">
            </div>
            <div class="form-group">
                <label for="userEmail">이메일로 찾기</label>
                <input type="text" name="email" id="userEmail" class="form-control" placeholder="이메일을 입력해주세요">
            </div>
            <button type="button" class="btn btn-primary btn-block">확인</button>
            
            <div class="my-3 text-center" id="div_findResult">
			   <c:if test="${requestScope.isExist == false}">
				   <span style="color: red;">입력하신 정보로 가입된 회원은 존재하지 않습니다.</span>
			   </c:if>
			   
			   <c:if test="${requestScope.isExist == true && requestScope.sendMailSuccess == true}">
				   <span>
				       인증번호가 <span style="color: #fbc02d; font-size: 16pt; font-weight: bold;">${requestScope.email}</span> 로 발송되었습니다.<br>전송된 인증번호를 입력해주세요.
				   </span>
				   <br><br>
				   <input type="text" name="input_confirmCode" style="border-radius: 5px; outline: none; text-align: center;" />
				   <br>
				   <button type="button" class="btn btn-warning">인증하기</button>
			   </c:if>
			   
			   <c:if test="${requestScope.isExist == true && requestScope.sendMailSuccess == false}">
			   	   <span style="color: red;">메일발송이 실패했습니다.</span>
			   </c:if>
			</div>
        </form>
    </div>
</div>

<form name="verifyCertificationFrm">
	<input type="hidden" name="userCertificationCode" />
	<input type="hidden" name="id" />
</form>

<jsp:include page="../footer.jsp" />

<!-- Bootstrap JS -->
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.16.0/umd/popper.min.js"></script>
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js"></script>
