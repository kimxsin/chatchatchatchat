    package com.ohgiraffers.semiproject.messenger.controller;

    import com.ohgiraffers.semiproject.home.auth.model.dto.AuthDetailes;
    import com.ohgiraffers.semiproject.main.model.dto.UserInfoResponse;
    import com.ohgiraffers.semiproject.main.model.service.UserInfoService;
    import com.ohgiraffers.semiproject.messenger.model.dto.ChatDTO;
    import com.ohgiraffers.semiproject.messenger.model.service.ChatService;
    import org.springframework.beans.factory.annotation.Autowired;
    import org.springframework.http.ResponseEntity;
    import org.springframework.messaging.handler.annotation.MessageMapping;
    import org.springframework.messaging.handler.annotation.SendTo;
    import org.springframework.security.core.Authentication;
    import org.springframework.security.core.context.SecurityContextHolder;
    import org.springframework.stereotype.Controller;
    import org.springframework.web.bind.annotation.*;

    import java.sql.Timestamp;
    import java.util.List;

    @RestController
    public class ChatController {

        private final ChatService chatService;
        private final UserInfoService userInfoService;

        @Autowired
        public ChatController(ChatService chatService, UserInfoService userInfoService) {
            this.chatService = chatService;
            this.userInfoService = userInfoService;
        }

        // 메시지 전송 메소드
        @MessageMapping("/app/send")
        @SendTo("/topic/messages")
        public ResponseEntity<Void> sendMessage(@RequestBody ChatDTO chat) {
            chat.setTimestamp(new Timestamp(System.currentTimeMillis())); // 타임스탬프 설정
            chatService.insertChat(chat); // DB에 저장
            return ResponseEntity.ok().build(); // 클라이언트에 응답
        }


        // 채팅 기록 조회
        @GetMapping("/chat/history/{senderCode}/{receiverCode}")
        public ResponseEntity<List<ChatDTO>> getChatHistory(@PathVariable String senderCode, @PathVariable String receiverCode) {
            List<ChatDTO> chatHistory = chatService.getChatHistory(senderCode, receiverCode);
            return ResponseEntity.ok(chatHistory); // 채팅 기록 반환
        }

        // 사용자 정보 반환 메소드 (이미 구현된 부분)
        @GetMapping("/chat/user/info")
        public ResponseEntity<UserInfoResponse> getUserInfo() {
            UserInfoResponse userInfo = userInfoService.getUserInfo();
            if (userInfo != null) {
                return ResponseEntity.ok(userInfo);
            } else {
                return ResponseEntity.status(401).build(); // 인증 실패
            }
        }
    }
