<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/views/common/setting.jsp"%> 
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>commentAction</title>
</head>
<body>
	<script>
		location.href='${path}/boardDetail.bc?board_category=${board_category}&board_num=${board_num}&pageNum=${pageNum}';  
	</script>
</body>
</html>