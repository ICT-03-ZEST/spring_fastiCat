<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/views/common/setting.jsp"%>  
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<script src="https://kit.fontawesome.com/e3f7bcf3d6.js" crossorigin="anonymous"></script>
<!-- (3-3-2)  자바스크립트 소스 연결 -->
<!--defer : 웹브라우저의 자바스크립트 해석기가 <body> 태그를 해석하면서 동시에 외부 자바스크립트 파일을 가져온다. 
			웹 브라우저 <body> 영역의 내용이 모두 출력되면 외부 스크립트 파일을 실행  -->
			
<script src="${path}/resources/js/customer/main.js" defer></script>

<title>이메일 인증</title>

</head>
<body>
	<!-- '/' 는 webapp의 의미 -->
	<div class="wrap">
		<div class="wrap">
		<!-- header 시작  -->
		<%@ include file="/WEB-INF/views/common/header.jsp" %>
		<!-- header 끝  -->
		
		<!-- 컨텐츠 시작 -->
		<!-- 서비스에서 넣은 값을 받는다 -->

		<c:if test="${insertCnt == 1}">
			<script type="text/javascript">
				alert("이메일 인증이 완료되었습니다. ");
				window.location='login.do'; 
			</script>
		</c:if>
		
		<c:if test="${insertCnt != 1}">
			<script type="text/javascript">
				alert("이메일 인증 실패");
				window.location='login.do'; 
			</script>
		</c:if>	
		
		<!-- 컨텐츠 끝 -->
		
		<!-- footer 시작  -->
		<%@ include file="/WEB-INF/views/common/footer.jsp" %>		
		<!-- footer 끝  -->
	</div>
		
	</div>
</body>
</html>