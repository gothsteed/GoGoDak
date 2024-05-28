<%@ page language="java" contentType="text/html; charset=UTF-8"
   pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %> 
   
   
<%
String ctxPath = request.getContextPath();
//    /GoGoDak
%>
<jsp:include page="../header.jsp" />
<style type="text/css">
.onlyLog{
   margin: 4% auto;
}   
.titleArea{
    color: #1a1a1a;
    font-size: 32px;
    font-weight: 700;
    text-align: center;
    line-height: 40px;
    text-transform: uppercase;
    
 }
   
.bordPic{
   border:solid 0px red;
   max-width: 80%;
   margin: 0 auto;
   display: flex;
   justify-content: center;
}
.btn-outline-dark{
   margin-top: 5%;
}
</style>

<script type="text/javascript">
$(document).ready(function(){
   
   document.getElementById('replyButton').addEventListener('click', function() {
        var textareaContainer = document.getElementById('textareaContainer');
        if (textareaContainer.style.display === 'none') {
            textareaContainer.style.display = 'block';
        } else {
            textareaContainer.style.display = 'none';
        }
    });
   
   
});
<%-- function goEdit() {
      
   const frm = document.questionEditinfoFrm;
    frm.action = "<%=ctxPath%>/admin/noticeEdit.dk";
    frm.method = "post";
    frm.submit();
} --%>
function goDelete() {
    if (confirm("정말로 이 게시물을 삭제하시겠습니까?")) {
        const frm = document.questionEditinfoFrm;
        frm.action = "<%=ctxPath%>/member/questionDelete.dk";
        frm.method = "post";
        frm.submit();
   }
}




function goadminAnswer(){ // 05-26 추가
   const frm = document.adminAnswerFrm;
    frm.action = "<%=ctxPath%>/admin/answer.dk";
    frm.method = "post";
    frm.submit();
    console.log("goadminAnswer 함수 호출됨");
   
}


</script>
   <div class="container" style="padding: 3% 0;" >
      <h2 class="titleArea ">
         <font face="Arial"> 1:1문의 </font>
      </h2>
      <br>
   
   <!-- 작성자가 아닌 경우 글에 대한 수정, 삭제 권한 부여 x -->
   <div align="right" class="onlyLog">
       <c:if test="${sessionScope.loginuser.id == 'admin' ||sessionScope.loginuser.id == qvo.id}">      
            <button type="button" class="btn btn-light" onclick="goDelete()">삭제</button> 
       </c:if> 
   </div>
   
   <!-- 작성자가 아닌 경우 글에 대한 수정, 삭제 권한 부여 x -->
   <input type="hidden" name="question_seq" value="${requestScope.qvo.question_seq}">
   <table class="table">
      <thead>
         <tr>
            <th class="questionView">제목</th>
               <th>${requestScope.qvo.title}</th>
           </tr>
           <tr>
            <th class="questionView">작성자</th>
               <th>${requestScope.qvo.id}</th>
           </tr>
       </thead>
    </table>
       
       <div>
       <br><br><br>
          <span style="display: flex; justify-content: center;">${requestScope.qvo.content}</span>
         <br><br>
       </div>




       <%-- 답변이 있을때 요기에 나타내기 시작  05-26--%>

       <c:if test="${empty requestScope.avo.answer_seq}">
          <span>아직 작성된 답변이 없습니다</span>
       </c:if>

       <c:if test="${not empty requestScope.avo.answer_seq}">
          
          <input type="hidden" name="fk_question_seq" value="${requestScope.avo.fk_question_seq}">
            <table class="table">
               <thead>
                  <tr>
                     <th class="answerView">답변 : </th>
                        <th>${requestScope.avo.title}</th>
                    </tr>
                </thead>
             </table>
                
             <div>
                <br><br><br>
                   <span style="display: flex; justify-content: center;">${requestScope.avo.content}</span>
                  <br><br>
             </div>
       </c:if>
       <%-- 답변이 있을때 요기에 나타내기 끝 --%>

          <hr>
       <div class="form-group"> 
           <c:if test="${sessionScope.loginuser.id == 'admin'}">
              <div align="right">
                  <button type="button" class="btn btn-light" id="replyButton" >관리자 답변달기</button>
                  <br><br>
              </div>

                 <div id="textareaContainer" style="display: none;">
                    <form name="adminAnswerFrm" method="post">
                        <div class="board-group">
                              <label for=Answertitle>제목</label>
                              <input type="text" name="Answertitle" id="Answertitle" placeholder="제목 입력" />
                          </div>
               
                        <div class="board-group">
                           <label for="Answercontent">내용</label>
                           <textarea name="AnswerContent" id="AnswerContent" placeholder="답변 입력"  ></textarea>
                        </div>
                        <input type="hidden" name="question_seq" value="${requestScope.qvo.question_seq}">
                         <br>
                         <button type="button" class="btn btn-light" onclick="goadminAnswer()"  style="float: right;">저장</button>
                         <%--이거 함수만들어야함 --%>
                    </form>   
                 </div>
                 <br>

           </c:if>       
       </div>



       <button type="button" class="btn btn-outline-dark" onclick="location.href='<%= ctxPath%>/member/question.dk'">목록</button>

    
   
   
   
   </div>
   
   <form name="questionEditinfoFrm" method="post" style="display: none;">
      <input type="text" name="question_seq" value="${requestScope.qvo.question_seq}">
      <input type="text" name="title" value="${requestScope.qvo.title}">
      <input type="text" name="content" value="${requestScope.qvo.content}">
      <input type="text" name="id" value="${requestScope.qvo.id}">
   </form>
<jsp:include page="../footer.jsp" />