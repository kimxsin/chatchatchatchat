package com.ohgiraffers.semiproject.manager.model.service;

import com.ohgiraffers.semiproject.manager.model.dao.ManagerApprovalMapper;
import com.ohgiraffers.semiproject.manager.model.dto.CacPaymentDTO;
import com.ohgiraffers.semiproject.manager.model.dto.OverTimeDTO;
import com.ohgiraffers.semiproject.manager.model.dto.RetirementDTO;
import com.ohgiraffers.semiproject.manager.model.dto.VacPaymentDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class ManagerApprovalService {

    @Autowired
    private final ManagerApprovalMapper managerApprovalMapper;

    public ManagerApprovalService(ManagerApprovalMapper managerApprovalMapper){
        this.managerApprovalMapper = managerApprovalMapper;
    }

    public List<CacPaymentDTO> getCacPaymentList(String code) {
        return managerApprovalMapper.getCacPaymentList(code);
    }

    public List<OverTimeDTO> getOverTimeList(String code) {
        return managerApprovalMapper.getOverTimeList(code);
    }

    public List<RetirementDTO> getRetirement(String code) {
        return managerApprovalMapper.getRetirement(code);
    }

    public List<VacPaymentDTO> getVacPayment(String code) {
        return managerApprovalMapper.getVacPayment(code);
    }

    public VacPaymentDTO getVacRequest(String documentNo) {

        return managerApprovalMapper.getVacRequest(documentNo);
    }
}
