<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/views/common/setting.jsp"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>

<form name="myCommentTable">
<input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}">
<table id="table">  <!-- 가능하면 자유/ 후기 나누기-->
	<thead>
       <tr>
       	<td style="width: 20px;" class="td_chk"></td>
           <th style="width: 40px;"class="serialNum">번호</th>
           <th style="width: 80px;"class="category">카테고리</th>
           <th style="width: 103px"class="writer">글쓴이</th>
           <th style="width: 40%;"class="content">내용</th>
           <th style="width: 105px	;"class="regDate">작성일</th>
       </tr> 
	</thead>

       <!-- 게시글이 있으면 -->
       <tbody>
        	<c:forEach var="dto" items="${list}" varStatus="status"> 
         		<tr>
					<td class="td_chk"><input type="checkbox" class="chkBox" id="${dto.comment_num}_chkBox"></td>
			        <td class="serialNum"> ${status.index + 1} </td>
			        
		           	<td class="category">${dto.board_category}</td>
		           	<td class="writer">${dto.userID}</td>
		           	<td class="content">
			           	
						<a href="${path}/boardDetail.bc?board_num=${dto.board_num}&board_category=${dto.board_category}&pageNum=${paging.pageNum}&views=1">
							${dto.content}
						</a>
						
					</td>
		           	<td class="regDate">${dto.regDate}</td>
			    </tr> 
        	</c:forEach>
	</tbody>
	
	<tfoot>
	<tr>
		<td colspan="9" align="center">
			<!-- 페이징 처리 -->
			<!-- 이전 버튼 활성화 -->
			<c:if test="${paging.startPage > 10}">
				<button type="button" onclick="toggleTable('${category}', '${num}', '${keyword}')">[이전]</button>
			</c:if>
			<!-- 페이지 번호 처리 -->
			<c:forEach var="num" begin="${paging.startPage}" end="${paging.endPage}">
				<c:if test="${num != 0}">
					<button type="button" onclick="toggleTable('${category}', '${num}', '${keyword}')">${num}</button>
				</c:if>
			</c:forEach>
			
			<!-- 다음 버튼 활성화 -->
			<c:if test="${paging.endPage < paging.pageCount}">
				<button type="button" onclick="toggleTable('${category}', '${num}', '${keyword}')">[다음]</button>
			</c:if>
		</td>
	</tr>
	</tfoot>
</table>
</form>

</body>
</html>