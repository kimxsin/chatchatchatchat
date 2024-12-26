package com.ohgiraffers.semiproject.mail.model.dto;

import com.ohgiraffers.semiproject.employee.model.dto.EmployeeDTO;
import lombok.*;
import java.util.Date;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
public class MailDTO {

    private String emailCode; // 메일코드
    private String importantEmail; // 중요여부
    private String emailTitle; // 쪽지제목
    private String emailFile; // 첨부파일
    private String emailContent; // 내용
    private Date sendDate; // 전송일자
    private Date deleteDate; // 삭제일자
    private String address; // 메일주소
    private String mailboxNo; // 메일함번호
    private String receptionType; // 수신종류
    private String receptionStatus; // 수신상태
    private String senderId; // 발신자 사번
    private String recipientId; // 수신자 사번

}
