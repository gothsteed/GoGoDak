let b_idcheck_click = false; 
let b_emailcheck_click = false; 
let b_zipcodeSearch_click = false;

$(document).ready(function(){
	
	$("span.error").hide();
    $("input#name").focus();

    $("input#name").blur( (e) => { 

        const name = $(e.target).val().trim();

        if(name == ""){ 
            $("table#tblMemberRegister :input").prop("disabled", true); 
            $(e.target).prop("disabled", false); 
            $(e.target).val("").focus(); 
            
            $(e.target).parent().find("span.error").show();
        }
        else{ 
            $("table#tblMemberRegister :input").prop("disabled", false);

            $(e.target).parent().find("span.error").hide();
        }

    }); // 아이디가 name 인 것은 포커스를 잃어버렸을 경우(blur) 이벤트를 처리해주는 것이다.

    ///////////////////////////////////////////////////////////////

    $("input#userid").blur( (e) => { 

        const userid = $(e.target).val().trim();

        if(userid == ""){ 
            $("table#tblMemberRegister :input").prop("disabled", true); 
            $(e.target).prop("disabled", false); 
            $(e.target).val("").focus(); 
            
            $(e.target).parent().find("span.error").show();
        }
        else{ 
            $("table#tblMemberRegister :input").prop("disabled", false);

            $(e.target).parent().find("span.error").hide();
        }

    }); // 아이디가 userid 인 것은 포커스를 잃어버렸을 경우(blur) 이벤트를 처리해주는 것이다.

    ///////////////////////////////////////////////////////////////

    $("input#pwd").blur( (e) => { 

        const regExp_pwd = new RegExp(/^.*(?=^.{8,15}$)(?=.*\d)(?=.*[a-zA-Z])(?=.*[^a-zA-Z0-9]).*$/g);
        
        const bool = regExp_pwd.test($(e.target).val());

        if(!bool){ 
            $("table#tblMemberRegister :input").prop("disabled", true); 
            $(e.target).prop("disabled", false); 
            $(e.target).val("").focus(); 
         
            $(e.target).parent().find("span.error").show();
        }
        else{ 
            $("table#tblMemberRegister :input").prop("disabled", false);

            $(e.target).parent().find("span.error").hide();
        }

    }); // 아이디가 pwd 인 것은 포커스를 잃어버렸을 경우(blur) 이벤트를 처리해주는 것이다.

    ///////////////////////////////////////////////////////////////

    $("input#pwdcheck").blur( (e) => { 

        if( $("input#pwd").val() != $(e.target).val() ){ 
            $("table#tblMemberRegister :input").prop("disabled", true); 
            $("input#pwd").prop("disabled", false);  
            $(e.target).prop("disabled", false); 
            $("input#pwd").val("").focus(); 
            $(e.target).val("");
            
            $(e.target).parent().find("span.error").show();
        }
        else{ 
            $("table#tblMemberRegister :input").prop("disabled", false);

            $(e.target).parent().find("span.error").hide();
        }

    }); // 아이디가 pwdcheck 인 것은 포커스를 잃어버렸을 경우(blur) 이벤트를 처리해주는 것이다.

    ///////////////////////////////////////////////////////////////

    $("input#email").blur( (e) => { 

        const regExp_email = new RegExp(/^[0-9a-zA-Z]([-_\.]?[0-9a-zA-Z])*@[0-9a-zA-Z]([-_\.]?[0-9a-zA-Z])*\.[a-zA-Z]{2,3}$/i);  

        const bool = regExp_email.test($(e.target).val());

        if(!bool){
            $("table#tblMemberRegister :input").prop("disabled", true); 
            $(e.target).prop("disabled", false); 
            $(e.target).val("").focus(); 
            
            $(e.target).parent().find("span.error").show();
        }
        else{ 
            $("table#tblMemberRegister :input").prop("disabled", false);
    
            $(e.target).parent().find("span.error").hide();
        }

    }); // 아이디가 email 인 것은 포커스를 잃어버렸을 경우(blur) 이벤트를 처리해주는 것이다.

    ///////////////////////////////////////////////////////////////

    $("input#hp2").blur( (e) => {
      
        const regExp_hp2 = new RegExp(/^[1-9][0-9]{3}$/);  
        
        const bool = regExp_hp2.test($(e.target).val());   
        
        if(!bool) { 
            $("table#tblMemberRegister :input").prop("disabled", true);  
            $(e.target).prop("disabled", false); 
         
            $(e.target).parent().find("span.error").show();
                
            $(e.target).val("").focus(); 
        }
        else { 
            $("table#tblMemberRegister :input").prop("disabled", false);
            
            $(e.target).parent().find("span.error").hide();
        }
        
    }); // 아이디가 hp2 인 것은 포커스를 잃어버렸을 경우(blur) 이벤트를 처리해주는 것이다.
        
    ///////////////////////////////////////////////////////////////

    $("input#hp3").blur( (e) => {
    
        const regExp_hp3 = new RegExp(/^\d{4}$/);  
        
        const bool = regExp_hp3.test($(e.target).val());   
        
        if(!bool) { 
            $("table#tblMemberRegister :input").prop("disabled", true);  
            $(e.target).prop("disabled", false); 
            
            $(e.target).parent().find("span.error").show();
                
            $(e.target).val("").focus(); 
        }
        else { 
            $("table#tblMemberRegister :input").prop("disabled", false);
   
            $(e.target).parent().find("span.error").hide();
        }
        
    }); // 아이디가 hp3 인 것은 포커스를 잃어버렸을 경우(blur) 이벤트를 처리해주는 것이다.

	///////////////////////////////////////////////////////////////

    $("input#postcode").blur( (e) => {
      
        const regExp_postcode = new RegExp(/^\d{5}$/);
        
        const bool = regExp_postcode.test($(e.target).val());   
        
        if(!bool) { 
            $("table#tblMemberRegister :input").prop("disabled", true);  
            $(e.target).prop("disabled", false); 
            
            $(e.target).parent().find("span.error").show();
                
            $(e.target).val("").focus(); 
        }
        else {  
            $("table#tblMemberRegister :input").prop("disabled", false);
       
            $(e.target).parent().find("span.error").hide();
        }
        
    }); // 아이디가 postcode 인 것은 포커스를 잃어버렸을 경우(blur) 이벤트를 처리해주는 것이다.

    ///////////////////////////////////////////////////////////////

    $("input#postcode").attr("readonly", true);

    $("input#address").attr("readonly", true);
        
    $("input#extraAddress").attr("readonly", true);

    ///////////////////////////////////////////////////////////////

    // === "우편번호찾기" 를 클릭했을 때 이벤트 처리하기 === //
    $("img#zipcodeSearch").click(function(){
      
        b_zipcodeSearch_click = true;
    
        new daum.Postcode({
            oncomplete: function(data) {
                let addr = ''; // 주소 변수
                let extraAddr = ''; // 참고항목 변수
            
                if (data.userSelectedType === 'R') { // 사용자가 도로명 주소를 선택했을 경우
                    addr = data.roadAddress;
                } else { // 사용자가 지번 주소를 선택했을 경우(J)
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
                } else {
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

    $('input#datepicker').keyup( (e) => { 
		
        $(e.target).val("").next().show();
        
    }); // end of $('input#datepicker').keyup((e) => {}) ----------

    ///////////////////////////////////////////////////////////////

    // === jQuery UI 의 datepicker === //
    $('input#datepicker').datepicker({
        dateFormat: 'yy-mm-dd'    // Input Display Format 변경
        ,showOtherMonths: true    // 빈 공간에 현재월의 앞뒤월의 날짜를 표시
        ,showMonthAfterYear: true // 년도 먼저 나오고, 뒤에 월 표시
        ,changeYear: true         // 콤보박스에서 년 선택 가능
        ,changeMonth: true        // 콤보박스에서 월 선택 가능                
        ,yearSuffix: "년"          // 달력의 년도 부분 뒤에 붙는 텍스트
        ,monthNamesShort: ['1','2','3','4','5','6','7','8','9','10','11','12'] // 달력의 월 부분 텍스트
        ,monthNames: ['1월','2월','3월','4월','5월','6월','7월','8월','9월','10월','11월','12월'] // 달력의 월 부분 Tooltip 텍스트
        ,dayNamesMin: ['일','월','화','수','목','금','토'] // 달력의 요일 부분 텍스트
        ,dayNames: ['일요일','월요일','화요일','수요일','목요일','금요일','토요일'] // 달력의 요일 부분 Tooltip 텍스트         
    });

    ///////////////////////////////////////////////////////////////

    // === 전체 datepicker 옵션 일괄 설정하기 === 
    $(function() {
        //모든 datepicker 에 대한 공통 옵션 설정
        $.datepicker.setDefaults({
            dateFormat: 'yy-mm-dd'   // Input Display Format 변경
            ,showOtherMonths: true   // 빈 공간에 현재월의 앞뒤월의 날짜를 표시
            ,showMonthAfterYear:true // 년도 먼저 나오고, 뒤에 월 표시
            ,changeYear: true        // 콤보박스에서 년 선택 가능
            ,changeMonth: true       // 콤보박스에서 월 선택 가능                        
            ,yearSuffix: "년"         // 달력의 년도 부분 뒤에 붙는 텍스트
            ,monthNamesShort: ['1','2','3','4','5','6','7','8','9','10','11','12'] // 달력의 월 부분 텍스트
            ,monthNames: ['1월','2월','3월','4월','5월','6월','7월','8월','9월','10월','11월','12월'] // 달력의 월 부분 Tooltip 텍스트
            ,dayNamesMin: ['일','월','화','수','목','금','토'] // 달력의 요일 부분 텍스트
            ,dayNames: ['일요일','월요일','화요일','수요일','목요일','금요일','토요일'] // 달력의 요일 부분 Tooltip 텍스트                   
        });
 
        // input 을 datepicker 로 선언
        $("input#fromDate").datepicker();                    
        $("input#toDate").datepicker();
        
        // From 의 초기값을 오늘 날짜로 설정
        $('input#fromDate').datepicker('setDate', 'today'); //(-1D:하루전, -1M:한달전, -1Y:일년전), (+1D:하루후, +1M:한달후, +1Y:일년후)
        
        // To 의 초기값을 3일후로 설정
        $('input#toDate').datepicker('setDate', '+3D'); //(-1D:하루전, -1M:한달전, -1Y:일년전), (+1D:하루후, +1M:한달후, +1Y:일년후)
    });
   
    ///////////////////////////////////////////////////////////////

    $('input#datepicker').bind("change", (e) => {
		
        if($(e.target).val() != ""){
            $(e.target).next().hide();
        }

    }); // 생년월일에 마우스로 달력에 있는 날짜를 선택한 경우 이벤트 처리 한 것 

    ///////////////////////////////////////////////////////////////

    // "아이디중복확인" 을 클릭했을 때 이벤트 처리하기 시작 //
    $("img#idcheck").click(function(){
        b_idcheck_click = true;
      
        $.ajax({
            url : "idDuplicateCheck.up", 
            data : {"userid":$("input#userid").val()}, 
            type : "post", 
            async : true,  
            dataType : "json",
            success : function(json){  
                if(json.isExists){ 
                    $("span#idcheckResult").html($("input#userid").val() + " 은 이미 사용 중 이므로 다른 아이디를 입력하세요").css({"color":"red"});
                    $("input#userid").val(""); 
                }
                else{ 
                    $("span#idcheckResult").html($("input#userid").val() + " 은 사용 가능한 아이디입니다.").css({"color":"navy"});
                }
            },
            error: function(request, status, error){ // 이것은 선택사항이다.
                alert("code: "+request.status+"\n"+"message: "+request.responseText+"\n"+"error: "+error);
            }
        });
    });
    // "아이디중복확인" 을 클릭했을 때 이벤트 처리하기 끝 //

    ///////////////////////////////////////////////////////////////

    // "이메일중복확인" 을 클릭했을 때 이벤트 처리하기 시작 //
    $("span#emailcheck").click(function(){
        b_emailcheck_click = true;

        $.ajax({
            url : "emailDuplicateCheck.up", 
            data : {"email":$("input#email").val()}, 
            type : "post", 
            async : true,  
            dataType : "json", 
            success : function(json){ 
                if(json.isExists){ 
                    $("span#emailCheckResult").html($("input#email").val() + " 은 이미 사용 중 이므로 다른 이메일을 입력하세요").css({"color":"red"});
                    $("input#email").val(""); 
                }
                else{ 
                    $("span#emailCheckResult").html($("input#email").val() + " 은 사용 가능한 이메일입니다.").css({"color":"navy"});
                }
            },
            error: function(request, status, error){ // 이것은 선택사항이다.
                alert("code: "+request.status+"\n"+"message: "+request.responseText+"\n"+"error: "+error);
            }
        });
    });
    // "이메일중복확인" 을 클릭했을 때 이벤트 처리하기 끝 //

    ///////////////////////////////////////////////////////////////

    // 아이디값이 변경되면 가입하기 버튼을 클릭 시 "아이디중복확인" 을 클릭했는지 클릭안했는지를 알아보기 위한 용도 초기화 시키기 
    $("input#userid").bind("change", function(){
        b_idcheck_click = false;
    });
  
    // 이메일값이 변경되면 가입하기 버튼을 클릭 시 "이메일중복확인" 을 클릭했는지 클릭안했는지를 알아보기 위한 용도 초기화 시키기 
    $("input#email").bind("change", function(){
        b_emailcheck_click = false;
    });

}); // end of $(document).ready(function(){}) ----------

///////////////////////////////////////////////////////////////

// Function Declaration
// "가입하기" 버튼 클릭 시 호출되는 함수
function goRegister(){

    let b_requiredInfo = true;
    
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

    ///////////////////////////////////////////////////////////////

    if(!b_idcheck_click){ 
        alert("아이디 중복확인을 클릭하셔야 합니다.");
        return; 
    }

    ///////////////////////////////////////////////////////////////

    if(!b_emailcheck_click){ 
        alert("이메일 중복확인을 클릭하셔야 합니다.");
        return;
    }

    ///////////////////////////////////////////////////////////////

    if(!b_zipcodeSearch_click){ 
        alert("우편번호찾기를 클릭하셔서 우편번호를 입력하셔야 합니다.");
        return; 
    }

    ///////////////////////////////////////////////////////////////

    const postcode = $("input#postcode").val().trim();
    const address = $("input#address").val().trim();
    const detailAddress = $("input#detailAddress").val().trim();
    const extraAddress = $("input#extraAddress").val().trim();
   
    if(postcode == "" || address == "" || detailAddress == "" || extraAddress == "") { 
        alert("우편번호 및 주소를 입력하셔야 합니다.");
        return; 
    }

    ///////////////////////////////////////////////////////////////

    const radio_checked_length = $("input:radio[name='gender']:checked").length;

    if(radio_checked_length == 0){
        alert("성별을 선택하셔야 합니다.");
        return;
    }

    ///////////////////////////////////////////////////////////////

    const birthday = $('input#datepicker').val().trim();

    if(birthday == ""){
        alert("생년월일을 입력하셔야 합니다.");
        return; 
    }

    ///////////////////////////////////////////////////////////////

    const checkbox_checked_length = $("input:checkbox[id='agree']:checked").length;

    if(checkbox_checked_length == 0){
        alert("이용약관에 동의하셔야 합니다.");
        return; 
    }

    ///////////////////////////////////////////////////////////////

    const frm = document.registerFrm;
    frm.action = "memberRegister.up";
    frm.method = "post";
    frm.submit();

} // end of function goRegister() ----------

///////////////////////////////////////////////////////////////

// Function Declaration
// "취소하기" 버튼 클릭 시 호출되는 함수
function goReset(){

    $("span.error").hide();

    $("span#idcheckResult").empty();
    $("span#emailCheckResult").empty();

} // end of function goReset() ----------