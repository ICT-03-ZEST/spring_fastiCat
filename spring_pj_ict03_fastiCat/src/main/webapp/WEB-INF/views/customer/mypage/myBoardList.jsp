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

</head>
<body>	
	<!-- header 시작-->
	<%@ include file="/WEB-INF/views/common/header.jsp"%>
	<!-- header 끝-->
	
<form id="myBoardList" name="myBoardList">
<input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}">

    <div class="container_box">
		<div class="container">
           	<div class="board_category">
	            <label>
	                <input type="radio" id="review_category" name="board_category" value="review_table" onclick="toggleTable('review_table',1, '')" checked>
	               	 공연후기
	            </label>
	            <label>
	                <input type="radio" id="free_category" name="board_category" value="free_table" onclick="toggleTable('free_table',1, '')">
	                	자유
	            </label>
        	</div>
        	
        	<div class="board_search">
        		<input type="text" id="search_input" name="search_input" onkeyup="onKeyUp(event)" placeholder="검색어 입력">
        		<button type="button" id="search_button" name="search_button" onclick="doSearch()">
        			<i class="fa-solid fa-magnifying-glass"></i>
        		</button>
        	</div>
        	
        	<!-- 테이블 컨테이너 -->
        	<div id="table-container" class="table-container"></div>
			
		    <div class="backMyPage">
			    <div class="writing">
			        <button type="button" name="boardWrite" class="write" onclick="window.location='${path}/boardInsert.bc'">글쓰기</button>
			        <button type="button" name="delete" class="delete" onclick="openPopup('board')">삭제</button>
			    </div>
			    <!-- 목록으로 돌아가기  - 소연-->
				<input class="btn_backmypage" onclick="window.location='${path}/mypage.myp'" value="마이페이지">
    		</div>
		</div>
	</div>
	
	<!-- 게시글 삭제 본인 확인 -->
    <div id="chk_popup" class="chk_popup"></div>
</form>
	
  	<!-- footer 시작-->
	<%@ include file="/WEB-INF/views/common/footer.jsp"%>
	<!-- footer 끝-->
	
  <script type="text/javascript">
  
	window.onload = function() {
	  	//시작 테이블 토글	
	 	toggleTable("review_table", 1, "");
	      
	};
	
	function pwdChk(page) {
		   
		   let param = {
			  "password": $('#pwd_chk').val(),
			  "page": page
		   };
		   
		   $('#pwd_chk').val('');
		   
		   $.ajax({
	           url :'${path}/pwdChk.myp?${_csrf.parameterName}=${_csrf.token}' ,         //3.
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
        	    url: '${path}/BoardDeleteAction.myp?${_csrf.parameterName}=${_csrf.token}',
	           	 type: 'POST',
	             traditional: true,
	             data: { 'num_list': num_list, 'category': category },
	             success: function(response) {
	                 alert("삭제가 완료되었습니다.");
	                 
	                 closePopup();
	                 toggleTable(category, 1, "");
	             },
	             error: function(xhr, status, error) {
	                 console.error(xhr.responseText); // 에러 응답 내용을 콘솔에 출력
	                 alert('삭제 중 오류가 발생했습니다.'); // 사용자에게 일반적인 오류 메시지 표시
	             }
	         });
	   }
	
	//테이블 토글
	 function toggleTable(category, pageNum, keyword) {
     
     let param = {
     	  "pageNum" : pageNum,
			  "category": category,
			  "keyword" : keyword,
		};
		   
		   $.ajax({
	           url :'${path}/myBoardTable.myp?${_csrf.parameterName}=${_csrf.token}' ,         //3.
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
	
	//비밀번호 확인 화면 되돌리기
	function returnPwdChk(page) {

		let param = {
		   	  "page" : page 
		  };
		    
		  $.ajax({
	         url :'${path}/returnPwdChk.myp?${_csrf.parameterName}=${_csrf.token}' ,         //3.
	         type : 'POST',
	         data : param,         
	         success : function(data){
	      	  let result = document.getElementById("chk_popup");
	       	  result.innerHTML = data;
	         },
	         error : function(){
	            alert('returnPwdChk() 오류');
	         }
		  });
	}
	
	function onKeyUp(e){
		/** 버튼 클릭이나 엔터가 클릭되었을 때*/
	    if (e.keyCode == 13){
	    	doSearch();
	    }
	}

	function doSearch(){
		 let keyword = $('#search_input').val();
		 let category = $('input[name="board_category"]:checked').val();
		 
		 toggleTable(category, 1, keyword);
	}
  
	//팝업 열기
	function openPopup(page) {
		returnPwdChk(page)
		
	    document.getElementById('chk_popup').style.display = 'block';
	    $('.dis_btn').prop('disabled', true);
	    $(".page_out").css("opacity","30%");
	}
	
	//팝업 닫기
	function closePopup() {
	    document.getElementById('chk_popup').style.display = 'none';
	    $('.dis_btn').prop('disabled', false);
	    $(".page_out").css("opacity","");
	}
  </script>
		
</body>
</html>