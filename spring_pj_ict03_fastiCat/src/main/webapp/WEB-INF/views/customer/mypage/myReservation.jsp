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
	   
	//비밀번호 확인 화면 되돌리기
	function returnPwdChk(page) {
	
	   let param = {
	    	  "page" : page 
	 	   };
	       
	   $.ajax({
	          url :'${path}/returnPwdChk.myp?${_csrf.parameterName}=${_csrf.token}' ,         //3.
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
    
	function resCancelConfirm() {
           
		   let resNum = $('#hidden_res_num').val();
		   
           $.ajax({
         	    url: '${path}/myReservationCancelAction.myp?${_csrf.parameterName}=${_csrf.token}',
          	 	type: 'POST',
            	traditional: true,
            	data: { 'resNum': resNum },
            	success: function(response) {
                alert("취소가 완료되었습니다.");
                
                $('#hidden_res_num').val('');
                closePopup();
                location.reload();
            },
            error: function(xhr, status, error) {
                console.error(xhr.responseText); // 에러 응답 내용을 콘솔에 출력
                alert('삭제 중 오류가 발생했습니다.'); // 사용자에게 일반적인 오류 메시지 표시
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
			<input type="hidden" id="hidden_res_num" name="hidden_res_num" class="hidden_res_num" value="">
			<table id="res_table" class="table-container">
				<thead>
		        <tr>
		            <th style="width: 40px;"class="resNum">번호</th>
		            <th class="title">제목</th>
		            <th class="resName">예약자</th>
		            <th class="showDate">공연 날짜</th>
		            <th class="resDate">예매 날짜</th>
		            <th class="totalprice">결제금액</th>
		            <td style="width: 40px;" class="td_cancel_btn"></td>
		        </tr> 
				</thead>

		        <!-- 게시글이 있으면 -->
		        <tbody>
           		<c:forEach var="dto" items="${list}"> 
            		<tr>
			            <td class="resNum"> ${dto.showId} </td>
			            <td class="title">${dto.showName}</td>
			            <td class="resName">${dto.userName}</td>
			            <td class="showDate">${dto.showDate}</td>
			            <td class="resDate">${dto.resDate}</td>
			            <td class="totalPrice">${dto.totalPrice}</td>
			            <td class="td_cancel_btn">
			            	<input type="button" name="cancel" class="cancel" value="예매취소" onclick="openPopup('reservation',${dto.showId})">
			            </td>
		        	</tr> 
           		</c:forEach>
				</tbody>
				
				<tfoot>
				<tr>
           			<td colspan="7" align="center">
           				<!-- 페이징 처리 -->
           				<!-- 이전 버튼 활성화 -->
           				<c:if test="${paging.startPage > 10}">
           					<a href="${path}/myReservation.myp?pageNum=${paging.prev}">[이전]</a>
           				</c:if>
           				<!-- 페이지 번호 처리 -->
           				<c:forEach var="num" begin="${paging.startPage}" end="${paging.endPage}">
           					<a href="${path}/myReservation.myp?pageNum=${num}">${num}</a>
           				</c:forEach>
           				
           				<!-- 다음 버튼 활성화 -->
           				<c:if test="${paging.endPage < paging.pageCount}">
           					<a href="${path}/myReservation.myp?pageNum=${paging.next}">[다음]</a>
           				</c:if>
           			</td>
           		</tr>
           		</tfoot>
			</table>
			
         	<!-- 목록으로 돌아가기  - 소연-->
		    <div class="backMyPage" align="center">
				<button class="btn_backmypage" onclick="window.location='${path}/mypage.myp'">마이페이지</button>
    		</div>
		</div>
	</div>
	
	<!-- 게시글 삭제 확인 팝업 -->
    <div id="chk_popup" class="chk_popup"></div>
    
	
  	<!-- footer 시작-->
	<%@ include file="/WEB-INF/views/common/footer.jsp"%>
	<!-- footer 끝-->
	
  <script type="text/javascript">
	//예매 취소 확인 팝업
	function openPopup(page, resNum) {
		$('#hidden_res_num').val(resNum);
		returnPwdChk(page);
		  
	    document.getElementById('chk_popup').style.display = 'block';
	    $('.dis_btn').prop('disabled', true);
	    $(".page_out").css("opacity","30%");
	}
	
	function closePopup() {
	    document.getElementById('chk_popup').style.display = 'none';
	    $('.dis_btn').prop('disabled', false);
	    $(".page_out").css("opacity","");
	}
	
  </script>
		
</body>
</html>