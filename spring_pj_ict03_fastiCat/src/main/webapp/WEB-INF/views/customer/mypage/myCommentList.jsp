<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/views/common/setting.jsp"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>내 게시글 댓글 확인</title>
    <link rel="stylesheet" type="text/css" href="${path}/resources/css/customer/myBoardList.css">
    <script src="https://kit.fontawesome.com/e3f7bcf3d6.js" crossorigin="anonymous"></script>
		
</head>
<body>	
	<!-- header 시작-->
	<%@ include file="/WEB-INF/views/common/header.jsp"%>
	<!-- header 끝-->
	
    <div class="container_box">
		<div class="container">
			<table id="comment_table" class="table-container">
				<thead>
				      <tr>
				      	<td style="width: 20px;" class="td_chk"></td>
				          <th style="width: 40px;"class="serialNum">번호</th>
				          <th style="width: 80px;"class="category">카테고리</th>
				          <th style="width: 103px"class="writer">유저ID</th>
				          <th style="width: 40%;"class="content">댓글 내용</th>
				          <th style="width: 105px	;"class="regDate">작성일</th>
				      </tr> 
				</thead>
				
				      <!-- 게시글이 있으면 -->
				      <tbody>
				       	<c:forEach var="dto" items="${list}"> 
				        		<tr>
								<td class="td_chk"><input type="checkbox" class="chkBox" id="${dto.board_num}_chkBox"></td>
						        <td class="serialNum"> ${dto.comment_num} </td>
						        
					           	<td class="category">${dto.board_category}</td>
					           	<td class="writer">${dto.userID}</td>
					           	<td class="content">
						           	
									<a href="${path}/content.bc?board_num=${dto.board_num}&board_category=${dto.board_category}&pageNum=${paging.pageNum}&views=1">
										${dto.content}
									</a>
									
								</td>
					           	<td class="regDate">${dto.regDate}</td>
						    </tr> 
				       	</c:forEach>
				</tbody>
				
				<tfoot>
				<tr>
           			<td colspan="7" align="center">
           				<!-- 페이징 처리 -->
           				<!-- 이전 버튼 활성화 -->
           				<c:if test="${paging.startPage > 10}">
           					<a href="${path}/myReceivedCommentList.myp?pageNum=${paging.prev}">[이전]</a>
           				</c:if>
           				<!-- 페이지 번호 처리 -->
           				<c:forEach var="num" begin="${paging.startPage}" end="${paging.endPage}">
           					<a href="${path}/myReceivedCommentList.myp?pageNum=${num}">${num}</a>
           				</c:forEach>
           				
           				<!-- 다음 버튼 활성화 -->
           				<c:if test="${paging.endPage < paging.pageCount}">
           					<a href="${path}/myReceivedCommentList.myp?pageNum=${paging.next}">[다음]</a>
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
		
</body>
</html>