<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/views/common/setting.jsp"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>나의 게시판</title>
    <link rel="stylesheet" type="text/css" href="${path}/resources/css/customer/myBoardList.css">
    <script src="https://kit.fontawesome.com/e3f7bcf3d6.js" crossorigin="anonymous"></script>

	<script type="text/javascript">
	
    window.onload = function() {
    	
    	//시작 테이블 토글	
    	toggleTable("review_table", 1);
        
    };
    
	function pwdChk() {
		   
		   let param = {
			  "password": $('#pwd_chk').val(),
			  "page": "board"
		   };
		   
		   $('#pwd_chk').val('');
		   
		   $.ajax({
	           url :'${path}/pwdChk.myp' ,         //3.
	           type : 'POST',
	           data : param,                  //요청데이터 형식(html,xml,json,text)
	           success : function(data){      //6. 콜백함수 - 전송성공시의 결과가 result에 전달된다.
	        	  let result = document.getElementById("bd_del_chk_popup");
	         	  result.innerHTML = data;
	           },
	           error : function(){
	              alert('bdDelPwdChk() 오류');
	           }
	        });
		   
	   }
	
	function deleteConfirm() {
			
		let num_list = [];
		let category = $('input[name="board_category"]:checked').val();
		
		$('#table input[type="checkbox"]:checked').each(function() {
            
          	 // ID에서 특정 문자 제거
             let id = $(this).attr('id').replace('_chkBox', '');
             num_list.push(id);
         });
		
		// 삭제할 항목이 선택되지 않은 경우
	    if (num_list.length === 0) {
	        alert("선택된 항목이 없습니다.");
	        delChkClosePopup();
	        return;
	    }
		
             
          //여기부터 작업
             $.ajax({
           	    url: '${path}/BoardDeleteAction.myp',
	           	 type: 'POST',
	             traditional: true,
	             data: { 'num_list': num_list, 'category': category },
	             success: function(response) {
	                 alert("삭제가 완료되었습니다.");
	                 bdDelChkClosePopup();
	                 toggleTable(category, 1);
	             },
	             error: function(xhr, status, error) {
	                 console.error(xhr.responseText); // 에러 응답 내용을 콘솔에 출력
	                 alert('삭제 중 오류가 발생했습니다.'); // 사용자에게 일반적인 오류 메시지 표시
	             }
	         });
	   }
	
	//테이블 토글
	 function toggleTable(category, pageNum) {
        
        let param = {
        	  "pageNum" : pageNum,
  			  "category": category
  		};
  		   
  		   $.ajax({
  	           url :'${path}/myBoardTable.myp' ,         //3.
  	           type : 'POST',
  	           data : param,                  //요청데이터 형식(html,xml,json,text)
  	           success : function(data){      //6. 콜백함수 - 전송성공시의 결과가 result에 전달된다.
  	        	  let result = document.getElementById("table-container");
  	         	  result.innerHTML = data;
  	           },
  	           error : function(){
  	              alert('toggleTable() 오류');
  	           }
  	        });
    }
	
	//취소 버튼 누를 시
	 function returnPwdChk() {
	        
  		   $.ajax({
  	           url :'${path}/returnPwdChk.myp' ,         //3.
  	           type : 'POST',
  	           success : function(data){      //6. 콜백함수 - 전송성공시의 결과가 result에 전달된다.
  	        	  let result = document.getElementById("bd_del_chk_popup");
  	         	  result.innerHTML = data;
  	           },
  	           error : function(){
  	              alert('returnPwdChk() 오류');
  	           }
  	        });
    }
	
	
	</script>
</head>
<body>	
	<!-- header 시작-->
	<%@ include file="/WEB-INF/views/common/header.jsp"%>
	<!-- header 끝-->
	
	
    <div class="container_box">
		<div class="container">
           	<div class="board_category">
	            <label>
	                <input type="radio" id="review_category" name="board_category" value="review_table" onclick="toggleTable('review_table',1)" checked>
	               	 공연후기
	            </label>
	            <label>
	                <input type="radio" id="free_category" name="board_category" value="free_table" onclick="toggleTable('free_table',1)">
	                	자유
	            </label>
	            <label>
	                <input type="radio" id="myComment_category" name="board_category" value="comment_table" onclick="toggleTable('comment_table',1)">
	                	내 댓글
	            </label>
        	</div>
        	
        	<!-- 테이블 컨테이너 -->
        	<div id="table-container" class="table-container">
        	</div>
			
		    <div class="backMyPage" align="center">
			    <div class="writing">
			        <input type="button" name="boardWrite" class="write" value="글쓰기" onclick="window.location='${path}/myWriting.bc'">
			        <input type="button" name="delete" class="delete" value="삭제" onclick="openPopup()">
			    </div>
			    <!-- 목록으로 돌아가기  - 소연-->
				<button class="btn_backmypage" onclick="window.location='${path}/mypage.myp'">마이페이지</button>
    		</div>
		</div>
	</div>
	
	<!-- 게시글 삭제 본인 확인 -->
    <div id="bd_del_chk_popup" class="bd_del_chk_popup"></div>
	
  	<!-- footer 시작-->
	<%@ include file="/WEB-INF/views/common/footer.jsp"%>
	<!-- footer 끝-->
	
  <script type="text/javascript">
	//게시글 삭제 확인 팝업
	function openPopup() {
		returnPwdChk()
		
	    document.getElementById('bd_del_chk_popup').style.display = 'block';
	    $('.dis_btn').prop('disabled', true);
	    $(".page_out").css("opacity","30%");
	}
	
	function closePopup() {
	    document.getElementById('bd_del_chk_popup').style.display = 'none';
	    $('.dis_btn').prop('disabled', false);
	    $(".page_out").css("opacity","");
	}
  </script>
		
</body>
</html>