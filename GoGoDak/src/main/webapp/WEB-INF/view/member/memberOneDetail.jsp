<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<%@ taglib prefix="c"   uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn"  uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>  

<jsp:include page="../header.jsp" /> 

<style type="text/css">

  table.table-bordered > tbody > tr > td:nth-child(1) {
  	 width: 25%;
  	 font-weight: bold;
  	 text-align: right;
  }

</style>

<script type="text/javascript">
  
  $(document).ready(function(){ 
	  
	  $("div#smsResult").hide();
	  
	  $("button#btnSend").click( () => {
		  
		// console.log( $("input#reservedate").val() + " " + $("input#reservetime").val() ); 
		// 2024-05-14 10:46
		
		let reservedate = $("input#reservedate").val();
		reservedate = reservedate.split("-").join("");
	//	["2024","05","14"] ==> "20240514"
	
	    let reservetime = $("input#reservetime").val();
	    reservetime = reservetime.split(":").join("");
		
	    const datetime = reservedate + reservetime; 
	    
	 // console.log(datetime);
		// 202405141046
		
		let dataObj;
		  
	    if( reservedate == "" || reservetime == "" ) {
		  // 문자를 바로 보내기인 경우 
		  dataObj = {"mobile":"${requestScope.mvo.tel}",
				     "smsContent":$("textarea#smsContent").val()};
	    }
	    else {
		  // 예약문자 보내기인 경우
		  dataObj = {"mobile":"${requestScope.mvo.tel}",
				     "smsContent":$("textarea#smsContent").val(),
				     "datetime":datetime};
	    }
		
	    $.ajax({
			  url:"${pageContext.request.contextPath}/member/smsSend.dk",
			  type:"get",
			  data:dataObj,
			  dataType:"json",
			  success:function(json){ 
				  // json 은 {"group_id":"R2GWPBT7UoW308sI","success_count":1,"error_count":0} 처럼 된다. 
				  
				  if(json.success_count == 1) {
					  $("div#smsResult").html("<span style='color:red; font-weight:bold;'>문자전송이 성공되었습니다.^^</span>");
				  }
				  else if(json.error_count != 0) {
					  $("div#smsResult").html("<span style='color:red; font-weight:bold;'>문자전송이 실패되었습니다.ㅜㅜ</span>");
				  }
				  
				  $("div#smsResult").show();
				  $("textarea#smsContent").val("");
			  },
			  error: function(request, status, error){
				  alert("code: "+request.status+"\n"+"message: "+request.responseText+"\n"+"error: "+error);
			  }
		  });
		
		  
	  });
	  
  });// end of $(document).ready(function(){})-----------
  
</script>

<div class="container">

	<c:if test="${empty requestScope.mvo}">
	      존재하지 않는 회원입니다.<br>
	</c:if>
	
	<c:if test="${not empty requestScope.mvo}">
	   
	   <c:set var="tel" value="${requestScope.mvo.tel}" />   
	      
	   <p class="h3 text-center mt-5 mb-4" style="font-famliy: bold;">	🐔🐔 ${requestScope.mvo.name} 님의 회원 상세정보 🐔🐔</p>    
	   
	   <table class="table table-bordered" style="width: 60%; margin: 0 auto;">
		   <tr>
		      <td>아이디&nbsp;:&nbsp;</td>
		      <td>${requestScope.mvo.id}</td>
		   </tr>
		   <tr>
		      <td>회원명&nbsp;:&nbsp;</td>
		      <td>${requestScope.mvo.name}</td>
		   </tr>
		   <tr>
		      <td>이메일&nbsp;:&nbsp;</td>
		      <td>${requestScope.mvo.email}</td>
		   </tr>
		   <tr>
		      <td>휴대폰&nbsp;:&nbsp;</td>
		      <td>${fn:substring(tel, 0, 3)}-${fn:substring(tel, 3, 7)}-${fn:substring(tel, 7, 11)}</td> 
		   </tr>
		   <tr>
		      <td>우편번호&nbsp;:&nbsp;</td>
		      <td>${requestScope.mvo.postcode}</td>
		   </tr>
		   <tr>
		      <td>주소&nbsp;:&nbsp;</td>
		      <td>${requestScope.mvo.address}&nbsp;
		          ${requestScope.mvo.address_detail}&nbsp;
		          ${requestScope.mvo.address_extra}
		      </td>
		   </tr>
		   <tr>
		      <td>성별&nbsp;:&nbsp;</td>
		      <td>
		         <c:choose>
		            <c:when test="${fn:substring(requestScope.mvo.jubun, 7, 1) == '1' || fn:substring(requestScope.mvo.jubun, 7, 1) == '3'  }">남</c:when> 
		            <c:otherwise>여</c:otherwise>
		         </c:choose>
		      </td>
		   </tr>
		   <tr>
		      <td>생년월일&nbsp;:&nbsp;</td>
		      <td>${fn:substring(requestScope.mvo.jubun, 0, 6)}</td>
		   </tr>
		   <tr>
		      <td>만나이&nbsp;:&nbsp;</td>
		      <td>${requestScope.mvo.age}&nbsp;세</td>
		   </tr>
		  <tr>
		      <td>포인트&nbsp;:&nbsp;</td>
		      <td>
		         <fmt:formatNumber value="${requestScope.mvo.point}" pattern="###,###" />&nbsp;POINT 
		      </td>
		   </tr>
		   <tr>
		      <td>가입일자&nbsp;:&nbsp;</td>
		      <td>${requestScope.mvo.registerDate}</td>
		   </tr>
	    </table>
	    
	    <%-- ==== 휴대폰 SMS(문자) 보내기 ==== --%>
		<div class="border my-5 text-center" style="width: 60%; margin: 0 auto;">
		  	<p class="h5 bg-info text-white">
		  	  &gt;&gt;&nbsp;&nbsp;휴대폰 SMS(문자) 보내기 내용 입력란&nbsp;&nbsp;&lt;&lt;
		  	</p>
		  	<div class="mt-4 mb-3">
		  		<span class="bg-danger text-white" style="font-size: 14pt;">문자발송 예약일자</span>
		  		<input type="date" id="reservedate" class="mx-2" />
		  		<input type="time" id="reservetime" />
		  	</div>
		  	<div style="display: flex;">
		  	   <div style="border: solid 0px red; width: 81%; margin: auto;">
		  	      <textarea rows="4" id="smsContent" style="width: 100%;"></textarea>
		  	   </div>
		  	   <div style="border: solid 0px blue; width: 19%; margin: auto;">
		  	      <button id="btnSend" class="btn btn-secondary">문자전송</button>
		  	   </div>
		  	</div>
		  	<div id="smsResult" class="p-3"></div>
		</div>	   
	      
	</c:if>
	
	<div class="text-center mb-5">
		<button type="button" class="btn btn-outline-warning" onclick="javascript:history.back()">회원목록</button>
 
    </div>   

</div>


<jsp:include page="../footer.jsp" />
    