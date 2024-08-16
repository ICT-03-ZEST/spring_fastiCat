<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/views//common/setting.jsp"%>    
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<title>Concert Information</title>
<link rel="stylesheet" type="text/css" href="${path}/resources/css/category_board.css/category_Info.css">
<script src="https://kit.fontawesome.com/e3f7bcf3d6.js" crossorigin="anonymous"></script>
<script src="${path}/resources/js/request.js"></script>
<script type="text/javascript">
$(function() {
	
	//목록으로 돌아가기(새로고침)
	$('#btn_back').click(function() {
		location.href="${path}/showList.pf?showCategory=${showCategory}&pageNum=${pageNum}";
	});	

});      

</script>
</head>
<body>
  	<!-- header 시작-->
	<%@ include file="/WEB-INF/views/common/header.jsp"%>
	<!-- header 끝-->
	    <h3 class="h3_category">
		    <c:if test="${showCategory == 'festival'}">
		       	페스티벌 정보
		     </c:if>
		     <c:if test="${showCategory != 'festival'}">
		      	공연 정보
		     </c:if>
	     </h3>
     
    <div class="info" id="detail1">
    <!--상세페이지1 시작-->
    
        <div class="info_box" >  
                <!--포스터&예매버튼-->
            <div class="info_content" >  
            
	            <ul>
	                <li><div class="photo"><img src="${dto.showImage}"></div></li>
	            	<li class="showName"><h3>${dto.showName}</h3><li>
	            </ul>  
				            
	            <!--상세 정보-->  
	            <table class="descript" cellspacing="0" cellpadding="0">
	                <tr>
	                    <th>관람연령 :</th>
	                    <td>${dto.showAge}</td>
	                </tr>
	
	                <tr>
	                    <th>장소  :</th>
	                    <td>${dto.showPlace}</td>
	                </tr>
	                
					<tr>
	                    <th>공연날짜 :</th>
	                    <td>${dto.showDay}</td>
	                </tr>
	                
	                <tr>
	                    <th>공연시간 :</th>
	                    <td>${dto.showTime}분</td>
	                </tr>
	
	                <tr>
	                    <th>가격 :</th>
	                    <td>${dto.showPrice}원</td>
	                </tr>
	            </table>
	         </div>
        </div>
        <!-- 목록으로 돌아가기 -->
	    <div class="div_back">
	    	<%--  <div><a class="reservationBtn" href="${path}/showTicket_Detail.do">예매하기</a></div> --%>
	    	 <div class="btn_back_box">
	        	<button class="btn_back" id="btn_back">목록</button>   
	    	</div>
	    	<c:if test="${showCategory != 'festival'}">
		      	<div class="buy_ticket">
	    	 		<a href="${path}/showTicket_Detail.do?showNum=${dto.showNum}&sendShowDay=${dto.showDay}&showName=${dto.showName}">예매하기</a>
	    	 	</div>
		     </c:if>
	    	 
	    </div>
	</div>
    
    <!-- footer 시작-->
	<%@ include file="/WEB-INF/views/common/footer.jsp"%>
	<!-- footer 끝-->
    
</body>
</html>