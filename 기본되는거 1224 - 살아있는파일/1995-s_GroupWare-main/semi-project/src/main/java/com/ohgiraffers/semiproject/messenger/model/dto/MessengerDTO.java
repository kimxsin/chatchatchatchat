package com.ohgiraffers.semiproject.messenger.model.dto;

import lombok.*;

@NoArgsConstructor
@Getter
@Setter
@AllArgsConstructor
@ToString
public class MessengerDTO {
    private String empCode; // 사원 코드
    private String empName; // 사원 이름
}
