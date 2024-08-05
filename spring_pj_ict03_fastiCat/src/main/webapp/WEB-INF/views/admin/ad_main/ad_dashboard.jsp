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
<script src="https://kit.fontawesome.com/e3f7bcf3d6.js" crossorigin="anonymous"></script>
<script type="text/javascript" src="https://www.gstatic.com/charts/loader.js"></script>
<script src="${path}/resources/js/jquery-3.7.1.min.js"></script>
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
                        <!-- 등록된 공연 및 페스티벌 수 -->
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

                        <!-- 일주일간 등록된 게시글 수-->
                        <div class="col-xl-3 col-md-6 mb-4">
                       
                            <div class="card border-left-warning shadow h-100 py-2">
                                <div class="card-body">
                                    <div class="row no-gutters align-items-center">
                                        <div class="col mr-2">
                                            <div class="text-xs font-weight-bold text-warning text-uppercase mb-1">
              	<a href="#" id="boardBtn"> 일주일간 등록된 게시글 수(chart)</a></div>
                                            <div class="h5 mb-0 font-weight-bold text-gray-800">${regCnt}</div>
                                        </div>
                                        <div class="col-auto">
                                            <i class="fas fa-music fa-2x text-gray-300"></i>
                                        </div>
                                    </div>
                                </div>
                            </div>
                           
                        </div>

                        <!-- 일주일 간 예매된 수량 -->
                        <div class="col-xl-3 col-md-6 mb-4">
                        <a href="#" id="bookBtn">
                            <div class="card border-left-info shadow h-100 py-2">
                                <div class="card-body">
                                    <div class="row no-gutters align-items-center">
                                        <div class="col mr-2">
                                            <div class="text-xs font-weight-bold text-info text-uppercase mb-1">
              <a href="#" id="reservBtn">일주일 간 예매된 수량(chart)</a></div>
                                            <div class="h5 mb-0 font-weight-bold text-gray-800">${bookCnt}</div>
                                        </div>
                                        <div class="col-auto">
                                            <i class="fas fa-clipboard-list fa-2x text-gray-300"></i>
                                        </div>
                                    </div>
                                </div>
                            </div>
                           </a>
                        </div>
                    </div>
                    
                    <!-- Content Row -->

                    <div class="row">
						<div class="col-xl-8 col-lg-7" >
							<div class="card shadow mb-4" style="width:800px">
								<div class="card-header py-3 d-flex flex-row align-items-center justify-content-between">
						            <h6 class="m-0 font-weight-bold text-primary">
					<a href="#" id="totalChart"> 웹사이트 현황 차트 </a>
						            	<span style=margin:20px><i class="fa-solid fa-caret-left" onclick="changeWeek(-1)"></i></span>
	    								<span><i class="fa-solid fa-caret-right" onclick="changeWeek(1)"></i></span>
						            </h6>
						            <span id="numOfvisit" style="display: none">방문자 수</span>
						            <span id="numOfboard" style="display: none">게시물 수</span>
						            <span id="numOfreserv" style="display: none">예매 수량</span>
						        </div>
						        		
						        	
						        <div class="card-body"> 
							        <div id="visitChart">
							        <c:forEach var="dto" items="${visit}"> <!-- 방문자수 -->
							        	<input type="hidden" class="visit_date" value="${dto.visit_date}">
							        	<input type="hidden" class="visit_count" value="${dto.visit_count}">
							        </c:forEach>
							        </div>
							        
							        <div id="boardChart">
							         <c:forEach var="dto" items="${boardCnt}"> <!-- 게시글 등록수 -->
							        	<input type="hidden" class="board_date" value="${dto.board_regDate}">
							        	<input type="hidden" class="board_count" value="${dto.board_count}">
							        </c:forEach> 
							        </div>
							        
							        <div id="reservChart">
							         <c:forEach var="dto" items="${reservCnt}"> <!-- 일일 예매수량 -->
							        	<input type="hidden" class="reserv_date" value="${dto.reservation_date}">
							        	<input type="hidden" class="reserv_count" value="${dto.reserv_count}">
							        </c:forEach> 
							        </div> 
						        
						        	<div id="chart_div"></div>
						    	</div>
						    </div>
						    <a href="#" id="visitBtn">방문자 수</a>
						</div>
  						
       						<div class="card shadow mb-4">
			            		<div class="card-header py-3">
			                		<h6 class="m-0 font-weight-bold text-primary">인기 게시글 목록</h6>
			            		</div>
				            	<div class="card-body">
				               		<div class="table-responsive">
				                  	  <table class="table table-bordered" id="dataTable" width="100%" cellspacing="0">
				                            <tr>
				                            	<th>번호</th>
				                                <th>제목</th>
				                                <th>작성자</th>
				                                <th>조회수</th>
				                                <th>작성일</th>
				                            </tr>
				                            <c:forEach var="dto" items="${list}">
					                            <tr>
					                            	<td>${dto.board_num}</td>
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

$(function() {
	//전체 차트
	 $('#totalChart').click(function() {
		 showVisitChartOnly = true;
		 showBoardChartOnly = true;
		 showReservChartOnly = true;
		 $('#numOfvisit').hide();
		 $('#numOfreserv').hide();
		 $('#numOfboard').hide();
		 
	    charts(); 
	 }); 
	 //방문자수 차트
	 $('#visitBtn').click(function() {
	  
	   $('#boardChart').hide();
	   $('#reservChart').hide();
	   
	   $('#numOfreserv').hide();
	   $('#numOfboard').hide();
	   
	   $('#numOfvisit').show();
	   $('#visitChart').show();
	    // 게시글 차트를 표시
	   showBoardChartOnly = false;
	   showReservChartOnly= false;
	   showVisitChartOnly = true; 
	   charts();
	}); 
	 
	 //게시글 차트
 	$('#boardBtn').click(function() {
	   $('#visitChart').hide(); 
	   $('#reservChart').hide();
	   
	   $('#numOfreserv').hide();
	   $('#numOfvisit').hide();
	   
	   $('#numOfboard').show();
	   $('#boardChart').show(); // 게시글 차트를 표시
	   showVisitChartOnly = false;
	   showReservChartOnly= false;
	   showBoardChartOnly = true; 
	   charts();
	}); 
	 
 	//예매수량 차트
 	$('#reservBtn').click(function() {
 	   $('#visitChart').hide(); // 방문자 수 차트를 숨김
 	   $('#boardChart').hide();
 	   
 	   $('#numOfboard').hide();
 	  $('#numOfvisit').hide();
 	  
 	   $('#numOfreserv').show();
 	   $('#reservChart').show(); // 예매수량 차트를 표시
 	   showVisitChartOnly = false;
 	   showBoardChartOnly = false; 
 	   showReservChartOnly= true;
 	   charts(); 
 	}); 
});
    google.charts.load('current', {packages: ['corechart']});
    google.charts.setOnLoadCallback(drawChart);
    
    // 페이지 로드 시 오늘 날짜로 그래프를 그립니다.
    window.onload = function() {
      currentWeekStart = new Date(today);
      charts();
    }
    
    let currentWeekStart = new Date();
    let today = new Date();
  	// 기본값
    let showVisitChartOnly = true;
    let showBoardChartOnly = true; 
    let showReservChartOnly = true;

    function charts() {
      let visit_d = document.getElementsByClassName('visit_date');
      let visit_c = document.getElementsByClassName('visit_count'); 
      
      let board_d = document.getElementsByClassName('board_date');
      let board_c = document.getElementsByClassName('board_count'); 
      
      let reserv_d = document.getElementsByClassName('reserv_date');
      let reserv_c = document.getElementsByClassName('reserv_count'); 

      drawChart(visit_d, visit_c, board_d, board_c, reserv_d, reserv_c);
    }

    function drawChart(visitDates, visitCounts, boardDates, boardCounts, reservDates, reservCounts) {
      let data = [['Date', '방문자 수','게시물 수', '예매 수량']];
      let uniqueDates = new Set(); 
      let endOfWeek = new Date(currentWeekStart); 
      let startOfWeek = new Date(endOfWeek);
      startOfWeek.setDate(endOfWeek.getDate() - 7);
      // 방문자수
      let visitMap = new Map();
      for (let i = 0; i < visitDates.length; i++) {
          let dateStr = visitDates[i].value;
          let count = Number(visitCounts[i].value);
          visitMap.set(dateStr, count);
      }
      //게시글 수
      let boardMap = new Map();
      for (let i = 0; i < boardDates.length; i++) {
          let dateStr = boardDates[i].value;
          let count = Number(boardCounts[i].value);
          boardMap.set(dateStr, count);
      }
      //예매 수량
      let reservMap = new Map();
      for (let i = 0; i < reservDates.length; i++) {
          let dateStr = reservDates[i].value;
          let count = Number(reservCounts[i].value);
          reservMap.set(dateStr, count);
      }

      for (let d = new Date(startOfWeek); d <= endOfWeek; d.setDate(d.getDate() + 1)) {
          let dateStr = d.toISOString().split('T')[0];
          let visitCount  = showVisitChartOnly ? (visitMap.has(dateStr) ? visitMap.get(dateStr) : 0) : 0;
          let boardCount  = showBoardChartOnly ? (boardMap.has(dateStr) ? boardMap.get(dateStr) : 0) : 0;
          let reservCount = showReservChartOnly ? (reservMap.has(dateStr) ? reservMap.get(dateStr) : 0) : 0;

          data.push([new Date(d), visitCount, boardCount, reservCount]);

          // 고유한 날짜를 uniqueDates 배열에 추가
          uniqueDates.add(new Date(d));
      }

      let dataTable = google.visualization.arrayToDataTable(data);
      let options = {
        hAxis: {
          format: 'MM/dd',
          gridlines: {count: 7},
          ticks: Array.from(uniqueDates)
        },
        vAxis: {
          minValue: 0,
        },
        legend: { position: 'bottom' }
      };

      let chart = new google.visualization.LineChart(document.getElementById('chart_div'));
      chart.draw(dataTable, options);
    }

    function changeWeek(offset) {
      
      let newStart = new Date(currentWeekStart);
      newStart.setDate(currentWeekStart.getDate() + offset);

      if (newStart > today) {
          return;
      }

      currentWeekStart = newStart;
      charts();
    }
</script>
</body>
</html>