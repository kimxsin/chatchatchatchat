package com.ohgiraffers.semiproject.messenger.model.service;

import com.ohgiraffers.semiproject.messenger.model.dao.ChatMapper;
import com.ohgiraffers.semiproject.messenger.model.dto.ChatDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ChatService {

    @Autowired
    private ChatMapper mapper;

    // 채팅 메시지 저장
    public void insertChat(ChatDTO chat) {
        mapper.save(chat); // DB에 채팅 메시지 저장
    }

    // 사용자 간 채팅 기록 조회
    public List<ChatDTO> getChatHistory(String senderCode, String receiverCode) {
        return mapper.findChatHistory(senderCode, receiverCode); // DB에서 사용자 간 채팅 기록 조회
    }
}
