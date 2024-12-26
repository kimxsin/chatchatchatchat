package com.ohgiraffers.semiproject.messenger.model.dto;

import lombok.*;

import java.sql.Timestamp;

@NoArgsConstructor
@Getter
@Setter
@ToString
public class ChatDTO {
    private Long id; // 메시지 ID
    private String senderCode; // 보내는 사람 사원 코드
    private String receiverCode; // 받는 사람 사원 코드
    private String message; // 메시지 내용
    private Timestamp timestamp; // 메시지 타임스탬프
}
