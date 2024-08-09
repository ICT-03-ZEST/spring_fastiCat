<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/views/common/setting.jsp" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<form name="myDeleteAction">
<input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}">

	<c:choose>
	    <c:when test="${deleteCnt eq 1}">
	        <script type="text/javascript">
	            alert("회원탈퇴 성공!!");
	            window.location='${path}/main.do';
	        </script>
	    </c:when>
	    <c:otherwise>
	        <script type="text/javascript">
	            alert("회원탈퇴 실패!!");
	            window.location='${path}/mypage.myp';
	        </script>
	    </c:otherwise>
	</c:choose>
</form>

</body>
</html>