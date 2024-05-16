<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%
    String ctxPath = request.getContextPath();
%>

<jsp:include page="../header.jsp" />  

<style type="text/css">
.sync-login-area {
    display: flex;
    justify-content: center;
    align-items: center;
    height: 100vh; /* Full viewport height */
}

.sync-login-form {
    width: 50%;
    padding: 20px;
    background-color: white; /* Optional: to make sure the form background is white */
    box-shadow: 0 0 10px rgba(0, 0, 0, 0.1); /* Optional: add some shadow for better visibility */
}

.form-group {
    margin-bottom: 15px;
     font-weight: 600;
    color: #555;
}

body > section.why_section.layout_padding > div > div > div > form > div.form-privacy-box > div > div > a:nth-child(1) {
    color: gray;
}

body > section.why_section.layout_padding > div > div > div > form > div.form-privacy-box > div > div > a:nth-child(3){
    color: gray;
}

.btn-primary {
  width: 100%;
  background-color: #333;
  border: none;
  padding: 10px;
  font-size: 16px;
  font-weight: 600;
  border-radius: 5px;
  transition: background-color 0.3s ease;
}

.btn-primary:hover {
  background-color: #555;
}

.sync-login-tab {
  text-align: center;
  margin-bottom: 20px;
}

.form-privacy-box {
  margin-bottom: 15px;
  text-align: center;
}

.btn-block {
  width: 100%;
}

.links {
  text-align: center;
  margin-top: 10px;
}

.membership-link {
  color: gray;
  text-decoration: none;
  font-size: 14px;
  display: block;
  margin-top: 10px;
}

.membership-link:hover {
  text-decoration: underline;
}
</style>



<section class="why_section layout_padding">
    <div class="sync-login-area">
        <div class="sync-login-form">
            <div>
                <div class="sync-login-tab">
                </div>
                <form action="<%=ctxPath %>/login" method="post"> 
                    <div class="form-group">
                        <label for="username">아이디</label>
                        <input type="text" id="username" name="username" class="form-control" required placeholder="아이디를 입력해주세요">
                    </div>
                    <div class="form-group">
                        <label for="password">비밀번호</label>
                        <input type="password" id="password" name="password" class="form-control" required placeholder="비밀번호를 입력해주세요">
                    </div>
                    <div class="form-privacy-box">
                        <div class="form-search-privacy">
                            <div>
                                <a href="<%=ctxPath %>/login/idFind.dk">아이디 찾기</a>
                                <span class="division"> | </span>
                                <a href="<%=ctxPath %>/login/pwdFind.dk">비밀번호 찾기</a>
                            </div>
                        </div>
                    </div>
                    <button type="submit" class="btn btn-primary btn-block">로그인</button>
                </form>
                <div class="links">
                    <a href="<%=ctxPath %>/member/memberRegister.dk" id="join" class="membership-link">회원가입</a>
                </div>
            </div>
        </div>
    </div>
</section>

<jsp:include page="../footer.jsp" />