package com.ohgiraffers.semiproject.manager.model.dao;

import com.ohgiraffers.semiproject.manager.model.dto.CacPaymentDTO;
import com.ohgiraffers.semiproject.manager.model.dto.OverTimeDTO;
import com.ohgiraffers.semiproject.manager.model.dto.RetirementDTO;
import com.ohgiraffers.semiproject.manager.model.dto.VacPaymentDTO;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface ManagerApprovalMapper {

    List<CacPaymentDTO> getCacPaymentList(String code);

    List<OverTimeDTO> getOverTimeList(String code);

    List<RetirementDTO> getRetirement(String code);

    List<VacPaymentDTO> getVacPayment(String code);

    // 연차 휴가 결재건 불러오기
    VacPaymentDTO getVacRequest(String documentNo);
}
