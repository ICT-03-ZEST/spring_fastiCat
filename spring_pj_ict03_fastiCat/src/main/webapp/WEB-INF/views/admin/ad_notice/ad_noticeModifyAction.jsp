<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="../ad_common/ad_setting.jsp" %>     
<!DOCTYPE html>
<html lang="en">

<head>

    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
    <meta name="description" content="">
    <meta name="author" content="">

    <title>공지사항 수정 </title>

</head>

<body id="page-top">

      			<!-- header 시작 -->
				<%@ include file="../ad_common/ad_header.jsp" %>
		     	<!-- header 끝 -->


                <!-- Begin Page Content -->
                <div class="container-fluid">

                    <!-- Page Heading -->
                    <div class="d-sm-flex align-items-center justify-content-between mb-4">
                        <h1 class="h3 mb-0 text-gray-800">공지사항 수정</h1>
                    </div>

    			<!-- 공지사항 수정 성공 alert창 -->
                <c:if test="${updateCnt == 1}">
                  <script type="text/javascript">
                     setTimeout(function() {
                        alert("공지사항 수정 성공!!");
                        window.location="${path}/ad_noticeEdit.adnot?pageNum=${hiddenPageNum}";
                     },1000); 
                  </script>
               </c:if>
               
               <c:if test="${updateCnt != 1}">
                  <script type="text/javascript">
                     setTimeout(function() {
                        alert("공지사항 수정 실패!!");
                        window.location="${path}/ad_noticeEdit.adnot?N_Board_Num=${hidden_num}&pageNum=${hiddenPageNum}";
                     },1000); 
                  </script>
               </c:if>
                <!-- 공지사항 수정 성공 alert창 종료 -->
               
                 </div> 
                <!-- /.container-fluid -->


    		<!-- footer 시작 -->
			<%@ include file="../ad_common/ad_footer.jsp" %>
			<!-- footer 끝 -->

</body>

</html>