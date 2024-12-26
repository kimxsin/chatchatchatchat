document.addEventListener('DOMContentLoaded', function() {
    var calendarEl = document.getElementById('calendar');
    var calendar = new FullCalendar.Calendar(calendarEl, {
        initialView: 'dayGridMonth',
        locale: 'ko',
        initialDate: '2024-12-01',
        timeZone: 'Asia/Seoul', // 한국 시간대 설정
        headerToolbar: {
            left: 'prev,next today',
            center: 'title',
            right: 'dayGridMonth,dayGridWeek,dayGridDay'
        },
        events: [], // 초기 이벤트 배열
        eventDidMount: function(info) {
            // workType에 따라 배경색 지정
            let backgroundColor;
            switch (info.event.title) {
                case '출근':
                    backgroundColor = 'rgba(39,211,39,0.7)'; // 출근 배경색
                    break;
                case '조퇴':
                    backgroundColor = 'rgba(255, 0, 0, 0.7)'; // 조퇴 배경색
                    break;
                case '정상':
                    backgroundColor = 'rgba(39,211,39,0.7)'; // 정상 배경색
                    break;
                case '연장':
                    backgroundColor = 'rgba(0, 0, 255, 0.7)'; // 연장 배경색
                    break;
                case '결근':
                    backgroundColor = 'rgba(128, 128, 128, 0.7)'; // 결근 배경색
                    break;
                case '지각':
                    backgroundColor = 'rgba(113,2,246,0.7)'; // 결근 배경색
                    break;
                default:
                    backgroundColor = 'rgba(0, 0, 0, 0.7)'; // 기본 배경색
            }

            // 이벤트 요소 스타일 설정
            info.el.style.color = 'white'; // 텍스트 색상 설정
            info.el.style.backgroundColor = backgroundColor; // 배경색 설정
            info.el.style.textAlign = 'center'; // 가운데 정렬
            info.el.style.display = 'flex'; // flexbox 사용
            info.el.style.justifyContent = 'center'; // 수평 가운데 정렬
            info.el.style.alignItems = 'center'; // 수직 가운데 정렬
            info.el.style.border = 'none'; // 라인 지우기
        }
    });

    // API 호출하여 근태 데이터 가져오기
    fetch('/sidemenu/schedule/getSchedule')
        .then(response => {
            if (!response.ok) {
                throw new Error('네트워크 응답이 좋지 않습니다.');
            }
            return response.json();
        })
        .then(data => {
            // 데이터를 캘린더 이벤트 형식으로 변환
            data.forEach(schedule => {
                // 출근 및 퇴근 시간을 UTC로 변환
                const startTime = new Date(schedule.workStartTime).toLocaleString('sv-SE', { timeZone: 'Asia/Seoul' }).split('T')[0]; // YYYY-MM-DD 형식
                const endTime = schedule.workEndTime ? new Date(schedule.workEndTime).toLocaleString('sv-SE', { timeZone: 'Asia/Seoul' }).split('T')[0] : null; // 퇴근 시간이 없으면 null

                calendar.addEvent({
                    title: schedule.workType, // 근태 타입을 제목으로 설정
                    start: startTime, // 출근 시간
                    end: endTime, // 퇴근 시간이 없으면 null로 설정
                    allDay: true, // 하루 종일 이벤트로 설정
                    extendedProps: { // 추가 속성으로 출근 및 퇴근 시간 저장
                        workDate: schedule.workDate,
                        workStartTime: schedule.workStartTime,
                        workEndTime: schedule.workEndTime
                    }
                });
            });
            calendar.render(); // 캘린더 렌더링
        })
        .catch(error => {
            console.error('문제가 발생했습니다:', error);
        });

    // 날짜 및 시간 포맷팅 함수
    function formatDateTime(dateTime) {
        if (!dateTime) return ''; // dateTime이 없으면 빈 문자열 반환
        const date = new Date(dateTime);
        const year = date.getFullYear();
        const month = String(date.getMonth() + 1).padStart(2, '0'); // 월은 0부터 시작하므로 +1
        const day = String(date.getDate()).padStart(2, '0');
        const hours = String(date.getHours()).padStart(2, '0');
        const minutes = String(date.getMinutes()).padStart(2, '0');
        return `${year}-${month}-${day} ${hours}:${minutes}`; // YYYY-MM-DD HH:MM 형식으로 변환
    }

    // 이벤트 클릭 시 모달 표시
    calendar.on('eventClick', function(info) {
        let event = info.event;
        let startTime = formatDateTime(event.extendedProps.workStartTime);
        let endTime = formatDateTime(event.extendedProps.workEndTime);

        // 모달 내용 업데이트
        document.getElementById('modalTitle').innerText = event.title;
        document.getElementById('modalStartTime').innerText = '출근 시간: ' + startTime;
        document.getElementById('modalEndTime').innerText = endTime ? '퇴근 시간: ' + endTime : '퇴근 시간이 기록되지 않았습니다.'; // 퇴근 시간이 없을 경우 메시지 표시

        // 모달 표시
        document.getElementById('modal').style.display = 'block';
    });

    // 닫기 버튼 클릭 시 모달 닫기
    document.getElementById('closeModal').addEventListener('click', function() {
        document.getElementById('modal').style.display = 'none';
    });

    // 모달 외부 클릭 시 모달 닫기
    window.onclick = function(event) {
        let modal = document.getElementById('modal');
        if (event.target == modal) {
            modal.style.display = 'none';
        }
    };

    calendar.render();
});
