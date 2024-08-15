document.addEventListener("DOMContentLoaded", function() {
    console.log("Shows data:", shows);

    const calendarDates = document.getElementById("calendarDates");
    const currentMonthElement = document.getElementById("currentMonth");
    const prevBtn = document.getElementById("prevBtn");
    const nextBtn = document.getElementById("nextBtn");
    
    let currentYear = new Date().getFullYear();
    let currentMonth = new Date().getMonth();

    const urlParams = new URLSearchParams(window.location.search);
    if (urlParams.has('curYear') && urlParams.has('curMonth')) {
        currentYear = parseInt(urlParams.get('curYear'), 10);
        currentMonth = parseInt(urlParams.get('curMonth'), 10) - 1;
    }

    function Calendar() {
        const firstDayOfMonth = new Date(currentYear, currentMonth, 1);
        const daysInMonth = new Date(currentYear, currentMonth + 1, 0).getDate();
        const startDayOfWeek = firstDayOfMonth.getDay();

        currentMonthElement.textContent = `${currentYear}년 ${currentMonth + 1}월`;
        calendarDates.innerHTML = "";

        const daysOfWeek = ['일', '월', '화', '수', '목', '금', '토'];
        const calendarDays = document.createElement("div");
        calendarDays.classList.add("calendar-days");

        daysOfWeek.forEach(day => {
            const dayElement = document.createElement("div");
            dayElement.classList.add("day");
            dayElement.textContent = day;
            calendarDays.appendChild(dayElement);
        });

        const calendarDatesGrid = document.createElement("div");
        calendarDatesGrid.classList.add("calendar-dates");

        let showsMap = new Map();
        for (let i = 1; i <= daysInMonth; i++) {
            let testDay = new Date(currentYear, currentMonth, i);
            let formattedDate = formatDate(testDay);

            let showsForDate = shows.filter(item => item.showDay === formattedDate);
            showsMap.set(i, showsForDate);
        }

        for (let i = 0; i < startDayOfWeek; i++) {
            const emptySlot = document.createElement("div");
            emptySlot.classList.add("date", "empty");
            calendarDatesGrid.appendChild(emptySlot);
        }

        for (let i = 1; i <= daysInMonth; i++) {
            let testDay = new Date(currentYear, currentMonth, i);
            let formattedDate = formatDate(testDay);

            const dateContainer = document.createElement("div");
            dateContainer.classList.add("date-container");

            const dateElement = document.createElement("div");
            dateElement.classList.add("date");

            const dateNumber = document.createElement("span");
            dateNumber.classList.add("date-number");
            dateNumber.textContent = i;

            const dateWeek = document.createElement("span");
            dateWeek.classList.add("date-week");
            dateWeek.textContent = `(${daysOfWeek[testDay.getDay()]})`;

            dateElement.appendChild(dateNumber);
            dateElement.appendChild(dateWeek);
            dateContainer.appendChild(dateElement);

            const showsForDate = showsMap.get(i);
            if (showsForDate && showsForDate.length > 0) {
                const showList = document.createElement("div");
                showList.classList.add("show-list");

                showsForDate.forEach(show => {
                    const showNameElement = document.createElement("div");
                    showNameElement.classList.add("show-name");
                    showNameElement.textContent = show.showName;

                    if (show.curCapacity === show.maxCapacity) {
                        showNameElement.classList.add("full-capacity");
                        showNameElement.style.pointerEvents = 'none'; // Disable click
                    } else {
                        showNameElement.addEventListener("click", () => openModal(show));
                    }

                    showList.appendChild(showNameElement);
                });
                dateContainer.appendChild(showList);
            }

            calendarDatesGrid.appendChild(dateContainer);
        }

        calendarDates.appendChild(calendarDays);
        calendarDates.appendChild(calendarDatesGrid);
    }

    function formatDate(date) {
        const year = date.getFullYear();
        const month = String(date.getMonth() + 1).padStart(2, '0');
        const day = String(date.getDate()).padStart(2, '0');
        return `${year}-${month}-${day}`;
    }

    function openModal(show) {
        const url = path + `/showTicket_Detail.do?showNum=${show.showNum}&sendShowDay=${show.showDay}&showName=${show.showName}`;
        window.location.href = url;
    }

    prevBtn.addEventListener("click", () => {
        currentMonth--;
        if (currentMonth < 0) {
            currentMonth = 11;
            currentYear--;
        }
        updateURLAndReload(); 
    });

    nextBtn.addEventListener("click", () => {
        currentMonth++;
        if (currentMonth > 11) {
            currentMonth = 0;
            currentYear++;
        }
        updateURLAndReload(); 
    });

    function updateURLAndReload() {
        const url = path + `/main.do?curYear=${currentYear}&curMonth=${currentMonth + 1}`;
        window.location.href = url;
    }

    var currentPath = window.location.pathname;
    var targetPath = path + "/main.do";

    if (currentPath === targetPath) {
        Calendar();
    }
});
