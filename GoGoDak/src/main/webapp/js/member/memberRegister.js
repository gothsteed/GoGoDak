let b_idcheck_click = false; 
let b_emailcheck_click = false;
let b_zipcodeSearch_click = false;

$(document).ready(function(){

	$("span.error").hide();
    $("input#name").focus();
	
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
    
    if(!b_idcheck_click){ 
        alert("아이디 중복확인을 클릭하셔야 합니다.");
        return;  
    }

    if(!b_emailcheck_click){ 
        alert("이메일 중복확인을 클릭하셔야 합니다.");
        return; 
    }
    
    if(!b_zipcodeSearch_click){ 
        alert("우편번호찾기를 클릭하셔서 우편번호를 입력하셔야 합니다.");
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
   
    const radio_checked_length = $("input:radio[name='gender']:checked").length;

    if(radio_checked_length == 0){
        alert("성별을 선택하셔야 합니다.");
        return; 
    }
   
    const birthday = $('input#datepicker').val().trim();

    if(birthday == ""){
        alert("생년월일을 입력하셔야 합니다.");
        return; 
    }
  
    const checkbox_checked_length = $("input:checkbox[id='agree']:checked").length;

    if(checkbox_checked_length == 0){
        alert("이용약관에 동의하셔야 합니다.");
        return;
    }
 
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