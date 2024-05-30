<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
    String ctxPath = request.getContextPath();
    //    /GoGoDak
%>
	<jsp:include page="../header.jsp" />
	
 	<section class="inner_page">
		<div class="container" style="margin: 3% auto;">
			<h2 class="text-center mb-5"><i class="fa-solid fa-quote-left"></i>&nbsp;&nbsp;회원 탈퇴&nbsp;&nbsp;<i class="fa-solid fa-quote-right"></i></h2>
			<div>
				<h4 class="text-center font-weight-bold" style="color: #fbc02d;">회원탈퇴를 신청하기 전, 다음 내용을 꼭 확인해주세요.</h4>
				<br><br>
             	<div>▶&nbsp;회원탈퇴와 함께 고고닭에 등록된 모든 개인정보는 삭제, 폐기 처리되며 복구되지 않습니다.</div><br>
             	<div>▶&nbsp;회원탈퇴 시 동일 아이디(이메일)로 재가입하실 수 없습니다.</div><br>
             	<div>▶&nbsp;단, 상법 및 '전자상거래 등에서 소비자 보호에 관한 법률' 등 관련 법령의 규정에 의하여 다음과 같이 ‘거래 관련 관리의무 관계 확인’ 등을 이유로 일정 기간 보관됩니다.
             		<p>
             			&nbsp;&nbsp;&nbsp;&nbsp;- 계약 또는 청약 철회 등에 관한 기록 : 5년<br>
             			&nbsp;&nbsp;&nbsp;&nbsp;- 대금결제 및 재화 등의 공급에 관한 기록 : 5년<br>
             			&nbsp;&nbsp;&nbsp;&nbsp;- 소비자의 불만 또는 분쟁 처리에 관한 기록 : 3년<br>
             			&nbsp;&nbsp;&nbsp;&nbsp;- 설문조사, 이벤트 등 일시적 목적을 위하여 수집한 경우 : 당해 설문조사, 이벤트 등의 종료 시까지
             		</p>
             	</div>
             	<br><br>
             	<form name="deleteEndFrm" style="text-align: center;">
             		<input type="checkbox" name="withdrawalInput" id="withdrawalInput" style="width: 3%;" />&nbsp;<label for="withdrawalInput">위 내용을 모두 이해하였으며, 이에 동의합니다.</label>
             		<br><br>
             		<table id="tblMemberDel" style="width: 35%; margin: 0 auto;">
             			<tbody>
             				<tr>
						    	<td>비밀번호</td>
						        <td>
						        	<input type="hidden" name="id" value="${sessionScope.loginuser.id}" />
						            <input type="password" name="pwd" id="pwd" maxlength="10" class="requiredInfo mb-0" />
						        </td>
						    </tr>
             			</tbody>
             		</table>
             	</form>
             	<br><br>
             	<div align="right">
             		<button type="button" class="btn btn-warning" onclick="goWithdrawal()">회원탈퇴</button>
             	</div>
          	</div>
		</div>
	</section>
	
	<script type="text/javascript">
		function goWithdrawal(){
				
			let is_chk = false;
			
			const checkbox_checked_length = $("input:checkbox[name='withdrawalInput']:checked").length;
		    if(checkbox_checked_length == 0){
		        alert("회원탈퇴에 대해 동의하여 주시기 바랍니다.");
		        is_chk = false;
				return;
		    }
		    
			const pwd = $("input#pwd").val().trim();
			if(pwd == "") {
				alert("비밀번호를 입력하셔야 합니다.");
				is_chk = false;
				return;
			}
			
			if(checkbox_checked_length > 0 && pwd != ""){
				is_chk = true;
			}
		    
			if(is_chk){
				if(confirm("${sessionScope.loginuser.name}님, 정말로 탈퇴하시겠습니까?")){
					const frm = document.deleteEndFrm;
					frm.action = "memberWithdrawalEnd.dk";
					frm.method = "post";
					frm.submit();
				}
			}
		}
	</script>
	
	<jsp:include page="../footer.jsp" />