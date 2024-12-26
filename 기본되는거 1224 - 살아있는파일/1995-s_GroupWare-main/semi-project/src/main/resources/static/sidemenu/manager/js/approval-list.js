async function fetchApprovalData() {
    try {
        const response = await fetch('/api/approval-list');

        if (!response.ok) {
            throw new Error('Network response was not ok');
        }

        const data = await response.json();
        const approvalList = document.querySelector('.approval');
        approvalList.innerHTML = '';

        const formatDate = (dateString) => {
            const date = new Date(dateString);
            const year = date.getFullYear();
            const month = String(date.getMonth() + 1).padStart(2, '0');
            const day = String(date.getDate()).padStart(2, '0');
            return `${year}-${month}-${day}`;
        };

        const getStatus = (value) => {
            if (value === null) {
                return '확인중';
            } else if (value === 'Y') {
                return '승인';
            } else if (value === 'N') {
                return '반려';
            }
            return value;
        };

        const getProgressStatus = (code) => {
            switch (code) {
                case 'SU1':
                    return '대기중';
                case 'SU2':
                    return '진행중';
                case 'SU3':
                    return '승인';
                case 'SU4':
                    return '반려';
                default:
                    return code;
            }
        };

        const allLists = [
            ...data.cacPaymentList,
            ...data.overTimeList,
            ...data.retirementList,
            ...data.vacPaymentList
        ];

        console.log(allLists); // 데이터 구조 확인

        allLists.forEach(item => {
            const listItem = document.createElement('li');
            listItem.innerHTML = `
                <span>${item.empCode}</span>
                <span>${formatDate(item.deadLineDate)}</span>
                <span>${item.type}</span>
                <span>${getStatus(item.managerAccept)}</span>
                <span>${getStatus(item.presidentAccept)}</span>
                <span>${getProgressStatus(item.progressCode)}</span>
            `;
            listItem.dataset.id = item.documentNo; // documentNo를 ID로 설정

            // 클릭 이벤트 리스너 추가
            listItem.addEventListener('click', () => {
                // item.type이 "연차" 또는 "휴가"인지 확인
                if (item.type.includes("연차") || item.type.includes("휴가")) {
                    // 해당 URL로 이동
                    window.location.href = `/readVacRequest?no=${item.documentNo}`;
                } else if (item.type.includes("경사") || item.type.includes("조사")) {
                    // "경사" 또는 "조사"일 경우 해당 URL로 이동
                    window.location.href = `/readCacRequest?no=${item.documentNo}`;
                } else {
                    // 다른 로직이 필요할 경우 여기에 추가
                    showForm(item.documentNo);
                }
            });

            approvalList.appendChild(listItem);

            console.log(item);
        });

    } catch (error) {
        console.error('Fetch error:', error);
    }
}

async function showForm(documentNo) {
    // 페이지 이동을 위해 URL을 설정합니다.
    const url = `/readVacRequest?no=${documentNo}`; // documentNo를 포함한 URL

    // 페이지를 해당 URL로 이동합니다.
    window.location.href = url;
}

// 페이지가 로드될 때 결재 정보 가져오기
window.onload = fetchApprovalData;
