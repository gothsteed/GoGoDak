<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%
    String ctxPath = request.getContextPath();
	//    /GoGoDak
%>

<jsp:include page="../header.jsp" />

<style type="text/css">
	.main-container {
	    display: flex;
	    justify-content: center;
	    align-items: center;
	    height: 100vh; 
	}
	.card-custom {
	    padding: 40px;
	    border-radius: 10px;
	    box-shadow: 0 2px 10px rgba(0, 0, 0, 0.1);
	    max-width: 600px;
	    width: 100%;
	}
	h1 {
	    font-family: 'Arial', sans-serif;
	    font-size: 24px;
	    font-weight: 700;
	    text-align: center;
	    margin-bottom: 20px;
	    color: #333;
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
	.btn-primary {
	    background-color: #333;
	    border: none;
	    padding: 10px;
	    font-size: 16px;
	    font-weight: 600;
	    border-radius: 5px;
	    transition: background-color 0.3s ease;
	    color: white;
	}
	.form-group label {
	 	font-weight: 600;
	    color: #555;
	}
	input.form-control{
		text-transform: none !important;
	}
	.btn-primary:hover {
	    background-color: #555;
	}
</style>
	
<script>
	$(document).ready(function(){
		const method = "${requestScope.method}";
	
		if(method == "GET"){
			$("div#div_findResult").hide();
		}
	
		if(method == "POST"){
			$("input:text[name='name']").val("${requestScope.name}");
			$("input:text[name='email']").val("${requestScope.email}");
		}
	
		$("button.btn-primary").click(function(){
			goFind();
		});
	
		$("input:text[name='email']").bind("keyup", function(e){
			if(e.keyCode == 13){
				goFind();
			}
		});
	
	}); // end of $(document).ready(function(){}) ----------
	
	function goFind(){
		
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
        
        const frm = document.idFindFrm;
        frm.action = "<%= ctxPath%>/login/idFind.dk";
        frm.method = "post";
        frm.submit();
		
	} // end of function goFind() ----------
</script>

<div class="main-container">
    <div class="card card-custom">
        <a href="<%= ctxPath%>/login/login.dk" class="mb-3 d-inline-block">←</a>
        <h1>아이디 찾기</h1>
        <form name="idFindFrm">
            <div class="form-group">
                <label for="userName">이름</label>
                <input type="text" name="name" id="userName" class="form-control" placeholder="이름을 입력해주세요">
            </div>
            <div class="form-group" id="emailGroup">
                <label for="userEmail">이메일로 찾기</label>
                <input type="text" name="email" id="userEmail" class="form-control" placeholder="이메일을 입력해주세요">
            </div>
            <button type="button" class="btn btn-primary btn-block">확인</button>
            
            <div class="my-3 text-center" id="div_findResult">
			   ID : <span style="color: red; font-size: 16pt; font-weight: bold;">${requestScope.id}</span>
			</div>
        </form>
    </div>
</div>

<jsp:include page="../footer.jsp" />
