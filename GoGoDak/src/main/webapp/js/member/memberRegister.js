let b_idcheck_click = false; 
// "아이디중복확인" 을 클릭했는지 클릭을 안했는지 여부를 알아오기 위한 용도
let b_emailcheck_click = false; 
// "이메일중복확인" 을 클릭했는지 클릭을 안했는지 여부를 알아오기 위한 용도
let b_zipcodeSearch_click = false; 
// "우편번호찾기" 를 클릭했는지 클릭을 안했는지 여부를 알아오기 위한 용도

$(document).ready(function(){
	
	$("small.error").hide();
    $("input#name").focus();

    ///////////////////////////////////////////////////////////////
    
    $("input#name").blur( (e) => { 
		
        const name = $(e.target).val().trim();

        if(name == ""){ 
            $("div#tblMemberRegister :input").prop("disabled", true); 
            // table 안에 있는 모든 input 태그를 비활성화한다.
            $(e.target).prop("disabled", false); 
            // 비활성화된 input 태그 중에서 name 만 활성화시킨다.
            $(e.target).val("").focus(); 
            $(e.target).parent().find("small.error").show();
        }
        else{ 
            $("div#tblMemberRegister :input").prop("disabled", false);
            $(e.target).parent().find("small.error").hide();
        }

    });

    ///////////////////////////////////////////////////////////////

    $("input#id").blur( (e) => { 

        const regExp_id = new RegExp(/^[a-z0-9_]{5,40}$/); 
        // 영문자, 숫자가 혼합된 5~40 자리 이내의 아이디 정규표현식 객체 생성
        
        const bool = regExp_id.test($(e.target).val());

        if(!bool){ // 아이디가 정규표현식에 위배된 경우
            $("div#tblMemberRegister :input").prop("disabled", true); 
            $(e.target).prop("disabled", false); 
            $(e.target).val("").focus(); 
            $(e.target).parent().find("small.error").show();
        }
        else{ 
            $("div#tblMemberRegister :input").prop("disabled", false);
            $(e.target).parent().find("small.error").hide();
        }

    });

    ///////////////////////////////////////////////////////////////

    $("input#pwd").blur( (e) => { 

        const regExp_pwd = new RegExp(/^.*(?=^.{8,15}$)(?=.*\d)(?=.*[a-zA-Z])(?=.*[^a-zA-Z0-9]).*$/g);
        // 숫자/문자/특수문자 포함 형태의 8~15자리 이내의 암호 정규표현식 객체 생성
        
        const bool = regExp_pwd.test($(e.target).val());

        if(!bool){ // 암호가 정규표현식에 위배된 경우
            $("div#tblMemberRegister :input").prop("disabled", true); 
            $(e.target).prop("disabled", false); 
            $(e.target).val("").focus(); 
            $(e.target).parent().find("small.error").show();
        }
        else{ 
            $("div#tblMemberRegister :input").prop("disabled", false);
            $(e.target).parent().find("small.error").hide();
        }

    });

    ///////////////////////////////////////////////////////////////

    $("input#pwdcheck").blur( (e) => { 

        if( $("input#pwd").val() != $(e.target).val() ){ 
            $("div#tblMemberRegister :input").prop("disabled", true); 
            $("input#pwd").prop("disabled", false);  
            $(e.target).prop("disabled", false); 
            $("input#pwd").val("").focus(); 
            $(e.target).val("");
            $(e.target).parent().find("small.error").show();
        }
        else{ 
            $("div#tblMemberRegister :input").prop("disabled", false);
            $(e.target).parent().find("small.error").hide();
        }

    });

    ///////////////////////////////////////////////////////////////

    $("input#email").blur( (e) => { 

        const regExp_email = new RegExp(/^[0-9a-z]([-_\.]?[0-9a-z])*@[0-9a-z]([-_\.]?[0-9a-z])*\.[a-z]{2,3}$/i);  
		// 이메일 정규표현식 객체 생성
		
        const bool = regExp_email.test($(e.target).val());

        if(!bool){ // 이메일이 정규표현식에 위배된 경우
            $("div#tblMemberRegister :input").prop("disabled", true); 
            $(e.target).prop("disabled", false); 
            $(e.target).val("").focus(); 
            $(e.target).parent().find("small.error").show();
        }
        else{ 
            $("div#tblMemberRegister :input").prop("disabled", false);
            $(e.target).parent().find("small.error").hide();
        }

    });

    ///////////////////////////////////////////////////////////////

    $("input#hp2").blur( (e) => {
      
        const regExp_hp2 = new RegExp(/^[1-9][0-9]{3}$/);  
        // 연락처 국번(숫자 4자리인데 첫번째 숫자는 1-9 이고 나머지는 0-9) 정규표현식 객체 생성
        
        const bool = regExp_hp2.test($(e.target).val());   
        
        if(!bool) { // 연락처 국번이 정규표현식에 위배된 경우
            $("div#tblMemberRegister :input").prop("disabled", true);  
            $(e.target).prop("disabled", false); 
            $(e.target).parent().parent().siblings("small.error").show();
            $(e.target).val("").focus(); 
        }
        else { 
            $("div#tblMemberRegister :input").prop("disabled", false);
            $(e.target).parent().parent().siblings("small.error").hide();
        }
        
    });
        
    ///////////////////////////////////////////////////////////////

    $("input#hp3").blur( (e) => {
    
        const regExp_hp3 = new RegExp(/^\d{4}$/);  
        // 숫자 4자리만 들어오도록 검사해주는 정규표현식 객체 생성
        
        const bool = regExp_hp3.test($(e.target).val());   
        
        if(!bool) { // 마지막 전화번호 4자리가 정규표현식에 위배된 경우
            $("div#tblMemberRegister :input").prop("disabled", true);  
            $(e.target).prop("disabled", false); 
            $(e.target).parent().parent().siblings("small.error").show();
            $(e.target).val("").focus(); 
        }
        else { 
            $("div#tblMemberRegister :input").prop("disabled", false);
            $(e.target).parent().parent().siblings("small.error").hide();
        }
        
    });

	///////////////////////////////////////////////////////////////

    $("input#postcode").blur( (e) => {
      
        const regExp_postcode = new RegExp(/^\d{5}$/);
        // 숫자 5자리만 들어오도록 검사해주는 정규표현식 객체 생성
        
        const bool = regExp_postcode.test($(e.target).val());   
        
        if(!bool) { // 우편번호가 정규표현식에 위배된 경우
            $("div#tblMemberRegister :input").prop("disabled", true);  
            $(e.target).prop("disabled", false); 
            $(e.target).parent().find("small.error").show();
            $(e.target).val("").focus(); 
        }
        else {  
            $("div#tblMemberRegister :input").prop("disabled", false);
            $(e.target).parent().find("small.error").hide();
        }
        
    });

    ///////////////////////////////////////////////////////////////

    $("input#postcode").attr("readonly", true);
    // 우편번호를 읽기전용(readonly) 으로 만들기
    $("input#address").attr("readonly", true);
    // 주소를 읽기전용(readonly) 으로 만들기
    $("input#extraAddress").attr("readonly", true);
    // 참고항목을 읽기전용(readonly) 으로 만들기

    ///////////////////////////////////////////////////////////////

    $("img#zipcodeSearch").click(function(){ // "우편번호찾기" 를 클릭했을 때 이벤트 처리
      
        b_zipcodeSearch_click = true;
    
        new daum.Postcode({
            oncomplete: function(data) {
                let addr = ''; // 주소 변수
                let extraAddr = ''; // 참고항목 변수
            
                if (data.userSelectedType === 'R') { // 사용자가 도로명 주소를 선택했을 경우
                    addr = data.roadAddress;
                } 
                else { // 사용자가 지번 주소를 선택했을 경우(J)
                    addr = data.jibunAddress;
                }

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
                } 
                else {
                    document.getElementById("extraAddress").value = '';
                }

                document.getElementById('postcode').value = data.zonecode;
                document.getElementById("address").value = addr;
                
                document.getElementById("detailAddress").focus();
            }
        }).open();

        $("input#postcode").attr("readonly", true);
        $("input#address").attr("readonly", true);
        $("input#extraAddress").attr("readonly", true);

    }); // end of $("img#zipcodeSearch").click() ----------

    ///////////////////////////////////////////////////////////////
   	
   	$("input#birthdate").blur( (e) => {
		
		const regExp_birthdate = new RegExp(/^([0-9]{2}(0[1-9]|1[0-2])(0[1-9]|[1,2][0-9]|3[0,1]))$/);
		// 생년월일을 검사해주는 정규표현식 객체 생성
		
		const bool = regExp_birthdate.test($(e.target).val()); 
    
        if(!bool) { // 생년월일이 정규표현식에 위배된 경우
            $("div#tblMemberRegister :input").prop("disabled", true);  
            $(e.target).prop("disabled", false); 
            $(e.target).parent().parent().siblings("small.error").show();
            $(e.target).val("").focus(); 
        }
        else {  
            $("div#tblMemberRegister :input").prop("disabled", false);
            $(e.target).parent().parent().siblings("small.error").hide();
        }
    });

    ///////////////////////////////////////////////////////////////
    
   	$("input#gender").blur( (e) => {
		
		const regExp_gender = new RegExp(/^[1-4]+$/);
		// 성별을 검사해주는 정규표현식 객체 생성
		
		const bool = regExp_gender.test($(e.target).val()); 
    
        if(!bool) { // 성별이 정규표현식에 위배된 경우
            $("div#tblMemberRegister :input").prop("disabled", true);  
            $(e.target).prop("disabled", false); 
            $(e.target).parent().parent().siblings("small.error").show();
            $(e.target).val("").focus(); 
        }
        else {  
            $("div#tblMemberRegister :input").prop("disabled", false);
            $(e.target).parent().parent().siblings("small.error").hide();
        }
    });

    ///////////////////////////////////////////////////////////////

    $("button#idcheck").click(function(){ // "아이디중복확인" 을 클릭했을 때 이벤트 처리
        b_idcheck_click = true;
      
        $.ajax({
            url : "idDuplicateCheck.dk", // js 는 DAO 를 바로 사용할 수 없기 때문에 url 로 보내줘야 한다.
            data : {"id":$("input#id").val()}, // url 로 전송해야할 데이터
            type : "post", 
            async : true, // 비동기 방식
            dataType : "json", // 실행되어진 결과물을 받아오는 데이터타입
            success : function(json){ // 결과물이 들어온다. true 또는 false
                if(json.isExists){ 
                    $("span#idcheckResult").html($("input#id").val() + " 은 이미 사용 중 이므로 다른 아이디를 입력하세요.").css({"color":"red", "font-weight":"bold"});
                    $("input#id").val(""); 
                }
                else{ 
                    $("span#idcheckResult").html($("input#id").val() + " 은 사용 가능한 아이디입니다.").css({"color":"#fbc02d", "font-weight":"bold"});
                }
            },
            error: function(request, status, error){
                alert("code: "+request.status+"\n"+"message: "+request.responseText+"\n"+"error: "+error);
            }
        });
    });

    ///////////////////////////////////////////////////////////////

    $("button#emailcheck").click(function(){ // "이메일중복확인" 을 클릭했을 때 이벤트 처리
        b_emailcheck_click = true;

        $.ajax({
            url : "emailDuplicateCheck.dk", 
            data : {"email":$("input#email").val()}, 
            type : "post", 
            async : true,  
            dataType : "json", 
            success : function(json){ 
                if(json.isExists){ 
                    $("span#emailCheckResult").html($("input#email").val() + " 은 이미 사용 중 이므로 다른 이메일을 입력하세요.").css({"color":"red", "font-weight":"bold"});
                    $("input#email").val(""); 
                }
                else{ 
                    $("span#emailCheckResult").html($("input#email").val() + " 은 사용 가능한 이메일입니다.").css({"color":"#fbc02d", "font-weight":"bold"});
                }
            },
            error: function(request, status, error){
                alert("code: "+request.status+"\n"+"message: "+request.responseText+"\n"+"error: "+error);
            }
        });
    });

    ///////////////////////////////////////////////////////////////

    // 아이디값이 변경되면 가입하기 버튼을 클릭 시 
    // "아이디중복확인" 을 클릭했는지 클릭안했는지를 알아보기 위한 용도 초기화 시키기 
    $("input#id").bind("change", function(){
        b_idcheck_click = false;
    });
  
    // 이메일값이 변경되면 가입하기 버튼을 클릭 시 
    // "이메일중복확인" 을 클릭했는지 클릭안했는지를 알아보기 위한 용도 초기화 시키기 
    $("input#email").bind("change", function(){
        b_emailcheck_click = false;
    });

}); // end of $(document).ready(function(){}) ----------

///////////////////////////////////////////////////////////////

// Function Declaration
// "가입하기" 버튼 클릭 시 호출되는 함수
function goRegister(){

    let b_requiredInfo = true; // 필수 입력사항에 모두 입력이 되었는지 검사
    const requiredInfo_list = document.querySelectorAll("input.requiredInfo");
    
    for(let i=0; i<requiredInfo_list.length; i++){
        
        const val = requiredInfo_list[i].value.trim();

        if(val == ""){
            alert("* 표시된 필수입력사항은 모두 입력하셔야 합니다.");
            b_requiredInfo = false;
            break; 
        }

    } // end of for ----------

    if(!b_requiredInfo){
        return; 
    }

    if(!b_idcheck_click){ // "아이디중복확인" 을 클릭했는지 검사
        alert("아이디 중복확인을 클릭하셔야 합니다.");
        return; 
    }

    if(!b_emailcheck_click){ // "이메일중복확인" 을 클릭했는지 검사
        alert("이메일 중복확인을 클릭하셔야 합니다.");
        return;
    }

    if(!b_zipcodeSearch_click){ // "우편번호찾기" 를 클릭했는지 검사
        alert("우편번호찾기를 클릭하셔서 우편번호를 입력하셔야 합니다.");
        return; 
    }

    ///////////////////////////////////////////////////////////////

	// 우편번호 및 주소에 값을 입력했는지 검사
    const postcode = $("input#postcode").val().trim();
    const address = $("input#address").val().trim();
    const detailAddress = $("input#detailAddress").val().trim();
   
    if(postcode == "" || address == "" || detailAddress == "") { 
        alert("우편번호 및 주소를 입력하셔야 합니다.");
        return; 
    }

    ///////////////////////////////////////////////////////////////

	// 주민등록번호에 값을 입력했는지 검사
    const birthdate = $('input#birthdate').val().trim();

    if(birthdate == ""){
        alert("주민등록번호를 입력하셔야 합니다.");
        return; 
    }

   	const gender = $('input#gender').val().trim();

    if(gender == ""){
        alert("주민등록번호를 입력하셔야 합니다.");
        return; 
    }
    
    ///////////////////////////////////////////////////////////////

	// 약관에 동의를 했는지 검사
    const checkbox_checked_length = $("input:checkbox[id='agree']:checked").length;

    if(checkbox_checked_length == 0){
        alert("이용약관에 동의하셔야 합니다.");
        return; 
    }

    ///////////////////////////////////////////////////////////////

    const frm = document.registerFrm;
    frm.action = "memberRegister.dk";
    frm.method = "post";
    frm.submit();

} // end of function goRegister() ----------

///////////////////////////////////////////////////////////////

// Function Declaration
// "취소하기" 버튼 클릭 시 호출되는 함수
function goReset(){

    $("small.error").hide();

    $("span#idcheckResult").empty();
    $("span#emailCheckResult").empty();

} // end of function goReset() ----------