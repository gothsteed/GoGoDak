<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%
    String ctxPath = request.getContextPath();
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
</style>

<div class="main-container">
    <div class="angry">
        <a href="<%= ctxPath %>/login/login.dk" class="mb-3 d-inline-block">←</a>
        <h1>비밀번호 찾기</h1>
        <form>
            <div class="form-group">
                <label for="userId">아이디</label>
                <input type="text" id="userId" class="form-control" placeholder="아이디를 입력해주세요">
            </div>
            <div class="form-group">
                <label for="userName">이름</label>
                <input type="text" id="userName" class="form-control" placeholder="이름을 입력해주세요">
            </div>
            <div class="form-group">
                <label for="userEmail">이메일로 찾기</label>
                <input type="email" id="userEmail" class="form-control" placeholder="이메일을 입력해주세요">
            </div>
            <button type="submit" class="btn btn-primary btn-block">확인</button>
        </form>
    </div>
</div>

<jsp:include page="../footer.jsp" />

<!-- Bootstrap JS -->
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.16.0/umd/popper.min.js"></script>
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js"></script>
