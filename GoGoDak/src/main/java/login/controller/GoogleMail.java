package login.controller;

import java.util.Properties;

import javax.mail.Address;
import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

public class GoogleMail {

	public void send_certification_code(String recipient, String certification_code, String id, String name) throws Exception {

		// 1. 정보를 담기 위한 객체
		Properties prop = new Properties();

		// 2. SMTP(Simple Mail Transfer Protocoal) 서버의 계정 설정
		// Google Gmail 과 연결할 경우 Gmail 의 email 주소를 지정
		prop.put("mail.smtp.user", "jyleeturbo1234@gmail.com");

		// 3. SMTP 서버 정보 설정
		// Google Gmail 인 경우 smtp.gmail.com
		prop.put("mail.smtp.host", "smtp.gmail.com");

		prop.put("mail.smtp.port", "465");
		prop.put("mail.smtp.starttls.enable", "true");
		prop.put("mail.smtp.auth", "true");
		prop.put("mail.smtp.debug", "true");
		prop.put("mail.smtp.socketFactory.port", "465");
		prop.put("mail.smtp.socketFactory.class", "javax.net.ssl.SSLSocketFactory");
		prop.put("mail.smtp.socketFactory.fallback", "false");

		prop.put("mail.smtp.ssl.enable", "true");
		prop.put("mail.smtp.ssl.trust", "smtp.gmail.com");
		prop.put("mail.smtp.ssl.protocols", "TLSv1.2"); // MAC 에서도 이메일 보내기 가능하도록 한것임. 또한 만약에 SMTP 서버를 google 대신 naver 를
														// 사용하려면 이것을 해주어야 함.

		/*
		 * 혹시나 465 포트에 연결할 수 없다는 에러메시지가 나오면 아래의 3개를 넣어주면 해결된다.
		 * prop.put("mail.smtp.starttls.enable", "true");
		 * prop.put("mail.smtp.starttls.required", "true");
		 * prop.put("mail.smtp.ssl.protocols", "TLSv1.2");
		 */

		Authenticator smtpAuth = new MySMTPAuthenticator();
		Session ses = Session.getInstance(prop, smtpAuth);

		// 메일을 전송할 때 상세한 상황을 콘솔에 출력한다.
		ses.setDebug(true);

		// 메일의 내용을 담기 위한 객체생성
		MimeMessage msg = new MimeMessage(ses);

		// 제목 설정
		String subject = "[ 고고닭 ] " + id + " 고객님, 요청하신 본인확인 인증번호 입니다.";
		msg.setSubject(subject);

		// 보내는 사람의 메일주소
		String sender = "jyleeturbo1234@gmail.com";
		Address fromAddr = new InternetAddress(sender);
		msg.setFrom(fromAddr);

		// 받는 사람의 메일주소
		Address toAddr = new InternetAddress(recipient);
		msg.addRecipient(Message.RecipientType.TO, toAddr);

		// 메시지 본문의 내용과 형식, 캐릭터 셋 설정
		msg.setContent("<div style=\"width: 50%; margin: 3% auto; border: 1px solid #ddd;\">"
					 + "		<div style=\"padding: 10px 20px 0 20px;\">"
					 + "			<h1>GOGODAK MALL</h1>"
					 + "			<div style=\"background: #fbc02d; padding: 10px 20px;\">"
					 + "				<h4>기분 좋은 쇼핑이 될 수 있도록 최선을 다하겠습니다.</h4>\n"
					 + "				<h3><span style=\"font-size: 24pt; font-weight: bold;\">요청하신 인증번호</span>를 알려드립니다.</h3>"
					 + "			</div>"
					 + "			<br>"
					 + "			<p>안녕하세요 고객님, <span style=\"font-weight: bold;\">고고닭</span> 입니다.</p>"
					 + "			<p>저희 쇼핑몰을 이용해주셔서 진심으로 감사드립니다.</p>"
					 + "			<p><span style=\"font-weight: bold;\"> " + name + "(" + id + ")</span> 고객님께서 요청하신 인증번호는 다음과 같습니다.</p>"
					 + "			<br>"
					 + "			<p>▣&nbsp;&nbsp;가입정보</p>"
					 + "			<table>"
					 + "				<tbody>"
					 + "					<tr>"
					 + "						<th style=\"border: 1px solid #ddd; width: 210px; height: 10px; padding: 10px; background: #fbc02d;\">아이디</th>"
					 + "						<td style=\"border: 1px solid #ddd; width: 210px; height: 10px; padding: 10px; text-align: center;\">" + id + "</td>"
					 + "						<th style=\"border: 1px solid #ddd; width: 210px; height: 10px; padding: 10px; background: #fbc02d;\">인증번호</th>"
					 + "						<td style=\"border: 1px solid #ddd; width: 210px; height: 10px; padding: 10px; text-align: center;\">" + certification_code + "</td>"
					 + "					</tr>"
					 + "				</tbody>"
					 + "			</table>"
					 + "			<p>·&nbsp;인증번호 입력창에 인증번호를 정확히 입력해주세요.</p>"
					 + "			<p>·&nbsp;인증번호 입력 후 새로운 비밀번호를 설정해주세요.</p>"
					 + "			<br>"
					 + "			<p>다시 한번 저희 쇼핑몰을 이용해주신 <span style=\"font-weight: bold;\"> "+ name + "(" + id + ")</span> 고객님께 진심으로 감사드립니다.</p>"
					 + "			<p>앞으로도 많은 관심 부탁드립니다&nbsp;:)</p>"
					 + "		</div>"
				     + "		<br>"
					 + "		<div style=\"background: #ddd; color: #fff; padding: 10px 20px;\">"
					 + "			<p>TEL: 1566-3197&nbsp;｜&nbsp;서울특별시 마포구 월드컵북로 21 풍성빌딩 2,3,4층</p>"
					 + "			<p>&copy;&nbsp;2024 All Rights Reserved By GOGODAK</p>"
					 + "		</div>"
					 + "	</div>"
					 , "text/html;charset=UTF-8");

		// 메일 발송하기
		Transport.send(msg);

	} // end of public void send_certification_code(String recipient, String certification_code) throws Exception --------

	public void sendMailOrderFinish(String email, String name, String string) throws MessagingException {
		// 1. 정보를 담기 위한 객체
		Properties prop = new Properties();

		// 2. SMTP(Simple Mail Transfer Protocoal) 서버의 계정 설정
		// Google Gmail 과 연결할 경우 Gmail 의 email 주소를 지정
		prop.put("mail.smtp.user", "jyleeturbo1234@gmail.com");

		// 3. SMTP 서버 정보 설정
		// Google Gmail 인 경우 smtp.gmail.com
		prop.put("mail.smtp.host", "smtp.gmail.com");

		prop.put("mail.smtp.port", "465");
		prop.put("mail.smtp.starttls.enable", "true");
		prop.put("mail.smtp.auth", "true");
		prop.put("mail.smtp.debug", "true");
		prop.put("mail.smtp.socketFactory.port", "465");
		prop.put("mail.smtp.socketFactory.class", "javax.net.ssl.SSLSocketFactory");
		prop.put("mail.smtp.socketFactory.fallback", "false");

		prop.put("mail.smtp.ssl.enable", "true");
		prop.put("mail.smtp.ssl.trust", "smtp.gmail.com");
		prop.put("mail.smtp.ssl.protocols", "TLSv1.2"); // MAC 에서도 이메일 보내기 가능하도록 한것임. 또한 만약에 SMTP 서버를 google 대신 naver 를
														// 사용하려면 이것을 해주어야 함.

		/*
		 * 혹시나 465 포트에 연결할 수 없다는 에러메시지가 나오면 아래의 3개를 넣어주면 해결된다.
		 * prop.put("mail.smtp.starttls.enable", "true");
		 * prop.put("mail.smtp.starttls.required", "true");
		 * prop.put("mail.smtp.ssl.protocols", "TLSv1.2");
		 */

		Authenticator smtpAuth = new MySMTPAuthenticator();
		Session ses = Session.getInstance(prop, smtpAuth);

		// 메일을 전송할 때 상세한 상황을 콘솔에 출력한다.
		ses.setDebug(true);

		// 메일의 내용을 담기 위한 객체생성
		MimeMessage msg = new MimeMessage(ses);

		// 제목 설정
		String subject = "[ 고고닭 ] " + name + "주문 내역";
		msg.setSubject(subject);

		// 보내는 사람의 메일주소
		String sender = "jyleeturbo1234@gmail.com";
		Address fromAddr = new InternetAddress(sender);
		msg.setFrom(fromAddr);

		// 받는 사람의 메일주소
		Address toAddr = new InternetAddress(email);
		msg.addRecipient(Message.RecipientType.TO, toAddr);

		// 메시지 본문의 내용과 형식, 캐릭터 셋 설정
		msg.setContent("<div style='font-size:14pt; color:red;'>"+string+"</div>", "text/html;charset=UTF-8");

		// 메일 발송하기
		Transport.send(msg);
		
	}

}
