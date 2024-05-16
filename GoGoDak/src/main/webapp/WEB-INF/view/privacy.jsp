<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
    String ctxPath = request.getContextPath();
    //    /GoGoDak
%>
	<jsp:include page="header.jsp" />
	
	<%-- start inner page section --%>
  	<section class="inner_page mt-5 mb-5">
  		<div class="container">
  			<h2 class="text-center" style="font-family: 'Noto Sans KR', sans-serif;"><i class="fa-solid fa-quote-left"></i>&nbsp;&nbsp;개인정보처리방침&nbsp;&nbsp;<i class="fa-solid fa-quote-right"></i></h2>
  			<div class="bg-light mt-5 pt-3 pr-3 pb-3 pl-3">
	  			<div class="agree border h-75 pt-3 pr-3 pb-3 pl-3" style="background-color: #fff;">
	  				<div class="fr-view fr-view-privacy-all">
	  					<p>(주)메디쿼터스는 (이하 "회사"는) 고객님의 개인정보를 중요시하며, "정보통신망 이용촉진 및 정보보호"에 관한 법률을 준수하고 있습니다.</p>
	  					<p>회사는 개인정보취급방침을 통하여 고객님께서 제공하시는 개인정보가 어떠한 용도와 방식으로 이용되고 있으며, 개인정보보호를 위해 어떠한 조치가 취해지고 있는지 알려드립니다.</p>
	  					<p><br></p>
	  					<p><br></p>
	  					<p>■ 수집하는 개인정보 항목 및 수집방법</p>
	  					<p><br></p>
	  					<p>가. 수집하는 개인정보의 항목</p>
	  					<p>o 회사는 회원가입, 상담, 서비스 신청 등을 위해 아래와 같은 개인정보를 수집하고 있습니다.</p>
	  					<p>- 회원가입시 : 이름, 생년월일, 성별, 로그인ID, 비밀번호, 자택 전화번호, 휴대전화번호, 이메일, 14세미만 가입자의 경우 법정대리인의 정보, 쇼핑몰 앱 이용 시 기기정보(안드로이드, IOS)</p>
	  					<p>- 서비스 신청시 : 주소, 결제 정보</p>
	  					<p><br></p>
	  					<p>o 서비스 이용 과정이나 사업 처리 과정에서 서비스이용기록, 접속로그, 쿠키, 접속 IP, 결제 기록, 불량이용 기록이 생성되어 수집될 수 있습니다.</p>
	  					<p><br></p>
	  					<p>나. 수집방법</p>
	  					<p>- 홈페이지, 서면양식, 게시판, 이메일, 이벤트 응모, 배송요청, 전화, 팩스, 생성 정보 수집 툴을 통한 수집</p>
	  					<p><br></p>
	  					<p><br></p>
	  					<p>■ 개인정보의 수집 및 이용목적</p>
	  					<p><br></p>
	  					<p>회사는 수집한 개인정보를 다음의 목적을 위해 활용합니다.</p>
	  					<p><br></p>
	  					<p>o 서비스 제공에 관한 계약 이행 및 서비스 제공에 따른 요금정산</p>
	  					<p>콘텐츠 제공, 구매 및 요금 결제, 물품배송 또는 청구지 등 발송, 금융거래 본인 인증 및 금융 서비스</p>
	  					<p><br></p>
	  					<p>o 회원 관리</p>
	  					<p>회원제 서비스 이용에 따른 본인확인, 개인 식별, 불량회원의 부정 이용 방지와 비인가 사용 방지, 가입 의사 확인, 연령확인, 만14세 미만 아동 개인정보 수집 시 법정 대리인 동의여부 확인, 불만처리 등 민원처리, 고지사항 전달</p>
	  					<p><br></p>
	  					<p>o 마케팅 및 광고에 활용</p>
	  					<p>이벤트 등 광고성 정보 전달, 접속 빈도 파악 또는 회원의 서비스 이용에 대한 통계</p>
	  					<p><br></p>
	  					<p><br></p>
	  					<p>■ 개인정보의 보유 및 이용기간</p>
	  					<p><br></p>
	  					<p>원칙적으로, 개인정보 수집 및 이용목적이 달성된 후에는 해당 정보를 지체 없이 파기합니다. 단, 다음의 정보에 대해서는 아래의 이유로 명시한 기간 동안 보존합니다.</p>
	  					<p><br></p>
	  					<p>가. 회사 내부방침에 의한 정보보유 사유</p>
	  					<p>회원이 탈퇴한 경우에도 불량회원의 부정한 이용의 재발을 방지, 분쟁해결 및 수사기관의 요청에 따른 협조를 위하여 이용계약 해지일로부터 3년간 회원의 정보를 보유할 수 있습니다.</p>
	  					<p><br></p>
	  					<p>나. 관련 법령에 의한 정보 보유 사유 </p>
	  					<p>전자상거래등에서의소비자보호에관한법률 등 관계법령의 규정에 의하여 보존할 필요가 있는 경우 회사는 아래와 같이 관계법령에서 정한 일정한 기간 동안 회원정보를 보관합니다.</p>
	  					<p><br></p>
	  					<p><br></p>
	  					<p>o 계약 또는 청약철회 등에 관한 기록</p>
	  					<p>-보존이유 : 전자상거래등에서의소비자보호에관한법률</p>
	  					<p>-보존기간 : 5년</p>
	  					<p><br></p>
	  					<p>o 대금 결제 및 재화 등의 공급에 관한 기록</p>
	  					<p>-보존이유: 전자상거래등에서의소비자보호에관한법률</p>
	  					<p>-보존기간 : 5년</p>
	  					<p><br></p>
	  					<p>o 소비자 불만 또는 분쟁처리에 관한 기록</p>
	  					<p>-보존이유 : 전자상거래등에서의소비자보호에관한법률</p>
	  					<p>-보존기간 : 3년</p>
	  					<p><br></p>
	  					<p>o 로그 기록</p>
	  					<p>-보존이유: 통신비밀보호법</p>
	  					<p>-보존기간 : 3개월</p>
	  					<p><br></p>
	  					<p><br></p>
	  					<p>■ 개인정보의 파기절차 및 방법</p>
	  					<p><br></p>
	  					<p>회사는 원칙적으로 개인정보 수집 및 이용목적이 달성된 후에는 해당 정보를 지체없이 파기합니다. 파기절차 및 방법은 다음과 같습니다.</p>
	  					<p><br></p>
	  					<p>o 파기절차</p>
	  					<p>회원님이 회원가입 등을 위해 입력하신 정보는 목적이 달성된 후 별도의 DB로 옮겨져(종이의 경우 별도의 서류함) 내부 방침 및 기타 관련 법령에 의한 정보보호 사유에 따라(보유 및 이용기간 참조) 일정 기간 저장된 후 파기되어집니다.</p>
	  					<p>별도 DB로 옮겨진 개인정보는 법률에 의한 경우가 아니고서는 보유되어지는 이외의 다른 목적으로 이용되지 않습니다.</p>
	  					<p><br></p>
	  					<p>o 파기방법</p>
	  					<p>전자적 파일형태로 저장된 개인정보는 기록을 재생할 수 없는 기술적 방법을 사용하여 삭제합니다.</p>
	  					<p><br></p>
	  					<p><br></p>
	  					<p>■ 개인정보 제공</p>
	  					<p><br></p>
	  					<p>회사는 이용자의 개인정보를 원칙적으로 외부에 제공하지 않습니다. 다만, 아래의 경우에는 예외로 합니다.</p>
	  					<p><br></p>
	  					<p>o 이용자들이 사전에 동의한 경우</p>
	  					<p><br></p>
	  					<p>o 법령의 규정에 의거하거나, 수사 목적으로 법령에 정해진 절차와 방법에 따라 수사기관의 요구가 있는 경우</p>
	  					<p><br></p>
	  					<p><br></p>
	  					<p>■ 수집한 개인정보의 위탁</p>
	  					<p><br></p>
	  					<p>회사는 서비스 이행을 위해 아래와 같이 외부 전문업체에 위탁하여 운영하고 있습니다.</p>
	  					<p><br></p>
	  					<p>o 위탁 대상자 : CJ대한통운</p>
	  					<p>o 위탁업무 내용 : 택배배송</p>
	  					<p><br></p>
	  					<p><br></p>
	  					<p>o 위탁 대상자 : (주)카페24</p>
	  					<p>o 위탁업무 내용 : 호스팅 시스템 구축 및 유지</p>
	  					<p><br></p>
	  					<p><br></p>
	  					<p>o 위탁 대상자 : 이니시스</p>
	  					<p>o 위탁업무 내용 : 결제, 구매안전서비스 제공 등</p>
	  					<p><br></p>
	  					<p><br></p>
	  					<p>o 위탁 대상자 : (주)크리마팩토리</p>
	  					<p>o 위탁업무 내용 : 주문관련정보, 간편리뷰/작성 관리 및 알림 관련 업무</p>
	  					<p><br></p>
	  					<p><br></p>
	  					<p>o 위탁 대상자 : ㈜루나소프트</p><p>o 위탁업무 내용 : 카카오톡 알림톡(정보성메시지) 발송 및 톡 기반 상담 서비스 제공</p>
	  					<p><br></p>
	  					<p><br></p>
	  					<p>o 위탁 대상자 : 주식회사 난소프트</p><p>o 위탁업무 내용 : 물류 배송</p>
	  					<p><br></p>
	  					<p><br></p>
	  					<p>o 위탁 대상자 : 효성ITX</p><p>o 위탁업무 내용 : 고객 CS 응대 및 주문관련정보</p>
	  					<p><br></p><p><br></p>
	  					<p>o 위탁 대상자 : 주식회사 우승물류</p><p>o 위탁업무 내용 : 택배배송</p>
	  					<br><br>o 위탁 대상자 : ㈜스냅컴퍼니<br>
	  					o 알림전송수탁업체 : 주식회사 루나소프트(재위탁 : 비즈톡 주식회사)<br>
						o 위탁업무 내용 : 고객 개인정보(이름, 아이디, 휴대전화번호)를 통해 신규 서비스(제품) 개발 및 맞춤 서비스 제공, 이벤트 및 광고성 정보 및 참여기회 제공
						<br><br><br>#&nbsp;개인정보 제3자 제공<br>
						<span style="font-size: 12px;"></span>
						<p style="margin-top:0cm;margin-right:0cm;margin-bottom:8.0pt;margin-left:0cm;text-align:left;line-height:107%;font-size:13px;font-family:&quot;맑은 고딕&quot;;background:white;">
							<span style="font-size: 12px;">수탁자&nbsp;: (주)바이앱스<br>위탁업무&nbsp;:&nbsp;앱플랫폼서비스<br>#개인정보의 위탁 처리<br>"(주)바이앱스"는 서비스 향상을 위해 관계법령에 따라 회원의 동의를 얻거나 관련 사항을 공개 또는 고지 후 회원의 개인정보를 외부에 위탁하여 처리하고 있습니다.</span>
						</p>
						<span style="font-size: 12px;">"(주)바이앱스"는 개인정보처리 수탁자와 그 업무의 내용은 다음과 같습니다.<br>-&nbsp;수탁자&nbsp;: (주)바이앱스<br>-&nbsp;위탁 업무 내용&nbsp;:&nbsp;앱 전용 포인트 및 리타겟팅 푸쉬알림 서비스 제공<br>-&nbsp;수집항목&nbsp;:&nbsp;앱행태정보,&nbsp;회원ID,&nbsp;회원등급<br>-&nbsp;보유기간&nbsp;:&nbsp;법정보유기간<br><br>o 제공받는 자 : (주)데이터라이즈<br>o&nbsp;제공 목적&nbsp;: SMS,&nbsp;카카오톡 채널 및&nbsp;E-mail&nbsp;안내에 필요한 정보,&nbsp;정보 안내 및 이벤트 전달 서비스 업무<br>o&nbsp;제공 항목&nbsp;: [일반]&nbsp;이름,&nbsp;아이디,&nbsp;휴대폰 번호,&nbsp;이메일 주소<br>o&nbsp;보유기간&nbsp;:&nbsp;계약 종료 시까지</span>
						<br><p><br>
						</p><p><br>
						<p>■ 이용자 및 법정대리인의 권리와 그 행사방법</p>
						<p><br></p>
						<p>o 이용자 및 법정 대리인은 언제든지 등록되어 있는 자신 혹은 당해 만 14세 미만 아동의 개인정보를 조회하거나 수정할 수 있으며 가입해지를 요청할 수도 있습니다.</p>
						<p>o 이용자 혹은 만 14세 미만 아동의의 개인정보 조회, 수정을 위해서는 "개인정보변경"(또는 "회원정보수정" 등)을 가입해지(동의철회)를 위해서는 "회원탈퇴"를 클릭하여 본인 확인 절차를 거치신 후 직접 열람, 정정 또는 탈퇴가 가능합니다.</p>
						<p>혹은 개인정보관리책임자에게 서면, 전화 또는 이메일로 연락시 처리 가능합니다.</p>
						<p>o 귀하가 개인정보의 오류에 대한 정정을 요청하신 경우에는 정정을 완료하기 전까지 당해 개인정보를 이용 또는 제공하지 않습니다. 또한 잘못된 개인정보를 제3자에게 이미 제공한 경우에는 정정 처리결과를 제3자에게 통지하여 정정 처리를 합니다.</p>
						<p><br></p>
						<p>o 회사는 이용자 혹은 법정 대리인의 요청에 의해 해지 또는 삭제된 개인정보는 "회사가 수집하는 개인정보의 보유 및 이용기간"에 명시된 바에 따라 처리하고 그 외의 용도로 열람 또는 이용할 수 없도록 처리하고 있습니다.</p>
						<p><br></p>
						<p><br></p>
						<p>■ 개인정보 자동수집 장치의 설치, 운영 및 그 거부에 관한 사항</p>
						<p><br></p>
						<p>회사는 귀하의 정보를 수시로 저장하고 찾아내는 "쿠키(cookie)" 등을 운용합니다. 쿠키란 웹사이트를 운영하는데 이용되는 서버가 귀하의 브라우저에 보내는 아주 작은 텍스트 파일로서 귀하의 컴퓨터 하드디스크에 저장됩니다.</p>
						<p>회사은(는) 다음과 같은 목적을 위해 쿠키를 사용합니다.</p>
						<p><br></p>
						<p>o 쿠키 등 사용 목적</p>
						<p>1. 회원과 비회원의 접속 빈도나 방문 시간 등을 분석, 이용자의 취향과 관심분야를 파악 및 자취 추적, 각종 이벤트 참여 정도 및 방문 회수 파악 등을 통한 타겟 마케팅 및 개인 맞춤 서비스 제공</p>
						<p>2. 귀하는 쿠키 설치에 대한 선택권을 가지고 있습니다. 따라서, 귀하는 웹브라우저에서 옵션을 설정함으로써 모든 쿠키를 허용하거나, 쿠키가 저장될 때마다 확인을 거치거나, 아니면 모든 쿠키의 저장을 거부할 수도 있습니다.</p>
						<p><br></p>
						<p>o 쿠키 설정 거부 방법</p>
						<p>1. 쿠키 설정을 거부하는 방법으로는 회원님이 사용하시는 웹 브라우저의 옵션을 선택함으로써 모든 쿠키를 허용하거나 쿠키를 저장할 때마다 확인을 거치거나, 모든 쿠키의 저장을 거부할 수 있습니다.</p>
						<p>2. 설정방법 예(인터넷 익스플로어의 경우) : 웹 브라우저 상단의 도구 &gt; 인터넷 옵션 &gt; 개인정보</p><p>3. 단, 귀하께서 쿠키 설치를 거부하였을 경우 서비스 제공에 어려움이 있을 수 있습니다.</p>
						<p><br></p>
						<p><br></p>
						<p>■ 개인정보에 관한 민원서비스</p>
						<p><br></p>
						<p>회사는 고객의 개인정보를 보호하고 개인정보와 관련한 불만을 처리하기 위하여 아래와 같이 관련 부서 및 개인정보관리책임자를 지정하고 있습니다.</p>
						<p><br></p>
						<p>o 개인정보관리담당자</p>
						<p>소속/성명 : CX실 최유정 파트장</p>
						<p>전화번호 : 1566-3197</p>
						<p>이메일 : hello@mediquitous.com</p>
						<p><br></p><p><br></p>
						<p>o 개인정보관리책임자</p>
						<p>소속/성명 : CX실 고기범 실장</p>
						<p>전화번호 : 1566-3197</p>
						<p>이메일 : hello@mediquitous.com</p>
						<p><br></p>
						<p>o 귀하께서는 회사의 서비스를 이용하시며 발생하는 모든 개인정보보호 관련 민원을 개인정보관리책임자 혹은 담당부서로 신고하실 수 있습니다.</p>
						<p><br></p>
						<p>o 회사는 이용자들의 신고사항에 대해 신속하게 충분한 답변을 드릴 것입니다.</p>
						<p><br></p>
						<p>o 기타 개인정보침해에 대한 신고나 상담이 필요하신 경우에는 아래 기관에 문의하시기 바랍니다.</p>
						<p>개인정보침해신고센터 (privacy.kisa.or.kr / 국번 없이 118)</p>
						<p>대검찰청 사이버범죄수사단 (www.spo.go.kr / 02-3480-2000)</p>
						<p>경찰청 사이버안전국 (www.ctrc.go.kr/ 국번 없이 182)</p>
						<br>
						<span style="color: rgb(0, 0, 0); font-family: Arial, sans-serif; font-size: 12px; font-style: normal; font-variant-ligatures: normal; font-variant-caps: normal; font-weight: 400; letter-spacing: normal; orphans: 2; text-align: start; text-indent: 0px; text-transform: none; white-space: normal; widows: 2; word-spacing: 0px; -webkit-text-stroke-width: 0px; background-color: rgb(255, 255, 255); text-decoration-thickness: initial; text-decoration-style: initial; text-decoration-color: initial; display: inline !important; float: none;">현재 개인정보 처리방침 공고일자: 2022년 9월 20일</span>
						<br style="color: rgb(0, 0, 0); font-family: Arial, sans-serif; font-size: 12px; font-style: normal; font-variant-ligatures: normal; font-variant-caps: normal; font-weight: 400; letter-spacing: normal; orphans: 2; text-align: start; text-indent: 0px; text-transform: none; white-space: normal; widows: 2; word-spacing: 0px; -webkit-text-stroke-width: 0px; background-color: rgb(255, 255, 255); text-decoration-thickness: initial; text-decoration-style: initial; text-decoration-color: initial;">
						<span style="color: rgb(0, 0, 0); font-family: Arial, sans-serif; font-size: 12px; font-style: normal; font-variant-ligatures: normal; font-variant-caps: normal; font-weight: 400; letter-spacing: normal; orphans: 2; text-align: start; text-indent: 0px; text-transform: none; white-space: normal; widows: 2; word-spacing: 0px; -webkit-text-stroke-width: 0px; background-color: rgb(255, 255, 255); text-decoration-thickness: initial; text-decoration-style: initial; text-decoration-color: initial; display: inline !important; float: none;">현재 개인정보 처리방침 시행일자: 2022년 10월 1일</span>
						<br>
					</div>
	  			</div>
  			</div>
		</div>
	</section>
	<%-- end inner page section --%>
	
	<jsp:include page="footer.jsp" />