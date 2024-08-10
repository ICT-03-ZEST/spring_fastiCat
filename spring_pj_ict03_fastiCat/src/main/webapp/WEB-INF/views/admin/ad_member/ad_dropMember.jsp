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

    <title>관리자 - 탈퇴회원목록</title>


<script type="text/javascript">
    function setRestore(userid) {
    	console.log("userid:", userid); // userid 값을 콘솔에 출력하여 확인
        document.getElementById('restoreButton').setAttribute('onclick', "window.location='${path}/ad_dropMemberRestore.admember?userid=" + userid + "'");
    	
    }
</script>


</head>

<body id="page-top">

			<!-- header 시작 -->
			<%@ include file="../ad_common/ad_header.jsp" %>
	     	<!-- header 끝 -->

                <!-- Begin Page Content -->
                <div class="container-fluid">
                
                <!-- 시큐리티 -->
				<!-- <form name="ad_dropMember"> -->
				<%-- <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"> --%>
                

                    <!-- Page Heading -->
                    <h1 class="h3 mb-2 text-gray-800">탈퇴회원 관리</h1>



                    <!-- DataTales Example -->
                    <div class="card shadow mb-4">
                        <div class="card-header py-3">
                            <h6 class="m-0 font-weight-bold text-primary">탈퇴회원 목록</h6>
                        </div>
                        <div class="card-body">
                            <div class="table-responsive">
                                <table class="table table-bordered" id="dataTable" width="100%" cellspacing="0" style="table-layout: fixed;">
                                    <thead>
                                        <tr>
                                            <th style="width:5%">번호</th>
                                            <th style="width:10%">아이디</th>
                                            <th style="width:15%">비밀번호</th>
                                            <th style="width:10%">이름</th>
                                            <th style="width:10%">생년월일</th>
                                            <th style="width:10%">주소</th>
                                            <th style="width:10%">전화번호</th>
                                            <th style="width:10%">이메일</th>
                                            <th style="width:10%">가입일</th>
                                            <th style="width:10%">회원복구</th>
                                        </tr>
                                    </thead>
                                    <tfoot>
                                        <tr>
                                            <th style="width:5%">번호</th>
                                            <th style="width:10%">아이디</th>
                                            <th style="width:15%">비밀번호</th>
                                            <th style="width:10%">이름</th>
                                            <th style="width:10%">생년월일</th>
                                            <th style="width:10%">주소</th>
                                            <th style="width:10%">전화번호</th>
                                            <th style="width:10%">이메일</th>
                                            <th style="width:10%">가입일</th>
                                            <th style="width:10%">회원복구</th>
                                        </tr>
                                    </tfoot>
                                    <tbody>
                                    	<!-- 회원목록이 있으면 -->
                                    	<c:forEach var="dto" items="${list}" varStatus="status">
                                        <tr>
                                            <td style="width:5%">${status.index + 1}</td>
                                            <td style="width:10%">${dto.userid}</td>
                                            <td style="width:15%">${dto.password}</td>
                                            <td style="width:10%">${dto.username}</td>
                                            <td style="width:10%">${dto.birthday}</td>
                                            <td style="width:10%">${dto.address}</td>
                                            <td style="width:10%">${dto.hp}</td>
                                            <td style="width:10%">${dto.email}</td>
                                            <td style="width:10%">${dto.regDate}</td>
                                            <td style="width:10%"> <!-- 회원복구 모달 -->
					    					<button class="btn btn-danger" id="btnDelete" href="#" data-toggle="modal" data-target="#RestoreModal"
				                              		onclick="setRestore('${dto.userid}')">회원복구</button>
					                 		</td> 
                                        </tr>
                                 		</c:forEach>
                                    </tbody>
                                </table>
                            </div>
                        </div>
                    </div>
                    <!-- </form> -->
                </div>
                <!-- /.container-fluid -->

            <!-- footer 시작 -->
			<%@ include file="../ad_common/ad_footer.jsp" %>
			<!-- footer 끝 -->
    
    <!--  탈퇴 회원 복구 모달 restore Modal-->
    <div class="modal fade" id="RestoreModal" tabindex="-1" role="dialog" aria-labelledby="exampleModalLabel"
        aria-hidden="true">
        <div class="modal-dialog" role="document">
            <div class="modal-content">
                <div class="modal-header">
                    <h5 class="modal-title" id="exampleModalLabel">회원복구</h5>
                    <button class="close" type="button" data-dismiss="modal" aria-label="Close">
                        <span aria-hidden="true">×</span>
                    </button>
                </div>
                <div class="modal-body">정말 회원복구 처리하시겠습니까?</div>
                <div class="modal-footer">
                    <button class="btn btn-secondary" type="button" data-dismiss="modal">취소</button>
                    <button class="btn btn-primary" id="restoreButton">회원복구</button>
                </div>
            </div>
        </div>
    </div>


</body>

</html>