<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/views/common/setting.jsp"%>    
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<title>myPage</title>
<link rel="stylesheet" type="text/css" href="${path}/resources/css/customer/myPage.css">
<script src="https://kit.fontawesome.com/e3f7bcf3d6.js" crossorigin="anonymous"></script>
<script type="text/javascript" src="https://code.jquery.com/jquery-3.7.1.min.js"></script>
<script type="text/javascript">

	function updateConfirm() {
	if(validate()){
	   if($('#password').val() != $('#repassword').val()){
		   alert("비밀번호가 일치하지 않습니다!! 다시 입력하세요");
		   confirmCancel();
		   $('#repassword').focus();
		   return false;
	   }
		   
	   let param = {
		  "password": $('#password').val(),
		  "username": $('#username').val(),
		  "birthday": $('#birthday').val(),
		  "address": $('#address').val(),
		  "hp1": $('#hp1').val(),
		  "hp2": $('#hp2').val(),
		  "hp3": $('#hp3').val(),
		  "email1": $('#email1').val(),
		  "email2": $('#email2').val(),
	   };
	   
	   $.ajax({
           url :'${path}/modifyUserAction.myp' ,         //3.
           type : 'POST',
           data : param,                  //요청데이터 형식(html,xml,json,text)
           success : function(){            //6. 콜백함수 - 전송성공시의 결과가 result에 전달된다.
           	  alert("수정이 완료되었습니다.")
           	  
           	  closePopup();
           },
           error : function(){
              alert('updateConfirm() 오류');
           }
        });
	   
	 }else{
		 return false;
	 }
   }
   
   function pwdChk(page) {
	   
	   let param = {
		  "password": $('#pwd_chk').val(),
		  "page": page
	   };
	   
	   $('#pwd_chk').val('');
	   
	   $.ajax({
           url :'${path}/pwdChk.myp' ,         //3.
           type : 'POST',
           data : param,                  //요청데이터 형식(html,xml,json,text)
           success : function(data){      //6. 콜백함수 - 전송성공시의 결과가 result에 전달된다.
        	  let result = document.getElementById("chk_popup");
         	  result.innerHTML = data;
           },
           error : function(){
              alert('pwdChk() 오류');
           }
        });
	   
   }
   
	//비밀번호 확인 화면 되돌리기
	function returnPwdChk(page) {
	
	   let param = {
	    	  "page" : page 
	 	   };
	       
	   $.ajax({
	          url :'${path}/returnPwdChk.myp' ,         //3.
	          type : 'POST',
	          data : param,         
	          success : function(data){      //6. 콜백함수 - 전송성공시의 결과가 result에 전달된다.
	       	  let result = document.getElementById("chk_popup");
	        	  result.innerHTML = data;
	          },
	          error : function(){
	             alert('returnPwdChk() 오류');
	          }
	       });
	}
   
</script>
<body> <!-- 수정 6/28  9:35 -->

   	<!-- header 시작-->
	<%@ include file="/WEB-INF/views/common/header.jsp"%>
	<!-- header 끝-->

    <div class="page_out">
        <div class="page_inner">
            <div class="left_bar"> <!-- 사이드 바 -->
                <div class="left_list">
                    <div>
                        <button class="btn_board dis_btn" onclick="location.href='${path}/myBoardList.myp'">나의 게시글</button>
                    </div>
                    <div>
                        <button class="btn_res dis_btn" onclick="location.href='${path}/myReservation.myp'">예매내역 확인</button>
                    </div>
                    <div>
                        <button class="btn_mod dis_btn" onclick="openPopup('modify')">회원정보 수정</button>
                    </div>
                    <div>
                        <button class="btn_del dis_btn" onclick="openPopup('withdraw')">회원 탈퇴</button>  <!-- 탈퇴시 팝업창 취소/ 확인 -->
                    </div>
                </div>
            </div>

            <div class="center_box"> <!-- 중앙  -->
                <div class="center_inner">
                    <div class="div_img">
                        <div class="icon"><i class="fa-regular fa-circle-user"></i></div>
                    </div>
                        
                   <div class="div_text">
                        <span>${sessionID}님, 안녕하세요 </span>
                   </div>
                </div>
            </div>
        </div>
    </div>
    
    <!-- 팝업 -->
	<div id="chk_popup" class="chk_popup"></div>

    <!-- footer 시작-->
	<%@ include file="/WEB-INF/views/common/footer.jsp"%>
	<!-- footer 끝-->
	
<script>
	
	//비밀번호 확인 팝업 열기
	function openPopup(page) {
		returnPwdChk(page)
		
	    document.getElementById('chk_popup').style.display = 'block';
	    $('.dis_btn').prop('disabled', true);
	    $(".page_out").css("opacity","30%");
	}
	
	//비밀번호 확인 팝업 닫기
	function closePopup() {
	    document.getElementById('chk_popup').style.display = 'none';
	    $('.dis_btn').prop('disabled', false);
	    $(".page_out").css("opacity","");
	}
	
	//input 체크	
	function validate() {
        const password = $('#password').val();
        const repassword = $('#repassword').val();
        const username = $('#username').val();
        const birthday = $('#birthday').val();
        const address = $('#address').val();
        const hp1 = $('#hp1').val();
        const hp2 = $('#hp2').val();
        const hp3 = $('#hp3').val();
        const email1 = $('#email1').val();
        const email2 = $('#email2').val();

        if (!password) {
            alert("비밀번호를 입력해 주세요.");
            $('#password').focus();
            return false;
        }
        if (!repassword) {
            alert("비밀번호 확인을 입력해 주세요.");
            $('#repassword').focus();
            return false;
        }
        if (password !== repassword) {
            alert("비밀번호와 비밀번호 확인이 일치하지 않습니다.");
            $('#repassword').focus();
            return false;
        }
        if (!username) {
            alert("이름을 입력해 주세요.");
            $('#username').focus();
            return false;
        }
        if (!birthday) {
            alert("생년월일을 입력해 주세요.");
            $('#birthday').focus();
            return false;
        }
        if (!address) {
            alert("주소를 입력해 주세요.");
            $('#address').focus();
            return false;
        }
        if (!hp1 || !hp2 || !hp3) {
            alert("연락처를 입력해 주세요.");
            if (!hp1) {
                $('#hp1').focus();
            } else if (!hp2) {
                $('#hp2').focus();
            } else {
                $('#hp3').focus();
            }
            return false;
        }
        if (!email1 || !email2) {
            alert("이메일을 입력해 주세요.");
            if (!email1) {
                $('#email1').focus();
            } else {
                $('#email2').focus();
            }
            return false;
        }
        return true; // 모든 입력 필드가 올바르게 입력되었을 경우
    }
	

</script>
    
</body>
</html>