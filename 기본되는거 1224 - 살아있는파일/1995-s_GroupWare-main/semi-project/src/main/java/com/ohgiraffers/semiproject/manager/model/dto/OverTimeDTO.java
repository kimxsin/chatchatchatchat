package com.ohgiraffers.semiproject.manager.model.dto;

import lombok.*;

import java.sql.Time;
import java.util.Date;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
public class OverTimeDTO {

    private int documentNo;
    private String empCode;
    private Date deadLineDate;
    private String type;
    private Time overTime;
    private String ovrTimeReason;
    private String managerAccept;
    private String presidentAccept;
    private String progressCode;
    private String adminCode;
    private String adminName;
    private String ownerCode;
}
