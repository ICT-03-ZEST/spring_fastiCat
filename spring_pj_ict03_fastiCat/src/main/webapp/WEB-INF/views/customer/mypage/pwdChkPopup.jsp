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
<form name="pwdChkPopup">
<input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}">
<div class="popup-header">비밀번호 확인</div>
        
<div class="chk_popup-body"> 
    비밀번호를 입력해주세요
    <table>
    	<tr>
    		<td>비밀번호</td>
    		<td><input id="pwd_chk" class="pwd_chk" type="text" placeholder="비밀번호확인"></td>
    	</tr>
    </table>
</div>
<div>
    <button class="pop_button" onclick="pwdChk('${page}')">확인</button>
    <button class="pop_button" onclick="closePopup()">취소</button>
</div>
</form>
</body>
</html>