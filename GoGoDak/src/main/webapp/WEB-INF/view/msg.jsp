<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<script type="text/javascript">
	
	alert("${requestScope.message}"); // 메시지 출력해주기
	location.href = "${requestScope.loc}"; // 페이지 이동하기	
	
	opener.location.reload(true);

	self.close(); // 팝업창 닫기

</script>