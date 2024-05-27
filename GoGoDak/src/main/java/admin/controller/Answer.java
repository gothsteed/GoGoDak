package admin.controller;

import admin.model.AdminDAO;
import admin.model.AdminDAO_imple;
import common.controller.AbstractController;
import domain.AnswerVO;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public class Answer extends AbstractController {

   private AdminDAO adao = null;

   public Answer() {
      adao = new AdminDAO_imple();
   }

   @Override
   public void execute(HttpServletRequest request, HttpServletResponse response) throws Exception {

      // 1:1 문의사항 답변하는곳 05-26추가

      System.out.println("엔써로옵니까?");

      String method = request.getMethod();
      System.out.println("HTTP Method: " + method);

      if ("POST".equalsIgnoreCase(method)) {
         String Answertitle = request.getParameter("Answertitle"); // 제목
         String AnswerContent = request.getParameter("AnswerContent"); // 답변내용

         int question_seq = Integer.parseInt(request.getParameter("question_seq")); // 질문고유번호

         AnswerVO ansewer = new AnswerVO();
         ansewer.setContent(AnswerContent);
         ansewer.setTitle(Answertitle);
         ansewer.setFk_question_seq(question_seq);

         int result = adao.answerWrite(ansewer);

         if (result == 1) {
            String message = "답변이 등록 되었습니다.";
            String loc = "javascript:history.back()";

            request.setAttribute("message", message);
            request.setAttribute("loc", loc);

            super.setViewPage("/WEB-INF/view/msg.jsp");
         } else {
            String message = "답변등록을 실패 했습니다.";
            String loc = request.getContextPath() + "javascript:history.back()";

            request.setAttribute("message", message);
            request.setAttribute("loc", loc);

            super.setViewPage("/WEB-INF/view/msg.jsp");
         }

      } else {
         super.setRedirect(false);
         super.setViewPage("/WEB-INF/view/member/questionView.dk");

      }

   }

}