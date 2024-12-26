package com.ohgiraffers.semiproject.approval.model.service;

import com.ohgiraffers.semiproject.approval.model.dao.ApprovalMapper;
import com.ohgiraffers.semiproject.manager.model.dto.VacPaymentDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class ApprovalService {

    private final ApprovalMapper approvalMapper;

    @Autowired
    public ApprovalService (ApprovalMapper approvalMapper){
        this.approvalMapper = approvalMapper;
    }

    @Transactional
    public void insertVacForm(VacPaymentDTO vacPaymentDTO) {
        approvalMapper.insertVacForm(vacPaymentDTO);
    }
}
