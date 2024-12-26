package com.ohgiraffers.semiproject.manager.controller;

import com.ohgiraffers.semiproject.approval.model.service.ApprovalService;
import com.ohgiraffers.semiproject.main.model.dto.UserInfoResponse;
import com.ohgiraffers.semiproject.main.model.service.UserInfoService;
import com.ohgiraffers.semiproject.manager.model.dto.VacPaymentDTO;
import com.ohgiraffers.semiproject.manager.model.service.ManagerApprovalService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class ManagerApprovalController {

    private final ManagerApprovalService managerApprovalService;
    private final ApprovalService approvalService;
    private final UserInfoService userInfoService;

    @Autowired
    public ManagerApprovalController(ManagerApprovalService managerApprovalService,
                                     ApprovalService approvalService, UserInfoService userInfoService){
        this.managerApprovalService = managerApprovalService;
        this.approvalService = approvalService;
        this.userInfoService = userInfoService;
    }

    @GetMapping("/approval/vacation-form")
    public String vacationForm(){
        return  "sidemenu/approval/vacation-form";
    }

    @PostMapping("/vacationFormRequest")
    public String vacationFormRequest(@ModelAttribute VacPaymentDTO vacPaymentDTO){

        UserInfoResponse userInfo = userInfoService.getUserInfo();

        String code = userInfo.getCode();

        vacPaymentDTO.setEmpCode(code);
        vacPaymentDTO.setOwnerCode("025"); // 대표 사번
        vacPaymentDTO.setProgressCode("SU1");

        System.out.println("vacPaymentDTO = " + vacPaymentDTO);

        approvalService.insertVacForm(vacPaymentDTO);

        return "/sidemenu/manager/approvalBox";
    }

    @GetMapping("/readVacRequest")
    public String approvalRequest(@RequestParam("no") String documentNo, Model model) {
        VacPaymentDTO response = managerApprovalService.getVacRequest(documentNo);
        UserInfoResponse userInfo = userInfoService.getUserInfo();

        String code = userInfo.getCode();

        // 포맷팅된 날짜를 모델에 추가
        model.addAttribute("formattedDeadLineDate", response.getFormattedDeadLineDate());
        model.addAttribute("formattedVacStartDate", response.getFormattedVacStartDate());
        model.addAttribute("formattedVacEndDate", response.getFormattedVacEndDate());

        model.addAttribute("vacRequestInfo", response);
        model.addAttribute("currentEmpCode", code);

        return "sidemenu/approval/readVacRequest";
    }

}
