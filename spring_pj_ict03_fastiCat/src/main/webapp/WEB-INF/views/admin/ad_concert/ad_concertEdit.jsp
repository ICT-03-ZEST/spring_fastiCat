<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<%@ include file="../ad_common/ad_setting.jsp" %> 
<!DOCTYPE html>
<html lang="en">

<head>

    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
    <meta name="description" content="">
    <meta name="author" content="">
    <title>국내공연 관리</title>

<script type="text/javascript">
    function setShowNum(showNum) {
        document.getElementById('deleteButton').setAttribute('onclick', "window.location='${path}/ad_showDeleteAction.adshow?showCategory=공연&showNum=" + showNum + "'");
    }
</script>




</head>

<body id="page-top">

			<!-- header 시작 -->
			<%@ include file="../ad_common/ad_header.jsp" %>
	     	<!-- header 끝 -->

                <!-- Begin Page Content -->
                <div class="container-fluid">
                

                    <!-- Page Heading -->
                    <h1 class="h3 mb-2 text-gray-800">국내공연 관리</h1>



                    <!-- DataTales Example -->
                    <div class="card shadow mb-4">
                        <div class="card-header py-3 d-flex justify-content-between align-items-center">
   							<h6 class="m-0 font-weight-bold text-primary">국내공연 목록</h6>
   							<button class="btn btn-primary" id="btnInsert" onclick="window.location='ad_showAdd.adshow?showCategory=공연'">공연등록</button>
						</div>

                        <div class="card-body">
                            <div class="table-responsive">
                                <table class="table table-bordered" id="dataTable" width="100%" cellspacing="0">
                                                            <thead>
                                        <tr>
                                        	<th style="width:5%">번호</th>
                                            <th>이미지</th>
                                            <th>카테고리</th>
                                            <th>공연명</th>
                                            <th>날짜</th>
                                            <th>공연시간</th>
                                            <th>장소</th>
                                            <th>관람연령</th>
                                            <th>가격</th>
                                            <th>혜택</th>
                                            <th>현수용인원</th>
                                            <th>최대수용인원</th>
                                            <th>등록일</th>
                                            <th>관리</th>
                                        </tr>
                                    </thead>
                                    <tfoot>
                                        <tr>
                                        	<th>번호</th>
                                        	<th>이미지</th>
                                        	<th>카테고리</th>
                                            <th>공연명</th>
                                            <th>날짜</th>
                                            <th>공연시간</th>
                                            <th>장소</th>
                                            <th>관람연령</th>
                                            <th>가격</th>
                                            <th>혜택</th>
                                            <th>현수용인원</th>
                                            <th>최대수용인원</th>
                                            <th>등록일</th>
                                            <th>관리</th>
                                        </tr>
                                    </tfoot>
                                    <tbody>
                                    	<!-- 상품이 있으면 -->
			                           <c:forEach var="dto" items="${list}">
				                           <tr>
				                              <td> ${dto.showNum} </td>
				                              <td> <img src="${dto.showImage}" width="80px" height="100px"> </td>
				                              <td> ${dto.showCategory} </td>
				                              <td> ${dto.showName} </td>
				                              <td> ${dto.showDay} </td>
				                              <td> ${dto.showTime}분</td>
				                              <td> ${dto.showPlace} </td>
				                              <td> ${dto.showAge} </td>
				                              <td> ${dto.showPrice} </td>
				                              <td> ${dto.showBene} </td>
				                              <td> ${dto.curCapacity} </td>
				                              <td> ${dto.maxCapacity} </td>
				                              <td> ${dto.showIndate} </td>
				                              <td>
				                              		<button class="btn btn-secondary" id="btnEdit" onclick="window.location='${path}/ad_showModify.adshow?showCategory=공연&showNum=${dto.showNum}'">수정</button>
				                              		<button class="btn btn-danger" id="btnDelete" href="#" data-toggle="modal" data-target="#DeleteModal"
				                              		onclick="setShowNum(${dto.showNum})">삭제</button>
				                           	  </td>
				                           </tr>
			                           </c:forEach>
			                           
                                    </tbody>
                                </table>
                            </div>
                        </div>
                    </div>

                </div>
                <!-- /.container-fluid -->


            <!-- footer 시작 -->
			<%@ include file="../ad_common/ad_footer.jsp" %>
			<!-- footer 끝 -->
			
    
    <!-- 공연 삭제 모달 delete Modal-->
    <div class="modal fade" id="DeleteModal" tabindex="-1" role="dialog" aria-labelledby="exampleModalLabel"
        aria-hidden="true">
        <div class="modal-dialog" role="document">
            <div class="modal-content">
                <div class="modal-header">
                    <h5 class="modal-title" id="exampleModalLabel">공연 삭제</h5>
                    <button class="close" type="button" data-dismiss="modal" aria-label="Close">
                        <span aria-hidden="true">×</span>
                    </button>
                </div>
                <div class="modal-body">정말 삭제 하시겠습니까?</div>
                <div class="modal-footer">
                    <button class="btn btn-secondary" type="button" data-dismiss="modal">취소</button>
                    <button class="btn btn-primary" id="deleteButton">삭제</button>
                </div>
            </div>
        </div>
    </div>


</body>

</html>