var stompClient = null; // STOMP 클라이언트, WebSocket 통신을 처리
var currentUser = null; // 현재 사용자의 코드
var selectedUser = null; // 선택된 사용자의 코드

// 사용자 정보를 가져오는 함수
function fetchUserInfo() {
    fetch('/user/info')
        .then(response => {
            if (!response.ok) {
                throw new Error('사용자 정보를 가져오지 못했습니다.');
            }
            return response.json();
        })
        .then(userInfo => {
            // 사용자 정보를 DOM에 렌더링
            renderUserInfo(userInfo);
            setCurrentUser(userInfo.code); // 현재 사용자의 empCode 설정
        })
        .catch(error => {
            console.error('Error:', error);
            alert('사용자 정보를 가져올 수 없습니다. 로그인 상태를 확인하세요.');
        });
}

// 사용자 정보를 DOM에 표시하는 함수
function renderUserInfo(userInfo) {
    const profileImageElement = document.getElementById('profile-image');
    if (profileImageElement && userInfo.profilePictureUrl) {
        profileImageElement.src = userInfo.profilePictureUrl;
    }
    const userNameElement = document.getElementById('user-name');
    if (userNameElement) {
        userNameElement.textContent = userInfo.name;
    }
    const userDeptElement = document.getElementById('user-department');
    if (userDeptElement) {
        userDeptElement.textContent = userInfo.department;
    }
    const userPositionElement = document.getElementById('user-position');
    if (userPositionElement) {
        userPositionElement.textContent = userInfo.position;
    }
}

// WebSocket 연결 및 구독 설정
function connect() {
    var socket = new SockJS('/chat'); // SockJS를 이용하여 /chat 엔드포인트에 연결
    stompClient = Stomp.over(socket); // STOMP 클라이언트 초기화
    stompClient.connect({}, function (frame) {
        console.log('Connected: ' + frame); // 연결 성공 메시지 출력
        stompClient.subscribe('/topic/messages', function (message) {
            var chat = JSON.parse(message.body); // 수신한 메시지 JSON 변환
            if ((chat.senderCode === currentUser && chat.receiverCode === selectedUser) ||
                (chat.senderCode === selectedUser && chat.receiverCode === currentUser)) {
                displayMessage(chat); // 메시지 표시 함수 호출
            }
        });
        loadUsers(); // 사용자 목록 로드
    });
}

// 사용자 목록 로드
function loadUsers() {
    fetch('/employees') // 사용자 목록을 가져오는 API 호출
        .then(response => response.json()) // JSON 응답 파싱
        .then(users => {
            var userList = document.getElementById('userList');
            userList.innerHTML = ''; // 이전 목록 초기화
            users.forEach(function(user) {
                var li = document.createElement('li'); // 사용자 리스트 항목 생성
                li.appendChild(document.createTextNode(user.empName)); // 사용자 이름 표시
                li.onclick = function() {
                    console.log('Selected user:', user.empCode); // 선택된 사원 코드 출력
                    selectUser(user.empCode); // 사용자 선택 함수 호출
                };
                userList.appendChild(li); // 리스트에 사용자 추가
            });
        })
        .catch(error => console.error('Error fetching users:', error)); // 오류 처리
}

// 사용자 선택 시 처리
function selectUser(empCode) {
    selectedUser = empCode; // 선택된 사용자 코드 저장
    document.getElementById('chatWith').innerText = '채팅 상대: ' + empCode; // UI 업데이트
    loadChatHistory(); // 채팅 히스토리 로드
}

// 메시지 전송
function sendMessage() {
    var messageInput = document.getElementById('messageInput'); // 메시지 입력 필드 가져오기
    var message = messageInput.value.trim(); // 입력된 메시지 가져오기 및 공백 제거
    if (message && selectedUser) { // 메시지가 비어있지 않고 사용자가 선택된 경우
        var chatMessage = {
            senderCode: currentUser,
            receiverCode: selectedUser,
            message: message
        };
        stompClient.send("/app/send", {}, JSON.stringify(chatMessage)); // 메시지 전송
        messageInput.value = ''; // 입력 필드 초기화
    }
}

// 메시지 표시
function displayMessage(chat) {
    var chatHistory = document.getElementById('chatHistory'); // 채팅 히스토리 영역 가져오기
    var messageDiv = document.createElement('div'); // 새 메시지 요소 생성
    messageDiv.className = 'chat-message'; // 클래스를 설정
    messageDiv.innerHTML = '<span class="sender">' + chat.senderCode + ':</span> ' + chat.message; // 메시지 내용 설정
    chatHistory.appendChild(messageDiv); // 채팅 히스토리에 메시지 추가
    chatHistory.scrollTop = chatHistory.scrollHeight; // 스크롤 하단으로 이동
}

// 채팅 히스토리 로드
// 채팅 히스토리 로드
function loadChatHistory() {
    var chatHistory = document.getElementById('chatHistory');
    chatHistory.innerHTML = ''; // 기존 기록 초기화

    if (selectedUser) {
        fetch('/chat/history/' + currentUser + '/' + selectedUser)
            .then(response => response.json())
            .then(data => {
                data.forEach(function(chat) {
                    displayMessage(chat); // 메시지 표시
                });
            })
            .catch(error => console.error('Error fetching chat history:', error));
    }
}


// 현재 사용자 정보 설정
function setCurrentUser(userCode) {
    currentUser = userCode; // 현재 사용자의 empCode 설정
}

// 초기화 함수 호출
document.addEventListener('DOMContentLoaded', function () {
    fetchUserInfo(); // 사용자 정보 가져오기
    connect(); // WebSocket 연결 시작
});
