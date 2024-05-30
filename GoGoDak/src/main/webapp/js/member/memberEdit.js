let b_emailcheck_click = false;
// "이메일중복확인" 을 클릭했는지 클릭을 안했는지 여부를 알아오기 위한 용도

let b_email_change = false;
// 이메일값을 변경했는지 여부를 알아오기 위한 용도

$(document).ready(function(){

	$("span.error").hide();
	$("input#name").focus();
	
	$("input#name").blur( (e) => {
		
		const name = $(e.target).val().trim();
		if(name == "") {
			// 입력하지 않거나 공백만 입력했을 경우 
			$("table#tblMemberEdit :input").prop("disabled", true);  
			$(e.target).prop("disabled", false); 
		    $(e.target).parent().find("span.error").show();
			$(e.target).val("").focus(); 
		}
		else {
			// 공백이 아닌 글자를 입력했을 경우
			$("table#tblMemberEdit :input").prop("disabled", false);
		    $(e.target).parent().find("span.error").hide();
		}
	
	});// 아이디가 name 인 것은 포커스를 잃어버렸을 경우(blur) 이벤트를 처리해주는 것이다.


	$("input#pwd").blur( (e) => {
	    const regExp_pwd = new RegExp(/^.*(?=^.{8,15}$)(?=.*\d)(?=.*[a-zA-Z])(?=.*[^a-zA-Z0-9]).*$/g); 
	    // 숫자/문자/특수문자 포함 형태의 8~15자리 이내의 암호 정규표현식 객체 생성 
	    
	    const bool = regExp_pwd.test($(e.target).val());	
		
		if(!bool) {
			// 암호가 정규표현식에 위배된 경우 
			$("table#tblMemberEdit :input").prop("disabled", true);  
			$(e.target).prop("disabled", false); 
		    $(e.target).parent().find("span.error").show();
			$(e.target).val("").focus(); 
		}
		else {
			// 암호가 정규표현식에 맞는 경우 
			$("table#tblMemberEdit :input").prop("disabled", false);
		    $(e.target).parent().find("span.error").hide();
		}
		
	});// 아이디가 pwd 인 것은 포커스를 잃어버렸을 경우(blur) 이벤트를 처리해주는 것이다.


	$("input#pwdcheck").blur( (e) => {
		
		if( $("input#pwd").val() != $(e.target).val() ) {
			// 암호와 암호확인값이 틀린 경우 
			$("table#tblMemberEdit :input").prop("disabled", true);  
			$("input#pwd").prop("disabled", false);
			$(e.target).prop("disabled", false); 
		    $(e.target).parent().find("span.error").show();
			$("input#pwd").focus();
		}
		else {
			// 암호와 암호확인값이 같은 경우
			$("table#tblMemberEdit :input").prop("disabled", false);
		    $(e.target).parent().find("span.error").hide();
		}
		
	});// 아이디가 pwdcheck 인 것은 포커스를 잃어버렸을 경우(blur) 이벤트를 처리해주는 것이다.	


	$("input#email").blur( (e) => {
	    const regExp_email = new RegExp(/^[0-9a-zA-Z]([-_\.]?[0-9a-zA-Z])*@[0-9a-zA-Z]([-_\.]?[0-9a-zA-Z])*\.[a-zA-Z]{2,3}$/i);  
	    // 이메일 정규표현식 객체 생성 
	    
	    const bool = regExp_email.test($(e.target).val());	
		
		if(!bool) {
			// 이메일이 정규표현식에 위배된 경우 
			$("table#tblMemberEdit :input").prop("disabled", true);  
			$(e.target).prop("disabled", false); 
		    $(e.target).parent().find("span.error").show();
			$(e.target).val("").focus(); 
		}
		else {
			// 이메일이 정규표현식에 맞는 경우 
			$("table#tblMemberEdit :input").prop("disabled", false);
		    $(e.target).parent().find("span.error").hide();
		}
		
	});// 아이디가 email 인 것은 포커스를 잃어버렸을 경우(blur) 이벤트를 처리해주는 것이다.


	$("input#hp2").blur( (e) => {
	    const regExp_hp2 = new RegExp(/^[1-9][0-9]{3}$/);  
	    // 연락처 국번( 숫자 4자리인데 첫번째 숫자는 1-9 이고 나머지는 0-9) 정규표현식 객체 생성 
	    
	    const bool = regExp_hp2.test($(e.target).val());	
		
		if(!bool) {
			// 연락처 국번이 정규표현식에 위배된 경우 
			$("table#tblMemberEdit :input").prop("disabled", true);  
			$(e.target).prop("disabled", false); 
		    $(e.target).parent().find("span.error").show();
			$(e.target).val("").focus(); 
		}
		else {
			// 연락처 국번이 정규표현식에 맞는 경우 
			$("table#tblMemberEdit :input").prop("disabled", false);
		    $(e.target).parent().find("span.error").hide();
		}
		
	});// 아이디가 hp2 인 것은 포커스를 잃어버렸을 경우(blur) 이벤트를 처리해주는 것이다.


	$("input#hp3").blur( (e) => {
	    const regExp_hp3 = new RegExp(/^\d{4}$/);  
	    // 숫자 4자리만 들어오도록 검사해주는 정규표현식 객체 생성 
	    
	    const bool = regExp_hp3.test($(e.target).val());	
		
		if(!bool) {
			// 마지막 전화번호 4자리가 정규표현식에 위배된 경우 
			$("table#tblMemberEdit :input").prop("disabled", true);  
			$(e.target).prop("disabled", false); 
		    $(e.target).parent().find("span.error").show();
			$(e.target).val("").focus(); 
		}
		else {
			// 마지막 전화번호 4자리가 정규표현식에 맞는 경우 
			$("table#tblMemberEdit :input").prop("disabled", false);
		    $(e.target).parent().find("span.error").hide();
		}
		
	});// 아이디가 hp3 인 것은 포커스를 잃어버렸을 경우(blur) 이벤트를 처리해주는 것이다.


	$("input#postcode").blur( (e) => {
	    const regExp_postcode = new RegExp(/^\d{5}$/);  
	    // 숫자 5자리만 들어오도록 검사해주는 정규표현식 객체 생성 
	    
	    const bool = regExp_postcode.test($(e.target).val());	
		
		if(!bool) {
			// 우편번호가 정규표현식에 위배된 경우 
			$("table#tblMemberEdit :input").prop("disabled", true);  
			$(e.target).prop("disabled", false); 
		    $(e.target).parent().find("span.error").show();
			$(e.target).val("").focus(); 
		}
		else {
			// 우편번호가 정규표현식에 맞는 경우 
			$("table#tblMemberEdit :input").prop("disabled", false);
		    $(e.target).parent().find("span.error").hide();
		}
		
	});// 아이디가 postcode 인 것은 포커스를 잃어버렸을 경우(blur) 이벤트를 처리해주는 것이다.


	// === "우편번호찾기"를 클릭했을 때 이벤트 처리하기 === //
	$("span#postcodecheck").click(function(){
		
		// 주소를 쓰기가능 으로 만들기
		$("input#address").removeAttr("readonly");
        // 참고항목을 쓰기가능 으로 만들기
        $("input#extraAddress").removeAttr("readonly");
        
        new daum.Postcode({
            oncomplete: function(data) {
                // 팝업에서 검색결과 항목을 클릭했을때 실행할 코드를 작성하는 부분.

                // 각 주소의 노출 규칙에 따라 주소를 조합한다.
                // 내려오는 변수가 값이 없는 경우엔 공백('')값을 가지므로, 이를 참고하여 분기 한다.
                let addr = ''; // 주소 변수
                let extraAddr = ''; // 참고항목 변수

                //사용자가 선택한 주소 타입에 따라 해당 주소 값을 가져온다.
                if (data.userSelectedType === 'R') { // 사용자가 도로명 주소를 선택했을 경우
                    addr = data.roadAddress;
                } else { // 사용자가 지번 주소를 선택했을 경우(J)
                    addr = data.jibunAddress;
                }

                // 사용자가 선택한 주소가 도로명 타입일때 참고항목을 조합한다.
                if(data.userSelectedType === 'R'){
                    if(data.bname !== '' && /[동|로|가]$/g.test(data.bname)){
                        extraAddr += data.bname;
                    }
                    if(data.buildingName !== '' && data.apartment === 'Y'){
                        extraAddr += (extraAddr !== '' ? ', ' + data.buildingName : data.buildingName);
                    }
                    if(extraAddr !== ''){
                        extraAddr = ' (' + extraAddr + ')';
                    }
                    document.getElementById("extraAddress").value = extraAddr;
                } else {
                    document.getElementById("extraAddress").value = '';
                }

                document.getElementById('postcode').value = data.zonecode;
                document.getElementById("address").value = addr;
                document.getElementById("detailAddress").focus();
            }
        }).open();
        
        // 주소를 읽기전용(readonly) 로 만들기
        $("input#address").attr("readonly", true);
        $("input#extraAddress").attr("readonly", true);
	});
 
	// "이메일중복확인" 을 클릭했을 때 이벤트 처리하기
	$("span#emailcheck").click(function(){
		 
		 b_emailcheck_click = true;
		 
		 $.ajax({
			 url:"emailDuplicateCheck.dk",
			 data:{"email":$("input#email").val()
			      ,"userid":$("input:hidden[name='id']").val()}, 
			 type:"post",
			 async:true,
			 dataType:"json", 
			 success:function(json){
				 if(json.isExists) {
					 $("span#emailCheckResult").html($("input#email").val() + " 은 이미 사용중 이므로 다른 이메일을 입력하세요").css({"color":"red"}); 
					 $("input#email").val(""); 
				 } else {
					 $("span#emailCheckResult").html($("input#email").val() + " 은 사용가능 합니다").css({"color":"navy"});  
				 }
			 },
			 error: function(request, status, error){
					alert("code: "+request.status+"\n"+"message: "+request.responseText+"\n"+"error: "+error);
			 }
		 });
	 });

	$("input#email").bind("change", function(){
		 b_emailcheck_click = false;
		 b_email_change = true;
	});
	
	
	
});// end of $(document).ready(function(){})----------------------


// Function Declaration
// "수정하기" 버튼 클릭시 호출되는 함수 
function goEdit(){
	let b_requiredInfo = false;
    const requiredInfo_list = document.querySelectorAll("input.requiredInfo"); 
    for(let i=0; i<requiredInfo_list.length; i++){
		const val = requiredInfo_list[i].value.trim();
		if(val == "") {
			alert("*표시된 필수입력사항은 모두 입력하셔야 합니다.");
		    b_requiredInfo = true;
		    break; 
		}
	}// end of for-----------------------------
     	
	
	if(b_requiredInfo) {
		return;
	}
	
	if(b_email_change && !b_emailcheck_click) {
		alert("이메일 중복확인을 클릭하셔야 합니다.");
		return;
	}
	
	const postcode = $("input#postcode").val().trim();
	const address = $("input#address").val().trim();
	const detailAddress = $("input#detailAddress").val().trim();
	const extraAddress = $("input#extraAddress").val().trim();
	
	if(postcode == "" || address == "" || detailAddress == "" || extraAddress == "") {
		alert("우편번호 및 주소를 입력하셔야 합니다.");
		return;
	}
	
	let isNewPwd = true;
	
	$.ajax({
			 url:"duplicatePwdCheck.up",
			 data:{"new_pwd":$("input:password[name='pwd']").val()
			      ,"userid":$("input:hidden[name='id']").val()},
			 type:"post",
			 async:false,
			 dataType:"json",
			 success:function(json){
				 if(json.isExists) {
					 $("span#duplicate_pwd").html("현재 사용중인 비밀번호로 비밀번호 변경은 불가합니다."); 
					 isNewPwd = false;
				 }
			 },
			 error: function(request, status, error){
					alert("code: "+request.status+"\n"+"message: "+request.responseText+"\n"+"error: "+error);
			 }
	}); 
	
	if(isNewPwd) {
		const frm = document.editFrm;
		frm.action = "/member/memberEdit.dk";
		frm.method = "post";
		frm.submit();
	}
}// end of function goEdit()-----------------------