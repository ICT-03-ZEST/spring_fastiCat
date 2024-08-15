<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page import="java.util.Date"%>
<%@ page import="java.util.List"%>
<%@ page import="com.spring.ict03_fastiCat.dto.ShowDTO"%>
<%@ include file="../common/setting.jsp"%>
<%@ page import="javax.servlet.http.HttpSession"%>

<!DOCTYPE html>
<html lang="ko">
<head>
<meta charset="UTF-8">
<meta name="_csrf" content="${_csrf.token}">
<title>달력 및 데이터 처리 예제</title>
<link rel="stylesheet" href="${path}/resources/css/calender/detail.css" />
<link rel="stylesheet"
	href="${path}/resources/css/showTicket/showTicketDetail.css" />
<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
<script src="${path}/resources/js/calender/calendarDetail.js"></script>
<link rel="stylesheet" href="${path}/resources/css/common/header.css">
</head>
<body>

	<%
	Date minDayValue = null;
	Date maxDayValue = null;
	List<ShowDTO> showList = (List<ShowDTO>) request.getAttribute("showList");

	for (ShowDTO show : showList) {
		Date showDay = show.getShowDay();
		if (minDayValue == null || showDay.compareTo(minDayValue) < 0) {
			minDayValue = showDay;
		}
		if (maxDayValue == null || showDay.compareTo(maxDayValue) > 0) {
			maxDayValue = showDay;
		}
	}

	// JSP 변수에 설정
	pageContext.setAttribute("minDay", minDayValue);
	pageContext.setAttribute("maxDay", maxDayValue);
/* --임시 세션 저장값-------------------------------------------------------*/
    // 현재 세션을 가져옵니다. 세션이 없으면 새로 생성합니다.
    //HttpSession session1 = request.getSession(true);
    
    // 세션에 sessionID를 hong1234로 저장합니다.
    //session1.setAttribute("sessionID", "hong1234");
/* -------------------------------------------------------------*/
    
    
	%>

	<div>
		<%@ include file="../common/header.jsp"%></div>
	
	<div class="content-container">
		<div class="content-left">
			<table>
				<tr>
					<th colspan="8"><h3>${dto.showName}</h3></th>
				</tr>
				<tr>
					<td rowspan="6" colspan="5"><img class="table-img"
						src="${dto.showImage}"
						alt="Show Image"></td>
					<td>장소</td>
					<td colspan="2">${dto.showPlace}</td>
				</tr>
				<tr>
					<td>공연기간</td>
					<td colspan="2">${minDay}~ ${maxDay}</td>
				</tr>
				<tr>
					<td>공연시간</td>
					<td colspan="2">${dto.showTime}분</td>
				</tr>
				<tr>
					<td>관람연령</td>
					<td colspan="2">${dto.showAge}</td>
				</tr>
				<tr>
					<td>가격</td>
					<td colspan="2">${dto.showPrice}</td>
				</tr>
				<tr>
					<td>혜택</td>
					<td colspan="2">${dto.showBene}</td>
				</tr>
			</table>
		</div>
		<div class="content-right">
			<div class="calendar-container">
				<div class="calendar-header">
					<button id="prevBtn">이전</button>
					<h2 id="currentMonth"></h2>
					<button id="nextBtn">다음</button>
				</div>
				<div id="calendarDates"></div>
			</div>
		</div>
	</div>
	
	<div class="imgContent">
		<img class="table-imgPopup" width="1000px" src="${path}/resources/images/show/패스티켓예매상세정보.jpg" alt="Show Image">
	</div>
	<!-- 모달 구조 -->
	<div id="showModal" class="modal">
	<input type="hidden" id="sessionID" value="${sessionID}">
		<div class="modal-content">
			<span class="close">&times;</span>
			<div class="modaa-left">
				<form name="reservationForm" method="post">
				<input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}">
					<input type="hidden" name="hiddenShowNum" id="showNumInput">
					<input type="hidden" name="hiddenAmount" id="amountInput"><!-- ticketQuantity -->
				    <input type="hidden" name="hiddenTotalPrice" id="totalPriceInput">
				    <input type="hidden" name="hiddenReservation_date" id="reservationDateInput">
					<input type="hidden" name="hiddenShowName" id="showNameInput">
					<table>
						<tr>
							<th colspan="8">
								<h3 id="modalShowName" data-show-num="${dto.showNum}"></h3>
							</th>
						</tr>
						<tr>
							<td rowspan="6" colspan="3"><img id="modalShowImage"
								class="table-img" alt="Show Image"></td>
							<td class="table-header">장소</td>
							<td colspan="4" id="modalShowPlace"></td>
						</tr>
						<tr>
							<td class="table-header">공연일</td>
							<td colspan="4" id="modalShowDay"></td>
						</tr>
						<tr>
							<td class="table-header">공연시간</td>
							<td colspan="4" id="modalShowTime">분</td>
						</tr>
						<tr>
							<td class="table-header">관람연령</td>
							<td colspan="4" id="modalShowAge"></td>
						</tr>
						<tr>
							<td class="table-header">가격</td>
							<td colspan="4" id="modalShowPrice"></td>
						</tr>
						<tr>
							<td class="table-header">혜택</td>
							<td colspan="4" id="modalShowBene"></td>
						</tr>
						<tr>
						<c:if test="${selectCnt < 2}">
							<td colspan="3" id="reserveButton">예약 하기</td>
						</c:if>
						<c:if test="${selectCnt >= 2}">
							<td colspan="3">예매불가(공연당 2매까지 예매가능)</td>
						</c:if>
							<td colspan="3" class="table-header">인원현황<br>[현인원/최대인원]
							</td>
							<td colspan="2" id="modalShowCapacity"></td>
						</tr>
						<tr>
							<td class="table-header">가격</td>
							<td colspan="3" id="modalShowTicketPrice">
								<label for="ticketQuantity">매수:</label> 
								<input type="number" id="ticketQuantity" name="ticketQuantity" min="1" max="2" value="1">
							</td>
							<td class="table-header">합계</td>
							<td colspan="3">총<span id="modalShowTicketListTotal"></span>원</td>
						</tr>
					</table>
				</form>
			</div>
		</div>
	</div>
	
	<!-- footer 시작 -->
	<%@include file="../common/footer.jsp"%>
	<!-- footer 끝 -->
	<script>
var shows = [
    <c:forEach var="item" items="${showList}" varStatus="status">
        {
            "showNum": "<c:out value='${item.showNum}'/>",
            "showName": "<c:out value='${item.showName}'/>",
            "showPlace": "<c:out value='${item.showPlace}'/>",
            "showPrice": "<c:out value='${item.showPrice}'/>",
            "showTime": "<c:out value='${item.showTime}'/>",
            "showAge": "<c:out value='${item.showAge}'/>",
            "showBene": "<c:out value='${item.showBene}'/>",
            "curCapacity": "<c:out value='${item.curCapacity}'/>",
            "maxCapacity": "<c:out value='${item.maxCapacity}'/>",
            "showImage": "<c:out value='${item.showImage}'/>",
            "showDay": "<c:out value='${item.showDay}'/>",
            "show": "<c:out value='${item.show}'/>"
        }${status.last ? '' : ','}
    </c:forEach>
];
var path = '<c:out value="${path}" />';

	
</script>
</body>
</html>
