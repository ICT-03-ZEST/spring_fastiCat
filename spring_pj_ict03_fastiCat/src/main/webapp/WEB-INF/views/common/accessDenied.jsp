<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<%@ include file="setting.jsp" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<!-- 반응형 웹 -->
<meta name="viewport" content="width=device-width, initial-scale=1">
<link rel="stylesheet" href="${path}/resources/css/common/header.css">
<link rel="stylesheet" href="${path}/resources/css/common/main.css">
<link rel="stylesheet" href="${path}/resources/css/common/footer.css">

<script src="https://kit.fontawesome.com/b435fbc087.js" crossorigin="anonymous"></script>
<!-- (3-3-2). 자바스크립트 소스 연결 -->
<!-- defer : 모든 html 파일을 로딩할때까지 html이 브라우저에 표시가 안되는 것을 방지 -->
<script src="${path}/resources/js/customer/main.js" defer></script>
<title>main</title>

</head>
<body>

	<div class="wrap">
		<!-- header 시작 -->
		<%@ include file="header.jsp" %>
		<!-- header 끝 -->
		
		<!-- 컨텐츠 시작 -->
		<div id="container">
			<div id="contents">
				<!-- 상단 중앙1 시작 -->
				<div id="section1" style="text-align: center; margin: 0 auto; padding: 20px;">
					<h1 align="center"> 관리자 페이지 </h1>
					<br>
					
					<!-- 시큐리티 - UserDeniedHandler에서 넘긴 errMsg 받기 -->			
					
					<c:if test="${errMsg != null}">
						<p style="font-size: 18px; margin-bottom: 20px; ">${errMsg}</p><br>
					</c:if>
					
					<c:if test="${sessionScope.sessionID != null}">
						<button value="로그아웃" class="inputButton" style="font-size: 16px; padding: 10px 20px; color: white; background-color: orange; border: none; border-radius: 5px; margin: 10px 5px;"
						onclick="window.location='logout.do'">로그아웃</button>
					</c:if>
					
					<c:if test="${sessionScope.sessionID == null}">
						<button value="로그인" class="inputButton" style="font-size: 16px; padding: 10px 20px; color: white; background-color: orange; border: none; border-radius: 5px; margin: 10px 5px;"
						onclick="window.location='login.do'">로그인</button>
					</c:if>
				</div>			
			
			</div>
		</div>
		
		<!-- 컨텐츠 끝 -->
		
		<!-- footer 시작 -->
		<%@ include file="footer.jsp" %>
		<!-- footer 끝 -->
	</div>
	
	
</body>
</html>    