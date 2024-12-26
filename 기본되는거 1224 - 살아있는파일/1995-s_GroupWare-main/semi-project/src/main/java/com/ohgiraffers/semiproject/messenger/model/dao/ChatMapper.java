package com.ohgiraffers.semiproject.messenger.model.dao;

import com.ohgiraffers.semiproject.messenger.model.dto.ChatDTO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface ChatMapper {

    void save(ChatDTO chat);

    List<ChatDTO> findChatHistory(@Param("senderCode") String senderCode, @Param("receiverCode") String receiverCode);

}
