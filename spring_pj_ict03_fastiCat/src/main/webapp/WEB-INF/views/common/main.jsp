<!-- 2024/07/17/11:04 -->

<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/views/common/setting.jsp" %>
<%@ page import="java.util.List" %>   
<!DOCTYPE html>
<html>
<head>
<!-- 반응형 웹 -->
<meta name="viewport" content="width=device-width, initial-scale=1">
<link rel="stylesheet" href="${path}/resources/css/common/header.css">
<link rel="stylesheet" href="${path}/resources/css/common/main.css">
<link rel="stylesheet" href="${path}/resources/css/common/footer.css">
<link rel="stylesheet" href="${path}/resources/css/customer/search.css">
<link rel="stylesheet" href="${path}/resources/css/calender/style.css" >

<script src="https://kit.fontawesome.com/e99c5d1543.js" crossorigin="anonymous"></script>
<!-- (3-3-2). 자바스크립트 소스 추가 -->
<!-- defer : 모든 html 파일을 로딩할때까지 html이 브라우저에 표시가 안되는 것을 방지 -->

<script src="${path}/resources/js/customer/main.js" defer></script>
<title>main</title>

</head>
<body>
	 
   <div class="wrap">
      <!-- header 시작 -->
	<%@include file="header.jsp" %>
      <!-- header 끝 -->
      <br>
       
      
      
      <!-- footer 시작 -->
		<%@include file="footer.jsp" %>
      <!-- footer 끝 -->
    </div>  
</body>
</html>
