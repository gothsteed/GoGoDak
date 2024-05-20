<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<% String ctxPath = request.getContextPath();


%>
    
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>

<script type="text/javascript">

window.onload = function() {
	
		alert("회원가입 완료")
		const frm = document.login;
		frm.action = "<%=ctxPath %>/login/login.dk"
		<%-- console.log("<%=request.getParameter("userid")%>") --%>
 		frm.method="post"
		frm.submit(); 
		
}

</script>
</head>
<body>
<form name="login">
	<input type="hidden" name="id" value="${requestScope.userid}">
	<input type="hidden" name="password" value="${requestScope.pwd}">
</form>
	
</body>
</html>