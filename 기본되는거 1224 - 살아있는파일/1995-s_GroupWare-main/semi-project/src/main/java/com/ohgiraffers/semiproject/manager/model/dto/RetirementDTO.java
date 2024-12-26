package com.ohgiraffers.semiproject.manager.model.dto;

import lombok.*;

import java.util.Date;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
public class RetirementDTO {

    private int documentNo;
    private String empCode;
    private Date deadLineDate;
    private String type;
    private Date retireDate;
    private String yearService;
    private String retireReason;
    private String managerAccept;
    private String presidentAccept;
    private String progressCode;
    private String adminCode;
    private String adminName;
    private String ownerCode;
}
