<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
    String ctxPath = request.getContextPath();
	//    /GoGoDak
%>
<jsp:include page="../header.jsp" />

<style type="text/css">

div#divRegisterFrm {
	text-align: center;
}

#divRegisterFrm > div > form {
    border: 5px solid darkblue;
   	font-size: 14px;
    margin: 12%;
    padding: 10%;
}

table#tblMemberRegister {
 /*	border: solid 1px red; */ 
	width: 93%;
	margin: 1% auto;
}

table#tblMemberRegister th {
    border: solid 1px gray; 
}

table#tblMemberRegister th {
	height: 60px;
	background-color: silver;
	font-size: 14pt;
}

table#tblMemberRegister td {
	line-height: 200%;
	padding: 1.2% 0;
}

span.star {
	color: red;
	font-weight: bold;
	font-size: 13pt;
}

table#tblMemberRegister > tbody > tr > td:first-child {
	width: 20%;
	font-weight: bold;
	text-align: left;
}

table#tblMemberRegister > tbody > tr > td:nth-child(2) {
	width: 80%;
	text-align: left;
}

img#idcheck, img#zipcodeSearch {
	cursor: pointer;
}

span#emailcheck {
	border: solid 1px gray;
	border-radius: 5px;
	font-size: 8pt;
	display: inline-block;
	width: 80px;
	height: 30px;
	text-align: center;
	margin-left: 10px;
	cursor: pointer;
}

body > div > div > div > div > div.card-header{
	text-align: center;
}

input.form-control{
	text-transform: none !important;
}

small.error{
	font-weight: bold;
}
</style>
   
<script src="https://t1.daumcdn.net/mapjsapi/bundle/postcode/prod/postcode.v2.js"></script>
<script type="text/javascript" src="<%= ctxPath%>/js/member/memberRegister.js"></script>

<div class="container mt-5">
    <div class="row justify-content-center">
        <div class="col-md-8">
            <div class="card">
                <div class="card-header">
                    <h3>회원가입 <small class="text-muted">(<span class="text-danger">*</span> 표시는 필수입력사항)</small></h3>
                </div>
                <div class="card-body" id="tblMemberRegister">
                    <form name="registerFrm">
                        <div class="form-group">
                            <label for="name">성명 <span class="text-danger">*</span></label>
                            <input type="text" name="name" id="name" class="form-control requiredInfo" maxlength="30">
                            <small class="form-text text-danger error">성명은 필수입력 사항입니다.</small>
                        </div>

                        <div class="form-group">
                            <label for="id">아이디 <span class="text-danger">*</span></label>
                            <input type="text" name="id" id="id" class="form-control requiredInfo" maxlength="40">
                            <button type="button" class="btn btn-outline-secondary btn-sm" id="idcheck">아이디 중복확인</button>&nbsp;&nbsp;
                            <small class="form-text text-danger error">아이디는 영문자, 숫자가 혼합된 5 ~ 40 글자로 입력하세요.</small>
                            <span id="idcheckResult"></span>
                        </div>

                        <div class="form-group">
                            <label for="pwd">비밀번호 <span class="text-danger">*</span></label>
                            <input type="password" name="pwd" id="pwd" class="form-control requiredInfo" maxlength="15">
                            <small class="form-text text-danger error">암호는 영문자, 숫자, 특수기호가 혼합된 8 ~ 15 글자로 입력하세요.</small>
                        </div>

                        <div class="form-group">
                            <label for="pwdcheck">비밀번호 확인 <span class="text-danger">*</span></label>
                            <input type="password" id="pwdcheck" class="form-control requiredInfo" maxlength="15">
                            <small class="form-text text-danger error">암호가 일치하지 않습니다.</small>
                        </div>

                        <div class="form-group">
                            <label for="email">이메일 <span class="text-danger">*</span></label>
                            <input type="text" name="email" id="email" class="form-control requiredInfo" maxlength="60">
                            <button type="button" class="btn btn-outline-secondary btn-sm" id="emailcheck">이메일 중복확인</button>&nbsp;&nbsp;
                            <small class="form-text text-danger error">이메일 형식에 맞지 않습니다.</small>
                            <span id="emailCheckResult"></span>
                        </div>

                        <div class="form-group">
                            <label for="hp1">연락처</label>
                            <div class="form-row">
                                <div class="col">
                                    <input type="text" name="hp1" id="hp1" class="form-control" size="6" maxlength="3" value="010" readonly>
                                </div>
                                <div class="col">
                                    <input type="text" name="hp2" id="hp2" class="form-control requiredInfo" size="6" maxlength="4">
                                </div>
                                <div class="col">
                                    <input type="text" name="hp3" id="hp3" class="form-control requiredInfo" size="6" maxlength="4">
                                </div>
                            </div>
                            <small class="form-text text-danger error">휴대폰 형식이 아닙니다.</small>
                        </div>

                        <div class="form-group">
                            <label for="postcode">우편번호</label>
                            <div class="input-group">
                                <input type="text" name="postcode" id="postcode" class="form-control" size="6" maxlength="5">&nbsp;
                                <img src="<%=ctxPath %>/images/register/b_zipcode.gif" id="zipcodeSearch" alt="Zipcode Search" width="120px" />
                            </div>
                            <small class="form-text text-danger error">우편번호 형식에 맞지 않습니다.</small>
                        </div>

                        <div class="form-group">
                            <label for="address">주소</label>
                            <input type="text" name="address" id="address" class="form-control" maxlength="200" placeholder="주소">
                            <input type="text" name="detailaddress" id="detailAddress" class="form-control mt-2" maxlength="200" placeholder="상세주소">
                            <input type="text" name="extraaddress" id="extraAddress" class="form-control mt-2" maxlength="200" placeholder="참고항목">
                            <small class="form-text text-danger error">주소를 입력하세요.</small>
                        </div>

                        <div class="form-group">
                            <label for="birthdate">주민등록번호 <span class="text-danger">*</span></label>
                     		<div class="form-row">
                                <div class="col">
                                    <input type="text" name="birthdate" id="birthdate" class="form-control requiredInfo" size="6" maxlength="6">
                                </div>
                                &nbsp;<i class="fa-solid fa-minus pt-2"></i>&nbsp;&nbsp;
                                <div>
                                    <input type="text" name="gender" id="gender" class="form-control requiredInfo" size="1" maxlength="1">
                                </div>
                                <div class="col">
                                    <input type="text" name="asterisk" id="asterisk" class="form-control" size="6" maxlength="6" value="******" readonly>
                                </div>
                            </div>
                            <small class="form-text text-danger error">주민번호 형식에 맞지 않습니다.</small>
                        </div>

                        <div class="form-group form-check">
                            <input type="checkbox" class="form-check-input" id="agree" style="width: 3%;">
                            <label class="form-check-label pl-2" for="agree">이용약관에 동의합니다.</label>
                        </div>

                        <div class="form-group">
                            <iframe src="<%= ctxPath %>/agree/agree.html" width="100%" height="150px" class="border border-primary"></iframe>
                        </div>

                        <div class="form-group text-center">
                            <button type="button" class="btn btn-success btn-lg mr-3" onclick="goRegister()">가입하기</button>
                            <button type="reset" class="btn btn-danger btn-lg" onclick="goReset()">취소하기</button>
                        </div>
                    </form>
                </div>
            </div>
        </div>
    </div>
</div>

<script src="https://cdn.jsdelivr.net/npm/@popperjs/core@2.5.4/dist/umd/popper.min.js"></script>
<script src="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js"></script>

<jsp:include page="../footer.jsp" />