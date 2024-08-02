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
<!-- 자유 -->
<c:if test="${category != 'comment_table'}">
<table id="table">  <!-- 가능하면 자유/ 후기 나누기-->
	<thead>
       <tr>
       	<td style="width: 20px;" class="td_chk"></td>
           <th style="width: 40px;"class="serialNum">번호</th>
           <th style="width: 103px;"class="title">제목</th>
           <th style="width: 60px;"class="image">이미지</th>
           <th style="width: 80px;"class="category">카테고리</th>
           <th style="width: 103px"class="writer">글쓴이</th>
           <th style="width: 105px	;"class="regDate">작성일</th>
           <th style="width: 40px;"class="views">조회</th>
           <th style="width: 20px;"class="like"><i class="fa-regular fa-heart"></i></th>
       </tr> 
	</thead>

       <!-- 게시글이 있으면 -->
       <tbody>
        	<c:forEach var="dto" items="${list}"> 
         		<tr>
					<td class="td_chk"><input type="checkbox" class="chkBox" id="${dto.board_num}_chkBox"></td>
			           <td class="serialNum"> ${dto.board_num} </td>
			           <td class="title">
			           	
						<a href="${path}/content.bc?board_num=${dto.board_num}&board_category=${dto.board_category}&pageNum=${paging.pageNum}&views=1">
							${dto.board_title}
						</a>
						
					</td>
					<td class="thumnail"><img src="${dto.board_thumnail}" alt="${dto.board_thumnail}" class="thumnailImg"></td>
		           <td class="category">${dto.board_category}</td>
		           <td class="writer">${dto.board_writer}</td>
		           <td class="regDate">${dto.board_regDate}</td>
		           <td class="views">${dto.board_views}</td>
		           <td class="like"><i>${dto.board_heart}</i></td>
			      </tr> 
        	</c:forEach>
	</tbody>
	
	<tfoot>
	<tr>
		<td colspan="9" align="center">
			<!-- 페이징 처리 -->
			<!-- 이전 버튼 활성화 -->
			<c:if test="${paging.startPage > 10}">
				<button onclick="toggleTable('${category}', '${num}')">[이전]</button>
			</c:if>
			<!-- 페이지 번호 처리 -->
			<c:forEach var="num" begin="${paging.startPage}" end="${paging.endPage}">
				<c:if test="${num != 0}">
					<button onclick="toggleTable('${category}', '${num}')">${num}</button>
				</c:if>
			</c:forEach>
			
			<!-- 다음 버튼 활성화 -->
			<c:if test="${paging.endPage < paging.pageCount}">
				<button onclick="toggleTable('${category}', '${num}')">[다음]</button>
			</c:if>
		</td>
	</tr>
	</tfoot>
</table>
</c:if>
<c:if test="${category == 'comment_table'}">
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
		<td colspan="9" align="center">
			<!-- 페이징 처리 -->
			<!-- 이전 버튼 활성화 -->
			<c:if test="${paging.startPage > 10}">
				<button onclick="toggleTable('${category}', '${num}')">[이전]</button>
			</c:if>
			<!-- 페이지 번호 처리 -->
			<c:forEach var="num" begin="${paging.startPage}" end="${paging.endPage}">
				<c:if test="${num != 0}">
					<button onclick="toggleTable('${category}', '${num}')">${num}</button>
				</c:if>
			</c:forEach>
			
			<!-- 다음 버튼 활성화 -->
			<c:if test="${paging.endPage < paging.pageCount}">
				<button onclick="toggleTable('${category}', '${num}')">[다음]</button>
			</c:if>
		</td>
	</tr>
	</tfoot>
</table>
</c:if>


</body>
</html>