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

<form name=myDeletePopup">
<input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}">
<c:if test="${selectCnt == 1}">
	<div class="popup-header">회원탈퇴 확인</div>
        <div class="del_popup-body"> 
            회원탈퇴시 회원님의 정보는 바로 삭제가 되며 복구할 수 없습니다. <br>
            회원탈퇴를 하시겠습니까? 
        </div>
        <div>
        	<button type="button" class="pop_button" onclick="window.location='${path}/deleteUserAction.myp';">탈퇴</button>
            <button type="button" class="pop_button" onclick="closePopup()">취소</button>
        </div>
</c:if>

<c:if test="${selectCnt != 1}">
<div class="chk_popup-body"> 
	<h3 class="popup-header">비밀번호 확인</h3>
	<div class="del_popup-body"> 
	    비밀번호가 일치하지 않습니다. 다시 입력해주세요
	    <div>
 			<input id="pwd_chk" class="pwd_chk" type="password" placeholder="비밀번호확인">
		</div>
    </div>
</div>
<div>
    <button type="button" class="pop_button" onclick="pwdChk('withdraw')">확인</button>
    <button type="button" class="pop_button" onclick="closePopup()">취소</button>
</div>
</c:if>
</form>
</body>
</html>