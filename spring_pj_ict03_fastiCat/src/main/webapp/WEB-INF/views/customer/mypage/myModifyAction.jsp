<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/views/common/setting.jsp" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<link rel="stylesheet" type="text/css" href="${path}/resources/css/customer/myPage.css">
<script src="https://kit.fontawesome.com/e3f7bcf3d6.js" crossorigin="anonymous"></script>
<script type="text/javascript" src="https://code.jquery.com/jquery-3.7.1.min.js"></script>
<script src="${path}/resources/js/customer/join.js" defer></script>
<script type="text/javascript">
$(document).ready(function() {
    alert("로딩 작동");
    
    $('select[name="email3"]').change(function() {
        const selectedValue = $(this).val();
        if (selectedValue !== '0') {
            $('#email2').val(selectedValue);
        } else {
            $('#email2').val('');
        }
    });
}
</script>
</head>

<body>
	<c:choose>
	    <c:when test="${updateCnt eq 1}">
	        <script type="text/javascript">
	            alert("회원수정 성공!!");
	            window.location='${path}/mypage.myp';
	        </script>
	    </c:when>
	    <c:otherwise>
	        <script type="text/javascript">
	            alert("회원수정 실패!!");
	            window.location='${path}/mypage.myp';
	        </script>
	    </c:otherwise>
	</c:choose>
</body>
</html>