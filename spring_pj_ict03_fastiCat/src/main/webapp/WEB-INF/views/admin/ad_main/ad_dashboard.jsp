<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
    <meta name="description" content="">
    <meta name="author" content="">
    <title>관리자 - 현황조회</title>
    <script type="text/javascript" src="https://www.gstatic.com/charts/loader.js"></script>

</head>
<body id="page-top">

      			<!-- header 시작 -->
				<%@ include file="../ad_common/ad_header.jsp" %>
		     	<!-- header 끝 -->


                <!-- Begin Page Content 현황조회 대시보드 -->
                <div class="container-fluid">

                    <!-- Page Heading -->
                    <div class="d-sm-flex align-items-center justify-content-between mb-4">
                        <h1 class="h3 mb-0 text-gray-800">현황조회</h1>
                    </div>

                    <!-- Content Row -->
                    <div class="row">

						<!-- 회원가입 수 card -->
                        <div class="col-xl-3 col-md-6 mb-4">
                            <div class="card border-left-blue shadow h-100 py-2">
                                <div class="card-body">
                                    <div class="row no-gutters align-items-center">
                                        <div class="col mr-2">
                                            <div class="text-xs font-weight-bold text-blue text-uppercase mb-1">
                                               전체 회원 수 </div>
                                            <div class="h5 mb-0 font-weight-bold text-gray-800">${userCnt}</div>
                                        </div>
                                        <div class="col-auto">
                                            <i class="fas fa-users fa-2x text-gray-300"></i>
                                        </div>
                                    </div>
                                </div>
                            </div>
                        </div>
                        <!-- 전체 게시판 조회수 card -->
                        <div class="col-xl-3 col-md-6 mb-4">
                            <div class="card border-left-success shadow h-100 py-2">
                                <div class="card-body">
                                    <div class="row no-gutters align-items-center">
                                        <div class="col mr-2">
                                            <div class="text-xs font-weight-bold text-success text-uppercase mb-1">
                                                 등록된 공연 및 페스티벌 수</div>
                                            <div class="h5 mb-0 font-weight-bold text-gray-800">${showCnt}</div>
                                        </div>
                                        <div class="col-auto">
                                            <i class="fas fa-eye fa-2x text-gray-300"></i>
                                        </div>
                                    </div>
                                </div>
                            </div>
                        </div>

                        <!-- 등록된 공연 및 페스티벌 수 card -->
                        <div class="col-xl-3 col-md-6 mb-4">
                            <div class="card border-left-warning shadow h-100 py-2">
                                <div class="card-body">
                                    <div class="row no-gutters align-items-center">
                                        <div class="col mr-2">
                                            <div class="text-xs font-weight-bold text-warning text-uppercase mb-1">
                                                일주일간 등록된 게시글 수</div>
                                            <div class="h5 mb-0 font-weight-bold text-gray-800">${regCnt}</div>
                                        </div>
                                        <div class="col-auto">
                                            <i class="fas fa-music fa-2x text-gray-300"></i>
                                        </div>
                                    </div>
                                </div>
                            </div>
                        </div>

                        <!-- 일주일간 등록된 게시글 수 card -->
                        <div class="col-xl-3 col-md-6 mb-4">
                            <div class="card border-left-info shadow h-100 py-2">
                                <div class="card-body">
                                    <div class="row no-gutters align-items-center">
                                        <div class="col mr-2">
                                            <div class="text-xs font-weight-bold text-info text-uppercase mb-1">
                                  일주일 간 예매된 횟수                  </div>
                                            <div class="h5 mb-0 font-weight-bold text-gray-800">${bookCnt}</div>
                                        </div>
                                        <div class="col-auto">
                                            <i class="fas fa-clipboard-list fa-2x text-gray-300"></i>
                                        </div>
                                    </div>
                                </div>
                            </div>
                        </div>

                    </div>
                    

                    <!-- Content Row -->

                    <div class="row">
							<div class="col-xl-8 col-lg-7" >
							<div class="card shadow mb-4" style="width:800px">
								<div class="card-header py-3 d-flex flex-row align-items-center justify-content-between">
						            <h6 class="m-0 font-weight-bold text-primary">웹사이트 방문자 수 (월별)</h6>
						        </div>
						        <div class="card-body">
						        <c:forEach var="dto" items="${visit}">
						        	<input type="hidden" id="visit_date${dto.visit_date}" value="${dto.visit_date}">
						        	<input type="hidden" id="visit_count${dto.visit_count}" value="${dto.visit_count}">
						        </c:forEach>
						        	<div id="chart_div"></div>
						    	</div>
						    </div>
						</div>
  
       						<div class="card shadow mb-4">
			            		<div class="card-header py-3">
			                		<h6 class="m-0 font-weight-bold text-primary">인기 게시글 목록</h6>
			            		</div>
			            	<div class="card-body">
			               		<div class="table-responsive">
			                  	  <table class="table table-bordered" id="dataTable" width="100%" cellspacing="0">
			                        
			                            <tr>
			                                <th>제목</th>
			                                <th>작성자</th>
			                                <th>조회수</th>
			                                <th>작성일</th>
			                            </tr>
			                            <c:forEach var="dto" items="${list}">
				                            <tr>
				                                <td>${dto.board_title}</td>
				                                <td>${dto.board_writer}</td>
				                                <td>${dto.board_views}</td>
				                                <td>${dto.board_regDate}</td>
				                            </tr>
			                            </c:forEach>
			                    	</table>
			                	</div>
			           		</div>
				       	 	</div>
                    </div>

                </div>
                <!-- /.container-fluid -->
            <!-- End of Main Content 현황조회 대시보드 끝-->

    		<!-- footer 시작 -->
			<%@ include file="../ad_common/ad_footer.jsp" %>
			<!-- footer 끝 -->
			
<script type="text/javascript">
  google.charts.load('current', {packages: ['corechart']});
  google.charts.setOnLoadCallback(drawChart);
 
  function drawChart() {
	  let date = Number(document.getElementById('visit_date').value.split("/").pop());
	  let count = Number(document.getElementById('visit_count').value);
	  
      let mViews = new Array();
      mViews[0] = ['Week', '조회수'];
      
      for(let i = 1; i <= 7; i++) {
        let viewArr = [date, count];
        mViews[i] = viewArr;
      }
      
      let data = google.visualization.arrayToDataTable(mViews);
      
      let chart = new google.visualization.LineChart(document.getElementById('chart_div'));
      chart.draw(data); 
    }
</script>
</body>
</html>