package com.ohgiraffers.semiproject.approval.model.dao;

import com.ohgiraffers.semiproject.manager.model.dto.VacPaymentDTO;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface ApprovalMapper {
    void insertVacForm(VacPaymentDTO vacPaymentDTO);
}
