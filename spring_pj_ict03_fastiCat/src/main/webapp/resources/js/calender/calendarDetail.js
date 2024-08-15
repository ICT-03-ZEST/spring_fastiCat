document.addEventListener("DOMContentLoaded", function() {
    // alert("디테일 스크립트 진입 확인 shows:" + shows); // 페이지 로드 시 shows 배열 내용 확인
    const calendarDates = document.getElementById("calendarDates"); // 캘린더 날짜 요소
    const currentMonthElement = document.getElementById("currentMonth"); // 현재 월 표시 요소
    const prevBtn = document.getElementById("prevBtn"); // 이전 달 버튼
    const nextBtn = document.getElementById("nextBtn"); // 다음 달 버튼
    const showModal = document.getElementById("showModal"); // 모달 창 요소
    const closeModal = document.getElementsByClassName("close")[0]; // 모달 닫기 버튼
    const modalShowName = document.getElementById("modalShowName"); // 모달 내 공연 이름 표시 요소
    const modalShowImage = document.getElementById("modalShowImage"); // 모달 내 공연 이미지 표시 요소
    const modalShowPlace = document.getElementById("modalShowPlace"); // 모달 내 공연 장소 표시 요소
    const modalShowDay = document.getElementById("modalShowDay"); // 모달 내 공연 날짜 표시 요소
    const modalShowTime = document.getElementById("modalShowTime"); // 모달 내 공연 시간 표시 요소
    const modalShowAge = document.getElementById("modalShowAge"); // 모달 내 공연 연령 제한 표시 요소
    const modalShowPrice = document.getElementById("modalShowPrice"); // 모달 내 공연 가격 표시 요소
    const modalShowBene = document.getElementById("modalShowBene"); // 모달 내 공연 혜택 표시 요소
    const modalShowCapacity = document.getElementById("modalShowCapacity"); // 모달 내 공연 수용 인원 표시 요소
    const modalShowTicketListTotal = document.getElementById("modalShowTicketListTotal"); // 모달 내 총 가격 표시 요소
    const modalBookButton = document.getElementById("modalBookButton"); // 모달 내 예약 버튼
    const ticketQuantity = document.getElementById("ticketQuantity"); // 티켓 수량 입력 요소
    const reserveButton = document.getElementById("reserveButton"); // 예약 버튼
    
    const listdata = []; // 공연 데이터 배열 초기화

    shows.forEach(item => {
        listdata.push({
            showNum: item.showNum, // 공연 번호
            showName: item.showName, // 공연 이름
            showPlace: item.showPlace, // 공연 장소
            showPrice: item.showPrice, // 공연 가격
            showTime: item.showTime || "미정", // 공연 시간 (기본값: "미정")
            showAge: item.showAge || "전체", // 연령 제한 (기본값: "전체")
            showBene: item.showBene, // 공연 혜택
            curCapacity: item.curCapacity, // 현재 수용 인원
            maxCapacity: item.maxCapacity, // 최대 수용 인원
            showDay: item.showDay, // 공연 날짜
            showImage: item.showImage, // 공연 이미지
            showCHK: item.showCHK, // 공연 확인 여부
            quantity: item.quantity // 티켓 수량
        });
    });

    const today = new Date(); // 오늘 날짜
    let currentYear = today.getFullYear(); // 현재 연도
    let currentMonth = today.getMonth(); // 현재 월

    const firstShowDate = new Date(listdata[0].showDay); // 첫 번째 공연 날짜
    currentYear = firstShowDate.getFullYear(); // 첫 번째 공연 연도
    currentMonth = firstShowDate.getMonth(); // 첫 번째 공연 월

    function Calendar() {
        const firstDayOfMonth = new Date(currentYear, currentMonth, 1); // 현재 월의 첫째 날
        const daysInMonth = new Date(currentYear, currentMonth + 1, 0).getDate(); // 현재 월의 마지막 날
        const startDayOfWeek = firstDayOfMonth.getDay(); // 현재 월의 첫째 날 요일

        currentMonthElement.textContent = `${currentYear}년 ${currentMonth + 1}월`; // 현재 연도 및 월 표시
        calendarDates.innerHTML = ""; // 캘린더 초기화

        const daysOfWeek = ['일', '월', '화', '수', '목', '금', '토']; // 요일 배열

        const calendarDays = document.createElement("div"); // 요일 레이블 요소
        calendarDays.classList.add("calendar-days"); // 요일 레이블 클래스 추가

        daysOfWeek.forEach(day => {
            const dayElement = document.createElement("div"); // 요일 요소
            dayElement.classList.add("day"); // 요일 클래스 추가
            dayElement.textContent = day; // 요일 텍스트 추가
            calendarDays.appendChild(dayElement); // 요일 요소 추가
        });

        const calendarDatesGrid = document.createElement("div"); // 날짜 레이블 요소
        calendarDatesGrid.classList.add("calendar-dates"); // 날짜 레이블 클래스 추가

        let showsMap = new Map(); // 날짜별 공연 정보를 저장할 맵

        for (let i = 1; i <= daysInMonth; i++) {
            let testDay = new Date(currentYear, currentMonth, i); // 테스트 날짜
            testDay.setDate(testDay.getDate() + 1); // 날짜 설정
            let formattedDate = testDay.toISOString().split('T')[0]; // 날짜 포맷 변환

            let showsForDate = listdata.filter(item => item.showDay === formattedDate); // 해당 날짜의 공연 정보 필터링
            showsMap.set(i, showsForDate); // 맵에 추가
        }

        for (let i = 0; i < startDayOfWeek; i++) {
            const emptySlot = document.createElement("div"); // 빈 슬롯 요소
            emptySlot.classList.add("date", "empty"); // 빈 슬롯 클래스 추가
            calendarDatesGrid.appendChild(emptySlot); // 빈 슬롯 추가
        }

        for (let i = 1; i <= daysInMonth; i++) {
            let testDay = new Date(currentYear, currentMonth, i); // 테스트 날짜
            let formattedDate = testDay.toISOString().split('T')[0]; // 날짜 포맷 변환

            const dateContainer = document.createElement("div"); // 날짜 컨테이너 요소
            dateContainer.classList.add("date-container"); // 날짜 컨테이너 클래스 추가

            const dateElement = document.createElement("div"); // 날짜 요소
            dateElement.classList.add("date"); // 날짜 클래스 추가

            const dateNumber = document.createElement("span"); // 날짜 숫자 요소
            dateNumber.classList.add("date-number"); // 날짜 숫자 클래스 추가
            dateNumber.textContent = i; // 날짜 숫자 텍스트 추가

            const dateWeek = document.createElement("span"); // 날짜 요일 요소
            dateWeek.classList.add("date-week"); // 날짜 요일 클래스 추가
            dateWeek.textContent = `(${daysOfWeek[testDay.getDay()]})`; // 날짜 요일 텍스트 추가

            dateElement.appendChild(dateNumber); // 날짜 요소에 숫자 추가
            dateElement.appendChild(dateWeek); // 날짜 요소에 요일 추가
            dateContainer.appendChild(dateElement); // 날짜 컨테이너에 날짜 요소 추가

            const showsForDate = showsMap.get(i); // 해당 날짜의 공연 정보
            if (showsForDate) {
                showsForDate.forEach(show => {
                    const showNameElement = document.createElement("div"); // 공연 이름 요소
                    showNameElement.classList.add("show-name"); // 공연 이름 클래스 추가
                    showNameElement.textContent = show.showName; // 공연 이름 텍스트 추가

                    // 공연이 매진된 경우
                    if (show.curCapacity >= show.maxCapacity) {
                        showNameElement.classList.add("full-capacity");
                    }

                    showNameElement.addEventListener("click", () => openModal(show)); // 클릭 시 모달 열기
                    dateContainer.appendChild(showNameElement); // 날짜 컨테이너에 공연 이름 요소 추가
                });
            }

            calendarDatesGrid.appendChild(dateContainer); // 날짜 그리드에 날짜 컨테이너 추가
        }

        calendarDates.appendChild(calendarDays); // 캘린더에 요일 추가
        calendarDates.appendChild(calendarDatesGrid); // 캘린더에 날짜 그리드 추가
    }

    Calendar(); // 캘린더 함수 호출

    prevBtn.addEventListener("click", () => {
        currentMonth--;
        if (currentMonth < 0) {
            currentMonth = 11;
            currentYear--;
        }
        Calendar();
    });

    nextBtn.addEventListener("click", () => {
        currentMonth++;
        if (currentMonth > 11) {
            currentMonth = 0;
            currentYear++;
        }
        Calendar();
    });

    function openModal(show) {
        modalShowName.textContent = show.showName; // 공연 이름
        modalShowImage.src = show.showImage; // 공연 이미지
        modalShowPlace.textContent = show.showPlace; // 공연 장소
        modalShowDay.textContent = show.showDay; // 공연 날짜
        modalShowTime.textContent = show.showTime; // 공연 시간
        modalShowAge.textContent = show.showAge; // 연령 제한
        modalShowPrice.textContent = show.showPrice; // 가격
        modalShowBene.textContent = show.showBene; // 혜택
        modalShowCapacity.textContent = `${show.curCapacity} / ${show.maxCapacity}`; // 수용 인원

        let totalTicketPrice = show.showPrice * (parseInt(ticketQuantity.value) || 1); // 총 티켓 가격 계산
        modalShowTicketListTotal.textContent = `${totalTicketPrice}`; // 총 가격 표시

        modalShowCapacity.classList.toggle("full-capacity", show.curCapacity >= show.maxCapacity); // 매진 여부에 따른 클래스 추가/제거
        showModal.style.display = "block"; // 모달 창 표시
    }

    closeModal.addEventListener("click", () => showModal.style.display = "none"); // 모달 닫기 버튼 클릭 시 모달 창 닫기

    ticketQuantity.addEventListener("input", function() {
        if (showModal.style.display === "block") {
            let showPrice = listdata.find(show => show.showName === modalShowName.textContent).showPrice; // 현재 모달에 표시된 공연의 가격 찾기
            let quantity = parseInt(ticketQuantity.value) || 1; // 수량 값 가져오기
            modalShowTicketListTotal.textContent = `${showPrice * quantity}`; // 총 가격 업데이트
        }
    });
 
    reserveButton.addEventListener("click", () => {
    	
    	// inputData의 값을 form의 hidden input 필드에 설정
        document.getElementById("showNumInput").value = document.getElementById("modalShowName").dataset.showNum;
        document.getElementById("totalPriceInput").value = modalShowTicketListTotal.textContent;
        document.getElementById("reservationDateInput").value = modalShowDay.textContent;
        document.getElementById("showNameInput").value = modalShowName.textContent;
        // form 제출
        document.reservationForm.action = "reservation.do";
        document.reservationForm.submit();
    });
});
